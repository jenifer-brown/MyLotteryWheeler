package com.example.mylotterywheeler;//lists all of the available games, their stats, and how to construct them

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class WheelGameInfo {
    private static final String[] wheelNames = new String[16];
    private static final HashMap<String, Integer> numGamesToPlay = new HashMap<>();
    private static final HashMap<String, int[][]> wheelCombos = new HashMap<>();

    WheelGameInfo() {
        // second digit: max numbers to win
        // third digit: out of numbers that match (add digit to previous num
        // fourth and fifth digits: num of digits to wheel
        // -1 == 1 power number
        wheelNames[0] = "53008-1";
        numGamesToPlay.put(wheelNames[0], 5);
        int[][] games530081 = {{1, 2, 3, 7, 8}, {1, 2, 4, 7, 8}, {1, 2, 5, 6, 7},
                                {1, 2, 5, 6, 8}, {1, 3, 4, 5, 6}};
        wheelCombos.put(wheelNames[0], games530081);

        wheelNames[1] = "53009-1";
        numGamesToPlay.put(wheelNames[1], 6);
        int[][] games530091 = {{1, 2, 3, 7, 9}, {1, 2, 4, 8, 9}, {1, 2, 5, 6, 9},
                                {1, 3, 4, 6, 8}, {1, 3, 5, 7, 8}, {1, 3, 5, 6, 7}};
        wheelCombos.put(wheelNames[1], games530091);

        wheelNames[2] = "53010-1";
        numGamesToPlay.put(wheelNames[2], 8);
        int[][] games530101 = {{1, 2, 3, 9, 10}, {1, 2, 4, 8, 10}, {1, 2, 5, 8, 10},
                                 {1, 2, 6, 7, 10}, {1, 3, 4, 7, 8}, {1, 3, 5, 7, 9},
                                    {1, 3, 6, 8, 9}, {1, 4, 5, 6, 9}};
        wheelCombos.put(wheelNames[2], games530101);

        wheelNames[3] = "54109-1";
        numGamesToPlay.put(wheelNames[3], 6);
        int[][] games541091 = {{1, 2, 4, 6, 7}, {1, 2, 4, 8, 9}, {1, 2, 6, 7, 8},
                                {1, 3, 4, 5, 8}, {1, 3, 5, 6, 9}, {1, 3, 5, 7, 9}};
        wheelCombos.put(wheelNames[3], games541091);


        wheelNames[4] = "54110-1";
        numGamesToPlay.put(wheelNames[4], 10);
        int[][] games541101 = {{1, 2, 3, 7, 10}, {1, 2, 3, 8, 10}, {1, 2, 4, 6, 9},
                                 {1, 2, 5, 6, 9}, {1, 3, 7, 8, 9}, {1, 3, 5, 6, 9},
                                    {1, 3, 6, 7, 10}, {1, 3, 6, 8, 10}, {1, 4, 5, 7, 8},
                                         {1, 4, 5, 9, 10}};
        wheelCombos.put(wheelNames[4], games541101);


        wheelNames[5] = "53006";
        numGamesToPlay.put(wheelNames[5], 4);
        int[][] games53006 = {{1, 2, 3, 4, 6}, {1, 2, 3, 5, 6}, {1, 2, 4, 5, 6},
                                 {1, 3, 4, 5, 6}};
        wheelCombos.put(wheelNames[5], games53006);

        wheelNames[6] = "53007";
        numGamesToPlay.put(wheelNames[6], 5);
        int[][] games53007 = {{1, 2, 3, 6, 7}, {1, 2, 4, 6, 7}, {1, 2, 5, 6, 7},
                                 {1, 3, 4, 5, 7}, {2, 3, 4, 5, 6}};
        wheelCombos.put(wheelNames[6], games53007);

        wheelNames[7] = "53009";
        numGamesToPlay.put(wheelNames[7], 12);
        int[][] games53009 = {{1, 2, 3, 7, 9}, {1, 2, 4, 6, 8}, {1, 2, 5, 6, 8},
                                {1, 3, 4, 8, 9}, {1, 3, 5, 6, 9}, {1, 4, 5, 7, 8}, {1, 6, 7, 8, 9},
                                    {2, 3, 4, 6, 9}, {2, 3, 5, 8, 9}, {2, 3, 6, 7, 8},
                                        {2, 4, 5, 7, 9}, {3, 4, 5, 6, 7}};
        wheelCombos.put(wheelNames[7], games53009);

        wheelNames[8] = "53211";
        numGamesToPlay.put(wheelNames[8], 5);
        int[][] games53211 = {{1, 3, 4, 10, 11}, {1, 4, 7, 8, 9}, {2, 5, 6, 7, 8},
                                {2, 5, 6, 7, 9}, {2, 5, 6, 8, 9}};
        wheelCombos.put(wheelNames[8], games53211);

        wheelNames[9] = "53212";
        numGamesToPlay.put(wheelNames[9], 6);
        int[][] games53212 = {{1, 3, 7, 10, 11}, {1, 4, 6, 8, 12}, {1, 4, 8, 9, 12},
                                {2, 4, 5, 8, 12}, {2, 6, 7, 9, 10}, {3, 5, 6, 9, 11}};
        wheelCombos.put(wheelNames[9], games53212);

        wheelNames[10] = "54108";
        numGamesToPlay.put(wheelNames[10], 5);
        int[][] games54108 = {{1, 2, 4, 7, 8}, {1, 3, 4, 5, 8}, {1, 3, 4, 6, 8},
                                  {1, 4, 5, 6, 8}, {2, 3, 5, 6, 7}};
        wheelCombos.put(wheelNames[10], games54108);

        wheelNames[11] = "54109";
        numGamesToPlay.put(wheelNames[11], 9);
        int[][] games54109 = {{1, 2, 4, 6, 9}, {1, 2, 5, 6, 8}, {1, 3, 4, 7, 8},
                                {1, 3, 5, 6, 7}, {1, 5, 6, 8, 9}, {2, 3, 4, 5, 9}, {2, 3, 7, 8, 9},
                                     {2, 4, 5, 7, 9}, {3, 4, 6, 7, 8}};
        wheelCombos.put(wheelNames[11], games54109);

        wheelNames[12] = "53111";
        numGamesToPlay.put(wheelNames[12], 10);
        int[][] games53111 = {{1, 3, 4, 10, 11}, {1, 4, 5, 7, 8}, {1, 4, 6, 8, 10},
                                  {1, 4, 8, 9, 11}, {2, 3, 4, 5, 11}, {2, 3, 6, 7, 9}, {2, 3, 8, 10, 11},
                                      {2, 5, 6, 7, 11}, {2, 5, 7, 9, 10}, {3, 5, 6, 9, 11}};
        wheelCombos.put(wheelNames[12], games53111);

        wheelNames[13] = "53112";
        numGamesToPlay.put(wheelNames[13], 12);
        int[][] games53112 = {{1, 2, 4, 9, 11}, {1, 3, 6, 7, 12}, {1, 4, 7, 8, 10},
                                  {1, 5, 6, 9, 10}, {1, 5, 8, 11, 12}, {2, 3, 5, 7, 8},
                                     {2, 3, 9, 10, 12}, {2, 4, 6, 8, 12}, {2, 6, 7, 10, 11},
                                        {3, 4, 5, 10, 11}, {3, 6, 8, 9, 11}, {4, 5, 7, 9, 12}};
        wheelCombos.put(wheelNames[13], games53112);

        wheelNames[14] = "53109";
        numGamesToPlay.put(wheelNames[14], 5);
        int[][] games53109 = {{1, 2, 3, 8, 9}, {1, 4, 5, 6, 8}, {1, 4, 6, 7, 9},
                                {2, 3, 5, 7, 8}, {2, 4, 5, 6, 9}};
        wheelCombos.put(wheelNames[14], games53109);

        wheelNames[15] = "53210";
        numGamesToPlay.put(wheelNames[15], 2);
        int[][] games53210 = {{1, 2, 6, 9, 10}, {3, 4, 5, 7, 8}};
        wheelCombos.put(wheelNames[15], games53210);

    }

    public HashMap<String, Integer> getNumGamesToPlay() {
        return numGamesToPlay;
    }

    public int[][] getWheelCombos(String wheelName) {
        return wheelCombos.get(wheelName);
    }

    public String[] getWheelNames() {
        return wheelNames;
    }

    public ArrayList<String> getNumDigitsAvailable() {
        ArrayList<String> listNumDigits = new ArrayList<>();

        String[] wheels = this.getWheelNames();

        for (String wheel : wheels) {
            if (!listNumDigits.contains(wheel.substring(3, 5))) {
                listNumDigits.add(wheel.substring(3, 5));
            }
        }
        Collections.sort(listNumDigits);
        return listNumDigits;
    }


}
