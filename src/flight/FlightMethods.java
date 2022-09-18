package flight;

import connect.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlightMethods extends Connect {

    Scanner sc = new Scanner(System.in);

    public void insertFlight(Flights flights) {
        String sqlInsertFlight = "insert into \"qa2\".flights (airplane_model, " +
                "departure_time, " +
                "airport_departure_code, " +
                "airport_arrival_code, " +
                "flight_duration, " +
                "count_place, " +
                "flight_number) " +
                "values (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlInsertFlight)) {

            System.out.println("Введите модель самолёта (например: у-204 ):");
            preparedStatement.setString(1, flights.setAirplaneModel(sc.nextLine()));

            System.out.println("Введите дату и время рейса (Формат даты YYYY-MM-DD hh:mm:ss):");
            preparedStatement.setTimestamp(2, flights.setDepartureTime(Timestamp.valueOf(sc.nextLine())));

            System.out.println("Введите код аэропорта откуда будет вылет самолёта например: NQZ233:");
            preparedStatement.setString(3, flights.setAirportDepartureCode(sc.nextLine()));

            System.out.println("Введите код аэропорта куда будет происходить прилёт самолёта например: 495jgrk:");
            preparedStatement.setString(4, flights.setAirportArrivalCode(sc.nextLine()));

            preparedStatement.setInt(5, flights.getFlightDuration());

            preparedStatement.setInt(6, flights.getCountPlace());

            System.out.println("Введите номер рейса (должен быть уникальным) например: 96plo");
            preparedStatement.setString(7, flights.setFlightNumber(sc.nextLine()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void deleteFlight(Flights flights) {
        String sqlDeleteFlight = "delete from \"qa2\".flights where id = ?";

        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlDeleteFlight)) {

            preparedStatement.setInt(1, flights.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void updateFlight(Flights flights) {
        String sqlUpdateFlight = "update \"qa2\".flights set airplane_model = ?," +
                " departure_time = ?," +
                "                       airport_departure_code = ?," +
                "                       airport_arrival_code = ?," +
                "                       flight_duration = ?," +
                "                       count_place = ? where flight_number = ?";

        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlUpdateFlight)) {

            System.out.println("Введите номер рейса (должно быть уникальным) например KG 312");
            preparedStatement.setString(7, flights.setFlightNumber(sc.nextLine()));

            System.out.println("Введите модель самолёта (например {Ту-204}):");
            preparedStatement.setString(1, flights.setAirplaneModel(sc.nextLine()));

            System.out.println("Введите дату и время рейса (Формат даты YYYY-MM-DD hh:mm:ss):");
            preparedStatement.setTimestamp(2, flights.setDepartureTime(Timestamp.valueOf(sc.nextLine())));

            System.out.println("Введите код аэропорта откуда будет вылет самолёта (например KG):");
            preparedStatement.setString(3, flights.setAirportDepartureCode(sc.nextLine()));

            System.out.println("Введите код аэропорта куда будет происходить прилёт самолёта (например KG):");
            preparedStatement.setString(4, flights.setAirportArrivalCode(sc.nextLine()));

            preparedStatement.setInt(5, flights.getFlightDuration());

            preparedStatement.setInt(6, flights.getCountPlace());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<Flights> getFlightNumber(Flights flights) {
        List<Flights> flightsList = new ArrayList<>();
        String sqlSelectFlightNumber = "select f.*, a.city_name, a.airport_code, a.code_country, count(flight_id) " +
                "from \"qa2\".flights f" +
                "         inner join \"qa2\".tickets t on f.id = t.flight_id" +
                "         inner join \"qa2\".airports a on a.airport_code = f.airport_arrival_code " +
                "where f.id = 5 " +
                "group by a.code_country, a.airport_code, f.id";


        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlSelectFlightNumber);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                flights.setId(resultSet.getInt("ID"));
                flights.setAirplaneModel(resultSet.getString("AIRPLANE_MODEL"));
                flights.setDepartureTime(resultSet.getTimestamp("DEPARTURE_TIME"));
                flights.setAirportDepartureCode(resultSet.getString("AIRPORT_DEPARTURE_CODE"));
                flights.setAirportArrivalCode(resultSet.getString("AIRPORT_ARRIVAL_CODE"));
                flights.setFlightDuration(resultSet.getInt("FLIGHT_DURATION"));
                flights.setCountPlace(resultSet.getInt("COUNT_PLACE"));
                flights.setFlightNumber(resultSet.getString("FLIGHT_NUMBER"));
                flights.setCityName(resultSet.getString("CITY_NAME"));
                flights.setAirportCode(resultSet.getString("AIRPORT_CODE"));
                flights.setCodeCountry(resultSet.getString("CODE_COUNTRY"));
                System.out.println("COUNT: " +resultSet.getInt("COUNT"));
                flightsList.add(flights);
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return flightsList;
    }

    public List<Flights> getFlightsFromDataBase() {
        List<Flights> flightsList = new ArrayList<>();
        String sqlSelectAllFlights = "select f.*,  a.city_name, a.airport_code, a.code_country " +
                "from qa2.flights f " +
                "inner join qa2.tickets t on f.id = t.flight_id " +
                "inner join qa2.airports a on a.airport_code = f.airport_arrival_code " +
                "where f.departure_time between '2017-01-01 10:00:00' and '2021-12-31 12:00:00'" +
                "  and f.airport_departure_code like 'BKA'" +
                "  and f.airport_arrival_code like 'FRU'";

        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlSelectAllFlights);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Flights flights = new Flights();
                flights.setId(resultSet.getInt("ID"));
                flights.setAirplaneModel(resultSet.getString("AIRPLANE_MODEL"));
                flights.setDepartureTime(resultSet.getTimestamp("DEPARTURE_TIME"));
                flights.setAirportDepartureCode(resultSet.getString("AIRPORT_DEPARTURE_CODE"));
                flights.setAirportArrivalCode(resultSet.getString("AIRPORT_ARRIVAL_CODE"));
                flights.setFlightDuration(resultSet.getInt("FLIGHT_DURATION"));
                flights.setCountPlace(resultSet.getInt("COUNT_PLACE"));
                flights.setFlightNumber(resultSet.getString("FLIGHT_NUMBER"));
                flights.setCityName(resultSet.getString("CITY_NAME"));
                flights.setAirportCode(resultSet.getString("AIRPORT_CODE"));
                flights.setCodeCountry(resultSet.getString("CODE_COUNTRY"));
                flightsList.add(flights);

            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return flightsList;
    }
}
