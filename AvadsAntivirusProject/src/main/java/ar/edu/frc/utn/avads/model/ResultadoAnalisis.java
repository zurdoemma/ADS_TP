/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.frc.utn.avads.model;

import com.google.gson.Gson;
import java.util.Date;

/**
 *
 * @author ecarballo
 */
public class ResultadoAnalisis {
    
    private Long idProceso;
    private String archivo;
    private String JSON_Respuesta; 
    private Date fechaEnvio;
    private Date fechaRespuesta;

    public ResultadoAnalisis() {
    }

    public ResultadoAnalisis(Long idProceso, String archivo, String JSON_Respuesta, Date fechaEnvio, Date fechaRespuesta) {
        this.idProceso = idProceso;
        this.archivo = archivo;
        this.JSON_Respuesta = JSON_Respuesta;
        this.fechaEnvio = fechaEnvio;
        this.fechaRespuesta = fechaRespuesta;
    }

    
    public Long getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Long idProceso) {
        this.idProceso = idProceso;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getJSON_Respuesta() {
        return JSON_Respuesta;
    }

    public void setJSON_Respuesta(String JSON_Respuesta) {
        this.JSON_Respuesta = JSON_Respuesta;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public String toJSON()
    {
        Gson gson = new Gson();
        return gson.toJson(this);
    }    

    @Override
    public String toString() {
        return "Analisis_Archivo{" + "id=" + idProceso + ", archivo=" + archivo + ", JSON_Respuesta=" + JSON_Respuesta +", fechaEnvio=" + fechaEnvio + ", fechaRespuesta=" + fechaRespuesta + '}';
    }
        
}
