package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private List<Player> players;
    private int whoMoves;
    private Board board;

    public Game(Board pBoard) {
        players = new ArrayList<Player>();
        whoMoves = 0;
        board = pBoard;
    }

    public void nextPlayer() {
        whoMoves = (whoMoves + 1) % players.size();
    }

    public Player getPlayer(int index) {
        if(index < 0 || index >= players.size()) {
            return null;
        }

        return players.get(index);
    }

    public int getCurrPlayer() {
        return whoMoves;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public boolean canMove(int index) {
        if(index < 0 || index >= board.getTotalTokens()) {
            return false;
        }

        return true;
    }

    static public int getScore(List<Token> tokens, int blankTokens) {
        Collections.sort(tokens);

        int maxLength = 0;
        for(int i = 0; i < tokens.size(); i++) {
            for(int j = i + 1; j < tokens.size(); j++) {
                int ratio = tokens.get(j).getValue() - tokens.get(i).getValue();
                int start = tokens.get(i).getValue();

                int nextToFind = start + ratio * 2;
                int length = 2;
                int auxCopy = blankTokens;
                while(auxCopy > 0 || tokens.contains(new Token(nextToFind))) {
                    if(!tokens.contains(new Token(nextToFind))) {
                        auxCopy--;
                    }

                    length++;
                    nextToFind += ratio;
                }

                if(length > maxLength) {
                    maxLength = length;
                }
            }
        }

        return maxLength;
    }
}
