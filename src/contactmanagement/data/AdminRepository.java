/*
 AdminRepository.java merupakan repository berisi class & fungsi2 untuk menggunakan objek dari AdminLocalData.java
*/
package contactmanagement.data;

import contactmanagement.db.AdminLocalData;
import contactmanagement.model.Admin;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository extends DbRepository {
    private static AdminRepository instance = null;
    private AdminLocalData localData = null;
    
    public AdminRepository() {
        super();
    }
    
    public static AdminRepository getInstance(AdminLocalData localData) {
        if(instance == null) {
            synchronized(AdminLocalData.class) {
                instance = new AdminRepository();
            }
        }
        
        if(instance != null) {
            instance.localData = localData;
            instance.setLocalData(localData);
            instance.checkConnection();
        }
        
        return instance;
    }
    
    public Admin getAdmin(Admin admin) {
        checkConnection();
        Admin model = null;
        ResultSet result = localData.getAdmin(admin);

        try {
            if(result.next()) {
                model = new Admin();
                model.setId(result.getString("id"));
                model.setUsername(result.getString("username"));
                model.setPassword(result.getString("password"));
                model.setSalt(result.getString("salt"));
            }
        } catch(SQLException e) {}
        
        return model;
    }
    
    public void updateAdmin(Admin admin) {
        checkConnection();
        localData.updateAdmin(admin);
    }
}
