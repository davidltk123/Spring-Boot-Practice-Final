package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Model.Company;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    List<Company> companies = new ArrayList<>();

    @GetMapping
    public List<Company> getAll(){
        return companies;
    }

    @PostMapping
    public Company create(@RequestBody Company company) {
        companies.add(company);
        return company;
    }
}
