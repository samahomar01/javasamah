package departmentStore.database;

import departmentStore.dataModel.order.Order;

public interface OrderDB {
    public Order getOrder(int id);

    public void updateorder(Order order);

    public void deleteorder(int id);

}
