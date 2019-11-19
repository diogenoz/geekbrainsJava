package com.geek.dz3;


import com.geek.dz3.entities.FindPatternTask;
import com.geek.dz3.entities.Task;
import com.geek.dz3.exceptions.MyArrayDataException;
import com.geek.dz3.exceptions.MyArraySizeException;
import com.geek.dz3.services.TaskService;
import com.geek.dz5.Apple;
import com.geek.dz5.Box;
import com.geek.dz5.Orange;
import com.geek.dz6.MyUniqueStringHelper;
import com.geek.dz6.PhoneDictionary;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.BrokenBarrierException;

public class Main {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        int taskCount = 10;
        UUID[] newTaskIds = new UUID[taskCount];
        TaskService taskService = new TaskService();

        taskService.print();
        for (int i = 0; i < taskCount; i++) {
            Task newTask = new Task("Test" + i, "Den" + i, "Ivan" + i, "Test11");
            if (i == 1 || i == 6 || i == 7) {
                newTask.setStatus(Task.TaskStatus.InProgress);
            }
            if (i == 2 || i == 5 || i == 8 || i == 9) {
                newTask.setStatus(Task.TaskStatus.Done);
            }
            taskService.addTask(newTask);
            newTaskIds[i] = newTask.getId();
        }
        taskService.print();
        taskService.addTask(new Task("Test" + taskCount + 1, "Den11", "Ivan0", "Test11"));

        // dz#8
        System.out.println("----------------dz8--------------------");
        ArrayList inProgressTasks = taskService.findByStatus(Task.TaskStatus.InProgress);
        taskService.print(inProgressTasks, "inProgressTasks");
        ArrayList doneTasks = taskService.findByStatus(Task.TaskStatus.Done);
        taskService.print(doneTasks, "doneTasks");

        boolean isTask1Exists = taskService.isTaskExists(newTaskIds[3]);
        System.out.println("Excpected isTask1Exists:true, fact:" + isTask1Exists);

        boolean isTask2Exists = taskService.isTaskExists(UUID.randomUUID());
        System.out.println("Excpected isTask2Exists:false, fact:" + isTask2Exists);

        taskService.print(taskService.getSortedTasks(), "getSortedTasks");

        System.out.println("Excpected inProgress taskCount:3, fact:" + taskService.computeTaskCountWithStatus(Task.TaskStatus.InProgress));
        System.out.println("Excpected done taskCount:4, fact:" + taskService.computeTaskCountWithStatus(Task.TaskStatus.Done));


        // delete task by random Id
        try {
            System.out.println("Deleted " + taskService.deleteTask(UUID.randomUUID()) + " tasks with random id");
        } catch (RuntimeException e) {
            System.out.println("Task with random id don't deleted");
        }

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
        System.out.println("Deleted one " + taskService.deleteTaskByPattern(new FindPatternTask(null, null, null, null, Task.TaskStatus.Open)) + " tasks with status=Open");
        taskService.print();

        // update task with id = 4
        System.out.println("Update " + taskService.updateTask(new FindPatternTask(newTaskIds[4]), new Task("TesUpdate", "Den11", "Ivan0", "Test11")) + " tasks with id=4");
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


        //dz#5
        //first fill boxes
        Box<Apple> appleBox = new Box<Apple>();
        for (int i = 0; i < 48; i++) {
            appleBox.addFruit(new Apple());
        }
        Box<Orange> orangeBox = new Box<Orange>();
        for (int i = 0; i < 20; i++) {
            orangeBox.addFruit(new Orange());
        }
        //d.
        System.out.println("Excpected weight of appleBox=48, fact:" + appleBox.getWeight());
        System.out.println("Excpected count of appleBox=48, fact:" + appleBox.getCount());
        System.out.println("Excpected weight of orangeBox=30, fact:" + orangeBox.getWeight());
        System.out.println("Excpected count of orangeBox=20, fact:" + orangeBox.getCount());

        //e.
        System.out.println("Excpected compare weight of appleBox&orangeBox: false, fact:" + appleBox.compare(orangeBox));

        //upfill orangeBox
        for (int i = 0; i < 12; i++) {
            orangeBox.addFruit(new Orange());
        }
        System.out.println("upfill orangeBox...");
        System.out.println("Excpected compare weight of appleBox&orangeBox: true, fact:" + appleBox.compare(orangeBox));

        //f.
        Box<Orange> orangeBox2 = new Box<Orange>();
        for (int i = 0; i < 20; i++) {
            orangeBox2.addFruit(new Orange());
        }
        System.out.println("orangeBox before pour:" + orangeBox);
        System.out.println("orangeBox2 before pour:" + orangeBox2);
        orangeBox.pourToOtherBox(orangeBox2);
        System.out.println("orangeBox after pour:" + orangeBox);
        System.out.println("orangeBox2 after pour:" + orangeBox2);

        // dz#6
        // dz#6 part 1
        ArrayList<String> stringArray = new ArrayList<String>(Arrays.asList("apple", "orange", "mango", "banan", "tomato", "potato"
                , "apple", "mango", "apple", "apple", "mango", "tomato", "table", "pencil", "table", "monitor", "steel", "fish"));
        System.out.println("Unique words:" + MyUniqueStringHelper.getUniqueElements(stringArray));
        System.out.println("Unique words frequence:" + MyUniqueStringHelper.getComputedWordsCount(stringArray));

        // dz#6 part 2
        PhoneDictionary phoneDictionary = new PhoneDictionary();
        phoneDictionary.add("Lenin", "111111111");
        phoneDictionary.add("Lenin", "111111111");
        phoneDictionary.add("Stalin", "2222222222");
        phoneDictionary.add("Lenin", "111111112");
        phoneDictionary.add("Lenin", "111111113");
        phoneDictionary.add("Stalin", "2222222223");
        phoneDictionary.add("Trozky", "3333333333");
        phoneDictionary.add("Gorky", "4444444444");

        System.out.println("Lenin's phones: " + phoneDictionary.get("Lenin"));
        System.out.println("Stalin's phones: " + phoneDictionary.get("Stalin"));
        System.out.println("Trozky's phones: " + phoneDictionary.get("Trozky"));

        //dz#7
        /*final int CARS_COUNT = 4;
        CyclicBarrier cb = new CyclicBarrier(CARS_COUNT + 1);
        Car.winnerLock = new ReentrantLock();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(Math.round(CARS_COUNT / 2)), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cb);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        cb.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        cb.await();
        cb.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
         */

        // dz#8
        ArrayList tasks;
        String outDir = "." + File.separator + "src" + File.separator + "out";
        System.out.println(outDir);
        String outFile;
        //JSON library Json-simple
        outFile = outDir + File.separator + "tasks.json";
        System.out.println(outFile);
        taskService.exportToJsonFile(new File(outFile));

        tasks = taskService.importFromJsonFile(new File(outFile));
        taskService.print(tasks, "Import From Json Tasks");

        //Serialize
        outFile = outDir + File.separator + "tasks.dat";
        taskService.exportToFile(new File(outFile));

        tasks = taskService.importFromFile(new File(outFile));
        taskService.print(tasks, "Imported Serialized Tasks");

        //CSV library Super CSV
        outFile = outDir + File.separator + "tasks.csv";
        taskService.exportToCsvFile(new File(outFile));

        tasks = taskService.importFromCsvFile(new File(outFile));
        taskService.print(tasks, "Imported Csv Tasks");

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
