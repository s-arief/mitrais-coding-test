package com.suharsono.arief.mitraiscodingtest.repository;

import com.suharsono.arief.mitraiscodingtest.model.Registration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends CrudRepository<Registration, Integer> {
    
}
