package io.khasang.freefly.dao.impl;


import io.khasang.freefly.dao.EmployeeDao;
import io.khasang.freefly.entity.Employee;

public class EmployeeDaoImpl extends BasicDaoImpl<Employee> implements EmployeeDao {
    public EmployeeDaoImpl(Class<Employee> entityClass) {
        super(entityClass);
    }
}
