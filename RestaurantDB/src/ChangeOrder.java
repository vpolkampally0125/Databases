import java.sql.*;

public class ChangeOrder {
    static String connectionUrl;
    public static void main(String[] args) {
        connectionUrl = 
        "jdbc:sqlserver://CSDSWINLAB017\\SQLEXPRESS"
        + "database=restaurant"      
        + "user=csdswinlab017\\student" 
        + "password=csds431134dscs; "   
        + "encrypt=true;"
        + "trustServerCertificate=true;"
        + "loginTimeout=15;";

        try{
            DriverManager.getConnection(connectionUrl);
        } catch(Exception e){
            System.out.println(e);
        }


        String[] order = {"hamburger", "fries"};
        runChangeOrderQuery("JEff", order);
    }

    // use case # 4
    public static void runChangeOrderQuery(String customerName, String[] itemsOrdered){

        try (Connection connection = DriverManager.getConnection(connectionUrl); 
                Statement statement = connection.createStatement();)  {
            
            // get the id of the customers most recent order to update
            String getOrderIdQuery = "SELECT max(orderId) FROM buys WHERE customer.name = " + customerName + ";";
            int orderID = Integer.parseInt(statement.executeQuery(getOrderIdQuery).getString(0));
            
            // delete previous ordered items
            String deletePreviousOrderItems = "DELETE FROM OrderedItems WHERE orderID = " + orderID + ";";
            statement.executeQuery(deletePreviousOrderItems);

            // insert new items ordered into ORDERED ITEMS table
            for(String item : itemsOrdered) {
                String selectMenuItemIDQuery = "SELECT ID from MenuItem where MenuItem.name = " + item + ";";
                int menuID = Integer.parseInt(statement.executeQuery(selectMenuItemIDQuery).getString(0));
                String insertOrderItemQuery = "INSERT INTO orderedItems (orderID, menuID) values(" + orderID + ", " + menuID + ");";
                statement.executeQuery(insertOrderItemQuery);
            }

            // get cost of new order
            int orderCost = 0;
            for(String item : itemsOrdered) {
                String itemCostQuery = "SELECT cost from MenuItem where MenuItem.name = " + item + ";";
                ResultSet itemCost = statement.executeQuery(itemCostQuery);
                orderCost += Integer.parseInt(itemCost.getString(0));
            }

            // update order cost
            String updateOrderQuery = "UPDATE order SET cost = " + orderCost + " where id = " + orderID + ";";
            statement.executeQuery(updateOrderQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
