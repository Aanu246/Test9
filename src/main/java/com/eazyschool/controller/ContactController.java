package com.eazyschool.controller;



//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;

import com.eazyschool.model.Contact;
import com.eazyschool.service.ContactService;

import lombok.extern.slf4j.Slf4j;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;


 
@Slf4j
@Controller
public class ContactController {
	
	//	private static Logger log = LoggerFactory.getLogger(ContactController.class);

	@RequestMapping("/contact")
	public String displayHomePage(Model model) {
		model.addAttribute("contact",new Contact());
		return "contact.html";
	}
	
	private final ContactService contactService;
	
	@Autowired
	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}
	
	//You can use @RequestMapping you will declare method=POST or @PostMapping, without POST
	//@RequestParam(name="named" String named)
	//@RequestMapping(value= "/saveMsg",method= POST)
	//public ModelAndView saveMessage(@RequestParam String name, @RequestParam String mobileNum, @RequestParam String email,
		//	@RequestParam String subject, @RequestParam String message) {
		
		//log.info("Name : "+name);
		//log.info("MobileNumber : "+mobileNum);
	//	log.info("email : "+ email);
		//log.info("subject : "+subject);
	//	log.info("Message : "+message);
		//return new ModelAndView("redirect:/contact");
		
	//}
	
	@RequestMapping(value="/saveMsg",method=POST)
	public String saveMessages(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
		if(errors.hasErrors()) {
			log.error("Contact form validation failed due to : "+errors.toString());
			return"contact.html";
		}
		contactService.saveMessageDetails(contact);
		contactService.setCounter(contactService.getCounter()+1);
		log.info("Number of times the Contact form is submitted : "+contactService.getCounter());
		
		return "redirect:/contact";
	}
	
	
	
	// before validation comes in
	//@RequestMapping(value="/saveMsg",method=POST)
	//public ModelAndView saveMessages(Contact contact) {
		//contactService.saveMessageDetails(contact);
		//return new ModelAndView("redirect:/contact");
	//}
	

}
