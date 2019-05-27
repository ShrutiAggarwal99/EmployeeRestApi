package com.tsf.EmployeeRestApi.controller;

import com.tsf.EmployeeRestApi.model.Employee;
import com.tsf.EmployeeRestApi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    //CREATE
    @RequestMapping(method = RequestMethod.POST, value = "/addEmployee")
    public String fun1(@RequestBody Employee employee){
        try {
            employeeRepository.save(employee);
            return "Record for " + employee.getName() + " is inserted!";
        }
        catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    //RETRIEVE
    @RequestMapping(method = RequestMethod.GET, value = "/getAllEmployees", produces = {"application/json"})
    public List<Employee> fun2(){
        return employeeRepository.findAll();
    }

    //UPDATE
    @RequestMapping(method = RequestMethod.PUT, value = "/updateEmployee")
    public String fun4(@RequestParam(name = "id") Long employeeID, @RequestParam(name = "name", defaultValue = "false") String newName,
                                    @RequestParam(name = "designation", defaultValue = "false") String newDesignation, @RequestParam(name = "department", defaultValue = "false") String newDept){
        try{
            Employee employee = employeeRepository.getOne(employeeID);
            if(newName.compareTo("false")!=0) employee.setName(newName);
            if(newDesignation.compareTo("false")!=0) employee.setDesignation(newDesignation);
            if(newDept.compareTo("false")!=0) employee.setDepartment(newDept);
            employeeRepository.save(employee);
            return "Record of student with ID " + employeeID + " updated!";
        }
        catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }


    //DELETE
    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteEmployee")
    public String fun5(@RequestParam(name = "id") Long employeeID){
        try{
            Employee employee = employeeRepository.getOne(employeeID);
            employeeRepository.delete(employee);
            return "Record for " + employee.getName() + " deleted!";
        }
        catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }


}
