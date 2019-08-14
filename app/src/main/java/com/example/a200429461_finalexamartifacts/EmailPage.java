package com.example.a200429461_finalexamartifacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class EmailPage extends AppCompatActivity {

    EditText SendToTxt, SubjectTxt, MessageTxt;
    Button SendBtn;

    String SendTo, Subject, Message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_page);


        TextView UserEmail = findViewById(R.id.textViewMyEmail);
        SendToTxt = findViewById(R.id.editTextSendTo);
        SubjectTxt = findViewById(R.id.editTextSubject);
        MessageTxt = findViewById(R.id.editTextMessage);

        String sessionId = getIntent().getStringExtra("sessionID");

        UserEmail.setText(sessionId);
    }

    public void clickSendEmail(View view) {
        SendTo = SendToTxt.getText().toString();
        Subject = SubjectTxt.getText().toString();
        Message = MessageTxt.getText().toString();


        if(TextUtils.isEmpty(SendTo)){
            Toast.makeText(EmailPage.this, "Please Enter an Email.", Toast.LENGTH_LONG).show();
            return;

        }

        else if(TextUtils.isEmpty(Subject)){
            Toast.makeText(EmailPage.this, "Please Enter Subject", Toast.LENGTH_LONG).show();
            return;

        }

        else if(TextUtils.isEmpty(Message)){
            Toast.makeText(EmailPage.this, "Please Enter Message.", Toast.LENGTH_LONG).show();
            return;

        }



            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", SendTo, null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, Subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, Message);
            startActivity(Intent.createChooser(emailIntent, "Send email..."));





    }
}
