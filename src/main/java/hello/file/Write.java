package hello.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import hello.controller.FileUploadController;
import hello.controller.SpringMVCController;
import hello.send.Sending;
import hello.webServices.CancelCallOrder;
import hello.webServices.ReleaseMsisdn;
import hello.webServices.UpdateLanguage;
import hello.webServices.UpdateSubscriptionProfileServices;

import java.time.LocalDateTime;




public class Write {
	private static int random;
	private static String data;

	public static void main(String[] args) throws IOException {
		

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		
		//Prepare results for writing in file according to methode's type
		switch (SpringMVCController.getMethod()) {
		case "cancelCallOrder":	
			if (Numbers.getCount() == Sending.c) {	
				
				Random rand = new Random(); 
				random = rand.nextInt(1000);
				setData("Starts at "+dtf.format(now)+"\r\n"+ CancelCallOrder.getResult() +"   "+ Numbers.num);
				
			}else if(Sending.c == 1) {
				
				setData(CancelCallOrder.getResult() +"   "+ Numbers.num+"\r\n"+"Ends at "+dtf.format(now));
				 
			}else{
				
				setData(CancelCallOrder.getResult() +"   "+ Numbers.num);
			}
			
			break;
		case "updateSubscriptionProfileServices":	
			if (Numbers.getCount() == Sending.c) {	
				
				Random rand = new Random(); 
				random = rand.nextInt(1000);
				setData("Starts at "+dtf.format(now)+"\r\n"+UpdateSubscriptionProfileServices.getResult() +"   "+ Numbers.num);
				
			}else if(Sending.c == 1) {
				
				setData(UpdateSubscriptionProfileServices.getResult() +"   "+ Numbers.num+"\r\n"+"Ends at "+dtf.format(now));
				 
			}else{
				
				setData(UpdateSubscriptionProfileServices.getResult() +"   "+ Numbers.num);
			}
			
			break;
		case "msisdn":
			if (Numbers.getCount() == Sending.c) {	
				
				Random rand = new Random(); 
				random = rand.nextInt(1000);
				setData("Starts at "+dtf.format(now)+"\r\n"+ReleaseMsisdn.getNumber() +"   "+ Numbers.num);
				
			}else if(Sending.c == 1) {
				
				setData(ReleaseMsisdn.getNumber() +"   "+ Numbers.num+"\r\n"+"Ends at "+dtf.format(now));
				 
			}else{
				
				setData(ReleaseMsisdn.getNumber() +"   "+ Numbers.num);
			}
			
			break;
		case "updateLanguage":
			if (Numbers.getCount() == Sending.c) {	
				
				Random rand = new Random(); 
				random = rand.nextInt(1000);
				setData("Starts at "+dtf.format(now)+"\r\n"+UpdateLanguage.getNumber() +"   "+ Numbers.num);
				
			}else if(Sending.c == 1) {
				
				setData(UpdateLanguage.getNumber() +"   "+ Numbers.num+"\r\n"+"Ends at "+dtf.format(now));
				 
			}else{
				
				setData(UpdateLanguage.getNumber() +"   "+ Numbers.num);
			}
			
			break;

		}
		
		String path = FileUploadController.getPath().toString().replace("file:/", "");
        String filePath = path.replace(".txt", "") + "_Log_" + random + ".txt";

        appendUsingBufferedWriter(filePath, getData());
        System.out.println("DONE");
    }
	
	//For writing in the file
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

	public static String getData() {
		return data;
	}

	public static void setData(String data) {
		Write.data = data;
	}
	
	

}
