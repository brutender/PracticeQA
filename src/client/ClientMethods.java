package client;

import connect.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//
public class ClientMethods extends Connect {

    Scanner sc = new Scanner(System.in);

    public void insertClients(Clients clients) {
        String sqlInsertClient = "insert into \"qa2\".clients (inn, passport_id, fio, gender, country_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlInsertClient)) {

            System.out.println("Введите ИНН (например 20309201224567) должен быть уникальным:");
            preparedStatement.setLong(1, clients.setInn(sc.nextLong()));

            System.out.println("Введите ФИО клиента:");
            sc.nextLine();
            preparedStatement.setString(3, clients.setFio(sc.nextLine()));

            System.out.println("Введите ID паспорта (например 12345678) должно быть уникальным:");
            preparedStatement.setInt(2, clients.setPassportId(sc.nextInt()));


            System.out.println("Введите пол клиента:");
            sc.nextLine();
            preparedStatement.setString(4, clients.setGender(sc.nextLine()));

            System.out.println("Введите ID страны клиента");
            preparedStatement.setInt(5, clients.setCountryId(sc.nextInt()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void deleteClient(Clients clients) {
        String sqlDeleteClient = "delete from \"qa2\".clients where id = ?";

        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlDeleteClient)) {

            System.out.println("Введите id клиента:");
            preparedStatement.setInt(1, clients.setId(sc.nextInt()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            try {
                throw new ExceptionNoClient("Такого клиента нет!");
            } catch (ExceptionNoClient ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void updateClient(Clients clients) {
        String sqlUpdateClient = "update \"qa2\".clients set country_id = ?, fio = ?, gender = ?, inn = ? where passport_id = ?";

        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlUpdateClient)) {

            System.out.println("Введите ID паспорта (например 12345678) должен быть уникальным:");
            preparedStatement.setInt(5, clients.setPassportId(sc.nextInt()));

            System.out.println("Введите ИНН (например 48573698569) должен быть уникальным:");
            preparedStatement.setLong(4, clients.setInn(sc.nextLong()));

            System.out.println("Введите ФИО клиента:");
            sc.nextLine();
            preparedStatement.setString(2, clients.setFio(sc.nextLine()));


            System.out.println("Введите пол клиента:");
            preparedStatement.setString(3, clients.setGender(sc.nextLine()));

            System.out.println("Введите ID страны клиента");
            preparedStatement.setInt(1, clients.setPassportId(sc.nextInt()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<Clients> getClientAndCountry(Clients clients) {
        List<Clients> clientsList = new ArrayList<>();
        String sqlSelectClientAndCountry = "select c.*, co.name as country from \"qa2\".clients c " +
                "inner join \"qa2\".countries co on co.id = c.country_id " +
                "where c.passport_id = '2344'";

        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlSelectClientAndCountry);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                clients.setId(resultSet.getInt("ID"));
                clients.setInn(resultSet.getLong("INN"));
                clients.setPassportId(resultSet.getInt("PASSPORT_ID"));
                clients.setFio(resultSet.getString("FIO"));
                clients.setGender(resultSet.getString("GENDER"));
                clients.setCountryId(resultSet.getInt("COUNTRY_ID"));
                clients.setName(resultSet.getString("COUNTRY"));
                clientsList.add(clients);

            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return clientsList;
    }

    public List<Clients> getAllClientFromDataBase() {
        List<Clients> clientsList = new ArrayList<>();
        String sqlSelectAll = "select c.*, co.name as country from \"qa2\".clients c " +
                "inner join \"qa2\".countries co on co.id = c.country_id ";

        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlSelectAll);
            ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Clients clients = new Clients();
                    clients.setId(resultSet.getInt("ID"));
                    clients.setInn(resultSet.getLong("INN"));
                    clients.setPassportId(resultSet.getInt("PASSPORT_ID"));
                    clients.setFio(resultSet.getString("FIO"));
                    clients.setGender(resultSet.getString("GENDER"));
                    clients.setCountryId(resultSet.getInt("COUNTRY_ID"));
                    clients.setName(resultSet.getString("COUNTRY"));
                    clientsList.add(clients);
                }
            } catch (SQLException e) {
            throw new RuntimeException();
        }
        return clientsList;
    }
}
