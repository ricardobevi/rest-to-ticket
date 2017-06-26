package resttoticket;
import static spark.Spark.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args )
    {
    	
    	port(8080); 
    	get("/print/:text", (req, res) -> {
    		
    		String printText = req.params(":text");
    		
    		print(printText);
    		return "Printing '" + printText + "'...";
    	});
    	
    	
    	
    }

	private static void print(String content) {
		// 0x1B, 0x2A, 0x0, 0x5, 0x0, 128, 64, 32, 16, 8

    	try {
    		
    		byte[] bytes = content.getBytes();
    		
    		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    		
    		
    		byteArrayOutputStream.write(0x1B);
    		byteArrayOutputStream.write(0x40);
    		/*
    		byteArrayOutputStream.write(0x1B);
    		byteArrayOutputStream.write(0x21);
    		byteArrayOutputStream.write(0x00);
    		
    		byteArrayOutputStream.write(0x1B);
    		byteArrayOutputStream.write(0x21);
    		byteArrayOutputStream.write(0x30);
    		
    	
    		byteArrayOutputStream.write(bytes);
    		
    		byteArrayOutputStream.write('\n');
    		*/
    		
    		byteArrayOutputStream.write(0x1B);
    		byteArrayOutputStream.write(0x2A);
    		byteArrayOutputStream.write(0x00);
    		byteArrayOutputStream.write(0x05);
    		byteArrayOutputStream.write(0x00);
    		byteArrayOutputStream.write(128);
    		byteArrayOutputStream.write(64);
    		byteArrayOutputStream.write(32);
    		byteArrayOutputStream.write(16);
    		byteArrayOutputStream.write(8);
    		
    		byteArrayOutputStream.write('\n');

    		System.out.println("Sending data...");
    		
    		
			Files.write(Paths.get("/dev/ttyUSB0"), 
					byteArrayOutputStream.toByteArray());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
