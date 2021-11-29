package meilleur.com.model;

import meilleur.com.utilitaire.Function;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    private final int L = 10, H = 10;
    public char[][] playBoard = new char[H][L];
    private String input;
    private int numb = 0, pos = 0;
    private final int[] posPionsAdverse = new int[2];
    private final Function function = new Function();
    private boolean haveMoved = true;

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

    public boolean isHaveMoved() {
        return haveMoved;
    }

    public void setHaveMoved(boolean haveMoved) {
        this.haveMoved = haveMoved;
    }

    /**
     * retourne le déplacement réalisé par le joueur pour l'afficher sur le json
     * @return le déplacement réalisé par le joueur
     */
    public String ReturnMove() {
        return "\t\t\"" + input + "\",\n";
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
        haveMoved = true;
        String reg = "[0-9][0-9] [1-9]([T^][$L]|[T^][$R]|[B^][$R]|[B^][$L])";
        if (player1.getName().equals("AI")) {
            input = player1.returnOneMove();
            System.out.println("\nLe bot réalise son déplacement\n");
            function.waitTime(2000, 'P');
        } else {
            printplayBoard();
            System.out.println(player1.getName() + " c'est a vous de jouer ! (" + player1.getLetter()[0]
                    + " et " + player1.getLetter()[1] + ")\nQue souhaitez vous faire ?");
            input = function.askTerminalString();
        }

        if (input.matches(reg)) {

            if (player1.movePion(input)) {

                movePionToPosition(Character.getNumericValue(input.charAt(1)),
                        Character.getNumericValue(input.charAt(0)),
                        input.substring(3), player1, player2);
                return 1;
            } else {
                haveMoved = false;
                System.out.println("\nLe déplacement n'a pas pu être réalisé\n");
                return 3;
            }
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
     * @param lastXPos = position du pion avant déplacement sur l'axe X
     * @param lastYPos = position du pion avant déplacement sur l'axe Y
     * @param pos = information donnée par le joueur pour le déplacement
     * @param player1 = joueur qui joue
     * @param player2 = joueur adverse
     */
    private void movePionToPosition(int lastXPos, int lastYPos, String pos, Player player1, Player player2) {
        pos = function.moveUpdate(pos, player1, lastXPos, lastYPos);
        String[] posSplited = pos.split("");
        int numberOfTileMoved = Integer.parseInt(posSplited[0]);
        int newYPos = posSplited[1].equals("T") ? lastYPos - numberOfTileMoved : lastYPos + numberOfTileMoved;
        int newXPos = posSplited[2].equals("L") ? lastXPos - numberOfTileMoved : lastXPos + numberOfTileMoved;

        if (newXPos <= 9 && newXPos >= 0 && newYPos <= 9 && newYPos >= 0) {
            if (checkIfMovePossible(lastYPos, lastXPos, "check1", null, posSplited, playBoard)) {
                if (dameMoveAllowed(lastXPos, lastYPos, posSplited, player1, player2)) {
                    playBoard[posPionsAdverse[0]][posPionsAdverse[1]] = '_';
                    player2.updatePionLive(posPionsAdverse[0], posPionsAdverse[1]);
                }
                playBoard[lastYPos][lastXPos] = '_';
                player1.updatePion(lastYPos, lastXPos, newXPos, newYPos);
                player1.updatePionToDame(newXPos, newYPos);
                playBoard[newYPos][newXPos] = player1.getPionLetter(newYPos, newXPos);

            } else if (checkIfMovePossible(lastYPos, lastXPos, "check2", player1, posSplited, playBoard)) {
                if (dameMoveAllowed(lastXPos, lastYPos, posSplited, player1, player2)) {
                    playBoard[posPionsAdverse[0]][posPionsAdverse[1]] = '_';
                    player2.updatePionLive(posPionsAdverse[0], posPionsAdverse[1]);
                }else {
                    playBoard[newYPos][newXPos] = '_';
                    player2.updatePionLive(newYPos, newXPos);
                }
                newYPos += posSplited[1].equals("T") ? -1 : 1;
                newXPos += posSplited[2].equals("L") ? -1 : 1;
                playBoard[lastYPos][lastXPos] = '_';
                player1.updatePion(lastYPos, lastXPos, newXPos, newYPos);
                player1.updatePionToDame(newXPos, newYPos);
                playBoard[newYPos][newXPos] = player1.getPionLetter(newYPos, newXPos);

            } else {
                System.out.println("Ce pion n'as pas pus etre déplacer. Vous devez rechoisir une action");
                move(player1, player2);
            }
        } else {
            System.out.println("Ce pion n'as pas pus etre déplacer. Vous devez rechoisir une action");
            move(player1, player2);
        }
    }

    public boolean checkIfMovePossible(int lastYPos, int lastXPos, String check, Player player1, String[] posSplited, char[][] playBoardNotBugged) {
        int numberOfTileMoved = Integer.parseInt(posSplited[0]);
        int newYPos = posSplited[1].equals("T") ? lastYPos - numberOfTileMoved : lastYPos + numberOfTileMoved;
        int newXPos = posSplited[2].equals("L") ? lastXPos - numberOfTileMoved : lastXPos + numberOfTileMoved;
        if (newXPos < 0 || newXPos > 9 || newYPos < 0 || newYPos > 9) return false;
        playBoardNotBugged[0][0] = ' ';
        if (playBoardNotBugged[newYPos][newXPos] == '_' && check.equals("check1")) return true;
        else if (playBoardNotBugged[newYPos][newXPos] != '_' && check.equals("check1")) return false;
        
        if (posSplited[1].equals("T") && posSplited[2].equals("R") && lastYPos != 0 && lastXPos != 9 && newXPos < 9 && newYPos > 0) {

            return function.checkIfHisPions(playBoardNotBugged[newYPos][newXPos], player1)
                    && playBoardNotBugged[newYPos + (function.inverseOrNotInt(posSplited[1]) * numberOfTileMoved)]
                    [newXPos + (function.inverseOrNotInt(posSplited[2]) * numberOfTileMoved)] == '_';

        }else if (posSplited[1].equals("T") && posSplited[2].equals("L") && lastYPos != 0 && lastXPos != 0 && newXPos > 0 && newYPos > 0) {

            return function.checkIfHisPions(playBoardNotBugged[newYPos][newXPos], player1)
                    && playBoardNotBugged[newYPos + (function.inverseOrNotInt(posSplited[1]) * numberOfTileMoved)]
                    [newXPos + (function.inverseOrNotInt(posSplited[2]) * numberOfTileMoved)] == '_';

        } else if (posSplited[1].equals("B") && posSplited[2].equals("R") && lastYPos != 9 && lastXPos != 9 && newYPos < 9 && newXPos < 9) {

            return function.checkIfHisPions(playBoardNotBugged[newYPos][newXPos], player1)
                    && playBoardNotBugged[newYPos + (function.inverseOrNotInt(posSplited[1]) * numberOfTileMoved)]
                    [newXPos + (function.inverseOrNotInt(posSplited[2]) * numberOfTileMoved)] == '_';

        } else if (posSplited[1].equals("B") && posSplited[2].equals("L") && lastYPos != 9 && lastXPos != 0 && newYPos < 9 && newXPos > 0) {

            return function.checkIfHisPions(playBoardNotBugged[newYPos][newXPos], player1)
                    && playBoardNotBugged[newYPos + (function.inverseOrNotInt(posSplited[1]) * numberOfTileMoved)]
                    [newXPos + (function.inverseOrNotInt(posSplited[2]) * numberOfTileMoved)] == '_';
        }
        return false;
    }

    private boolean dameMoveAllowed(int lastXPos, int lastYPos, String[] posSplited, Player p1, Player p2) {
        int countPionsAdverse = 0;
        for (int i = 1; i <= Integer.parseInt(posSplited[0]); i++) {
            if (countPionsAdverse < 2) {
                for (char c1: p2.getLetter()) {
                    if (playBoard[lastYPos + function.inverseOrNotInt(posSplited[1])*i]
                                 [lastXPos + function.inverseOrNotInt(posSplited[2])*i] == c1) {
                        countPionsAdverse++;
                        posPionsAdverse[0] = lastYPos + function.inverseOrNotInt(posSplited[1])*i;
                        posPionsAdverse[1] = lastXPos + function.inverseOrNotInt(posSplited[2])*i;
                        System.out.println(countPionsAdverse);
                    }
                }
            } else return false;
        }
        return true;
    }



}



