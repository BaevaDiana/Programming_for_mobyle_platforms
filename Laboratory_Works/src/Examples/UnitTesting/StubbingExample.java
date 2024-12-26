package Examples.UnitTesting;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.mockito.Mockito.*;

public class StubbingExample {
    @Test
    void testStubbing() {
        // Создаем мок
        List<String> mockList = mock(List.class);

        // Задаем поведение
        when(mockList.get(0)).thenReturn("Hello");
        when(mockList.get(1)).thenThrow(new RuntimeException("Exception!"));

        // Используем мок
        System.out.println(mockList.get(0)); // Выведет "Hello"

        // Вызов метода с выбрасыванием исключения
        try {
            System.out.println(mockList.get(1)); // Выбросит исключение
        } catch (RuntimeException e) {
            System.out.println(e.getMessage()); // Выведет "Exception!"
        }
    }
}
