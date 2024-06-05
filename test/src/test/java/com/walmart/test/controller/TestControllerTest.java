package com.walmart.test.controller;

import com.walmart.test.service.TestService;
import com.walmart.test.vo.ArrayRequest;
import com.walmart.test.vo.ArrayResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestControllerTest {

    @Mock
    private TestService testService;

    @InjectMocks
    private TestController testController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(testController).build();
    }

    @Test
    public void testSearchArray() throws Exception {
        List<String> arrayA = Arrays.asList("Pollo", "Chile", "Aguacate", "Limon");
        List<String> arrayB = Arrays.asList("Tomate", "Chile", "Mochila", "Melon", "Limon", "Sandia", "Chile");
        List<String> expectedResponse = Arrays.asList("Pollo = 1 veces", "Chile = 3 veces", "Aguacate = 1 veces", "Limon = 2 veces", "Tomate = 1 veces", "Mochila = 1 veces", "Melon = 1 veces", "Sandia = 1 veces");

        ArrayRequest request = new ArrayRequest();
        request.setArrayA(arrayA);
        request.setArrayB(arrayB);

        ArrayResponse response = new ArrayResponse();
        response.setResults(expectedResponse);

        when(testService.compareArrays(arrayA, arrayB)).thenReturn(expectedResponse);

        mockMvc.perform(post("/api/searchArrayFull")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"arrayA\": [\"Pollo\", \"Chile\", \"Aguacate\", \"Limon\"], \"arrayB\": [\"Tomate\", \"Chile\", \"Mochila\", \"Melon\", \"Limon\", \"Sandia\", \"Chile\"]}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"results\":[\"Pollo = 1 veces\",\"Chile = 3 veces\",\"Aguacate = 1 veces\",\"Limon = 2 veces\",\"Tomate = 1 veces\",\"Mochila = 1 veces\",\"Melon = 1 veces\",\"Sandia = 1 veces\"]}"));
    }

    @Test
    public void testSearchArray_EmptyArrays() throws Exception {
        mockMvc.perform(post("/api/searchArrayFull")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"arrayA\": [], \"arrayB\": []}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSearchArray_NullArrays() throws Exception {
        mockMvc.perform(post("/api/searchArrayFull")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"arrayA\": null, \"arrayB\": null}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSearchArray2() throws Exception {
        List<String> arrayA = Arrays.asList("Pollo", "Chile", "Aguacate", "Limon");
        List<String> arrayB = Arrays.asList("Tomate", "Chile", "Mochila", "Melon", "Limon", "Sandia", "Chile");
        List<String> expectedResponse = Arrays.asList("Chile = 3 veces", "Limon = 2 veces");

        ArrayRequest request = new ArrayRequest();
        request.setArrayA(arrayA);
        request.setArrayB(arrayB);

        ArrayResponse response = new ArrayResponse();
        response.setResults(expectedResponse);

        when(testService.compareArraysFilterByFrequency(arrayA, arrayB, 2)).thenReturn(expectedResponse);

        mockMvc.perform(post("/api/searchArray")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"arrayA\": [\"Pollo\", \"Chile\", \"Aguacate\", \"Limon\"], \"arrayB\": [\"Tomate\", \"Chile\", \"Mochila\", \"Melon\", \"Limon\", \"Sandia\", \"Chile\"]}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"results\":[\"Chile = 3 veces\",\"Limon = 2 veces\"]}"));
    }

    @Test
    public void testSearchArray2_EmptyArrays() throws Exception {
        mockMvc.perform(post("/api/searchArray")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"arrayA\": [], \"arrayB\": []}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSearchArray2_NullArrays() throws Exception {
        mockMvc.perform(post("/api/searchArray")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"arrayA\": null, \"arrayB\": null}"))
                .andExpect(status().isBadRequest());
    }
}