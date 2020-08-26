package com.suharsono.arief.mitraiscodingtest.service;

import com.suharsono.arief.mitraiscodingtest.model.Registration;
import com.suharsono.arief.mitraiscodingtest.repository.RegistrationRepository;
import com.suharsono.arief.mitraiscodingtest.response.ResponseException;
import com.suharsono.arief.mitraiscodingtest.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    
    @Autowired
    private RegistrationRepository registrationRepository;
    
    public void save(Registration registration) throws ResponseException {
        try {
            registrationRepository.save(registration);
        } catch (DataIntegrityViolationException ex) {
            String message = ex.getMessage();
            if (message.contains("constraint [mobile_number]")) {
                throw new ResponseException(ResponseMessage.RegistrationMobileNumberAlreadyExist);
            } else if (message.contains("constraint [email]")) {
                throw new ResponseException(ResponseMessage.RegistrationEmailAlreadyExist);
            } else {
                throw new ResponseException(ResponseMessage.GenericDBError);
            }
        }
    }
    
}
