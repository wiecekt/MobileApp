package wiecekt.mobileapp.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import wiecekt.mobileapp.R;
import wiecekt.mobileapp.User;
import wiecekt.mobileapp.activity.LoginActivity;
import wiecekt.mobileapp.fragment.FragmentHome;
import wiecekt.mobileapp.fragment.FragmentInfo;
import wiecekt.mobileapp.fragment.FragmentSettings;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private User user;
    private TextView textViewMenuEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        user = new User();

        if(!user.isUserSignedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            user.getUserEmail();
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        textViewMenuEmail = headerView.findViewById(R.id.menuEmail);
        textViewMenuEmail.setText(user.getUserEmail());

        displaySelectedScreen(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void displaySelectedScreen(int id) {
        Fragment fragment = null;

        switch (id) {
            case R.id.nav_home:
                fragment = new FragmentHome();
                break;
            case R.id.nav_settings:
                fragment = new FragmentSettings();
                break;
            case R.id.nav_info:
                fragment = new FragmentInfo();
                break;
            case R.id.nav_logout:
                user.signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                break;
        }

        if (fragment != null) {
           // android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
           // getFragmentManager().beginTransaction().replace(R.id.content_navigation, fragment).commit();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content_navigation, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        displaySelectedScreen(id);
        return true;
    }
}
