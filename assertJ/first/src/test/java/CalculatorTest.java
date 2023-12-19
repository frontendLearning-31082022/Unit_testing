import org.example.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CalculatorTest {

    @Test
    @DisplayName("calculateDiscount" +
            " оптимальные исходные данные")
    void calculateDiscount() {
        assertThat(Calculator.calculateDiscount(200, 50)).isEqualTo(100);
    }

    @Test
    @DisplayName("calculateDiscount" +
            " отрицательная сумма покупки")
    void calculateDiscount_negotive_purchaseAmount() {
        assertThatThrownBy(() ->
                Calculator.calculateDiscount(-29, 50)
        ).isInstanceOf(ArithmeticException.class);
    }

    @Test
    @DisplayName("calculateDiscount" +
            " отрицательный процент скидки")
    void calculateDiscount_negotive_discountAmount() {
        assertThatThrownBy(() ->
                Calculator.calculateDiscount(410, -50)
        ).isInstanceOf(ArithmeticException.class);
    }
}