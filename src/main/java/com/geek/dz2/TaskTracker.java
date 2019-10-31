package com.geek.dz2;

public class TaskTracker {
    final private int MAX_TASK_COUNT = 10;
    Task[] tasks = new Task[MAX_TASK_COUNT];
    int taskCount = 0;

    public void addTask(String name, String owner, String assignee, String description) {
        if (taskCount >= MAX_TASK_COUNT) {
            System.out.println("Список задач заполнен");
        } else {
            for (int i = 0; i < this.tasks.length; i++) {
                if (this.tasks[i] == null) {
                    this.tasks[i] = new Task(name, owner, assignee, description);
                    this.taskCount++;
                    break;
                }
            }
        }
    }

    public void print() {
        if (this.taskCount > 0) {
            System.out.println("Список задач:");
            for (int i = 0; i < this.tasks.length; i++) {
                if (this.tasks[i] != null) {
                    this.tasks[i].print();
                }
            }
        } else {
            System.out.println("Список задач пуст");
        }
    }

    private int[] findTasksByIdOrByName(long id, String name) {
        int[] foundTasks = new int[this.MAX_TASK_COUNT];
        int foundTaskCount = 0;
        for (int i = 0; i < this.MAX_TASK_COUNT; i++) {
            if (this.tasks[i] != null && this.tasks[i].isEqual(id, name)) {
                foundTasks[foundTaskCount] = i;
            }
            foundTaskCount++;
        }
        return foundTasks;
    }

    public void deleteTaskByIdOrName(long id, String name) {
        int[] foundTasks = findTasksByIdOrByName(id, name);
        for (int i = 0; i < foundTasks.length; i++) {
            if (foundTasks[i] != 0) {
                this.tasks[foundTasks[i]] = null;
                this.taskCount--;
            }
        }
    }
}
