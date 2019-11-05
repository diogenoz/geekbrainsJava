package com.geek.dz3.services;

import com.geek.dz3.entities.IFindable;
import com.geek.dz3.entities.Task;
import com.geek.dz3.repositories.ITaskRepository;
import com.geek.dz3.repositories.TaskRepository;

public class TaskService {
    ITaskRepository repository;
    int taskCount = 0;

    public void addTask(Task task) {
        if (!repository.isAllowedAddNewItem()) {
            System.out.println("Список задач заполнен");
        } else {
            boolean isTaskAdded = repository.addItem(task);
        }
    }

    public TaskService(int taskCount) {
        this.repository = new TaskRepository(taskCount);
    }

    public void print() {
        IFindable[] tasks = this.repository.getItems();
        if (tasks.length > 0) {
            System.out.println("Список задач:");
            for (IFindable task : tasks) {
                if (task != null) {
                    System.out.println((Task) task);
                }
            }
        } else {
            System.out.println("Список задач пуст");
        }
    }

    public void deleteTask(Task task) {
        repository.deleteItem(task);
    }
}
