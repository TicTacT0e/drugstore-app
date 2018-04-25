package handle;

import java.io.Serializable;

public class HandleData implements Serializable{

    private EventNamespace event = null;

    private String username = null;

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
}
