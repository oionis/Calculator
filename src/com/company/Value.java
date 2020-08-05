package com.company;

public class Value {

    private int firstValue;
    private int secondValue;
    private boolean isLatin;

    public int getFirstValue() {
        return firstValue;
    }

    public int getSecondValue() {
        return secondValue;
    }

    public boolean isLatin() {
        return isLatin;
    }

    public Value(int firstValue, int secondValue, boolean isLatin) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.isLatin = isLatin;
    }

    public Value() {
    }
}
