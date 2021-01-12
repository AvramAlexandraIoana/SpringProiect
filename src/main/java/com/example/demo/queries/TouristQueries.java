package com.example.demo.queries;

public class TouristQueries {
    public final static String GET_SQL = "SELECT * FROM tourist";
    public final static String CREATE_SQL = "INSERT INTO tourist(touristId, firstName, lastName, dateOfBirth) values (?, ?, ?, ?)";
    public final static String UPDATE_SQL = "UPDATE tourist SET firstName = ?, lastName = ?,  dateOfBirth = ? WHERE touristId = ?";
    public final static String DELETE_SQL = "DELETE FROM tourist WHERE touristId = ?";
    public final static String GETBYID_SQL = "SELECT * FROM tourist WHERE touristId = ?";
    public final static String ORDER_ASC_SQL = "SELECT * FROM tourist ORDER BY firstName ASC";
    public final static String ORDER_DESC_SQL = "SELECT * FROM tourist ORDER BY firstName DESC";
    public final static String GETBYDATEOFBIRTH_SQL = "SELECT *  " +
            " FROM mydb.tourist  " +
            " WHERE year(dateOfBirth) = ?";

    public final  static String TOURISTWITHNUMBEROFTRIPS_SQL = "SELECT firstName, lastName, COUNT(*) tripCount " +
            " FROM mydb.tourist tourist join mydb.purchase " +
            " on tourist.touristId = purchase.touristCode " +
            " GROUP BY touristId, firstName;";
}
