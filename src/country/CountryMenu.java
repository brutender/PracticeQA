package country;

import airport.Airports;

import java.util.List;
import java.util.Scanner;

public class CountryMenu {
    public static void start() {

        CountryMethods countryMethods = new CountryMethods();
        Countries countries = new Countries();
        Airports airports = new Airports();

        boolean exitMainMenu = true;
        Scanner sc = new Scanner(System.in);
        while (exitMainMenu) {
            System.out.println("Меню стран: ");
            System.out.println("Добавление страны - 1");
            System.out.println("Удаление страны - 2");
            System.out.println("Изменение аэропорта по коду страны - 3");
            System.out.println("Поиск страны по коду - 4");
            System.out.println("Вывод списка стран - 5");
            System.out.println("Выход в главное меню - 6");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    countryMethods.insertCountry(countries);
                    break;

                case "2":
                    countryMethods.deleteCountry(countries);
                    break;

                case "3":
                    countryMethods.updateAirportOnCountryCode(airports);
                    break;

                case "4":
                    List<Countries> countriesList = countryMethods.selectCountryOnCodeCountry(countries);
                    if (countriesList.isEmpty()) {
                        try {
                            throw new ExceptionDeleteAndFrom("Страны с таким кодом нет!");
                        } catch (ExceptionDeleteAndFrom e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        for (Countries country : countriesList) {
                            System.out.println(country);
                            System.out.println("----------");
                        }
                    }
                    break;

                case "5":
                    List<Countries> countriesListAll = countryMethods.getCountriesFromDataBase();
                    if (countriesListAll.isEmpty()) {
                        System.out.println("Пустой список");
                    } else {
                        for (Countries country : countriesListAll) {
                            System.out.println(country);
                            System.out.println("----------");
                        }
                    }
                    break;

                case "6":
                    exitMainMenu = false;
                    break;

                default:
                    System.out.println("-------------------------------------------------------------");
                    System.err.println("Ошибка. Введите название из меню!!!");
            }
        }
    }
}
