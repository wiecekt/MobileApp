package wiecekt.mobileapp.controller;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import wiecekt.mobileapp.ContactsSettings;
import wiecekt.mobileapp.OnGetDataListener;
import wiecekt.mobileapp.User;
import wiecekt.mobileapp.dto.ContactsDTO;
import wiecekt.mobileapp.dto.EventDTO;


public class DatabaseController {

    private FirebaseDatabase database;
    private DatabaseReference refEvents;
    private DatabaseReference refContacts;
    private DatabaseReference refDevicesStatus;
    private User user;



    /*    public DatabaseController() {
        user = new User();
        String email = user.getUserEmail();
        database = FirebaseDatabase.getInstance();
        refEvents = database.getReferenceFromUrl("https://diploma-7a1ba.firebaseio.com/users/" + email + "/events").push();
        refContacts = database.getReferenceFromUrl("https://diploma-7a1ba.firebaseio.com/users/" + email + "/contacts");
    }*/

        public DatabaseController() {
        database = FirebaseDatabase.getInstance();
        refEvents = database.getReferenceFromUrl("https://diploma-7a1ba.firebaseio.com/users/user1/events").push();
        refContacts = database.getReferenceFromUrl("https://diploma-7a1ba.firebaseio.com/users/user1/contacts");
        refDevicesStatus = database.getReferenceFromUrl("https://diploma-7a1ba.firebaseio.com/users/user1/devices_status");
    }

    public void saveEvent(EventDTO eventDTO) {
        refEvents.setValue(eventDTO);
    }

    public void saveContactsDB(List<ContactsDTO> contacts) {
        for (ContactsDTO contact : contacts) {

            refContacts.push().setValue(contact);

        }
    }

    private void getContactsSettingsDB(final OnGetDataListener listener) {

        listener.onStart();

        refContacts.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onFailed(databaseError);
            }
        });

    }
    // to chyba jednak powinno byc w klasie ContactsSettings tak mi sie wydaje
    public void getAndSetContactsSettings() {

        getContactsSettingsDB(new OnGetDataListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(DataSnapshot data) {
                List<ContactsDTO> contacts = new ArrayList<>();
                ContactsSettings contactsSettings = ContactsSettings.getInstance();

                for(DataSnapshot child : data.getChildren()) {

                    ContactsDTO contactDTO = child.getValue(ContactsDTO.class);
                    contacts.add(contactDTO);
                    contactsSettings.setContacts(contacts);
                }
            }

            @Override
            public void onFailed(DatabaseError databaseError) {

            }
        });
    }

    public void checkIfContactNodeExists() {

    }


}
