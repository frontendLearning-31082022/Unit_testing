package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void evenOddNumber() {
        assertTrue(Main.evenOddNumber(2));
        assertTrue(Main.evenOddNumber(-2));
        assertFalse(Main.evenOddNumber(1));
        assertFalse(Main.evenOddNumber(-1));
        assertTrue(Main.evenOddNumber(0));
    }

    @ParameterizedTest
    @ValueSource(ints = { -100, 2, 1000,24,101 })
    void testMethod(int argument) {
        assertFalse(Main.numberInInterval(argument));
    }

}