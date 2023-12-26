package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class TwoListsCompareTest {

    private TwoListsCompare twoListsCompare;

    @Test
    @DisplayName("testAvrFunction тест расчета среднего значения списка значений double")
    void testAvrFunction() throws NoSuchFieldException, IllegalAccessException {
        twoListsCompare = new TwoListsCompare(null, null);

        Field field = null;
        field = TwoListsCompare.class.getDeclaredField("avrFn");
        field.setAccessible(true);

        Function<List<Double>, Double> avrFn = (Function<List<Double>, Double>) field.get(twoListsCompare);
        Double avr = avrFn.apply(List.of(23.3, 124.3, 21.3, 344.23, 213.2));

        assertEquals(avr, 145.266);
    }


    @Test
    @DisplayName("compareEngine проверка динамического метода сравнения")
    void compareEngine() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List[] data = new List[]{
                List.of(23.3, 124.3, 21.3, 344.23, 213.2),
                List.of(23.3, 124.3, 21.3, 600.3, 213.2)
        };
        twoListsCompare = new TwoListsCompare(data[0], data[1]);

        Method method = TwoListsCompare.class.getDeclaredMethod("compareEngine");
        method.setAccessible(true);
        int comparator = (int) method.invoke(twoListsCompare);

        assertEquals(-1, comparator);
    }

    @Test
    @DisplayName("compare_first_bigger среднее значение первого списка больше")
    void compare_first_bigger() {
        List[] data = new List[]{
                List.of(23.3, 124.3, 21.3, 344.23, 213.2),
                List.of(23.3, 124.3, 21.3, 10.3, 213.2)
        };
        twoListsCompare = new TwoListsCompare(data[0], data[1]);

        assertEquals(twoListsCompare.compare(), "Первый список имеет большее среднее значение");
    }

    @Test
    @DisplayName("compare_first_less среднее значение первого списка меньше")
    void compare_first_less() {
        List[] data = new List[]{
                List.of(23.3, 124.3, 21.3, 1.1, 213.2),
                List.of(23.3, 124.3, 21.3, 600.3, 213.2)
        };
        twoListsCompare = new TwoListsCompare(data[0], data[1]);

        assertEquals(twoListsCompare.compare(), "Второй список имеет большее среднее значение");
    }

    @Test
    @StdIo
    @DisplayName("print проверка печати в консоль")
    void print(StdOut out) {
        List[] data = new List[]{
                List.of(23.3, 124.3, 21.3, 1.1, 213.2),
                List.of(23.3, 124.3, 21.3, 600.3, 213.2)
        };
        twoListsCompare = new TwoListsCompare(data[0], data[1]);
        twoListsCompare.compare();
        twoListsCompare.print();

        assertEquals("Второй список имеет большее среднее значение", out.capturedLines()[0]);
    }

    @Test
    @StdIo
    @DisplayName("print_noCallCompare проверка печати в консоль без предварительного вызова функции сравнения")
    void print_noCallCompare(StdOut out) {
        List[] data = new List[]{
                List.of(23.3, 124.3, 21.3, 1.1, 213.2),
                List.of(23.3, 124.3, 21.3, 600.3, 213.2)
        };
        twoListsCompare = new TwoListsCompare(data[0], data[1]);
        twoListsCompare.print();

        assertEquals("Второй список имеет большее среднее значение", out.capturedLines()[0]);
    }

    @Test
    @DisplayName("compare_listHasNulls проверка работособности при наличии null значений в списке")
    void compare_listHasNulls() {

        List[] data = new List[]{
                new ArrayList<>(Arrays.asList(new Double[10])),
                List.of(23.3, 124.3, 21.3, 10.3, 213.2)
        };
        twoListsCompare = new TwoListsCompare(data[0], data[1]);

        assertEquals(twoListsCompare.compare(), "Второй список имеет большее среднее значение");
    }

    @Test
    @DisplayName("TwoListsCompare_nullArgs проверка создания объекта при передаче взамен списка null")
    void TwoListsCompare_nullArgs() {
        assertDoesNotThrow(() -> {
            twoListsCompare = new TwoListsCompare(null, null);
        });
    }


    @Test
    @DisplayName("compare_notWorkingWhenListEmpty проверка запрета функционирования метода compare при наличии" +
            " пустого списка")
    void compare_notWorkingWhenListEmpty() {
        twoListsCompare = new TwoListsCompare(null, null);
        assertThrows(IllegalArgumentException.class, () -> twoListsCompare.compare());


        String msg = null;
        try {
            twoListsCompare.compare();
        } catch (IllegalArgumentException e) {
            msg = e.getMessage();
        }
        assertEquals("Передан пустой второй список", msg);
    }

    @Test
    @DisplayName("recognizeList")
    void recognizeList() {
        List[] data = new List[]{
                List.of(23.3, 124.3, 21.3, 10.3, 213.2),
                List.of(23.3, 124.3, 21.3, 10.3, 213.2)
        };

        assertDoesNotThrow(() -> twoListsCompare = new TwoListsCompare(data[0],
                data[1], true));
    }

    @Test
    @DisplayName("recognizeList_String проверка конвертации String в списке чисел")
    void recognizeList_String() {
        List[] data = new List[]{
                List.of(23.3, 124.3, "21.3", 10.3, 213.2),
                List.of(23.3, 124.3, 21.3, 10.3, 213.2)
        };

        assertDoesNotThrow(() -> twoListsCompare = new TwoListsCompare(data[0],
                data[1], true));
    }

    @Test
    @DisplayName("recognizeList_Integer проверка конвертации Integer в списке чисел")
    void recognizeList_Integer() {
        List[] data = new List[]{
                List.of(23.3, 124.3, 2, Integer.valueOf(4), 213.2),
                List.of(23.3, 124.3, 21.3, 10.3, 213.2)
        };


        assertDoesNotThrow(() -> twoListsCompare = new TwoListsCompare(data[0],
                data[1], true));
    }

    @Test
    @DisplayName("recognizeList_Float проверка запрета функционирования программы при передача в чсиле списка число " +
            "в формате float")
    void recognizeList_Float() {
        List[] data = new List[]{
                List.of(23.3, 124.3f, 2, 10.3, 213.2),
                List.of(23.3, 124.3, Float.valueOf(43f), 10.3, 213.2)
        };

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    twoListsCompare = new
                            TwoListsCompare(data[0], data[1], true);
                }
        );
        assertEquals("Программа не работает с типом данных float в данной версии", exception.getMessage());
    }

    @Test
    @DisplayName("recognizeList_Long проверка запрета функционирования программы при передача в чсиле списка число " +
            "в формате long")
    void recognizeList_Long() {
        List[] data = new List[]{
                List.of(23.3, 124L, 2, 10.3, 213.2),
                List.of(23.3, 124.3, Long.valueOf(43), 10.3, 213.2)
        };

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    twoListsCompare = new
                            TwoListsCompare(data[0], data[1], true);
                }
        );
        assertEquals("Программа не работает с типом данных long в данной версии", exception.getMessage());
    }

    @Test
    @DisplayName("recognizeList_UncompobilityTypeVarAtList проверка передачи в составе списка переменной, " +
            "имеющий неподдерживаемый программой формат")
    void recognizeList_UncompobilityTypeVarAtList() {
        List[] data = new List[]{
                List.of(23.3, true, 2, 10.3, 213.2),
                List.of(23.3, 124.3, Long.valueOf(43), 10.3, 213.2)
        };

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    twoListsCompare = new
                            TwoListsCompare(data[0], data[1], true);
                }
        );
        assertNotEquals(exception.getMessage().indexOf("Программа не работает с данным типом данных - "), -1);
    }


    @Test
    @DisplayName("compare_NegotiveVals отрицательные числа в списке")
    void compare_NegotiveVals() {
        List[] data = new List[]{
                List.of(-1, 124.3, 2, 9, 213.2),
                List.of(23.3, 124.3, -10, 10.3, 213.2)
        };

        twoListsCompare = new TwoListsCompare(data[0], data[1], true);
        String res = twoListsCompare.compare();
        assertEquals("Второй список имеет большее среднее значение", res);
    }


}