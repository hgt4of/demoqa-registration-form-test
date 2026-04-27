package collectionTasks;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UniqueIdSet {
    private Set<Integer> ids = new HashSet<>();

    // Добавление
    public void add(int id) {
        if (ids.add(id)) {
            System.out.println("Добавлен ID: " + id);
        } else {
            System.out.println("ID уже есть: " + id);
        }
    }

    // Удаление
    public boolean remove(int id) {
        boolean removed = ids.remove(id);
        if (removed) {
            System.out.println("Удалён ID: " + id);
        }
        return removed;
    }

    // Поиск
    public boolean find(int id) {
        Iterator<Integer> iterator = ids.iterator();
        while (iterator.hasNext()) {
            Integer current = iterator.next();
            if (current.equals(id)) {
                System.out.println("Найден ID: " + id);
                return true;
            }
        }
        System.out.println("Не найден ID: " + id);
        return false;
    }

    // Вывод
    public void printAll() {
        System.out.println("Все ID:");
        for (Integer id : ids) {
            System.out.println("  - " + id);
        }
    }

    //Проверка
    public static void main(String[] args) {
        UniqueIdSet set = new UniqueIdSet();
        set.add(100);
        set.add(200);
        set.add(100);
        set.printAll();
        set.find(200);
        set.remove(200);
        set.printAll();
    }
}