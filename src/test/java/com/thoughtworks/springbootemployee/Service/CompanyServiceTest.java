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

    @Test
    public void should_return_all_companies_when_get_all_given_all_companies() {
        //given
        final List<Employee> employees = Arrays.asList(
                new Employee(1,"david",22,"male",11111),
                new Employee(1,"peter",22,"male",11111)
        );
        final List<Company> expected = Arrays.asList(
                new Company("alibaba",2,employees)
        );
        when(companyRepository.getAll()).thenReturn(expected);

        //when
        final List<Company> companies = companyService.getAll();

        //then
        assertEquals(expected,companies);
    }
}
