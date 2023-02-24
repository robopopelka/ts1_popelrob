package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PopelrobTest {

    @Test
    void factorialRek() {
        assertEquals(120, Popelrob.factorialRek(5));
    }

    @Test
    void factorialIter() {
        assertEquals(120, Popelrob.factorialIter(5));
    }
}