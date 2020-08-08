package com.company;

public class Value {

    private int firstValue;
    private int secondValue;
    private boolean isRoman;

    public int getFirstValue() {
        return firstValue;
    }

    public int getSecondValue() {
        return secondValue;
    }

    public boolean isRoman() {
        return isRoman;
    }

    public Value(int firstValue, int secondValue, boolean isRoman) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.isRoman = isRoman;
    }

    public Value() {
    }
}
