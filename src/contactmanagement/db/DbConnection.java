/*
 DatabaseConnection.java berfungsi untuk menghubungkan db
*/
package contactmanagement.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection instance = null;
    
    private final String driverClassname = "com.mysql.cj.jdbc.Driver";
    private Connection connection = null;
    
    private final String host = "127.0.0.1";
    private final String port = "3306";
    private final String db = "dbcontact";
    private final String username = "root";
    private final String password = "";
    
    private final String url = "jdbc:mysql://"+host+":"+port+"/"+db;
    
    public static DbConnection getInstance() {
        if (instance == null) {
            synchronized(DbConnection.class) {
                instance = new DbConnection();
            }
        }
        
        return instance;
    }
    
    public Connection get() {
        try {
            Class.forName(driverClassname);
            connection = DriverManager.getConnection(url, username, password);
        } catch(ClassNotFoundException | SQLException e) {
            System.err.println("Error! Database gagal terkoneksi");
        }
        
        return connection;
    }
    
    public void close() throws SQLException {
        if(connection != null) {
            connection.close();
        }
    }
}
