package com.geek.dz11.repositories;

import com.geek.dz11.entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostgreEmployeeRepository implements IEmployeeRepository {
    private SessionFactory factory;

    public PostgreEmployeeRepository(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public boolean addEmployee(Employee employee) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
            return true;

        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List getEmployees() {
        Session session = factory.getCurrentSession();
        ArrayList<Employee> employees;
        try {
            session.beginTransaction();
            employees = (ArrayList<Employee>) session.createNamedQuery("Employee.findAll", Employee.class).getResultList();
            session.getTransaction().commit();
            return employees;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Employee updateEmployee(Employee udpatedEmployee) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.save(udpatedEmployee);
            session.getTransaction().commit();
            return udpatedEmployee;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee findById(Long id) {
        Session session = factory.getCurrentSession();
        Employee employee;
        try {
            session.beginTransaction();
            employee = session.createNamedQuery("Employee.findById", Employee.class)
                    .setParameter("id", id)
                    .getSingleResult();
            session.getTransaction().commit();
            return employee;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee findByName(String name) {
        Session session = factory.getCurrentSession();
        Employee employee;
        try {
            session.beginTransaction();
            employee = session.createNamedQuery("Employee.findByName", Employee.class)
                    .setParameter("name", name)
                    .getSingleResult();
            session.getTransaction().commit();
            return employee;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean isEmpty() {
        Session session = factory.getCurrentSession();
        boolean isEmpty;
        try {
            session.beginTransaction();
            isEmpty = session.createNamedQuery("Employee.findAll", Employee.class)
                    .getResultList().size() == 0;
            session.getTransaction().commit();
            return isEmpty;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
