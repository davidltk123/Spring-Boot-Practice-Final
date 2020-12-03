package com.thoughtworks.springbootemployee.Service;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
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
    @Mock
    EmployeeRepository employeeRepository;

    @Test
    public void should_return_all_companies_when_get_all_given_all_companies() {
        //given
        final List<String> employeeIds = Arrays.asList("1","2");
        final List<Company> expected = Arrays.asList(
                new Company("alibaba", 2, employeeIds)
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
        final List<String> employeeIds = Arrays.asList("1","2");
        final Company expected = new Company("alibaba", 2, employeeIds);
        when(companyRepository.findById("1")).thenReturn(java.util.Optional.of(expected));

        //when
        final Company companies = companyService.getById("1");

        //then
        assertEquals(expected, companies);
    }

    //TODO: investigate later
    @Test
    public void should_return_employees_when_get_employees_by_company_given_valid_company_id() {
        //given
        //given
        final List<String> employeeIds = Arrays.asList("1","2");
        final Company expected = new Company("alibaba", 2, employeeIds);
        final List<Employee> expectedEmployees = Arrays.asList(
                new Employee("david", 22, "male", 11111),
                new Employee("peter", 22, "male", 11111)
        );
        when(companyRepository.findById("1")).thenReturn(java.util.Optional.of(expected));
        when(employeeRepository.findAllById(employeeIds)).thenReturn(expectedEmployees);

        //when
        final List<Employee> employees = companyService.getEmployeesByCompanyId("1");

        //then
        assertEquals(expectedEmployees, employees);
    }

    @Test
    public void should_return_2_companies_when_get_paginated_all_given_3_companies_and_page_is_1_and_page_size_is_2() {
        //given
        final List<String> employeeIds = Arrays.asList("1","2","3");
        final List<Company> expected = Arrays.asList(
                new Company("alibaba", 2, employeeIds),
                new Company("blibaba", 2, employeeIds),
                new Company("clibaba", 2, employeeIds)
        );
        when(companyRepository.findAll()).thenReturn(expected);

        //when
        final List<Company> companies = companyService.getPaginatedAll(1, 2);

        //then
        assertEquals(2, companies.size());
    }

    @Test
    public void should_return_created_company_when_create_given_no_company_in_the_database() {
        //given
        final List<String> employeeIds = Arrays.asList("1","2");
        final Company expected = new Company("alibaba", 2, employeeIds);
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
        final List<String> employeeIds = Arrays.asList("1","2");
        final Company originCompany = new Company("alibaba", 2, employeeIds);
        final Company updatedCompany = new Company("blibaba", 2, employeeIds);
        when(companyRepository.findById("1")).thenReturn(java.util.Optional.of(originCompany));
        when(companyRepository.save(updatedCompany)).thenReturn(updatedCompany);

        //when
        final Company company = companyService.update("1", updatedCompany);

        //then
        assertEquals(updatedCompany, company);
    }

    @Test
    public void should_delete_all_employees_of_a_specifc_company_when_delete_given_valid_company_id() {
        //given
        final List<String> employeeIds = Arrays.asList("1","2");
        final Company expected = new Company("alibaba", 2, employeeIds);
        when(companyRepository.findById("1")).thenReturn(java.util.Optional.of(expected));

        //when
        companyService.delete("1");

        //then
        verify(companyRepository, times(1)).deleteById("1");
    }
}
