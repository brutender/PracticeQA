package country;

import airport.Airports;
import connect.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountryMethods extends Connect {

    Scanner sc = new Scanner(System.in);

    public void insertCountry(Countries countries) {
        String sqlInsertCountry = "insert into \"qa2\".countries (name, country_code) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlInsertCountry)) {

            System.out.println("Введите название Страны:");
            preparedStatement.setString(1, countries.sc.nextLine());

            System.out.println("Введите код страны (например KG)");
            preparedStatement.setString(2, countries.sc.nextLine());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void deleteCountry(Countries countries) {
        String sqlDeleteCountry = "delete from \"qa2\".countries where id = ?";

        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlDeleteCountry)) {

            preparedStatement.setInt(1, countries.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            try {
                throw new ExceptionDeleteAndFrom("Такого id нет!");
            } catch (ExceptionDeleteAndFrom ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void updateAirportOnCountryCode(Airports airports) {
        String sqlUpdateAirport = "update \"qa2\".airports set city_name = ?, airport_code = ? where code_country = ?";

        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlUpdateAirport)) {

            System.out.println("Введите код страны (например KG):");
            preparedStatement.setString(3, airports.setCodeCountry(sc.nextLine()));

            System.out.println("Введите название города:");
            preparedStatement.setString(1, airports.setCityName(sc.nextLine()));

            System.out.println("Введите код аэропорта(например KOL09):");
            preparedStatement.setString(2, airports.setAirportCode(sc.nextLine()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<Countries> selectCountryOnCodeCountry(Countries countries) {
        List<Countries> countriesList = new ArrayList<>();
        String sqlSelectCountryOnCodeCountry = "select * from \"qa2\".countries " +
                "where country_code = 'KAZ'";
        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlSelectCountryOnCodeCountry);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                countries.setId(resultSet.getInt("ID"));
                countries.setName(resultSet.getString("NAME"));
                countries.setCountryCode(resultSet.getString("COUNTRY_CODE"));
                countriesList.add(countries);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return countriesList;
    }

    public List<Countries> getCountriesFromDataBase() {
        List<Countries> countriesList = new ArrayList<>();
        String sqlSelectAllFromCountries = "select * from \"qa2\".countries";

        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlSelectAllFromCountries);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Countries countries = new Countries();
                countries.setId(resultSet.getInt("ID"));
                countries.setName(resultSet.getString("NAME"));
                countries.setCountryCode(resultSet.getString("COUNTRY_CODE"));
                countriesList.add(countries);
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return countriesList;
    }
}
