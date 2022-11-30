import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.Scanner;

public class UseCases {

    static String connectionUrl;

    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) {
        connectionUrl = "jdbc:sqlserver://localhost;"
                + "database=university;"
                + "user=dbuser;" // might need to change this to your database user such as "sa". not good to use
                                 // "sa"
                + "password=ChangeToYourPasswordForUser-dbuser;"
                + "encrypt=true;"
                + "trustServerCertificate=true;"
                + "loginTimeout=30;";
    }

    public static void insertMenuItemSP(String menuItem, float price, String ingredient, String equipment) {

        String callStoredProc = "{call dbo.insertMenuItem(?,?, ?, ?)}";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                PreparedStatement prepsInsertMenuItem = connection.prepareStatement(callStoredProc);) {
            prepsInsertMenuItem.setString(1, menuItem);
            prepsInsertMenuItem.setFloat(2, price);
            prepsInsertMenuItem.setString(3, ingredient);
            prepsInsertMenuItem.setString(4, equipment);
            prepsInsertMenuItem.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
