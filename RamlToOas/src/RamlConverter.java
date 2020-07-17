import java.util.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class RamlConverter 
 {
		
	public static void main(String [] args) 
	  {
		  Scanner sc=new Scanner(System.in);
		  Logger logger=Logger.getLogger(RamlConverter.class);
		  BasicConfigurator.configure();
		  try 
		  {
			  logger.info("Enter path of raml specification that you want to convert into Oas Specification");
			  logger.info("Sample : file://c:/branch/branch-management.raml");
			  	
			  String filePath=sc.next();
	    	  NavigatorClass.navigateApi(filePath);
	    	  //Translator.translateFromLibrary(filePath);
	    	  //Oas30Generation.generateString(filePath);
	    	  //Oas30Generation.RecursiveRead(filePath, Oas30Generation.FileToString(filePath), "/home/hp/Documents/APIConverter/BranchManagementYaml/");
	      }
	      catch(Exception e)
		  {	
	    		logger.error(e);
		  }
		  sc.close();
	  }
	  
 }
	