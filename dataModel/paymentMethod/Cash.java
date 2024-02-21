package departmentStore.dataModel.paymentMethod;

import departmentStore.Exeption.DiscountInvalidException;
import departmentStore.Exeption.InvalidPercentageException;

public class Cash extends PaymentMethod {
    private double discountPercentage;

    public Cash(double discountPercentage) throws InvalidPercentageException , DiscountInvalidException{
        setDiscountPercentage(discountPercentage);
    }

    public void setDiscountPercentage(double discountPercentage) throws DiscountInvalidException {
        if (discountPercentage >= 0 && discountPercentage <= 100) {
            this.discountPercentage = discountPercentage;
        } else {
            throw new DiscountInvalidException();
        }
    }

    @Override
    public double calculateAmount(double amount) {
        double discountAmount = amount * (discountPercentage / 100);
        return amount - discountAmount;
    }
}
