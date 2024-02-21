package departmentStore.dataModel.order;

import java.util.ArrayList;
import java.util.Date;
import departmentStore.Exeption.PriceNegativeException;
import departmentStore.Exeption.InvalidListOfItemsException;
import departmentStore.Exeption.InvalidPercentageException;
import departmentStore.Exeption.PromotionTypeMismatchException;
import departmentStore.Exeption.NullPrudectExeption;
import departmentStore.dataModel.paymentMethod.PaymentMethod;
import departmentStore.dataModel.proudect.Product;
import departmentStore.promotion.Promotion;

public class Order {
    private int id;
    private Date date;
    private boolean isPromotion;
    private ArrayList<OrderItem> orderItems = new ArrayList<>();
    private double discount;

    public Order(int id, Date date) throws InvalidListOfItemsException {
        this.id = id;
        this.date = date;
        this.isPromotion = false;
    }

    public Order(int id, Date date, ArrayList<OrderItem> orderItems) {
        this.id = id;
        this.date = date;
        this.orderItems = orderItems;
        this.isPromotion = false;
    }

    public boolean isPromotionApplied() {
        return isPromotion;
    }

    public void truePromotion() {
        this.isPromotion = true;
    }

    public void falsePromotion() {
        this.isPromotion = false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    // public void setTotalCost(double totalCost) {
    // this.totalCost = totalCost;
    // }

    // public double getTotalCost() {
    // return totalCost;
    // }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void addItem(OrderItem item) {
        if (!orderItems.contains(item)) {
            orderItems.add(item);
        }
    }


    public void addItem(int qty, double price, Product product) throws NullPrudectExeption, PriceNegativeException
    {

            OrderItem newOrderItem = new OrderItem(qty, price, product);

            if (orderItems.contains(newOrderItem)) {
                removeItem(newOrderItem);
                addItem(newOrderItem);
            }}
    public void removeItem(OrderItem item)  {
        if (orderItems.contains(item)) {
            orderItems.remove(item);
        }
    }

    public void removeItem(Product product)  throws NullPrudectExeption,PriceNegativeException {
        OrderItem newOrderItem = new OrderItem(0, 0, product);
        if (orderItems.contains(newOrderItem)) {
            OrderItem oldOrderItem = orderItems.get(orderItems.indexOf(newOrderItem));
            oldOrderItem.setQty(oldOrderItem.getQty() - 1);
            if (oldOrderItem.getQty() == 0) {
                this.removeItem(oldOrderItem);
            }
        }
    }

    public String toString() {
        String r = "========= Order =============================================================\n";
        r += String.format("Order number: %1$10d     Order Date: %2$te/%2$tm/%2$tY \n", id, date);
        for (OrderItem orderItem : orderItems) {
            r += (orderItem.toString() + " \n");
        }
        r += String.format("============== T O T A L =================: %1$15.3f", getOrderTotal());
        return r;
    }

    public double getOrderTotal() {
        double total = 0;
        for (OrderItem orderItem : orderItems) {
            total += (orderItem.getQty() * orderItem.getPrice());
        }
        return total - total * discount;
    }

    public double getOrderAfterPaid(PaymentMethod paymentMethod) {
        return paymentMethod.calculateAmount(getOrderTotal());
    }

    public Order applyPromotion(Promotion p)throws PriceNegativeException, InvalidPercentageException,PromotionTypeMismatchException{
        if (p.isValid(date)) {
            return p.applyPromotion(this);
        }
        return this;
    }

}