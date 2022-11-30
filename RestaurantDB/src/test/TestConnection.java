import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnection {
    public static void main(String[] args) {
        String connectionUrl = 
        "jdbc:sqlserver://CSDSWINLAB017\\SQLEXPRESS;"
        + "database=restuarantDB;"         //change this
        + "user=dbuser;"                 //change this
        + "password=scsd431134dscs;"   //change this
        + "encrypt=true;"
        + "trustServerCertificate=true;"
        + "loginTimeout=15;";

        String url = "jdbc:sqlserver://CSDSWINLAB017\\SQLEXPRESS;databaseName=university;encrypt=true;trustServerCertificate=true;";
        String user = "dbuser";
        String password = "scsd431134dscs";
        try{
            //Connection connection = DriverManager.getConnection(url,user,password); 
            Connection connection = DriverManager.getConnection(connectionUrl); 
            System.out.println("DONE");
        }catch(Exception e){
            System.out.println(e);
            //e.printStackTrace();
        }
    }
}