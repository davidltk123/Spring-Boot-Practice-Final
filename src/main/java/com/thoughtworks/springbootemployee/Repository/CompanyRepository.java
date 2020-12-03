package com.thoughtworks.springbootemployee.Repository;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyRepository {
    private List<Company> companies = new ArrayList<>();

    public List<Company> getAll() {
        return companies;
    }

    public Company getById(Integer id) {
        return companies.stream().filter(company -> id.equals(company.getId())).findFirst().orElse(null);
    }
//
//    public List<Employee> getEmployeesByCompanyId(String id) {
//        return companies.stream().filter(company -> id.equals(company.getId())).findFirst().orElse(null).getEmployeesId();
//    }

    public Company create(Company company) {
        companies.add(company);
        return company;
    }

    public Company update(Integer id, Company companyUpdate) {
        companies.stream().filter(company -> id.equals(company.getId())).findFirst().ifPresent(company -> {
            companies.remove(company);
            companies.add(companyUpdate);
        });
        return companyUpdate;
    }

    public void delete(Integer id) {
        companies.stream().filter(company -> id.equals(company.getId())).findFirst().ifPresent(company -> {
            companies.remove(company);
        });
    }
}
