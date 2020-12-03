package com.thoughtworks.springbootemployee.Intergration;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository1;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository1;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntergrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private CompanyRepository1 companyRepository;

    @AfterEach
    void tearDown() {
        companyRepository.deleteAll();
    }

    @Test
    public void should_return_all_companies_when_get_all_given_all_companies() throws Exception {
        //given
        List<String> employeeIds = Arrays.asList("1","2");
        Company company = new Company("alibaba", 2, employeeIds);
        companyRepository.save(company);
        //when
        //then
        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].companyName").value("alibaba"))
                .andExpect(jsonPath("$[0].employeesNumber").value(2))
                .andExpect(jsonPath("$[0].employeesId[0]").value("1"))
                .andExpect(jsonPath("$[0].employeesId[1]").value("2"));
    }

    @Test
    public void should_return_specific_company_when_get_by_id_given_valid_company_id() throws Exception {
        //given
        List<String> employeeIds = Arrays.asList("1","2");
        Company company = new Company("alibaba", 2, employeeIds);
        companyRepository.save(company);
        //when
        //then
        mockMvc.perform(get("/companies/" + company.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.companyName").value("alibaba"))
                .andExpect(jsonPath("$.employeesNumber").value(2))
                .andExpect(jsonPath("$.employeesId[0]").value("1"))
                .andExpect(jsonPath("$.employeesId[1]").value("2"));

    }
}
