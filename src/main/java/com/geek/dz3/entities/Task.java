package com.geek.dz3.entities;

import java.util.UUID;

public class Task {

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

    UUID id;
    String name;
    String owner;
    String assignee;
    String description;
    TaskStatus status;

    public Task(String name, String owner, String assignee, String description) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.owner = owner;
        this.assignee = assignee;
        this.description = description;
        this.status = TaskStatus.Open;
    }

    public Task() {
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
    public String toString() {
        return String.format("%s, %s, %s, %s, %s", this.id, this.name, this.owner, this.assignee, this.status.getStatusName());
    }

    String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    String getOwner() {
        return owner;
    }

    String getAssignee() {
        return assignee;
    }

    String getDescription() {
        return description;
    }

    TaskStatus getStatus() {
        return status;
    }

    void setStatus(TaskStatus status) {
        this.status = status;
    }
}