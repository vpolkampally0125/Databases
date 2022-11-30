import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MakeOrder {
    static String connectionUrl;
    public static void main(String[] args) {
        connectionUrl = 
        "jdbc:sqlserver://CSDSWINLAB017\\SQLEXPRESS;"
        + "database=university;"         //change this
        + "user=dbuser;"                 //change this
        + "password=scsd431134dscs;"   //change this
        + "encrypt=true;"
        + "trustServerCertificate=true;"
        + "loginTimeout=15;";
    
        String[] order = {"hamburger", "fries"};
        runMakeOrderQuery("JEff", order);
    }

    public static void runMakeOrderQuery(String customerName, String[] items){
        try (Connection connection = DriverManager.getConnection(connectionUrl); 
                Statement statement = connection.createStatement();){

            // get cost of order
            int orderCost = 0;
            for(String item : items){
                String itemCostQuery = "SELECT cost from MenuItem where MenuItem.name = " + item + ";";
                ResultSet itemCost = statement.executeQuery(itemCostQuery);
                orderCost += Integer.parseInt(itemCost.getString(0));
            }

            // insert order into ORDER table
            String insertOrderQuery = "INSERT INTO order (cost) VALUES ("+orderCost+");";
            statement.executeQuery(insertOrderQuery);

            // get ID of the inserted Order
            String getOrderIDQuery = "SELECT max(orderID) FROM Order";
            String orderID = statement.executeQuery(getOrderIDQuery).getString(0);

            // get ID of the customer
            String getCustomerIDQuery = "SELECT ID FROM customer where customer.name = " + customerName;
            String customerID = statement.executeQuery(getCustomerIDQuery).getString(0);

            // insert orderID and customerID into BUYS table
            String insertNewBuys = "INSERT INTO buys (customerID, orderID) VALUES(" + customerID + ", " + orderID + ");";
            statement.executeQuery(insertNewBuys);

            // insert each ordered items into ORDERED ITEMS table
            for(String item : items){
                String selectMenuItemIDQuery = "SELECT ID from MenuItem where MenuItem.name = " + item + ";";
                int menuID = Integer.parseInt(statement.executeQuery(selectMenuItemIDQuery).getString(0));
                String insertOrderItemQuery = "INSERT INTO orderedItems (orderID, menuID) values(" + orderID + ", " + menuID + ");";
                statement.executeQuery(insertOrderItemQuery);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
