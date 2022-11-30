//use case 6 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteIngredient {
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

    public static void runDeleteIngredientQuery(String ingredient) {

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();) {

            // get the deleted ingredient id
            PreparedStatement getIngredientID = conn
                    .prepareStatement("SELECT id from Ingredients where Ingredient.name = ?");
            deleteIngredient.setString(1, ingredient);
            String ingredientID = statement.executeQuery(deleteIngredient);

            // delete ingredient from ingredient table
            PreparedStatement deleteIngredient = conn
                    .prepareStatement("DELETE from Ingredients where Ingredient.name = ?");
            deleteIngredient.setString(1, ingredient);
            statement.executeQuery(deleteIngredient);

            // delete the tuple in supplies that references the deleted ingredient
            PreparedStatement deleteSuppliesIngredient = conn
                    .prepareStatement("DELETE from Supplies where Ingredient.id = ?");
            deleteSuppliesIngredient.setString(1, ingredientID);
            statement.executeQuery(deleteSuppliesIngredient);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
