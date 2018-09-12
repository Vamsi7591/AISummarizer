package com.example.aisummarizer.aisummarizer.utils;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.aisummarizer.aisummarizer.R;

public class Utility {

    Dialog dialog;
    Context context ;

    public Utility(Context context) {
        this.context = context;
    }

    public boolean checkInternetConnection() {

        ConnectivityManager conMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
// ARE WE CONNECTED TO THE NET
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    public void setInternetAlertMessage() {
        if (context == null)
            return;

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_alert_dialog);
        dialog.setCanceledOnTouchOutside(false);
        TextView textDialog = (TextView) dialog.findViewById(R.id.textDialog);
        textDialog.setText(context.getString(R.string.oops));
        textDialog.setPadding(15, 15, 15, 15);
        textDialog.setGravity(Gravity.CENTER);

        TextView textOk = (TextView) dialog.findViewById(R.id.textOk);
// if button is clicked, close the custom dialog
        textOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.show();
    }

    public void setAlertMessage(String title, String msg) {

        if (context == null)
            return;

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_alert_dialog);
        dialog.setCanceledOnTouchOutside(false);
        TextView textDialog = (TextView) dialog.findViewById(R.id.textDialog);
        textDialog.setText(msg);
        textDialog.setPadding(15, 15, 15, 15);
        textDialog.setGravity(Gravity.CENTER);

        TextView textOk = (TextView) dialog.findViewById(R.id.textOk);
        TextView textCancel = (TextView) dialog.findViewById(R.id.textCancel);
        textOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        textCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        dialog.show();
    }


}
