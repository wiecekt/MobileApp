package wiecekt.mobileapp;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContactsSettings {


    private static ContactsSettings instance = null;

    private String[] contactNames;
    private boolean[] checkedList;
    private List<Integer> chosenItemIds = new ArrayList<>();

    private List<ContactsDTO> contacts = new ArrayList<>();

    public List<ContactsDTO> getContacts() {
        return contacts;
    }


    private ContactsSettings() {

    }

    public static ContactsSettings getInstance() {
        if (instance == null)
            instance = new ContactsSettings();

        return instance;
    }


    public void setContacts(List<ContactsDTO> contacts) {
        this.contacts = contacts;
    }

    // lista wewnatrz metody i metoda ma zwracac ta liste a potem ewentualnie ustawiac jako contact settings
    // bo potrzebuje cos co odczytuje kontakty z telefonu i porowna z tym co jest w bazie/lokalnie
    public void readContacts(Context cx) {

        String id, name;
        String phoneNumber = "";

        ContentResolver contentResolver = cx.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        Cursor phoneCursor = null;

        while(cursor.moveToNext()) {
            id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

            System.out.println("ID: " + id);
            System.out.println("NAME: " + name);

            phoneCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
            if (phoneCursor.moveToNext()) {
                phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                System.out.println("Nr telefonu: " + phoneNumber);
            }
            phoneCursor.close();

            contacts.add(new ContactsDTO(id, name, phoneNumber));
            Collections.sort(contacts, new Comparator<ContactsDTO>() {
                @Override
                public int compare(ContactsDTO o1, ContactsDTO o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

        }
        //tutaj metoda ktora przyjmie
        cursor.close();

    }


    //ta metode wrzuc do fragmentu z settingsami
    public void showDialog(Context cx) {
        contactNames = new String[contacts.size()];
        checkedList = new boolean[contacts.size()];
        System.out.println("dlugosc listy po konstruktorze: " + checkedList.length);
        for(int i = 0; i < contacts.size(); i++) {
            contactNames[i] = contacts.get(i).getName();
            checkedList[i] = true;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(cx);
        builder.setTitle("ContactsSettings")
                .setMultiChoiceItems(contactNames, checkedList, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            checkedList[which] = true;
                        } else {
                            checkedList[which] = false;
                        }
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (boolean b : checkedList) {
                            System.out.println(b);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // cos po anulowniu, ale chyba tutaj nic
                    }
                });

        contacts.clear();
        AlertDialog dialog = builder.create();
        dialog.show();

    }

}
