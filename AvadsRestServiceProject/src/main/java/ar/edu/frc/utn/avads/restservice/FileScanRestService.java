package ar.edu.frc.utn.avads.restservice;

import java.io.InputStream;

import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;

/**
 * 
 * @author gustavo
 *
 */

public interface FileScanRestService {

	
    public Response fileScan(InputStream uploadedInputStream, FormDataContentDisposition fileDetail);
	
    public Response fileReport(String scanId);
	
}
