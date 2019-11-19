package com.geek.dz3.repositories;

import com.geek.dz3.entities.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class TaskSqlLiteRepository implements ITaskRepository {
    private Connection connection;
    private Statement stmt;

    public TaskSqlLiteRepository() {
        try {
            connect();
            createTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private void createTable() throws SQLException {
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS tasks (\n" +
                "        id          TEXT PRIMARY KEY,\n" +
                "        name        TEXT,\n" +
                "        owner       TEXT,\n" +
                "        assignee    TEXT,\n" +
                "        description TEXT,\n" +
                "        status      TEXT\n" +
                "    );");
    }

    private void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:gb.db");
        stmt = connection.createStatement();
    }

    private void disconnect() {
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addTask(Task task) {
        try {
            connect();
            String query = "INSERT INTO tasks (id, name, owner, assignee, description, status) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, task.getId().toString());
            ps.setString(2, task.getName());
            ps.setString(3, task.getOwner());
            ps.setString(4, task.getAssignee());
            ps.setString(5, task.getDescription());
            ps.setString(6, task.getStatus().getStatusName());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            disconnect();
        }
        return true;
    }

    @Override
    public ArrayList getTasks() {
        ArrayList tasks = new ArrayList();
        try {
            connect();
            String query = "SELECT t.id, t.name, t.owner, t.assignee, t.description, t.status FROM tasks t;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UUID id = UUID.fromString(rs.getString("id"));
                String name = rs.getString("name");
                String owner = rs.getString("owner");
                String assignee = rs.getString("assignee");
                String description = rs.getString("description");
                Task.TaskStatus status = Task.TaskStatus.fromString(rs.getString("status"));
                tasks.add(new Task(id, name, owner, assignee, description, status));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return tasks;
    }

    private ArrayList<String> preparePatternTask(Task findPattenTask) {
        ArrayList<String> patternList = new ArrayList<>();
        if (findPattenTask.getId() != null) {
            patternList.add("id=\"" + findPattenTask.getId().toString() + "\"");
        }
        if (findPattenTask.getName() != null) {
            patternList.add("name=\"" + findPattenTask.getName() + "\"");
        }
        if (findPattenTask.getOwner() != null) {
            patternList.add("owner=\"" + findPattenTask.getOwner() + "\"");
        }
        if (findPattenTask.getAssignee() != null) {
            patternList.add("assignee=\"" + findPattenTask.getAssignee() + "\"");
        }
        if (findPattenTask.getDescription() != null) {
            patternList.add("description=\"" + findPattenTask.getDescription() + "\"");
        }
        if (findPattenTask.getStatus() != null) {
            patternList.add("status=\"" + findPattenTask.getStatus().getStatusName() + "\"");
        }
        return patternList;
    }

    private String prepareWhereStatement(Task findPattenTask) {
        return String.join(" OR ", preparePatternTask(findPattenTask));
    }

    private String prepareUpdateStatement(Task findPattenTask) {
        return String.join(", ", preparePatternTask(findPattenTask));
    }

    @Override
    public Task updateTask(Task findPattenTask, Task newTask) {
        try {
            connect();
            String updateStatement = prepareUpdateStatement(findPattenTask);
            String whereStatement = prepareWhereStatement(newTask);
            String query = "UPDATE tasks set " + updateStatement + " WHERE " + whereStatement + ";";
            System.out.println(query);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return newTask;
    }

    @Override
    public boolean deleteTask(Task findTask) {
        int deleteCount = 0;
        try {
            connect();
            String whereStatement = prepareWhereStatement(findTask);
            String query = "DELETE FROM tasks WHERE " + whereStatement + ";";
            System.out.println(query);
            Statement stmt = connection.createStatement();
            deleteCount = stmt.executeUpdate(query);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return deleteCount > 0;
    }

    @Override
    public boolean isEmpty() {
        int recordCount = 0;
        try {
            connect();
            String query = "SELECT * FROM tasks LIMIT 1;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                recordCount++;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return recordCount == 0;
    }
}
