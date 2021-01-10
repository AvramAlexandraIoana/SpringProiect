package com.example.demo.queries;

public class PurchaseQueries {
    public final static String GET_SQL = "SELECT * FROM purchase";
    public final static String CREATE_SQL = "INSERT INTO purchase(touristCode, tripCode, startDate, endDate, purchaseDate, discount)" +
            " values (?, ?, ?, ?, ?, ?)";
    public final static String UPDATE_SQL = "UPDATE purchase SET startDate = ?, endDate = ?, purchaseDate = ?, discount = ?" +
            " WHERE touristCode = ? AND tripCode = ?";
    public final static String DELETE_SQL = "DELETE FROM purchase WHERE touristCode = ? AND tripCode = ?";
    public final static String GETBYID_SQL = "SELECT * FROM purchase WHERE touristCode = ? AND tripCode = ?";
}
