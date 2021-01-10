package com.example.demo.queries;

public class CountryQueries {
    public final static String GET_SQL = "SELECT * FROM country";
    public final static String CREATE_SQL = "INSERT INTO country(countryId, name) values (?, ?)";
    public final static String UPDATE_SQL = "UPDATE country SET name = ? WHERE countryId = ?";
    public final static String DELETE_SQL = "DELETE FROM country WHERE countryId = ?";
    public final static String GETBYID_SQL = "SELECT * FROM country WHERE countryId = ?";
    public final static String GETBYNAME_SQL = "SELECT * FROM country WHERE name = ?";

}
