package com.geek.dz3.repositories;

import java.util.ArrayList;

public class TaskRepository<T> implements ITaskRepository<T> {
    private ArrayList<T> tasks = new ArrayList<T>();

    @Override
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    @Override
    public boolean addTask(T task) {
        return tasks.add(task);
    }

    @Override
    public ArrayList<T> getTasks() {
        return this.tasks;
    }

    @Override
    public T updateTask(T findPattenTask, T newTask) {
        int pos = tasks.indexOf(findPattenTask);
        if (pos == -1) {
            throw new RuntimeException("Такой задачи для обновления не существует");
        }
        tasks.set(pos, newTask);
        return tasks.get(pos);
    }
    @Override
    public boolean deleteTask(T findTask) {
        if (!tasks.contains(findTask)) {
            throw new RuntimeException("Такой задачи для удаления не существует");
        }
        return tasks.remove(findTask);
    }
}