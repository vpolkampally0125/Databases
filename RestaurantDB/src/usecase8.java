//use case 8

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GiveOrdersBasedOnPrice {
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

    public static void runGiveOrdersBasedOnPriceQuery(double cost) {

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();) {

            PreparedStatement ordersByCost = conn.prepareStatement("SELECT * from orders where cost = ?");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
