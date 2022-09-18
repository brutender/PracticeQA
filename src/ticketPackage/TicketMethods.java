package ticketPackage;

import connect.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketMethods extends Connect{

    Scanner sc = new Scanner(System.in);

    public void insertTicket(Tickets tickets) {
        String sqlInsertTicket = "insert into \"qa2\".tickets (client_id, flight_id, receiving_ticket, number_ticket) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlInsertTicket)) {

            System.out.println("Введите id клиента:");
            preparedStatement.setInt(1, tickets.setClientId(sc.nextInt()));

            System.out.println("Введите id рейса:");
            preparedStatement.setInt(2, tickets.setFlightId(sc.nextInt()));

            preparedStatement.setTimestamp(3, tickets.getReceivingTicket());

            System.out.println("Введите номер билета (например: 1332323136267 ):");
            preparedStatement.setLong(4, tickets.setNumberTicket(sc.nextLong()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void deleteTicket(Tickets tickets) {
        String sqlDeleteTicket = "delete from \"qa2\".tickets where number_ticket = ?";

        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlDeleteTicket)) {

            System.out.println("Введите номер билета (например: 1332323136267):");
            preparedStatement.setLong(1, tickets.setNumberTicket(sc.nextLong()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            try {
                throw new ExceptionNoTicket("Такого билета нет");
            } catch (ExceptionNoTicket ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void updateTicket(Tickets tickets) {
        String sqlUpdateTicket = "update \"qa2\".tickets set client_id = ?, flight_id = ?, receiving_ticket = ? where number_ticket = ?";

        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlUpdateTicket)) {

            System.out.println("Введите номер билета (например: 1332323136267):");
            preparedStatement.setLong(4, tickets.setNumberTicket(sc.nextLong()));

            System.out.println("Введите id клиента:");
            preparedStatement.setInt(1, tickets.setClientId(sc.nextInt()));

            System.out.println("Введите id рейса:");
            preparedStatement.setInt(2, tickets.setFlightId(sc.nextInt()));

            preparedStatement.setTimestamp(3, tickets.getReceivingTicket());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<Tickets> getTicketClientsAndNameCountry() {
        List<Tickets> ticketsList = new ArrayList<>();
        String sqlTicketClient = "select t.id, c.fio, t.flight_id, t.receiving_ticket, t.number_ticket, c.passport_id, co.name as country from \"qa2\".tickets t " +
                "inner join \"qa2\".clients c on c.id = t.client_id " +
                "inner join \"qa2\".countries co on co.id = c.country_id " +
                "where c.passport_id = '2345678'";

        try (PreparedStatement preparedStatement = connection().prepareStatement(sqlTicketClient);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Tickets tickets = new Tickets();
                tickets.setId(resultSet.getInt("ID"));
                tickets.setFio(resultSet.getString("FIO"));
                tickets.setFlightId(resultSet.getInt("FLIGHT_ID"));
                tickets.setReceivingTicket(resultSet.getTimestamp("RECEIVING_TICKET"));
                tickets.setNumberTicket(resultSet.getLong("NUMBER_TICKET"));
                tickets.setPassportId(resultSet.getInt("PASSPORT_ID"));
                tickets.setName(resultSet.getString("COUNTRY"));
                ticketsList.add(tickets);
            }
        } catch (SQLException e) {
                throw new RuntimeException();
        }
        return ticketsList;
    }

    public List<Tickets> getAllTicketFromDataBase() {
        List<Tickets> ticketsListAll = new ArrayList<>();
        String sqlAllTickets = "select t.id, c.fio, t.flight_id, t.receiving_ticket, t.number_ticket, c.passport_id, co.name as country " +
                "from \"qa2\".tickets t" +
                "         inner join \"qa2\".clients c on c.id = t.client_id" +
                "         inner join \"qa2\".countries co on co.id = c.country_id " +
                "order by t.number_ticket, c.passport_id desc";

        try (Statement statement = connection().createStatement();
             ResultSet resultSet = statement.executeQuery(sqlAllTickets)) {

            while (resultSet.next()) {
                Tickets tickets = new Tickets();
                tickets.setId(resultSet.getInt("ID"));
                tickets.setFio(resultSet.getString("FIO"));
                tickets.setFlightId(resultSet.getInt("FLIGHT_ID"));
                tickets.setReceivingTicket(resultSet.getTimestamp("RECEIVING_TICKET"));
                tickets.setNumberTicket(resultSet.getLong("NUMBER_TICKET"));
                tickets.setPassportId(resultSet.getInt("PASSPORT_ID"));
                tickets.setName(resultSet.getString("COUNTRY"));
                ticketsListAll.add(tickets);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return ticketsListAll;
    }
}
