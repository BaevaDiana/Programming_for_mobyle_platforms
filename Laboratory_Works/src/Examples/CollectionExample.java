package Examples;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionExample {
    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();

        // Добавляем элементы
        collection.add("Java");
        collection.add("Python");
        collection.add("C++");

        System.out.println("Коллекция: " + collection);

        // Проверка наличия элемента
        System.out.println("Содержит Java? " + collection.contains("Java"));

        // Удаление элемента
        collection.remove("Python");
        System.out.println("После удаления: " + collection);

        // Размер коллекции
        System.out.println("Размер коллекции: " + collection.size());

        // Очистка коллекции
        collection.clear();
        System.out.println("Пустая? " + collection.isEmpty());
    }
}
