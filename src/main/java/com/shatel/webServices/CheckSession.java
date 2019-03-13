package com.shatel.webServices;


import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.NodeList;

public class CheckSession {
	
private static String check;
private static String time;
	

	public static SOAPMessage createSoapRequest() throws Exception{
		
		//create SOAPMessage instance (baraye sakhte yek nemoone az soap message)
		 MessageFactory messageFactory = MessageFactory.newInstance();
		 
		 //create SOAP document(XML)
		 SOAPMessage soapMessage = messageFactory.createMessage();
		 
		 //create container for SOAP specific portion of SOAPMessage
		 SOAPPart soapPart = soapMessage.getSOAPPart();
		 
		 //SOAPEnvelope is the container for SOAPHeader and SOAPBody portions
   	     SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
   	     soapEnvelope.addNamespaceDeclaration("ws7", "http://ws7.bssexternalclient.alinea.com/");
   	         
   	     //create an object for a SOAP message
		 SOAPBody soapBody = soapEnvelope.getBody();
		 
		 //represents the element of SOAPMessage
		 SOAPElement soapElement = soapBody.addChildElement("checkRemainingSessions", "ws7");
		 
		 //make Message's body
		 SOAPElement element1 = soapElement.addChildElement("wsSessionId");
		 element1.addTextNode(Login.getSession());
		 
		 soapMessage.saveChanges();
		 
		 System.out.println("----------SOAP Request------------");
		 soapMessage.writeTo(System.out);
		 return soapMessage;
	 }
	
	 public static void createSoapResponse(SOAPMessage soapResponse) throws Exception  {
		 
		 	//create Transformer
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			
			//transform a source tree into result tree
			Transformer transformer = transformerFactory.newTransformer();
			
			//acts as XML source
			Source sourceContent = soapResponse.getSOAPPart().getContent();
			
			System.out.println("\n----------SOAP Response-----------");
			
			//holder of transformer result that can be an XML
			StreamResult result = new StreamResult(System.out);
			transformer.transform(sourceContent, result);
			
			SOAPBody body = soapResponse .getSOAPBody();
			
			// processing nodes to get SessionId
			NodeList returnList = body.getElementsByTagName("resultCode");
			for (int k = 0; k < returnList.getLength(); k++) {
			    NodeList innerResultList = returnList.item(k).getChildNodes();
			    setCheck(innerResultList.item(k).getTextContent());
		 }
			
			NodeList returnList2 = body.getElementsByTagName("nextAvailableDate");
			for (int k = 0; k < returnList2.getLength(); k++) {
			    NodeList innerResultList = returnList2.item(k).getChildNodes();
			    setTime(innerResultList.item(k).getTextContent());
		 }
			
	 }

	public static String getCheck() {
		return check;
	}

	public static void setCheck(String check) {
		CheckSession.check = check;
	}
	
	public static String getTime() {
		return time;
	}

	public static void setTime(String time) {
		CheckSession.time = time;
	}



}
