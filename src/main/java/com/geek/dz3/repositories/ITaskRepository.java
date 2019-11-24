package com.geek.dz3.repositories;


import com.geek.dz3.entities.Task;

import java.util.List;

public interface ITaskRepository {

    boolean addTask(Task task);

    List getTasks();

    Task updateTask(Task udpatedTask);

    boolean deleteTask(Task findTask);

    boolean isEmpty();
}