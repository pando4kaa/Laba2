public class Product {
    private String name;
    private String author;
    private String description;
    private String publisher;
    private int quantity;
    private double price;

    public String toStr() {
        return "\n\t\tНазва книги: " + name + "" +
                "\n\t\tАвтор: " + author  + "" +
                "\n\t\tОпис: " + description +
                "\n\t\tВидавництво: " + publisher +
                "\n\t\tКількість на складі: " + quantity +
                "\n\t\tЦіна: " + price + "\n";
    }

    @Override
    public String toString() {
        return name;
    }

    public Product(String name, String author, String description, String publisher, int quantity, double price) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.publisher = publisher;
        this.quantity = quantity;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
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

}
