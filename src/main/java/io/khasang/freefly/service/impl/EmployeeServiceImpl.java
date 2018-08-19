package io.khasang.freefly.service.impl;

import io.khasang.freefly.dao.EmployeeDao;
import io.khasang.freefly.dto.EmployeeDTO;
import io.khasang.freefly.entity.Employee;
import io.khasang.freefly.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private EmployeeDTO employeeDTO;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeDao.create(employee);
    }

    @Override
    public EmployeeDTO getEmployeeDTOById(long id) {
        return employeeDTO.getEmployeeDTO(employeeDao.getById(id));
    }

    @Override
    public List<EmployeeDTO> getListDTO() {
        return employeeDTO.getEmployeeDTOList(employeeDao.getList());
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeDao.update(employee);
    }

    @Override
    public Employee delEmployeeById(long id) {
        return employeeDao.delById(id);
    }
}
