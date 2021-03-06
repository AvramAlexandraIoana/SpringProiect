package com.example.demo.service;

import com.example.demo.model.Country;
import com.example.demo.model.Location;
import com.example.demo.repository.CountryRepository;
import com.example.demo.repository.LocationRepository;
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
public class LocationServiceTest {
    //@Mock, @InjectMocks
    @InjectMocks
    private LocationService locationService;

    @Mock
    private LocationRepository locationRepository;

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
    @DisplayName("Adaugarea unei locatii in repo")
    public void createTest() {
        //arrange
        Location location = new Location("Bucuresti","Str.Unirii", 1);
        Location savedLocation = new Location("Bucuresti","Str.Unirii", 1);
        when(locationRepository.create(location)).thenReturn(savedLocation);

        //act
        Location result = locationService.create(location);

        //assert
        assertEquals(location.getCity(), result.getCity());
        assertEquals(location.getAddress(), result.getAddress());
        assertEquals(location.getCountryCode(), result.getCountryCode());

        verify(locationRepository, times(1)).create(location);
    }

    @Test
    @DisplayName("Afisarea locatiilor")
    public void getTest(){
        //arrange
        when(locationRepository.get()).thenReturn(
                Arrays.asList(new Location(1,"Bucuresti","Str.Unirii", 1))
        );

        //act
        List<Location> result = locationService.get();

        //assert
        Location location = result.get(0);
        assertEquals(result.size(), 1);
        assertEquals(location.getCity(), "Bucuresti");
        assertEquals(location.getAddress(), "Str.Unirii");
        assertEquals(location.getCountryCode(), 1);
    }

    @Test
    @DisplayName("Updatarea unei locatii")
    public void updateTest() {
        //arrange
        Location location = new Location(1,"Bucuresti", "Str.Unirii", 1);
        when(locationRepository.getById(location.getLocationId())).thenReturn(Optional.of(location));
        when(locationRepository.update(location)).thenReturn(location);

        //act
        Location result = locationService.update(location);

        //assert
        assertEquals(location.getCity(), result.getCity());
        assertEquals(location.getAddress(), result.getAddress());
        assertEquals(location.getCountryCode(), result.getCountryCode());

        verify(locationRepository, times(1)).getById(location.getLocationId());
        verify(locationRepository, times(1)).update(location);
    }

    @Test
    @DisplayName("Stergerea unei locatii")
    public void deleteTest() {
        //arrange
        Location location = new Location(1,"Bucuresti", "Str.Unirii", 1);
        when(locationRepository.getById(location.getLocationId())).thenReturn(Optional.of(location));
        when(locationRepository.delete(location.getLocationId())).thenReturn(Optional.of(location));

        //act
        Optional<Location> result = locationService.delete(location.getLocationId());

        //assert
        assertEquals(location.getCity(), result.get().getCity());
        assertEquals(location.getAddress(), result.get().getAddress());
        assertEquals(location.getCountryCode(), result.get().getCountryCode());

        verify(locationRepository, times(1)).getById(location.getLocationId());
        verify(locationRepository, times(1)).delete(location.getLocationId());
    }

    @Test
    @DisplayName("Afisarea locatiilor din tara cu numele z")
    public void getLocationForCountryName() {
        //arrange
        Country country = new Country(1, "Romania");
        Location location = new Location(1,"Bucuresti", "Str.Unirii", 1);
        when(countryRepository.getByName(country.getName())).thenReturn(Optional.of(country));
        when(locationRepository.getLocationForCountryName(country.getName())).thenReturn(
                Arrays.asList(new Location(1,"Bucuresti","Str.Unirii", 1))
        );

        //act
        List<Location> result = locationService.getLocationForCountryName(country.getName());

        //assert
        assertEquals(location.getCity(), result.get(0).getCity());
        assertEquals(location.getAddress(), result.get(0).getAddress());
        assertEquals(location.getCountryCode(), result.get(0).getCountryCode());

        verify(countryRepository, times(1)).getByName(country.getName());
        verify(locationRepository, times(1)).getLocationForCountryName(country.getName());
    }

}
