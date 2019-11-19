package com.geek.dz3.repositories;

import com.geek.dz3.entities.Task;

import java.util.ArrayList;

public class TaskRepository implements ITaskRepository {
    private ArrayList tasks = new ArrayList();

    @Override
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    @Override
    public boolean addTask(Task task) {
        return tasks.add(task);
    }

    @Override
    public ArrayList getTasks() {
        return this.tasks;
    }

    @Override
    public Task updateTask(Task findPattenTask, Task newTask) {
        int pos = tasks.indexOf(findPattenTask);
        if (pos == -1) {
            throw new RuntimeException("Такой задачи для обновления не существует");
        }
        tasks.set(pos, newTask);
        return (Task) tasks.get(pos);
    }
    @Override
    public boolean deleteTask(Task findTask) {
        if (!tasks.contains(findTask)) {
            throw new RuntimeException("Такой задачи для удаления не существует");
        }
        return tasks.remove(findTask);
    }
}