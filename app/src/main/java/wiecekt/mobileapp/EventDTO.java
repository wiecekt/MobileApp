package wiecekt.mobileapp;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by PC on 2017-03-24.
 */
@IgnoreExtraProperties
public class EventDTO {

    public String sender;
    public String textMessage;

    public EventDTO() {
    }

    public EventDTO(String sender, String textMessage) {
        this.sender = sender;
        this.textMessage = textMessage;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }
}
