import webapi.Raml10;
import webapi.WebApiBaseUnit;

import java.util.concurrent.ExecutionException;
public class ramlTrial {
	public static void generateFile() throws InterruptedException, ExecutionException {
	    // Parse RAML 1.0 file to get WebApi Model
	    final WebApiBaseUnit result = Raml10.parse("/home/hp/trials/Album.raml").get();

	    // Resolve parsed model (optional)
	    final WebApiBaseUnit resolved = Raml10.resolve(result).get();

	    String fpath = "/home/hp/trials/generated.raml";
	    // Generate RAML 1.0 file
	    Raml10.generateFile(resolved, fpath).get();
	    System.out.println("Generating Raml10 file at: " + fpath);
	  }
	public static void main(String[] args) {
		try {
			generateFile();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
