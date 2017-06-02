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
public class ResponseScanVirusTotal {
    
    private String permalink;
    private Double responseCode;
    private String scanId;
    private String verboseMsg;
    private String scansVirus;

    public ResponseScanVirusTotal() {
    }

    public ResponseScanVirusTotal(String permalink, Double responseCode, String scanId, String verboseMsg, String scansVirus) {
        this.permalink = permalink;
        this.responseCode = responseCode;
        this.scanId = scanId;
        this.verboseMsg = verboseMsg;
        this.scansVirus = scansVirus;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public Double getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Double responseCode) {
        this.responseCode = responseCode;
    }

    public String getScanId() {
        return scanId;
    }

    public void setScanId(String scanId) {
        this.scanId = scanId;
    }

    public String getVerboseMsg() {
        return verboseMsg;
    }

    public void setVerboseMsg(String verboseMsg) {
        this.verboseMsg = verboseMsg;
    }

    public String getScansVirus() {
        return scansVirus;
    }

    public void setScansVirus(String scansVirus) {
        this.scansVirus = scansVirus;
    }

    @Override
    public String toString() {
        return "ResponseScanVirusTotal{" + "permalink=" + permalink + ", responseCode=" + responseCode + ", scanId=" + scanId + ", verboseMsg=" + verboseMsg + ", scansVirus=" + scansVirus + '}';
    }  
    
}
