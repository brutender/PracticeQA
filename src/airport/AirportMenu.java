package airport;

import java.util.List;
import java.util.Scanner;

public class AirportMenu {
    public static void start() {

        AirportMethods airportMethods = new AirportMethods();
        Airports airports = new Airports();

        boolean exitMainMenu = true;
        Scanner sc = new Scanner(System.in);
        while (exitMainMenu) {
            System.out.println("Меню аэропортов: ");
            System.out.println("Добавление аэропорта - 1");
            System.out.println("Удаление аэропорта - 2");
            System.out.println("Изменение аэропорта - 3");
            System.out.println("Поиск аэропорта по коду - 4");
            System.out.println("Вывод списка аэропортов - 5");
            System.out.println("Выход в главное меню - 6");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    airportMethods.insertAirport(airports);
                    break;

                case "2":
                    airportMethods.deleteAirport(airports);
                    break;

                case "3":
                    airportMethods.updateAirport(airports);
                    break;

                case "4":
                    List<Airports> airportsCodeList = airportMethods.selectAirportOnAirportCode(airports);
                    if (airportsCodeList.isEmpty()) {
                        try {
                            throw new ExceptionNoAirportCode("Такого аэропорта нет!");
                        } catch (ExceptionNoAirportCode e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        for (Airports airport : airportsCodeList) {
                            System.out.println(airport);
                            System.out.println("----------");
                        }
                    }
                    break;

                case "5":
                    List<Airports> airportsListAll = airportMethods.getAirportsFromDataBase();
                    if (airportsListAll.isEmpty()) {
                        System.out.println("Пустой список");
                    } else {
                        for (Airports airportsAll : airportsListAll) {
                            System.out.println(airportsAll);
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
