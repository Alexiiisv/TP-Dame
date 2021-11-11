package meilleur.com.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Board {

    private final int L = 10, H = 10;
    private final char[][] playBoard = new char[L][H];
    private String input;
    private int numb = 0, pos = 0;

    /** Créer la zone de jeu */
    public void createBoard() {
        for (char[] col : playBoard) {

            for (int i = 0; i < col.length; i++) {
                if (numb % 2 == 0 && pos % 2 == 1 || numb % 2 == 1 && pos % 2 == 0) {
                    col[i] = '_';
                } else {
                    col[i] = ' ';
                }
                pos++;
            }
            numb++;
            pos = 0;
        }
    }

    /** place les pions de chaque joueur */
    public void placePion(ArrayList<Pions> objects) {
        for (Pions o : objects) {
            playBoard[o.getPosY()][o.getPosX()] = o.getLetter();
        }
    }

    /** Affiche la zone de jeu */
    public void printplayBoard() {
        int i = 0;
        System.out.println("  | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |\n  |   |   |   |   |   |   |   |   |   |   |");

        for (char[] s : playBoard) {
            System.out.print(i + " | ");
            for (char c: s) {
                System.out.print(c + " | ");
            }
            System.out.println();
            i++;
        }
        System.out.println();
    }

    /**
     * Demande l'action que le joueur veut faire et essaie de la realiser
     * @param player1 = joueur qui joue
     * @param player2 = joueur adverse
     * @return Integer qui équivaut a une action;<p>
     *          1 = déplacement réalisé et fonctionnel<p>
     *          2 = demande d'information a propos des pions d'un joueur<p>
     *          0 = fin de partie<p>
     */
    public int move(Player player1, Player player2) {
        String reg = "[0-9][0-9] [1-9]([T^][$L]|[T^][$R]|[B^][$R]|[B^][$L])";
        Scanner scanner = new Scanner(System.in);
        printplayBoard();
        System.out.println(player1.getName() + " c'est a vous de jouer ! (" + player1.getLetter() + ")\nQue souhaitez vous faire ?");
        input = scanner.nextLine();

        if (input.matches(reg)) {

            if (player1.movePion(input)) {
                movePionToPosition(Character.getNumericValue(input.charAt(1)), Character.getNumericValue(input.charAt(0)), input.substring(3), player1, player2);
                return 1;
            } else return 3;
        } else if (input.equals("p") || input.equals("P")) {
            player1.printPion();
            return 3;
        } else if (input.equals("q") || input.equals("Q")) {
            return 0;
        } else return 3;
        }


    /**
     * Faire les deplacements des pions lors de replay
     * @param player1 = joueur qui joue
     * @param player2 = joueur adverse
     * @param str = move actuelle
     */
    public void move(Player player1, Player player2, String str) {
        System.out.println("\n\n\n");
        movePionToPosition(Character.getNumericValue(str.charAt(1)), Character.getNumericValue(str.charAt(0)), str.substring(3), player1, player2);
        printplayBoard();
    }

    /**
     * Déplace le pion a la case demandée, si c'est possible le code se réalise et on passe au joueur suivant sinon on redemande au joueur de jouer
     * @param lastX = position du pion avant déplacement sur l'axe X
     * @param lastY = position du pion avant déplacement sur l'axe Y
     * @param pos = information donnée par le joueur pour le déplacement
     * @param player1 = joueur qui joue
     * @param player2 = joueur adverse
     */
    private void movePionToPosition(int lastX, int lastY, String pos, Player player1, Player player2) {
        String[] posSplited = pos.split("");
        int numberOfTileMoved = Integer.parseInt(posSplited[0]);
        int newX = posSplited[2].equals("L") ? lastX - numberOfTileMoved : lastX + numberOfTileMoved;
        int newY = posSplited[1].equals("T") ? lastY - numberOfTileMoved : lastY + numberOfTileMoved;

        if (newX <= 9 && newX >= 0 && newY <= 9 && newY >= 0) {

            if (playBoard[newY][newX] == '_') {

                playBoard[lastY][lastX] = '_';
                player1.updatePion(lastY, lastX, newX, newY);
                player1.updatePionToDame(newX, newY);
                playBoard[newY][newX] = player1.getPionLetter(newY, newX);

            } else if (playBoard[newY][newX] != player1.getLetter() && playBoard[newY + (newY - lastY)][newX + (newX - lastX)] == '_') {

                playBoard[newY][newX] = '_';
                player2.updatePionLive(newY, newX);
                newX += posSplited[2].equals("L") ? -1 : 1;
                newY += posSplited[1].equals("T") ? -1 : 1;
                //System.out.println(newY + " oui " + newX);
                playBoard[lastY][lastX] = '_';
                player1.updatePion(lastY, lastX, newX, newY);
                player1.updatePionToDame(newX, newY);
                playBoard[newY][newX] = player1.getPionLetter(newY, newX);

            } else {
                System.out.println("Ce pion n'as pas pus etre déplacer. Vous devez rechoisir une action");
                move(player1, player2);
            }
        } else {
            System.out.println("Ce pion n'as pas pus etre déplacer. Vous devez rechoisir une action");
            move(player1, player2);
        }
    }

    /*
        private int isDame(Pion pion, int i) {
            if (pion.isDame()) return i;
            return 1;
        }
     */

    /**
     * retourne le déplacement réalisé par le joueur pour l'afficher sur le json
     * @return le déplacement réalisé par le joueur
     */
    public String ReturnMove() {
        return "\t\t\"" + input + "\",\n";
    }

}



