package Codegym;

import java.util.ArrayList;
import java.util.List;

public class ProductManagement {
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void showAll() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void addNewProduct(Product product) {
        this.products.add(product);
    }

    public void updateProductById(String id, Product product) {
        int index = findProductById(id);
        if (index != -1) {
            products.set(index, product);
        } else {
            System.out.println("Không tìm thấy");
        }
    }

    public void removeProductById(String id) {
        int index = findProductById(id);
        if (index != -1) {
            products.remove(index);
        } else {
            System.out.println("Không tìm thấy");
        }
    }

    public int findProductById(String id) {
        int index = -1;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int findByProductToPriceMax() {
        int index = -1;
        if (products.isEmpty()) {
            return index;
        }
        double max = products.get(0).getPrice();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getPrice() > max) {
                max = products.get(i).getPrice();
                index = i;
            }
        }
        return index;

    }
}
