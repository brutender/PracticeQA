package airport;

import connect.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirportMethods extends Connect {

    Scanner sc = new Scanner(System.in);

    public void insertAirport(Airports airports) {
        String sqlInsertAirport = "insert into \"qa2\".airports (city_name, airport_code, code_country) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlInsertAirport)) {

            System.out.println("Введите название города:");
            preparedStatement.setString(1, airports.setCityName(sc.nextLine()));

            System.out.println("Введите код аэропорта (например: ALA):");
            preparedStatement.setString(2, airports.setAirportCode(sc.nextLine()));

            System.out.println("Введите код страны (например: KAZ):");
            preparedStatement.setString(3, airports.setCodeCountry(sc.nextLine()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void deleteAirport(Airports airports) {
        String sqlDeleteAirport = "delete from \"qa2\".airports where id = ?";

        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlDeleteAirport)) {

            preparedStatement.setInt(1, airports.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            try {
                throw new ExceptionNoAirportCode("Такого аэропорта нет!");
            } catch (ExceptionNoAirportCode ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void updateAirport(Airports airports) {
        String sqlUpdateAirport = "update \"qa2\".airports set city_name = ?, airport_code = ?, code_country = ? where id = ?";

        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlUpdateAirport)) {

            preparedStatement.setInt(4, airports.getId());

            System.out.println("Введите название города:");
            preparedStatement.setString(1, airports.setCityName(sc.nextLine()));

            System.out.println("Введите код аэропорта (например: ALA):");
            preparedStatement.setString(2, airports.setAirportCode(sc.nextLine()));

            System.out.println("Введите код страны (например KG):");
            preparedStatement.setString(3, airports.setCodeCountry(sc.nextLine()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<Airports> selectAirportOnAirportCode(Airports airports) {
        List<Airports> airportsList = new ArrayList<>();
        String sqlSelectAirportOnAirportCode = "select a.*, c.name as country from \"qa2\".airports a " +
                "inner join \"qa2\".countries c on c.country_code = a.code_country " +
                "where airport_code = 'TAS'";

        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlSelectAirportOnAirportCode);
            ResultSet resultSet = preparedStatement.executeQuery() ) {

                while (resultSet.next()) {

                    airports.setId(resultSet.getInt("ID"));
                    airports.setCityName(resultSet.getString("CITY_NAME"));
                    airports.setAirportCode(resultSet.getString("AIRPORT_CODE"));
                    airports.setCodeCountry(resultSet.getString("CODE_COUNTRY"));
                    airports.setName(resultSet.getString("COUNTRY"));
                    airportsList.add(airports);

                }
            } catch (SQLException e) {
            throw new RuntimeException();
        }
        return airportsList;
    }

    public List<Airports> getAirportsFromDataBase() {
        List<Airports> airportsList = new ArrayList<>();
        String sqlSelectAllAirport = "select a.id, a.city_name, a.airport_code, a.code_country, c.name as country from \"qa2\".airports a " +
                "inner join qa2.countries c on c.country_code = a.code_country";

        try (Statement statement = connection().createStatement();
             ResultSet resultSet = statement.executeQuery(sqlSelectAllAirport)) {

            while (resultSet.next()) {
                Airports airports = new Airports();
                airports.setId(resultSet.getInt("ID"));
                airports.setCityName(resultSet.getString("CITY_NAME"));
                airports.setAirportCode(resultSet.getString("AIRPORT_CODE"));
                airports.setCodeCountry(resultSet.getString("CODE_COUNTRY"));
                airports.setName(resultSet.getString("COUNTRY"));
                airportsList.add(airports);

            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return airportsList;
    }
}
