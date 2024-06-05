package com.walmart.test.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestServiceTest {

    private TestService testService;

    @BeforeEach
    public void setUp() {
        testService = new TestService();
    }

    @Test
    public void testCompareArrays() {
        List<String> arrayA = Arrays.asList("Pollo", "Chile", "Aguacate", "Limon");
        List<String> arrayB = Arrays.asList("Tomate", "Chile", "Mochila", "Melon", "Limon", "Sandia", "Chile");

        List<String> expectedResponse = Arrays.asList("Pollo = 1 veces", "Chile = 3 veces", "Aguacate = 1 veces", "Limon = 2 veces", "Tomate = 1 veces", "Mochila = 1 veces", "Melon = 1 veces", "Sandia = 1 veces");

        List<String> result = testService.compareArrays(arrayA, arrayB);

        // Ordenar ambas listas antes de la comparación
        Collections.sort(expectedResponse);
        Collections.sort(result);

        assertEquals(expectedResponse.size(), result.size());
        assertEquals(expectedResponse, result);
    }

    @Test
    public void testCompareArrays_EmptyArrays() {
        List<String> arrayA = Arrays.asList();
        List<String> arrayB = Arrays.asList();

        List<String> expectedResponse = Arrays.asList();

        List<String> result = testService.compareArrays(arrayA, arrayB);

        assertEquals(expectedResponse, result);
    }

    @Test
    public void testCompareArraysFilterByFrequency() {
        List<String> arrayA = Arrays.asList("Pollo", "Chile", "Aguacate", "Limon");
        List<String> arrayB = Arrays.asList("Tomate", "Chile", "Mochila", "Melon", "Limon", "Sandia", "Chile");

        List<String> expectedResponse = Arrays.asList("Chile = 3 veces", "Limon = 2 veces");

        List<String> result = testService.compareArraysFilterByFrequency(arrayA, arrayB, 2);

        // Ordenar ambas listas antes de la comparación
        Collections.sort(expectedResponse);
        Collections.sort(result);

        assertEquals(expectedResponse.size(), result.size());
        assertEquals(expectedResponse, result);
    }

    @Test
    public void testCompareArraysFilterByFrequency_NoMatches() {
        List<String> arrayA = Arrays.asList("Pollo", "Chile", "Aguacate", "Limon");
        List<String> arrayB = Arrays.asList("Tomate", "Mochila", "Melon", "Sandia");

        List<String> expectedResponse = Arrays.asList();

        List<String> result = testService.compareArraysFilterByFrequency(arrayA, arrayB, 2);

        assertEquals(expectedResponse, result);
    }

    @Test
    public void testCompareArraysFilterByFrequency_EmptyArrays() {
        List<String> arrayA = Arrays.asList();
        List<String> arrayB = Arrays.asList();

        List<String> expectedResponse = Arrays.asList();

        List<String> result = testService.compareArraysFilterByFrequency(arrayA, arrayB, 2);

        assertEquals(expectedResponse, result);
    }
}