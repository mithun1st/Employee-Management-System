package com.example.employeemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FeedBack extends AppCompatActivity {

    private Button feedbackButton;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        feedbackButton=findViewById(R.id.feedbackButton);
        editText=findViewById(R.id.feedback);

        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=editText.getText().toString();
                String[] mail=new String[]{"mithun.2121@yahoo.com"};

                feedback(text,mail);
            }
        });
    }

    //function
    public void feedback(String text, String[] mail){
        Intent i = new Intent(Intent.ACTION_SEND);

        i.setType("text/email");

        i.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
        i.putExtra(Intent.EXTRA_TEXT, text);
        i.putExtra(Intent.EXTRA_EMAIL,mail);

        startActivity(Intent.createChooser(i,"Feedback Now"));
    }
}