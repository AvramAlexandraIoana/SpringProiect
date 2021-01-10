package com.example.demo.service;
import com.example.demo.model.Purchase;
import com.example.demo.model.Tourist;
import com.example.demo.repository.PurchaseRepository;
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
public class PurchaseServiceTest {
    //@Mock, @InjectMocks
    @InjectMocks
    private PurchaseService purchaseService;

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
    @DisplayName("Adaugarea unei achizitii in repo")
    public void createPurchaseTest() throws ParseException {
        //arrange
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse("2020-12-25");
        Date endDate = format.parse("2020-12-31");
        Purchase purchase = new Purchase(1, 1, startDate, endDate, new Date(), 10.2);
        Purchase savedPurchase = new Purchase(1, 1, startDate, endDate, new Date(), 10.2);
        when(purchaseRepository.create(purchase)).thenReturn(savedPurchase);

        //act
        Purchase result = purchaseService.create(purchase);

        //assert
        assertEquals(purchase.getTouristCode(), result.getTouristCode());
        assertEquals(purchase.getTripCode(), result.getTripCode());
        assertEquals(purchase.getStartDate(), result.getStartDate());
        assertEquals(purchase.getEndDate(), result.getEndDate());
        assertEquals(purchase.getDiscount(), result.getDiscount());

        verify(purchaseRepository, times(1)).create(purchase);
    }

    @Test
    @DisplayName("Afisarea achizitiilor")
    public void getAllPurchasesTest() throws ParseException {
        //arrange
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse("2020-12-25");
        Date endDate = format.parse("2020-12-31");
        when(purchaseRepository.get()).thenReturn(
                Arrays.asList( new Purchase(1, 1, startDate, endDate, new Date(), 10.2))
        );

        //act
        List<Purchase> result = purchaseService.get();

        //assert
        Purchase purchase = result.get(0);
        assertEquals(result.size(), 1);
        assertEquals(purchase.getTouristCode(), 1);
        assertEquals(purchase.getTripCode(), 1);
        assertEquals(purchase.getStartDate(), startDate);
        assertEquals(purchase.getEndDate(), endDate);
        assertEquals(purchase.getDiscount(), 10.2);
    }

    @Test
    @DisplayName("Updatarea unei achizitii")
    public void updatePurchaseTest() throws ParseException {
        //arrange
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse("2020-12-25");
        Date endDate = format.parse("2020-12-31");
        Purchase purchase = new Purchase(1, 1, startDate, endDate, new Date(), 10.2);
        when(purchaseRepository.getById(purchase.getTouristCode(), purchase.getTripCode())).thenReturn(Optional.of(purchase));
        when(purchaseRepository.update(purchase)).thenReturn(purchase);

        //act
        Purchase result = purchaseService.update(purchase);

        //assert
        assertEquals(purchase.getTouristCode(), result.getTouristCode());
        assertEquals(purchase.getTripCode(), result.getTripCode());
        assertEquals(purchase.getStartDate(), result.getStartDate());
        assertEquals(purchase.getEndDate(), result.getEndDate());
        assertEquals(purchase.getPurchaseDate(), result.getPurchaseDate());
        assertEquals(purchase.getDiscount(), result.getDiscount());

        verify(purchaseRepository, times(1)).getById(purchase.getTouristCode(), purchase.getTripCode());
        verify(purchaseRepository, times(1)).update(purchase);
    }

    @Test
    @DisplayName("Stergerea unei achizitii")
    public void deletePurchaseTest() throws ParseException {
        //arrange
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse("2020-12-25");
        Date endDate = format.parse("2020-12-31");
        Purchase purchase = new Purchase(1, 1, startDate, endDate, new Date(), 10.2);
        when(purchaseRepository.getById(purchase.getTouristCode(), purchase.getTripCode())).thenReturn(Optional.of(purchase));
        when(purchaseRepository.delete(purchase.getTouristCode(), purchase.getTripCode())).thenReturn(Optional.of(purchase));

        //act
        Optional<Purchase> result = purchaseService.delete(purchase.getTouristCode(), purchase.getTripCode());

        //assert
        assertEquals(purchase.getTouristCode(), result.get().getTouristCode());
        assertEquals(purchase.getTripCode(), result.get().getTripCode());
        assertEquals(purchase.getStartDate(), result.get().getStartDate());
        assertEquals(purchase.getEndDate(), result.get().getEndDate());
        assertEquals(purchase.getPurchaseDate(), result.get().getPurchaseDate());
        assertEquals(purchase.getDiscount(), result.get().getDiscount());

        verify(purchaseRepository, times(1)).getById(purchase.getTouristCode(), purchase.getTripCode());
        verify(purchaseRepository, times(1)).delete(purchase.getTouristCode(), purchase.getTripCode());
    }
}
