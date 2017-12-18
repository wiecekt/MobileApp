package wiecekt.mobileapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactsDTO {

    private String id;
    private String name;
    private String phoneNumber;
    private boolean notifyCall;
    private boolean notifySMS;

    public ContactsDTO(String id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notifyCall = notifyCall;
        this.notifySMS = notifySMS;
    }
}
