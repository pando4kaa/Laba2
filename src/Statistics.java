import java.util.ArrayList;

public class Statistics {
//    public static void showAllProducts(ArrayList<ProductsGroup> groups) {
//        for (ProductsGroup group : groups) {
//            for (Product product : group.getProducts()) {
//                System.out.println(product.getName() + " - " + product.getQuantity() + "шось ще");
//            }
//        }
//    }
//
//    public static void showAllProductsByGroup(ArrayList<ProductsGroup> groups) {
//        for (ProductsGroup group : groups) {
//            System.out.println(group.getName() + ":");
//            for (Product product : group.getProducts()) {
//                System.out.println(product.getName() + " - " + product.getQuantity() + " ...");
//            }
//        }
//    }

    /**
     * Обчислює загальну вартість всіх товарів на складі
     * @param groups список груп товарів
     * @return загальна вартість всіх товарів
     */
    public static double calculateTotalValue(ArrayList<ProductsGroup> groups) {
        double totalValue = 0;
        for (ProductsGroup group : groups) {
            for (Product product : group.getProducts()) {
                totalValue += product.getPrice() * product.getQuantity();
            }
        }
        return totalValue;
    }

    /**
     * Обчислює загальну вартість товарів в заданій групі
     * @param groups список груп товарів
     * @param groupName назва групи товарів, вартість якої треба розрахувати
     * @return загальна вартість товарів в групі з вказаною назвою
     */
    public static double calculateTotalValueByGroup(ArrayList<ProductsGroup> groups, String groupName) {
        double totalValue = 0;
        for (ProductsGroup group : groups) {
            if (group.getName().equalsIgnoreCase(groupName)) {
                for (Product product : group.getProducts()) {
                    totalValue += product.getPrice() * product.getQuantity();
                }
            }
        }
        return totalValue;
    }

}
