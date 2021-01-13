package com.example.demo.controller;
import com.example.demo.dto.CountryRequest;
import com.example.demo.dto.CountryUpdate;
import com.example.demo.dto.TouristRequest;
import com.example.demo.dto.TouristUpdate;
import com.example.demo.mapper.CountryMapper;
import com.example.demo.mapper.TouristMapper;
import com.example.demo.model.Country;
import com.example.demo.model.Tourist;
import com.example.demo.service.CountryService;
import com.example.demo.service.TouristService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TouristController.class)
public class TouristControllerIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private TouristService touristService;
    @MockBean
    private TouristMapper touristMapper;

    @Test
    public void createCountryHappyFlow() throws Exception {
        //arrange
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = format.parse("1999-10-23");
        TouristRequest touristRequest = new TouristRequest("Ion", "Popa", birthDate);
        Tourist tourist = new Tourist(1,"Ion", "Popa", birthDate);
        when(touristService.create(any())).thenReturn(tourist);

        //act + assert
        mockMvc.perform(post("/tourists/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(touristRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value(touristRequest.getFirstName()));
    }


    @Test
    public void getTouristHappyFlow() throws Exception {
        //arrange
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = format.parse("1999-10-23");
        Tourist tourist = new Tourist("Ion", "Popa", birthDate);

        when(touristService.get()).thenReturn(
                Arrays.asList(new Tourist("Ion", "Popa", birthDate))
        );

        //act + assert
        mockMvc.perform(get("/tourists/get")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new Tourist("Ion", "Popa", birthDate))))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$[0].firstName").value(tourist.getFirstName()));
    }

    @Test
    public void updateTouristHappyFlow() throws Exception {
        //arrange
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = format.parse("1999-10-23");
        TouristUpdate touristUpdate = new TouristUpdate(1,"Ion", "Popa", birthDate);

        when(touristService.update(any())).thenReturn(new Tourist(1,"Ion", "Popa", birthDate));

        //act + assert
        mockMvc.perform(put("/tourists/update")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(touristUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(touristUpdate.getFirstName()));
    }

    @Test
    public void deleteTouristsHappyFlow() throws Exception {
        //arrange
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = format.parse("1999-10-23");
        Tourist tourist = new Tourist(1,"Ion", "Popa", birthDate);
        Optional<Tourist> result = Optional.of(tourist);


        when(touristService.delete(1)).thenReturn(result);

        //act + assert
        mockMvc.perform(delete("/tourists/delete?id=" + tourist.getTouristId())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(tourist)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(tourist.getFirstName()));
    }

    @Test
    public void orderHappyFlow() throws Exception {
        //arrange
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = format.parse("1999-10-23");
        Tourist firstTourist = new Tourist(1,"Ion", "Popa", birthDate);
        Tourist secondTourist = new Tourist(2,"Ana", "Ana", birthDate);
        List<Tourist> result = Arrays.asList(
                new Tourist(2,"Ana", "Ana", birthDate),
                new Tourist(1,"Ion", "Popa", birthDate));



        when(touristService.orderTurists("asc")).thenReturn(result);

        //act + assert
        mockMvc.perform(get("/tourists/order?type=asc" )
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(result)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value(secondTourist.getFirstName()));
    }



    @Test
    public void getByBirthDateHappyFlow() throws Exception {
        //arrange
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = format.parse("1999-10-23");
        Date birthDate2 = format.parse("1998-10-23");
        Tourist firstTourist = new Tourist(1,"Ion", "Popa", birthDate);
        Tourist secondTourist = new Tourist(2,"Ana", "Ana", birthDate2);
        List<Tourist> result = Arrays.asList(
                new Tourist(2,"Ana", "Ana", birthDate2));



        when(touristService.getByBirthDate(1998)).thenReturn(result);

        //act + assert
        mockMvc.perform(get("/tourists/getByBirthDate?year=1998")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(result)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value(secondTourist.getFirstName()));
    }


}
