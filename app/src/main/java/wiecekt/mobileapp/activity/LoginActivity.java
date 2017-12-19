package wiecekt.mobileapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import wiecekt.mobileapp.R;
import wiecekt.mobileapp.User;
import wiecekt.mobileapp.broadcast_receiver.BRUtil;
import wiecekt.mobileapp.broadcast_receiver.CallReceiver;
import wiecekt.mobileapp.broadcast_receiver.SMSReceiver;

public class LoginActivity extends AppCompatActivity {

    private Button buttonLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;

    private User user;

    private CallReceiver callReceiver = new CallReceiver();
    private SMSReceiver smsReceiver = new SMSReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d("LoginActivity", "tekst");
        user = new User();

        if(user.getUser() != null) {
            startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
            finish();
        }

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword =  findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewSignup = findViewById(R.id.textViewSignUp);
        progressDialog = new ProgressDialog(this);

        buttonLogin.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            user.signIn(LoginActivity.this, email, password);
        });

        textViewSignup.setOnClickListener(v -> {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                finish();
        });
    }
}
