package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeesController {

    @Autowired
    EmployeeService service;

    @GetMapping("/admin/employees")
    public List<Employee> getEmployeeList() {
        return service.getEmployees();
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return service.getEmployees();
    }

    @GetMapping("/me")
    public Employee getMe(@AuthenticationPrincipal Employee employee) {
        return employee;
    }

}

