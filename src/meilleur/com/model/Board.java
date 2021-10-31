package meilleur.com.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Board {

    private String input;
    private int numb = 0, pos = 0;
    private final int L = 10;
    private final int H = 10;
    private char[][] map = new char[L][H];

    public void createBoard(){
        for (char[] col : map) {

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

    public void move(Player player1, Player player2) {
        boolean falseInput = true;
        Scanner scanner = new Scanner(System.in);
        while (falseInput){
            System.out.println("Que souhaitez vous faire ?");
            input = scanner.nextLine();
            if (input.matches("[0-9][0-9] [0-9]([T^][$L]|[T^][$R]|[B^][$R]|[B^][$L])")){
                falseInput = false;
                if (player1.movePion(input)) {
                    movePionToPosition(Character.getNumericValue(input.charAt(1)), Character.getNumericValue(input.charAt(0)), input.substring(3), player1, player2);
                }
            }
            else if (input.equals("p") || input.equals("P")) player1.printStuff();
            else if (input.equals("q") || input.equals("Q")) System.exit(1);
        }
    }
    private void movePionToPosition(int lastX, int lastY, String pos, Player player1, Player player2) {
        String[] split = pos.split("");
        int newX = split[2].equals("L") ? lastX-Integer.parseInt(split[0]) : lastX+Integer.parseInt(split[0]);
        int newY = split[1].equals("T") ? lastY-Integer.parseInt(split[0]) : lastY+Integer.parseInt(split[0]);
        System.out.println(map[newY][newX]);
        if (map[newY][newX] == '_') {
            map[lastY][lastX] = '_';
            player1.updatePion(lastY, lastX, newX, newY);
            map[newY][newX] = player1.getLetter();
        }
        else if (map[newY][newX] != '_') {
            map[newY][newX] = '_';
            player2.updatePionLive(newY, newX);
            newX = split[2].equals("L") ? lastX-(Integer.parseInt(split[0])*2) : lastX+(Integer.parseInt(split[0])*2);
            newY = split[1].equals("T") ? lastY-(Integer.parseInt(split[0])*2) : lastY+(Integer.parseInt(split[0])*2);
            System.out.println(newY + "" + newX);
            map[lastY][lastX] = '_';
            player1.updatePion(lastY, lastX, newX, newY);
            map[newY][newX] = player1.getLetter();
        }
    }

    public String ReturnMove(){
        return input;
    }

}


