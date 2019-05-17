package com.example.eranzech.ezrale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class Ezrale extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton helperBtn = (ImageButton)findViewById(R.id.helperButton);

        setTitle("EzraLe");


        helperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ezrale.this, LoginActivity.class));
            }
        });

        ImageButton helpedBtn = (ImageButton)findViewById(R.id.helpedButton);

        helpedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ezrale.this, HelpMeActivity.class));
            }
        });

    }

}
