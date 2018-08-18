package io.khasang.freefly.dto;

import io.khasang.freefly.entity.Car;
import io.khasang.freefly.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeDTO {
    private Long id;
    private String name;
    private String description;
    private List<CarDTO> carDTOList = new ArrayList<>();

    public List<EmployeeDTO> getEmployeeDTOList(List<Employee> employeeList) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();

        for (Employee employee: employeeList) {
            List<CarDTO> carDTOList = new ArrayList<>();
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setName(employee.getName());
            employeeDTO.setDescription(employee.getDescription());

            getCarDTOFromCar(employee, carDTOList);

            employeeDTO.setCarDTOList(carDTOList);
            employeeDTOList.add(employeeDTO);
        }

        return  employeeDTOList;
    }

    public EmployeeDTO getEmployeeDTO(Employee employee) {
            List<CarDTO> carDTOList = new ArrayList<>();
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setName(employee.getName());
            employeeDTO.setDescription(employee.getDescription());

            getCarDTOFromCar(employee, carDTOList);

            employeeDTO.setCarDTOList(carDTOList);

            return employeeDTO;
        }

    private void getCarDTOFromCar(Employee employee, List<CarDTO> carDTOList) {
        for (Car car: employee.getCarList()) {
            CarDTO carDTO = new CarDTO();
            carDTO.setId(car.getId());
            carDTO.setModel(car.getModel());
            carDTO.setYear(car.getYear());

            carDTOList.add(carDTO);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CarDTO> getCarDTOList() {
        return carDTOList;
    }

    public void setCarDTOList(List<CarDTO> carDTOList) {
        this.carDTOList = carDTOList;
    }
}
