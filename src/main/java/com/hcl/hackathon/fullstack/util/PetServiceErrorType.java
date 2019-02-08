package com.hcl.hackathon.fullstack.util;


public class PetServiceErrorType {

    private String errorMessage;

    public PetServiceErrorType(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
