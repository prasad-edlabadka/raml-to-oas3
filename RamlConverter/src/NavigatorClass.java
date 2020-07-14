import java.util.List;
import java.util.concurrent.ExecutionException;

import amf.client.model.domain.*;

import webapi.Raml10;

import webapi.WebApiDocument;

public class NavigatorClass 
{
	public static void navigateApi(String inp) throws InterruptedException, ExecutionException 
	{
	    
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
	    				NodeShape object = (NodeShape) payload.schema().inherits().get(0).linkTarget().get();
	    				System.out.println("\t\tDataType: "+object.name().value()+"\n\t\tProperties");
	    				List<PropertyShape> properties = object.properties();
	    				for(int l = 0; l < properties.size(); l++) {
	    					System.out.println("\t\t\t"+properties.get(l).name().value());
	    				}
	    			}
	    		}
	    		List<Response> responses = op.responses();
	    		System.out.println("Responses");
	    		for(int k = 0; k < responses.size(); k++) {
	    			Response response = responses.get(k);
	    			System.out.println("\tResponse : ");
	    			System.out.println("\t\tStatus Code: "+response.statusCode().value());
	    			List <Payload> payloads = response.payloads();
	    			for(int l = 0; l < payloads.size(); l++) {
	    				Payload payload = payloads.get(l);
	    				NodeShape object = (NodeShape) payload.schema().inherits().get(0).linkTarget().get();
	    				System.out.println("\t\tDataType: "+object.name().value()+"\n\t\tProperties");
	    				List<PropertyShape> properties = object.properties();
	    				for(int m = 0; m < properties.size(); m++) {
	    					System.out.println("\t\t\t\t"+properties.get(m).name().value());
	    				}
	    			}
	    		}
	    	}
	    	
	    }
} 
	
	
}
