/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quizscores;

import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class UserIO implements UserIOInterface {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        // not sure if this is what is being asked for
        System.out.print(message);
    }

    @Override
    public double readDouble(String prompt) {
        return convertToDouble(getInput(prompt));
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        /*
            initialize test to a value directly between min and max, so it will always
            be in range no matter the min or max values.
            this means that the prompt to re-enter input will not print on the first iteration
        */
        double test = min + (max-min)/2;
        boolean valid = false;
        while (!valid) {
            test = convertToDouble(getInput(prompt));
            if (test < min || test > max) {
                System.out.println("input out of bounds");
            } else {
                valid = true;
            }
        }
        return test;
    }

    @Override
    public float readFloat(String prompt) {
        return convertToFloat(getInput(prompt));
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        /*
            initialize test to a value directly between min and max, so it will always
            be in range no matter the min or max values.
            this means that the prompt to re-enter input will not print on the first iteration
        */
        float test = min + (max-min)/2;
        boolean valid = false;
        while (!valid) {
            test = convertToFloat(getInput(prompt));
            if (test < min || test > max) {
                System.out.println("input out of bounds");
            } else {
                valid = true;
            }
        }
        return test;
    }

    @Override
    public int readInt(String prompt) {
        return convertToInt(getInput(prompt));
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        /*
            initialize test to a value directly between min and max, so it will always
            be in range no matter the min or max values.
            this means that the prompt to re-enter input will not print on the first iteration
        */
        int test = min + (max-min)/2;
        boolean valid = false;
        while (!valid) {
            test = convertToInt(getInput(prompt));
            if (test < min || test > max || min > max) {
                System.out.println("input out of bounds");
            } else {
                valid = true;
            }
        }
        return test;
    }

    @Override
    public long readLong(String prompt) {
        return convertToLong(getInput(prompt));
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        /*
            initialize test to a value directly between min and max, so it will always
            be in range no matter the min or max values.
            this means that the prompt to re-enter input will not print on the first iteration
        */
        long test = min + (max-min)/2;
        boolean valid = false;
        while (!valid) {
            test = convertToLong(getInput(prompt));
            if (test < min || test > max) {
                System.out.println("input out of bounds");
            } else {
                valid = true;
            }
        }
        return test;
    }

    @Override
    public String readString(String prompt) {
        return getInput(prompt);
    }

    private String getInput(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    private int convertToInt(String s) {
        int value = 0;
        boolean tryAgain = true;
        while (tryAgain) {
            try {
                value = Integer.parseInt(s);
                tryAgain = false;
            } catch (NumberFormatException e) {
                System.out.print("invalid input (not an int)- try again: ");
                s = sc.nextLine();
            }
        }
        return value;
    }

    private double convertToDouble(String s) {
        double value = 0;
        boolean tryAgain = true;
        while (tryAgain) {
            try {
                value = Double.parseDouble(s);
                tryAgain = false;
            } catch (NumberFormatException e) {
                System.out.print("invalid input (not a double)- try again: ");
                s = sc.nextLine();
            }
        }
        return value;
    }

    private long convertToLong(String s) {
        long value = 0;
        boolean tryAgain = true;
        while (tryAgain) {
            try {
                value = Long.parseLong(s);
                tryAgain = false;
            } catch (NumberFormatException e) {
                System.out.print("invalid input (not a long) - try again: ");
                s = sc.nextLine();
            }
        }
        return value;
    }

    private float convertToFloat(String s) {
        float value = 0;
        boolean tryAgain = true;
        while (tryAgain) {
            try {
                value = Float.parseFloat(s);
                tryAgain = false;
            } catch (NumberFormatException e) {
                System.out.print("invalid input (not a float) - try again: ");
                s = sc.nextLine();
            }
        }
        return value;
    }
}
