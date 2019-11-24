package com.geek;


import com.geek.dz11.entities.Task;
import com.geek.dz11.services.TaskService;


public class Main {
    public static void main(String[] args) {
        int taskCount = 10;
        Task[] newTaskIds = new Task[taskCount];
        TaskService taskService = new TaskService();

        taskService.print();
        for (int i = 0; i < taskCount; i++) {
            Task newTask = new Task("Test" + i, "Den" + i, "Ivan" + i, "Test11");
            if (i == 1 || i == 6 || i == 7) {
                newTask.setStatus(Task.TaskStatus.InProgress);
            }
            if (i == 2 || i == 5 || i == 8 || i == 9) {
                newTask.setStatus(Task.TaskStatus.Done);
            }
            taskService.addTask(newTask);
            newTaskIds[i] = newTask;
        }
        taskService.print();
        taskService.addTask(new Task("Test" + taskCount + 1, "Den11", "Ivan0", "Test11"));

        // delete task by random Id
        try {
            //System.out.println("Deleted " + taskService.deleteTask(new Task("incorrectTask", "den", "Ivan", "test delete"));
        } catch (RuntimeException e) {
            System.out.println("Task with random id don't deleted");
        }

        // delete task test3
        //System.out.println("Deleted " + taskService.deleteTask(newTaskIds[2]) + " tasks with id=" + newTaskIds[2]);
        taskService.print();
    }
}
