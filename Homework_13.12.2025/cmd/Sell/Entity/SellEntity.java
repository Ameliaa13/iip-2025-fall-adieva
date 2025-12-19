package cmd.Sell.Entity;

import java.time.LocalDateTime;

public class SellEntity {
    
    private long id;
    private long productId;
    private int quantity;
    private double price;
    private String customer;
    private LocalDateTime date;

    public SellEntity(long id, long productId, int quantity, double price, String customer) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.customer = customer;
        this.date = LocalDateTime.now();
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() { 
        return id; 
    }

    public long getProductId() { 
        return productId;
    }

    public int getQuantity() { 
        return quantity;
    }

    public double getTotalPrice() {
         return price;
    }

    public String getCustomerName() {
         return customer; 
    }

    public LocalDateTime getDate() {
        return date; 
    }

    public String print() {
        return String.format("Информация о продаже: saleId=%d, productId=%d, price=%.2f, quantity=%d, customer=%s", 
            id, price, quantity, customer);
    }



}
