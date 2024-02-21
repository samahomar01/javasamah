package departmentStore.promotion;
import departmentStore.Exeption. PriceNegativeException;
import java.util.Date;
import  departmentStore.Exeption.PromotionTypeMismatchException;
import departmentStore.Exeption.InvalidPercentageException;
import departmentStore.Exeption.NegattivePriceException;
import departmentStore.dataModel.order.Order;

public abstract class Promotion {
    private String name;
    protected Constants.PromotionType promotionType;
    protected Validator validator;

    public Promotion(String name, Constants.PromotionType promotionType, Validator validator) {
        this.name = name;
        this.promotionType = promotionType;
        this.validator = validator;
    }

    public void setValidator(Validator validator, Date today) throws PromotionTypeMismatchException {
        if (validator != null && !validator.validate(today)) {
            throw new PromotionTypeMismatchException();
        }
        this.validator = validator;
    }

    public void setPromotionType(Constants.PromotionType promotionType) throws PromotionTypeMismatchException {
        if (promotionType == null) {
            throw new PromotionTypeMismatchException();
        }
        this.promotionType = promotionType;
    }









    public abstract boolean isValid(Date today) throws InvalidPercentageException,PromotionTypeMismatchException;



    public abstract Order  applyPromotion(Order o)throws PriceNegativeException;
}