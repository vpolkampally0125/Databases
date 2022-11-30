//use case #2

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MakeNewMenuItem {
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

    // each ingredient is paired with a equipment, the ingredient-equipment pair is
    // index based for both arrays
    public static void runMakeNewMenuItemQuery(String name, String[] ingredients, String[] equipment, int price) {

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();) {

            // insert menu item into menu item table
            String insertMenuItemQuery = "insert into MenuItem (name, price) values (" + name + ", " + price + ");";
            statement.executeQuery(insertMenuItemQuery);

            // get the new menu item id
            String getMenuItemIDQuery = "SELECT max(id) from MenuItem";
            int menuItemID = Integer.parseUnsignedInt(statement.executeQuery(getMenuItemIDQuery));

            // insert the menu item info into the reciple table for ingredients
            for (int i = 0; i < ingredients.length; i++) {
                // get ingredient id
                String getIngredientIDQuery = "SELECT id from ingredients where ingredient.name = " + ingredients[i];
                String ingredientID = statement.executeQuery(getMenuItemIDQuery);

                PreparedStatement pStmt = conn.prepareStatement(
                        "insert into recipe (Ingredient.id, MenuItem.id, Equipment.id) values (?,?,?)");
                pStmt.setString(1, ingredientID);
                pStmt.setString(2, menuItemID);
                pStmt.setString(3, equipment[i]);
                pStmt.executeUpdate();
                statement.executeQuery(pStmt);
                // pStmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
