package com.company;

import java.util.Scanner;

public class ManualPlayer extends Player {
    private Thread t;
    public Integer move;

    public ManualPlayer(String pName) {
        super(pName);
        t = null;
        move = 0;
        setType(1);
    }

    public void chooseToken() {
        synchronized (Main.lock) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Alege un index: ");
            move = scanner.nextInt();
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
