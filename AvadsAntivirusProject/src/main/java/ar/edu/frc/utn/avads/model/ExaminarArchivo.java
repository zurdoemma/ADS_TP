/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.frc.utn.avads.model;

import com.google.gson.Gson;

/**
 *
 * @author ecarballo
 */
public class ExaminarArchivo {
    
    private Long idProceso;
    private String[] archivos;
    private String estado;
    private String fecha;
    private String hora;

    public ExaminarArchivo() {
    }
    
    public ExaminarArchivo(Long idProceso, String[] archivos, String estado, String fecha, String hora) {
        this.idProceso = idProceso;
        this.archivos = archivos;
        this.estado = estado;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Long getId() {
        return idProceso;
    }

    public void setId(Long id) {
        this.idProceso = id;
    }

    public String[] getArchivos() {
        return archivos;
    }

    public void setArchivos(String[] archivos) {
        this.archivos = archivos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    public String toJSON()
    {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public String toString() {
        return "ExaminarArchivo{" + "idProceso=" + idProceso + ", archivos=" + archivos.length + ", estado=" + estado + ", fecha=" + fecha + ", hora=" + hora + '}';
    }
    
}
