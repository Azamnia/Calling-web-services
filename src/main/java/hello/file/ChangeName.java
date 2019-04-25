package hello.file;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ChangeName {
	
	private static String path;
	
	//To change the file name
	@Test
	public static void changeFileName() throws IOException {
		
		String changePath;
		System.out.println("Change Name");
	    File file = new File(getPath());
	    changePath = getPath().replace(".csv", "@@@@@");
	    System.out.println( changePath.replace("live_reports", "Archive") + ".csv");
	    file.renameTo(new File(changePath.replace("live_reports", "Archive") + ".csv"));
	}
    
	//For reading files from folder every hour
	@Scheduled(cron = "0 1 */1 * * ?")
    public static void listing() throws EncryptedDocumentException, InvalidFormatException, IOException {
    	
        File file = new File("C:/renew_action/live_reports/");
        File[] files = file.listFiles();
        for(File f: files){
        	System.out.println(f.getPath());
            System.out.println(f.getName());
            setPath(f.getPath());
            if(!(f.getName().contains("@@@@@")) && f.getName().contains(".csv")) {
            	System.out.println("changiiiiiiiiiiiing");
            	Csv.readUsingBufferedReader();
            	changeFileName();
            }
        }
        
    }

	public static String getPath() {
		return path;
	}

	public static void setPath(String path) {
		ChangeName.path = path;
	}
    
    

}
