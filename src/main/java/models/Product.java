package models;

public class Product {
    private String name;
    private int quantity;
    private double quantityPrice;
    private double totalPrice;

    public static final class ProductBuilder {
        private String name;
        private int quantity;
        private double quantityPrice;
        private double totalPrice;

        public ProductBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductBuilder quantityPrice(double quantityPrice) {
            this.quantityPrice = quantityPrice;
            return this;
        }

        public ProductBuilder totalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.name = this.name;
            product.quantity = this.quantity;
            product.quantityPrice = this.quantityPrice;
            product.totalPrice = this.totalPrice;

            return product;
        }
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getQuantityPrice() {
        return quantityPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    @Override
    public String toString() {
        return "name: " + name + ", quantity: " + quantity + ", quantity price: " + quantityPrice + ", total price: " + totalPrice;
    }

    public static ProductBuilder productBuilder() {
        return new ProductBuilder();
    }
}