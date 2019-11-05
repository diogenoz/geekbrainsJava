package com.geek.dz3.entities;

import java.util.UUID;

public class Task implements IFindable {
    public enum TaskStatus {
        Open("Open"), InProgress("In Progress"), Done("Done");
        private String statusName;

        public String getStatusName() {
            return statusName;
        }

        TaskStatus(String statusName) {
            this.statusName = statusName;
        }
    }

    private UUID id;
    private String name;
    private String owner;
    private String assignee;
    private String description;
    private TaskStatus status;

    public Task(UUID id) {
        this.id = id;
    }

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, String owner, String assignee, String description) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.owner = owner;
        this.assignee = assignee;
        this.description = description;
        this.status = TaskStatus.Open;
    }

    public Task(String name, String owner, String assignee, String description, TaskStatus status) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.owner = owner;
        this.assignee = assignee;
        this.description = description;
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof Task)) {
            return false;
        }
        Task otherTask = (Task) obj;
        return this.id == otherTask.id || this.name.equals(otherTask.name);
    }

    @Override
    public boolean findByPatternObj(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof Task)) {
            return false;
        }
        Task otherTask = (Task) obj;
        return (this.id == otherTask.getId() && this.getId() != null && otherTask.getId() != null)
                || (this.name.equals(otherTask.getName()) && this.name != null && otherTask.getName() != null)
                || (this.assignee.equals(otherTask.assignee) && this.assignee != null && otherTask.getAssignee() != null)
                || (this.owner.equals(otherTask.owner) && this.owner != null && otherTask.getOwner() != null)
                || (this.description.equals(otherTask.description) && this.description != null && otherTask.getDescription() != null)
                || (this.status.equals(otherTask.status) && this.status != null && otherTask.getStatus() != null);
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s", this.id, this.name, this.owner, this.assignee, this.status.getStatusName());
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getAssignee() {
        return assignee;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }
}