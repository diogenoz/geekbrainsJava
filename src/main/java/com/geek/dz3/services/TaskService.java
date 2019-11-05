package com.geek.dz3.services;

import com.geek.dz3.entities.FindPatternTask;
import com.geek.dz3.entities.Task;
import com.geek.dz3.repositories.ITaskRepository;
import com.geek.dz3.repositories.TaskRepository;

import java.util.UUID;

public class TaskService {
    ITaskRepository repository;

    public void addTask(Task task) {
        if (!repository.isFull()) {
            System.out.println("Список задач заполнен");
        } else {
            boolean isTaskAdded = repository.addTask(task);
        }
    }

    public TaskService(int taskCount) {
        this.repository = new TaskRepository(taskCount);
    }

    public void print() {
        if (!this.repository.isEmpty()) {
            Task[] tasks = this.repository.getTasks();

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

    public int deleteTask(UUID id) {
        return repository.deleteTasks(new FindPatternTask(id));
    }

    public int deleteTaskByName(String name) {
        return repository.deleteTasks(new FindPatternTask(name));
    }

    public int deleteTaskByOwner(String owner) {
        return repository.deleteTasks(new FindPatternTask(null, owner, null, null, null));
    }

    public int deleteTaskByPattern(FindPatternTask findPatternTask) {
        return repository.deleteTasks(findPatternTask);
    }
}
