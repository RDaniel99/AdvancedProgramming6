package com.company;

public class Board {
    private char[][] table;

    public Board() {
        table = new char[15][15];

        reset();
    }

    public boolean makeMove(int row, int column, char c) {
        if(column < 0 || column > 14 || row < 0 || row > 14) {
            return false;
        }

        if(table[row][column] != '.') {
            return false;
        }

        table[row][column] = c;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        
        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 15; j++) {
                buffer.append(table[i][j]);
                
                if(j < 14) {
                    buffer.append(' ');
                }
                else {
                    buffer.append(" ttt");
                }
            }
        }

        return buffer.toString();
    }

    public void reset() {
        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 15; j++) {
                table[i][j] = '.';
            }
        }
    }
}
