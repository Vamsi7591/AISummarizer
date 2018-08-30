package com.example.aisummarizer.aisummarizer.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.aisummarizer.aisummarizer.LangModel;
import com.example.aisummarizer.aisummarizer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sathish on 9/2/2017.
 */

public class SpinnerAdapter extends BaseAdapter implements android.widget.SpinnerAdapter {

    private final Context activity;
    private List<LangModel> asr;

    public SpinnerAdapter(Context context, List<LangModel> asr) {
        this.asr = asr;
        activity = context;
    }

    public int getCount() {
        return asr.size();
    }

    public Object getItem(int i) {
        return asr.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView txt = new TextView(activity);
        txt.setPadding(16, 16, 0, 16);
        txt.setTextSize(18);
        txt.setGravity(Gravity.CENTER);
        txt.setText(asr.get(position).getLanguage());
        txt.setTextColor(Color.BLACK);
//        txt.setBackgroundResource(activity.getResources().getDrawable(R.drawable.line_divider));

        GradientDrawable gd = new GradientDrawable();
//        gd.setColor(0xef2e83fa); // Changes this drawable to use a single color instead of a gradient
        gd.setCornerRadius(0);
        gd.setStroke(1, 0xef2e83fa);
        gd.setSize(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        gd.setShape(GradientDrawable.RECTANGLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            txt.setBackground(gd);
        }

        return txt;
    }


    public View getView(int i, View view, ViewGroup viewgroup) {
        TextView txt = new TextView(activity);
        txt.setMaxLines(1);
        txt.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        txt.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        txt.setGravity(Gravity.RIGHT);
        txt.setPadding(16, 16, 0, 16);
        txt.setTextSize(18);
//        txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_down, 0);
        txt.setText(asr.get(i).getLanguage());
        txt.setTextColor(Color.BLACK);
        return txt;
    }

}
