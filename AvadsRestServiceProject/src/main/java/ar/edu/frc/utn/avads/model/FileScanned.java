/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.frc.utn.avads.model;

import com.google.gson.Gson;
import java.util.Objects;

/**
 *
 * @author ecarballo
 */
public class FileScanned {
    
    private String scanId;
    private String checkSum;
    private String nombreArchivo;
    private String fechaRegistro;
    private String fechaVencimiento;
    private String reporteScaneo;

    public FileScanned() {
    }

    public FileScanned(String scanId, String checkSum, String nombreArchivo, String fechaRegistro, String fechaVencimiento, String reporteScaneo) {
        this.scanId = scanId;
        this.checkSum = checkSum;
        this.nombreArchivo = nombreArchivo;
        this.fechaRegistro = fechaRegistro;
        this.fechaVencimiento = fechaVencimiento;
        this.reporteScaneo = reporteScaneo;
    }

    public String getScanId() {
        return scanId;
    }

    public void setScanId(String scanId) {
        this.scanId = scanId;
    }

    
    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getReporteScaneo() {
        return reporteScaneo;
    }

    public void setReporteScaneo(String reporteScaneo) {
        this.reporteScaneo = reporteScaneo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FileScanned other = (FileScanned) obj;
        if (!Objects.equals(this.scanId, other.scanId)) {
            return false;
        }
        if (!Objects.equals(this.checkSum, other.checkSum)) {
            return false;
        }
        if (!Objects.equals(this.nombreArchivo, other.nombreArchivo)) {
            return false;
        }
        if (!Objects.equals(this.fechaRegistro, other.fechaRegistro)) {
            return false;
        }
        if (!Objects.equals(this.fechaVencimiento, other.fechaVencimiento)) {
            return false;
        }
        if (!Objects.equals(this.reporteScaneo, other.reporteScaneo)) {
            return false;
        }
        return true;
    }



    public String toJSON()
    {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public String toString() {
        return "FilesScanned{" + "scanId=" + scanId + ", checkSum=" + checkSum + ", nombreArchivo=" + nombreArchivo + ", fechaRegistro=" + fechaRegistro + ", fechaVencimiento=" + fechaVencimiento + ", reporteScaneo=" + reporteScaneo + '}';
    }

}
