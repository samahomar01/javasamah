package departmentStore;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import departmentStore.Exeption.InvalidListOfItemsException;
import departmentStore.Exeption.PriceNegativeException;
import departmentStore.Exeption.InvalidPercentageException;
import departmentStore.Exeption.PromotionTypeMismatchException;
import departmentStore.Exeption.NullPrudectExeption;
import departmentStore.dataModel.order.Order;
import departmentStore.dataModel.order.OrderItem;
import departmentStore.dataModel.paymentMethod.Cash;
import departmentStore.dataModel.paymentMethod.CreditCard;
import departmentStore.dataModel.paymentMethod.PaymentMethod;
import departmentStore.dataModel.proudect.Category;
import departmentStore.dataModel.proudect.Product;
import departmentStore.promotion.CategoryDis;
import departmentStore.promotion.DayOfWeek;
import departmentStore.promotion.FlatDiscount;
import departmentStore.promotion.Match;
import departmentStore.promotion.Promotion;
import departmentStore.promotion.Validator;
import departmentStore.Exeption.DiscountInvalidException;

public class DepartmentStore {
    public static final String csvFile = "./departmentStore/MOCK_DATA.csv";
    public static final String delimiter = ",";

    public static void main(String[] args) {
        // Read the text file and store products in a product list
        ArrayList<Product> products = new ArrayList<Product>();
        ArrayList<Order> orders = new ArrayList<Order>();

        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while ((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);
                int id = Integer.parseInt(tempArr[0]);
                String name = tempArr[1];
                Category category = Category.valueOf(tempArr[2]);
                double cost = Double.parseDouble(tempArr[3]);
                double price = cost * 1.45;
                Product p = new Product(id, name, category, cost, price);
                products.add(p);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error reading file:\n" + e.toString());
        }


        try {
            Random r = new Random(10);
            int numberOrders = (int) (10 * r.nextDouble());
            for (int i = 1; i <= numberOrders; ++i) {
                Order ord = new Order(i + 100, new Date());
                int numberItems = (int) (10 * r.nextDouble());
                for (int j = 1; j <= numberItems; ++j) {
                    int productIndex = (int) (1000 * r.nextDouble());
                    Product p = products.get(productIndex);
                    int qty = (int) (10 * r.nextDouble());
                    OrderItem ordItem = new OrderItem(qty, p.getUnitPrice(), p);
                    ord.addItem(ordItem);
                }
                orders.add(ord);
                System.out.println(ord.toString());
            }
        } catch (NullPrudectExeption | PriceNegativeException | InvalidListOfItemsException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }

        System.out.println("*****************************************************************");


        try {
            // Flat Discount Test
            System.out.println("--------------------Flat Discount Test--------------------------");
            Validator flatDiscountValidator = new DayOfWeek(new String[]{"Monday", "Tuesday", "Wednesday", "Thursday"});
            FlatDiscount flatDiscount = new FlatDiscount("Fixed discount", flatDiscountValidator);

            // Try setting a negative discount
            try {
                flatDiscount.setDiscount(-0.01);
            } catch (DiscountInvalidException e) {
                System.out.println("Discount error: " + e.getMessage());
                e.printStackTrace(System.out);
            }

            // Continue with the rest of your code
            for (int i = 0; i < 5; i++) {
                Order order = orders.get(i);
                System.out.println(order);
                try {
                    flatDiscount.setDiscount(0.05); // Set a valid discount value
                    Order discountedOrder = order.applyPromotion(flatDiscount);
                    System.out.println("After applying flat discount:");
                    System.out.println(discountedOrder);

                    PaymentMethod paymentMethod = new CreditCard(3);
                    System.out.println("CreditCard(3%): " + discountedOrder.getOrderAfterPaid(paymentMethod));
                    System.out.println();
                } catch (DiscountInvalidException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}

