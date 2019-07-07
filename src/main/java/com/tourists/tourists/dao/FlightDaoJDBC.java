package com.tourists.tourists.dao;

import com.tourists.tourists.model.Flight;
import com.tourists.tourists.model.Tourist;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;
@Repository
public class FlightDaoJDBC {
    private static final String FULL_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/touristFlight?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASS = "NGhiphop020188!";
    private static final String TABLEFLIGHT = "insert into flight values (default, ?, ?, ?, ?, ?)";
    private static final String UPDATEFLIGHT = "update flight SET name = ? where flightId = ?";
    private static final String SHOWFLIGHTS = "SELECT * FROM flight";
    private static final String SHOWTOURISTFLIGHTS = "";
    private static final String TOURISTFLIGHT = "INSERT INTO touristFlight VALUES (?, ?)";
    public static final String DELFLIGHT = "delete from flight WHERE flightId =?";
    private Connection connection;
    private PreparedStatement preparedStatement;
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

    public Flight addFlights(Flight flight) {
        try {
            this.getConnection();
            preparedStatement = connection.prepareStatement( TABLEFLIGHT );
            preparedStatement.setString( 1, flight.getName());
            String departure = flight.getDeparture().substring(0, flight.getDeparture().length() - 14);
            preparedStatement.setString( 2, departure);
            String arrive = flight.getArrive().substring(0, flight.getArrive().length() - 14);
            preparedStatement.setString( 3, arrive);
            preparedStatement.setInt( 4, flight.getSeats());
            preparedStatement.setInt( 5, flight.getPrice());
//            for (Tourist tourist:flight.getListOfTourists())
//            {PreparedStatement ps = connection.prepareStatement(TOURISTFLIGHT);
//                ps.setInt(1, tourist.getId());
//                ps.setInt(2, flight.getId());
//                preparedStatement.execute();
//            }
            preparedStatement.execute();
            System.out.println( "flight in the db" );
        } catch (SQLException e) {
            System.err.println( e );

        }
        return flight;
    }

    public Map<Integer, Flight> initFlights() {
        try {
            this.getConnection();
            preparedStatement = connection.prepareStatement(SHOWFLIGHTS);

            ResultSet result = preparedStatement.executeQuery();
            System.out.println( "Выводим PreparedStatement" );
            while (result.next()) {
                flyMap.put(result.getInt( "flightId" ), new Flight(result.getInt( "flighttId" ), result.getString( "name" ), result.getString( "departure" ), result.getString( "arrive" ), result.getInt( "seats" ), result.getInt( "price" )));
                System.out.println(result.getString( "name" ));
            }
        } catch (SQLException e) {
            System.err.println( e );
            System.out.println("НЕ ПОЛУЧИЛОСЬ");
        }
        return flyMap;
    }


    public Flight getFlights(int id) {
        return flyMap.get(id);
    }

    public List<Flight> getAllFlights() {
        Collection<Flight> c = initFlights().values();
        List<Flight> list = new ArrayList<Flight>();
        list.addAll(c);
        return list;
    }
    public Flight updateFlight(Flight flyForm) {
        Flight fly = this.getFlights(flyForm.getId());
        if(fly!= null)  {
            fly.setName(flyForm.getName());

            try {
                this.getConnection();
                preparedStatement = connection.prepareStatement( UPDATEFLIGHT );
                preparedStatement.setString( 1, flyForm.getName() );
                preparedStatement.setInt( 2, flyForm.getId());
                preparedStatement.execute();
                System.out.println( "flight updated" );
            } catch (SQLException e) {
                System.err.println( e );
            }

        }
        flyMap.put(flyForm.getId(), fly);
        return fly;
    }

    public void deleteFlight(int id) {

        try {
            this.getConnection();
            preparedStatement = connection.prepareStatement( DELFLIGHT );
            preparedStatement.setInt( 1, id);
            preparedStatement.execute();
            System.out.println( "Студент под номером " + id + " удален" );

        } catch (SQLException e) {
            System.err.println( e );
        }

        flyMap.remove(id);
    }

}
