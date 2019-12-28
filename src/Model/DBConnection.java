package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.PreparedStatement;

public class DBConnection {

    public Connection connection;
    public PreparedStatement statement;

    public void DBconnection() {

    }

    public void connection() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            //System.out.println("Connection is done . ");

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void Connectiontomysql() {
        connection();
        String DBName = "socialnetwork";
        String password = "";
        String username = "root";
        String host = "jdbc:mysql://localhost:3306/" + DBName;

        try {
            connection = (Connection) DriverManager.getConnection(host, username, password);
            //System.out.println("Connection to database is done . ");
        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
        }

    }

}
