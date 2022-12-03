import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.ResultSet;

public class UseCases {

    static final String connectionUrl = "jdbc:sqlserver://CSDSWINLAB017\\SQLEXPRESS;"
            + "database=restaurantDB;" // DB Name
            + "user=dbuser;" // user name
            + "password=scsd431134dscs;" // password
            + "encrypt=true;"
            + "trustServerCertificate=true;"
            + "loginTimeout=15;";

    /*
     * For use case 1
     * Calls MakeOrder SQL stored procedure to let a existing customer order a menu
     * item
     * Input: String customernName that represents the customer that will be
     * ordering
     * Input: String menuItem that represents the menu item that is being ordered
     */
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

    /*
     * For use case 2
     * Calls MakeNewMenuItem SQL stored procedure to let a make a new menu item
     * Input: String menuItem that represents the menu item name that is being
     * created
     * Input: float price that represents the price of the menu item that is being
     * created
     * Input: String ingredient that represents an ingredient used for the menu item
     * Input: String equipment that represnts the equipment used for the menu item
     */
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

    /*
     * For use case 3
     * Calls ChangeMenuItem SQL stored procedure to make a change to an existing
     * menu item
     * Input: String menuItem that represents the menu item name to be changed
     * Input: String ingredientReplaced that represents the ingredient to be
     * replaced
     * Input: String ingredientReplacing that represnts the replacing ingredient
     * Input: String equipmentReplaced that represents the equipment to be replaced
     * Input: String equipmentReplacing that represnts the replacing eqiupment
     */
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

    /*
     * For use case 4
     * Calls the UpdateOrder SQL stored procedure to update a existing customer
     * order
     * Input: int orderID that represents the order to be changed
     * Input: String menuItem that represents the new menuItem to be ordered
     */
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

    /*
     * For use case 5
     * Calls DeleteMenuItem SQL stored procedure to delete an existing menu item
     * Input: String menuItem that represents the menu item to be deleted
     */
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

    /*
     * For use case 6
     * Calls DeleteIngredient SQL stored procedure to delete an ingredient
     * Input: String ingredient that represents the ingredient to be deleted
     */
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

    /*
     * For use caes 7
     * Calls FindMenuItemIngredients SQL stored procedures to give all the
     * ingredients a menu item uses
     * Input: String menuItem to represent the menu item in question
     */
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

    /*
     * For use case 8
     * Calls FindOrderBasedOnCost SQL stored procedure to give the orders that are
     * the same as the input cost
     * Input: float cost that represents the cost in question
     */
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

    /*
     * For use case 9
     * Calls FindOrderHistory SQL stored procedure to give all the orders that a
     * customer has ordered
     * Input: String name that represents the customer in question
     */
    public static String findOrderHistory(String name) {

        String callStoredProc = "{call dbo.FindOrderHistory(?)}";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                CallableStatement prepsFindOrderHistory = connection.prepareCall(callStoredProc);) {
            prepsFindOrderHistory.setString(1, name);

            ResultSet resultSet = prepsFindOrderHistory.executeQuery();
            String res = "Orders:";
            while (resultSet.next()) {
                res += "#" + resultSet.getString(1) + ":" + resultSet.getString(2) + " ";
            }
            return res;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    /*
     * For use case 10
     * Calls FindSupplierIngredients SQL stored procedure to give all ingredients
     * that a supplier supplies
     * Input: String name that represents the supplier in question
     */
    public static String findSupplierIngredients(String name) {

        String callStoredProc = "{call dbo.FindSupplierIngredients(?)}";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                CallableStatement prepsFindSupplierIngredients = connection.prepareCall(callStoredProc);) {
            prepsFindSupplierIngredients.setString(1, name);

            ResultSet resultSet = prepsFindSupplierIngredients.executeQuery();
            String res = "Ingredients:";
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
