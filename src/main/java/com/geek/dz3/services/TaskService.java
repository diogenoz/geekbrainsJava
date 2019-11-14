package com.geek.dz3.services;

import com.geek.dz3.entities.FindPatternTask;
import com.geek.dz3.entities.Task;
import com.geek.dz3.repositories.ITaskRepository;
import com.geek.dz3.repositories.TaskRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public void print(ArrayList<Task> tasks, String title) {
        if (!tasks.isEmpty()) {
            System.out.println("Список задач:" + title);
            for (Task task : tasks) {
                if (task != null) {
                    System.out.println(task);
                }
            }
        } else {
            System.out.println("Список задач " + title + " пуст");
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

    public ArrayList<Task> findByStatus(Task.TaskStatus status) {
        return (ArrayList<Task>) repository.getTasks().stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList()
                );
    }

    public boolean isTaskExists(UUID id) {
        return repository.getTasks().stream()
                .anyMatch(task -> task.getId() == id);
    }

    public ArrayList<Task> getSortedTasks() {
        return (ArrayList<Task>) repository.getTasks().stream()
                .sorted(new Comparator<Task>() {
                            @Override
                            public int compare(Task t1, Task t2) {
                                return t1.getStatus().getPrior() - t2.getStatus().getPrior();
                            }
                        }
                ).collect(Collectors.toList()
                );
    }

    public long computeTaskCountWithStatus(Task.TaskStatus status) {
        return (int) repository.getTasks().stream()
                .filter(task -> task.getStatus() == status)
                .count();
    }


}
