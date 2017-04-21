package ar.edu.frc.utn.avads.service;

import java.io.File;
import java.util.Map;

public interface FileScanService {

	Map<String, Object> scanFile(final File file);
	
	Map<String, Object> fileReport(String scanId);
}
