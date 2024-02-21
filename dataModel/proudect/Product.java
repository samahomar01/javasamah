package departmentStore.dataModel.proudect;


import departmentStore.Exeption.PriceNegativeException;

public class Product {
    private int id;
    private String name;
    private Category category;
    private double cost;
    private double unitPrice;

    public Product(int id, String name, Category category, double cost, double unitPrice) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.cost = cost;
        this.unitPrice = unitPrice;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public double getCost() {
        return cost;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setUnitPrice(double unitPrice) throws PriceNegativeException {
        if (unitPrice < 0) {
            throw new PriceNegativeException();
        }
        this.unitPrice = unitPrice;

    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", cost=" + cost +
                ", unitPrice=" + unitPrice +
                '}';
    }

}