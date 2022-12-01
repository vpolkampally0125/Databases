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
        String connectionUrl = 
            "jdbc:sqlserver://CSDSWINLAB017\\SQLEXPRESS;"
            + "database=restaurantDB;"         //change this
            + "user=dbuser;"                 //change this
            + "password=scsd431134dscs;"   //change this
            + "encrypt=true;"
            + "trustServerCertificate=true;"
            + "loginTimeout=15;";
    }

    // for use case 1
    public static void insertOrderSP(String customerName, String menuItem) {

        String callStoredProc = "{call dbo.MakeNewOrder(?,?)}";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                PreparedStatement prepsInsertOrder = connection.prepareStatement(callStoredProc);) {
            prepsInsertOrder.setString(1, customerName);
            prepsInsertOrder.setString(2, menuItem);
            prepsInsertOrder.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // for use case 2
    public static void insertMenuItemSP(String menuItem, float price, String ingredient, String equipment) {

        String callStoredProc = "{call dbo.MakeNewMenuItem(?,?, ?, ?)}";

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

    // for use case 4
    public static void updateOrderSP(int orderID, String menuItem) {

        String callStoredProc = "{call dbo.ChangeOrder(?,?)}";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                PreparedStatement prepsUpdateOrder = connection.prepareStatement(callStoredProc);) {
            prepsUpdateOrder.setInt(1, orderID);
            prepsUpdateOrder.setString(2, menuItem);
            prepsUpdateOrder.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // for use case 5
    public static void deleteMenuItem(String menuItem) {

        String callStoredProc = "{call dbo.DeleteMenuItem(?)}";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                PreparedStatement prepsDeleteMenuItem = connection.prepareStatement(callStoredProc);) {
            prepsDeleteMenuItem.setString(1, menuItem);
            prepsDeleteMenuItem.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // for use case 6
    public static void deleteIngredient(String ingredient) {

        String callStoredProc = "{call dbo.DeleteIngredient(?)}";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                PreparedStatement prepsDeleteIngredient = connection.prepareStatement(callStoredProc);) {
            prepsDeleteIngredient.setString(1, ingredient);
            prepsDeleteIngredient.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // for use case 7
}
