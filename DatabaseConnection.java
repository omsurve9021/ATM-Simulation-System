import java.sql.*;
public class DatabaseConnection {
    Connection conn;
    Statement smt;
    public DatabaseConnection(){
        try{
        //initializes the connection
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Set Driver
        conn = DriverManager.getConnection("jdbc:mysql:///Bankworkspace","root","Omsurve@9021");
        //Statement creation
        smt = conn.createStatement();
        }
        catch(Exception a){
            System.out.println(a);
        }
    }
}
