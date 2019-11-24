package com.geek.dz11.services;

import com.geek.dz11.entities.Task;
import com.geek.dz11.repositories.ITaskRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskService {
    private ITaskRepository repository;

    public TaskService() {
        //    repository = new TaskRepository();
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

    public void print(ArrayList tasks, String title) {
        if (!tasks.isEmpty()) {
            System.out.println("Список задач:" + title);
            for (Object task : tasks) {
                if (task != null) {
                    System.out.println(task);
                }
            }
        } else {
            System.out.println("Список задач " + title + " пуст");
        }
    }

    public boolean deleteTask(Task task) {
        return repository.deleteTask(task);
    }

    public Task updateTask(Task updTask) {
        return repository.updateTask(updTask);
    }
}
