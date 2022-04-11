package com.example.mylotterywheeler;

import static com.example.mylotterywheeler.EnterNumbers.EXTRA_ARRAYLIST;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class ChoosePowerNumber extends AppCompatActivity {

    //length of wheel name string that indicates it uses a power number
    private final int IS_POWER = 7;

    private String chosenWheel;
    private ArrayList<Integer> selectedNums;
    //holds the information about how to combine the numbers, number of games for each wheel, etc
    private WheelGameInfo gameInfo;
    // number picker to help user choose their desired power number
    private NumberPicker selectPower;
    private int numPowerNums = 0;
    private int remainingPowerNums = 0;
    private TextView topMessage;
    private TextView selectPowerTextView;
    private TextView chosenPowerTextView;
    private ArrayList<Integer> powerNums;
    private String[] sortedNums;
    protected static final String EXTRA_WHEELPOWER = "com.example.mylotterywheeler." +
            "extra.EXTRA_WHEELPOWER"; 
    protected static final String EXTRA_WHEEL_NAME = "com.example.mylotterywheeler.extra.WHEELNAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_power_number);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        // get data from previous activity
        Intent intent = getIntent();

        // get chosenWheel string and selected numbers from previous activity
        chosenWheel = intent.getStringExtra(SelectWheel.EXTRA_WHEEL_NAME);
        selectedNums = intent.getIntegerArrayListExtra(EXTRA_ARRAYLIST);

        gameInfo = new WheelGameInfo();

        // number of power numbers the user can choose
        numPowerNums = (int) Integer.parseInt("" + chosenWheel.charAt(IS_POWER - 1));
        remainingPowerNums = Integer.parseInt("" + chosenWheel.charAt(IS_POWER - 1));

        // use number picker to set power number
        selectPower = (NumberPicker) findViewById(R.id.select_power_picker);
        selectPower.setMinValue(0);
        selectPower.setMaxValue(selectedNums.size() - 1);
        sortedNums = getSelectNumsStrArray();
        selectPower.setDisplayedValues(sortedNums);
        selectPower.setWrapSelectorWheel(false);

        // let user know how many more numbers they can choose
        topMessage = (TextView) findViewById(R.id.top_message_power);

        topMessage.setText(String.valueOf(numPowerNums));
        displayPowerTopMessage();

        // store the power numbers chosen by the user
        powerNums = new ArrayList<>();

        // the number selected by the user as shown on the numberpicker
        selectPowerTextView = (TextView) findViewById(R.id.selected_power);
        selectPowerTextView.setText("Selected: " + sortedNums[selectPower.getValue()]);

        // the number the user has chosen by clicking the "choose" button
        chosenPowerTextView = (TextView) findViewById(R.id.chosen_power);
        displayPowerNums();

        selectPower.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                selectPowerTextView.setText("Selected: " + sortedNums[i1]);
            }
        });
    }

    private void displayPowerTopMessage() {
        if (remainingPowerNums == 1) topMessage.setText("Choose 1 Power Number");
        else if (remainingPowerNums == 2) topMessage.setText("Choose 2 Power Numbers");
    }


    private void displayPowerNums() {
        String text;
        if (numPowerNums == 1) text = "Your Power Number: \n";
        else text = "Your Power Numbers: \n";

        if (powerNums != null) {
            for (int i = 0; i < powerNums.size(); i++) {
                if (i == 0) text += powerNums.get(i);
                else text += ", " + powerNums.get(i);
            }
        }
        chosenPowerTextView.setText(text);
    }

    public void chooseClicked(View view) {
        int num = Integer.parseInt(sortedNums[selectPower.getValue()]);

        // don't do anything if the user has no remaining power numbers or num already chosen
        if (remainingPowerNums == 0 || powerNums.contains(num)) return;

        // otherwise add number to power nums and display result
        powerNums.add(num);
        displayPowerNums();
        remainingPowerNums--;

        if (remainingPowerNums == 1) {
            topMessage.setText("Choose 1 more Power Number");
        } else if (remainingPowerNums == 0) {
            if (numPowerNums == 1) topMessage.setText("Here is your Power Number!");
            else if (numPowerNums == 2) topMessage.setText("Here are your Power Numbers!");
            }
        return;
    }

    // removes the last element in the array. Returns false if there are no more objects to remove
    public boolean removeNum() {
        int i = powerNums.size() - 1;
        if (i >= 0) {
            return powerNums.remove(i) >= 0;
        }
        return false;
    }

    // re-displays the updated numbers if number can be removed
    public void undoPower(View view) {
        if (removeNum()) {
            remainingPowerNums++;
            displayPowerTopMessage();
            displayPowerNums();
        }
    }

    // clears all of the numbers the user has chosen
    public void clearPower(View view) {
        powerNums.clear();
        remainingPowerNums = numPowerNums;
        displayPowerTopMessage();
        displayPowerNums();
    }

    private String[] getSelectNumsStrArray() {
        String[] arr = new String[selectedNums.size()];

        // sort selectedNums in ascending order for convenient display, don't sort original list
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(0, selectedNums);
        Collections.sort(list);

        for (int i = 0; i < list.size(); i++) {
            arr[i] = String.valueOf(list.get(i));
        }
        return arr;
    }

    public void setPowerNumber() {
        // always put the power numbers at the front of the arraylist of selected numbers
        int powIndex = 0;
        for (int num : powerNums) {
            //put power number at the front of the numbers to play arraylist
            // figure out where the desired power number is
            int index = selectedNums.indexOf(num);
            //adds the power number to the front of list but now shifted by 1 and with extra entry
            selectedNums.add(powIndex++, num);
            //remove old number (duplicate)
            selectedNums.remove(index + 1);
        }
    }

    public void launchDisplayWheeledNumbers(View view) {
        setPowerNumber();
        Intent intent = new Intent(this, DisplayWheeledNumbers.class);
        intent.putIntegerArrayListExtra(EXTRA_ARRAYLIST, selectedNums);
        intent.putExtra(EXTRA_WHEEL_NAME, chosenWheel);
        startActivity(intent);
    }

    public void returnSelectWheel(View view) {
        Intent intent = new Intent(this, SelectWheel.class);
        intent.putIntegerArrayListExtra(EXTRA_ARRAYLIST, selectedNums);
        intent.putExtra(EXTRA_WHEEL_NAME, chosenWheel);
        startActivity(intent);
    }

}