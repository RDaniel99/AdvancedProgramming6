package com.company;

import java.util.*;

public class Board {
    private List<Token> tokens;
    private int blankTokens;
    private int length;

    public Board(int n, int m, int k) {
        length = k;
        Integer[] array = new Integer[m];
        for(int i = 1; i <= m; i++) {
            array[i - 1] = i;
        }

        tokens = new ArrayList<Token>();
        Random random = new Random();
        boolean flag;

        do {
            flag = false;
            tokens.clear();
            blankTokens = 0;

            List<Integer> intList = Arrays.asList(array);
            Collections.shuffle(intList);
            intList.toArray(array);

            blankTokens = random.nextInt(n);
            if(blankTokens + m < n) blankTokens = n - m;
            for (int i = 1; i <= n - blankTokens; i++) {
                tokens.add(new Token(array[i - 1]));
            }

            if(getScore() < k) {
                flag = true;
            }
        } while(flag);
    }

    public int getLength() {
        return length;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public int getBlankTokens() {
        return blankTokens;
    }

    public int getScore() {
        return Game.getScore(getTokens(), getBlankTokens());
    }

    public int getTotalTokens() {
        return tokens.size() + blankTokens;
    }

    public int extractToken(int index) {
        if(index < tokens.size()) {
            int value = tokens.get(index).getValue();
            tokens.remove(index);
            return value;
        }

        blankTokens--;
        return -1;
    }

    public void printAvailableTokens() {
        System.out.println("Available tokens are:");
        for(int i = 0; i < tokens.size(); i++) {
            System.out.println("Index " + i + ": " + tokens.get(i).getValue());
        }

        for(int i = 0; i < blankTokens; i++) {
            System.out.println("Index " + (i + tokens.size()) + ": B");
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();

        buffer.append("Board: \n Tokens: ");
        buffer.append(tokens.size() + blankTokens);
        buffer.append("\n ");

        for(Token token: tokens) {
            buffer.append(token.toString());
            buffer.append(" ");
        }

        for(int i = 0; i < blankTokens; i++) {
            buffer.append("B ");
        }

        return buffer.toString();
    }
}
