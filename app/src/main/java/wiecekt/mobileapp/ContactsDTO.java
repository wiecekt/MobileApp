package wiecekt.mobileapp;

public class ContactsDTO {

    private String id;
    private String name;
    private String phoneNumber;
    private boolean notifyCall;
    private boolean notifySMS;

    public ContactsDTO() {
    }

    public ContactsDTO(String id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notifyCall = notifyCall;
        this.notifySMS = notifySMS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isNotifyCall() {
        return notifyCall;
    }

    public void setNotifyCall(boolean notifyCall) {
        this.notifyCall = notifyCall;
    }

    public boolean isNotifySMS() {
        return notifySMS;
    }

    public void setNotifySMS(boolean notifySMS) {
        this.notifySMS = notifySMS;
    }
}
