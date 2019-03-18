package hello.send;

import java.io.IOException;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;

import hello.file.Numbers;
import hello.file.Write;
import hello.webServices.CancelCallOrder;
import hello.webServices.CheckSession;
import hello.webServices.Login;
import hello.webServices.ReleaseMsisdn;
import hello.webServices.UpdateLanguage;
import hello.webServices.UpdateSubscriptionProfileServices;



public class Sending  {
	
	public static int c;
	
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
	
	public static void checkSession () {
		
		 try{
		        
		        //create SOAPConnection
				SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
				
				//to send the message directly
				SOAPConnection soapConnection = soapConnectionFactory.createConnection();		
				String url2 = "https://pre.shatelmobile.ir/externalclient/services/access/v7.0?wsdl";
				
				SOAPMessage soapRequest2 = CheckSession.createSoapRequest();
				
				//hit soapRequest to the server to get response				
				SOAPMessage soapResponse2 = soapConnection.call(soapRequest2, url2);
				CheckSession.createSoapResponse(soapResponse2);
				
				soapConnection.close();
				
			}catch (Exception e) {
			     e.printStackTrace();
			}
	}
	
	public static void msisdn() throws IOException {
		
		System.out.println("sdsdasdsd");
		 c = Numbers.getCount();
		
		 checkSession();
		 
		if(!(CheckSession.getCheck().equals("OK"))) {
				
				login();
				
			}
		 
		do {
			
		try{

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
	
	public static void updateLanguage() throws IOException {
		
		System.out.println("sdsdasdsd");
		 c = Numbers.getCount();
		
		 checkSession();
		 
		if(!(CheckSession.getCheck().equals("OK"))) {
				
				login();
				
			}
		 
		do {
			
		try{

			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			String url = "https://pre.shatelmobile.ir/externalclient/services/subscriptionbasic/v7.3?wsdl";
		
			SOAPMessage soapRequest = UpdateLanguage.createSoapRequest();
			SOAPMessage soapResponse = soapConnection.call(soapRequest, url);
			UpdateLanguage.createSoapResponse(soapResponse);
			
			Write.main(null);
			
			c--;
			
		}catch (Exception e) {
		     e.printStackTrace();
		}
		}while(c > 0);
	}
	
	public static void updateSubscriptionProfileServices() throws IOException {
		
		System.out.println("sdsdasdsd");
		 c = Numbers.getCount();
		
		 checkSession();
		 
		if(!(CheckSession.getCheck().equals("OK"))) {
				
				login();
				
			}
		 
		do {
			
		try{

			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			String url = "http://172.16.2.15:8118/externalclient/services/subscriptionbasic/v7.3?wsdl";
		
			SOAPMessage soapRequest = UpdateSubscriptionProfileServices.createSoapRequest();
			SOAPMessage soapResponse = soapConnection.call(soapRequest, url);
			UpdateSubscriptionProfileServices.createSoapResponse(soapResponse);
			
			Write.main(null);
			
			c--;
			
		}catch (Exception e) {
		     e.printStackTrace();
		}
		}while(c > 0);
	}
	
	public static void cancelCallOrder() throws IOException {
		
		System.out.println("sdsdasdsd");
		 c = Numbers.getCount();
		
		 checkSession();
		 
		if(!(CheckSession.getCheck().equals("OK"))) {
				
				login();
				
			}
		 
		do {
			
		try{

			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			String url = "http://172.16.2.15:8118/externalclient/services/subscriptionorder/v7.1?wsdl";
		
			SOAPMessage soapRequest = CancelCallOrder.createSoapRequest();
			SOAPMessage soapResponse = soapConnection.call(soapRequest, url);
			CancelCallOrder.createSoapResponse(soapResponse);
			
			Write.main(null);
			
			c--;
			
		}catch (Exception e) {
		     e.printStackTrace();
		}
		}while(c > 0);
	}
	
}
