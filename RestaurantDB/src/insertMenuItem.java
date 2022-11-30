import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner; 


public class SQLStoredProcInsertMenuItem {

    public static void main(String[] args) {
        String connectionUrl = 
        "jdbc:sqlserver://localhost;"
                + "database=restuarantDB;"
                + "user=dbuser;" 
                + "password=scsd431134dscs;"
                + "encrypt=true;"
                + "trustServerCertificate=true;"
                + "loginTimeout=115;";
        Scanner myObj = new Scanner(System.in);
        String name;
        float price;

        // Enter username and press Enter
       System.out.println("Enter menu item name then enter. "); 
       inpDeptName = myObj.nextLine();   
       System.out.println("Enter price as numeric (12,2) then enter. "); 
       inpBudget = myObj.nextFloat();
       
       myObj.close();

       System.out.println("menu item name: " + name);
       System.out.println("price: " + price);
        
        String callStoredProc = "{call dbo.insertMenuItem(?,?)}"; 
   
        try (Connection connection = DriverManager.getConnection(connectionUrl);
                PreparedStatement prepsInsertMenuItem = connection.prepareStatement(callStoredProc);) {
            prepsInsertMenuItem.setString(1, name);
            prepsInsertMenuItem.setFloat(2, price);
            prepsInsertMenuItem.execute();
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
