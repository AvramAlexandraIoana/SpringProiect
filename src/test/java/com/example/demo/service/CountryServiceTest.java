package com.example.demo.service;

import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {
    //@Mock, @InjectMocks
    @InjectMocks
    private CountryService countryService;

    @Mock
    private CountryRepository countryRepository;

    //@BeforeEach
    //@BeforeAll
    //@AfterEach
    //@AfterAll

    @BeforeEach
    public void beforeEach(){
        System.out.println("Hello from before each!!!");
    }

    @BeforeAll
    public static void beforeAll(){
        System.out.println("Hello from before all");
    }

    @AfterEach
    public  void afterEach() {
        System.out.println("Hello from after each");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Hello from after all");
    }

    @Test
    @DisplayName("Adaugarea unei tari in repo")
    public void createTest() {
        //arrange
        Country country = new Country("Romania");
        Country savedCountry = new Country(1, "Romania");
        when(countryRepository.create(country)).thenReturn(savedCountry);

        //act
        Country result = countryService.create(country);

        //assert
        assertEquals(country.getName(), result.getName());

        verify(countryRepository, times(1)).create(country);
    }

    @Test
    @DisplayName("Afisarea tarilor")
    public void getTest(){
        //arrange
        when(countryRepository.get()).thenReturn(
                Arrays.asList(new Country(1, "Romania"))
        );

        //act
        List<Country> result = countryService.get();

        //assert
        Country country = result.get(0);
        assertEquals(result.size(), 1);
        assertEquals(country.getCountryId(), 1);
        assertEquals(country.getName(), "Romania");
    }

    @Test
    @DisplayName("Updatarea unei tari")
    public void updateTest() {
        //arrange
        Country country = new Country(1, "Romania");
        when(countryRepository.getById(country.getCountryId())).thenReturn(Optional.of(country));
        when(countryRepository.update(country)).thenReturn(country);

        //act
        Country result = countryService.update(country);

        //assert
        assertEquals(country.getCountryId(), 1);
        assertEquals(country.getName(), result.getName());

        verify(countryRepository, times(1)).getById(country.getCountryId());
        verify(countryRepository, times(1)).update(country);
    }

    @Test
    @DisplayName("Stergerea unei tari")
    public void deleteTest() {
        //arrange
        Country country = new Country(1, "Romania");
        when(countryRepository.getById(country.getCountryId())).thenReturn(Optional.of(country));
        when(countryRepository.delete(country.getCountryId())).thenReturn(Optional.of(country));

        //act
        Optional<Country> result = countryService.delete(country.getCountryId());

        //assert
        assertEquals(country.getCountryId(), 1);
        assertEquals(country.getName(), result.get().getName());

        verify(countryRepository, times(1)).getById(country.getCountryId());
        verify(countryRepository, times(1)).delete(country.getCountryId());
    }





}
