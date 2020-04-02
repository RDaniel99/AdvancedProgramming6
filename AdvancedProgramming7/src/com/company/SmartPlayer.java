package com.company;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SmartPlayer extends Player {
    private Thread t;
    public Integer move;
    public List<Token> tokensOnBoard;
    public int blankOnBoard;
    public int ratioPossible;

    public SmartPlayer(String pName) {
        super(pName);
        t = null;
        move = 0;
        ratioPossible = 0;
        setType(3);
    }

    public void setTokens(List<Token> pTokens) {
        this.tokensOnBoard = pTokens;
    }

    public void setBlankOnBoard(int blankOnBoard) {
        this.blankOnBoard = blankOnBoard;
    }

    public void chooseToken() {
        synchronized (Main.lock) {
            boolean flag = false;
            if(blankOnBoard > 0) {
                move = blankOnBoard + tokensOnBoard.size() - 1;
            }
            else {
                int currScore = getScore();
                int maximumScore = currScore;
                int position = -1;
                for(int i = 0; i < tokensOnBoard.size(); i++) {
                    Token tokenToAdd = tokensOnBoard.get(i);
                    addToken(tokenToAdd);
                    int score = getScore();
                    if(score > maximumScore) {
                        maximumScore = score;
                        position = i;
                    }

                    removeToken(tokenToAdd);
                }

                if(position == -1) {
                    position = 0;
                }

                move = position;
            }

            System.out.println("Player " + getName() + " had chosen " + move);

            Main.lock.notify();
        }
    }

    public void start() {
        t = new Thread(this, "Thread-" + getName());
        t.start();
    }

    @Override
    public void run() {
        chooseToken();
    }

    public int getMove() {
        return move;
    }
}
