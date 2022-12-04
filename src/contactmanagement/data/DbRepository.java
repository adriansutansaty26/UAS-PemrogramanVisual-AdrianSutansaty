/*
 AdminRepository.java merupakan repository berisi class & fungsi2 untuk menggunakan objek dari LocalData.java
*/
package contactmanagement.data;

import contactmanagement.db.LocalData;

public class DbRepository {
    private LocalData localData;
    private ConnectionObserver connectionObserver = null;
   
    protected void setLocalData(LocalData localData) {
        this.localData = localData;
    }
   
    protected boolean checkConnection() {
        boolean status = localData.checkConnection();
        setConnectionStatus(status);
        return status;
    }
   
    private void setConnectionStatus(boolean status) {
        boolean isObserverAvailable = connectionObserver != null;
        
        if(isObserverAvailable) {
            connectionObserver.connectionStatus(status);
        }
    }
    
    public void observeConnectionDB(ConnectionObserver connectionObserver) {
        this.connectionObserver = connectionObserver;
        checkConnection();
    }
   
    public interface ConnectionObserver {
        void connectionStatus(boolean status);
    }
}
