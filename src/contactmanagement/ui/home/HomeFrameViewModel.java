package contactmanagement.ui.home;

import contactmanagement.data.DbRepository;
import contactmanagement.data.ContactRepository;
import contactmanagement.di.DbRepositoryInjection;
import contactmanagement.model.Contact;
import java.util.ArrayList;
import contactmanagement.viewmodel.ViewModel;

/**
 *
 * @author tech
 */
public final class HomeFrameViewModel {
    private ContactRepository repository = null;
    private ViewModel connectionObserver;
    
    public HomeFrameViewModel() {
        super();
        connectDB();
    }
    
    public void connectDB() {
        repository = DbRepositoryInjection.getContactRepository();
    }
    
    private void startObserveConnection() {
        repository.observeConnectionDB(new DbRepository.ConnectionObserver() {
            @Override
            public void connectionStatus(boolean status) {
                connectionObserver.connectionStatus(status);
            }
        });
    }
    
    public void observeConnection(ViewModel connectionObserver) {
        this.connectionObserver = connectionObserver;
        startObserveConnection();
    }
    
    public ArrayList<Contact> getContacts() {
        return repository.getContacts();
    }
    
    public ArrayList<Contact> searchContacts(String keyword) {
        return repository.searchContacts(keyword);
    }
    
    public void addContact(Contact contact) {
        repository.addContact(contact);
    }
    
    public void updateContact(Contact contact) {
        repository.updateContact(contact);
    }
    
    public void deleteContact(Contact contact) {
        repository.deleteContact(contact);
    }   
}
