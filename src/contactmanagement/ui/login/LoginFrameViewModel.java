package contactmanagement.ui.login;

import contactmanagement.auth.Session;
import contactmanagement.data.AdminRepository;
import contactmanagement.data.DbRepository;
import contactmanagement.di.DbRepositoryInjection;
import contactmanagement.model.Admin;
import contactmanagement.utilities.PasswordUtils;
import contactmanagement.viewmodel.ViewModel;

public final class LoginFrameViewModel {
    private AdminRepository repository = null;
    private ViewModel connectionObserver;
    
    public LoginFrameViewModel() {
        super();
        connectDB();
    }
    
    public void connectDB() {
        repository = DbRepositoryInjection.getAdminRepository();
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
    
    public Session performLogin(String username, String password) {
        boolean sucessLogin = false;
        Session session = new Session();
        Admin adminFromUser = new Admin();
        
        session.setLoginStatus(sucessLogin);
        adminFromUser.setUsername(username);
        adminFromUser.setPassword(password);
        
        Admin adminFromDB = repository.getAdmin(adminFromUser);
        if(adminFromDB != null) {
            sucessLogin = loginValidation(adminFromUser, adminFromDB);
        
            if(sucessLogin) {
                repository.updateAdmin(adminFromDB);        
                session.setId(adminFromUser.getId());
                session.setUsername(adminFromUser.getUsername());
                session.setLoginStatus(sucessLogin);
            }
        }
        
        return session;
    }
    
    private boolean loginValidation(Admin adminFromUser, Admin adminFromDB) {
        boolean isSuccess = false;        
        String typedUsername;
        String typedPassword;
        String dbUsername;
        String dbPassword;
        String dbSalt;
               
        typedUsername = adminFromUser.getUsername();
        typedPassword = adminFromUser.getPassword();
        dbUsername = adminFromDB.getUsername();
        dbPassword = adminFromDB.getPassword();
        dbSalt = adminFromDB.getSalt();
            
        boolean isUsernameCorrect = dbUsername.equals(typedUsername);
        boolean isPasswordCorrect = PasswordUtils.verifyUserPassword(
                    typedPassword, dbPassword, dbSalt);
            
        if(isUsernameCorrect && isPasswordCorrect) {
            isSuccess = true;
        }
        
        return isSuccess;
    }
}
