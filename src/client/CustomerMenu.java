package client;

import java.util.List;
import java.util.Scanner;

public class CustomerMenu {
    public static void start() {

        ClientMethods clientMethods = new ClientMethods();
        Clients clients = new Clients();

        boolean exitMainMenu = true;
        Scanner sc = new Scanner(System.in);
        while (exitMainMenu) {
            System.out.println("Меню клиентов: ");
            System.out.println("Добавление клиента - 1");
            System.out.println("Удаление клиента - 2");
            System.out.println("Изменение клиента - 3");
            System.out.println("Поиск клиента по номеру паспорта - 4");
            System.out.println("Вывод списка клиентов - 5");
            System.out.println("Выход в главное меню - 6");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    clientMethods.insertClients(clients);
                    break;

                case "2":
                    clientMethods.deleteClient(clients);
                    break;

                case "3":
                    clientMethods.updateClient(clients);
                    break;

                case "4":
                    List<Clients> clientsList = clientMethods.getClientAndCountry(clients);
                    if (clientsList.isEmpty()) {
                        try {
                            throw new ExceptionNoClient("Такого клиента нет!");
                        } catch (ExceptionNoClient e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        for (Clients client : clientsList) {
                            System.out.println(client);
                            System.out.println("----------");
                        }
                    }
                    break;

                case "5":
                    List<Clients> clientsListAll = clientMethods.getAllClientFromDataBase();
                    if (clientsListAll.isEmpty()) {
                        System.out.println("Пустой список");
                    } else {
                        for (Clients client: clientsListAll) {
                            System.out.println(client);
                            System.out.println("----------");
                        }
                    }
                    break;

                case "6":
                    exitMainMenu = false;
                    break;

                default:
                    System.out.println("-------------------------------------------------------------");
                    System.err.println("Ошибка. Введите название из меню!");
            }
        }
    }
}
