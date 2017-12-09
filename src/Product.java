public class Product {

    private ProductCategory category;
    private String name;
    private int price;
    private int value;

    public Product(ProductCategory category, String name, int price, int value) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.value = value;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
