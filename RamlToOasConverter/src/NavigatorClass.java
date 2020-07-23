import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;

import amf.client.model.domain.*;

import webapi.Raml10;

import webapi.WebApiDocument;

public class NavigatorClass 
{
	public void navigateApi(String inp) throws InterruptedException, ExecutionException 
	{
		 Logger logger=Logger.getLogger(NavigatorClass.class);
		 WebApiDocument model = (WebApiDocument) Raml10.parse(inp).get();
	    
		 WebApi api = (WebApi) model.encodes();
		 logger.info("Title: " + api.name().value() );
		 List<EndPoint> endpoints = api.endPoints();
	    
		 for(int endpointSize = 0; endpointSize < endpoints.size(); endpointSize++) 
		 {
	    	
	    	EndPoint endpoint = (EndPoint) api.endPoints().get(endpointSize);
	    	logger.info("Endpoint : " + endpoint.path().value());
	    	
	    	List <Operation> operations = endpoint.operations();
	    	
	    	for(int opSize = 0; opSize < operations.size(); opSize++) 
	    	{
	    		
	    		Operation op = operations.get(opSize);
	    		logger.info("Method : "+op.method());
	    		logger.info("\tDescription : "+op.description());
	    		
	    		
	    		if(!op.method().value().contentEquals("get")) 
	    		{
	    			
	    			logger.info("\tRequest: ");
	    			Request opRequest = op.request();
	    			List <Payload> payloads = opRequest.payloads();
	    			
	    			for(int payloadSize = 0; payloadSize < payloads.size(); payloadSize++) 
	    			{
	    				Payload payload = payloads.get(payloadSize);
	    				NodeShape object = (NodeShape) payload.schema().inherits().get(0).linkTarget().get();
	    				logger.info("\t\tDataType: "+object.name().value()+"\n\t\t\t\t\t\tProperties:");
	    				List<PropertyShape> properties = object.properties();
	    				for(int l = 0; l < properties.size(); l++)
	    				{
	    					logger.info("\t\t\t"+properties.get(l).name().value());
	    				}
	    			}
	    		}
	    		List<Response> responses = op.responses();
	    		logger.info("Responses");
	    		for(int responseSize = 0; responseSize < responses.size(); responseSize++) 
	    		{
	    			Response response = responses.get(responseSize);
	    			logger.info("\tResponse : ");
	    			logger.info("\t\tStatus Code: "+response.statusCode().value());
	    			List <Payload> payloads = response.payloads();
	    			for(int payloadSize = 0; payloadSize < payloads.size(); payloadSize++)
	    			{
	    				Payload payload = payloads.get(payloadSize);
	    				NodeShape object = (NodeShape) payload.schema().inherits().get(0).linkTarget().get();
	    				logger.info("\t\tDataType: "+object.name().value()+"\n\t\t\t\t\t\tProperties:");
	    				List<PropertyShape> properties = object.properties();
	    				for(int m = 0; m < properties.size(); m++) 
	    				{
	    					logger.info("\t\t\t\t"+properties.get(m).name().value());
	    				}
	    			}
	    		}
	    	}
	    	
	    }
	} 
	
	
}
