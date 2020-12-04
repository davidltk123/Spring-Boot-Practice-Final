package com.thoughtworks.springbootemployee.Intergration;

import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntergrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    public void should_return_all_employees_when_get_all_given_all_employees() throws Exception {
        //given
        Employee employee = new Employee("David", 18, "male", 10000);
        employeeRepository.save(employee);
        //when
        //then
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].name").value("David"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(10000));

    }

    @Test
    public void should_return_specific_employee_when_get_by_id_given_valid_employee_id() throws Exception {
        //given
        Employee employee = new Employee("David", 18, "male", 10000);
        employeeRepository.save(employee);
        //when
        //then
        mockMvc.perform(get("/employees/" + employee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.name").value("David"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(10000));

    }

    @Test
    public void should_return_404_not_found_when_get_by_id_given_invalid_employee_id() throws Exception {
        //given
        Employee employee = new Employee("David", 18, "male", 10000);
        employeeRepository.save(employee);
        //when
        //then
        mockMvc.perform(get("/employees/" + "999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_return_all_male_employees_when_get_by_gender_given_gender_is_male() throws Exception {
        //given
        Employee employee1 = new Employee("David", 18, "male", 10000);
        Employee employee2 = new Employee("Jackie", 18, "female", 10000);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        //when
        //then
        mockMvc.perform(get("/employees").param("gender", "male"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].name").value("David"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(10000));

        List<Employee> filteredEmployees = employeeRepository.findAllByGender("male");
        assertEquals(1, filteredEmployees.size());
    }

    @Test
    public void should_return_2_employees_when_get_by_paging_given_3_employees_and_page_number_is_0_and_pagesize_is_2() throws Exception {
        //given
        Employee employee1 = new Employee("David", 18, "male", 10000);
        Employee employee2 = new Employee("Jackie", 18, "female", 10000);
        Employee employee3 = new Employee("Tom", 18, "male", 10000);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        //when
        //then
        mockMvc.perform(get("/employees").param("page", String.valueOf(0)).param("pageSize",String.valueOf(2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].name").value("David"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(10000))
                .andExpect(jsonPath("$[1].id").isString())
                .andExpect(jsonPath("$[1].name").value("Jackie"))
                .andExpect(jsonPath("$[1].age").value(18))
                .andExpect(jsonPath("$[1].gender").value("female"))
                .andExpect(jsonPath("$[1].salary").value(10000));
    }

    @Test
    public void should_return_created_employee_when_create_given_employee() throws Exception {
        //given
        String employeeAsJson = "{\n" +
                "        \"name\": \"tom\",\n" +
                "        \"age\": 22,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"salary\": 7000\n" +
                "}";

        //when
        //then
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeAsJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.name").value("tom"))
                .andExpect(jsonPath("$.age").value(22))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(7000));

        List<Employee> employees = employeeRepository.findAll();
        assertEquals(1, employees.size());
        assertEquals("tom", employees.get(0).getName());
        assertEquals(22, employees.get(0).getAge());
        assertEquals("male", employees.get(0).getGender());
        assertEquals(7000, employees.get(0).getSalary());
    }

    @Test
    public void should_delete_specific_employee_when_delete_given_valid_employee_id() throws Exception {
        //given
        Employee employee = new Employee("David", 18, "male", 10000);
        employeeRepository.save(employee);

        //when
        //then
        mockMvc.perform(delete("/employees/" + employee.getId()))
                .andExpect(status().isOk());

        Optional<Employee> employees = employeeRepository.findById(employee.getId());
        assertEquals(Optional.empty(), employees);
    }

    @Test
    public void should_return_404_not_found_when_delete_given_invalid_employee_id() throws Exception {
        //given
        Employee employee = new Employee("David", 18, "male", 10000);
        employeeRepository.save(employee);
        //when
        //then
        mockMvc.perform(delete("/employees/" + "999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_return_updated_employee_when_update_given_employee() throws Exception {
        //given
        Employee employee = new Employee("David", 18, "male", 10000);
        employeeRepository.save(employee);
        String updateEmployeeAsJson = "{\n" +
                "        \"name\": \"David\",\n" +
                "        \"age\": 18,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"salary\": 1000000\n" +
                "}";

        //when
        //then
        mockMvc.perform(put("/employees/" + employee.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateEmployeeAsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.name").value("David"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(1000000));

        List<Employee> employees = employeeRepository.findAll();
        assertEquals("David", employees.get(0).getName());
        assertEquals(18, employees.get(0).getAge());
        assertEquals("male", employees.get(0).getGender());
        assertEquals(1000000, employees.get(0).getSalary());
    }

    @Test
    public void should_return_404_not_found_when_update_given_invalid_employee_id() throws Exception {
        //given
        Employee employee = new Employee("David", 18, "male", 10000);
        employeeRepository.save(employee);
        String updateEmployeeAsJson = "{\n" +
                "        \"name\": \"David\",\n" +
                "        \"age\": 18,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"salary\": 1000000\n" +
                "}";
        //when
        //then
        mockMvc.perform(put("/employees/" + "999999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateEmployeeAsJson))
                .andExpect(status().isNotFound());
    }
}
