package departmentStore.Exeption;

public class InvalidListOfItemsException extends Exception {
  public  InvalidListOfItemsException(){
      super("please provide a valid product");
}
}