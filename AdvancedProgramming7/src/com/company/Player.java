package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Player implements Runnable {
    private List<Token> tokens;
    private int blankTokens;
    private String name;
    private int type;

    public Player(String pName) {
        tokens = new ArrayList<Token>();
        name = pName;
        blankTokens = 0;
        type = 0;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getBlankTokens() {
        return blankTokens;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public int getScore() {
        return Game.getScore(getTokens(), getBlankTokens());
    }

    public void addToken(Token token) {
        if(token.isBlank()) {
            blankTokens++;
        }
        else{
            tokens.add(token);
        }
    }

    public void removeToken(Token token) {
        tokens.remove(token);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();

        buffer.append(name + " status:");

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
