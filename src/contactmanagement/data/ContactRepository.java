/*
 ContactRepository.java merupakan repository berisi class & fungsi2 untuk menggunakan objek dari ContactLocalData.java
*/
package contactmanagement.data;

import contactmanagement.db.ContactLocalData;
import contactmanagement.model.Contact;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author tech
 */
public final class ContactRepository extends DbRepository {
    private static ContactRepository instance = null;
    private ContactLocalData localData = null;
    
    public ContactRepository() {
        super();
    }
    
    public static ContactRepository getInstance(ContactLocalData localData) {
        if(instance == null) {
            synchronized(ContactLocalData.class) {
                instance = new ContactRepository();
            }
        }
        
        if(instance != null) {
            instance.localData = localData;
            instance.setLocalData(localData);
            instance.checkConnection();
        }
        
        return instance;
    }
    
    private boolean checkConnectionDB() {
        boolean status = super.checkConnection();
        return status;
    }
    
    public ArrayList<Contact> getContacts() {
        ArrayList<Contact> contacts = new ArrayList();
        ResultSet results = localData.getContacts();
        
        try {
            if(results != null) {
                while(results.next()) {
                    Contact contact = new Contact();
                    contact.setId(results.getString("id"));
                    contact.setName(results.getString("name"));
                    contact.setpn(results.getString("pn"));
                    contacts.add(contact);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error! Gagal mengambil kontak");
        }
        
        return contacts;
    }
    
    public ArrayList<Contact> searchContacts(String keyword) {
        checkConnectionDB();
        ArrayList<Contact> contacts = new ArrayList();
        ResultSet results = localData.searchContacts(keyword);
        
        try {
            if(results != null) {
                while(results.next()) {
                    Contact contact = new Contact();
                    contact.setId(results.getString("id"));
                    contact.setName(results.getString("name"));
                    contact.setpn(results.getString("pn"));
                    contacts.add(contact);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error! Gagal mencari kontak");
        }
        
        return contacts;
    }
    
    public void addContact(Contact contact) {
        checkConnectionDB();
        localData.addContact(contact);
    }
    
    public void updateContact(Contact contact) {
        checkConnectionDB();
        localData.updateContact(contact);
    }
    
    public void deleteContact(Contact contact) {
        checkConnectionDB();
        localData.deleteContact(contact);
    }   
}
