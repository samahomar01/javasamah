package departmentStore.promotion;

import java.util.Date;

import departmentStore.Exeption.DiscountInvalidException;
import departmentStore.Exeption.PriceNegativeException;
import departmentStore.dataModel.order.Order;
import departmentStore.dataModel.order.OrderItem;
import departmentStore.dataModel.proudect.Category;

public class CategoryDis extends Promotion {
	Category category;
	double discount;

	public CategoryDis(String name, Validator validator, Category category, double discount) {
		super(name, null, validator);
		this.category = category;
		this.discount = discount / 100;
		if (validator instanceof DayOfWeek) {
			promotionType = Constants.PromotionType.DAYOFWEEK;
		} else if (validator instanceof Period) {
			promotionType = Constants.PromotionType.DAYOFWEEK;
		} else {
			promotionType = Constants.PromotionType.ALWAYS;
		}
	}

	public void setDiscount(double discount) throws DiscountInvalidException {
		if (discount < 0 || discount > 100) {
			throw new DiscountInvalidException();
		} else {
			this.discount = discount / 100; // قم بتحويل الخصم إلى نسبة
		}
	}

	@Override
	public boolean isValid(Date today) {
		return promotionType == Constants.PromotionType.ALWAYS || validator.validate(today);
	}

	@Override
	public Order applyPromotion(Order order) throws PriceNegativeException {
		Order newOrder = new Order(order.getId(), order.getDate(), order.getOrderItems());
		double totalDiscount = 0;

		for (OrderItem orderItem : newOrder.getOrderItems()) {
			if (orderItem.getProduct().getCategory().equals(category)) {
				double itemDiscount = orderItem.getPrice() * discount;
				double newUnitPrice = orderItem.getPrice() - itemDiscount;
				orderItem.setPrice(newUnitPrice);
				totalDiscount += itemDiscount;
			} else {
				// لا شيء لأننا نقوم بنسخ جميع عناصر الطلب
			}
		}

		// تعيين إجمالي الخصم على الطلب
		order.setDiscount(totalDiscount);
		return newOrder;
	}
}
