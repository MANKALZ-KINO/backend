package com.example.backend.controllers;

import com.example.backend.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backend.model.Employee;
import com.example.backend.repositories.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/login")

@RestController
@CrossOrigin("*")
public class LoginController {

    private final LoginService loginService;
    @Autowired
    IEmployeeRepository iEmployeeRepository;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/employees")
    public List<Employee> movies() {
        return iEmployeeRepository.findAll();
    }
    @GetMapping("/employee/{name}") public List<Employee> allEmployeesByName(@PathVariable String name) {
        return iEmployeeRepository.findByNameIgnoreCase(name);
    }

    @PostMapping("/loggedin")
    public ResponseEntity<String> login(@RequestParam String name, @RequestParam String password) {
        boolean isAuthenticated = loginService.authenticate(name, password);

        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
