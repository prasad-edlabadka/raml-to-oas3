import webapi.Oas30;
import webapi.Raml10;
import webapi.WebApiBaseUnit;

import java.util.concurrent.ExecutionException;



public class Oas30Generation {

  
  public static void generateString() throws InterruptedException, ExecutionException {
    // Parse OAS 3.0 JSON file to get WebApi Model
    final WebApiBaseUnit result = Raml10.parse("file://c:/branch/branch-management.raml").get();

    // Generate OAS 3.0 JSON string
    final String output = Oas30.generateString(result).get();
    System.out.println("Generating Oas30 JSON string: " + output);
  }
}