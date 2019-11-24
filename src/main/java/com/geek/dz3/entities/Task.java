package com.geek.dz3.entities;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "task")
public class Task implements Serializable, JSONStreamAware {

    public Task(UUID id, String name, String owner, String assignee, String description, TaskStatus status) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.assignee = assignee;
        this.description = description;
        this.status = TaskStatus.Open;
    }

    public static Task fromJSONObject(Map jsonObject) {
        Task task = new Task();
        task.id = UUID.fromString(jsonObject.get("id").toString());
        task.name = jsonObject.get("name").toString();
        task.owner = jsonObject.get("owner").toString();
        task.assignee = jsonObject.get("assignee").toString();
        task.description = jsonObject.get("description").toString();
        task.status = TaskStatus.fromString(jsonObject.get("status").toString());
        return task;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    Long id;

    @Column(name = "id")
    String name;
    @Column(name = "owner")
    String owner;
    @Column(name = "assignee")
    String assignee;
    @Column(name = "description")
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

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public void writeJSONString(Writer writer) throws IOException {
        LinkedHashMap obj = new LinkedHashMap();
        obj.put("id", this.id.toString());
        obj.put("name", this.name);
        obj.put("owner", this.owner);
        obj.put("assignee", this.assignee);
        obj.put("description", this.description);
        obj.put("status", this.status.getStatusName());

        JSONValue.writeJSONString(obj, writer);
    }

    public enum TaskStatus {
        Open("Open", 1), InProgress("In Progress", 2), Done("Done", 3);
        private String statusName;
        private int prior;

        public String getStatusName() {
            return statusName;
        }

        public int getPrior() {
            return prior;
        }

        public static TaskStatus fromString(String statusString) {
            for (TaskStatus taskStatus : TaskStatus.values()) {
                if (taskStatus.getStatusName().equals(statusString)) {
                    return taskStatus;
                }
            }
            throw new RuntimeException("Not found task Status");
        }

        TaskStatus(String statusName, int prior) {
            this.statusName = statusName;
            this.prior = prior;
        }

    }

}