package com.example.mylotterywheeler;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private int numDigits = 0;
    private static final int DEFAULT_DIGITS = 6;


    public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
        numDigits = (int) parent.getSelectedItem();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        numDigits = DEFAULT_DIGITS;
    }

    public int getNumDigits() {
        return numDigits;
    }
}
