package com.example.demo.service;
import com.example.demo.model.Agency;
import com.example.demo.model.AgencyWithNumberOfTrips;
import com.example.demo.repository.AgencyRepository;
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
public class AgencyServiceTest {
    //@Mock, @InjectMocks
    @InjectMocks
    private AgencyService agencyService;

    @Mock
    private AgencyRepository agencyRepository;

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
    @DisplayName("Adaugarea unei agentii in repo")
    public void createAgencyTest() {
        //arrange
        Agency agency = new Agency("Ego Travel Romania", 1);
        Agency savedAgency = new Agency(1, "Ego Travel Romania", 1);
        when(agencyRepository.create(agency)).thenReturn(savedAgency);

        //act
        Agency result = agencyService.create(agency);

        //assert
        assertEquals(agency.getName(), result.getName());
        assertEquals(agency.getLocationCode(), result.getLocationCode());

        verify(agencyRepository, times(1)).create(agency);
    }

    @Test
    @DisplayName("Afisarea agentiilor")
    public void getAllAgenciesTest(){
        //arrange
        when(agencyRepository.get()).thenReturn(
                Arrays.asList(new Agency(1, "Ego Travel Romania", 1))
        );

        //act
        List<Agency> result = agencyService.get();

        //assert
        Agency agency = result.get(0);
        assertEquals(result.size(), 1);
        assertEquals(agency.getAgencyId(), 1);
        assertEquals(agency.getName(), "Ego Travel Romania");
        assertEquals(agency.getLocationCode(), 1);
    }

    @Test
    @DisplayName("Updatarea unei agentii")
    public void updateAgencyTest() {
        //arrange
        Agency agency = new Agency(1, "Ego Travel Romania", 1);
        when(agencyRepository.getById(agency.getAgencyId())).thenReturn(Optional.of(agency));
        when(agencyRepository.update(agency)).thenReturn(agency);

        //act
        Agency result = agencyService.update(agency);

        //assert
        assertEquals(agency.getAgencyId(), 1);
        assertEquals(agency.getName(), result.getName());
        assertEquals(agency.getLocationCode(), result.getLocationCode());

        verify(agencyRepository, times(1)).getById(agency.getAgencyId());
        verify(agencyRepository, times(1)).update(agency);
    }

    @Test
    @DisplayName("Stergerea unei agentii")
    public void deleteAgencyTest() {
        //arrange
        Agency agency = new Agency(1, "Ego Travel Romania", 1);
        when(agencyRepository.getById(agency.getAgencyId())).thenReturn(Optional.of(agency));
        when(agencyRepository.delete(agency.getAgencyId())).thenReturn(Optional.of(agency));

        //act
        Optional<Agency> result = agencyService.delete(agency.getAgencyId());

        //assert
        assertEquals(agency.getAgencyId(), 1);
        assertEquals(agency.getName(), result.get().getName());
        assertEquals(agency.getLocationCode(), result.get().getLocationCode());


        verify(agencyRepository, times(1)).getById(agency.getAgencyId());
        verify(agencyRepository, times(1)).delete(agency.getAgencyId());
    }

    @Test
    @DisplayName("Afisarea id-ului, numele agentiei si numarul de excursii de la acea agentie")
    public void agencyWithNumberOfTripsTest() {
        //arrange
        AgencyWithNumberOfTrips agencyWithNumberOfTrips = new AgencyWithNumberOfTrips(1, "Ego Travel Romania", 3);
        when(agencyRepository.agencyWithNumberOfTrips()).thenReturn(
                Arrays.asList(new AgencyWithNumberOfTrips(1, "Ego Travel Romania", 3))
        );

        //act
        List<AgencyWithNumberOfTrips> result = agencyService.agencyWithNumberOfTrips();

        //assert
        assertEquals(agencyWithNumberOfTrips.getAgencyId(), 1);
        assertEquals(agencyWithNumberOfTrips.getName(), result.get(0).getName());
        assertEquals(agencyWithNumberOfTrips.getTripCount(), result.get(0).getTripCount());


        verify(agencyRepository, times(1)).agencyWithNumberOfTrips();
    }


}
