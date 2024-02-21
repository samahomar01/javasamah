package departmentStore.dataModel.paymentMethod;

import departmentStore.Exeption.InvalidPercentageException;

public class CreditCard extends PaymentMethod {
    private double overhead;

    public CreditCard(double overhead)throws InvalidPercentageException
    {
       if ((overhead>0 ) && (overhead<1)) throw new InvalidPercentageException();
        else{
         this.overhead = overhead;
     }

        }


    @Override
    public double calculateAmount(double amount) {
        // TODO Add the overhead percentage to the amount.
        double overheadAmount = amount * (overhead / 100);
        return amount + overheadAmount;

    }

}
