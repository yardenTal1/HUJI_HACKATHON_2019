package com.example.eranzech.ezrale;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.ComponentName;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.Toast;

import java.lang.String;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
        setContentView(R.layout.activity_help_me);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main__helper, menu);
        return true;
    }

    interface GenericCallback {
        void callme(String param);
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
        makeACall(this.isTv, this.isPc, this.isPhone, this.isAC,
                this.isWashingMachine, this.isOther, new GenericCallback() {
                    @Override
                    public void callme(String phoneNumber) {
                        final String DisplayName = "__Helper__";
                        MakeVideoCall(DisplayName, phoneNumber);
                    }
                });
    }

    public void send_sms(View view) {
        String phoneNUmber = "+972508535764";
        Intent sendIntent = new Intent("android.intent.action.MAIN");
        sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
        String waNumber = phoneNUmber.replace("+", "");
        sendIntent.putExtra("jid", waNumber + "@s.whatsapp.net");
        startActivity(sendIntent);
        Uri uri = Uri.parse("callto:" + phoneNUmber);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.setPackage("com.whatsapp");
        startActivity(i);
    }


    public void videoCall(String name) {
        String mimeString = "vnd.android.cursor.item/vnd.com.whatsapp.video.call";

        String displayName = null;
        Long _id;
        ContentResolver resolver = getApplicationContext().getContentResolver();

        Cursor cursor = resolver.query(ContactsContract.Data.CONTENT_URI, null, null, null, ContactsContract.Contacts.DISPLAY_NAME);
        assert cursor != null;
        while (cursor.moveToNext()) {
            _id = cursor.getLong(cursor.getColumnIndex(ContactsContract.Data._ID));
            displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
            String mimeType = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.MIMETYPE));
            if (displayName.equals(name)) {
                if (mimeType.equals(mimeString)) {
                    String data = "content://com.android.contacts/data/" + _id;
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_VIEW);
                    sendIntent.setDataAndType(Uri.parse(data), mimeString);
                    sendIntent.setPackage("com.whatsapp");
                    startActivity(sendIntent);
                }
            }
        }
    }

    public void addContact(String DisplayName, String MobileNumber) {

        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

        ops.add(ContentProviderOperation.newInsert(
                ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());

        //------------------------------------------------------ Names
        if (DisplayName != null) {
            ops.add(ContentProviderOperation.newInsert(
                    ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(
                            ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                            DisplayName).build());
        }

        //------------------------------------------------------ Mobile Number
        if (MobileNumber != null) {
            ops.add(ContentProviderOperation.
                    newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, MobileNumber)
                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                            ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                    .build());
        }

        // Asking the Contact provider to create a new contact
        try {
            getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteContact(String number) {
        ContentResolver contactHelper = getApplicationContext().getContentResolver();
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        String[] args = new String[]{String.valueOf(getContactID(contactHelper,
                number))};
        ops.add(ContentProviderOperation.newDelete(ContactsContract.RawContacts.CONTENT_URI).withSelection(ContactsContract.RawContacts.CONTACT_ID + "=?", args).build());
        try {
            contactHelper.applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
    }

    private static long getContactID(ContentResolver contactHelper, String
            number) {
        Uri contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(number));
        String[] projection = {ContactsContract.PhoneLookup._ID};
        Cursor cursor = null;
        try {
            cursor = contactHelper.query(contactUri, projection, null, null, null);
            if (cursor.moveToFirst()) {
                int personID = cursor.getColumnIndex(ContactsContract.PhoneLookup._ID);
                return cursor.getLong(personID);
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
        return -1;
    }

    public void requestContactPermission() {
        // TODO implement
        return;
    }


    public void MakeVideoCall(String DisplayName, String MobileNumber) {
        addContact(DisplayName, MobileNumber);
        requestContactPermission();
        // TODO search for available mentor
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        videoCall(DisplayName);
//        deleteContact(MobileNumber);
    }


    private void makeACall(boolean isTv, boolean isPc, boolean isPhone, boolean
            isAC, boolean isWashingMachine, boolean isOther, final GenericCallback callback) {
        final DatabaseReference database;
        DatabaseReference childRef;
        database = FirebaseDatabase.getInstance().getReference();
        childRef = database.child("helpers");
        final App_User toCheck = new App_User(isTv, isPc, isPhone, isAC, isWashingMachine, isOther);

        FirebaseDatabase.getInstance().getReference().child("helpers")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                App_User user = snapshot.getValue(App_User.class);
                            if (user.equals(toCheck)
                                    ) {
                                String phoneNumber = snapshot.getKey();
                                callback.callme(phoneNumber);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
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
}
