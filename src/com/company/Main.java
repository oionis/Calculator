package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String string;
        String[] subString;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            string = scanner.nextLine();
            subString = string.trim().split(" ");

            Value value = check(subString);
            if (value == null) {
                System.exit(0);
            }
            Integer answer = operation(value.getFirstValue(), value.getSecondValue(), subString[1]);
            print(value.isLatin(), answer);


        }
    }

    private static Value check(String[] arrayStr) {

        try {
            if (arrayStr.length != 3) {
                throw new MyException("Expression is not written correctly");
            } else if (!Arrays.asList(Constants.SIGN).contains(arrayStr[1])) {
                throw new MyException("Error sign");
            } else if (Arrays.asList(Constants.ARAB_NUMERAL).containsAll(Arrays.asList(arrayStr[0], arrayStr[2]))) {
                return new Value(Integer.parseInt(arrayStr[0]), Integer.parseInt(arrayStr[2]), false);
            } else if (Arrays.asList(Constants.LATIN_NUMERAL).containsAll(Arrays.asList(arrayStr[0], arrayStr[2]))) {
                return new Value((LatinNumerals.valueOf(arrayStr[0]).ordinal() + 1), (LatinNumerals.valueOf(arrayStr[2]).ordinal() + 1), true);
            }
            throw new MyException("Incorrect value");
        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    private static Integer operation(int firstVariable, int secondVariable, String sign) {
        switch (sign) {
            case "+":
                return (firstVariable + secondVariable);
            case "-":
                return (firstVariable - secondVariable);
            case "*":
                return (firstVariable * secondVariable);
            case "/":
                return (firstVariable / secondVariable);
        }
        return null;
    }

    private static void print(boolean isLatinN, int answer) {
        if (isLatinN) {
            System.out.println(arabNumToLatinNum(answer));
        } else {
            System.out.println(answer);
        }


    }

    private static StringBuilder arabNumToLatinNum(int arabNum) {

        int decimalNum, tmpNum;
        StringBuilder str = new StringBuilder();

        if (arabNum > 0 && arabNum <= 10) {
            str.append(Constants.LATIN_NUMERAL[arabNum - 1]);
        } else if (arabNum > 10 && arabNum < 40) {
            decimalNum = arabNum / 10;
            tmpNum = arabNum % 10;
            str.append(Constants.X.repeat(decimalNum));
            if (tmpNum != 0) {
                str.append(Constants.LATIN_NUMERAL[tmpNum - 1]);
            }
        } else if (arabNum >= 40 && arabNum < 50) {
            arabNum -= 40;
            str.append(Constants.X).append(Constants.L);
            if (arabNum != 0) {
                str.append(Constants.LATIN_NUMERAL[arabNum - 1]);
            }
        } else if (arabNum >= 50 && arabNum < 90) {
            arabNum -= 50;
            str.append(Constants.L);
            decimalNum = arabNum / 10;
            tmpNum = arabNum % 10;
            str.append(Constants.X.repeat(decimalNum));
            if (tmpNum != 0) {
                str.append(Constants.LATIN_NUMERAL[tmpNum - 1]);
            }
        } else if (arabNum >= 90 && arabNum < 100) {
            arabNum -= 90;
            str.append(Constants.X).append(Constants.C);
            if (arabNum != 0) {
                str.append(Constants.LATIN_NUMERAL[arabNum - 1]);
            }
        } else if (arabNum == 100) {
            str.append(Constants.C);
        } else {
            System.out.println("Number must be integer");
        }

        return str;
    }
}
