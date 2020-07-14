import java.util.Scanner;
import webapi.Raml10;
import java.util.concurrent.ExecutionException;

import amf.client.model.domain.NodeShape;
import webapi.Raml10;
import webapi.WebApiDocument;
import co.acme.parse.Oas30Parsing;

public class Translator
{
	public static void translateFromLibrary(String inp) throws InterruptedException, ExecutionException ,ClassCastException {
	    
	   Scanner sc=new Scanner(System.in);
	   
	    WebApiDocument model = (WebApiDocument) Raml10.parse(inp).get();
	    
	    int i= 0;
	    
	    while(true)
	    {
	    	try
	    	{
	    		
	    	
	    	
	    	NodeShape book2 = (NodeShape) model.declares().get(i);
	    
	    	System.out.println("Name :"+book2.name());
	    	
	    		System.out.println(book2.toJsonSchema());
	    		i++;
	    	}catch(Exception e)
	    	{
	    		break;
	    	}
	    		
	    		
	    	
	    }}
	    
	
	    
}
