package departmentStore.dataModel.paymentMethod;

public abstract class PaymentMethod extends SuperPayment {
    public abstract double calculateAmount(double amount);
}
