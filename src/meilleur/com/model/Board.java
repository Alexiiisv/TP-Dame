package meilleur.com.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private int numb = 0, pos = 0;
    private final int L = 10;
    private final int H = 10;
    private char[][] map = new char[L][H];

    public void createBoard(){
        for (char[] col : map) {
            //Arrays.fill(col, '.');

            for (int i = 0; i < col.length; i++) {
                if (numb%2 == 0 && pos%2 == 1 || numb%2 == 1 && pos%2 == 0) {
                    col[i] = '_';
                }
                else {
                    col[i] = ' ';
                }
                pos++;
            }
            numb++;
            pos = 0;
        }
    }

    public void placePion(ArrayList<Pion> objects) {
        for (Pion o: objects) {
            map[o.getPosY()][o.getPosX()] = o.getLetter();
        }
    }

    public void printMap() {
        int i = 0;
        System.out.println("\t 0\t1  2  3  4  5  6  7  8  9\n");
        for (char[] s : map) {
            System.out.println(i + "\t" + Arrays.toString(s));
            i++;
        }
        System.out.println();
    }

}


