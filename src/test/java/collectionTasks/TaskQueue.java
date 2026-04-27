package collectionTasks;

import java.util.LinkedList;
import java.util.Queue;

public class TaskQueue {
    private Queue<String> tasks = new LinkedList<>();

    // Добавление в конец очереди
    public void add(String task) {
        tasks.offer(task);
        System.out.println("Добавлена задача: " + task);
    }

    // Удаление из начала очереди
    public String remove() {
        String task = tasks.poll();
        if (task != null) {
            System.out.println("Выполнена задача: " + task);
        } else {
            System.out.println("Очередь пуста");
        }
        return task;
    }

    // Поиск
    public boolean find(String taskName) {
        if (tasks.isEmpty()) {
            System.out.println("Очередь пуста");
            return false;
        }

        Queue<String> tempQueue = new LinkedList<>(tasks);
        boolean found = false;

        do {
            String current = tempQueue.poll();
            if (current != null && current.equals(taskName)) {
                found = true;
            }
        } while (!tempQueue.isEmpty() && !found);

        if (found) {
            System.out.println("Найдена задача: " + taskName);
        } else {
            System.out.println("Не найдена: " + taskName);
        }
        return found;
    }

    // Вывод
    public void printAll() {
        System.out.println("Очередь задач:");
        for (String task : tasks) {
            System.out.println("  - " + task);
        }
    }

    //Проверка
    public static void main(String[] args) {
        TaskQueue queue = new TaskQueue();
        queue.add("Открыть браузер");
        queue.add("Заполнить форму");
        queue.printAll();
        queue.find("Заполнить форму");
        queue.remove();
        queue.printAll();
    }
}