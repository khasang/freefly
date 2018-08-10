package io.khasang.freefly.service;

import io.khasang.freefly.entity.Employee;

import java.util.List;

public interface EmployeeService {
    /**
     * method for add employee
     *
     * @param employee - new employee for creation
     * @return created employee
     */
    Employee addEmployee(Employee employee);

    /**
     * method for getting employee by specific id
     *
     * @param id - employee's id
     * @return employee by id
     */
    Employee getEmployeeById(long id);

    /**
     * method for getting all employees
     *
     * @return employee's list
     */
    List<Employee> getAllEmployees();

    /**
     * method for update employee
     *
     * @param employee - update for employee
     * @return updated employee
     */
    Employee updateEmployee(Employee employee);

    /**
     * method for delete employee by specific id
     *
     * @param id for deleting employee
     */
    void deleteEmployeeById(long id);
}
