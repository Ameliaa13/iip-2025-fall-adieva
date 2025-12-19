package cmd;

import java.util.Scanner;
import cmd.Auth.service.*;
import cmd.Product.Entity.ProductEntity;
import cmd.Product.service.*;
import cmd.Sell.Entity.SellEntity;
import cmd.Sell.repository.SellRepoImpl;
import cmd.Sell.service.*;


public class Main {
    
    private static Scanner scanner = new Scanner(System.in);
    private static AuthService authService = new AuthService();
    private static ProductService productService = new ProductService();
    private static SellService sellService = new SellService();
    
    public static void main(String[] args) {
        System.out.println("Тестовый логин и пароль Amelia 2222");
        
        if (!login()) {
            System.out.println("Неверные данные для входа");
            return;
        }
        
        boolean running = true;
        while (running) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    productMenu();
                    break;
                case 2:
                    sellMenu();
                    break;
                case 3:
                    viewSales();
                    break;
                case 4:
                    salesStatistics();
                    break;
                case 5:
                    System.out.println("Выход");
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }
    
    private static boolean login() {
        System.out.print("Логин: ");
        String username = scanner.nextLine();
        System.out.print("Пароль: ");
        String password = scanner.nextLine();
        return authService.login(username, password);
    }
    
    private static void showMenu() {
        System.out.println("\n--- Главное меню ---");
        System.out.println("1) Управление товарами");
        System.out.println("2) Создать продажу");
        System.out.println("3) Просмотр продаж");
        System.out.println("4) Статистика продаж за период");
        System.out.println("5) Выход");
        System.out.print("Выберите опцию: ");
    }
    
    private static void productMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Управление товарами ---");
            System.out.println("1) Добавить товар");
            System.out.println("2) Просмотреть все товары");
            System.out.println("3) Найти товар по ID");
            System.out.println("4) Удалить товар");
            System.out.println("5) Назад");
            System.out.print("Выберите опцию: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("Название товара: ");
                    String name = scanner.nextLine();
                    System.out.print("Цена: ");
                    double price = scanner.nextDouble();
                    System.out.print("Количество: ");
                    int quantity = scanner.nextInt();
                    productService.addProduct(name, price, quantity);
                    break;
                case 2:
                    for (ProductEntity p : productService.findAll())
                        System.out.println(p.print());
                    break;
                case 3:
                    System.out.print("ID товара: ");
                    int id = scanner.nextInt();
                    System.out.println(productService.getById(id));
                    break;
                case 4:
                    System.out.print("ID товара для удаления: ");
                    id = scanner.nextInt();
                    productService.removeById(id);
                    break;
                case 5:
                    back = true;
                    break;
            }
        }
    }
    
    private static void sellMenu() {
        System.out.println("\n--- Создание продажи ---");
        System.out.print("ID товара: ");
        int productId = scanner.nextInt();
        System.out.print("Количество: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Имя клиента: ");
        String customerName = scanner.nextLine();
        
        sellService.createSell(productId, quantity, customerName);
    }
    
    private static void viewSales() {
        System.out.println("\n--- Все продажи ---");
        for (SellEntity s : sellService.getAllSells())
            System.out.println(s.print());
    }

    private static void salesStatistics() {
        System.out.println("\n--- Статистика продаж за период ---");
        System.out.println("Введите даты в формате dd.mm.yyyy");
        System.out.print("Начальная дата (включительно): ");
        String startDate = scanner.nextLine();
        System.out.print("Конечная дата (включительно): ");
        String endDate = scanner.nextLine();
        
        sellService.showSalesStatistics(startDate, endDate);
    }
}