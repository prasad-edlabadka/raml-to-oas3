import java.util.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
public class Main 
 {
	  public static void main(String [] args) 
	  {
		  Scanner sc=new Scanner(System.in);
		  Logger logger=Logger.getLogger(Main.class);
		  BasicConfigurator.configure();
		  logger.info("------WELCOME TO API CONVERTER------");
		  
		  try 
		  { 
			  String continueProcess,filePath, prefix;
			  
			  do
			  {
				 
				  logger.info("\n 1. Navigate RAML Specification \n 2. Translate RAML Data types to JSON \n 3. Convert RAML to OAS (for RAML with inline declarations) \n 4. Convert RAML to OAS (for RAML with included files)");
				  logger.info("** Please Enter Your Choice to proceed **");
				  int UserChoice=sc.nextInt();
				  switch(UserChoice)
				  {
				  	case 1:
					  logger.info("Enter path of raml specification");
					  logger.info("Usage : file://+path");
					  filePath=sc.next();
					  NavigatorClass navigateObject=new NavigatorClass();
					  navigateObject.navigateApi(filePath);
					  break;
				  	case 2:
					  logger.info("Enter path of raml specification");
					  logger.info("Usage : file://+path");
					  filePath=sc.next();
					  Translator translateObject=new Translator();
					  translateObject.translateFromLibrary(filePath);
					  
					  break;
				  	case 3:
					  logger.info("Enter path of raml specification");
					  logger.info("Usage : file://+path");
					  filePath=sc.next();
					  Oas30Generation generateObjectInline=new Oas30Generation();
					  generateObjectInline.generateString(filePath);
					  
					  break;
				  	case 4:
				  	  logger.info("Enter path of raml specification");
					  logger.info("Usage : path");
					  filePath=sc.next();
					  logger.info("Enter directory prefix");
					  prefix = sc.next();
					  Oas30Generation generateObject=new Oas30Generation();
					  generateObject.RecursiveRead(filePath, generateObject.FileToString(filePath), prefix);
					  break;
				  	default:
				  		logger.info("Inavlid Choice");
					  break;
			 
				  }
				  logger.info("Do you want to continue(yes/no)..Enter yes or no");
				  continueProcess=sc.next();
			  }while(continueProcess.equalsIgnoreCase("yes"));
		  }
	      catch(Exception e)
		  {	
	    		logger.error(e);
		  }
		  sc.close();
	  }
 }
	