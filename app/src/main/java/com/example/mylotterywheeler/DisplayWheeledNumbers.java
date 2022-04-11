package com.example.mylotterywheeler;

// the string needed to get the arraylist of the user's selected numbers from the previous activity
import static com.example.mylotterywheeler.EnterNumbers.EXTRA_ARRAYLIST;
import static com.example.mylotterywheeler.SelectWheel.EXTRA_WHEEL_NAME;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class DisplayWheeledNumbers extends AppCompatActivity {

    //length of wheel name string that indicates it uses a power number
    private final int IS_POWER = 7;
    private static final int NUMS_PER_GAME = 5;
    private int[][] wheelTemplate;
    private String chosenWheel;
    private ArrayList<Integer> selectedNums;
    private WheelGameInfo gameInfo;
    private HashSet<int[]> fullCombos;
    private TextView displayCombosTV;
    private TextView topMessage;
    protected static final String EXTRA_WHEEL_NAME = "com.example.mylotterywheeler.extra.WHEELNAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_wheeled_numbers);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        // create gameInfo to create wheeled number combos
        gameInfo = new WheelGameInfo();

        // get data from previous activities
        Intent intent = getIntent();

        // String with the name of the wheel the user chose
        chosenWheel = intent.getStringExtra(EXTRA_WHEEL_NAME);
        // the numbers the user selected
        selectedNums = intent.getIntegerArrayListExtra(EXTRA_ARRAYLIST);

        // int[][] needed to create wheeled number combos
        wheelTemplate = gameInfo.getWheelCombos(chosenWheel);

        // int[][] that holds to final combinations displayed to the user
        fullCombos = new HashSet<>();

        // generate the combinations for all of the games in the wheel
        setAllCombos();

        // display all of the combos for the user in the text view
        topMessage = (TextView) findViewById(R.id.top_message_display);
        topMessage.setText("Here are your numbers to play!");
        displayCombosTV = (TextView) findViewById(R.id.display_final_combos);
        displayCombosTV.setText(displayAllCombos());
        displayCombosTV.setMovementMethod(new ScrollingMovementMethod());


    }

    private void setAllCombos() {
        for (int[] template : wheelTemplate) {
            fullCombos.add(setThisCombo(template));
        }
    }

    public String displayAllCombos() {
        String text = "";
        int c = 1;
        for (int[] combo : fullCombos) {
            text += c++ + ") ";
            for (int i = 0; i < NUMS_PER_GAME; i++) {
                text += combo[i] + " ";
            }
            text += "\n\n";
        }
        return text;
    }

    private int[] setThisCombo(int[] template) {
        int[] combo = new int[selectedNums.size()];
        //position in this combo array
        int curr = 0;
        //take the correct numbers from numbersToPlay and insert them into
        //this combo array
        for (int pos : template) {
            combo[curr++] = selectedNums.get(pos - 1);
        }
        return combo;
    }

    public void restartApp(View view) {
        Intent intent = new Intent(this, FullscreenActivity.class);
        startActivity(intent);
    }

    public void returnPreviousActivity(View view) {
        Intent intent;
        if (chosenWheel.length() == IS_POWER) {
            intent =  new Intent(this, ChoosePowerNumber.class);
        } else {
            intent = new Intent(this, SelectWheel.class);
        }
        intent.putIntegerArrayListExtra(EXTRA_ARRAYLIST, selectedNums);
        startActivity(intent);
    }
}