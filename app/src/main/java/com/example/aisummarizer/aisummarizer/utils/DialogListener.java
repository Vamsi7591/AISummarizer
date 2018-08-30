package com.example.aisummarizer.aisummarizer.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.example.aisummarizer.aisummarizer.R;


/**
 * Created by Sathish on 09-Sep-16.
 */

public class DialogListener implements DialogInterface.OnClickListener {

    Context context;

    public DialogListener(Context context) {
        super();
        context = context;
    }

    public static void createAlertDialog(Context context, final String message, boolean calncelable) {
        AlertDialog.Builder alertbox = new AlertDialog.Builder(context);

        String setmessage = message;

        if (setmessage.contains("@")) {
            String getmessage[] = setmessage.split("@");
            alertbox.setTitle(getmessage[0]);
            alertbox.setMessage(getmessage[1]);
        } else {
            alertbox.setTitle(context.getResources().getString(R.string.app_name));
            alertbox.setMessage(setmessage);
        }

        alertbox.setCancelable(calncelable);

        alertbox.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        alertbox.show();
    }

    public static void createToastDialog(Activity activity, String string) {
        Toast.makeText(activity, "" + string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }
}
