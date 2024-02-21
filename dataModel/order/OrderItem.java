package departmentStore.dataModel.order;

import java.util.Objects;
import departmentStore.Exeption.PriceNegativeException;
import departmentStore.Exeption.NegattivePriceException;
import departmentStore.Exeption.NullPrudectExeption;
import departmentStore.dataModel.proudect.Product;
import departmentStore.Exeption.PriceNegativeException;
public class OrderItem {
    private int qty;
    private double price;
    private Product product;

    public OrderItem(int qty, double price, Product product) throws NullPrudectExeption, PriceNegativeException {
        {
            this.setQty(qty);
            this.setPrice(price);
            this.setProduct(product);
        }

    }


    public void setQty(int qty) {
        if (qty > 0) {
            this.qty = qty;
        } else {
            this.qty = 1;
        }
    }

    public int getQty() {
        return qty;
    }

    public void setPrice(double price)throws PriceNegativeException {
        if (price > 0) {
            this.price = price;
        }else {
            throw new PriceNegativeException();
        }

    }

    public double getPrice() {
        return price;
    }

    public void setProduct(Product product) throws NullPrudectExeption
    {
        if (product != null) {
            this.product = product;
            if (this.price == 0) {
                this.price = product.getUnitPrice();
            }
        } else {
            throw new  NullPrudectExeption();
        }
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public boolean equals(Object item) {
        if (this == item) {
            return true;
        }
        if (item == null || getClass() != item.getClass()) {
            return false;
        }
        OrderItem orderItem = (OrderItem) item;
        return Objects.equals(product, orderItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }

    @Override
    public String toString() {
        String r = "";
        r += String.format("%1$-10d %2$-30s  %3$10d  %4$10.3f %5$10.3f",
                product.getId(), product.getName(), qty, price, qty * price);
        return r;
    }
}