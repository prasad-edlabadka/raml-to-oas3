import webapi.Raml10;
import webapi.WebApiBaseUnit;
import webapi.Oas30;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class conversion {

	public static void OasConversion() throws InterruptedException, ExecutionException {
		
		String inp = "file:///home/hp/Documents/APIConverter/BranchManagement.raml";
		String fpath = "file:///home/hp/Documents/APIConverter/BranchManagement.yaml";
		final WebApiBaseUnit result = Raml10.parse(inp).get();

		Oas30.generateFile(result, fpath).get();
	}
	
	public static String getFileName(String input) throws FileNotFoundException, IOException {
		String filename = "";
		try {
			int position = input.lastIndexOf("/");
			filename = input.substring(position+1);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return filename;
	}
	
	public static void getSchemas() throws InterruptedException, ExecutionException {
		String fpath = "/home/hp/Documents/APIConverter/BranchManagement.yaml";
		JSONParser parser = new JSONParser();
	      try {
	         Object obj = parser.parse(new FileReader(fpath));
	         JSONObject jsonObject = (JSONObject)obj;
	         
	         JSONObject components = (JSONObject)jsonObject.get("components");
	         JSONObject schemas = (JSONObject) components.get("schemas");
	         
	         Set dataTypesNames = schemas.keySet();
	         for(Object dataTypeName:dataTypesNames) {
	        	 JSONObject dataType = (JSONObject) schemas.get(dataTypeName);
	        	 if(dataType.containsKey("properties")) {
	        		 JSONObject properties = (JSONObject) dataType.get("properties");
	        		 Set propertyNames = properties.keySet();
	        		 for(Object propertyName:propertyNames) {
	        			 JSONObject property = (JSONObject) properties.get(propertyName);
	        			 if(property.containsKey("x-amf-merge")) {
	        				 JSONArray mergeObjectArray = (JSONArray) property.get("x-amf-merge");
	        				 for(int i = 0; i < mergeObjectArray.size(); i++) {
	        					 JSONObject mergeObject = (JSONObject) mergeObjectArray.get(i);
	        					 String value = (String) mergeObject.get("$ref");
	        					 
	        					 String fileName = getFileName(value);
	        					 mergeObject.put("$ref", fileName+".yaml");
	        				 }
	        		     }
	        	      }   
	        	 }
	        	 try (FileWriter file = new FileWriter("/home/hp/Documents/APIConverter/"+dataTypeName+".yaml")) {
    		        file.write(dataType.toJSONString());
    		        file.flush();
    		      
    		     } catch (IOException e) {
    		         e.printStackTrace();
    		     }
	        	 dataType.clear();
	        	 dataType.putIfAbsent("$ref", dataTypeName.toString()+".yaml");
    		
	        }
	        	 
	       
	         FileWriter file = new FileWriter("/home/hp/Documents/APIConverter/NewBranchManagement.yaml");
	         file.write(jsonObject.toString());
	         file.flush();
	      }
	
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	    
	public static void main(String [] args) {
		try {
			//OasConversion();
			getSchemas();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}

