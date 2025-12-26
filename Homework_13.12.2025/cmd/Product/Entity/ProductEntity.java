package cmd.Product.Entity;
public class ProductEntity {

    private String name;
    private double price;
    private long id;
    private int quantity;

    public ProductEntity(String name, double price, long id, int quantity) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.quantity = quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public long getId() {
        return this.id;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int q) {
        this.quantity = q;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String print() {
        return String.format("Информация о продукте: id=%d, name='%s', price=%.2f, quantity=%d", 
            id, name, price, quantity);
    }

}
