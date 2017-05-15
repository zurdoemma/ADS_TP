/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.frc.utn.avads.controller.impl;

import ar.edu.frc.utn.avads.controller.AnalisisVirus;
import ar.edu.frc.utn.avads.controller.FileScanService;
import ar.edu.frc.utn.avads.main.AvadsMain;
import static ar.edu.frc.utn.avads.igu.VentanaPrincipal.vEstadoArchivos;
import ar.edu.frc.utn.avads.model.AnalisisArchivo;
import ar.edu.frc.utn.avads.model.ExaminarArchivo;
import ar.edu.frc.utn.avads.model.ResponseScanReportVirusTotal;
import ar.edu.frc.utn.avads.model.ResponseScanVirusTotal;
import ar.edu.frc.utn.avads.model.ResultadoAnalisis;
import ar.edu.frc.utn.avads.util.AvadsUtil;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 *
 * @author ecarballo
 */
public class VirusTotal implements AnalisisVirus{
    
    private String[] archivosAnalizar;
    private ExaminarArchivo examinarArchivo;
    private FileScanService fileScanService;

    public VirusTotal(String[] archivosAnalizar) {
        
        this.archivosAnalizar = archivosAnalizar;
        examinarArchivo = new ExaminarArchivo();
        fileScanService = new VirusTotalFileScanServiceImpl();
    }
    
    public boolean enviarArchivo()
    {
        boolean resultadoEnvA = false;
        Date fechaYHora = new Date();
        
        examinarArchivo.setArchivos(archivosAnalizar);
        examinarArchivo.setEstado("Inicio");
        examinarArchivo.setFecha(AvadsUtil.getDate(fechaYHora));
        examinarArchivo.setHora(AvadsUtil.getHour(fechaYHora));
        Long idMaxProceso = AvadsMain.serviceDB.getMaxIdProceso("Examinar_Archivo", "idProceso");
        if(idMaxProceso == -1L) idMaxProceso = 1L;
        else idMaxProceso++;
        examinarArchivo.setId(idMaxProceso);
        AvadsMain.serviceDB.insertCollection("Examinar_Archivo", examinarArchivo.toJSON());
        
        for(int i=0; i<archivosAnalizar.length;i++)
        {
            AnalisisArchivo anA = new AnalisisArchivo();
            anA.setArchivo(archivosAnalizar[i]);
            anA.setFechaEnvio(fechaYHora);
            anA.setEstado("Enviando");
            anA.setIdProceso(examinarArchivo.getId());
            
            vEstadoArchivos.agregarAnalisisArchivo(anA);
            
            anA.setJSON_Respuesta(fileScanService.scanFileJSON(new File(archivosAnalizar[i])));
            AvadsMain.serviceDB.insertCollection("Analisis_Archivo", anA.toJSON());
            vEstadoArchivos.agregarAnalisisArchivo(anA);
        }        
        if(examinarArchivo.getEstado().compareTo("Con Errores") != 0) examinarArchivo.setEstado("En Analisis");
        
        Map<String, Object> search = new HashMap<String, Object>();
        search.put("idProceso", examinarArchivo.getId());

        Map<String, Object> upVal = new HashMap<String, Object>();
        upVal.put("estado", examinarArchivo.getEstado());
        if(AvadsMain.serviceDB.updateObjectCollection("Examinar_Archivo", search, upVal)) resultadoEnvA = true;
       
        return resultadoEnvA;
    }
    
    public boolean buscarReporte()
    {
        boolean resultadBR = false;
        Date fechaYHora = new Date();
        
        List<String> anaArchivos = AvadsMain.serviceDB.findObjectCollection("Analisis_Archivo", "idProceso", examinarArchivo.getId());
        int cantReportes = anaArchivos.size();
        for(String archAn : anaArchivos)
        {
            AnalisisArchivo analArchiC = (AnalisisArchivo) AvadsUtil.gson.fromJson(archAn, AnalisisArchivo.class);
            analArchiC.setJSON_Respuesta(analArchiC.getJSON_Respuesta().replace("\\\"", "\"").replace(":\"{", ":{").replace("}\",", "},"));

            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            Map<String, Object> mapResult = AvadsUtil.gson.fromJson(analArchiC.getJSON_Respuesta(), type);
            
            ResponseScanVirusTotal resSendFile = (ResponseScanVirusTotal) AvadsUtil.getClassReflection(ResponseScanVirusTotal.class, mapResult);
            Map<String, Object> res2 = fileScanService.fileReport(resSendFile.getScanId());

            ResponseScanReportVirusTotal resulF = (ResponseScanReportVirusTotal) AvadsUtil.getClassReflection(ResponseScanReportVirusTotal.class, res2);
            ResultadoAnalisis resAnal = null;
            if(resulF.getScans() != null && resulF.getScans().size() > 0)
            {
                String scansVirus = "";
                Iterator it = resulF.getScans().entrySet().iterator();
                while (it.hasNext())
                {
                    Object entry = it.next();
                    String resultPars = entry.toString().replace("={", ", ");
                    resultPars = "{nombre="+resultPars;
                    resultPars = resultPars.replace(":", "-");
                    scansVirus = scansVirus + resultPars + ",";
                }
                
                resAnal = new ResultadoAnalisis();
                resAnal.setArchivo(analArchiC.getArchivo());
                resAnal.setFechaEnvio(fechaYHora);
                resAnal.setFechaRespuesta(fechaYHora);
                resAnal.setIdProceso(analArchiC.getIdProceso());
                resAnal.setJSON_Respuesta(scansVirus.substring(0, scansVirus.length()-1));
                resAnal.setJSON_Respuesta(resAnal.getJSON_Respuesta()+"}");
                
                resultadBR = true;
                analArchiC.setEstado("Finalizado");
                cantReportes++;                
                AvadsMain.serviceDB.insertCollection("Resultado_Analisis", resAnal.toJSON());                
            }
            else if(resulF.getResponseCode() == -2D) analArchiC.setEstado("En Cola");
                        
            Map<String, Object> search = new HashMap<String, Object>();
            search.put("idProceso", examinarArchivo.getId());
            search.put("archivo", analArchiC.getArchivo());

            Map<String, Object> upVal = new HashMap<String, Object>();
            upVal.put("estado", analArchiC.getEstado());
            AvadsMain.serviceDB.updateObjectCollection("Analisis_Archivo", search, upVal);
            vEstadoArchivos.agregarAnalisisArchivo(analArchiC); 
        }
        
        if(cantReportes != 0 && cantReportes == anaArchivos.size())
        {
            examinarArchivo.setEstado("Finalizado");
            
            Map<String, Object> search = new HashMap<String, Object>();
            search.put("idProceso", examinarArchivo.getId());

            Map<String, Object> upVal = new HashMap<String, Object>();
            upVal.put("estado", examinarArchivo.getEstado());
            AvadsMain.serviceDB.updateObjectCollection("Examinar_Archivo", search, upVal);              
        }
        
        return resultadBR;
    }    
}
