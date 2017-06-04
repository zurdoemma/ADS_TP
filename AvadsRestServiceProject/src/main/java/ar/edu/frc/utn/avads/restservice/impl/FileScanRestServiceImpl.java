package ar.edu.frc.utn.avads.restservice.impl;

import ar.edu.frc.utn.avads.db.service.DBService;
import ar.edu.frc.utn.avads.db.service.impl.DBServiceMongoImpl;
import ar.edu.frc.utn.avads.model.FileScanned;
import ar.edu.frc.utn.avads.model.ResponseScanVirusTotal;
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
import ar.edu.frc.utn.avads.util.AvadsUtil;
import ar.edu.frc.utn.avads.util.PropertiesServerUtil;
import com.google.gson.reflect.TypeToken;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author gustavo
 *
 */
@Path("/v1/file")
public class FileScanRestServiceImpl implements FileScanRestService {

	private static final String UPLOAD_FOLDER = "./uploadedFiles/";

	private FileScanService fileScanService = new VirusTotalFileScanServiceImpl();
	
	@Context
	private UriInfo context;

	@POST
        @Path("scan")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fileScan(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {

		// check if all form parameters are provided
		if (uploadedInputStream == null || fileDetail == null)
			return Response.status(400).entity("Invalid form data").build();
                
                DBService dbConn = new DBServiceMongoImpl();
                dbConn.startDB(PropertiesServerUtil.getProperty("db.mongo.user"), PropertiesServerUtil.getProperty("db.mongo.pwd"), 
                PropertiesServerUtil.getProperty("db.mongo.url"), PropertiesServerUtil.getProperty("db.mongo.puerto"),
                PropertiesServerUtil.getProperty("db.mongo.nombre.db"));

                // check if file procesed
                boolean fileProcesed = isFileProcesed(uploadedInputStream, dbConn, null);
                if(fileProcesed == true) return Response.status(200)
				.entity("File scanned").build();
                
		// create our destination folder, if it not exists
		try {
			createFolderIfNotExists(UPLOAD_FOLDER);
		} catch (SecurityException se) {
			return Response.status(500)
					.entity("Can not create destination folder on server")
					.build();
		}
		String uploadedFileLocation = UPLOAD_FOLDER + fileDetail.getFileName();
                File fileToProcess = null;
		try {
                        fileToProcess = saveToFile(uploadedInputStream, uploadedFileLocation);
		} catch (IOException e) {
                    System.out.println(e.getMessage());
                    return Response.status(500).entity("Can not save file").build();
		}
		
                String jsonResponse = fileScanService.scanFileJSON(fileToProcess);
		System.out.println("jsonResponse" + jsonResponse);
                processScannedFile(jsonResponse, dbConn, uploadedInputStream, fileDetail.getName());
                
		return Response.status(200).entity(jsonResponse).build();
	}

	@GET
        @Path("report/{scanId}")
        @Produces(MediaType.APPLICATION_JSON)
	public Response fileReport(@PathParam("scanId") String scanId) {
                
                DBService dbConn = new DBServiceMongoImpl();
                dbConn.startDB(PropertiesServerUtil.getProperty("db.mongo.user"), PropertiesServerUtil.getProperty("db.mongo.pwd"), 
                PropertiesServerUtil.getProperty("db.mongo.url"), PropertiesServerUtil.getProperty("db.mongo.puerto"),
                PropertiesServerUtil.getProperty("db.mongo.nombre.db"));
                
                String jsonResponse = null;
                
                // check if file procesed
                boolean fileProcesed = isFileProcesed(null, dbConn, scanId);
                if(fileProcesed == true)
                {
                    List<String> searchFile = dbConn.findObjectCollection("Files_Scanned", "reporteScaneo", scanId);
                    for(String sFile : searchFile) 
                    {
                        FileScanned fileScan = (FileScanned) AvadsUtil.gson.fromJson(sFile, FileScanned.class);
                        
                        if(fileScan == null)
                        {
                            fileProcesed = false;
                            break;
                        }
                        else if(validDateToExpirationReport(fileScan.getFechaVencimiento()))
                        {
                            dbConn.removeObjectCollection("Files_Scanned", "reporteScaneo", scanId);
                            fileProcesed = false;
                            break;
                        }
                            
                        jsonResponse = fileScan.getReporteScaneo();
                    }
                    if(searchFile.isEmpty()) fileProcesed = false;
                }
                
                if(fileProcesed == false)
                {
                    jsonResponse = fileScanService.fileReportJSON(scanId);
                    
                    Map<String, Object> search = new HashMap<String, Object>();
                    search.put("scanId", scanId);

            
                    Map<String, Object> upVal = new HashMap<String, Object>();
                    upVal.put("reporteScaneo", jsonResponse);
                    dbConn.updateObjectCollection("Files_Scanned", search, upVal);
                }
                              
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
        
        private String calcHashFile(InputStream fileStream)
        {
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("MD5");              
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(FileScanRestServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Create byte array to read data in chunks
            byte[] byteArray = new byte[1024];
            int bytesCount = 0; 

            try {
                //Read file data and update in message digest
                while ((bytesCount = fileStream.read(byteArray)) != -1) {
                    md.update(byteArray, 0, bytesCount);
                }
            } catch (IOException ex) {
                Logger.getLogger(FileScanRestServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Get the hash's bytes
            byte[] bytes = md.digest();

            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            //return complete hash
            return sb.toString();
        }
        
        private boolean isFileProcesed(InputStream fileStream, DBService dbConn, String scanId)
        {
            if(scanId == null)
            {
                String hashFile = calcHashFile(fileStream);

                List<String> searchFile = dbConn.findObjectCollection("Files_Scanned", "checkSum", hashFile);
                for(String sFile : searchFile) return true;
            }
            else
            {
                List<String> searchFile = dbConn.findObjectCollection("Files_Scanned", "scanId", scanId);
                for(String sFile : searchFile) return true;                
            }
                
            return false;
        }
        
        private boolean validDateToExpirationReport(String dateToExpirationReport)
        {
            SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
            try {
                if(AvadsUtil.daysBetween(fecha.parse(dateToExpirationReport), new Date()) < 0) return false;
            } catch (ParseException ex) {
                Logger.getLogger(FileScanRestServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return true;
        }
        
        private void processScannedFile (String jsonResponse, DBService DBConn, InputStream uploadedInputStream, String nameFile)
        {
            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            Map<String, Object> mapResult = AvadsUtil.gson.fromJson(jsonResponse, type);
            
            SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
            ResponseScanVirusTotal resSendFile = (ResponseScanVirusTotal) AvadsUtil.getClassReflection(ResponseScanVirusTotal.class, mapResult);
            FileScanned fileScanned = new FileScanned();
            fileScanned.setCheckSum(calcHashFile(uploadedInputStream));
            fileScanned.setFechaRegistro(fecha.format(new Date()));
            fileScanned.setFechaVencimiento(AvadsUtil.addDaysToDate(fileScanned.getFechaRegistro(), Integer.parseInt(PropertiesServerUtil.getProperty("expiration_time_file_db"))));
            fileScanned.setNombreArchivo(nameFile);
            fileScanned.setScanId(resSendFile.getScanId());
            
            DBConn.insertCollection("Files_Scanned", fileScanned.toJSON());
        }
                

}