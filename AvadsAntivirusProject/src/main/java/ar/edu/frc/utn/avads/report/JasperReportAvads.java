
package ar.edu.frc.utn.avads.report;

import ar.edu.frc.utn.avads.main.AvadsMain;
import ar.edu.frc.utn.avads.model.ReporteAnalisisArchivo;
import ar.edu.frc.utn.avads.model.ReporteAntiVirus;
import ar.edu.frc.utn.avads.model.ResultadoAnalisis;
import ar.edu.frc.utn.avads.util.AvadsUtil;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;



public class JasperReportAvads
{
    private JasperPrint jasperPrint;

    public JasperReportAvads() {        
     
    }
    
    private void runReporteAnalisisArchivo(Map<String, Object> parametros, String reporte) {
        
        try {
            File file = AvadsUtil.getFileByPath(reporte);

            JasperReport masterReport = (JasperReport) JRLoader.loadObject(file);

            java.awt.Image imagen = AvadsUtil.getImageByPath(AvadsMain.propC.getProperty("icono.system.image"));

            File fileImagen = null;
            URL resource = JasperReportAvads.class.getResource(AvadsMain.propC.getProperty("igu.estadoArchivos.imagen.reporte.analisis.archivo"));
            try {
                fileImagen = Paths.get(resource.toURI()).toFile();
            } catch (URISyntaxException ex) {
                Logger.getLogger(JasperReportAvads.class.getName()).log(Level.SEVERE, null, ex);
            }

            parametros.put("rutaImagen", fileImagen);
            jasperPrint = JasperFillManager.fillReport(masterReport, parametros, new JREmptyDataSource());

            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle(AvadsMain.propC.getProperty("igu.estadoArchivos.title.reporte.analisis.archivo"));

            jviewer.setIconImage(imagen);
            jviewer.setVisible(true);
            jviewer.requestFocus();
        }
        catch (JRException e)
        {
            System.out.println(AvadsMain.propC.get("report.error.carga.maestro") + " " + e.getMessage());
        }            
    }
    
    public void mostrarReporte(Long idProceso, String archivo) {
        
        Map<String, Object> query = new HashMap<>();
        query.put("idProceso", idProceso);
        query.put("archivo", archivo);
        
        List<String> resul = AvadsMain.serviceDB.findObjectCollectionMultipleAtributes("Resultado_Analisis", query);
        ResultadoAnalisis resultAnalisis = null;
        
        for(String result : resul)
        {
            resultAnalisis = AvadsUtil.gson.fromJson(result, ResultadoAnalisis.class);
            ReporteAnalisisArchivo reportAnalisisArchivo = AvadsUtil.gson.fromJson(result, ReporteAnalisisArchivo.class);
            List<ReporteAntiVirus> listaRepoAnt = new ArrayList<>();
            reportAnalisisArchivo.setReportesAntiVirus(listaRepoAnt);
            
            String[] antivirus = result.substring(result.indexOf("\"{")+1,result.indexOf("}\"")+1).split("},");
            for(int i=0;i<antivirus.length;i++)
            {
                if(i < antivirus.length-1)
                {
                    antivirus[i] = antivirus[i]+"}";
                    antivirus[i] = antivirus[i].replace("detected", "virusDetectado");
                    antivirus[i] = antivirus[i].replace("update", "ultimaActualizacion");
                    antivirus[i] = antivirus[i].replace("result", "nombreVirus");

                    ReporteAntiVirus reporteAntiVirus = AvadsUtil.gson.fromJson(antivirus[i], ReporteAntiVirus.class);
                    String ultimaAct = AvadsUtil.getDateReportUpdateAV(reporteAntiVirus.getUltimaActualizacion());
                    reporteAntiVirus.setUltimaActualizacion((( ultimaAct == null) ? "--" : ultimaAct));
                    reportAnalisisArchivo.getReportesAntiVirus().add(reporteAntiVirus);
                }
            }
            
            query.put("fecha", AvadsUtil.getDate(resultAnalisis.getFechaEnvio())+" "+AvadsUtil.getHour(resultAnalisis.getFechaEnvio()));
            Collections.sort(reportAnalisisArchivo.getReportesAntiVirus());
            query.put("listaAnalisis", reportAnalisisArchivo.getReportesAntiVirus());
            runReporteAnalisisArchivo(query,AvadsMain.propC.getProperty("igu.estadoArchivos.ruta.reporte.analisis.archivo"));
        }
    }
    
}
