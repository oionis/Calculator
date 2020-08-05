package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final String[] ARAB_NUMERAL = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private static final String[] LATIN_NUMERAL = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    private static final String[] SIGN = {"+", "-", "*", "/"};

    public static void main(String[] args) {

        String string;
        String[] subString;
        Scanner p = new Scanner(System.in);
        while (true) {
            string = p.nextLine();
            subString = string.trim().split(" ");

            Value value = check(subString);
            Integer ans = operation(value.getA(), value.getB(), subString[1]);
            print(value.isLatin(), ans);


        }
    }

    private static Value check(String[] arrayStr) {

        try {
            if (arrayStr.length != 3) {
                throw new MyException("Expression is not written correctly");
            } else if (!Arrays.asList(SIGN).contains(arrayStr[1])) {
                throw new MyException("Error sign");
            } else if (Arrays.asList(ARAB_NUMERAL).containsAll(Arrays.asList(arrayStr[0], arrayStr[2]))) {
                return new Value(Integer.parseInt(arrayStr[0]), Integer.parseInt(arrayStr[2]), false);
            } else if (Arrays.asList(LATIN_NUMERAL).containsAll(Arrays.asList(arrayStr[0], arrayStr[2]))) {
                return new Value((LatinNumerals.valueOf(arrayStr[0]).ordinal() + 1), (LatinNumerals.valueOf(arrayStr[2]).ordinal() + 1), true);
            }
            throw new MyException("Incorrect value");
        } catch (MyException ex) {
            System.exit(0);
            System.out.println(ex.getMessage());
        }
        return null;
    }

    private static Integer operation(int a, int b, String sign) {
        switch (sign) {
            case "+":
                return (a + b);
            case "-":
                return (a - b);
            case "*":
                return (a * b);
            case "/":
                return (a / b);
        }
        return null;
    }

    private static void print(boolean isLatinN, int ans) {
        if (isLatinN) {


            int b, c;
            StringBuilder str = new StringBuilder();
            if (ans > 0 && ans <= 10) {
                str.append(LATIN_NUMERAL[ans - 1]);
            } else if (ans > 10 && ans < 40) {
                b = ans / 10;
                c = ans % 10;
                str.append("X".repeat(b));
                if (c != 0) {
                    str.append(LATIN_NUMERAL[c - 1]);
                }
            } else if (ans >= 40 && ans < 50) {
                ans -= 40;
                str.append("XL");
                if (ans != 0) {
                    str.append(LATIN_NUMERAL[ans - 1]);
                }
            } else if (ans >= 50 && ans < 90) {
                ans -= 50;
                str.append("L");
                b = ans / 10;
                c = ans % 10;
                str.append("X".repeat(b));
                if (c != 0) {
                    str.append(LATIN_NUMERAL[c - 1]);
                }
            } else if (ans >= 90 && ans < 100) {
                ans -= 90;
                str.append("XC");
                if (ans != 0) {
                    str.append(LATIN_NUMERAL[ans - 1]);
                }
            } else if (ans == 100) {
                str.append("C");
            } else {
                System.out.println("Number must be integer");
            }

            System.out.println(str);


        } else {
            System.out.println(ans);
        }


    }
}
