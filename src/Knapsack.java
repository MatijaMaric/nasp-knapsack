import java.util.*;
import java.util.stream.Collectors;

public class Knapsack {

    private List<Product> products;
    private List<ProductCategory> categories;

    public Knapsack() {
        products = new ArrayList<>();
        categories = new ArrayList<>();
    }

    private void addCategory(ProductCategory category) {
        categories.add(category);
    }

    private void addProduct(Product product) {
        products.add(product);
        if (!categories.contains(product.getCategory())) {
            addCategory(product.getCategory());
        }
        product.getCategory().addProduct(product);
    }

    private void addProduct(ProductCategory category, String name, int price, int satisfaction) {
        addProduct(new Product(category, name, price, satisfaction));
    }
    private void addProduct(String category, String name, int price, int satisfaction) {
        List<ProductCategory> query = categories.stream().filter(x -> x.getName() == category).limit(1).collect(Collectors.toList());
        ProductCategory productCategory = query.size() == 1 ? query.get(0) : new ProductCategory(category);
        addProduct(productCategory, name, price, satisfaction);
    }

    private List<Product> knapsackSelect(int budget) {
        List<Product> ans = new ArrayList<>();
        int[][] w = new int[budget+1][products.size()+1];
        boolean[][] decisions = new boolean[budget+1][products.size()+1];

        int k = 1;
        for (ProductCategory category : categories) {
            int cat = 0;
            for (Product product : category.getProducts()) {
                cat++
                for (int c = 1; c <= budget; ++c) {
                    for (int i = 0; i < cat; ++i) {
                        if (c >= product.getPrice() && w[])
                    }
                    int kNo = w[c][k-1];
                    int kYes;
                    if (c >= product.getPrice()) {
                        kYes = w[c-product.getPrice()][k-1] + product.getSatisfaction();
                    } else {
                        kYes = kNo;
                    }
                    if (kYes > kNo) {
                        w[c][k] = kYes;
                        decisions[c][k] = true;
                    } else {
                        w[c][k] = kNo;
                    }
                }
                k++;
            }
        }

        int c = budget;
        k = products.size();
        do {
            if (decisions[c][k]) {
                ans.add(products.get(k-1));
                c -= products.get(k-1).getPrice();
            }
            k--;
        } while (c > 0 && k > 0);
        return ans;
    }

    public static void main(String[] args) {
        Knapsack knapsack = new Knapsack();
        knapsack.addProduct("GPU", "Nvidia GTX 1080 Ti", 710, 13000);
        knapsack.addProduct("GPU", "Nvidia GTX 980 Ti", 500, 11000);
        knapsack.addProduct("GPU", "AMD Radeon R9 Fury", 400, 9000);

        knapsack.addProduct("CPU", "AMD Ryzen Threadripper 1950X", 900, 22000);
        knapsack.addProduct("CPU", "Intel Core i7-6950X", 1450, 20000);
        knapsack.addProduct("CPU", "Intel Core i7-8700K", 400, 16000);
        knapsack.addProduct("CPU", "AMD Ryzen 7 1800X", 400, 15500);

        knapsack.addProduct("Storage", "HDD 4TB", 100, 1000);
        knapsack.addProduct("Storage", "SSD 256GB", 100, 5000);
        knapsack.addProduct("Storage", "SSD 1TB", 300, 10000);
        knapsack.addProduct("Storage", "HDD 4TB + SSD 256GB", 400, 6000);

        knapsack.addProduct("RAM", "16GB", 200, 1000);
        knapsack.addProduct("RAM", "32GB", 450, 3000);

        List<Product> configuration = knapsack.knapsackSelect(2500);

        int totalSatisfaction = 0;
        int totalPrice = 0;
        for (Product part : configuration) {
            System.out.println(part.getName() + ": " + part.getPrice());
            totalPrice += part.getPrice();
            totalSatisfaction += part.getSatisfaction();
        }

        System.out.println("Total: " + totalPrice);
        System.out.println("Satisfaction: " + totalSatisfaction);
    }
}
