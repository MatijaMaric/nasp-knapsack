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

    private void addProduct(ProductCategory category, String name, int price, int value) {
        addProduct(new Product(category, name, price, value));
    }
    private void addProduct(String category, String name, int price, int value) {
        List<ProductCategory> query = categories.stream().filter(x -> x.getName() == category).limit(1).collect(Collectors.toList());
        ProductCategory productCategory = query.size() == 1 ? query.get(0) : new ProductCategory(category);
        addProduct(productCategory, name, price, value);
    }

    private List<Product> knapsackSelect(int budget) {
        List<Product> ans = new ArrayList<>();
        int[][] w = new int[budget+1][products.size()+1];
        boolean[][] decisions = new boolean[budget+1][products.size()+1];

        int k = 1;
        int lastCat = 1;
        for (ProductCategory category : categories) {
            int cat = 0;
            for (Product product : category.getProducts()) {
                cat++;
                int left = 0;
                for (int c = 1; c <= budget; ++c) {
                    for (int i = 0; i < lastCat; ++i) {
                        if (w[c][k-cat-i] > left) {
                            left = w[c][k-cat-i];
                        }
                    }
                    if (product.getPrice() > c) {
                        w[c][k] = left;
                    } else {
                        int max = 0;
                        for (int i = 0; i < lastCat; ++i) {
                            if (c > product.getPrice() && w[c-product.getPrice()][k-cat-i] > max) {
                                max = w[c-product.getPrice()][k-cat-i];
                            }
                        }
                        if (max + product.getPrice() >= left) {
                            w[c][k] = max + product.getValue();
                            decisions[c][k] = true;
                        } else {
                            w[c][k] = left;
                        }
                    }
                }
                k++;
            }
            lastCat = cat;
        }

        int c = budget;
        k = products.size();
        int cat = categories.size()-1;
        do {
            if (decisions[c][k]) {
                ans.add(products.get(k-1));
                c -= products.get(k-1).getPrice();
                k -= categories.get(cat).getProducts().size();
            } else {
                k--;
            }

            cat--;
        } while (c > 0 && k > 0 && cat >= 0);
        int[] x = w[budget];
        boolean[] y = decisions[budget];
        return ans;
    }

    public static void main(String[] args) {
        Knapsack knapsack = new Knapsack();
        knapsack.addProduct("GPU", "AMD Radeon R9 Fury", 4, 9);
        knapsack.addProduct("GPU", "Nvidia GTX 980 Ti", 5, 11);
        knapsack.addProduct("GPU", "Nvidia GTX 1080 Ti", 7, 13);

        knapsack.addProduct("CPU", "AMD Ryzen 7 1800X", 4, 15);
        knapsack.addProduct("CPU", "Intel Core i7-8700K", 4, 16);
        knapsack.addProduct("CPU", "Intel Core i7-6950X", 14, 20);
        knapsack.addProduct("CPU", "AMD Ryzen Threadripper 1950X", 9, 22);

        knapsack.addProduct("Storage", "HDD 4TB", 1, 1);
        knapsack.addProduct("Storage", "SSD 256GB", 1, 5);
        knapsack.addProduct("Storage", "HDD 4TB + SSD 256GB", 2, 6);
        knapsack.addProduct("Storage", "SSD 1TB", 3, 10);
        knapsack.addProduct("Storage", "HDD 4TB + SSD 1TB", 4, 11);

        knapsack.addProduct("RAM", "16GB", 2, 1);
        knapsack.addProduct("RAM", "32GB", 4, 3);

        Scanner scanner = new Scanner(System.in);
        int budget = scanner.nextInt();
        List<Product> configuration = knapsack.knapsackSelect(budget);


        int totalValue = 0;
        int totalPrice = 0;
        for (Product part : configuration) {
            System.out.println(part.getName() + ": " + part.getPrice() + "; val= " + part.getValue());
            totalPrice += part.getPrice();
            totalValue += part.getValue();
        }

        System.out.println("Total: " + totalPrice);
        System.out.println("Value: " + totalValue);

    }
}
