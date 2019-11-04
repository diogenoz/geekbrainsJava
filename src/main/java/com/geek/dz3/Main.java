package com.geek.dz3;


import com.geek.dz3.entities.Task;
import com.geek.dz3.services.TaskService;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        int taskCount = 10;
        UUID[] newTaskIds = new UUID[taskCount];
        TaskService taskService = new TaskService(taskCount);
        taskService.print();
        for (int i = 0; i < taskCount; i++) {
            Task newTask = new Task("Test" + i, "Den" + i, "Ivan" + i, "Test11");
            taskService.addTask(newTask);
            newTaskIds[i] = newTask.getId();
        }
        taskService.print();
        // should print error
        taskService.addTask(new Task("Test" + taskCount + 1, "Den11", "Ivan0", "Test11"));

        // delete task test3
        taskService.deleteTask(new Task(newTaskIds[2]));
        taskService.print();

        // delete task with name Test5
        taskService.deleteTask(new Task("Test5"));
        taskService.print();

        // delete task with owner Den7
        taskService.deleteTask(new Task(null, "Den7", null, null, null));
        taskService.print();

    }
}
