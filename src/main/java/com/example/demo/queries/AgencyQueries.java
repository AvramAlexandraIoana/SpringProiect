package com.example.demo.queries;

public class AgencyQueries {
    public final static String GET_SQL = "SELECT * FROM agency";
    public final static String CREATE_SQL = "INSERT INTO agency(agencyId, name, locationCode) values (?, ?, ?)";
    public final static String UPDATE_SQL = "UPDATE agency SET name = ?, locationCode = ? WHERE agencyId = ?";
    public final static String DELETE_SQL = "DELETE FROM agency WHERE agencyId = ?";
    public final static String GETBYID_SQL = "SELECT * FROM agency WHERE agencyId = ?";
    public final static String GETAGENCYWITHNUMBEROFTRIPS_SQL = "SELECT a.agencyId, a.name, COUNT(*) tripCount\n" +
            "FROM mydb.agency a join mydb.trip t " +
            " on a.agencyId = t.agencyId " +
            " GROUP BY  a.agencyId, a.name;";

}
