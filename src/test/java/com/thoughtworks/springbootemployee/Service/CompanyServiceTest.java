package com.thoughtworks.springbootemployee.Service;

import com.thoughtworks.springbootemployee.Exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @InjectMocks
    private CompanyService companyService;
    @Mock
    CompanyRepository companyRepository;
    @Mock
    EmployeeRepository employeeRepository;

    @Test
    public void should_return_all_companies_when_get_all_given_all_companies() {
        //given
        final List<Company> expected = Arrays.asList(
                new Company("alibaba")
        );
        when(companyRepository.findAll()).thenReturn(expected);

        //when
        final List<Company> companies = companyService.getAll();

        //then
        assertEquals(expected, companies);
    }

    @Test
    public void should_return_specific_company_when_get_by_id_given_valid_company_id() {
        //given
        final Company expected = new Company("alibaba");
        when(companyRepository.findById("1")).thenReturn(java.util.Optional.of(expected));

        //when
        final Company companies = companyService.getById("1");

        //then
        assertEquals(expected, companies);
    }

    @Test
    public void should_throw_company_not_found_exception_when_get_by_id_given_invalid_company_id() {
        //given
        when(companyRepository.findById(any())).thenReturn(Optional.empty());

        //when
        CompanyNotFoundException companyNotFoundException = assertThrows(CompanyNotFoundException.class, () -> {
            companyService.getById("99999");
        });

        //then
        assertEquals("Company Not Found!", companyNotFoundException.getMessage());
    }

    @Test
    public void should_return_employees_when_get_employees_by_company_given_valid_company_id() {
        //given
        final Company expected = new Company("alibaba");
        final List<Employee> expectedEmployees = Arrays.asList(
                new Employee("david", 22, "male", 11111, "123"),
                new Employee("peter", 22, "male", 11111, "123")
        );
        when(companyRepository.findById("1")).thenReturn(java.util.Optional.of(expected));
        when(employeeRepository.findAllByCompanyId(expected.getId())).thenReturn(expectedEmployees);

        //when
        final List<Employee> employees = companyService.getEmployeesByCompanyId("1");

        //then
        assertEquals(expectedEmployees, employees);
    }

    @Test
    public void should_throw_company_not_found_exception_when_employees_by_company_id_given_invalid_company_id() {
        //given
        when(companyRepository.findById(any())).thenReturn(Optional.empty());

        //when
        CompanyNotFoundException companyNotFoundException = assertThrows(CompanyNotFoundException.class, () -> {
            companyService.getEmployeesByCompanyId("99999");
        });

        //then
        assertEquals("Company Not Found!", companyNotFoundException.getMessage());
    }

    @Test
    public void should_return_2_companies_when_get_paginated_all_given_3_companies_and_page_is_0_and_page_size_is_2() {
        //given
        final List<Company> expected = Arrays.asList(
                new Company("alibaba"),
                new Company("blibaba"),
                new Company("clibaba")
        );
        when(companyRepository.findAll()).thenReturn(expected);

        //when
        final List<Company> companies = companyService.getPaginatedAll(0, 2);

        //then
        assertEquals(2, companies.size());
    }

    @Test
    public void should_return_created_company_when_create_given_no_company_in_the_database() {
        //given
        final Company expected = new Company("alibaba");
        when(companyRepository.save(expected)).thenReturn(expected);

        //when
        companyService.create(expected);
        ArgumentCaptor<Company> companyArgumentCaptor = ArgumentCaptor.forClass(Company.class);
        verify(companyRepository, times(1)).save(companyArgumentCaptor.capture());

        //then
        final Company company = companyArgumentCaptor.getValue();
        assertEquals(expected, company);
    }

    @Test
    public void should_return_updated_company_when_update_given_valid_company_id() {
        //given
        final Company originCompany = new Company("alibaba");
        final Company updatedCompany = new Company("blibaba");
        when(companyRepository.findById("1")).thenReturn(java.util.Optional.of(originCompany));
        when(companyRepository.save(updatedCompany)).thenReturn(updatedCompany);

        //when
        final Company company = companyService.update("1", updatedCompany);

        //then
        assertEquals(updatedCompany, company);
    }

    @Test
    public void should_throw_company_not_found_exception_when_update_given_invalid_company_id() {
        //given
        final Company company = new Company("alibaba");
        when(companyRepository.findById(any())).thenReturn(Optional.empty());

        //when
        CompanyNotFoundException companyNotFoundException = assertThrows(CompanyNotFoundException.class, () -> {
            companyService.update("99999", company);
        });

        //then
        assertEquals("Company Not Found!", companyNotFoundException.getMessage());
    }

    @Test
    public void should_delete_company_when_delete_given_valid_company_id() {
        //given
        final Company expected = new Company("alibaba");
        when(companyRepository.findById("1")).thenReturn(java.util.Optional.of(expected));

        //when
        companyService.delete("1");

        //then
        verify(companyRepository, times(1)).deleteById(expected.getId());
    }

    @Test
    public void should_throw_company_not_found_exception_when_delete_given_invalid_company_id() {
        //given
        when(companyRepository.findById(any())).thenReturn(Optional.empty());

        //when
        CompanyNotFoundException companyNotFoundException = assertThrows(CompanyNotFoundException.class, () -> {
            companyService.delete("99999");
        });

        //then
        assertEquals("Company Not Found!", companyNotFoundException.getMessage());
    }
}
