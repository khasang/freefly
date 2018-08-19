package io.khasang.freefly.controller;

import io.khasang.freefly.dto.CarDTO;
import io.khasang.freefly.dto.EmployeeDTO;
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

    public static final String ROOT = "http://localhost:8080/employee";
    public static final String ADD = "/add";
    public static final String GET_BY_ID = "/get/{id}";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete/{id}";

    @Test
    public void complexTest(){
        Employee employee = addingEmployee();
        EmployeeDTO employeeDtoFromDB = gettingEmployeeDTO(employee.getId());
        assertEquals(employee.getName(), employeeDtoFromDB.getName());
        assertEquals(employee.getId(), employeeDtoFromDB.getId());
        List<Car> carList = employee.getCarList();
        List<CarDTO> carDTOList = employeeDtoFromDB.getCarDTOList();
        assertEquals(carList.size(), carDTOList.size());
        for (int i = 0; i < carList.size(); i++) {
            assertEquals(carList.get(i).getModel(), carDTOList.get(i).getModel());
            assertEquals(carList.get(i).getModel(), carDTOList.get(i).getModel());
        }
    }

    private EmployeeDTO gettingEmployeeDTO(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<EmployeeDTO> responseEntity = restTemplate.exchange(ROOT + GET_BY_ID, HttpMethod.GET, null, EmployeeDTO.class, id);
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        return responseEntity.getBody();
    }

    private Employee addingEmployee() {

        Employee expectedEmployee = prefillEmployee();

        //создание запроса на отправке запроса rest на создание
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Employee> httpEntity = new HttpEntity<>(expectedEmployee, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Employee> responseEntity = restTemplate.exchange(ROOT+ADD, HttpMethod.POST, httpEntity, Employee.class);
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        Employee actualEmpoyee = responseEntity.getBody();
        assertNotNull(actualEmpoyee.getId());
        assertEquals(expectedEmployee.getName(), actualEmpoyee.getName());

        return actualEmpoyee;
    }

    private Employee prefillEmployee() {
        Employee employee =new Employee();
        employee.setName("Karlson");
        List<Car> carList = new ArrayList<>();
        employee.setCarList(carList);
        Car car1 = new Car();
        car1.setModel("bmw");
        car1.setYear(LocalDate.of(2015, 11, 2));
        Car car2;
        car2 = new Car();
        car2.setYear(LocalDate.of(2018, 2, 5));
        carList.add(car1);
        carList.add(car2);

        return employee;
    }
}
