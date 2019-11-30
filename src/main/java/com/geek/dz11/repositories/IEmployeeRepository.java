package com.geek.dz11.repositories;

import com.geek.dz11.entities.Employee;

import java.util.List;

public interface IEmployeeRepository {
    boolean addEmployee(Employee employee);

    List getEmployees();

    Employee updateEmployee(Employee udpatedEmployee);

    Employee findById(Long id);

    Employee findByName(String name);

    boolean isEmpty();
}
