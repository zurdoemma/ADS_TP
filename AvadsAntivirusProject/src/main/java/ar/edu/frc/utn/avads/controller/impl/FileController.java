/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.frc.utn.avads.controller.impl;

import ar.edu.frc.utn.avads.util.PropertiesClientUtil;
import java.io.File;
import java.nio.file.Files;

/**
 *
 * @author ecarballo
 */
public class FileController {
    
    private File fileAnalizar;
    
    public FileController(File fileAnalizar) {
        this.fileAnalizar = fileAnalizar;
    }
    
    public File getFileAnalizar() {
        return fileAnalizar;
    }

    public void setFileAnalizar(File fileAnalizar) {
        this.fileAnalizar = fileAnalizar;
    }

    public boolean esEjecutable()
    {
        boolean resul = Files.isExecutable(fileAnalizar.toPath());
        
        if(resul)
        {
            if(PropertiesClientUtil.getProperty("igu.examinarArchivos.revision.extensiones").compareTo("1") == 0)
            {
                resul = false;
                String[] extensionesAc = PropertiesClientUtil.getProperty("igu.examinarArchivos.revision.extensiones.aceptadas").split(",");
                String extArchivoSel = fileAnalizar.getName().substring(fileAnalizar.getName().lastIndexOf(".")+1).toLowerCase();
                for(String extA : extensionesAc)
                {
                    if(extA.toLowerCase().compareTo(extArchivoSel) == 0)
                    {
                        resul = true;
                        break;
                    }
                }
            }
        }
            
        return resul;
    }
    
    public boolean isSizeAccepted()
    {
        Long fileSize = (fileAnalizar.length()/(1024*1024));
        Long fileSizeP = Long.parseLong(PropertiesClientUtil.getProperty("igu.examinarArchivos.revision.tamano"));
        
        if(fileSize > fileSizeP) return false;
        else return true;
    }
    
    
}
