package com.example.demo.service;
import com.example.demo.model.Purchase;
import com.example.demo.model.Trip;
import com.example.demo.repository.PurchaseRepository;
import com.example.demo.repository.TripRepository;
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
public class TripServiceTest {
    //@Mock, @InjectMocks
    @InjectMocks
    private TripService tripService;

    @Mock
    private TripRepository tripRepository;

    @Mock
    private PurchaseRepository purchaseRepository;

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
    @DisplayName("Adaugarea unei excursii in repo")
    public void createTripTest() {
        //arrange
        Trip trip = new Trip("Excursie Predeal", 200.0, 10, 2, 1 , 1);
        Trip savedTrip = new Trip(1,"Excursie Predeal", 200.0, 10, 2, 1 , 1);
        when(tripRepository.create(trip)).thenReturn(savedTrip);

        //act
        Trip result = tripService.create(trip);

        //assert
        assertEquals(trip.getName(), result.getName());
        assertEquals(trip.getPrice(), result.getPrice());
        assertEquals(trip.getNumberOfSeats(), result.getNumberOfSeats());
        assertEquals(trip.getDuration(), result.getDuration());
        assertEquals(trip.getAgencyId(), result.getAgencyId());
        assertEquals(trip.getLocationId(), result.getLocationId());

        verify(tripRepository, times(1)).create(trip);
    }

    @Test
    @DisplayName("Afisarea excursiilor")
    public void getAllTripsTest(){
        //arrange
        when(tripRepository.get()).thenReturn(
                Arrays.asList(new Trip(1,"Excursie Predeal", 200.0, 10, 2, 1 , 1))
        );

        //act
        List<Trip> result = tripService.get();

        //assert
        Trip trip = result.get(0);
        assertEquals(result.size(), 1);
        assertEquals(trip.getTripId(), 1);
        assertEquals(trip.getName(), "Excursie Predeal");
        assertEquals(trip.getPrice(), 200.0);
        assertEquals(trip.getNumberOfSeats(), 10);
        assertEquals(trip.getDuration(), 2);
        assertEquals(trip.getAgencyId(), 1);
        assertEquals(trip.getLocationId(), 1);
    }

    @Test
    @DisplayName("Updatarea unei excrusii")
    public void updateTripTest() {
        //arrange
        Trip trip = new Trip(1,"Excursie Predeal", 200.0, 10, 2, 1 , 1);
        when(tripRepository.getById(trip.getTripId())).thenReturn(Optional.of(trip));
        when(tripRepository.update(trip)).thenReturn(trip);

        //act
        Trip result = tripService.update(trip);

        //assert
        assertEquals(trip.getTripId(), 1);
        assertEquals(trip.getName(), result.getName());
        assertEquals(trip.getPrice(), result.getPrice());
        assertEquals(trip.getNumberOfSeats(), result.getNumberOfSeats());
        assertEquals(trip.getDuration(), result.getDuration());
        assertEquals(trip.getAgencyId(), result.getAgencyId());
        assertEquals(trip.getLocationId(), result.getLocationId());

        verify(tripRepository, times(1)).getById(trip.getTripId());
        verify(tripRepository, times(1)).update(trip);
    }

    @Test
    @DisplayName("Stergerea unei excursii")
    public void deleteAgencyTest() {
        //arrange
        Trip trip = new Trip(1,"Excursie Predeal", 200.0, 10, 2, 1 , 1);
        when(tripRepository.getById(trip.getTripId())).thenReturn(Optional.of(trip));
        when(tripRepository.delete(trip.getTripId())).thenReturn(Optional.of(trip));

        //act
        Optional<Trip> result = tripService.delete(trip.getTripId());

        //assert
        assertEquals(trip.getTripId(), 1);
        assertEquals(trip.getName(), result.get().getName());
        assertEquals(trip.getPrice(), result.get().getPrice());
        assertEquals(trip.getNumberOfSeats(), result.get().getNumberOfSeats());
        assertEquals(trip.getDuration(), result.get().getDuration());
        assertEquals(trip.getAgencyId(), result.get().getAgencyId());
        assertEquals(trip.getLocationId(), result.get().getLocationId());


        verify(tripRepository, times(1)).getById(trip.getTripId());
        verify(tripRepository, times(1)).delete(trip.getTripId());
    }

    @Test
    @DisplayName("Excursiile achizitionate de turistul cu id-ul x")
    public void getTripByTouristIdTest() throws ParseException {
        //arrange
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse("2020-12-25");
        Date endDate = format.parse("2020-12-31");
        Purchase purchase = new Purchase(1, 1, startDate, endDate, new Date(), 12.2);
        Trip trip = new Trip(1,"Excursie Predeal", 200.0, 10, 2, 1 , 1);
        when(tripRepository.getTripByTouristId(1)).thenReturn(
                Arrays.asList(new Trip(1,"Excursie Predeal", 200.0, 10, 2, 1 , 1))
        );
        when(purchaseRepository.getByTouristId(1)).thenReturn(Optional.of(purchase));


        //act
        List<Trip> result = tripService.getTripByTouristId(1);

        //assert
        Trip firstTrip = result.get(0);
        assertEquals(trip.getTripId(), 1);
        assertEquals(trip.getName(), firstTrip.getName());
        assertEquals(trip.getPrice(), firstTrip.getPrice());
        assertEquals(trip.getNumberOfSeats(), firstTrip.getNumberOfSeats());
        assertEquals(trip.getDuration(), firstTrip.getDuration());
        assertEquals(trip.getAgencyId(), firstTrip.getAgencyId());
        assertEquals(trip.getLocationId(), firstTrip.getLocationId());


        verify(purchaseRepository, times(1)).getByTouristId(1);
        verify(tripRepository, times(1)).getTripByTouristId(1);
    }


}
