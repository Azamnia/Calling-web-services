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

public class UpdateSubscriptionProfileServices {
	
	private static String result;

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
		 SOAPElement soapElement = soapBody.addChildElement("updateSubscriptionProfileServices", "ws7");
		 
		 //make Message's body
		 SOAPElement element1 = soapElement.addChildElement("wsSessionId");
		 element1.addTextNode(Login.getSession());
		 
		 SOAPElement element2 = soapElement.addChildElement("subscriptionID");
		 element2.addTextNode(Numbers.getNumbers());
		 
		 SOAPElement element13 = soapElement.addChildElement("services");
		 
		 
		 SOAPElement element3 = element13.addChildElement("active");
		 element3.addTextNode("false");
		 
		 SOAPElement element4 = element13.addChildElement("extBool");
		 element4.addTextNode("false");
		 
		 SOAPElement element5 = element13.addChildElement("extNumber");
		 element5.addTextNode("-1");
		 
		 SOAPElement element12 = element13.addChildElement("extString");
		 element12.addTextNode("?");
		 
		 SOAPElement element6 = element13.addChildElement("userModifiable");
		 element6.addTextNode("true");
		 
		 SOAPElement element7 = element13.addChildElement("userVisible");
		 element7.addTextNode("false");
		 
		 SOAPElement element8 = element13.addChildElement("featureName");
		 element8.addTextNode("Outgoing SMSs Enabled");
		 
		 SOAPElement element9 = element13.addChildElement("featureID");
		 element9.addTextNode("ShortMessageMO-PP");
		 
		 SOAPElement element10 = element13.addChildElement("featureModel");
		 element10.addTextNode("SimpleService.xsd");
		 
		 SOAPElement element11 = element13.addChildElement("featureOnlyAdmin");
		 element11.addTextNode("false");
		 
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
			
	 }

	public static String getResult() {
		return result;
	}

	public static void setResult(String result) {
		UpdateSubscriptionProfileServices.result = result;
	}
	 
	 
	 
}
