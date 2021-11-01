import java.sql.*;

public class Mysqlconnect {

    Connection c;
    Statement s;

    public Mysqlconnect() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectjava", "root", "");
            s = c.createStatement();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
    public static void main(String[] args) {
        
    }

}
