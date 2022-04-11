package com.example.mylotterywheeler;

import java.util.*;
import java.io.Console;


public class Wheeler {
    //numbers that the user wants to play in the lotto
    private ArrayList<Integer> numbersToPlay;
    //length of wheel name string that indicates it uses a power number
    private final int IS_POWER = 7;
    //number of digits to play
    private int numDigits;
    //final set of lottery numbers (as int[]) to play
    private HashSet<int[]> wheeledNumbers;
    //the name of the wheel being used to combine the numbers
    private String wheel;
    //holds the information about how to combine the numbers, number of games for each wheel, etc
    private WheelGameInfo gameInfo;
    //which wheels the player can use given the number of digits to play
    private HashSet<String> wheelsAvailable;
    //number of digits per lotto combo
    private final int NUMS_PLAY = 5;
    Console input = System.console();


    Wheeler() {
        System.out.println("WELCOME TO MY LOTTERY WHEELER!");
        gameInfo = new WheelGameInfo();
    }

    //how many numbers the person wants to wheel
    public void getNumDigitsToWheel() {
        try {
            String answer;
            do {
                System.out.println("How many numbers do you want to wheel? (6-43 numbers)  ");
                answer = input.readLine();
                numDigits = Integer.parseInt(answer);
                System.out.println("You want to wheel " + numDigits + " numbers? (y/n)");
                answer = input.readLine();
            } while (numDigits < 6 || numDigits > 43 || answer.toUpperCase().equals("N"));
            System.out.println("Awesome!");
            System.out.println();
        } catch (Exception ex) {
            System.out.println("There was an error: " + ex);
        }
    }

    //which numbers the person wants to wheel
    public void getLottoNumbers() {
        try {

            boolean correctNums = false;
            while (!correctNums) {
                String delimiter = ", ";
                numbersToPlay = new ArrayList<>();
                System.out.println("Enter the " + numDigits + " numbers you would like to wheel." +
                        " Separate numbers with a comma (ex. 1, 3, 8): ");
                String numbers = input.readLine();
                //start asking for numbers again if the user didn't enter anything
                if (numbers.equals("")) {
                    System.out.println("You didn't enter anything.");
                    continue;
                }
                //parse input using delimiter and make an int arraylist
                String[] nums = numbers.split(delimiter);
                for (String str : nums) {
                    numbersToPlay.add(Integer.parseInt(str));
                }

                //if they didn't enter the correct number of digits, start again
                if (numbersToPlay.size() != numDigits) {
                    System.out.println("You entered " + numbersToPlay.size() + " digits instead " +
                            "of " + numDigits + " digits.");
                    continue;
                }

                //check if the user wants to correct any numbers
                System.out.println("Are these numbers correct? (y/n)?  ");
                //print out numbers previously entered
                for (int num : numbersToPlay) {
                    System.out.println("(" + num + ")");
                }
                String answer = input.readLine();
                //ask for numbers again if input wasn't correct
                if (!answer.toUpperCase().equals("Y")) {
                    continue;
                }
                System.out.printf("Those look like lucky numbers! Let's get wheeling!\n");
                //can exit this loop if all the numbers entered are correct
                correctNums = true;
            }
        } catch (Exception ex) {
            System.out.println("There was an error: " + ex);
        }
    }

    public void wheelsToUse(){
        //figure out which wheels the player can use given number of digits to play
        wheelsAvailable = new HashSet<>();

        for (String str : gameInfo.getWheelNames()) {
            if (Integer.parseInt(str.substring(3, 5)) == numDigits) {
                wheelsAvailable.add(str);
            }
        }

    }

    public void setWheel() {
        try {
            String answer;
            this.wheelsToUse();
            do {
                answer = input.readLine("Here are the wheels you can use while playing "
                                         + numDigits + " numbers");
                for (String str : wheelsAvailable) {
                    System.out.println("Wheel #" + str + ": " + gameInfo.getNumGamesToPlay().get(str)
                            + " games to play with a " + str.charAt(1) + " of "
                            + (Integer.parseInt(str.substring(1, 2)) +  Integer.parseInt(str.substring(2, 3)))
                            + " Win");
                }
                answer = input.readLine("Which wheel do you want to use?  ");
                wheel = answer;
            } while (!wheelsAvailable.contains(answer));
            System.out.println("Awesome!");
            System.out.println();
        } catch (Exception ex) {
            System.out.println("There was an error: " + ex);
        }
    }


    //create one array of lotto numbers using lotto wheel
    public int[] setThisCombo(int[] template) {
        int[] combo = new int[NUMS_PLAY];
        //position in this combo array
        int curr = 0;
        //take the correct numbers from numbersToPlay and insert them into
        //this combo array
        for (int pos : template) {
            combo[curr++] = numbersToPlay.get(pos - 1);
        }
        return combo;
    }

    //put all of the combos from the wheel into a final set of all number combos
    public void setAllCombos() {
        wheeledNumbers = new HashSet<>();
        //get the number combinations associated with the selected wheel
        int[][] allCombos = gameInfo.getWheelCombos(wheel);

        if (wheel.length() == IS_POWER) {
            setPowerNums();
        }
        //create all of the combos described by the wheel and add them to the final
        //set of lotto numbers to play
        for (int[] combo : allCombos) {
            wheeledNumbers.add(setThisCombo(combo));
        }
    }

    public void setPowerNums() {
        int powerNums = Integer.parseInt(String.valueOf(wheel.charAt(IS_POWER - 1)));
        switch (powerNums) {
            case 1 :
                System.out.println("You can choose 1 power number");
                System.out.println("Which number would you like to choose?");
                break;
            case 2 :
                System.out.println("You can choose 2 power numbers");
                System.out.println("Which numbers would you like to choose?");
                break;
        }

        for (int num : numbersToPlay) {
            System.out.println(num);
        }

        int powIndex = 0;
        while (powIndex < powerNums) {
            System.out.println("Choose one power number:  ");
            int num = Integer.parseInt(input.readLine());
            while (!numbersToPlay.contains(num)) {
                System.out.println("Sorry, that number is not valid. Choose again:  ");
                num = Integer.parseInt(input.readLine());
            }
            //put power number at the front of the numbers to play arraylist
            // figure out where the desired power number is
            int index = numbersToPlay.indexOf(num);
            //adds the power number to the front of list but now shifted by 1 and with extra entry
            numbersToPlay.add(powIndex++, num);
            //remove old number (duplicate)
            numbersToPlay.remove(index + 1);
        }
    }

    public void showAllFinalCombos() {
        System.out.println("Here are your numbers to play!");
        //print out all of the final lotto numbers to play
        for (int[] combo : wheeledNumbers) {
            System.out.println(Arrays.toString(combo));
        }
    }
}
