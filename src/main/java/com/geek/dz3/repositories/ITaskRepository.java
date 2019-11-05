package com.geek.dz3.repositories;


import com.geek.dz3.entities.FindPatternTask;
import com.geek.dz3.entities.Task;

public interface ITaskRepository {
    boolean isFull();

    boolean addTask(Task task);

    Task[] getTasks();

    int deleteTasks(FindPatternTask findPatternTask);

    Task[] findTasks(FindPatternTask findPatternTask);

    boolean isEmpty();
}

