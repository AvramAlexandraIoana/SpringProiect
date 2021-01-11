package com.example.demo.queries;

public class LocationQueries {
    public final static String GET_SQL = "SELECT * FROM location";
    public final static String CREATE_SQL = "INSERT INTO location(locationId, city, address, countryCode) values (?, ?, ?, ?)";
    public final static String UPDATE_SQL = "UPDATE location SET city = ?, address = ?, countryCode = ? WHERE locationId = ?";
    public final static String DELETE_SQL = "DELETE FROM location WHERE locationId = ?";
    public final static String GETBYID_SQL = "SELECT * FROM location WHERE locationId = ?";
    public final static String GETLOCATIONFORCOUNTRYNAME_SQL = "SELECT l.locationId, l.city, l.address, l.countryCode " +
            " FROM mydb.country c join mydb.location l " +
            " on c.countryId = l.countryCode " +
            " WHERE c.name = ?";

}
