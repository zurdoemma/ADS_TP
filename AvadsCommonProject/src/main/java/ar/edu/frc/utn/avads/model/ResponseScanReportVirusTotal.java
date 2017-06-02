/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.frc.utn.avads.model;

import java.util.LinkedHashMap;

/**
 *
 * @author ecarballo
 */
public class ResponseScanReportVirusTotal {

    private String scanDate;
    private Integer responseCode;
    private String scanId;
    private String verboseMsg;
    private Integer positives;
    private Integer total;
    private LinkedHashMap scans;

    public ResponseScanReportVirusTotal() {
    }

    public ResponseScanReportVirusTotal(String scanDate, Integer responseCode, String scanId, String verboseMsg, Integer positives, Integer total, LinkedHashMap scans) {
        this.scanDate = scanDate;
        this.responseCode = responseCode;
        this.scanId = scanId;
        this.verboseMsg = verboseMsg;
        this.positives = positives;
        this.total = total;
        this.scans = scans;
    }

    public String getScanDate() {
        return scanDate;
    }

    public void setScanDate(String scanDate) {
        this.scanDate = scanDate;
    }

    public Integer getPositives() {
        return positives;
    }

    public void setPositives(Integer positives) {
        this.positives = positives;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public LinkedHashMap getScans() {
        return scans;
    }

    public void setScans(LinkedHashMap scans) {
        this.scans = scans;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
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

    @Override
    public String toString() {
        return "ResponseScanReportVirusTotal{" + "scanDate=" + scanDate + ", responseCode=" + responseCode + ", scanId=" + scanId + ", verboseMsg=" + verboseMsg + ", positives=" + positives + ", total=" + total + ", scans=" + scans + '}';
    }
        
}
