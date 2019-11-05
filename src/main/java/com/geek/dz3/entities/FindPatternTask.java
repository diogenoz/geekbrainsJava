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
}
