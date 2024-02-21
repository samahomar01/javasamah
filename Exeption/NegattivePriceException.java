package departmentStore.Exeption;

    public class NegattivePriceException extends  Exception{
        public NegattivePriceException (){
            super("the price must be a positive value");

        }

    }
