package toDoList;

import java.util.*;
import java.util.stream.Collectors;

public class ToDoList {
    private final Map<Integer,Task> tasks=new HashMap<>();


    public boolean taskExists(int taskId){
        return tasks.containsKey(taskId);
    }

    public void addTask(Task task){
        if (task == null) {
            throw new IllegalArgumentException("Задача не может быть null");
        }
        tasks.put(task.getId(), task);
    }

    public void removeTask(int taskId){
        if (tasks.remove(taskId)==null)
            throw new NoSuchElementException("Задача с таким id не существует");
    }

    public Task getTask(int taskId){
        Task task = tasks.get(taskId);
        if (task==null)
            throw new NoSuchElementException("Задача с таким id не существует");
        return task;
    }

    public void editTaskTitle(int taskId, String newTitle){
        Task task = getTask(taskId);
        task.setTitle(newTitle);
    }

    public void editTaskDescription(int taskId, String newDescription){
        Task task = getTask(taskId);
        task.setDescription(newDescription);
    }

    public void changeTaskStatus(int taskId){
        Task task = getTask(taskId);
        task.setCompleted(!task.isCompleted());
    }

    public List<Task> getTasksAsList(){
        return new ArrayList<>(tasks.values());
    }

    public List<Task> getFilteredTasksAsList(boolean isCompleted){
        return tasks.values().stream()
                .filter(t->t.isCompleted()==isCompleted)
                .collect(Collectors.toList());
    }

}
