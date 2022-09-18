package airport;

import country.Countries;

import java.util.Scanner;

public class Airports extends Countries {

    Scanner sc = new Scanner(System.in);

    private Integer id;
    private String cityName;
    private String airportCode;
    private String codeCountry;

    public Integer getId() {
        System.out.println("Введите id аэропорта:");
        return sc.nextInt();
    }

    public int setId(Integer id) {
        this.id = id;
        return 0;
    }

    public String getCityName() {
        return cityName;
    }

    public String setCityName(String cityName) {
        this.cityName = cityName;
        return cityName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public String setAirportCode(String airportCode) {
        this.airportCode = airportCode;
        return airportCode;
    }

    public String getCodeCountry() {
        return codeCountry;
    }

    public String setCodeCountry(String codeCountry) {
        this.codeCountry = codeCountry;
        return codeCountry;
    }

    @Override
    public String toString() {
        return "ID: " + id + " \n" +
                "CITY_NAME: " + cityName + " \n" +
                "AIRPORT_CODE: " + airportCode + " \n" +
                "CODE_COUNTRY: " + codeCountry + " \n" +
                "COUNTRY: " + getName();
    }
}
