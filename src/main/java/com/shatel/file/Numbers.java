package hello.file;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hello.controller.FileUploadController;

public class Numbers {
	
	public static String num;
	private static List<String> line = new ArrayList<String>();
	private static int count;
	private static String filePath;
	
	 public static void main(String[] args) throws IOException {
	  
		 filePath = FileUploadController.getPath().toString().replace("file:/", "");
		 System.out.println(filePath);
	        readUsingScanner(filePath);
	        
	    }

	private static void readUsingScanner(String filePath) throws IOException {
			count = 0;
	        Path path = Paths.get(filePath);
	        Scanner scanner = new Scanner(path);
	        System.out.println("Read text file using Scanner");
	        
	        //read line by line
	        while(scanner.hasNextLine()){
	        	
	            //process each line
	        	line.add(scanner.nextLine());
	        	count++;

	        }
	        
	    scanner.close();
    }
	
	public static String getNumbers() {
		for (String str : line) {
			System.out.println(str);
			num = str;
			line.remove(0);
			break;
		}
		return num;
		
	}

	public static int getCount() throws IOException {
		return count;
	}

	public static void setCount(int count) {
		Numbers.count = count;
	}

}
