package com.geek.dz11.repositories;

import com.geek.dz11.entities.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class PostgreTaskRepository implements ITaskRepository {
    private SessionFactory factory;

    public PostgreTaskRepository() {
    }

    @Override
    public boolean addTask(Task task) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
            return true;

        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List getTasks() {
        Session session = factory.getCurrentSession();
        ArrayList<Task> tasks;
        try {
            session.beginTransaction();
            tasks = (ArrayList<Task>) session.createNamedQuery("Task.findAll", Task.class).getResultList();
            session.getTransaction().commit();
            return tasks;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Task updateTask(Task udpatedTask) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.save(udpatedTask);
            session.getTransaction().commit();
            return udpatedTask;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteTask(Task task) {

        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.delete(task);
            session.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Task findById(Long id) {
        Session session = factory.getCurrentSession();
        Task task;
        try {
            session.beginTransaction();
            task = session.createNamedQuery("Task.findById", Task.class)
                    .setParameter("id", id)
                    .getSingleResult();
            session.getTransaction().commit();
            return task;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Task findByName(String name) {
        Session session = factory.getCurrentSession();
        Task task;
        try {
            session.beginTransaction();
            task = session.createNamedQuery("Task.findByName", Task.class)
                    .setParameter("name", name)
                    .getSingleResult();
            session.getTransaction().commit();
            return task;
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
            isEmpty = session.createNamedQuery("Task.findAll").getResultList().size() == 0;
            session.getTransaction().commit();
            return isEmpty;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }
}