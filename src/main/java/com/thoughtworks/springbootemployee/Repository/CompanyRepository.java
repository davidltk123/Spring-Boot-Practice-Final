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

    public List<Employee> getEmployeesByCompanyId(Integer id) {
        return companies.stream().filter(company -> id.equals(company.getId())).findFirst().orElse(null).getEmployees();
    }

    public List<Company> getPaginatedAll(Integer page, Integer pageSize) {
        return null;
    }
}
