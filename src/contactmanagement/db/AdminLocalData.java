/*
 AdminLocalData.java berisi fungsi2 untuk menjalankan query database ke tabel admin
*/
package contactmanagement.db;

import contactmanagement.model.Admin;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminLocalData extends LocalData {
    private static AdminLocalData instance = null;
    private DbHelper dbHelper;
    private final String dbTable = "admins";
    
    public static AdminLocalData getInstance(DbHelper databaseHelper) {
        if(instance == null) {
            synchronized(AdminLocalData.class) {
                instance = new AdminLocalData();
            }
        }
        
        if(instance != null) {
            instance.dbHelper = databaseHelper;
        }
        
        return instance;
    }

    @Override
    public boolean checkConnection() {
        boolean isConnected = false;
        
        try {
            String query = "SELECT * FROM "+ dbTable +" LIMIT 1";
            ResultSet result = dbHelper.getResultSet(query);
            
            if(result != null) {
                isConnected = true;
            }
        } catch (Exception e) { }
        
        return isConnected;
    }
    
    public ResultSet getAdmin(Admin admin) {
        String preparedQuery = "";		
        List<String> queryValue = new ArrayList();
        
        if(admin.getId() != null) {
            if(!admin.getId().isEmpty()) {
                preparedQuery = "SELECT * FROM " + dbTable + " WHERE id=?";   
                queryValue.add(admin.getId());
            }
        }            
        else {
            preparedQuery = "SELECT * FROM " + dbTable + " WHERE username=?";
            queryValue.add(admin.getUsername());
        }
            
        return dbHelper.getPreparedQueryResult(preparedQuery, queryValue.toArray(String[]::new));
    }
    
    public void updateAdmin(Admin admin) {
        String preparedQuery = "UPDATE "
                + dbTable 
                +" SET username=?, password=?, salt=?, last_login=NULL WHERE id=?";
        String queryValue[] = {
            admin.getUsername(), admin.getPassword(), admin.getSalt(), admin.getId()
        };
        
        dbHelper.executeUpdate(preparedQuery, queryValue);
    }
}
