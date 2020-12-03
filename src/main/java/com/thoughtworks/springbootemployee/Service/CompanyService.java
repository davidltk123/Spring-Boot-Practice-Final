package com.thoughtworks.springbootemployee.Service;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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
        page = page - 1;
        return companyRepository.getAll().stream().skip(page * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public Company create(Company company) {
        return companyRepository.create(company);
    }

    public Company update(Integer id, Company companyUpdate) {
        return companyRepository.update(id, companyUpdate);
    }

    public void delete(Integer id) {
        companyRepository.delete(id);
    }
}
