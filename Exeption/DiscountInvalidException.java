package departmentStore.Exeption;


public class DiscountInvalidException extends Exception {
    public DiscountInvalidException() { super("نسبة الخصم يجب أن تكون بين 0 و 100.");
    }
}
