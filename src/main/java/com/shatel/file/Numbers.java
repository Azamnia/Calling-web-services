package com.shatel.file;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Numbers {
	
	private static String num;
	private static List<String> line = new ArrayList<String>();
	private static int count;
	private static String fileName = "E://file.txt";
	
	 public static void main(String[] args) throws IOException {
	  
	        readUsingScanner(fileName);
	        
	    }

	private static void readUsingScanner(String fileName) throws IOException {
			count = 0;
	        Path path = Paths.get(fileName);
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
