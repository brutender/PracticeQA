package ticketPackage;

import java.util.List;
import java.util.Scanner;

public class TicketMenu {
    public static void start() {

        TicketMethods ticketMethods = new TicketMethods();
        Tickets tickets = new Tickets();

        boolean exitMainMenu = true;
        Scanner sc = new Scanner(System.in);
        while (exitMainMenu) {
            System.out.println("Меню билетов: ");
            System.out.println("Добавление билета");
            System.out.println("Удаление билета");
            System.out.println("Изменение билета");
            System.out.println("Поиск билета по номеру");
            System.out.println("Вывод списка билетов");
            System.out.println("Выход в главное меню");

            String choice = sc.nextLine();

            switch (choice) {
                case "Добавление билета" : ticketMethods.insertTicket(tickets);
                case "Удаление билета" : ticketMethods.deleteTicket(tickets);
                case "Изменение билета" : ticketMethods.updateTicket(tickets);
                case "Поиск билета по номеру" : {
                    List<Tickets> ticketsListNumber = ticketMethods.getTicketClientsAndNameCountry();
                    if (ticketsListNumber.isEmpty()) {
                        try {
                            throw new ExceptionNoTicket("Такого билета нет");
                        } catch (ExceptionNoTicket e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        for (Tickets ticket : ticketsListNumber) {
                            System.out.println(ticket);
                            System.out.println("----------");
                        }
                    }
                }
                case "Вывод списка билетов" : {
                    List<Tickets> ticketsListAll = ticketMethods.getAllTicketFromDataBase();
                    if (ticketsListAll.isEmpty()) {
                        System.out.println("Список пустой");
                    } else {
                        for (Tickets ticketAll : ticketsListAll) {
                            System.out.println(ticketAll);
                            System.out.println("----------");
                        }
                    }
                }
                case "Выход в главное меню" : exitMainMenu = false;
                default : {
                    System.out.println("-------------------------------------------------------------");
                    System.err.println("Ошибка. Введите название из меню!");
                }
            }
        }
    }
}
