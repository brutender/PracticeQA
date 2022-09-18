package ticketPackage;

import client.Clients;

import java.sql.Timestamp;
import java.util.Scanner;

public class Tickets extends Clients {

    Scanner sc = new Scanner(System.in);

    private Integer id;
    private Integer clientId;
    private Integer flightId;
    private Timestamp receivingTicket;
    private Long numberTicket;

    public Integer getId() {
        return id;
    }

    public int setId(Integer id) {
        this.id = id;
        return id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public int setClientId(Integer clientId) {
        this.clientId = clientId;
        return clientId;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public int setFlightId(Integer flightId) {
        this.flightId = flightId;
        return flightId;
    }

    public Timestamp getReceivingTicket() {
        System.out.println("Введите дату и время рейса (Формат даты{YYYY-MM-DD hh:mm:ss}):");
        return Timestamp.valueOf(sc.nextLine());
    }

    public void setReceivingTicket(Timestamp receivingTicket) {
        this.receivingTicket = receivingTicket;
    }

    public Long getNumberTicket() {
        return numberTicket;
    }

    public long setNumberTicket(Long numberTicket) {
        this.numberTicket = numberTicket;
        return numberTicket;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + " \n" +
                "FIO: " + getFio() + " \n" +
                "FLIGHT_ID: " + this.flightId + " \n" +
                "RECEIVING_TICKET: " + this.receivingTicket + " \n" +
                "NUMBER_TICKET: " + this.numberTicket + " \n" +
                "PASSPORT_ID: " + getPassportId() + " \n" +
                "COUNTRY: " + getName();
    }
}
