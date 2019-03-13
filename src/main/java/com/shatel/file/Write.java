package com.shatel.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import com.shatel.controller.FileUploadController;
import com.shatel.send.Sending;
import com.shatel.webServices.ReleaseMsisdn;

import java.time.LocalDateTime;




public class Write {
	static int random;
	public static void main(String[] args) throws IOException {
		
		String data;

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		
		//Prepare results for writing in file    
		if (Numbers.getCount() == Sending.c) {	
			
			Random rand = new Random(); 
			random = rand.nextInt(1000);
			data = "Starts at "+dtf.format(now)+"\r\n"+ReleaseMsisdn.getNumber() +"   "+ Numbers.num;
			
		}else if(Sending.c == 1) {
			
			 data = ReleaseMsisdn.getNumber() +"   "+ Numbers.num+"\r\n"+"Ends at "+dtf.format(now);
			 
		}else{
			
			data = ReleaseMsisdn.getNumber() +"   "+ Numbers.num;
		}
		
		String path = FileUploadController.getPath().toString().replace("file:/", "");
        String filePath = path.replace(".txt", "") + "_Log_" + random + ".txt";

        appendUsingBufferedWriter(filePath, data);
        

        System.out.println("DONE");
    }
	
	private static void appendUsingBufferedWriter(String filePath, String text) {
		File file = new File(filePath);
		FileWriter fr = null;
		BufferedWriter br = null;
		try {
			// to append to file, you need to initialize FileWriter using below constructor
			fr = new FileWriter(file, true);
			br = new BufferedWriter(fr);
			
				br.newLine();
				// you can use write or append method
				br.write(text);
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
