package com.geek.dz2;

public class Main {
    public static void main(String[] args) {
        TaskTracker taskTracker = new TaskTracker();
        taskTracker.print();
        for (int i = 0; i < 10; i++) {
            taskTracker.addTask("Test" + i, "Den" + i, "Ivan" + (11 - i), "Test" + (i));
        }
        taskTracker.print();
        // should print error
        taskTracker.addTask("Test11", "Den11", "Ivan0", "Test11");

        // delete task test3
        taskTracker.deleteTaskByIdOrName(3, null);
        taskTracker.print();

        // delete task 5
        taskTracker.deleteTaskByIdOrName(-1, "Test5");
        taskTracker.print();

    }
}
