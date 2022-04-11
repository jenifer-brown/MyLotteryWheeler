package com.example.mylotterywheeler;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class ChooseNumDigits extends AppCompatActivity {

    private WheelGameInfo gameInfo;
    protected int numDigits = 0;
    // extra used with intent to communicate numDigits variable between activities
    protected static final String EXTRA_NUMBER = "com.example.mylotterywheeler.extra.NUMBER";
    protected static final int DEFAULT_DIGITS = 6;
    private NumberPicker numberPicker;
    private TextView numPickerTextView;
    private ArrayList<String> numDigitsAvailable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_num_digits);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        gameInfo = new WheelGameInfo();
        // ArrayList of numbers of digits available to play in all games
        numDigitsAvailable = gameInfo.getNumDigitsAvailable();

        // set values for number picker
        numberPicker = (NumberPicker) findViewById(R.id.number_picker);
        numPickerTextView = (TextView) findViewById(R.id.select_num_textView);
        numberPicker.setMaxValue(Integer.parseInt(numDigitsAvailable.get(numDigitsAvailable.size() - 1)));
        numberPicker.setMinValue(Integer.parseInt(numDigitsAvailable.get(0)));
        numberPicker.setValue(Integer.parseInt(numDigitsAvailable.get(numDigitsAvailable.size() / 2)));
        numberPicker.setDisplayedValues(numDigitsAvailable.toArray(new String[numDigitsAvailable.size()]));
        numPickerTextView.setText("Wheel " + numberPicker.getValue() + " numbers");

        // add functionality to number picker when user selects a value
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                numPickerTextView.setText("Wheel " + i1 + " numbers");
            }
        });
    }

    public void launchEnterNumbers(View view) {
        Intent intent = new Intent(this, EnterNumbers.class);
        // assign numDigits the value chosen by the user
        numDigits = numberPicker.getValue();
        // add numDigits to intent to communicate to next activity
        intent.putExtra(EXTRA_NUMBER, numDigits);
        startActivity(intent);
    }

    public void returnHome(View view) {
        Intent intent = new Intent(this, FullscreenActivity.class);
        startActivity(intent);
    }
}