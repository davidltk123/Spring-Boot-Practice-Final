package com.thoughtworks.springbootemployee.Service;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company getById(String id) {
        try {
            return companyRepository.findById(id).orElse(null);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company Not Found!");
        }
    }

    public List<Employee> getEmployeesByCompanyId(String id) {
        Company company = getById(id);
        if (company != null) {
            List<String> employeeIds = company.getEmployeeIds();
            Iterable<Employee> employees = employeeRepository.findAllById(employeeIds);
            return StreamSupport
                    .stream(employees.spliterator(), false)
                    .collect(Collectors.toList());
        }
        return null;
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
        if (getById(id) != null) {
            companyUpdate.setId(id);
            return companyRepository.save(companyUpdate);
        }
        return null;
    }

    public void delete(String id) {
        if (getById(id) != null) {
            companyRepository.deleteById(id);
        }
    }
}
