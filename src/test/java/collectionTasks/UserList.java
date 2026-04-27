package collectionTasks;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private List<String> users = new ArrayList<>();

    // Добавление
    public void add(String name) {
        users.add(name);
        System.out.println("Добавлен: " + name);
    }

    // Удаление
    public boolean remove(String name) {
        boolean removed = users.remove(name);
        if (removed) {
            System.out.println("Удалён: " + name);
        }
        return removed;
    }

    // Поиск
    public boolean find(String name) {
        for (String user : users) {
            if (user.equals(name)) {
                System.out.println("Найден: " + name);
                return true;
            }
        }
        System.out.println("Не найден: " + name);
        return false;
    }

    // Вывод всех элементов
    public void printAll() {
        System.out.println("Список пользователей:");
        for (String user : users) {
            System.out.println("  - " + user);
        }
    }

    //Проверка
    public static void main(String[] args) {
        UserList list = new UserList();
        list.add("Дима");
        list.add("Валентин");
        list.printAll();
        list.find("Дима");
        list.remove("Дима");
        list.printAll();
    }
}