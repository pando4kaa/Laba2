import javax.swing.table.TableModel;
import java.util.List;

public class GoodsParser {
    public static String[][] parseGroups(Warehouse warehouse){
        List<ProductsGroup> groups = warehouse.getGroups();
        String[][] result = new String[groups.size()][2];

        for(int i = 0; i < groups.size(); i++){
            result[i][0] = groups.get(i).getName();
            result[i][1] = groups.get(i).getDescription();
        }
        return result;
    }

    public static String[][] parseGroupGoods(ProductsGroup groupByTitle) {
        List<Product> products = groupByTitle.products;
        String[][] result = new String[products.size()][5];

        for(int i = 0; i < products.size(); i++){
            result[i][0] = products.get(i).getName();
            result[i][1] = products.get(i).getAuthor();
            result[i][2] = products.get(i).getDescription();
            result[i][3] = Integer.toString(products.get(i).getQuantity());
            result[i][4] = Double.toString(products.get(i).getPrice());
        }
        return result;
    }
}
