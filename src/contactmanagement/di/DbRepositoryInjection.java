package contactmanagement.di;
import contactmanagement.data.AdminRepository;
import contactmanagement.data.ContactRepository;
import contactmanagement.db.AdminLocalData;
import java.sql.Connection;
import contactmanagement.db.DbConnection;
import contactmanagement.db.DbHelper;
import contactmanagement.db.ContactLocalData;

public class DbRepositoryInjection {
    public static ContactRepository getContactRepository() {
        Connection connection = DbConnection.getInstance().get();
        DbHelper dbHelper = new DbHelper(connection);
        ContactLocalData localData = ContactLocalData.getInstance(dbHelper);
        return ContactRepository.getInstance(localData);
    }
    
    public static AdminRepository getAdminRepository() {
        Connection connection = DbConnection.getInstance().get();
        DbHelper dbHelper = new DbHelper(connection);
        AdminLocalData localData = AdminLocalData.getInstance(dbHelper);
        return AdminRepository.getInstance(localData);
    }
}
