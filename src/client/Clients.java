package client;

import country.Countries;

public class Clients extends Countries {
    private Integer id;
    private Long inn;
    private Integer passportId;
    private String fio;
    private String gender;
    private Integer countryId;

    public Integer getId() {
        return id;
    }

    public int setId(Integer id) {
        this.id = id;
        return id;
    }

    public Long getInn() {
        return inn;
    }

    public Long setInn(Long inn) {
        this.inn = inn;
        return inn;
    }

    public Integer getPassportId() {
        return passportId;
    }

    public int setPassportId(Integer passportId) {
        this.passportId = passportId;
        return passportId;
    }

    public String getFio() {
        return fio;
    }

    public String setFio(String fio) {
        this.fio = fio;
        return fio;
    }

    public String getGender() {
        return gender;
    }

    public String setGender(String gender) {
        this.gender = gender;
        return gender;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public int setCountryId(Integer countryId) {
        this.countryId = countryId;
        return countryId;
    }

    @Override
    public String toString() {
        return "ID: " + id + " \n" +
                "INN: " + inn + " \n" +
                "PASSPORT_ID: " + passportId + " \n" +
                "FIO: " + fio + " \n" +
                "GENDER: " + gender + " \n" +
                "COUNTRY_ID: " + countryId + " \n" +
                "COUNTRY: " + getName();
    }
}
