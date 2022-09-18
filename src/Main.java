import airport.AirportMenu;
import client.CustomerMenu;
import country.CountryMenu;
import flight.FlightMenu;
import ticketPackage.TicketMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Главное меню " +
                    "Выберите действие:");
            System.out.println("Работа с рейсами - 1");
            System.out.println("Работа со странами - 2");
            System.out.println("Работа с аэропортами - 3");
            System.out.println("Работа с клиентами - 4");
            System.out.println("Работа с билетами - 5");
            System.out.println("Выход из программы - 6");

            String choice = sc.nextLine();

            switch (choice) {
                case "1": FlightMenu.start();
                case "2": CountryMenu.start();
                case "3": AirportMenu.start();
                case "4": CustomerMenu.start();
                case "5": TicketMenu.start();
                case "6": System.exit(0);
                default: {
                    System.out.println("-------------------------------------------------------------");
                    System.err.println("Ошибка. Введите название из меню!");
                }
            }
        }
    }
}
