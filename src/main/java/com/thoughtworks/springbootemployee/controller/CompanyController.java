package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.DTO.CompanyRequest;
import com.thoughtworks.springbootemployee.DTO.CompanyResponse;
import com.thoughtworks.springbootemployee.DTO.EmployeeResponse;
import com.thoughtworks.springbootemployee.Mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.Mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    List<Company> companies = new ArrayList<>();

    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping
    public List<CompanyResponse> getAll() {
        return companyService.getAll().stream().map(this::getCompanyResponse).collect(Collectors.toList());
    }

    private CompanyResponse getCompanyResponse(Company company){
        List<Employee> employees = companyService.getEmployeesByCompanyId(company.getId());
        return companyMapper.toResponse(company,employees.stream().map(employeeMapper::toResponse).collect(Collectors.toList()));
    }

    @GetMapping("/{companyId}")
    public CompanyResponse getById(@PathVariable String companyId) {
        return getCompanyResponse(companyService.getById(companyId));
    }

    @GetMapping("/{companyId}/employees")
    public List<EmployeeResponse> getEmployeesByCompanyId(@PathVariable String companyId) {
        return companyService.getEmployeesByCompanyId(companyId).stream().map(employeeMapper::toResponse).collect(Collectors.toList());
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<CompanyResponse> getPaginatedAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        return companyService.getPaginatedAll(page, pageSize).stream().map(this::getCompanyResponse).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse create(@RequestBody CompanyRequest companyRequest) {
        return getCompanyResponse(companyService.create(companyMapper.toEntity(companyRequest)));
    }

    @PutMapping("/{companyId}")
    public CompanyResponse update(@PathVariable String companyId, @RequestBody CompanyRequest companyRequest) {
        return getCompanyResponse(companyService.update(companyId, companyMapper.toEntity(companyRequest)));
    }

    @DeleteMapping("/{companyId}")
    public void delete(@PathVariable String companyId) {
        companyService.delete(companyId);
    }
}
