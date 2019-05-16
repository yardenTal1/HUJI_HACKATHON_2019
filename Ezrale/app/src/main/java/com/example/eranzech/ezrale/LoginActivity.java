package com.example.eranzech.ezrale;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    // fields
    private boolean isTv = false;
    private boolean isPc = false;
    private boolean isPhone = false;
    private boolean isAC = false;
    private boolean isWashingMachine = false;
    private boolean isOther = false;

    public LoginActivity() {
        this.isTv = false;
        this.isPc = false;
        this.isPhone = false;
        this.isAC = false;
        this.isWashingMachine = false;
        this.isOther = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button helperBtn = (Button)findViewById(R.id.register_button);

        helperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ThanksActivity.class));
            }
        });
    }

    public void changeCatergory(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.tv_cb:
                this.isTv = checked;
                break;
            case R.id.phone_cb:
                this.isPhone = checked;
                break;
            case R.id.ac_cb:
                this.isAC = checked;
            case R.id.pc_cb:
                this.isPc = checked;
                break;
            case R.id.wm_cb:
                this.isWashingMachine = checked;
                break;
            case R.id.other_cb:
                this.isOther = checked;
        }
    }

    public void registerHelper(View view) {
        EditText phoneEdtxt = (EditText)findViewById(R.id.phoneNum);
        String phoneNumber = phoneEdtxt.getText().toString();

        updateDb(phoneNumber, this.isTv, this.isPc, this.isPhone, this.isAC,
                this.isWashingMachine, this.isOther);

    }

    private void updateDb(String phoneNumber, boolean isTv, boolean isPc, boolean isPhone, boolean
            isAC, boolean isWashingMachine, boolean isOther) {
        DatabaseReference database;
        DatabaseReference childRef;
        database = FirebaseDatabase.getInstance().getReference();
        childRef = database.child("helpers");

        // Write new user to database
        App_User user = new App_User(isTv, isPc, isPhone, isAC, isWashingMachine, isOther);
        childRef.child(phoneNumber).setValue(user);
    }
}
