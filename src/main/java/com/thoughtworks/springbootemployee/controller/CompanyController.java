package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    List<Company> companies = new ArrayList<>();

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<Company> getAll(){
        return companyService.getAll();
    }

    @PostMapping
    public Company create(@RequestBody Company company) {
        companies.add(company);
        return company;
    }
}
