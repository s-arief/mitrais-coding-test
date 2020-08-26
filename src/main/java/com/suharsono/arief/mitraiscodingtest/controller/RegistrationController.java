package com.suharsono.arief.mitraiscodingtest.controller;

import com.suharsono.arief.mitraiscodingtest.model.Registration;
import com.suharsono.arief.mitraiscodingtest.service.RegistrationService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    
    @Autowired
    private RegistrationService registrationService;
    
    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String register(@Valid @RequestBody RegisterRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("d-M-Y");
        
        Registration registration = new Registration();
        registration.setMobileNumber(request.getMobileNumber());
        registration.setFirstName(request.getFirstName());
        registration.setLastName(request.getLastName());
        try {
            registration.setBirthDate(sdf.parse(request.getBirthDate()));
        } catch (ParseException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        registration.setGender(request.getGender());
        registration.setEmail(request.getEmail());
        registrationService.save(registration);
        return "success";
    }
    
}
