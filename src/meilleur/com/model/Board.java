package meilleur.com.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Board {

    private String Moves;
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

    public void move(Player player) {
        boolean falseInput = true;
        Scanner scanner = new Scanner(System.in);
        Moves = scanner.nextLine();
        while (falseInput){
            System.out.println("Que souhaitez vous faire ?");
            String input = scanner.nextLine();
            if (input.matches("[0-9][0-9] [0-9]([T^][$L]|[T^][$R]|[B^][$R]|[B^][$L])")){
                falseInput = false;
                if (player.movePion(input)) {
                    movePionToPosition(Character.getNumericValue(input.charAt(1)), Character.getNumericValue(input.charAt(0)), input.substring(2), player.getLetter());
                }
            }
            else if (input.equals("p")) player.printStuff();
        }
    }
    private void movePionToPosition(int lastX, int lastY, String pos, char c) {
        map[lastY][lastX] = '_';
        String[] split = pos.split("");
        lastY = split[2].equals("T") ? lastY-Integer.parseInt(split[1]) : lastY+Integer.parseInt(split[1]);
        lastX = split[3].equals("L") ? lastX-Integer.parseInt(split[1]) : lastX+Integer.parseInt(split[1]);
        map[lastY][lastX] = c;
    }
    public String ReturnMove(){
        return Moves;
    }

}


