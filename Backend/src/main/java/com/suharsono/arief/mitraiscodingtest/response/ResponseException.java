package com.suharsono.arief.mitraiscodingtest.response;

public class ResponseException extends Exception {
    
    private final ResponseMessage responseMessage;
    
    public ResponseException() {
        this.responseMessage = ResponseMessage.Default;
    }
    
    public ResponseException(ResponseMessage _responseMessage) {
        this.responseMessage = _responseMessage;
    }
    
    public ResponseMessage getResponseMessage() {
        return this.responseMessage;
    }
    
}
