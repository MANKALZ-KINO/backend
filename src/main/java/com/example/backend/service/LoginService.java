package com.example.backend.service;

import com.example.backend.model.Employee;
import com.example.backend.repositories.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    public LoginService(IEmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    private final IEmployeeRepository employeeRepository;


    public boolean authenticate(String name, String password) {
        Employee employee = employeeRepository.findByName(name);

        return employee != null && employee.getPassword().equals(password);
    }
}
