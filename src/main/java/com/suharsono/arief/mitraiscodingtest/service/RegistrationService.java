package com.suharsono.arief.mitraiscodingtest.service;

import com.suharsono.arief.mitraiscodingtest.model.Registration;
import com.suharsono.arief.mitraiscodingtest.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    
    @Autowired
    private RegistrationRepository registrationRepository;
    
    public void save(Registration registration) {
        registrationRepository.save(registration);
    }
    
}
