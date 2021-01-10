package com.example.demo.queries;

public class LocationQueries {
    public final static String GET_SQL = "SELECT * FROM location";
    public final static String CREATE_SQL = "INSERT INTO location(locationId, address, countryCode) values (?, ?, ?)";
    public final static String UPDATE_SQL = "UPDATE location SET address = ?, countryCode = ? WHERE locationId = ?";
    public final static String DELETE_SQL = "DELETE FROM location WHERE locationId = ?";
    public final static String GETBYID_SQL = "SELECT * FROM location WHERE locationId = ?";
}
