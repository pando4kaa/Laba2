/**
 * Authors: Tuhai Anastasia, Rafikov Rinat
 * File: ProductGroup.java
 * Class, that describes ProductsGroup.
 */
import java.util.ArrayList;

public class ProductsGroup {
    private String name; //назва групи товарів
    private String description; //опис групи товарів
    public ArrayList<Product> products;

    public ProductsGroup(String name, String description) {
        this.name = name;
        this.description = description;
        this.products = new ArrayList<>();
    }

    public ProductsGroup(String name, String description, ArrayList<Product> products) {
        this.name = name;
        this.description = description;
        this.products = products;
    }

    public String toStr() {
        StringBuilder str = new StringBuilder("Група книжок на тему " + name +
                "\n\tОпис: " + description +
                "\n\tТовари: ");
        for (Product product : products){
            str.append(product.toStr());
        }
        return str.toString();
    }

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

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
