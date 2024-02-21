package departmentStore.Exeption;

public class ProductNullException extends Exception {
    public ProductNullException() {
        super("لا يمكن أن يكون المنتج فارغًا.");
    }
}
