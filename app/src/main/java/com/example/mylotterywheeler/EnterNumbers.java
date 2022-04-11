package com.example.mylotterywheeler;

import static com.example.mylotterywheeler.ChooseNumDigits.DEFAULT_DIGITS;
import static com.example.mylotterywheeler.ChooseNumDigits.EXTRA_NUMBER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;

public class EnterNumbers extends AppCompatActivity {

    private TextView topMessage;
    private TextView selectedMessage;
    private TextView displayNumbers;
    private int numDigits;
    private NumberPicker numberPicker;
    private static final int MAX_VAL = 43;
    private static final int MIN_VAL = 1;
    ArrayList<Integer> selectedNums;
    protected static final String EXTRA_ARRAYLIST = "com.example.mylotterywheeler.extra.ARRAYLIST";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_numbers);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        // get information from previous activities if it exists
        Intent intent = getIntent();

        // create empty ArrayList to hold the numbers the user selects
        selectedNums = new ArrayList<>();

        if (intent.getIntegerArrayListExtra(EXTRA_ARRAYLIST) != null) {
            selectedNums = intent.getIntegerArrayListExtra(EXTRA_ARRAYLIST);
        }

        // TextView that displays the numbers the user has chosen
        displayNumbers = (TextView) findViewById(R.id.display_nums_textView);
        displayNumbers.setText("Numbers: ");

        // get data from previous activity
        numDigits = intent.getIntExtra(EXTRA_NUMBER, DEFAULT_DIGITS);

        // set top message with data from previous activity
        topMessage = (TextView) findViewById(R.id.top_Message_TextView);
        topMessage.setText("Please enter your " + numDigits + " numbers");

        //create number picker for user to select numbers
        numberPicker = (NumberPicker) findViewById(R.id.number_picker_enter);
        numberPicker.setMinValue(MIN_VAL);
        numberPicker.setMaxValue(MAX_VAL);
        numberPicker.setValue(MAX_VAL / 2);

        // set selectedMessage TextView to display the number that the user has selected
        selectedMessage = (TextView) findViewById(R.id.number_selected_textView);
        selectedMessage.setText("Number Selected: " + numberPicker.getValue());

        if (selectedNums.size() == numDigits) showNumbers();

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                selectedMessage.setText("Number Selected: " + i1 + "");
            }
        });
    }

    // displays numbers in the selectedNums arraylist on the numbersChosen textview
    public void showNumbers() {
        String nums = "";
        for (int i = 0; i < selectedNums.size(); i++) {
            if (i == 0 ) {
                nums += selectedNums.get(i);
            } else {
                nums += ", " + selectedNums.get(i);
            }
        }
        displayNumbers.setText("Numbers: " + nums);

        String text = "";
        if (selectedNums.size() < numDigits) {
            text += "Choose " + (numDigits - selectedNums.size()) + " more number";
            if (selectedNums.size() < numDigits - 1) text += "s";
        } else {
            text += "These are your " + numDigits + " numbers!";
        }
        topMessage.setText(text);
    }

    // returns true if the user has chosen all of the numbers they can choose
    public boolean isFull() {
        if (selectedNums.size() == numDigits) {
            return true;
        }
        return false;
    }

    // adds a number to the user's number bank; returns false if number is already chosen
    public boolean add(View view) {
        int num = numberPicker.getValue();
        if (selectedNums.contains(num) || selectedNums.size() >= numDigits) {
            return false;
        }
        selectedNums.add(num);
        showNumbers();
        return true;
    }

    // removes the last element in the array. Returns false if there are no more objects to remove
    public boolean removeNum() {
        int i = selectedNums.size() - 1;
        if (i >= 0) {
            return selectedNums.remove(i) >= 0;
        }
        return false;
    }

    // re-displays the updated numbers if number can be removed
    public void undo(View view) {
        if (removeNum()) showNumbers();
    }

    // clears all of the numbers the user has chosen
    public void clear(View view) {
        selectedNums.clear();
        showNumbers();
    }

    // proceeds to next activity only if all numbers are selected
    public void launchVerifyNumbers(View view) {
        if (isFull()) {
            Intent intent = new Intent(this, VerifyNumbers.class);
            // communicates arraylist of selectedNums to the next activity
            intent.putIntegerArrayListExtra(EXTRA_ARRAYLIST, selectedNums);
            startActivity(intent);
        }
    }

    // goes back to previous activity and clears all data
    public void returnChooseNumDigits(View view) {
        Intent intent = new Intent(this, ChooseNumDigits.class);
        startActivity(intent);
    }

}