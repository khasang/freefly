package io.khasang.freefly.controller;

import io.khasang.freefly.entity.Car;
import io.khasang.freefly.entity.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeeControllerIntegrationTest {

    private final static String ROOT = "http://localhost:8080/employee";
    private final static String ADD = "/add";
    private final static String GET_BY_ID = "/get";

    @Test
    public void addEmployeeAndCheck() {
        Employee employee = createEmployee();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Employee> responseEntity = template.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Employee.class,
                employee.getId()
        );

        assertEquals("Ok", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody());
    }

    private Employee createEmployee() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Employee employee = prefillEmployee();

        HttpEntity<Employee> entity = new HttpEntity<>(employee, headers);
        RestTemplate template = new RestTemplate();
        Employee createdEmployee = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Employee.class
        ).getBody();

        assertNotNull(createdEmployee);
        assertEquals(employee.getName(), createdEmployee.getName());
        return createdEmployee;

    }

    private Employee prefillEmployee() {
        Employee employee = new Employee();
        employee.setName("Bob");
        employee.setDescription("Good");

        Car car1 = new Car();
        car1.setModel("VAZ");
        car1.setYear(LocalDate.of(2005, 12, 23));

        Car car2 = new Car();
        car2.setModel("GAZ");
        car2.setYear(LocalDate.of(1999, 1, 5));

        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);
        employee.setCarList(carList);

        return employee;
    }

}
