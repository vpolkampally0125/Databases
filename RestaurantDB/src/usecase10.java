//use case 10

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FindOrderHistory {
    // Connect to your database.
    // Replace server name, username, and password with your
    // credentials
    public static void main(String[] args) {
        String connectionUrl = "jdbc:sqlserver://localhost;"
                + "database=university; " // change this
                + "user=DBAccountWhateverItIs; " // change this
                + "password=whatEverItIs; " // change this
                + "encrypt=true;"
                + "trustServerCertificate=true;"
                + "loginTimeout=15;";
    }

    public static void runFindOrderHistoryQuery(String customerName) {

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();) {

            ResultSet buys = null;
            ResultSet orders = null;
            ResultSet orderItems = null;
            ResultSet menuItems = null;

            // selects all buy tuples that the input customerName is related to
            String selectBuys = "SELECT * from Customer JOIN Buys where Customer.id = Buys.Customer.id";
            buys = statement.executeQuery(selectOrders);

            // selects all orders that a customer has bought
            PreparedStatement selectOrders = conn
                    .prepareStatement("SELECT * from ? JOIN Orders where ?.Order.id = Orders.id");
            selectOrders.setString(1, buys);
            orders = statement.executeQuery(selectOrders);

            // selects all ordered items that a customer has bought
            PreparedStatement selectOrderedItems = conn
                    .prepareStatement("SELECT * from ? JOIN OrderItems where ?.Order.id = OrderItems.id");
            selectOrderedItems.setString(1, orders);
            orderItems = statement.executeQuery(selectOrderedItems);

            // selects all menu items that a customer has bought
            PreparedStatement selectMenuItems = conn
                    .prepareStatement("SELECT * from ? JOIN MenuItems where ?.OrderItems.id = OrderItems.id");
            selectOrderedItems.setString(1, orderItems);
            menuItems = statement.executeQuery(selectMenuItems);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
