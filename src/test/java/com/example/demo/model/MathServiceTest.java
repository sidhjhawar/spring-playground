package com.example.demo.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MathServiceTest {
    MathService mathService;

    @Before
    public void setUp() throws Exception {
        mathService = new MathService();
    }

    @Test
    public void sum() {
        assertEquals(15, mathService.sum(10, 5));
    }

    @Test
    public void subtract() {
        assertEquals(5, mathService.subtract(10, 5));
    }

    @Test
    public void multiply() {
        assertEquals(50, mathService.multiply(10, 5));
    }

    @Test
    public void divide() {
        assertEquals(2, mathService.divide(10, 5));
    }
}