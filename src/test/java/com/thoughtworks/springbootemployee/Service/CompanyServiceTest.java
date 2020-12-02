package com.thoughtworks.springbootemployee.Service;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @InjectMocks
    private CompanyService companyService;
    @Mock
    CompanyRepository companyRepository;

    @Test
    public void should_return_all_companies_when_get_all_given_all_companies() {
        //given
        final List<Employee> employees = Arrays.asList(
                new Employee(1, "david", 22, "male", 11111),
                new Employee(1, "peter", 22, "male", 11111)
        );
        final List<Company> expected = Arrays.asList(
                new Company(1, "alibaba", 2, employees)
        );
        when(companyRepository.getAll()).thenReturn(expected);

        //when
        final List<Company> companies = companyService.getAll();

        //then
        assertEquals(expected, companies);
    }

    @Test
    public void should_return_specific_company_when_get_by_id_given_valid_company_id() {
        //given
        final List<Employee> employees = Arrays.asList(
                new Employee(1, "david", 22, "male", 11111),
                new Employee(1, "peter", 22, "male", 11111)
        );
        final Company expected = new Company(1, "alibaba", 2, employees);
        when(companyRepository.getById(1)).thenReturn(expected);

        //when
        final Company companies = companyService.getById(1);

        //then
        assertEquals(expected, companies);
    }

    @Test
    public void should_return_employees_when_get_employees_by_company_given_valid_company_id() {
        //given
        final List<Employee> expectedEmployees = Arrays.asList(
                new Employee(1, "david", 22, "male", 11111),
                new Employee(1, "peter", 22, "male", 11111)
        );
        when(companyRepository.getEmployeesByCompanyId(1)).thenReturn(expectedEmployees);

        //when
        final List<Employee> employees = companyService.getEmployeesByCompanyId(1);

        //then
        assertEquals(expectedEmployees, employees);
    }

    @Test
    public void should_return_2_companies_when_get_paginated_all_given_3_companies_and_page_is_1_and_page_size_is_2() {
        //given
        final List<Employee> employees = Arrays.asList(
                new Employee(1, "david", 22, "male", 11111),
                new Employee(1, "peter", 22, "male", 11111)
        );
        final List<Company> expected = Arrays.asList(
                new Company(1, "alibaba", 2, employees),
                new Company(1, "blibaba", 2, employees),
                new Company(1, "clibaba", 2, employees)
        );
        when(companyRepository.getPaginatedAll(1, 2)).thenReturn(expected);

        //when
        final List<Company> companies = companyService.getPaginatedAll(1, 2);

        //then
        assertEquals(expected, companies);
    }

    @Test
    public void should_return_created_company_when_create_given_no_company_in_the_database() {
        //given
        final List<Employee> employees = Arrays.asList(
                new Employee(1, "david", 22, "male", 11111),
                new Employee(1, "peter", 22, "male", 11111)
        );
        final Company expected = new Company(1, "alibaba", 2, employees);
        when(companyRepository.create(expected)).thenReturn(expected);

        //when
        companyService.create(expected);
        ArgumentCaptor<Company> companyArgumentCaptor = ArgumentCaptor.forClass(Company.class);
        verify(companyRepository, times(1)).create(companyArgumentCaptor.capture());

        //then
        final Company company = companyArgumentCaptor.getValue();
        assertEquals(expected, company);
    }

    @Test
    public void should_return_updated_company_when_update_given_valid_company_id() {
        //given
        final List<Employee> employees = Arrays.asList(
                new Employee(1, "david", 22, "male", 11111),
                new Employee(1, "peter", 22, "male", 11111)
        );
        final Company expected = new Company(1, "alibaba", 2, employees);
        when(companyRepository.update(1, expected)).thenReturn(expected);

        //when
        final Company companies = companyService.update(1, expected);

        //then
        assertEquals(expected, companies);
    }
}
