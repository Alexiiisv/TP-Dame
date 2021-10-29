package meilleur.com.model;

import java.util.Arrays;

public class Board {
    private int numb = 0;
    private final int L = 10;
    private final int H = 10;
    private char[][] map = new char[L][H];

    public void createBoard(){
        for (char[] col : map) {
            Arrays.fill(col, '.');
            }
        }

    public void printMap() {
        for (char[] s : map) System.out.println(Arrays.toString(s));
        System.out.println();
    }

}


