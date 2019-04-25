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

import hello.file.Csv;

public class AddPackagesSimple {

	//Creating the SOAPMessage body
		
		private static String result;
		private static String ticket;
		private static String saleCode;

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
			 SOAPElement soapElement = soapBody.addChildElement("addPackagesSimple", "ws7");
			 
			 //make Message's body
			 SOAPElement element1 = soapElement.addChildElement("wsSessionId");
			 element1.addTextNode(Login.getSession());
			 
			 SOAPElement element2 = soapElement.addChildElement("msisdn");
			 element2.addTextNode(Csv.getMsisdn());
			 
			 SOAPElement element3 = soapElement.addChildElement("addPackageArray");
			 element3.addTextNode(Csv.getPKGOutput());
			 
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
				    setResult(innerResultList.item(k).getTextContent());
			 }
				if(!getResult().equals("OK")) {
					setSaleCode(null);
					setTicket(null);
				}
				
				// processing nodes to get SessionId
				NodeList returnList2 = body.getElementsByTagName("ticket");
				for (int k = 0; k < returnList2.getLength(); k++) {
				    NodeList innerResultList = returnList2.item(k).getChildNodes();
				    setTicket(innerResultList.item(k).getTextContent());
			 }
				
				// processing nodes to get SessionId
				NodeList returnList3 = body.getElementsByTagName("saleCode");
				for (int k = 0; k < returnList3.getLength(); k++) {
				    NodeList innerResultList = returnList3.item(k).getChildNodes();
				    setSaleCode(innerResultList.item(k).getTextContent());
			 }
				
		 }

		public static String getResult() {
			return result;
		}

		public static void setResult(String result) {
			AddPackagesSimple.result = result;
		}

		public static String getTicket() {
			return ticket;
		}

		public static void setTicket(String ticket) {
			AddPackagesSimple.ticket = ticket;
		}

		public static String getSaleCode() {
			return saleCode;
		}

		public static void setSaleCode(String saleCode) {
			AddPackagesSimple.saleCode = saleCode;
		}
		 
		 
	
}
