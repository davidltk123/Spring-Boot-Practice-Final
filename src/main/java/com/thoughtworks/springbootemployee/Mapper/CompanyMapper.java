package com.thoughtworks.springbootemployee.Mapper;

import com.thoughtworks.springbootemployee.DTO.CompanyRequest;
import com.thoughtworks.springbootemployee.DTO.CompanyResponse;
import com.thoughtworks.springbootemployee.DTO.EmployeeRequest;
import com.thoughtworks.springbootemployee.DTO.EmployeeResponse;
import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Service.CompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private EmployeeMapper employeeMapper;

    public Company toEntity(CompanyRequest companyRequest){
        Company company = new Company();
        BeanUtils.copyProperties(companyRequest, company);
        return company;
    }

    public CompanyResponse toResponse(Company company){
        List<Employee> employees = companyService.getEmployeesByCompanyId(company.getId());
        CompanyResponse companyResponse = new CompanyResponse();
        BeanUtils.copyProperties(company, companyResponse);
        companyResponse.setEmployees(employees);
        companyResponse.setEmployeesNumber(employees.size());
        return companyResponse;
    }
}
