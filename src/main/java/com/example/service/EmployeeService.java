package com.example.service;

import com.example.model.Account;
import com.example.model.Employee;
import com.example.repository.AccountRepository;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class EmployeeService {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RandomService randomService;

    public void save(Employee employee) { // Đổi tên phương thức từ saveEmployee -> save
        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    @Transactional
    public void saveAccountEmp(Account account) {
        String code = randomService.generateRandomCode();
        String encodedPassword = passwordEncoder.encode(account.getPassword());
        account.setPassword(encodedPassword);
        accountRepository.save(account);

    }
}
