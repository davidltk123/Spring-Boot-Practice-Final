package com.thoughtworks.springbootemployee.Repository;

import com.thoughtworks.springbootemployee.Model.Company;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyRepository {
    private List<Company> companies = new ArrayList<>();

    public List<Company> getAll() {
        return companies;
    }
}
