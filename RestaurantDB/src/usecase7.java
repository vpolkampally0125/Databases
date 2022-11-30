//use case 7

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectIngredientsOfMenuItem {
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

    public static void runSelectIngredientsOfMenuItemQuery(String menuItem) {

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();) {

            ResultSet recipe = null;
            ResultSet ingredients = null;

            // select recipe of the menu item input
            String selectRecipeQuery = "SELECT * from MenuItem JOIN Recipe where MenuItem.id = Recipe.MenuItem.id";
            recipe = statement.executeQuery(selectRecipeQuery);

            // select the ingredients of the given recipe
            PreparedStatement selectIngredientsOfRecipeQuery = conn
                    .prepareStatement("SELECT ingredients from ? JOIN Ingredients where ?.Ingredient.ID = Recipe.ID");
            selectIngredientsOfRecipeQuery.setString(1, recipe);
            selectIngredientsOfRecipeQuery.executeUpdate();
            ingredients = statement.executeQuery(selectIngredientsOfRecipeQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
