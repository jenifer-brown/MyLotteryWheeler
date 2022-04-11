package com.example.mylotterywheeler;

import androidx.appcompat.app.AppCompatActivity;

// the string needed to get the arraylist of the user's selected numbers from the previous activity
import static com.example.mylotterywheeler.EnterNumbers.EXTRA_ARRAYLIST;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;

public class SelectWheel extends AppCompatActivity {

    private WheelGameInfo gameInfo;
    private ArrayList<String> wheelsAvailable;
    private ArrayList<Integer> selectedNums;
    private int numDigits;
    private NumberPicker wheelsPicker;
    private TextView wheelsDisplay;
    private TextView topMessage;
    private String chosenWheel;
    private TextView selectedWheelTextView;
    //length of wheel name string that indicates it uses a power number
    private final int IS_POWER = 7;
    protected static final String EXTRA_WHEEL_NAME = "com.example.mylotterywheeler.extra.WHEELNAME";
    protected static final String EXTRA_NUMBER = "com.example.mylotterywheeler.extra.NUMBER";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_wheel);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        // initialize the gameInfo used to create the wheels
        gameInfo = new WheelGameInfo();

        // get the numbers the user selected from the previous activity
        Intent intent = getIntent();
        selectedNums = intent.getIntegerArrayListExtra(EXTRA_ARRAYLIST);

        // number of digits the user selected
        numDigits = selectedNums.size();

        // display informational top message
        topMessage = (TextView) findViewById(R.id.top_message_select);
        topMessage.setText("Wheels available for "
                + numDigits + " numbers");

        // display in a number picker the wheels the user can use given the number of digits chosen
        wheelsPicker = (NumberPicker) findViewById(R.id.picker_select_wheel);
        this.wheelsToUse();
        String[] wheels = new String[wheelsAvailable.size()];
        for (int i = 0; i < wheelsAvailable.size(); i++) {
            wheels[i] = wheelsAvailable.get(i);
        }
        wheelsPicker.setDisplayedValues(wheels);
        wheelsPicker.setMinValue(0);
        wheelsPicker.setMaxValue(wheels.length - 1);
        wheelsPicker.setWrapSelectorWheel(false);
        wheelsDisplay = (TextView) findViewById(R.id.display_wheel_info_select_wheel);
        wheelsDisplay.setText(wheelGameInfoMessage(wheelsPicker.getValue()));

        wheelsPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                wheelsDisplay.setText(wheelGameInfoMessage(i1));
            }
        });

        // textView that displays the wheel the user has chosen
        selectedWheelTextView = (TextView) findViewById(R.id.selected_wheel_textView);
    }

    private String wheelGameInfoMessage(int val) {
        String selectedWheel = wheelsAvailable.get(val);
        String message = "Wheel #" + selectedWheel + ":\n";
        message += gameInfo.getNumGamesToPlay().get(selectedWheel)
                + " games to play with a " + selectedWheel.charAt(1) + " of "
                + (Integer.parseInt(selectedWheel.substring(1, 2)) +
                Integer.parseInt(selectedWheel.substring(2, 3))) + " win";
        return message;
    }

    public void setChosenWheel(View view) {
        chosenWheel = wheelsAvailable.get(wheelsPicker.getValue());
        selectedWheelTextView.setText("You've selected wheel #" + chosenWheel);
    }

    public void wheelsToUse(){
        //figure out which wheels the player can use given number of digits to play
        wheelsAvailable = new ArrayList<>();

        for (String str : gameInfo.getWheelNames()) {
            if (Integer.parseInt(str.substring(3, 5)) == numDigits) {
                wheelsAvailable.add(str);
            }
        }
    }

    public void launchNextActivity(View view) {
        Intent intent;
        if (chosenWheel == null) {
            selectedWheelTextView.setText("Please choose a wheel to continue");
            return;
        }
        if (chosenWheel.length() == IS_POWER) { // if the wheel has a power number to set
            intent = new Intent(this, ChoosePowerNumber.class);
        } else {
            intent = new Intent(this, DisplayWheeledNumbers.class);
        }
        intent.putExtra(EXTRA_WHEEL_NAME, chosenWheel);
        intent.putIntegerArrayListExtra(EXTRA_ARRAYLIST, selectedNums);
        startActivity(intent);
    }

    public void returnEnterNumbers(View view) {
        Intent intent = new Intent(this, EnterNumbers.class);
        intent.putExtra(EXTRA_NUMBER, selectedNums.size());
        intent.putIntegerArrayListExtra(EXTRA_ARRAYLIST, selectedNums);
        startActivity(intent);
    }
}