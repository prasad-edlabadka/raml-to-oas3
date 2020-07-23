import webapi.Raml10;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;

import amf.client.model.domain.NodeShape;

import webapi.WebApiDocument;


public class Translator
{
	Logger logger=Logger.getLogger(Translator.class);
	public void translateFromLibrary(String inp) throws InterruptedException, ExecutionException ,ClassCastException
	{
		 WebApiDocument model = (WebApiDocument) Raml10.parse(inp).get();
	    
		 int index= 0;
	    
		 while(true)
		 {
	    	try
	    	{	    		
	    		NodeShape datatype = (NodeShape) model.declares().get(index);
	    		logger.info(datatype.toJsonSchema());
	    		index ++;
	    	}catch(Exception e)
	    	{
	    		break;
	    	}
	    }
	 }	    
}
