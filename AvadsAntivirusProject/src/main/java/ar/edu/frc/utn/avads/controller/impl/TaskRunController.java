/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.frc.utn.avads.controller.impl;

import ar.edu.frc.utn.avads.controller.AnalisisVirus;
import ar.edu.frc.utn.avads.util.PropertiesClientUtil;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ecarballo
 */
public class TaskRunController extends Thread {

    private String[] archivosAnalizar;
    private int cantidadArchivosEnProceso;
    
    public TaskRunController(String[] archivosAnalizar) {
        this.archivosAnalizar = archivosAnalizar;
        cantidadArchivosEnProceso = archivosAnalizar.length;
    }
    
    public void run() 
    {
        AnalisisVirus analisisVT = new VirusTotal(archivosAnalizar);
        analisisVT.enviarArchivo();
        
        while(cantidadArchivosEnProceso > 0)
        {
            try {
                Thread.sleep(Long.parseLong(PropertiesClientUtil.getProperty("task.run.time.sleep").toString())*(1000*60));
            } catch (InterruptedException ex) {
                Logger.getLogger(TaskRunController.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(analisisVT.buscarReporte()) cantidadArchivosEnProceso--;
        }
    }
    
}
