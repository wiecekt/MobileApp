package wiecekt.mobileapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import wiecekt.mobileapp.R;
import wiecekt.mobileapp.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonRegister;
    private TextView textViewSignIn;

    private FirebaseAuth firebaseAuth;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user = new User();

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        textViewSignIn = findViewById(R.id.textViewSignIn);
        //progressBar = new ProgressDialog(this);

        if(user.getUser() != null) {
            startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
            finish();
        }


        buttonRegister.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            user.registerUser(RegisterActivity.this, email, password);
        });

        textViewSignIn.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });

    }
}
