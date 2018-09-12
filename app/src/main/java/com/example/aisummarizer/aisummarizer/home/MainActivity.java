package com.example.aisummarizer.aisummarizer.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.aisummarizer.aisummarizer.R;
import com.example.aisummarizer.aisummarizer.about.AboutActivity;
import com.example.aisummarizer.aisummarizer.aisummarizer.AISummarizerActivity;
import com.example.aisummarizer.aisummarizer.donate.PaymentActivity;
import com.example.aisummarizer.aisummarizer.faq.FaqActivity;
import com.example.aisummarizer.aisummarizer.login.LoginActivity;
import com.example.aisummarizer.aisummarizer.service_calls.AISummarizer;
import com.example.aisummarizer.aisummarizer.super_class.SuperCompatActivity;

import java.util.Locale;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends SuperCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 200;

    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout) findViewById(R.id.rl);
        if (!checkPermission())
            requestPermission();
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (locationAccepted && cameraAccepted)
                        Snackbar.make(relativeLayout, "Permission Granted, Now you can access files.", Snackbar.LENGTH_LONG).show();
                    else {

                        Snackbar.make(relativeLayout, "Permission Denied, You cannot access files to be summarized.", Snackbar.LENGTH_LONG).show();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });
                                return;
                            }
                        }

                    }
                }


                break;
        }
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    /*Click actions*/
    public void about(View view) {
        Intent main_activity = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(main_activity);

    }

    public void faq(View view) {
        Intent main_activity = new Intent(getApplicationContext(), FaqActivity.class);
        startActivity(main_activity);
    }

    public void summarize(View view) {
        Intent main_activity = new Intent(getApplicationContext(), AISummarizerActivity.class);
        startActivity(main_activity);
    }

    public void donate(View view) {
        Intent main_activity = new Intent(getApplicationContext(), PaymentActivity.class);
        startActivity(main_activity);
    }
    /*Click actions end*/
}
