package flight;

import java.util.List;
import java.util.Scanner;

public class FlightMenu {
    public static void start() {

        FlightMethods flightMethods = new FlightMethods();
        Flights flights = new Flights();

        boolean exitMainMenu = true;
        Scanner sc = new Scanner(System.in);
        while (exitMainMenu) {
            System.out.println("Меню рейсов: ");
            System.out.println("Добавление рейса - 1");
            System.out.println("Удаление рейса - 2");
            System.out.println("Изменение рейса - 3");
            System.out.println("Поиск рейса по номеру - 4");
            System.out.println("Вывод списка рейсов - 5");
            System.out.println("Выход в главное меню - 6");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    flightMethods.insertFlight(flights);
                    break;

                case "2":
                    flightMethods.deleteFlight(flights);
                    break;

                case "3":
                    flightMethods.updateFlight(flights);
                    break;

                case "4":
                    List<Flights> flightsListNumber = flightMethods.getFlightNumber(flights);
                    if (flightsListNumber.isEmpty()) {
                        try {
                            throw new ExceptionNoFlights("РЕЙСА НЕ СУЩЕСТВУЕТ!!!");
                        } catch (ExceptionNoFlights e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        for (Flights flightNumber : flightsListNumber) {
                            System.out.println(flightNumber);
                            System.out.println("---------");
                        }
                    }
                    break;

                case "5":
                    List<Flights> flightsList = flightMethods.getFlightsFromDataBase();
                    if (flightsList.isEmpty()) {
                        System.out.println("EMPTY FLIGHTS");
                    } else {
                        for (Flights flight : flightsList) {
                            System.out.println(flight);
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
