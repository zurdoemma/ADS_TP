/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.frc.utn.avads.model;

/**
 *
 * @author Emmanuel
 */
public class ReporteAntiVirus implements Comparable<ReporteAntiVirus> {
    
    private String nombre;
    private boolean virusDetectado;
    private String ultimaActualizacion;
    private String version;
    private String nombreVirus;

    public ReporteAntiVirus() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isVirusDetectado() {
        return virusDetectado;
    }

    public void setVirusDetectado(boolean virusDetectado) {
        this.virusDetectado = virusDetectado;
    }

    public String getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(String ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public String getNombreVirus() {
        return nombreVirus;
    }

    public void setNombreVirus(String nombreVirus) {
        this.nombreVirus = nombreVirus;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ReporteAntiVirus{" + "nombre=" + nombre + ", virusDetectado=" + virusDetectado + ", ultimaActualizacion=" + ultimaActualizacion + ", nombreVirus=" + nombreVirus + ", version=" + version + '}';
    }
    
    @Override
    public int compareTo(ReporteAntiVirus another) {
        return this.nombre.compareTo(another.getNombre());
    }    
}
