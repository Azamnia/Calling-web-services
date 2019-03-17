package hello.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import hello.send.Sending;
import hello.storage.StorageService;

@Controller
public class SpringMVCController 
{
	
	private final StorageService storageService;

	
    @Autowired
    public SpringMVCController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/releaseMsisdn")
    public String releaseMsisdn() throws IOException {
        
    	Sending.msisdn();    	
    	return "redirect:/";
    	
    }
    
    @PostMapping("/updateLanguage")
    public String updateLanguage() throws IOException {
        
    	Sending.updateLanguage();    	
    	return "redirect:/";
    	
    }
    
    @PostMapping("/updateSubscriptionProfileServices")
    public String updateSubscriptionProfileServices() throws IOException {
        
    	Sending.updateSubscriptionProfileServices();    	
    	return "redirect:/";
    	
    }
    
    @PostMapping("/cancelCallOrder")
    public String cancelCallOrder() throws IOException {
        
    	Sending.cancelCallOrder();    	
    	return "redirect:/";
    	
    }


}