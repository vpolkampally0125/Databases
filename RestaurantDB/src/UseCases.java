import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.ResultSet;

public class UseCases {

    static final String connectionUrl = "jdbc:sqlserver://CSDSWINLAB017\\SQLEXPRESS;"
        + "database=restaurantDB;"      // DB Name
        + "user=dbuser;"                // user name
        + "password=scsd431134dscs;"    // password
        + "encrypt=true;"
        + "trustServerCertificate=true;"
        + "loginTimeout=15;";

    // for use case 1
    public static void insertOrderSP(String customerName, String menuItem) {

        String callStoredProc = "{call dbo.MakeOrder(?,?)}";

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

    // for use case 3
    public static void changeMenuItem(String menuItem, String ingredientReplaced, String ingredientReplacing,
            String equipmentReplaced, String equipmentReplacing) {

        String callStoredProc = "{call dbo.ChangeMenuItem(?,?,?,?,?)}";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                PreparedStatement prepsChangeMenuItem = connection.prepareStatement(callStoredProc);) {
            prepsChangeMenuItem.setString(1, menuItem);
            prepsChangeMenuItem.setString(2, ingredientReplaced);
            prepsChangeMenuItem.setString(3, ingredientReplacing);
            prepsChangeMenuItem.setString(4, equipmentReplaced);
            prepsChangeMenuItem.setString(5, equipmentReplacing);
            prepsChangeMenuItem.execute();

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
    public static String findMenuItemIngredients(String menuItem) {

        String callStoredProc = "{call dbo.FindMenuItemIngredients(?)}";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                CallableStatement prepsFindMenuItemIngredients = connection.prepareCall(callStoredProc);) {
            prepsFindMenuItemIngredients.setString(1, menuItem);
            
            ResultSet resultSet = prepsFindMenuItemIngredients.executeQuery();
            String res = "Ingredients: ";
            while (resultSet.next()) {
                res += resultSet.getString(1) + " "; 
            }
            return res;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    // for use case 8
    public static String findOrdersBasedOnCost(float cost) {

        String callStoredProc = "{call dbo.FindOrdersBasedOnCost(?,?)}";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
                CallableStatement prepsFindOrders = connection.prepareCall(callStoredProc);) {
            prepsFindOrders.setFloat(1, cost);
            prepsFindOrders.registerOutParameter(2, Types.VARCHAR);
            prepsFindOrders.executeUpdate();
            return "Receipt Number: " + prepsFindOrders.getString(2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    // for use case 9
    public static String findOrderHistory(String name) {

        String callStoredProc = "{call dbo.FindOrderHistory(?)}";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                CallableStatement prepsFindOrderHistory = connection.prepareCall(callStoredProc);) {
            prepsFindOrderHistory.setString(1, name);
            
            ResultSet resultSet = prepsFindOrderHistory.executeQuery();
            String res = "Orders by ";
            while (resultSet.next()) {
                res += "#"+resultSet.getString(1) + ":" + resultSet.getString(2); 
            }
            return res;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    // for use case 10
    public static String findSupplierIngredients(String name) {

        String callStoredProc = "{call dbo.FindSupplierIngredients(?)}";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                CallableStatement prepsFindSupplierIngredients = connection.prepareCall(callStoredProc);) {
            prepsFindSupplierIngredients.setString(1, name);

            ResultSet resultSet = prepsFindSupplierIngredients.executeQuery();
            String res = "Ingredients by ";
            while (resultSet.next()) {
                res += resultSet.getString(1) + " "; 
            }
            return res;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
