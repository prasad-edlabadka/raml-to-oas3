import webapi.Raml10;
import webapi.WebApiBaseUnit;
import webapi.WebApiDocument;
import amf.client.model.domain.*;
import java.util.*;

import java.util.concurrent.ExecutionException;

public class ramlTrial {
	
	public static void resolveFile() throws InterruptedException, ExecutionException {
	   
	    final WebApiBaseUnit result = Raml10.parse("file:///home/hp/Documents/APIConverter/BranchManagementRaml/branchManagement.raml").get();

	    final WebApiBaseUnit resolved = Raml10.resolve(result).get();

	    String fpath = "file:///home/hp/Documents/APIConverter/BranchManagementRaml/generated.raml";
	    
	    Raml10.generateFile(resolved, fpath).get();
	    
	    System.out.println("Generating Raml10 file at: " + fpath);
	  }
	
	public static void navigateApi() throws InterruptedException, ExecutionException {
	    
		//String inp = "file:///home/hp/Documents/APIConverter/BranchManagementRaml/generated.raml";
	    String inp = "file:///home/hp/Documents/APIConverter/BranchManagementRaml/branchManagement.raml";
	    //String inp = "file:///home/hp/Documents/APIConverter/Example/generated.raml";
		
	    WebApiDocument model = (WebApiDocument) Raml10.parse(inp).get();
	    
	    WebApi api = (WebApi) model.encodes();
	    
	    //Title
	    System.out.println("Title: " + api.name().value());
	    
	    //Paths
	    List<EndPoint> endpoints = api.endPoints();
	    
	    for(int i = 0; i < endpoints.size(); i++) {
	    	
	    	EndPoint endpoint = (EndPoint) api.endPoints().get(i);
	    	System.out.println("Endpoint : " + endpoint.path().value());
	    	
	    	List <Operation> operations = endpoint.operations();
	    	
	    	for(int j = 0; j < operations.size(); j++) {
	    		
	    		Operation op = operations.get(j);
	    		System.out.println("Method : "+op.method());
	    		System.out.println("\tDescription : "+op.description());
	    		
	    		
	    		if(!op.method().value().contentEquals("get")) {
	    			
	    			System.out.println("\tRequest: ");
	    			Request opRequest = op.request();
	    			List <Payload> payloads = opRequest.payloads();
	    			
	    			for(int k = 0; k < payloads.size(); k++) {
	    				Payload payload = payloads.get(k);
	    				System.out.println("\t\tMedia Type: "+payload.mediaType());
	    				/*
	    				 * For Unresolved we are not getting properties*/
	    				Shape schema = payload.schema();
	    				List <Shape> dataTypes = schema.inherits();
	    				for(int l = 0; l < dataTypes.size(); l++) {
	    					
	    					System.out.println("\t\tdataType : "+dataTypes.get(l).name().value());
	    					
	    					NodeShape dataType = (NodeShape) dataTypes.get(l);
	    					List <PropertyShape> properties = dataType.properties();
	    					System.out.println("\t\tProperty List Size: "+properties.size());
	    				}
	    				
	    				/*For resolved 
	    				 Shape schema = payload.schema();
	    				 List <Shape> dataTypes = schema.inherits();
	    				 System.out.println(dataTypes.size());*/
	    			}
	    		}
	    		List <Response> opResponse = op.responses();
	    	}
	    }
	    
	   
	}
	public static void main(String [] args) {
		try {
			resolveFile();
			//navigateApi();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
