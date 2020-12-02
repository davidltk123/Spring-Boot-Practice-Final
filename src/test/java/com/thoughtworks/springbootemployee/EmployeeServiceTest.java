package com.thoughtworks.springbootemployee;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.Service.EmployeeService;
import com.thoughtworks.springbootemployee.controller.Employee;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;

public class EmployeeServiceTest {
    @Test
    public void should_return_all_employees_when_get_all_given_all_employees() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        final List<Employee> expected = Arrays.asList(new Employee(1,"david",22,"male",11111));
        when(employeeRepository.getAll()).thenReturn(expected);

        //when
        final List<Employee> employees = employeeService.getAll();

        //then
        assertEquals(expected,employees);
    }

}
