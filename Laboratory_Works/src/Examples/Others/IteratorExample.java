package Examples.Others;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorExample {
    public static void main(String[] args) {


        // Создаем коллекцию
        ArrayList<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        list.add("C++");
        list.add("JavaScript");

        // Получаем Iterator из коллекции
        Iterator<String> iterator = list.iterator();

        // Перебираем элементы коллекции с помощью Iterator
        System.out.println("Элементы коллекции:");
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println(element);

            // Удаляем элемент "C++" во время итерации
            if (element.equals("C++")) {
                iterator.remove();
            }
        }

        // Проверяем результат
        System.out.println("После удаления: " + list);
    }
}
