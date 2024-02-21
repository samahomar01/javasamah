package departmentStore.Exeption;

import  departmentStore.Exeption.PromotionTypeMismatchException;
public class PromotionTypeMismatchException extends Exception {
    public PromotionTypeMismatchException() {
        super("نوع الترقية لا يتناسب مع الفاحص المقدم من قبل المستخدم.");
    }
}
