/*
 Session.java berfungsi untuk mengatur sesi pada login
*/
package contactmanagement.auth;

public class Session {
    private String id;
    private String username;
    private boolean status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getLoginStatus() {
        return status;
    }

    public void setLoginStatus(boolean status) {
        this.status = status;
    }
}
