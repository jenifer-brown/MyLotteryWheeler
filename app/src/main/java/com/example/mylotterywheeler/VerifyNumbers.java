package com.example.mylotterywheeler;

import static com.example.mylotterywheeler.EnterNumbers.*;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;


public class VerifyNumbers extends AppCompatActivity {

    private ArrayList<Integer> selectedNums;
    private TextView displayNums;
    protected static final String EXTRA_NUMBER = "com.example.mylotterywheeler.extra.NUMBER";
    protected static final String EXTRA_ARRAYLIST = "com.example.mylotterywheeler.extra.ARRAYLIST";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_numbers);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        // get selectedNums arraylist from previous EnterNumbers activity
        Intent intent = getIntent();
        selectedNums = intent.getIntegerArrayListExtra(EXTRA_ARRAYLIST);

        // display the selectedNums on the numbers_verify textView
        displayNums = (TextView) findViewById(R.id.numbers_verify);
        displayNums.setText("Numbers: \n\n" + makeNumsString());
    }

    public String makeNumsString() {
        String result = "";
        for (int i = 0; i < selectedNums.size(); i++) {
            if (i == 0) result += selectedNums.get(i);
            else result += ", " + selectedNums.get(i);
        }
        return result;
    }

    public void launchSelectWheel(View view) {
        Intent intent = new Intent(this, SelectWheel.class);
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