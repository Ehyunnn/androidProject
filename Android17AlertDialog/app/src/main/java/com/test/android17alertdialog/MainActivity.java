package com.test.android17alertdialog;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_READ_CONTACTS = 33;
    private static final int DIALOG_YES_NO_MESSAGE = 1;
    private static final int DIALOG_YES_NO_LONG_MESSAGE = 2;
    private static final int DIALOG_LIST = 3;
    private static final int DIALOG_PROGRESS = 4;
    private static final int DIALOG_SINGLE_CHOICE = 5;
    private static final int DIALOG_MULTIPLE_CHOICE = 6;
    private static final int DIALOG_TEXT_ENTRY = 7;
    private static final int DIALOG_MULTIPLE_CHOICE_CURSOR = 8;
    private static final int DIALOG_YES_NO_ULTRA_LONG_MESSAGE = 9;
    private static final int DIALOG_YES_NO_OLD_SCHOOL_MESSAGE = 10;
    private static final int DIALOG_YES_NO_HOLO_LIGHT_MESSAGE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dialog);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    REQUEST_CODE_READ_CONTACTS);
        }

        /* Display a text message with yes/no buttons and handle each message as well as the cancel action */
        Button twoButtonsTitle = findViewById(R.id.two_buttons);
        twoButtonsTitle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setIconAttribute(android.R.attr.alertDialogIcon)
                        .setTitle(R.string.alert_dialog_two_buttons_title)
                        .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Log.i("testLog","OK...");
                                /* User clicked OK so do some stuff */
                            }
                        })
                        .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                /* User clicked Cancel so do some stuff */
                            }
                        })
                        .show();
            }
        });

        Button twoButtons2Title = (Button) findViewById(R.id.two_buttons2);
        twoButtons2Title.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_YES_NO_LONG_MESSAGE);
            }
        });

        /* Display a list of checkboxes, backed by a cursor */
        Button checkBox2 = (Button) findViewById(R.id.checkbox_button2);
        checkBox2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_MULTIPLE_CHOICE_CURSOR);
            }
        });
    }//end onCreate()

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case DIALOG_YES_NO_MESSAGE:
                return new AlertDialog.Builder(MainActivity.this)
                        .setIconAttribute(android.R.attr.alertDialogIcon)
                        .setTitle(R.string.alert_dialog_two_buttons_title)
                        .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Log.i("testLog","OK...");
                                /* User clicked OK so do some stuff */
                            }
                        })
                        .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                /* User clicked Cancel so do some stuff */
                            }
                        })
                        .create();
            case DIALOG_YES_NO_LONG_MESSAGE:
                return new AlertDialog.Builder(MainActivity.this)
                        .setIconAttribute(android.R.attr.alertDialogIcon)
                        .setTitle(R.string.alert_dialog_two_buttons_msg)
                        .setMessage(R.string.alert_dialog_two_buttons2_msg)
                        .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                /* User clicked OK so do some stuff */
                            }
                        })
                        .setNeutralButton(R.string.alert_dialog_something, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                /* User clicked Something so do some stuff */
                            }
                        })
                        .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                /* User clicked Cancel so do some stuff */
                            }
                        })
                        .create();
            case DIALOG_MULTIPLE_CHOICE_CURSOR:
                String[] projection = new String[] {
                        ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.DISPLAY_NAME,
                        ContactsContract.Contacts.SEND_TO_VOICEMAIL
                };
                Cursor cursor = managedQuery(ContactsContract.Contacts.CONTENT_URI,
                        projection, null, null, null);
                return new AlertDialog.Builder(MainActivity.this)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle(R.string.alert_dialog_multi_choice_cursor)
                        .setMultiChoiceItems(cursor,
                                ContactsContract.Contacts.SEND_TO_VOICEMAIL,
                                ContactsContract.Contacts.DISPLAY_NAME,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton,
                                                        boolean isChecked) {
                                        Toast.makeText(MainActivity.this,
                                                "Readonly Demo Only - Data will not be updated",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .create();
        }
        return null;
    }//end onCreateDialog()
}//end class
