package country;


import java.util.Scanner;

public class Countries {

    Scanner sc = new Scanner(System.in);

    private Integer id;
    private String name;
    private String countryCode;

    public Integer getId() {
        System.out.println("Введите id страны: ");
        return sc.nextInt();
    }

    public int setId(Integer id) {
        this.id = id;
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + " \n" +
                "NAME: " + this.name + " \n" +
                "COUNTRY_CODE: " + this.countryCode;
    }
}
