public class Product {

    private ProductCategory category;
    private String name;
    private int price;
    private int satisfaction;

    public Product(ProductCategory category, String name, int price, int satisfaction) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.satisfaction = satisfaction;
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

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
