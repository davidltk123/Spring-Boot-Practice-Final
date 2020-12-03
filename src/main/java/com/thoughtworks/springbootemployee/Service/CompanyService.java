package com.thoughtworks.springbootemployee.Service;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository1;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository1 companyRepository;
    @Autowired
    private EmployeeRepository1 employeeRepository;


    public CompanyService(CompanyRepository1 companyRepository, EmployeeRepository1 employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company getById(String id) {
        return companyRepository.findById(id).orElse(null);
    }

    public List<Employee> getEmployeesByCompanyId(String id) {
        Company company = getById(id);
        List<String> employeeIds = company.getEmployeesId();
        Iterable<Employee> employees = employeeRepository.findAllById(employeeIds);
        return StreamSupport
                .stream(employees.spliterator(), false)
                .collect(Collectors.toList());

    }

    public List<Company> getPaginatedAll(Integer page, Integer pageSize) {
        page = page - 1;
        return companyRepository.findAll().stream().skip(page * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public Company create(Company company) {
        return companyRepository.save(company);
    }

    public Company update(String id, Company companyUpdate) {
        if (companyRepository.findById(id).orElse(null) != null) {
            companyUpdate.setId(id);
            return companyRepository.save(companyUpdate);
        }
        return null;
    }

    public void delete(String id) {
        companyRepository.deleteById(id);
    }
}
