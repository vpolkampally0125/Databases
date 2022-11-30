//use case #5 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteMenuItem {
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

    public static void runDeleteMenuItemQuery(String menuItem) {

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();) {

            // delete menu item
            PreparedStatement deleteMenuItem = conn.prepareStatement("DELETE from MenuItem where MenuItem.name = ?");
            pStmt.setString(1, menuItem);
            pStmt.executeUpdate();
            statement.executeQuery(deleteMenuItem);

            // get menu item id
            PreparedStatement getMenuItemID = conn.prepareStatement("SELECT id from MenuItem where MenuItem.name = ?");
            pStmt.setString(1, menuItem);
            pStmt.executeUpdate();
            String menuItemID = statement.executeQuery(getMenuItemID);

            // delete corresponding menu items from recipe table
            PreparedStatement deleteRecipe = conn.prepareStatement("DELETE from Recipe where recipe.id = ?");
            pStmt.setString(1, menuItemID);
            pStmt.executeUpdate();
            statement.executeQuery(deleteRecipe);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
