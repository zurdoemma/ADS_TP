package ar.edu.frc.utn.avads.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.IOUtils;

import ar.edu.frc.utn.avads.service.FileScanService;

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
	
	private static final String API_FIELD_KEY = "apikey";
	private static final String API_FIELD_KEY_VALUE = "0458b0d7bc03b0ae116e0df441b95c746dcac6d07fe4719b0ce1a7d07995ae32";
	private static final String FILE_FIELD_KEY = "file";
	private static final String RESOURCE_FIELD_KEY = "resource";
	
	private static final String HOST_URL = "https://www.virustotal.com";

	private static final String URI_FILE_SCAN = HOST_URL
			+ "/vtapi/v2/file/scan";
	private static final String URI_FILE_SCAN_REPORT = HOST_URL
			+ "/vtapi/v2/file/report";

	public Map<String, Object> scanFile(File file) {
		Map<String, Object> mapResponse = new HashMap<String, Object>();
		
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(URI_FILE_SCAN);

			FileDataBodyPart filePart = new FileDataBodyPart(FILE_FIELD_KEY,file);

			MultiPart multipartEntity = new FormDataMultiPart().field(
					API_FIELD_KEY, API_FIELD_KEY_VALUE,
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
			keyValuesPairs.putSingle(API_FIELD_KEY, API_FIELD_KEY_VALUE);
			keyValuesPairs.putSingle(RESOURCE_FIELD_KEY, scanId);
			
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

	public static void main(String[] args) {
		try {
			new VirusTotalFileScanServiceImpl().scanFile(new File("/home/gustavo/TestVS.exe"));
			new VirusTotalFileScanServiceImpl().fileReport("c65f8a83fa607f9e98d041ea90c6e6cb06d9d27ec5077e48beb62bd39a19e8f5-1492475018");
			
		} catch (UniformInterfaceException e) {
			e.printStackTrace();
		} catch (ClientHandlerException e) {
			e.printStackTrace();
		}
	}

}