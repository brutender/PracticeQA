package flight;

import airport.Airports;

import java.sql.Timestamp;
import java.util.Scanner;

public class Flights extends Airports {

    Scanner sc = new Scanner(System.in);

    private Integer id;
    private String airplaneModel;
    private Timestamp departureTime;
    private String airportDepartureCode;
    private String airportArrivalCode;
    private Integer flightDuration;
    private Integer countPlace;
    private String flightNumber;

    public int setId(Integer id) {
        this.id = id;
        return 0;
    }

    public String setAirplaneModel(String airplaneModel) {
        this.airplaneModel = airplaneModel;
        return airplaneModel;
    }

    public Timestamp setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
        return departureTime;
    }

    public String setAirportDepartureCode(String airportDepartureCode) {
        this.airportDepartureCode = airportDepartureCode;
        return airportDepartureCode;
    }

    public String setAirportArrivalCode(String airportArrivalCode) {
        this.airportArrivalCode = airportArrivalCode;
        return airportArrivalCode;
    }

    public int setFlightDuration(Integer flightDuration) {
        this.flightDuration = flightDuration;
        return 0;
    }

    public int setCountPlace(Integer countPlace) {
        this.countPlace = countPlace;
        return 0;
    }

    public String setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
        return flightNumber;
    }

    public Integer getId() {
        System.out.println("Введите id рейса:");
        return sc.nextInt();
    }

    public String getAirplaneModel() {
        return airplaneModel;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public String getAirportDepartureCode() {
        return airportDepartureCode;
    }

    public String getAirportArrivalCode() {
        return airportArrivalCode;
    }


    public Integer getFlightDuration() {
        System.out.println("Введите расстояние (длительность полёта):");
        return sc.nextInt();
    }

    public Integer getCountPlace() {
        System.out.println("Введите количество мест:");
        return sc.nextInt();
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + " \n" +
                "AIRPLANE_MODEL: " + this.airplaneModel + " \n" +
                "DEPARTURE_TIME: " + this.departureTime + " \n" +
                "AIRPORT_DEPARTURE_CODE: " + this.airportDepartureCode + " \n" +
                "AIRPORT_ARRIVAL_CODE: " + this.airportArrivalCode + " \n" +
                "FLIGHT_DURATION: " + this.flightDuration + " \n" +
                "COUNT_PLACE: " + this.countPlace + " \n" +
                "FLIGHT_NUMBER: " + this.flightNumber + " \n" +
                "CITY_NAME: " + getCityName() + " \n" +
                "AIRPORT_CODE: " + getAirportCode() + " \n" +
                "CODE_COUNTRY: " + getCodeCountry();
    }
}
