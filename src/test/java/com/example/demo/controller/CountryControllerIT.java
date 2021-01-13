package com.example.demo.controller;
import com.example.demo.dto.CountryRequest;
import com.example.demo.dto.CountryUpdate;
import com.example.demo.mapper.CountryMapper;
import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;
import com.example.demo.service.CountryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CountryController.class)
public class CountryControllerIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CountryService countryService;
    @MockBean
    private CountryMapper countryMapper;

    @Test
    public void createCountryHappyFlow() throws Exception {
        //arrange
        CountryRequest request = new CountryRequest("S.U.A");

        when(countryService.create(any())).thenReturn(new Country(1, "S.U.A"));

        //act + assert
        mockMvc.perform(post("/countries/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(request.getName()));
    }

    @Test
    public void getCountryHappyFlow() throws Exception {
        //arrange
        Country country = new Country(1,"S.U.A");

        when(countryService.get()).thenReturn(
                Arrays.asList(new Country(1, "S.U.A"))
        );

        //act + assert
        mockMvc.perform(get("/countries/get")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(Arrays.asList(new Country(1, "S.U.A")))))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$[0].name").value(country.getName()));
    }

    @Test
    public void updateCountryHappyFlow() throws Exception {
        //arrange
        CountryUpdate countryUpdate = new CountryUpdate(3, "Polonia");

        when(countryService.update(any())).thenReturn(new Country(3, "Polonia"));

        //act + assert
        mockMvc.perform(put("/countries/update")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(countryUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(countryUpdate.getName()));
    }

    @Test
    public void deleteCountryHappyFlow() throws Exception {
        //arrange
        Country country = new Country(3, "Polonia");
        Optional<Country> result = Optional.of(country);


        when(countryService.delete(3)).thenReturn(result);

        //act + assert
        mockMvc.perform(delete("/countries/delete?id=" + country.getCountryId())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(country)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(country.getName()));
    }


}
