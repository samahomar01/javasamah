package departmentStore.Exeption;
import departmentStore.Exeption.PriceNegativeException;
public class PriceNegativeException extends Exception {
    public PriceNegativeException() {
        super("السعر لا يمكن أن يكون قيمة سالبة.");
    }
}
