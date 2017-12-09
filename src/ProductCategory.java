import java.util.ArrayList;
import java.util.List;

public class ProductCategory {

    private String name;
    private List<Product> products;

    public ProductCategory(String name) {
        products = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }
}
