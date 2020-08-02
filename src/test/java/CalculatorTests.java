import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CalculatorTests {

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "1, 1, 2",
            "0, 1, 1",
            "0, 0, 0"}
    )
    void add_given_2numbers_returna_sum(double number1, double number2, double rezult) {
        double sum = Calculator.add(number1, number2);

        assertThat(sum).isEqualTo(rezult);
    }
}
