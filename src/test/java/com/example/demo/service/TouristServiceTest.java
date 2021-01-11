package com.example.demo.service;
import com.example.demo.model.Tourist;
import com.example.demo.repository.TouristRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TouristServiceTest {
    //@Mock, @InjectMocks
    @InjectMocks
    private TouristService touristService;

    @Mock
    private TouristRepository touristRepository;

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
    @DisplayName("Adaugarea unei turist in repo")
    public void createTest() throws ParseException {
        //arrange
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = format.parse("1998-12-23");
        Tourist tourist = new Tourist("Ion", "Popa", birthDate);
        Tourist savedTourist =  new Tourist(1,"Ion", "Popa", birthDate);
        when(touristRepository.create(tourist)).thenReturn(savedTourist);

        //act
        Tourist result = touristService.create(tourist);

        //assert
        assertEquals(tourist.getFirstName(), result.getFirstName());
        assertEquals(tourist.getLastName(), result.getLastName());
        assertEquals(tourist.getDateOfBirth(), result.getDateOfBirth());

        verify(touristRepository, times(1)).create(tourist);
    }

    @Test
    @DisplayName("Afisarea turistiilor")
    public void getTest() throws ParseException {
        //arrange
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = format.parse("1998-12-23");
        when(touristRepository.get()).thenReturn(
                Arrays.asList(new Tourist(1,"Ion", "Popa", birthDate))
        );

        //act
        List<Tourist> result = touristService.get();

        //assert
        Tourist tourist = result.get(0);
        assertEquals(result.size(), 1);
        assertEquals(tourist.getTouristId(), 1);
        assertEquals(tourist.getFirstName(), "Ion");
        assertEquals(tourist.getLastName(), "Popa");
        assertEquals(tourist.getDateOfBirth(), birthDate);
    }

    @Test
    @DisplayName("Updatarea unui turist")
    public void updateTest() throws ParseException {
        //arrange
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = format.parse("1998-12-23");
        Tourist tourist = new Tourist(1, "Ion", "Popa", birthDate);
        when(touristRepository.getById(tourist.getTouristId())).thenReturn(Optional.of(tourist));
        when(touristRepository.update(tourist)).thenReturn(tourist);

        //act
        Tourist result = touristService.update(tourist);

        //assert
        assertEquals(tourist.getTouristId(), 1);
        assertEquals(tourist.getFirstName(), result.getFirstName());
        assertEquals(tourist.getLastName(), result.getLastName());
        assertEquals(tourist.getDateOfBirth(), result.getDateOfBirth());

        verify(touristRepository, times(1)).getById(tourist.getTouristId());
        verify(touristRepository, times(1)).update(tourist);
    }

    @Test
    @DisplayName("Stergerea unui turist")
    public void deleteTest() throws ParseException {
        //arrange
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = format.parse("1998-12-23");
        Tourist tourist = new Tourist(1, "Ion", "Popa", birthDate);
        when(touristRepository.getById(tourist.getTouristId())).thenReturn(Optional.of(tourist));
        when(touristRepository.delete(tourist.getTouristId())).thenReturn(Optional.of(tourist));

        //act
        Optional<Tourist> result = touristService.delete(tourist.getTouristId());

        //assert
        assertEquals(tourist.getTouristId(), 1);
        assertEquals(tourist.getFirstName(), result.get().getFirstName());
        assertEquals(tourist.getLastName(), result.get().getLastName());
        assertEquals(tourist.getDateOfBirth(), result.get().getDateOfBirth());


        verify(touristRepository, times(1)).getById(tourist.getTouristId());
        verify(touristRepository, times(1)).delete(tourist.getTouristId());
    }

    @Test
    @DisplayName("Ordonarea turistiilor crescator dupa firstName")
    public void orderTouristAscTest() throws ParseException {
        //arrange
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = format.parse("1998-12-23");
        Tourist firstTourist = new Tourist(1, "Ion", "Popa", birthDate);
        Tourist secondTourist = new Tourist(2, "Ana", "Avram", birthDate);
        when(touristRepository.orderTuristsAsc()).thenReturn(
                Arrays.asList(new Tourist(2, "Ana", "Avram", birthDate),
                              new Tourist(1,"Ion", "Popa", birthDate))
        );

        //act
        List<Tourist> result = touristService.orderTurists("asc");

        //assert
        Tourist tourist = result.get(0);
        assertEquals(result.size(), 2);
        assertEquals(tourist.getTouristId(), 2);
        assertEquals(tourist.getFirstName(), "Ana");
        assertEquals(tourist.getLastName(), "Avram");
        assertEquals(tourist.getDateOfBirth(), birthDate);
    }

    @Test
    @DisplayName("Ordonarea turistiilor descrescator dupa firstName")
    public void orderTouristDescTest() throws ParseException {
        //arrange
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = format.parse("1998-12-23");
        Tourist firstTourist = new Tourist(1, "Ion", "Popa", birthDate);
        Tourist secondTourist = new Tourist(2, "Ana", "Avram", birthDate);
        when(touristRepository.orderTuristsDesc()).thenReturn(
                Arrays.asList(new Tourist(1, "Ion", "Popa", birthDate),
                        new Tourist(2,"Ana", "Avram", birthDate))
        );

        //act
        List<Tourist> result = touristService.orderTurists("desc");

        //assert
        Tourist tourist = result.get(0);
        assertEquals(result.size(), 2);
        assertEquals(tourist.getTouristId(), 1);
        assertEquals(tourist.getFirstName(), "Ion");
        assertEquals(tourist.getLastName(), "Popa");
        assertEquals(tourist.getDateOfBirth(), birthDate);
    }

}
