/*
 DatabaseHelper.java berisi fungsi-fungsi untuk mengeksekusi query database
*/
package contactmanagement.db;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DbHelper {
    
    private Connection connection = null;
    
    public DbHelper(Connection connection) {
        this.connection = connection;
    }
    
    public ResultSet getResultSet(String query) {
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return resultSet;
    }
    
    public ResultSet getPreparedQueryResult(String preparedQuery, String[] value) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(preparedQuery);
            for(int i = 0; i < value.length; i++) {
                preparedStatement.setString((i+1), value[i]);
            }
            resultSet = preparedStatement.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    
    public void executeUpdate(String preparedQuery, String[] value) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(preparedQuery);
            for(int i = 0; i < value.length; i++) {
                preparedStatement.setString((i+1), value[i]);
            }
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
}
