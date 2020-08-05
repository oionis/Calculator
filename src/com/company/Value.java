package com.company;

public class Value {

    private int a;
    private int b;
    private boolean isLatin;

    public Value(int a, int b, boolean isLatin) {
        this.a = a;
        this.b = b;
        this.isLatin = isLatin;
    }

    public boolean isLatin() {
        return isLatin;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }



}
