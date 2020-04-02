package com.company;

import java.util.Scanner;

public class ManualPlayer extends Player {
    private Thread t;

    public ManualPlayer(String pName) {
        super(pName);
        t = null;
    }

    public int chooseToken() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("S-a apelat fraieru");
        return scanner.nextInt();
    }

    public void start() {
        if(t == null) {
            t = new Thread(this, "Thread-" + getName());
            t.start();
        }
    }

    @Override
    public void run() {
        chooseToken();
    }
}
