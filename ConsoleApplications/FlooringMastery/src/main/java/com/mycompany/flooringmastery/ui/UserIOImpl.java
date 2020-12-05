/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.ui;

import com.mycompany.flooringmastery.ProductType;
import com.mycompany.flooringmastery.TaxState;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author nhyat
 */
public class UserIOImpl implements UserIO {

    private final Scanner sc = new Scanner(System.in);

    private final String DATE_FORMAT = "MMddyyyy";
    private final String L_MARGIN = "\t ";

    @Override
    public void print(String message) {
        System.out.print(L_MARGIN+message);
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
        double test; //min + (max - min) / 2;
        boolean valid = false;
        do {
            test = convertToDouble(getInput(prompt));
            if (test < min || test > max) {
                println(L_MARGIN+"input out of bounds");
            } else {
                valid = true;
            }
        } while (!valid);
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
        float test; //min + (max - min) / 2;
        boolean valid = false;
        do {
            test = convertToFloat(getInput(prompt));
            if (test < min || test > max) {
                println(L_MARGIN+"input out of bounds");
            } else {
                valid = true;
            }
        } while (!valid);
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
        int test; //min + (max - min) / 2;
        boolean valid = false;
        do {
            test = convertToInt(getInput(prompt));
            if (test < min || test > max || min > max) {
                println("input out of bounds");
            } else {
                valid = true;
            }
        } while (!valid);
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
        long test; // min + (max - min) / 2;
        boolean valid = false;
        do {
            test = convertToLong(getInput(prompt));
            if (test < min || test > max) {
                println("input out of bounds");
            } else {
                valid = true;
            }
        } while (!valid);
        return test;
    }

    @Override
    public String readString(String prompt) {
        return getInput(prompt);
    }

    @Override
    public LocalDate readLocalDate(String prompt, String pattern) {
        return convertLocalDate(getInput(prompt), pattern);
    }

    @Override
    public LocalDate readLocalDate(String prompt, String pattern, LocalDate min, LocalDate max) {
        //long halfDays = Period.between(min, max).getDays()/2;
        //LocalDate test = min.plusDays(halfDays);
        LocalDate test = null;
        boolean valid = false;
        while (!valid) {
            test = convertLocalDate(getInput(prompt), pattern);
            if (min.until(test).isNegative() || test.until(max).isNegative()) {
                println("Input out of bounds");
            } else {
                valid = true;
            }
        }
        return test;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        return convertBigDecimal(getInput(prompt));
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        BigDecimal test = null; // min.add((max.subtract(min)).divide(new BigDecimal("2")));
        boolean valid = false;
        while (!valid) {
            test = convertBigDecimal(getInput(prompt));
            if (test.compareTo(min) < 0 || test.compareTo(max) > 0) {
                println("input out of bounds");
            } else {
                valid = true;
            }
        }
        return test;
    }

    private String getInput(String prompt) {
        print(prompt);
        return sc.nextLine();
    }

    /*  due to the single responsibility principle, the following convert methdods may need to 
        be absorbed into the original read methods. Could just catch error in read methods so 
        that only one method deals with user input. -- future Nate's problem --
     */
    private int convertToInt(String s) {
        int value = 0;
        boolean tryAgain = true;
        while (tryAgain) {
            try {
                value = Integer.parseInt(s);
                tryAgain = false;
            } catch (NumberFormatException e) {
                print("invalid input (not an int)- try again: ");
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
                print("invalid input (not a double)- try again: ");
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
                print("invalid input (not a long) - try again: ");
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
                print("invalid input (not a float) - try again: ");
                s = sc.nextLine();
            }
        }
        return value;
    }

    private BigDecimal convertBigDecimal(String s) {
        BigDecimal value = null;
        boolean tryAgain = true;
        while (tryAgain) {
            try {
                value = new BigDecimal(s);
                tryAgain = false;
            } catch (Exception e) {
                println("Invalid Input (not BigDecimal) - try again: ");
                s = sc.nextLine();
            }
        }
        return value;
    }

    private LocalDate convertLocalDate(String s, String p) {
        LocalDate value = null;
        boolean tryAgain = true;
        while (tryAgain) {
            try {
                value = LocalDate.parse(s, DateTimeFormatter.ofPattern(p));
                tryAgain = false;
            } catch (Exception e) {
                println("Invalid Input (not " + p + ") - try again: ");
                s = sc.nextLine();
            }
        }
        return value;
    }

    @Override
    public void println(String message) {
        print(message + "\n");
    }

    @Override
    public String readStateString(String prompt) {

        String state = "NO INPUT";
        boolean tryAgain = true;
        while (tryAgain) {
            try {

                // print out the valid states for the users reference
                print("VALID STATES: ");
                Object[] includedStates = TaxState.values();
                for (Object o : includedStates) {
                    print(o.toString() + ", ");
                }
                println("");
                state = getInput(prompt);
                state = state.toUpperCase();
                TaxState.valueOf(state); // will throw an error if not in the TaxState enum

                return state;

            } catch (IllegalArgumentException e) {
                println(state + " is not a state we provide service to - please try again.");
            }
        }
        return null;
    }

    @Override
    public String readProdTypeString(String prompt) {
        
        String prodType = "NO INPUT";
        boolean tryAgain = true;
        while (tryAgain) {
            try {

                // print out the valid states for the users reference
                print("VALID PRODUCT TYPES: ");
                Object[] includedProducts = ProductType.values();
                for (Object o : includedProducts) {
                    print(o.toString() + ", ");
                }
                println("");
                prodType = getInput(prompt).toUpperCase();
                ProductType.valueOf(prodType); // will throw an error if not in the TaxState enum

                return prodType;

            } catch (Exception e) {
                print( prodType + " is not a product we provide - please try again:");
            }
        }
        return null;
    }
}
