/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.frc.utn.avads.model;

/**
 *
 * @author ecarballo
 */
public class ReportAntiVirus implements Comparable<ReportAntiVirus> {
    
    private String nombre;
    private boolean detected;
    private String version;
    private String result;
    private String update;

    public ReportAntiVirus() {
    }

    public ReportAntiVirus(String nombre, boolean detected, String version, String result, String update) {
        this.nombre = nombre;
        this.detected = detected;
        this.version = version;
        this.result = result;
        this.update = update;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isDetected() {
        return detected;
    }

    public void setDetected(boolean detected) {
        this.detected = detected;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    @Override
    public String toString() {
        return "ReportAntiVirus{" + "nombre=" + nombre + ", detected=" + detected + ", version=" + version + ", result=" + result + ", update=" + update + '}';
    }
    
    @Override
    public int compareTo(ReportAntiVirus another) {
        return this.nombre.compareTo(another.getNombre());
    }     
}
