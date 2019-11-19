package com.geek.dz3.repositories;


import com.geek.dz3.entities.Task;

import java.util.ArrayList;

public interface ITaskRepository<T> {

    boolean addTask(Task task);

    ArrayList getTasks();

    Task updateTask(Task findPattenTask, Task newTask);

    boolean deleteTask(Task findTask);

    boolean isEmpty();
}

