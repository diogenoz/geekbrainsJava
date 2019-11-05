package com.geek.dz3.repositories;

import com.geek.dz3.entities.FindPatternTask;
import com.geek.dz3.entities.Task;

public class TaskRepository implements ITaskRepository {
    private Task[] tasks;
    private int taskCount = 0;

    @Override
    public boolean isFull() {
        return taskCount < this.tasks.length;
    }

    @Override
    public boolean isEmpty() {
        return this.taskCount == 0;
    }

    @Override
    public boolean addTask(Task task) {
        if (isFull()) {
            for (int i = 0; i < this.tasks.length; i++) {
                if (this.tasks[i] == null) {
                    this.tasks[i] = task;
                    this.taskCount++;
                    return true;
                }
            }
        }
        return false;
    }

    public TaskRepository(int taskCount) {
        this.tasks = new Task[taskCount];
    }

    @Override
    public Task[] getTasks() {
        return this.tasks;
    }

    private Integer[] findInnerTasks(Object obj) {
        Integer[] founditems = new Integer[this.tasks.length];
        int founditemCount = 0;
        for (int i = 0; i < this.tasks.length; i++) {
            if (this.tasks[i] != null && this.tasks[i].findByPatternObj(obj)) {
                founditems[founditemCount] = i;
            }
            founditemCount++;
        }
        return founditems;
    }

    @Override
    public int deleteTasks(FindPatternTask findPatternTask) {
        Integer[] foundTasks = this.findInnerTasks(findPatternTask);
        int deletedTaskCount = 0;
        for (Integer founTask : foundTasks) {
            if (founTask != null) {
                this.tasks[founTask] = null;
                this.taskCount--;
                deletedTaskCount++;
            }
        }
        return deletedTaskCount;
    }

    @Override
    public Task[] findTasks(FindPatternTask findPatternTask) {
        int findResultsCount = 0;
        Integer[] foundTasks = this.findInnerTasks(findPatternTask);
        Task[] findResults = new Task[foundTasks.length];
        for (Integer foundTask : foundTasks) {
            if (foundTask != null) {
                findResults[findResultsCount] = this.tasks[foundTask];
                findResultsCount++;
            }
        }
        return findResults;
    }
}


