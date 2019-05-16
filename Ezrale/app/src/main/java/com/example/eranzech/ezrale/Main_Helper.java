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

public class Main_Helper extends AppCompatActivity {

    // fields
    private boolean isTv;
    private boolean isPc;
    private boolean isPhone = false;
    private boolean isAC = false;
    private boolean isWashingMachine = false;
    private boolean isOther = false;

    public Main_Helper()
    {
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

    public void makeACall(View view) {
        // Elad
    }

    public void changeCatergory(View view) {
        boolean checked =  ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId())
        {
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
