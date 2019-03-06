package com.shatel.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


import com.shatel.webServices.ReleaseMsisdn;


public class Write {
	
	public static void main(String[] args) throws IOException {
		
        String data = ReleaseMsisdn.getNumber();

        writeUsingOutputStream(data);
        

        System.out.println("DONE");
    }
	
	private static void writeUsingOutputStream(String data) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File("E://result.txt"));
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
