package com.tourists.tourists.dao;
import com.tourists.tourists.model.Country;
import com.tourists.tourists.model.Flight;
import com.tourists.tourists.model.Gender;
import com.tourists.tourists.model.Tourist;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class TouristDaoJDBC {
    private static final String FULL_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/touristFlight?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASS = "NGhiphop020188!";
    private static final String TABLE = "insert into tourist values (default, ?, ?, ?, ?, ?, ?)";
    private static final String UPDTABLE = "update tourist SET name = ? where touristId = ?";
    private static final String FINDSTUDENT = "SELECT * FROM touristFlight where id=?";
    private static final String SHOWTOURISTS = "SELECT * FROM tourist";
    private static final String SHOWTOURISTFLIGHTS = "";
    private static final String TOURISTFLIGHT = "INSERT INTO touristFlight VALUES (?, ?)";
    public static final String DELTOURIST = "delete from tourist WHERE touristId =?";
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Map<Integer, Tourist> tourMap = new HashMap<>();
    private Map<Integer, Flight> flyMap = new HashMap<>();

    public Connection getConnection() {
        try {
            Class.forName( FULL_DRIVER_NAME );
            connection = DriverManager.getConnection( URL, LOGIN, PASS );
            System.out.println( "Connection is Successful" );
        } catch (SQLException e) {
            System.err.println( e );
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println( e );

        }
        return connection;
    }

    public Tourist addTourists(Tourist tourist) {
        try {
            this.getConnection();
            preparedStatement = connection.prepareStatement( TABLE );
            preparedStatement.setString( 1, tourist.getName());
            preparedStatement.setString( 2, tourist.getSurname() );
            preparedStatement.setString( 3, tourist.getGender().name());
            preparedStatement.setString( 4, tourist.getCountry().name());
            preparedStatement.setString( 5, tourist.getRemarks());
            String dateOfBirth = tourist.getDateOfBirth().substring(0, tourist.getDateOfBirth().length() - 14);
            preparedStatement.setString(6, dateOfBirth);
      //      preparedStatement.setDate( 6, tourist.getDateOfBirth());
//            for (Flight flight:tourist.getListOfFlights())
//            {PreparedStatement ps = connection.prepareStatement(TOURISTFLIGHT);
//                ps.setInt(1, tourist.getId());
//                ps.setInt(2, flight.getId());
//                preparedStatement.execute();
//            }
            preparedStatement.execute();
            System.out.println( "tourist in the db" );
        } catch (SQLException e) {
            System.err.println( e );

        }
        return tourist;
    }

    //всечто ниже требует доработки

    public Map<Integer, Tourist> initTourists() {
        try {
            this.getConnection();
            preparedStatement = connection.prepareStatement(SHOWTOURISTS);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                    tourMap.put(result.getInt( "touristId" ), new Tourist(result.getInt( "touristId" ), result.getString( "name" ), result.getString( "surname" ), Gender.valueOf(result.getString( "gender")), Country.valueOf(result.getString( "country" )), result.getString("remarks"), result.getString("dateOfBirth") ));
                System.out.println(result.getString( "name" ));
                }
        } catch (SQLException e) {
            System.err.println( e );
        }
        return tourMap;
    }


    public Tourist getTourists(int id) {
        return tourMap.get(id);
    }

    public List<Tourist> getAllTourists() {
        Collection<Tourist> c = initTourists().values();
        List<Tourist> list = new ArrayList<Tourist>();
        list.addAll(c);
        return list;
    }

        public Tourist updateTourist(Tourist tourForm) {
        Tourist tour = this.getTourists(tourForm.getId());
        if(tour!= null)  {
            tour.setName(tourForm.getName());

            try {
                this.getConnection();
                preparedStatement = connection.prepareStatement( UPDTABLE );
                preparedStatement.setString( 1, tourForm.getName() );
                preparedStatement.setInt( 2, tourForm.getId());
                preparedStatement.execute();
            } catch (SQLException e) {
                System.err.println( e );
            }

        }
        tourMap.put(tourForm.getId(), tour);
        return tour;
    }

        public void deleteTourist(int id) {

            try {
                this.getConnection();
                preparedStatement = connection.prepareStatement( DELTOURIST );
                preparedStatement.setInt( 1, id);
                preparedStatement.execute();

            } catch (SQLException e) {
                System.err.println( e );
            }

        tourMap.remove(id);
    }
}
