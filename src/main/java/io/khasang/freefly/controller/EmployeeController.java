package io.khasang.freefly.controller;

import io.khasang.freefly.dto.EmployeeDTO;
import io.khasang.freefly.entity.Employee;
import io.khasang.freefly.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Employee addEmployee(@RequestBody Employee employee) {
        return  employeeService.addEmployee(employee);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public EmployeeDTO getEmployeeDTOById(@PathVariable(value = "id") String id){
        return employeeService.getEmployeeDTOById(Long.parseLong(id));
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<EmployeeDTO> getAllEmployeesDTO(){
        return employeeService.getListDTO();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Employee delEmployee(@PathVariable(value = "id") String id) {
        return employeeService.delEmployeeById(Long.parseLong(id));
    }

}
