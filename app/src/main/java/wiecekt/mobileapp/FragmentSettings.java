package wiecekt.mobileapp;

import android.app.Fragment;
import android.content.Context;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentSettings extends Fragment {


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Settings");
        getFragmentManager().beginTransaction().replace(R.id.content_navigation, new SettingsFragment(), "SETTINGS_FRAGMENT").commit();
        return inflater.inflate(R.layout.fragment_settings, container, false);

    }



    /*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_settings);
        // zamiast tego co na dole w sumie mozna dac
        //getFragmentManager().beginTransaction().add(android.R.id.content, new SettingsFragment()).commit();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SettingsFragment settingsFragment = new SettingsFragment();

        fragmentTransaction.add(android.R.id.content, settingsFragment, "SETTINGS_FRAGMENT");
        fragmentTransaction.commit();

    }*/

    public static class SettingsFragment extends PreferenceFragment {

        private ContactsSettings contactsSettings;
        private Context cx;

/*        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            cx = context;
        }*/

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            this.cx = container.getContext();
            return super.onCreateView(inflater, container, savedInstanceState);
        }



        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            contactsSettings = ContactsSettings.getInstance();
            addPreferencesFromResource(R.xml.preferences);

            Preference button =  getPreferenceManager().findPreference("button");
            if(button != null) {
                button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference preference) {
                        System.out.println("=============klik");
                        contactsSettings.readContacts(cx);
                        contactsSettings.showDialog(cx);
                        method();
                        return true;
                    }
                });
            }
        }

        private void method() {
            //tutaj chyba miala byc metoda od wyswietlania dialogu
        }

    }
}
