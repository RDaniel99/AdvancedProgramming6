package com.company;

public class Token implements Comparable<Token>{
    private int value;

    public Token(int val) {
        value = val;
    }

    public int getValue() {
        return value;
    }

    public boolean isBlank() {
        return value == -1;
    }

    @Override
    public String toString() {
        if(isBlank()) {
            return "B";
        }

        return String.valueOf(value);
    }

    @Override
    public int compareTo(Token o) {
        return getValue() - o.getValue();
    }
}
