public class Product {
    private String name;
    private String author;
    private String description;
    private String publisher;
    private int quantity;
    private double price;

    public Product(String name, String author, String description, String publisher, int quantity, double price) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.publisher = publisher;
        this.quantity = quantity;
        this.price = price;
    }
    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
