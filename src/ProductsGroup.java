import java.util.List;

public class ProductsGroup {
    private String name; //назва групи товарів
    private String description; //опис групи товарів
    public List<Product> products;

    public ProductsGroup(String name, String description, List<Product> products) {
        this.name = name;
        this.description = description;
        this.products = products;
    }

    /*@Override
    public String toString() {
        return "Група книжок на тему " + name + "\n" +
                "опис: " + description + "\n" +
                "Товари: \n" + products;
    }*/

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
