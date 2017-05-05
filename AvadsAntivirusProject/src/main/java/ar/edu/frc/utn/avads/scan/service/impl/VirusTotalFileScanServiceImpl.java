package ar.edu.frc.utn.avads.scan.service.impl;

import ar.edu.frc.utn.avads.main.AvadsMain;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.IOUtils;

import ar.edu.frc.utn.avads.scan.service.FileScanService;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.MultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;

public class VirusTotalFileScanServiceImpl implements FileScanService {

final static Logger log = Logger.getLogger(VirusTotalFileScanServiceImpl.class.getName());
	
	private static final String URI_FILE_SCAN = AvadsMain.propC.getProperty("host.url")
			+ AvadsMain.propC.getProperty("url.file.scan");
	private static final String URI_FILE_SCAN_REPORT = AvadsMain.propC.getProperty("host.url")
			+ AvadsMain.propC.getProperty("url.file.scan.report");

	public Map<String, Object> scanFile(File file) {
		Map<String, Object> mapResponse = new HashMap<String, Object>();
		
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(URI_FILE_SCAN);

			FileDataBodyPart filePart = new FileDataBodyPart(AvadsMain.propC.getProperty("file.field.key.name"),file);

			MultiPart multipartEntity = new FormDataMultiPart().field(AvadsMain.propC.getProperty("api.key.name"), AvadsMain.propC.getProperty("api.key"),
					MediaType.TEXT_PLAIN_TYPE).bodyPart(filePart);

			ClientResponse response = webResource.type(
					MediaType.MULTIPART_FORM_DATA).post(ClientResponse.class,
					multipartEntity);
			StringWriter writer = new StringWriter();
			IOUtils.copy(response.getEntityInputStream(), writer, "UTF-8");
			String jsonResponse = writer.toString();
			
			ObjectMapper mapper = new ObjectMapper();
			// convert JSON string to Map
			mapResponse = mapper.readValue(jsonResponse, new TypeReference<Map<String, String>>(){});

			log.info(mapResponse.toString());
			
		} catch (UniformInterfaceException e) {
			e.printStackTrace();
		} catch (ClientHandlerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return mapResponse;
	}
	
	public Map<String, Object> fileReport(String scanId) {
		Map<String, Object> mapResponse = new HashMap<String, Object>();
		
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(URI_FILE_SCAN_REPORT);

			MultivaluedMap<String, String> keyValuesPairs = new MultivaluedMapImpl();
			keyValuesPairs.putSingle(AvadsMain.propC.getProperty("api.key.name"), AvadsMain.propC.getProperty("api.key"));
			keyValuesPairs.putSingle(AvadsMain.propC.getProperty("resource.field.key.name"), scanId);
			
			ClientResponse response = webResource.type(
					MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class,
					keyValuesPairs);
			
			
			
			StringWriter writer = new StringWriter();
			IOUtils.copy(response.getEntityInputStream(), writer, "UTF-8");
			String jsonResponse = writer.toString();
			
			log.info(jsonResponse);
			
			ObjectMapper mapper = new ObjectMapper();
			// convert JSON string to Map
			mapResponse = mapper.readValue(jsonResponse, new TypeReference<Map<String, Object>>(){});

			log.info(mapResponse.toString());
			
		} catch (UniformInterfaceException e) {
			e.printStackTrace();
		} catch (ClientHandlerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return mapResponse;
	}
                
	public String scanFileJSON(File file) {	
                String jsonResponse = "";
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(URI_FILE_SCAN);

			FileDataBodyPart filePart = new FileDataBodyPart(AvadsMain.propC.getProperty("file.field.key.name"),file);

			MultiPart multipartEntity = new FormDataMultiPart().field(AvadsMain.propC.getProperty("api.key.name"), AvadsMain.propC.getProperty("api.key"),
					MediaType.TEXT_PLAIN_TYPE).bodyPart(filePart);

			ClientResponse response = webResource.type(
					MediaType.MULTIPART_FORM_DATA).post(ClientResponse.class,
					multipartEntity);
			StringWriter writer = new StringWriter();
			IOUtils.copy(response.getEntityInputStream(), writer, "UTF-8");
			jsonResponse = writer.toString();
						
		} catch (UniformInterfaceException e) {
			e.printStackTrace();
		} catch (ClientHandlerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return jsonResponse;
	}
	
	public String fileReportJSON(String scanId) {
		String jsonResponse = "";
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(URI_FILE_SCAN_REPORT);

			MultivaluedMap<String, String> keyValuesPairs = new MultivaluedMapImpl();
			keyValuesPairs.putSingle(AvadsMain.propC.getProperty("api.key.name"), AvadsMain.propC.getProperty("api.key"));
			keyValuesPairs.putSingle(AvadsMain.propC.getProperty("resource.field.key.name"), scanId);
			
			ClientResponse response = webResource.type(
					MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class,
					keyValuesPairs);
			
			
			
			StringWriter writer = new StringWriter();
			IOUtils.copy(response.getEntityInputStream(), writer, "UTF-8");
			jsonResponse = writer.toString();
			
			log.info(jsonResponse);
			
		} catch (UniformInterfaceException e) {
			e.printStackTrace();
		} catch (ClientHandlerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return jsonResponse;
	}         

}