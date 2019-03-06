package com.shatel.send;

import java.io.IOException;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;

import com.shatel.file.Numbers;
import com.shatel.file.Write;
import com.shatel.webServices.Login;
import com.shatel.webServices.ReleaseMsisdn;

public class Sending  {
	
	public static void login () {
		
		 try{
		        
		        //create SOAPConnection
				SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
				
				//to send the message directly
				SOAPConnection soapConnection = soapConnectionFactory.createConnection();		
				String url2 = "https://pre.shatelmobile.ir/externalclient/services/access/v7.0?wsdl";
				
				SOAPMessage soapRequest2 = Login.createSoapRequest();
				
				//hit soapRequest to the server to get response				
				SOAPMessage soapResponse2 = soapConnection.call(soapRequest2, url2);
				Login.createSoapResponse(soapResponse2);
				
				soapConnection.close();
				
			}catch (Exception e) {
			     e.printStackTrace();
			}
	}
	
	public static void msisdn() throws IOException {
		
		int c = Numbers.getCount();
		
		do {
			
		try{
					if(ReleaseMsisdn.getSession() != "ok") {
			
						login();
						
					}
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			String url = "https://pre.shatelmobile.ir/externalclient/services/inventory/v7.2?wsdl";
		
			SOAPMessage soapRequest = ReleaseMsisdn.createSoapRequest();
			SOAPMessage soapResponse = soapConnection.call(soapRequest, url);
			ReleaseMsisdn.createSoapResponse(soapResponse);
			
			Write.main(null);
			
			c--;
			
		}catch (Exception e) {
		     e.printStackTrace();
		}
		}while(c > 0);
	}
}
