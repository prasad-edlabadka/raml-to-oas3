import java.util.*;
import java.util.logging.Logger;

import org.apache.log4j.BasicConfigurator;
public class RamlCOnverter 
 {
	  public static void main(String [] args) 
	  {
		  Scanner sc=new Scanner(System.in);
		  Logger logger=Logger.getLogger(RamlCOnverter.class);
		  BasicConfigurator.configure();
		  try 
		  {
			  logger.info("Enter path of raml specification that you want to convert into Oas Specification");
			  logger.info("Sample : file://c:/branch/branch-management.raml");
			  System.out.println("Enter path of raml specification that you want to convert into Oas Specification ");
			  System.out.println("Sample : file://c:/branch/branch-management.raml");
			  String input=sc.next();
	    			NavigatorClass.navigateApi(input);
	    			//Translator.translateFromLibrary(input);
	    			
	    			Oas30Generation.generateString();
	    		 }
	    		catch(Exception e)
		  		{
	    			System.out.println(e);
	    		}
	    	}
	  
 }
	