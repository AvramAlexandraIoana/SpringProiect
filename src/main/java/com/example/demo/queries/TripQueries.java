package com.example.demo.queries;

public class TripQueries {
    public final static String GET_SQL = "SELECT * FROM trip";
    public final static String CREATE_SQL = "INSERT INTO trip(tripId, name, price, numberOfSeats, duration, agencyId, locationId)" +
            " values (?, ?, ?, ?, ?, ?, ?)";
    public final static String UPDATE_SQL = "UPDATE trip SET name = ?, price = ?, numberOfSeats = ?, duration = ?, agencyId = ?, locationId = ?" +
            " WHERE tripId = ?";
    public final static String DELETE_SQL = "DELETE FROM trip WHERE tripId = ?";
    public final static String GETBYID_SQL = "SELECT * FROM trip WHERE tripId = ?";
}
