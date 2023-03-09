package hw2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calc = new Calculator();
    @Test
    void add() {
        Assertions.assertEquals(5, calc.add(2,3));
    }

    @Test
    void subtract() {
        Assertions.assertEquals(-1, calc.subtract(2,3));
    }

    @Test
    void multiply() {
        Assertions.assertEquals(6, calc.multiply(2,3));
    }

    @Test
    void divide() {
        Assertions.assertEquals(5, calc.divide(15,3));
    }

    @Test
    void divideByZero() {
        Assertions.assertThrows(java.lang.ArithmeticException.class, ()-> {
            calc.divide(2, 0);}
        );
    }
}