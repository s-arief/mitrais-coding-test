package com.suharsono.arief.mitraiscodingtest.response;

public enum ResponseMessage {
    
    // Generic Error Message
    GenericBadRequest(1101, "Parameter missing / invalid"),
    GenericServerError(1102, "Oops, server error, please call the administrator"),
    GenericDBError(1103, "Database error"),
    
    // Registration Success Message
    RegistrationSuccess(1000, "Registration Success"),
    
    // Registration Error Message
    RegistrationBirthdateInvalid(1101, "Invalid birthdate format"),
    RegistrationEmailAlreadyExist(1102, "E-mail already exist"),
    RegistrationMobileNumberAlreadyExist(1103, "Mobile number already exist"),
    RegistrationMobileNumberInvalid(1104, "Mobile number invalid"),
    
    // Default
    Default(9999, "Default message");
    
    public final int code;
    public final String message;
    
    ResponseMessage (int _code, String _message) {
        this.code = _code;
        this.message = _message;
    }
    
}
