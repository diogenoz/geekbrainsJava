package com.geek.dz11.services;

import com.geek.dz11.entities.Employee;
import com.geek.dz11.repositories.IEmployeeRepository;
import com.geek.dz11.repositories.PostgreEmployeeRepository;
import org.hibernate.SessionFactory;

import java.util.List;

public class EmployeeService {
    private IEmployeeRepository repository;

    public EmployeeService(SessionFactory factory) {
        repository = new PostgreEmployeeRepository(factory);
    }

    public boolean addEmployee(Employee employee) {
        return repository.addEmployee(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return repository.updateEmployee(employee);
    }

    public List getEmployees() {
        return repository.getEmployees();
    }

    public Employee findById(Long id) {
        return repository.findById(id);
    }

    public Employee findByName(String name) {
        return repository.findByName(name);
    }
}
