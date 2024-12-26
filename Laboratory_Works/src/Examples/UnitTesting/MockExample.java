package Examples.UnitTesting;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;

public class MockExample {
    @Test
    void testMock() {
        // Создаем мок-объект
        List<String> mockList = Mockito.mock(List.class);

        // Устанавливаем поведение
        when(mockList.size()).thenReturn(5);

        // Используем мок
        System.out.println(mockList.size()); // Выведет 5

        // Проверяем вызов метода
        verify(mockList).size();
    }
}

