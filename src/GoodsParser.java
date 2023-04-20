/**
 * Authors: Tuhai Anastasia, Rafikov Rinat
 * File: GoodsParser.java
 *
 * Клас GoodsParser містить методи для отримання даних про товари зі складу у вигляді двовимірних масивів.
 * Методи parseGroups, parseGroupGoods та parseAllGoods повертають дані про групи товарів,
 * товари в групі та всі товари відповідно. Метод getGroupPrices повертає інформацію про загальну вартість товарів у групі.
 */

import java.util.List;

public class GoodsParser {
    public static Object[][] parseGroups(Warehouse warehouse){
        List<ProductsGroup> groups = warehouse.getGroups();
        Object[][] result = new Object[groups.size()][2];

        for(int i = 0; i < groups.size(); i++){
            result[i][0] = groups.get(i);
            result[i][1] = groups.get(i).getDescription();
        }
        return result;
    }

    public static Object[][] parseGroupGoods(ProductsGroup groupByTitle) {
        List<Product> products = groupByTitle.products;
        Object[][] result = new Object[products.size()][6];

        for(int i = 0; i < products.size(); i++){
            result[i][0] = products.get(i);
            result[i][1] = products.get(i).getAuthor();
            result[i][2] = products.get(i).getDescription();
            result[i][3] = products.get(i).getPublisher();
            result[i][4] = Integer.toString(products.get(i).getQuantity());
            result[i][5] = Double.toString(products.get(i).getPrice());
        }
        return result;
    }

    public static Object[][] parseAllGoods(Warehouse warehouse) {

        int amountProducts = 0;

        for(ProductsGroup group : warehouse.getGroups()){
            for (Product product : group.products){
                amountProducts++;
            }
        }

        Object[][] result = new Object[amountProducts][7];

        int index = 0;

        for(ProductsGroup group : warehouse.getGroups()){
            for (Product product : group.products){
                result[index][0] = group;
                result[index][1] = product;
                result[index][2] = product.getAuthor();
                result[index][3] = product.getDescription();
                result[index][4] = product.getPublisher();
                result[index][5] = Integer.toString(product.getQuantity());
                result[index][6] = Double.toString(product.getPrice());
                index++;
            }
        }
        return result;
    }

    public static Object[][] getGroupPrices(Warehouse warehouse){
        List<ProductsGroup> groups = warehouse.getGroups();
        Object[][] result = new Object[groups.size()][2];

        for(int i = 0; i < groups.size(); i++){
            result[i][0] = groups.get(i);
            result[i][1] = Statistics.calculateTotalValueByGroup(groups.get(i));
        }
        return result;
    }
}
