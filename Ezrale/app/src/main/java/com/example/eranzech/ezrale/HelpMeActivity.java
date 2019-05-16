package com.example.eranzech.ezrale;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;

import java.lang.String;

public class HelpMeActivity extends AppCompatActivity {

    // fields
    private boolean isTv = false;
    private boolean isPc = false;
    private boolean isPhone = false;
    private boolean isAC = false;
    private boolean isWashingMachine = false;
    private boolean isOther = false;

    public HelpMeActivity() {
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
        setContentView(R.layout.activity_main__helper);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main__helper, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void helpMe(View view) {
        String phoneNumber = getHelpersNumber(this.isTv, this.isPc, this.isPhone, this.isAC,
                this.isWashingMachine, this.isOther);
        makeACall(phoneNumber);
    }

    /**
     *
     * @param phoneNumber The phone number to whatsapp call to.
     */
    public void makeACall(String phoneNumber)
    {
        //elad
    }



    private String getHelpersNumber(boolean isTv, boolean isPc, boolean isPhone, boolean
            isAC, boolean isWashingMachine, boolean isOther) {
        //mika
        System.out.println("Mika should do it");
        return "hey";
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
}
