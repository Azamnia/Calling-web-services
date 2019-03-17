package hello.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import hello.send.Sending;
import hello.webServices.CheckSession;
import hello.webServices.Login;

@Component
public class Sessions {
	
	private static Duration diff;
	
	public static void main(String[] args) throws Exception {
		
		String data = Login.getSession();
        appendUsingBufferedWriter("C:/Users/sessions.txt", data);
        System.out.println("DONE");	
		
	}
	
	//Write the last SessionId in file
	private static void appendUsingBufferedWriter(String filePath, String text) {
		File file = new File(filePath);
		FileWriter fr = null;
		BufferedWriter br = null;
		try {
				//overwrites file
				fr = new FileWriter(file, false);
				br = new BufferedWriter(fr);
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
	
	//read the last SessionId
	public static String checking () throws Exception {
		
		String fileName = "C:/Users/sessions.txt";
		Scanner scanner = new Scanner(fileName);
		String line = scanner.nextLine();
		scanner.close();
		
		//Check it
		Login.setSession(line);
		Sending.checkSession();
		if( CheckSession.getCheck() != "OK") {
			
			Sending.login();
			main(null);
			return Login.getSession();
			
		}else {
			
			timing();
			Duration d = Duration.ofMinutes(30);
			
			if(getDiff().compareTo(d) == -1 || getDiff().compareTo(d) == 0) {
				
				Sending.login();
				main(null);
				
			}
			
			return line;
		}
	}
	
	public static void timing() throws ParseException {
		
        //Get the expiration time
        String string = CheckSession.getTime();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = format.parse(string);
        System.out.println(date);

        DateFormat output = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = output.format(date);
        System.out.println(formattedTime);
        
        //Get the current time
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss"); 
        LocalDateTime now = LocalDateTime.now();  
        String nowTime = dtf.format(now);
        System.out.println(nowTime);  

        //Calculate the remaining time
        LocalTime t1 = LocalTime.parse(formattedTime);
        LocalTime t2 = LocalTime.parse(nowTime);
        setDiff(Duration.between(t2, t1));
        System.out.println(diff.toMinutes());
        
	}
	
	//Check the SessionId every 30 minutes
	@Scheduled(cron = "0 */30 * * * ?")
	public void getSessionScheduled() throws Exception {
		
		Sending.checkSession();
		timing();
		
		Duration d = Duration.ofMinutes(30);
		
		if(getDiff().compareTo(d) == -1 || getDiff().compareTo(d) == 0) {
			
			Sending.login();
			main(null);
			
		}
		
	}

	public static Duration getDiff() {
		return diff;
	}

	public static void setDiff(Duration diff) {
		Sessions.diff = diff;
	}
	
	

}
