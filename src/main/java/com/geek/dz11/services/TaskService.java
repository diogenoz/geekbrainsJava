package com.geek.dz11.services;

import com.geek.dz11.entities.Task;
import com.geek.dz11.repositories.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class TaskService {
    private ITaskRepository repository;

    public TaskService() {
    }

    public boolean addTask(Task task) {
        return repository.addTask(task);
    }

    public void print() {
        if (!this.repository.isEmpty()) {
            List tasks = this.repository.getTasks();

            System.out.println("Список задач:");
            Iterator iterator = tasks.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } else {
            System.out.println("Список задач пуст");
        }
    }

    public boolean deleteTask(Task task) {
        return repository.deleteTask(task);
    }

    public Task updateTask(Task updTask) {
        return repository.updateTask(updTask);
    }

    public List getEmployees() {
        return repository.getTasks();
    }

    public Task findById(Long id) {
        return repository.findById(id);
    }

    public Task findByName(String name) {
        return repository.findByName(name);
    }

    @Autowired
    public void setRepository(ITaskRepository repository) {
        this.repository = repository;
    }
}
