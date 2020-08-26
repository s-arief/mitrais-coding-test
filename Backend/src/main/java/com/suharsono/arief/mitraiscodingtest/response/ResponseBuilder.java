package com.suharsono.arief.mitraiscodingtest.response;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {
    
    // Error after calling controllers
    public static ResponseEntity buildResponseBadRequest() {
        JSONObject retVal = new JSONObject();
        retVal.put("error", true);
        retVal.put("code", ResponseMessage.GenericBadRequest.code);
        retVal.put("message", ResponseMessage.GenericBadRequest.message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(retVal.toString());
    }
    
    public static ResponseEntity buildResponseBadRequest(String message) {
        JSONObject retVal = new JSONObject();
        retVal.put("error", true);
        retVal.put("code", ResponseMessage.GenericBadRequest.code);
        retVal.put("message", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(retVal.toString());
    }
    
    public static ResponseEntity buildResponseBadRequest(ResponseMessage rm) {
        JSONObject retVal = new JSONObject();
        retVal.put("error", true);
        retVal.put("code", rm.code);
        retVal.put("message", rm.message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(retVal.toString());
    }
    
    public static ResponseEntity buildResponseServerError() {
        JSONObject retVal = new JSONObject();
        retVal.put("error", true);
        retVal.put("code", ResponseMessage.GenericServerError.code);
        retVal.put("message", ResponseMessage.GenericServerError.message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(retVal.toString());
    }
    
    public static ResponseEntity buildResponseError(ResponseMessage rm) {
        JSONObject retVal = new JSONObject();
        retVal.put("error", true);
        retVal.put("code", rm.code);
        retVal.put("message", rm.message);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(retVal.toString());
    }
    
    public static ResponseEntity buildResponseError(ResponseException ex) {
        JSONObject retVal = new JSONObject();
        retVal.put("error", true);
        retVal.put("code", ex.getResponseMessage().code);
        retVal.put("message", ex.getResponseMessage().message);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(retVal.toString());
    }
    
    // Success
    public static ResponseEntity buildResponseSuccess(ResponseMessage rm) {
        JSONObject retVal = new JSONObject();
        retVal.put("error", false);
        retVal.put("code", rm.code);
        retVal.put("message", rm.message);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(retVal.toString());
    }
    
}
