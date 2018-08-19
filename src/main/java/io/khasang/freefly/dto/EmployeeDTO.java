package io.khasang.freefly.dto;

import io.khasang.freefly.entity.Car;
import io.khasang.freefly.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeDTO {

    private Long id;
    String name;
    private List<CarDTO> carDTOList = new ArrayList<>();

    public List<EmployeeDTO> getEmployeeDTOList(List<Employee> employeeList){
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for (Employee employee : employeeList) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setName(employee.getName());
            employeeDTO.setCarDTOList(getCarDTOListFromEmployee(employee));

            employeeDTOList.add(employeeDTO);
        }
        return employeeDTOList;
    }

    private List<CarDTO> getCarDTOListFromEmployee(Employee employee) {
        List<Car> carList = employee.getCarList();
        List<CarDTO> carDTOList = new ArrayList<>();
        for (Car car : carList) {
            CarDTO carDTO = new CarDTO();
            carDTO.setId(car.getId());
            carDTO.setModel(car.getModel());
            carDTO.setYear(car.getYear());
            carDTOList.add(carDTO);
        }
        return carDTOList;
    }

    public EmployeeDTO getEmployeeDTO(Employee employee){
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setName(employee.getName());
            employeeDTO.setCarDTOList(getCarDTOListFromEmployee(employee));
            return  employeeDTO;
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

    public List<CarDTO> getCarDTOList() {
        return carDTOList;
    }

    public void setCarDTOList(List<CarDTO> carDTOList) {
        this.carDTOList = carDTOList;
    }
}
