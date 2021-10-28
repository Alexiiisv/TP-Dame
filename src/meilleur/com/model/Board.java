package meilleur.com.model;

import java.util.Arrays;

public class Board {
    private int numb = 0;
    private int L = 10;
    private int H = 10;
    private char[][] map = new char[L][H];
    public void CreateBoard(){
        for (char[] col : map) {
            Arrays.fill(col, '.');
            }
        }

    public void printMap() {
        for (char[] s : map) System.out.println(Arrays.toString(s));
        System.out.println();
    }

}


