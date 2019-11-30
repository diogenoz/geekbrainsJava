package com.geek;


import com.geek.dz11.HibernateConfig;
import com.geek.dz11.entities.Employee;
import com.geek.dz11.entities.Task;
import com.geek.dz11.services.EmployeeService;
import com.geek.dz11.services.TaskService;
import org.hibernate.SessionFactory;


public class Main {
    public static void main(String[] args) {
        SessionFactory factory = HibernateConfig.getFactory();
        TaskService taskService = new TaskService(factory);
        EmployeeService employeeService = new EmployeeService(factory);

        Employee employee1 = new Employee("Ivanov", 22);
        employeeService.addEmployee(employee1);
        Employee employee2 = new Employee("Petrov", 33);
        employeeService.addEmployee(employee2);
        Employee employee3 = new Employee("Sidorov", 44);
        employeeService.addEmployee(employee3);


        int taskCount = 10;
        Task[] newTasks = new Task[taskCount];
        Employee ownerEmployee = employeeService.findByName("Petrov");
        Employee assigneeEmployee = employeeService.findByName("Sidorov");
        taskService.print();
        for (int i = 0; i < taskCount; i++) {
            Task newTask = new Task("Test" + i, ownerEmployee, assigneeEmployee, "Test11");
            if (i == 1 || i == 6 || i == 7) {
                newTask.setStatus(Task.TaskStatus.InProgress);
            }
            if (i == 2 || i == 5 || i == 8 || i == 9) {
                newTask.setStatus(Task.TaskStatus.Done);
            }
            taskService.addTask(newTask);
            newTasks[i] = newTask;
        }
        // delete task by random Id
        try {
            System.out.println("Deleted " + taskService.deleteTask(new Task("incorrectTask", ownerEmployee, assigneeEmployee, "test delete")));
        } catch (RuntimeException e) {
            System.out.println("Task with random id don't deleted");
        }

        // delete task test3
        System.out.println("Deleted " + taskService.deleteTask(taskService.findById(newTasks[2].getId())) + " tasks with id=" + newTasks[2].getId());
        taskService.print();

        // delete task test5
        System.out.println("Deleted " + taskService.deleteTask(taskService.findByName(newTasks[5].getName())) + " tasks with name=" + newTasks[5].getName());
        taskService.print();

        //update task test7
        Task updatedTask = taskService.findByName(newTasks[7].getName());
        updatedTask.setStatus(Task.TaskStatus.Done);

        taskService.updateTask(updatedTask);
        System.out.println("Updated task with name=" + newTasks[7].getName());
        taskService.print();





    }
}
