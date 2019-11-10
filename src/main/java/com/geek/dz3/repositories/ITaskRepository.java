package com.geek.dz3.repositories;


import java.util.ArrayList;

public interface ITaskRepository<T> {

    boolean addTask(T task);

    ArrayList<T> getTasks();

    T updateTask(T findPattenTask, T newTask);

    boolean deleteTask(T findTask);

    boolean isEmpty();
}

