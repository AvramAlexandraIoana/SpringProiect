package com.example.demo.queries;

public class TripQueries {
    public final static String GET_SQL = "SELECT * FROM trip";
    public final static String CREATE_SQL = "INSERT INTO trip(tripId, name, price, numberOfSeats, duration, agencyId, locationId)" +
            " values (?, ?, ?, ?, ?, ?, ?)";
    public final static String UPDATE_SQL = "UPDATE trip SET name = ?, price = ?, numberOfSeats = ?, duration = ?, agencyId = ?, locationId = ?" +
            " WHERE tripId = ?";
    public final static String DELETE_SQL = "DELETE FROM trip WHERE tripId = ?";
    public final static String GETBYID_SQL = "SELECT * FROM trip WHERE tripId = ?";
    public final static String  GETBYTOURISTID_SQL = "SELECT  tr.tripId, tr.name, tr.price, tr.numberOfSeats, tr.duration, tr.agencyId, tr.locationId\n" +
            " FROM mydb.tourist t join  mydb.purchase p on t.touristId = p.touristCode " +
                 " join mydb.trip tr on tr.tripId = p.tripCode " +
            " WHERE t.touristId = ?; ";

    public final static String MINMAXSUMAVGPRICE_SQL  = "SELECT MIN(price) \"min\", MAX(price) \"max\", SUM(price) \"sum\", AVG(price) \"avg\"\n" +
            "FROM  mydb.trip;";
    public final static String EXPENSIVETRIPSTHAN_SQL = "SELECT t.firstName, t.lastName, tr.name, tr.price, tr.numberOfSeats, tr.duration\n" +
            " FROM mydb.tourist t join mydb.purchase p on t.touristId = p.touristCode " +
                 " join mydb.trip tr on tr.tripId = p.tripCode " +
            " WHERE tr.price > ALL(SELECT trip.price " +
                                   " FROM mydb.tourist tourist join  mydb.purchase purchase on tourist.touristId = purchase.touristCode " +
                                       " join mydb.trip trip on trip.tripId = purchase.tripCode " +
                                    " WHERE tourist.touristId = ?) " +
            " ORDER BY tr.price DESC; ";

    public final static  String GETTRIPWITHCITYANDCOUNTRY_SQL = "SELECT trip.name tripName, trip.price tripPrice, location.city city, country.name country\n" +
            " FROM mydb.trip trip join mydb.location location  " +
            " on trip.locationId = location.locationId " +
            "    join mydb.country country " +
            "    on location.countryCode = country.countryId; ";
}
