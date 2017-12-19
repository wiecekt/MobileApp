package wiecekt.mobileapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import wiecekt.mobileapp.ContactsSettings;
import wiecekt.mobileapp.controller.DatabaseController;
import wiecekt.mobileapp.R;

public class FragmentHome extends Fragment {

    /*private Button buttonTest;
    private Button buttonTestUpdate;*/
    private DatabaseController databaseController;
    private ContactsSettings contactsSettings;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");

        contactsSettings = ContactsSettings.getInstance();
        databaseController = new DatabaseController();

        /*buttonTest = (Button) view.findViewById(R.id.buttonTest);
        buttonTest.setOnClickListener(v -> {
            //System.out.println("WIADOMOSC: " + databaseController.getAndSetContactsSettings());
            //List<ContactsDTO> list = databaseController.getContacts();
            //contactsSettings.setContacts(list);
        });

        buttonTestUpdate = (Button) view.findViewById(R.id.button_testUpdate);
        buttonTestUpdate.setOnClickListener(v -> {
            //databaseController.getDesktopStatus(); // sprawdz jutro i modl sie zeby zdazyc bo kiepsko to widze na prawdee kiepsko :(
        });*/
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}
