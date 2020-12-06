package com.thoughtworks.springbootemployee.Intergration;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
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
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    void tearDown() {
        companyRepository.deleteAll();
    }

    @Test
    public void should_return_all_companies_when_get_all_given_all_companies() throws Exception {
        //given
        Company company = new Company("alibaba");
        companyRepository.save(company);
        //when
        //then
        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].companyName").value("alibaba"))
                .andExpect(jsonPath("$[0].employeesNumber").value(0))
                .andExpect(jsonPath("$[0].employees",hasSize(0)));
    }

    @Test
    public void should_return_specific_company_when_get_by_id_given_valid_company_id() throws Exception {
        //given
        Company company = new Company("alibaba");
        companyRepository.save(company);
        //when
        //then
        mockMvc.perform(get("/companies/" + company.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.companyName").value("alibaba"))
                .andExpect(jsonPath("$.employeesNumber").value(0))
                .andExpect(jsonPath("$.employees", hasSize(0)));

    }
//
//    @Test
//    public void should_return_bad_request_when_get_by_id_given_wrong_format_company_id() throws Exception {
//        //given
//        List<String> employeeIds = Arrays.asList("1", "2");
//        Company company = new Company("alibaba", 2, employeeIds);
//        companyRepository.save(company);
//        //when
//        //then
//        mockMvc.perform(get("/companies/" + "9999999"))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void should_return_not_found_when_get_by_id_given_invalid_company_id() throws Exception {
//        //given
//        List<String> employeeIds = Arrays.asList("1", "2");
//        Company company = new Company("alibaba", 2, employeeIds);
//        companyRepository.save(company);
//        //when
//        //then
//        mockMvc.perform(get("/companies/" + "5fc9a83a8a77666d0c8ea0e1"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void should_return_2_companies_when_get_by_paging_given_3_companies_and_page_number_is_0_and_pagesize_is_2() throws Exception {
//        //given
//        List<String> employeeIds = Arrays.asList("1", "2");
//        Company company1 = new Company("alibaba", 2, employeeIds);
//        Company company2 = new Company("blibaba", 2, employeeIds);
//        Company company3 = new Company("clibaba", 2, employeeIds);
//        companyRepository.save(company1);
//        companyRepository.save(company2);
//        companyRepository.save(company3);
//        //when
//        //then
//        mockMvc.perform(get("/companies").param("page", String.valueOf(0)).param("pageSize", String.valueOf(2)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").isString())
//                .andExpect(jsonPath("$[0].companyName").value("alibaba"))
//                .andExpect(jsonPath("$[0].employeesNumber").value(2))
//                .andExpect(jsonPath("$[0].employeeIds[0]").value("1"))
//                .andExpect(jsonPath("$[0].employeeIds[1]").value("2"))
//                .andExpect(jsonPath("$[1].id").isString())
//                .andExpect(jsonPath("$[1].companyName").value("blibaba"))
//                .andExpect(jsonPath("$[1].employeesNumber").value(2))
//                .andExpect(jsonPath("$[1].employeeIds[0]").value("1"))
//                .andExpect(jsonPath("$[1].employeeIds[1]").value("2"));
//    }
//
//    @Test
//    public void should_return_all_employees_of_a_specific_company_when_get_employees_by_company_id_given_a_valid_company_id() throws Exception {
//        //given
//        Employee employee1 = new Employee("David", 18, "male", 10000);
//        Employee employee2 = new Employee("Jackie", 18, "female", 10000);
//        employeeRepository.save(employee1);
//        employeeRepository.save(employee2);
//        List<String> employeeIds = Arrays.asList(employee1.getId(), employee2.getId());
//        Company company = new Company("alibaba", 2, employeeIds);
//        companyRepository.save(company);
//        //when
//        //then
//        mockMvc.perform(get("/companies/" + company.getId() + "/employees"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").isString())
//                .andExpect(jsonPath("$[0].name").value("David"))
//                .andExpect(jsonPath("$[0].age").value(18))
//                .andExpect(jsonPath("$[0].gender").value("male"))
//                .andExpect(jsonPath("$[0].salary").value(10000))
//                .andExpect(jsonPath("$[1].id").isString())
//                .andExpect(jsonPath("$[1].name").value("Jackie"))
//                .andExpect(jsonPath("$[1].age").value(18))
//                .andExpect(jsonPath("$[1].gender").value("female"))
//                .andExpect(jsonPath("$[1].salary").value(10000));
//    }
//
//    @Test
//    public void should_return_created_company_when_create_given_company() throws Exception {
//        //given
//        String companyAsJson = "{\n" +
//                "    \"companyName\": \"alibaba\",\n" +
//                "    \"employeesNumber\": 2,\n" +
//                "    \"employeeIds\": [\"1\",\"2\"]\n" +
//                "}";
//
//        int originalSize = companyRepository.findAll().size();
//
//        //when
//        //then
//        mockMvc.perform(post("/companies")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(companyAsJson))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").isString())
//                .andExpect(jsonPath("$.companyName").value("alibaba"))
//                .andExpect(jsonPath("$.employeesNumber").value(2))
//                .andExpect(jsonPath("$.employeeIds[0]").value("1"))
//                .andExpect(jsonPath("$.employeeIds[1]").value("2"));
//
//        int newSize = companyRepository.findAll().size();
//
//        assertEquals(originalSize + 1, newSize);
//    }
//
//    @Test
//    public void should_delete_specific_company_when_delete_given_valid_company_id() throws Exception {
//        //given
//        List<String> employeeIds = Arrays.asList("1", "2");
//        Company company = new Company("alibaba", 2, employeeIds);
//        companyRepository.save(company);
//
//        //when
//        //then
//        mockMvc.perform(delete("/companies/" + company.getId()))
//                .andExpect(status().isOk());
//
//        Optional<Company> actual = companyRepository.findById(company.getId());
//        assertEquals(Optional.empty(), actual);
//    }
//
//    @Test
//    public void should_return_bad_request_when_delete_given_wrong_format_company_id() throws Exception {
//        //given
//        List<String> employeeIds = Arrays.asList("1", "2");
//        Company company = new Company("alibaba", 2, employeeIds);
//        companyRepository.save(company);
//        //when
//        //then
//        mockMvc.perform(delete("/companies/" + "9999999"))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void should_return_not_found_when_delete_given_invalid_company_id() throws Exception {
//        //given
//        List<String> employeeIds = Arrays.asList("1", "2");
//        Company company = new Company("alibaba", 2, employeeIds);
//        companyRepository.save(company);
//        //when
//        //then
//        mockMvc.perform(delete("/companies/" + "5fc9a83a8a77666d0c8ea0e1"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void should_return_updated_company_when_update_given_company() throws Exception {
//        //given
//        List<String> employeeIds = Arrays.asList("1", "2");
//        Company company = new Company("alibaba", 2, employeeIds);
//        companyRepository.save(company);
//        String updateCompanyAsJson = "{\n" +
//                "    \"companyName\": \"Tesla\",\n" +
//                "    \"employeesNumber\": 2,\n" +
//                "    \"employeeIds\": [\"1\",\"100\"]\n" +
//                "}";
//
//        //when
//        //then
//        mockMvc.perform(put("/companies/" + company.getId())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(updateCompanyAsJson))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").isString())
//                .andExpect(jsonPath("$.companyName").value("Tesla"))
//                .andExpect(jsonPath("$.employeesNumber").value(2))
//                .andExpect(jsonPath("$.employeeIds[0]").value("1"))
//                .andExpect(jsonPath("$.employeeIds[1]").value("100"));
//    }
//
//    @Test
//    public void should_return_bad_request_when_update_given_wrong_format_company_id() throws Exception {
//        //given
//        List<String> employeeIds = Arrays.asList("1", "2");
//        Company company = new Company("alibaba", 2, employeeIds);
//        companyRepository.save(company);
//        String updateCompanyAsJson = "{\n" +
//                "    \"companyName\": \"Tesla\",\n" +
//                "    \"employeesNumber\": 2,\n" +
//                "    \"employeesId\": [\"1\",\"100\"]\n" +
//                "}";
//        //when
//        //then
//        mockMvc.perform(put("/companies/" + "9999999")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(updateCompanyAsJson))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void should_return_not_found_when_update_given_invalid_company_id() throws Exception {
//        //given
//        List<String> employeeIds = Arrays.asList("1", "2");
//        Company company = new Company("alibaba", 2, employeeIds);
//        companyRepository.save(company);
//        String updateCompanyAsJson = "{\n" +
//                "    \"companyName\": \"Tesla\",\n" +
//                "    \"employeesNumber\": 2,\n" +
//                "    \"employeesId\": [\"1\",\"100\"]\n" +
//                "}";
//        //when
//        //then
//        mockMvc.perform(put("/companies/" + "5fc9a83a8a77666d0c8ea0e1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(updateCompanyAsJson))
//                .andExpect(status().isNotFound());
//    }
}
