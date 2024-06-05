package com.walmart.test.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;


@Service
public class TestService {

    public List<String> compareArrays(List<String> arrayA, List<String> arrayB) {
        // Crear un mapa para contar la frecuencia de elementos en ambos arrays
        Map<String, Integer> frequencyMap = new HashMap<>();

        // Contar la frecuencia de elementos en el Array A
        for (String element : arrayA) {
            frequencyMap.put(element, frequencyMap.getOrDefault(element, 0) + 1);
        }

        // Contar la frecuencia de elementos en el Array B
        for (String element : arrayB) {
            frequencyMap.put(element, frequencyMap.getOrDefault(element, 0) + 1);
        }

        // Crear una lista para almacenar el resultado
        List<String> arrayC = new ArrayList<>();

        // Construir la lista de resultado
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            arrayC.add(entry.getKey() + " = " + entry.getValue() + " veces");
        }

        return arrayC;
    }
    
    public List<String> compareArraysFilterByFrequency(List<String> arrayA, List<String> arrayB, int minFrequency) {
        // Crear un mapa para contar la frecuencia de elementos en ambos arrays
        Map<String, Integer> frequencyMap = new HashMap<>();

        // Contar la frecuencia de elementos en el Array A
        for (String element : arrayA) {
            frequencyMap.put(element, frequencyMap.getOrDefault(element, 0) + 1);
        }

        // Contar la frecuencia de elementos en el Array B
        for (String element : arrayB) {
            frequencyMap.put(element, frequencyMap.getOrDefault(element, 0) + 1);
        }

        // Crear una lista para almacenar el resultado
        List<String> arrayC = new ArrayList<>();

        // Filtrar y construir la lista de resultado solo con elementos que se repiten 2 o más veces
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() >= minFrequency) {
                arrayC.add(entry.getKey() + " = " + entry.getValue() + " veces");
            }
        }

        return arrayC;
    }
}