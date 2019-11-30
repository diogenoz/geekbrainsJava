package com.geek.dz11.services;

import com.geek.dz11.entities.Employee;
import com.geek.dz11.repositories.IEmployeeRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private IEmployeeRepository repository;

    public EmployeeService(SessionFactory factory) {
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

    @Autowired
    public void setRepository(IEmployeeRepository repository) {
        this.repository = repository;
    }

}
