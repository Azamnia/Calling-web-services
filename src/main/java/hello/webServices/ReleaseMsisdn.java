package hello.webServices;

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

import hello.file.Numbers;



public class ReleaseMsisdn {
	
	private static String session;
	private static String number;
	
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
		 SOAPElement soapElement = soapBody.addChildElement("releaseMsisdn", "ws7");
		 
		 //make Message's body
		 SOAPElement element1 = soapElement.addChildElement("wsSessionId");
		 element1.addTextNode(Login.getSession());
		 
		 SOAPElement element2 = soapElement.addChildElement("msisdn");
		 element2.addTextNode(Numbers.getNumbers());
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
			StreamResult result = new StreamResult(System.out);
			transformer.transform(sourceContent, result);
			
			SOAPBody body = soapResponse .getSOAPBody();
			
			NodeList returnList = body.getElementsByTagName("wsSessionId");
			for (int k = 0; k < returnList.getLength(); k++) {
			    NodeList innerResultList = returnList.item(k).getChildNodes();
			    setSession(innerResultList.item(k).getTextContent());
				}
			
			NodeList returnList2 = body.getElementsByTagName("callMsg");
			for (int k = 0; k < returnList2.getLength(); k++) {
			    NodeList innerResultList = returnList2.item(k).getChildNodes();
			    setNumber(innerResultList.item(k).getTextContent());
				}
			
		 }
	public static String getSession() {
		return session;
	}
	public static void setSession(String session) {
		ReleaseMsisdn.session = session;
	}
	
	public static String getNumber() {
		return number;
	}
	public static void setNumber(String number) {
		ReleaseMsisdn.number = number;
	}
	

}



