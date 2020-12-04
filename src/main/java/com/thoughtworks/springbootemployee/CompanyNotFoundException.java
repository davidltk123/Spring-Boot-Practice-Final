package com.thoughtworks.springbootemployee;

public class CompanyNotFoundException extends  RuntimeException{
    public CompanyNotFoundException(String message) {
        super(message);
    }
}
