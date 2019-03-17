package hello.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import hello.controller.FileUploadController;
import hello.send.Sending;
import hello.webServices.CancelCallOrder;
import hello.webServices.ReleaseMsisdn;
import hello.webServices.UpdateLanguage;
import hello.webServices.UpdateSubscriptionProfileServices;

import java.time.LocalDateTime;




public class Write {
	static int random;
	public static void main(String[] args) throws IOException {
		
		String data = null ;

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		
		//Prepare results for writing in file according to methode's type
		switch (Sending.getMethod()) {
		case "cancelCallOrder":
			
			if (Numbers.getCount() == Sending.c) {	
				
				Random rand = new Random(); 
				random = rand.nextInt(1000);
				data = "Starts at "+dtf.format(now)+"\r\n"+ CancelCallOrder.getResult() +"   "+ Numbers.num;
				
			}else if(Sending.c == 1) {
				
				 data = CancelCallOrder.getResult() +"   "+ Numbers.num+"\r\n"+"Ends at "+dtf.format(now);
				 
			}else{
				
				data = CancelCallOrder.getResult() +"   "+ Numbers.num;
			}
			
			break;
		case "updateSubscriptionProfileServices":
			
			if (Numbers.getCount() == Sending.c) {	
				
				Random rand = new Random(); 
				random = rand.nextInt(1000);
				data = "Starts at "+dtf.format(now)+"\r\n"+UpdateSubscriptionProfileServices.getResult() +"   "+ Numbers.num;
				
			}else if(Sending.c == 1) {
				
				 data = UpdateSubscriptionProfileServices.getResult() +"   "+ Numbers.num+"\r\n"+"Ends at "+dtf.format(now);
				 
			}else{
				
				data = UpdateSubscriptionProfileServices.getResult() +"   "+ Numbers.num;
			}
			
			break;
		case "msisdn":
	
			if (Numbers.getCount() == Sending.c) {	
				
				Random rand = new Random(); 
				random = rand.nextInt(1000);
				data = "Starts at "+dtf.format(now)+"\r\n"+ReleaseMsisdn.getNumber() +"   "+ Numbers.num;
				
			}else if(Sending.c == 1) {
				
				 data = ReleaseMsisdn.getNumber() +"   "+ Numbers.num+"\r\n"+"Ends at "+dtf.format(now);
				 
			}else{
				
				data = ReleaseMsisdn.getNumber() +"   "+ Numbers.num;
			}
			
			break;
		case "updateLanguage":
			
			if (Numbers.getCount() == Sending.c) {	
				
				Random rand = new Random(); 
				random = rand.nextInt(1000);
				data = "Starts at "+dtf.format(now)+"\r\n"+UpdateLanguage.getNumber() +"   "+ Numbers.num;
				
			}else if(Sending.c == 1) {
				
				 data = UpdateLanguage.getNumber() +"   "+ Numbers.num+"\r\n"+"Ends at "+dtf.format(now);
				 
			}else{
				
				data = UpdateLanguage.getNumber() +"   "+ Numbers.num;
			}
			
			break;

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
