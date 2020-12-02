package com.thoughtworks.springbootemployee.Service;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    public List<Company> getAll() {
        return companyRepository.getAll();
    }

    public Company getById(Integer id) {
        return companyRepository.getById(id);
    }

    public List<Employee> getEmployeesByCompanyId(Integer id) {
        return companyRepository.getEmployeesByCompanyId(id);
    }

    public List<Company> getPaginatedAll(Integer page, Integer pageSize) {
        return null;
    }
}
