package com.company;

import java.util.Random;
import java.util.Scanner;

public class RandomPlayer extends Player {
    private Thread t;
    public Integer move;
    public int tokens;

    public RandomPlayer(String pName) {
        super(pName);
        t = null;
        move = 0;
        tokens = 0;
        setType(2);
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public void chooseToken() {
        synchronized (Main.lock) {
            Random random = new Random();
            move = random.nextInt(tokens);

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
