package com.geek.dz2;

public class Task {
    private static long numberOfTasks = 0;
    protected long id;
    protected String name;
    protected String owner;
    protected String assignee;
    protected String description;
    protected String status = "Open";

    public Task(String name, String owner, String assignee, String description) {
        this.id = ++numberOfTasks;
        this.name = name;
        this.owner = owner;
        this.assignee = assignee;
        this.description = description;
    }

    public boolean isEqual(long id, String name) {
        return this.getId() == id || ((String) this.getName()).equals(name);
    }

    public void print() {
        System.out.println(this.id + " " + this.name + " " + this.owner + " " + this.assignee + " " + this.status);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}