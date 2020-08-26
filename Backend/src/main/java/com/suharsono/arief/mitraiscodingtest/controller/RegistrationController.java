package com.suharsono.arief.mitraiscodingtest.controller;

import com.suharsono.arief.mitraiscodingtest.model.Registration;
import com.suharsono.arief.mitraiscodingtest.response.ResponseBuilder;
import com.suharsono.arief.mitraiscodingtest.response.ResponseException;
import com.suharsono.arief.mitraiscodingtest.response.ResponseMessage;
import com.suharsono.arief.mitraiscodingtest.service.RegistrationService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class RegistrationController {
    
    @Autowired
    private RegistrationService registrationService;
    
    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@Valid @RequestBody RegisterRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        Registration registration = new Registration();
        String mobileNumber = request.getMobileNumber();
        if (mobileNumber.length() > 14 ||
                (mobileNumber.charAt(0) != '0') && !mobileNumber.substring(0, 2).equals("62")) {
            return ResponseBuilder.buildResponseError(ResponseMessage.RegistrationMobileNumberInvalid);
        }
        
        registration.setMobileNumber(request.getMobileNumber());
        registration.setFirstName(request.getFirstName());
        registration.setLastName(request.getLastName());
        
        if (request.getBirthDate() != null) {
            try {
                registration.setBirthDate(sdf.parse(request.getBirthDate()));
            } catch (ParseException ex) {
                return ResponseBuilder.buildResponseError(ResponseMessage.RegistrationBirthdateInvalid);
            }
        }
        
        registration.setGender(request.getGender());
        registration.setEmail(request.getEmail());
        try {
            registrationService.save(registration);
        } catch (ResponseException ex) {
            return ResponseBuilder.buildResponseError(ex);
        }
        return ResponseBuilder.buildResponseSuccess(ResponseMessage.RegistrationSuccess);
    }
    
}
