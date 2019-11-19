package com.geek.dz3.services;

import com.geek.dz3.entities.FindPatternTask;
import com.geek.dz3.entities.Task;
import com.geek.dz3.repositories.ITaskRepository;
import com.geek.dz3.repositories.TaskRepository;
import com.geek.dz3.services.csvprocessors.TaskStatusCsvProcessor;
import com.geek.dz3.services.csvprocessors.UuidCsvProcessor;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TaskService {
    private ITaskRepository<Task> repository;

    public TaskService() {
        repository = new TaskRepository<Task>();
    }

    public boolean addTask(Task task) {
        return repository.addTask(task);
    }

    public void print() {
        if (!this.repository.isEmpty()) {
            ArrayList<Task> tasks = this.repository.getTasks();

            System.out.println("Список задач:");
            for (Task task : tasks) {
                if (task != null) {
                    System.out.println(task);
                }
            }
        } else {
            System.out.println("Список задач пуст");
        }
    }

    public void print(ArrayList<Task> tasks, String title) {
        if (!tasks.isEmpty()) {
            System.out.println("Список задач:" + title);
            for (Task task : tasks) {
                if (task != null) {
                    System.out.println(task);
                }
            }
        } else {
            System.out.println("Список задач " + title + " пуст");
        }
    }

    public boolean deleteTask(UUID id) {
        return repository.deleteTask(new FindPatternTask(id));
    }

    public boolean deleteTaskByName(String name) {
        return repository.deleteTask(new FindPatternTask(name));
    }

    public boolean deleteTaskByOwner(String owner) {
        return repository.deleteTask(new FindPatternTask(null, owner, null, null, null));
    }

    public boolean deleteTaskByPattern(FindPatternTask findPatternTask) {
        return repository.deleteTask(findPatternTask);
    }

    public Task updateTask(FindPatternTask findPattenTask, Task task) {
        return repository.updateTask(findPattenTask, task);
    }

    public ArrayList<Task> findByStatus(Task.TaskStatus status) {
        return (ArrayList<Task>) repository.getTasks().stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList()
                );
    }

    public boolean isTaskExists(UUID id) {
        return repository.getTasks().stream()
                .anyMatch(task -> task.getId().equals(id));
    }

    public ArrayList<Task> getSortedTasks() {
        return (ArrayList<Task>) repository.getTasks().stream()
                .sorted(new Comparator<Task>() {
                            @Override
                            public int compare(Task t1, Task t2) {
                                return t1.getStatus().getPrior() - t2.getStatus().getPrior();
                            }
                        }
                ).collect(Collectors.toList()
                );
    }

    public long computeTaskCountWithStatus(Task.TaskStatus status) {
        return (int) repository.getTasks().stream()
                .filter(task -> task.getStatus() == status)
                .count();
    }

    public void exportToJsonFile(File file) {
        ArrayList<Task> tasks = repository.getTasks();
        JSONArray JSONTasks = new JSONArray();
        JSONTasks.addAll(tasks);
        try (StringWriter writer = new StringWriter()) {
            JSONTasks.writeJSONString(writer);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(writer.toString());
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> importFromJsonFile(File file) {
        ArrayList<Task> tasks = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String str;
            while ((str = reader.readLine()) != null) {
                stringBuilder.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONParser parser = new JSONParser();
        try {
            System.out.println(stringBuilder.toString());
            Object obj = parser.parse(stringBuilder.toString());
            JSONArray jsonTasks = (JSONArray) obj;
            Iterator iterator = jsonTasks.iterator();
            while (iterator.hasNext()) {
                tasks.add((Task.fromJSONObject((Map) iterator.next())));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tasks;
    }


    public void exportToFile(File file) {
        ArrayList<Task> tasks = repository.getTasks();
        try (BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file))) {
            ObjectOutputStream out = new ObjectOutputStream(writer);
            out.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> importFromFile(File file) {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file))) {
            ObjectInputStream in = new ObjectInputStream(reader);
            tasks = (ArrayList<Task>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    private CellProcessor[] getProcessors() {
        CellProcessor[] processors = new CellProcessor[]{
                new UuidCsvProcessor(), // taskId (must be unique)
                new NotNull(), // taskName
                new Optional(), // owner
                new Optional(), // assignee
                new Optional(), // description
                new TaskStatusCsvProcessor() // task
        };
        return processors;
    }

    public void exportToCsvFile(File file) {
        ArrayList<Task> tasks = repository.getTasks();
        //ICsvListWriter writer = null;
        try (ICsvBeanWriter writer = new CsvBeanWriter(new FileWriter(file), CsvPreference.STANDARD_PREFERENCE)) {
            CellProcessor[] processors = getProcessors();
            String[] header = new String[]{"id", "name", "owner", "assignee", "description", "status"};
            writer.writeHeader(header);
            Iterator iterator = tasks.iterator();
            while (iterator.hasNext()) {
                writer.write(iterator.next(), header, processors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> importFromCsvFile(File file) {
        ArrayList<Task> tasks = new ArrayList<>();
        try (ICsvBeanReader reader = new CsvBeanReader(new FileReader(file), CsvPreference.STANDARD_PREFERENCE)) {
            CellProcessor[] processors = getProcessors();
            String[] header = reader.getHeader(true);
            Task task;
            while ((task = (Task) reader.read(Task.class, header, processors)) != null) {
                tasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

}
