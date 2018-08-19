package io.khasang.freefly.service;

import io.khasang.freefly.dto.EmployeeDTO;
import io.khasang.freefly.entity.Employee;

import java.util.List;

public interface EmployeeService {
    /**
     * method for add employee
     *
     * @param employee new employee for creation
     * @return created employee
     */
    Employee addEmployee(Employee employee);

    /**
     * method for get employeeDTO by specific id
     *
     * @param id id employee's
     * @return employeeDTO : employeeDTO's id = id
     */
    EmployeeDTO getEmployeeDTOById(long id);

    /**
     * method for get all employeesDTO
     *
     * @return all employees
     */
    List<EmployeeDTO> getListDTO();

    /**
     * method for update employee
     *
     * @param employee new date employee
     * @return updated employee, null if not exists employee: employee.id==id
     */
    Employee updateEmployee(Employee employee);

    /**
     * method for delete employee
     *
     * @param id employee's for delete
     * @return deleted employee
     */
    Employee delEmployeeById(long id);
}
