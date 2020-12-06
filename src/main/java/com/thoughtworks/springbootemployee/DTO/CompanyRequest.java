package com.thoughtworks.springbootemployee.DTO;

public class CompanyRequest {
    public CompanyRequest() { }
    private String companyName;

    public CompanyRequest(String name) {
        this.companyName = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
