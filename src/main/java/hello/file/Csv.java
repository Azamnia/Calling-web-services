package hello.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import hello.controller.SpringMVCController;
import hello.send.Sending;

public class Csv {
	
	private static String SID;
	private static String Msisdn;
	private static String PKGInput;
	private static String PKGOutput;
	private static int check = 0;
	private static String filePath;
	
	public static void readUsingBufferedReader() throws IOException, EncryptedDocumentException, InvalidFormatException {

		SpringMVCController.setMethod("addPackagesSimple");
		filePath = ChangeName.getPath();
		List<List<String>> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
		    String line;
		    
		    //Read the whole file and
		    while ((line = br.readLine()) != null) {
		    	String[] values = line.split("\"");
		        records.add(Arrays.asList(values));
		        System.out.println(Arrays.asList(values));
		    }
		    
		    setCheck(1);
		    int i = records.size();
		    System.out.println(i);
		    //Read line by line
		    while(i > 1) {
		    	
			    String j = records.get(i-1).toString();
			    System.out.println(j);
			    setSID(j.substring(j.indexOf("SID"), j.indexOf('|')-2));
			    setMsisdn(j.substring(j.indexOf("998"), j.indexOf("998")+10));
			    
			    if(j.contains(("Ini")))
			    setPKGInput(j.substring(j.indexOf("Ini"), j.indexOf("PKG")-3));
			    
			    else if(j.contains(("Data")))
			    setPKGInput(j.substring(j.indexOf("Data"), j.indexOf("PKG")-3));
			    
			    else if(j.contains(("Test")))
			    setPKGInput(j.substring(j.indexOf("Test"), j.indexOf("PKG")-3));

			    System.out.println(getSID());
			    System.out.println(getMsisdn());
			    System.out.println(getPKGInput());

			    i--;
			    
			    checkWithTable(getPKGInput());
				
		    }

		}
		

				
    }
	
	//Check with the mapping table
	private static void checkWithTable(String Package_input) throws IOException {
		
		int ko = 0;
		String jo;
		File myFile = new File("C:/Users/input&amp_ADDON_2019_09_04_20190410_120625750.xlsx");
		FileInputStream fis = new FileInputStream(myFile);
		// Finds the workbook instance for XLSX file
		XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
		// Return first sheet from the XLSX workbook 
		XSSFSheet mySheet = myWorkBook.getSheetAt(0); 
		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = mySheet.iterator();
		// Traversing over each row of XLSX file
		while (rowIterator.hasNext()) { Row row = rowIterator.next();
		// For each row, iterate through each columns 
		Iterator<Cell> cellIterator = row.cellIterator();
		while (cellIterator.hasNext()) 
		{ 
			Cell cell = cellIterator.next();
		switch (cell.getCellType())
		{ 
		case Cell.CELL_TYPE_STRING:
			jo = cell.getStringCellValue().toString();
			if(jo.contains("Ini")){
				if(jo.substring(jo.indexOf("Ini")).equals(getPKGInput())) {
					System.out.println("Ini");
					ko=1;
					break;
				}
			}
			
			else if(jo.contains("Data")){
				if(jo.substring(jo.indexOf("Data")).equals(getPKGInput())) {
					System.out.println("Data");
					ko=1;
					break;
				}
			}
			
			else if(jo.contains("Test")){
				if(jo.substring(jo.indexOf("Test")).equals(getPKGInput())) {
					System.out.println("Test");
					ko=1;
					break;
				}
			}
			if(ko==1) {
				setPKGOutput(jo.substring(jo.indexOf("Bonu")));
				System.out.println(getPKGOutput());
				Sending.addPackagesSimple();
				System.out.println("");
				ko=0;

				}
			break; 
			
		case Cell.CELL_TYPE_NUMERIC:
			System.out.print(cell.getNumericCellValue() + "\t");
			break;
			
		case Cell.CELL_TYPE_BOOLEAN:
			System.out.print(cell.getBooleanCellValue() + "\t");
			break;
		
		default : 
			
	}
	}
	}			
	}
	
	
	public static String getSID() {
		return SID;
	}

	public static void setSID(String sID) {
		SID = sID;
	}

	public static String getMsisdn() {
		return Msisdn;
	}

	public static void setMsisdn(String msisdn) {
		Msisdn = msisdn;
	}

	public static String getPKGInput() {
		return PKGInput;
	}

	public static void setPKGInput(String pKGInput) {
		PKGInput = pKGInput;
	}

	public static String getPKGOutput() {
		return PKGOutput;
	}

	public static void setPKGOutput(String pKGOutput) {
		PKGOutput = pKGOutput;
	}

	public static int getCheck() {
		return check;
	}

	public static void setCheck(int check) {
		Csv.check = check;
	}
	
	

}
