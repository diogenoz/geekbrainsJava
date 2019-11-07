package com.geek.dz3;


import com.geek.dz3.entities.FindPatternTask;
import com.geek.dz3.entities.Task;
import com.geek.dz3.exceptions.MyArrayDataException;
import com.geek.dz3.exceptions.MyArraySizeException;
import com.geek.dz3.services.TaskService;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        int taskCount = 10;
        UUID[] newTaskIds = new UUID[taskCount];
        TaskService taskService = new TaskService(taskCount);
        taskService.print();
        for (int i = 0; i < taskCount; i++) {
            Task newTask = new Task("Test" + i, "Den" + i, "Ivan" + i, "Test11");
            taskService.addTask(newTask);
            newTaskIds[i] = newTask.getId();
        }
        taskService.print();
        // should print error
        taskService.addTask(new Task("Test" + taskCount + 1, "Den11", "Ivan0", "Test11"));

        // delete task by random Id
        System.out.println("Deleted " + taskService.deleteTask(UUID.randomUUID()) + " tasks with random id");
        taskService.print();

        // delete task test3
        System.out.println("Deleted " + taskService.deleteTask(newTaskIds[2]) + " tasks with id=" + newTaskIds[2]);
        taskService.print();

        // delete task with name Test5
        System.out.println("Deleted " + taskService.deleteTaskByName("Test5") + " tasks with name=Test5");
        taskService.print();

        // delete task with owner Den7
        System.out.println("Deleted " + taskService.deleteTaskByOwner("Den7") + " tasks with owner=Den7");
        taskService.print();

        // delete task with status Open
        System.out.println("Deleted " + taskService.deleteTaskByPattern(new FindPatternTask(null, null, null, null, Task.TaskStatus.Open)) + " tasks with status=Open");
        taskService.print();

        //dz#4
        // Norm array
        String[][] srcArray = new String[][]{{"1", "2", "3", "4"}, {"5", "6", "7", "8"}, {"9", "10", "11", "12"}, {"13", "14", "15", "16"}};
        int sum = sumQuartArrayWithPrintExceptions(srcArray);
        System.out.println("Excpected Sum of array:136, fact:" + sum);

        // array with incorrect cols
        String[][] srcArray1 = new String[][]{{"1", "2", "3", "4"}, {"5", "6", "7", "8"}, {"9", "10", "11", "12"}};
        int sum1 = sumQuartArrayWithPrintExceptions(srcArray1);
        System.out.println("Excpected Sum of array1:78, fact:" + sum1);

        // array with incorrect rows
        String[][] srcArray2 = new String[][]{{"1", "2", "3", "4"}, {"5", "6", "7", "8"}, {"9", "10", "11"}, {"13", "14", "15"}};
        int sum2 = sumQuartArrayWithPrintExceptions(srcArray2);
        System.out.println("Excpected Sum of array2:108, fact:" + sum2);

        // array with incorrect data
        String[][] srcArray3 = new String[][]{{"1", "2", "3", "4"}, {"5", "6", "7test", "8"}, {"9", "10", "11", "12"}, {"13", "14", "15", "16"}};
        int sum3 = sumQuartArrayWithPrintExceptions(srcArray3);
        System.out.println("Excpected Sum of array3:0, fact:" + sum3);

    }

    protected static int sumQuartArrayWithPrintExceptions(String[][] srcArray) {
        int sum = 0;
        try {
            sum = sumQuarterArray(srcArray);
        } catch (MyArraySizeException e) {
            System.out.println("Incorrect array dimension");
        } catch (MyArrayDataException e) {
            System.out.println("Incorrect data format, col:" + e.getCol() + ", row:" + e.getRow());
        }
        return sum;
    }

    //dz#4
    protected static int sumQuarterArray(String[][] srcArray) throws MyArrayDataException, MyArraySizeException {
        boolean isIncorrectColsDimension = false;
        boolean isIncorrectRowsDimension = false;
        if (srcArray.length != 4) {
            throw new MyArraySizeException("Входящий массив имеет размерность, отличную от 4х4");
        } else {
            for (int i = 0; i < srcArray.length; i++) {
                if (srcArray[i].length != 4) {
                    throw new MyArraySizeException("Входящий массив имеет размерность, отличную от 4х4");
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < srcArray.length; i++) {
            for (int j = 0; j < srcArray[i].length; j++) {
                try {
                    sum += Integer.parseInt(srcArray[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(e.getMessage(), i, j);
                }
            }
        }
        return sum;
    }
}
