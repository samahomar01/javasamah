package departmentStore.database;

import departmentStore.dataModel.order.Order;

public interface ProductDB {

    public Order getProduct(int id);

    public void updateProduct(Order order);

    public void deleteProduct(int id);

}
