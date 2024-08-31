package toDoList;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ToDoApp {
    private final ToDoList toDoList=new ToDoList();
    private final Scanner sc=new Scanner(System.in);

    public static void main(String[] args) {
        ToDoApp app = new ToDoApp();
        app.run();
    }

    public void run() {
        String command;
        do {
            printMenu();
            command = sc.nextLine();
            switch (command) {
                case "1" -> addTask();
                case "2" -> editTask();
                case "3" -> viewTask();
                case "4" -> removeTask();
                case "5" -> showTasks();
                case "6" -> showCompletedTasks();
                case "7" -> showIncompleteTasks();
                case "0" -> System.out.println("Выход...");
                default -> System.out.println("Неверная команда. Попробуйте снова");
            }
        } while (!command.equals("0"));
    }

    private void printMenu() {
        System.out.println("Введите код операции: 1-Добавить задачу, 2-Редактировать задачу, 3-Посмотреть задачу, 4-Удалить задачу, 5-Посмотреть все задачи, \n6-Просмотреть все завершённые задачи, 7-Просмотреть все незавершённые задачи, 0-Выход");
    }

    private void addTask() {
        System.out.println("Введите название задачи");
        String taskTitle = sc.nextLine();
        System.out.println("Введите описание задачи");
        String taskDescription = sc.nextLine();
        Task task = new Task(taskTitle,taskDescription);
        toDoList.addTask(task);
        System.out.println("Задача добавлена");
    }

    private void removeTask() {
        try {
            System.out.println("Введите id задачи:");
            int taskId = Integer.parseInt(sc.nextLine());
            toDoList.removeTask(taskId);
            System.out.println("Задача удалена");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Введите корректный id");
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    private void editTask(){
        try {
            System.out.println("Введите id задачи:");
            int taskId = Integer.parseInt(sc.nextLine());
            if (!toDoList.taskExists(taskId)){
                System.out.println("Задача с таким id не существует");
                return;
            }
            System.out.println("Выберите код операции: 1-Редактировать название, 2-Редактировать описание, 3-Изменить статус задачи");
            String command = sc.nextLine();
            switch (command) {
                case "1" -> {
                    System.out.println("Введите новое название задачи:");
                    String newTitle = sc.nextLine();
                    toDoList.editTaskTitle(taskId, newTitle);
                    System.out.println("Название задачи обновлено");
                }
                case "2" -> {
                    System.out.println("Введите новое описание задачи:");
                    String newDescription = sc.nextLine();
                    toDoList.editTaskDescription(taskId, newDescription);
                    System.out.println("Описание задачи обновлено");
                }
                case "3" -> {
                    toDoList.changeTaskStatus(taskId);
                    System.out.println("Статус задачи изменён");
                }
                default -> System.out.println("Неверная команда. Попробуйте снова");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Введите корректный id");
        }
    }

    private void viewTask() {
        try {
            System.out.println("Введите id задачи:");
            int taskId = Integer.parseInt(sc.nextLine());
            Task task = toDoList.getTask(taskId);
            System.out.println(task);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Введите корректный id");
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showTasks(){
        List<Task> tasks = toDoList.getTasksAsList();
        if (tasks.isEmpty()) {
            System.out.println("Нет задач");
        } else {
            tasks.forEach(System.out::println);
        }
    }

    private void showCompletedTasks(){
        List<Task> tasks = toDoList.getFilteredTasksAsList(true);
        if (tasks.isEmpty()) {
            System.out.println("Нет завершённых задач");
        } else {
            tasks.forEach(System.out::println);
        }
    }

    private void showIncompleteTasks(){
        List<Task> tasks = toDoList.getFilteredTasksAsList(false);
        if (tasks.isEmpty()) {
            System.out.println("Нет незавершённых задач");
        } else {
            tasks.forEach(System.out::println);
        }
    }
}
