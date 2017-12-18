package wiecekt.mobileapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import wiecekt.mobileapp.activity.NavigationActivity;
import wiecekt.mobileapp.controller.DatabaseController;

public class User {

    private ContactsSettings contactsSettings;
    private DatabaseController databaseController;
    private FirebaseAuth firebaseAuth;

    public User() {
        firebaseAuth = FirebaseAuth.getInstance();
        contactsSettings = ContactsSettings.getInstance();
        databaseController = new DatabaseController();

    }

    public void registerUser(Context cx, String email, String password) {
        final Context context = cx;
        final ProgressDialog progressDialog = new ProgressDialog(context);

        if (TextUtils.isEmpty(email)) {

            Toast.makeText(cx, "Please enter email!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {

            Toast.makeText(cx, "Please enter password!", Toast.LENGTH_SHORT).show();
            return;

        }

        progressDialog.setMessage("Registering user please wait...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {

            if(task.isSuccessful()) {
                contactsSettings.readContacts(context);
                databaseController.saveContactsDB(contactsSettings.getContacts());
                context.startActivity(new Intent(context, NavigationActivity.class));
                ((Activity) context).finish();
            } else {
                /*Exception ex = task.getException();
                ex.printStackTrace();*/
                System.out.println("============= error =========== " + task.getException().getMessage());
                Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
            progressDialog.dismiss();
        });

    }


    public void signIn(Context cx, String email, String password) {
        final Context context = cx;

        final ProgressDialog progressDialog = new ProgressDialog(context);

        if (TextUtils.isEmpty(email)) {

            Toast.makeText(context, "Please enter email!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {

            Toast.makeText(context, "Please enter password!", Toast.LENGTH_SHORT).show();
            return;

        }

        progressDialog.setMessage("Signing in please wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {

                    if(task.isSuccessful()) {
                        // tutaj odczytaj kontakty z bazy
                        context.startActivity(new Intent(context, NavigationActivity.class));
                        ((Activity) context).finish();
                    } else {
                        Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                });
    }

    public void signOut() {
        firebaseAuth.signOut();
    }

    public boolean isUserSignedIn() {
        if(firebaseAuth.getCurrentUser() == null)
            return false;
        else
            return true;
    }

    public FirebaseUser getUser() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        return user;
    }

    public String getUserEmail() {
        return getUser().getEmail();
    }

}
