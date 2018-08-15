package com.example.demo.service;

import com.example.demo.EmployeeRepository;
import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    public List<Employee> getEmployees() {
        return (List<Employee>) this.repository.findAll();
    }

}
