package hw2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTestParametrized {
    Calculator calc = new Calculator();

    @ParameterizedTest(name = "{0} plus {1} should be equal to {2}")
    @CsvSource({"1, 2, 3", "2, 3, 5"})
    void add_A_B_ret_C(int a, int b, int c) {
        int expected = c;
        int actual = calc.add(a, b);
        Assertions.assertEquals(expected, actual);
    }
}