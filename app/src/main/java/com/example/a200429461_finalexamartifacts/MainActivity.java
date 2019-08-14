package com.example.a200429461_finalexamartifacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText EmailTxt, PassTxt;
    Button LoginBtn, CreateBtn;
    TextView UserEmail;

    protected String email;

    ProgressDialog progressdialog;
    protected FirebaseAuth Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Db = FirebaseAuth.getInstance();

        EmailTxt = findViewById(R.id.emailEditText);
        PassTxt = findViewById(R.id.PasswordEditText);
        LoginBtn = findViewById(R.id.btnLogin);
        CreateBtn = findViewById(R.id.btnSignup);

        progressdialog = new ProgressDialog(this);
    }
    public void clickSignUp(View v) {
        Intent SignUpPage = new Intent (this, SignUpPage.class);
        startActivity(SignUpPage);
    }

    public void clickLogin(View v) {
        email = EmailTxt.getText().toString();
        String password = PassTxt.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter a email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_LONG).show();
            return;
        }
        progressdialog.setMessage("logging in.");
        progressdialog.show();
        Db.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();



                    Intent HomePage = new Intent (MainActivity.this, EmailPage.class);
                    HomePage.putExtra("sessionID", email);
                    startActivity(HomePage);

                    finish();

                }

                else
                {
                    Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
                progressdialog.dismiss();

            }


        });





    }
}
