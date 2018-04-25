package handle;

import java.io.Serializable;

public class HandleData implements Serializable{

    private EventNamespace event = null;

    /**
     * clients username
     */
    private String username = null;

    private String databaseLogin = null;
    private String databasePassword = null;

    private boolean databaseRoot;


    public HandleData(){}

    public HandleData(EventNamespace event){
        this.event = event;
    }

    public HandleData(EventNamespace event, String username){
        this.event = event;
        this.username = username;
    }

    public EventNamespace getEvent() {
        return event;
    }

    public void setEvent(EventNamespace event) {
        this.event = event;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDatabaseLogin() {
        return databaseLogin;
    }

    public void setDatabaseLogin(String databaseLogin) {
        this.databaseLogin = databaseLogin;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    public boolean getDatabaseRoot() {
        return databaseRoot;
    }

    public void setDatabaseRoot(boolean databaseRoot) {
        this.databaseRoot = databaseRoot;
    }
}
