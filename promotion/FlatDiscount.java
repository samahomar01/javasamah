package departmentStore.promotion;

import java.util.Date;

import departmentStore.Exeption.DiscountInvalidException;
import departmentStore.Exeption.PriceNegativeException;
import departmentStore.dataModel.order.Order;
import departmentStore.dataModel.order.OrderItem;

public class FlatDiscount extends Promotion {
    private String name;
    private double discount;

    public void setDiscount(double discount) throws DiscountInvalidException {
        if (discount < -100 || discount > 100) {
            throw new DiscountInvalidException();
        } else {
            this.discount = discount;
        }
    }

    public FlatDiscount(String name, Validator validator) throws DiscountInvalidException {
        super(name, null, validator);
        if (validator instanceof DayOfWeek) {
            promotionType = Constants.PromotionType.DAYOFWEEK;
        } else if (validator instanceof Period) {
            promotionType = Constants.PromotionType.PERIODIC;
        } else {
            promotionType = Constants.PromotionType.ALWAYS;
        }
    }

    @Override
    public boolean isValid(Date today) {
        return promotionType == Constants.PromotionType.ALWAYS || validator.validate(today);
    }

    @Override
    public Order applyPromotion(Order order) throws PriceNegativeException {
        double totalCost = order.getOrderTotal();
        double discountAmount = 0.0;

        if (totalCost >= 500 && totalCost < 700) {
            // Apply 5% discount
            discountAmount = 0.05;
        } else if (totalCost >= 700 && totalCost < 1000) {
            // Apply 7% discount
            discountAmount = 0.07;
        } else if (totalCost >= 1000) {
            // Apply 10% discount
            discountAmount = 0.1;
        }

        Order newOrder = new Order(order.getId(), order.getDate(), order.getOrderItems());
        for (OrderItem orderItem : newOrder.getOrderItems()) {
            double newPriceAfterDiscount = orderItem.getPrice() - (orderItem.getPrice() * discountAmount);
            if (newPriceAfterDiscount < 0) {
                throw new PriceNegativeException();
            }
            orderItem.setPrice(newPriceAfterDiscount);
        }

        order.setDiscount(discountAmount);
        order.truePromotion(); // Mark the promotion as applied

        return newOrder;
    }
}