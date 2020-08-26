package com.suharsono.arief.mitraiscodingtest.controller;

import com.suharsono.arief.mitraiscodingtest.model.Registration;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class RegisterRequest {
    
    @NotBlank(message = "mobileNumber needed")
    @Pattern(regexp="^([0-9]*)$", message = "mobileNumber invalid")
    private String mobileNumber;
    
    @NotBlank(message = "firstName needed")
    private String firstName;
    
    @NotBlank(message = "lastName needed")
    private String lastName;
    
    private String birthDate;
    
    private Registration.Gender gender;
    
    @NotBlank(message = "email needed")
    @Email(message = "email invalid")
    private String email;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Registration.Gender getGender() {
        return gender;
    }

    public void setGender(Registration.Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
