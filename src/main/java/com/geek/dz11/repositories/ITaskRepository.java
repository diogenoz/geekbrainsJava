package com.geek.dz11.repositories;


import com.geek.dz11.entities.Task;

import java.util.List;

public interface ITaskRepository {

    boolean addTask(Task task);

    List getTasks();

    Task updateTask(Task udpatedTask);

    Task findById(Long id);

    Task findByName(String name);

    boolean deleteTask(Task task);

    boolean isEmpty();
}