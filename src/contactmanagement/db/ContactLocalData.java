/*
 ContactLocalData.java berisi fungsi2 untuk menjalankan query database ke tabel contact untuk mendukung operasi CRUD
*/
package contactmanagement.db;

import contactmanagement.model.Contact;
import contactmanagement.utilities.StringUtils;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactLocalData extends LocalData {
    private static ContactLocalData instance = null;
    private DbHelper dbHelper;
    private final String dbTable = "contacts";
    
    public static ContactLocalData getInstance(DbHelper databaseHelper) {
        if(instance == null) {
            synchronized(ContactLocalData.class) {
                instance = new ContactLocalData();                
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
    
    public String generateId(int length) {
        String id = "";
        ResultSet contactResult;
        
        try {
            do {
                id = StringUtils.randomString(20);
                Contact contact = new Contact();
                contact.id = id;
                contactResult = getContact(contact);
            } while(contactResult.next());
        } catch(SQLException e) {
            System.err.println("Error! Gagal membuat id kontak");
        }
                
        return id;
    }
    
    public ResultSet getContacts() {
        String query = "SELECT * FROM " + dbTable +" ORDER BY updated_at DESC, created_at DESC";
        return dbHelper.getResultSet(query);
    }
    
    public ResultSet getContact(Contact contact) {
        String preparedQuery = "SELECT * FROM " + dbTable + " WHERE id=?";
        String queryValue[] = { contact.id };
        return dbHelper.getPreparedQueryResult(preparedQuery, queryValue);
    }
    
    public ResultSet searchContacts(String keyword) {
        String preparedQuery = "SELECT * FROM " + dbTable + " WHERE (name LIKE ? OR pn LIKE ?)";
        String queryValue[] = {
            "%"+keyword+"%", "%"+keyword+"%"
        };
        
        return dbHelper.getPreparedQueryResult(preparedQuery, queryValue);
    }
    
    public void addContact(Contact contact) {
        String preparedQuery = "INSERT INTO "+ dbTable +"(name, pn, created_at, updated_at) VALUES(?, ?, NULL, NULL)";
        String[] contactInfo = {
            contact.name, contact.pn
        };

        dbHelper.executeUpdate(preparedQuery, contactInfo);
    }
    
    public void updateContact(Contact contact) {
        String preparedQuery = "UPDATE "+ dbTable +" SET name=?, pn=?, updated_at=NULL WHERE id=?";
        String queryValue[] = {
            contact.name, contact.pn, contact.id
        };
        
        dbHelper.executeUpdate(preparedQuery, queryValue);
    }
    
    public void deleteContact(Contact contact) {
        String preparedQuery = "DELETE FROM "+ dbTable +" WHERE id=?";
        String queryValue[] = { contact.id };
        
        dbHelper.executeUpdate(preparedQuery, queryValue);
    }
}
