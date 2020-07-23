import webapi.Oas30;
import webapi.Raml10;
import webapi.WebApiBaseUnit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;
public class Oas30Generation 
{
	public void generateString(String input) throws InterruptedException, ExecutionException 
	{
		Logger logger=Logger.getLogger(Oas30Generation.class);
		WebApiBaseUnit result = Raml10.parse(input).get();
		String output = Oas30.generateString(result).get();
		
		logger.info(input+"\n"+output);
    
    }
	public String FileToString(String filePath) 
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
        {
 
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) 
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }  
	
	public void RecursiveRead(String filepath, String content, String prefix) throws InterruptedException, ExecutionException 
	{
		int position = content.indexOf("!include");
		if(position == -1) {
			generateString("file://"+filepath);
		}
		else {
			content = content.substring(position+9);
			position = content.indexOf('\n');
			RecursiveRead(prefix+content.substring(0, position), FileToString(prefix+content.substring(0, position)), prefix);
			content = content.substring(position+1);
			RecursiveRead(filepath, content, prefix);
		}
	}
}