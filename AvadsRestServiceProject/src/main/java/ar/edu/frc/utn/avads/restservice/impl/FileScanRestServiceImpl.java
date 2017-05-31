package ar.edu.frc.utn.avads.restservice.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import ar.edu.frc.utn.avads.restservice.FileScanRestService;
import ar.edu.frc.utn.avads.scan.service.FileScanService;
import ar.edu.frc.utn.avads.scan.service.impl.VirusTotalFileScanServiceImpl;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * 
 * @author gustavo
 *
 */
@Path("/v1/file")
public class FileScanRestServiceImpl implements FileScanRestService {

	private static final String UPLOAD_FOLDER = "/uploadedFiles/";

	private FileScanService fileScanService = new VirusTotalFileScanServiceImpl();
	
	@Context
	private UriInfo context;

	@POST
    @Path("scan")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response fileScan(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {

		// check if all form parameters are provided
		if (uploadedInputStream == null || fileDetail == null)
			return Response.status(400).entity("Invalid form data").build();
		// create our destination folder, if it not exists
		try {
			createFolderIfNotExists(UPLOAD_FOLDER);
		} catch (SecurityException se) {
			return Response.status(500)
					.entity("Can not create destination folder on server")
					.build();
		}
		String uploadedFileLocation = UPLOAD_FOLDER + fileDetail.getFileName();
		try {
			saveToFile(uploadedInputStream, uploadedFileLocation);
		} catch (IOException e) {
			return Response.status(500).entity("Can not save file").build();
		}
		String jsonResponse = fileScanService.fileReportJSON("");
		System.out.println("jsonResponse" + jsonResponse);
		return Response.status(200)
				.entity("File saved to " + uploadedFileLocation).build();
	}

	@GET
    @Path("report/{scanId}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response fileReport(@PathParam("scanId") String scanId) {
		String jsonResponse = fileScanService.fileReportJSON(scanId);
		return Response.status(200).entity(jsonResponse).build();
	}

	private File saveToFile(InputStream inStream, String target)
			throws IOException {
		OutputStream out = null;
		int read = 0;
		byte[] bytes = new byte[1024];
		out = new FileOutputStream(new File(target));
		while ((read = inStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
		
		return new File(target);
	}

	private void createFolderIfNotExists(String dirName)
			throws SecurityException {
		File theDir = new File(dirName);
		if (!theDir.exists()) {
			theDir.mkdir();
		}
	}

}