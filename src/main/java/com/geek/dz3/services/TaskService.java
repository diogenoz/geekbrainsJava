package com.geek.dz3.services;

import com.geek.dz3.entities.FindPatternTask;
import com.geek.dz3.entities.Task;
import com.geek.dz3.repositories.ITaskRepository;
import com.geek.dz3.repositories.TaskRepository;

import java.util.ArrayList;
import java.util.UUID;

public class TaskService {
    private ITaskRepository<Task> repository;

    public TaskService() {
        repository = new TaskRepository<Task>();
    }

    public boolean addTask(Task task) {
        return repository.addTask(task);
    }

    public void print() {
        if (!this.repository.isEmpty()) {
            ArrayList<Task> tasks = this.repository.getTasks();

            System.out.println("Список задач:");
            for (Task task : tasks) {
                if (task != null) {
                    System.out.println(task);
                }
            }
        } else {
            System.out.println("Список задач пуст");
        }
    }

    public boolean deleteTask(UUID id) {
        return repository.deleteTask(new FindPatternTask(id));
    }

    public boolean deleteTaskByName(String name) {
        return repository.deleteTask(new FindPatternTask(name));
    }

    public boolean deleteTaskByOwner(String owner) {
        return repository.deleteTask(new FindPatternTask(null, owner, null, null, null));
    }

    public boolean deleteTaskByPattern(FindPatternTask findPatternTask) {
        return repository.deleteTask(findPatternTask);
    }

    public Task updateTask(FindPatternTask findPattenTask, Task task) {
        return repository.updateTask(findPattenTask, task);
    }
}
