package hello.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import hello.file.Csv;
import hello.send.Sending;
import hello.storage.StorageService;

@Controller
public class SpringMVCController 
{
	
	private final StorageService storageService;
	private static String language;
	private static String method;
	
    @Autowired
    public SpringMVCController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/releaseMsisdn")
    public String releaseMsisdn() throws IOException {
        
    	setMethod("msisdn");
    	Sending.msisdn();    	
    	return "redirect:/";
    	
    }
    
    @PostMapping("/updateLanguage")
    public String updateLanguage(HttpServletRequest request) throws IOException {
        
    	setMethod("updateLanguage");
    	setLanguage(request.getParameter("language"));
    	Sending.updateLanguage();    	
    	return "redirect:/";
    	
    }
    
    @PostMapping("/updateSubscriptionProfileServices")
    public String updateSubscriptionProfileServices() throws IOException {
        
    	setMethod("updateSubscriptionProfileServices");
    	Sending.updateSubscriptionProfileServices();    	
    	return "redirect:/";
    	
    }
    
    @PostMapping("/cancelCallOrder")
    public String cancelCallOrder() throws IOException {
        
    	setMethod("cancelCallOrder");
    	Sending.cancelCallOrder();    	
    	return "redirect:/";
    	
    }

	public static String getLanguage() {
		return language;
	}

	public static void setLanguage(String language) {
		SpringMVCController.language = language;
	}
    
	public static String getMethod() {
		return method;
	}

	public static void setMethod(String method) {
		SpringMVCController.method = method;
	}

}