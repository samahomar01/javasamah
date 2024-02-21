package departmentStore.promotion;
import departmentStore.Exeption.ProductNullException;
import java.util.ArrayList;
import java.util.Date;
import departmentStore.Exeption. PriceNegativeException;

import departmentStore.Exeption.PromotionTypeMismatchException;
import departmentStore.dataModel.order.Order;
import departmentStore.dataModel.order.OrderItem;
import departmentStore.dataModel.proudect.Product;
import departmentStore.Exeption. PriceNegativeException;
import departmentStore.Exeption.NegattivePriceException;
public class Match extends Promotion {
    private int qtyitemsOrder;
    private int qtyitemsPayed;
    private ArrayList<Product> products;

    public Match(String name, Validator validator, int qtyitemsOrder,
            int qtyitemsPayed, ArrayList<Product> products) {
        super(name, null, validator);
        this.qtyitemsOrder = qtyitemsOrder;
        this.qtyitemsPayed = qtyitemsPayed;
        this.products = products;
        if (validator instanceof DayOfWeek) {
            promotionType = Constants.PromotionType.DAYOFWEEK;
        } else if (validator instanceof Period) {
            promotionType = Constants.PromotionType.DAYOFWEEK;
        } else {
            promotionType = Constants.PromotionType.ALWAYS;
        }
    }

    // Getters and Setters for products ArrayList

    public void addProduct(Product product) throws ProductNullException {
        if (product == null) {
            throw new ProductNullException();
        }
        products.add(product);
    }


    public void removeProduct(Product product) {
        products.remove(product);
    }


    public ArrayList<Product> getProducts() {
        return products;
    }

    @Override
    public boolean isValid(Date today) {
        return promotionType == Constants.PromotionType.ALWAYS || validator.validate(today);
    }





    @Override
    public Order applyPromotion(Order order) throws PriceNegativeException{
        int n = qtyitemsOrder;
        int m = qtyitemsPayed;

        Order newOrder = new Order(order.getId(), order.getDate(), order.getOrderItems());

        for (OrderItem orderItem : newOrder.getOrderItems()) {
            if (products.contains(orderItem.getProduct())) {
                double oldUnitPrice = orderItem.getPrice();
                double newUnitPrice;
                int l = orderItem.getQty();
                if (l % n == 0) {
                    newUnitPrice = (oldUnitPrice * (n - m)) / n;
                } else {
                    newUnitPrice = (oldUnitPrice * (l - (l % n))) / l + (oldUnitPrice * (l % n)) / l;
                }
                orderItem.setPrice(newUnitPrice);
            }
        }

        order.truePromotion(); // Mark the promotion as applied

        return newOrder;
    }
    }