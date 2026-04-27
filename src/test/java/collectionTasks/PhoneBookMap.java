package collectionTasks;

import java.util.HashMap;
import java.util.Map;

public class PhoneBookMap {
    private Map<String, String> phoneBook = new HashMap<>();

    // Добавление
    public void put(String name, String phone) {
        phoneBook.put(name, phone);
        System.out.println("Добавлен: " + name + " -> " + phone);
    }

    // Удаление
    public boolean remove(String name) {
        String removed = phoneBook.remove(name);
        if (removed != null) {
            System.out.println("Удалён: " + name);
            return true;
        }
        System.out.println("Не найден: " + name);
        return false;
    }

    // Поиск
    public String find(String name) {
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            if (entry.getKey().equals(name)) {
                System.out.println("Найден: " + name + " -> " + entry.getValue());
                return entry.getValue();
            }
        }
        System.out.println("Не найден: " + name);
        return null;
    }

    // Вывод
    public void printAll() {
        System.out.println("Телефонная книга:");
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
    }

    //Проверка
    public static void main(String[] args) {
        PhoneBookMap book = new PhoneBookMap();
        book.put("Дима", "111-111");
        book.put("Валентин", "333-333");
        book.printAll();
        book.find("Дима");
        book.remove("Дима");
        book.printAll();
    }
}