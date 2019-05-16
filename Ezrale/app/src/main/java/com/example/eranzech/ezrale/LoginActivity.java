package com.example.eranzech.ezrale;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

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

    public void updateDb(String phoneNumber, boolean isTv, boolean isPc, boolean isPhone, boolean
            isAC, boolean isWashingMachine, boolean isOther)
    {
        // Mika is the Queen.
    }
}
