package ar.edu.frc.utn.avads.controller;

import java.io.File;
import java.util.Map;

public interface FileScanService {

	Map<String, Object> scanFile(final File file);
	
	Map<String, Object> fileReport(String scanId);
        
	String scanFileJSON(final File file);
	
	String fileReportJSON(String scanId);        
}
