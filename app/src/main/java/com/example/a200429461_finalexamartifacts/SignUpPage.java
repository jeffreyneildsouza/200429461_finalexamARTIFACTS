package com.example.a200429461_finalexamartifacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpPage extends AppCompatActivity {
    Button registerButton;
    EditText emailEditText, passwordEditText, confirmEditText;



    FirebaseAuth authDb;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sign_up_page);


            authDb = FirebaseAuth.getInstance();

            registerButton = findViewById(R.id.btnSignup);
            emailEditText = findViewById(R.id.emailEditText);
            passwordEditText = findViewById(R.id.passwordEditText);
            confirmEditText = findViewById(R.id.confirmEditText);

            progressDialog = new ProgressDialog(this);

            ActionBar actionBar = getSupportActionBar();
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);

        }


        //Register User

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*
        public void clickSignUp(View v) {

        }
    */
    public void clickBack(View v) {
        Intent LoginPage = new Intent(this, MainActivity.class);
        startActivity(LoginPage);
    }

    public void clickSignUp(View view) {
        {

            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmEditText.getText().toString();

            if(TextUtils.isEmpty(email)){
                Toast.makeText(this, "Please Enter an Email.", Toast.LENGTH_LONG).show();
                return;
            }

            if(TextUtils.isEmpty(password)){
                Toast.makeText(this, "Please Enter a password.", Toast.LENGTH_LONG).show();
                return;

            }

            if(TextUtils.isEmpty(confirmPassword)){
                Toast.makeText(this, "Please Enter confirm password.", Toast.LENGTH_LONG).show();
                return;

            }

            if(!password.equals(confirmPassword)){
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_LONG).show();
                return;

            }

            progressDialog.setMessage("Register account");
            progressDialog.show();


            authDb.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignUpPage.this, "Register Success!", Toast.LENGTH_SHORT).show();
                        Intent LoginPage = new Intent(SignUpPage.this, MainActivity.class);
                        startActivity(LoginPage);
                    }else
                    {
                        Toast.makeText(SignUpPage.this, "Register Fail. Please Try again!", Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                }
            });

        }
    }
}
