package com.thoughtworks.springbootemployee.Service;

import com.thoughtworks.springbootemployee.Exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company getById(String id) {
        return companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException("Company Not Found!"));
    }

    public List<Employee> getEmployeesByCompanyId(String id) {
        Company company = getById(id);
        return employeeRepository.findAllByCompanyId(company.getId());
    }

    public List<Company> getPaginatedAll(Integer page, Integer pageSize) {
        return companyRepository.findAll().stream().skip(page * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public Company create(Company company) {
        return companyRepository.save(company);
    }

    public Company update(String id, Company companyUpdate) {
        Company company = getById(id);
        companyUpdate.setId(company.getId());
        return companyRepository.save(companyUpdate);
    }

    public void delete(String id) {
        Company company = getById(id);
        companyRepository.deleteById(company.getId());
    }
}
