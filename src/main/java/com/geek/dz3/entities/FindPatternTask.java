package com.geek.dz3.entities;

import java.util.UUID;

public class FindPatternTask extends Task {

    public FindPatternTask(UUID id) {
        this.id = id;
    }

    public FindPatternTask(String name) {
        this.name = name;
    }

    public FindPatternTask(String name, String owner, String assignee, String description, TaskStatus status) {
        super(name, owner, assignee, description);
        super.setStatus(status);
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
        return this.getId() != null && otherTask.getId() != null && this.id == otherTask.getId()
                || this.name != null && otherTask.getName() != null && this.name.equals(otherTask.getName())
                || this.getAssignee() != null && otherTask.getAssignee() != null && this.assignee.equals(otherTask.assignee)
                || this.getOwner() != null && otherTask.getOwner() != null && this.owner.equals(otherTask.owner)
                || this.getDescription() != null && otherTask.getDescription() != null && this.description.equals(otherTask.description)
                || this.getStatus() != null && otherTask.getStatus() != null && this.status.equals(otherTask.status);

    }

    @Override
    public String toString() {
        return null;
    }

}
