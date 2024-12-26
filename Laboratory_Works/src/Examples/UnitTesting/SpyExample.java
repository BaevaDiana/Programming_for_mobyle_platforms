package Examples.UnitTesting;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class SpyExample {
    @Test
    void testSpy() {
        // Создаем реальный объект
        List<String> realList = new ArrayList<>();

        // Создаем шпиона
        List<String> spyList = Mockito.spy(realList);

        // Задаем поведение для метода
        when(spyList.size()).thenReturn(100);

        // Используем шпиона
        spyList.add("Hello");
        System.out.println(spyList.get(0)); // Выведет "Hello"
        System.out.println(spyList.size()); // Выведет 100 (переопределено)

        // Проверяем вызов метода
        verify(spyList).add("Hello");
        verify(spyList).size();
    }
}
