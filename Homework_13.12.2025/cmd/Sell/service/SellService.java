package cmd.Sell.service;

import cmd.Sell.Entity.SellEntity;
import cmd.Sell.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import cmd.Product.Entity.ProductEntity;
import cmd.Product.service.ProductService;

public class SellService {
    
    private SellRepo repo = new SellRepoImpl();
    private ProductService productService = new ProductService();

    public void createSell(int productId, int quantity, String customer) {
        ProductEntity product = productService.getById(productId);

        if (product.getQuantity() < quantity) {
            System.out.println("Недостаточно товара на складе");
            return;
        }

        SellEntity sell = new SellEntity(0, productId, quantity, quantity*product.getPrice(), customer);
        repo.addSell(sell);


        productService.updateQuantity(productId, product.getQuantity() - quantity);

        System.out.println("Создана продажа на сумму " + quantity*product.getPrice());
    }

    public ArrayList<SellEntity> getAllSells() {
        return repo.findAll();
    }
    
    public SellEntity getSellById(int id) {
        return repo.findById(id);
    }

        public void showSalesStatistics(String startDateStr, String endDateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate startDate = LocalDate.parse(startDateStr, formatter);
            LocalDate endDate = LocalDate.parse(endDateStr, formatter);
            
            LocalDateTime startDateTime = startDate.atStartOfDay();
            LocalDateTime endDateTime = endDate.atTime(23, 59, 59);
            
            List<SellEntity> salesInPeriod = repo.getSalesByPeriod(startDateTime, endDateTime);
            
            if (salesInPeriod.isEmpty()) {
                System.out.println("Нет продаж за указанный период.");
                return;
            }
            
            System.out.println("\n(   Статистика продаж за период " + startDateStr + " - " + endDateStr + "   )");
            System.out.println("Всего продаж: " + salesInPeriod.size());
            
            double totalRevenue = 0;
            int totalItemsSold = 0;
            
            for (SellEntity sell : salesInPeriod) {
                totalRevenue += sell.getTotalPrice();
                totalItemsSold += sell.getQuantity();
            }
            
            System.out.println("Общая выручка: " + String.format("%.2f", totalRevenue));
            System.out.println("Общее количество проданных товаров: " + totalItemsSold);
            System.out.println("Средняя сумма продажи: " + String.format("%.2f", totalRevenue / salesInPeriod.size()));
            
            System.out.println("\n=== Детализация продаж ===");
            for (SellEntity sell : salesInPeriod) {
                System.out.println(sell);
            }
            
        } catch (DateTimeParseException e) {
            System.out.println("Ошибка формата даты! Используйте формат ДД.ММ.ГГГГ");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

}
