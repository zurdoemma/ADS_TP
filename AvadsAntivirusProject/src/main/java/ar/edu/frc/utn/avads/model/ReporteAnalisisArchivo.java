/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.frc.utn.avads.model;

import java.util.List;

/**
 *
 * @author Emmanuel
 */
public class ReporteAnalisisArchivo {
    
    private Long idProceso;
    private String archivo;
    private List<ReporteAntiVirus> reportesAntiVirus;

    public ReporteAnalisisArchivo() {
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

    public List<ReporteAntiVirus> getReportesAntiVirus() {
        return reportesAntiVirus;
    }

    public void setReportesAntiVirus(List<ReporteAntiVirus> reportesAntiVirus) {
        this.reportesAntiVirus = reportesAntiVirus;
    }

    @Override
    public String toString() {
        return "ReporteAnalisisArchivo{" + "idProceso=" + idProceso + ", archivo=" + archivo + ", reportesAntiVirus=" + reportesAntiVirus + '}';
    }
    
}
