package com.company;

import java.util.Arrays;
import java.util.List;
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
            print(value.isRoman(), answer);


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
            } else {
                return new Value(romanToArabic(arrayStr[0]), romanToArabic(arrayStr[2]), true);
            }

        } catch (MyException | IllegalArgumentException ex) {
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

    private static void print(boolean isRoman, int answer) {
        if (isRoman) {
            System.out.println(arabicToRoman(answer));
        } else {
            System.out.println(answer);
        }
    }

    private static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    private static int romanToArabic(String romanStr) {

        String[] arrRomanString = romanStr.split("");
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
        int result = 0;
        int i = 0;
        int j = 0;


        while (i < arrRomanString.length && j < romanNumerals.size()) {

            RomanNumeral currentSymbol = romanNumerals.get(j);
            if (currentSymbol.toString().length() == 1) {
                if (currentSymbol.toString().equals(arrRomanString[i])) {
                    result += currentSymbol.getValue();
                    i++;
                } else {
                    j++;
                }

            } else if (currentSymbol.toString().length() == 2 && ((arrRomanString.length - i) > 1)) {
                if (currentSymbol.toString().equals(arrRomanString[i] + arrRomanString[i + 1])) {
                    result += currentSymbol.getValue();
                    i += 2;
                } else {
                    j++;
                }
            } else
                j++;
        }

        if (arrRomanString.length != i){
            throw new IllegalArgumentException(romanStr + " is incorrect roman numeral");
        }

        return result;

    }
}
