package meilleur.com.model;

import java.util.ArrayList;
import java.util.Random;


public class Player {
    private final ArrayList<Pions> allPion = new ArrayList<>();
    private final ArrayList<String> possibleMoves = new ArrayList<>();
    private final String[] allMoves = new String[]{"1TR", "1TL", "1BR", "1BL"};
    private final int id;
    private int lastPions;
    private final char[] letter;
    private String name;
    private boolean winner;
    private final Board board = new Board();

    public Player(int id) {
        this.id = id;
        this.letter = id == 1 ? new char[]{'P', 'D'} : new char[]{'p', 'd'};
        this.winner = false;
    }

    public int getLastPions() {
        return lastPions;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return le nom du joueur
     */
    public String getName() {
        return name;
    }

    /**
     * retourne la liste de tous les pions
     */
    public ArrayList<Pions> getAllPion() {
        return allPion;
    }

    /**
     * @return lettre du joueur
     */
    public char[] getLetter() {
        return letter;
    }

    /**
     * Initialise la position des pions
     */
    public void initPion() {
        int y = id == 0 ? 0 : 6, x = 0;

        for (int i = 0; i < 21; i++) {


            if (y % 2 == 0 && x < 2) x = 1;
            if (y % 2 == 1 && x < 2) x = 0;
            if (y % 2 == 1 && x < 10) x += 2;
            if (y % 2 == 0 && x < 11) x += 2;

            this.allPion.add(new Pion(this.letter[0], x - 2, y));

            if (i % 5 == 0 && x == 11 || x == 10) y++;
            if (i % 5 == 0 && x > 5) x = 0;
        }


        //if (this.id == 1) this.allPion.add(new Pion(this.letter[0], 2, 1));
        //if (this.id == 0) this.allPion.add(new Pion(this.letter[0], 3, 4));

        this.lastPions = this.allPion.size();
    }

    /**
     * Affiche tous les Pions du joueur
     */
    public void printPion() {
        System.out.println("Voici les pions du joueur " + this.id + "\n\nID  pos");
        int i = 0;
        for (Pions p : this.allPion) {
            if (p.isAlive()) {
                if (i < 10) System.out.println(i + "   " + p.toStringBot());
                else System.out.println(i + "  " + p.toStringBot());
                i++;
            }
        }
        System.out.println();
    }

    /**
     * verifie si un pion existe a une position donnée
     *
     * @param move = placement du pion que le joueur veut bouger
     * @return true si un pion existe a la position donnée<p>false si n'existe pas
     */
    public boolean movePion(String move) {
        String[] resultMove = move.split("");
        int y = Integer.parseInt(resultMove[0]);
        int x = Integer.parseInt(resultMove[1]);
        for (Pions pions : this.allPion) {
            if (pions.getPosY() == y && pions.getPosX() == x) {
                return true;
            }
        }
        System.out.println("aucun pion t'appartenant se trouve a la position que tu as choisis");
        return false;
    }

    /**
     * update la position du pion a la position lastX, lastY
     *
     * @param lastY {@code int} = position avant déplacement sur l'axe Y
     * @param lastX {@code int} = position avant déplacement sur l'axe X
     * @param newX  {@code int} = position apres déplacement sur l'axe X
     * @param newY  {@code int} = position apres déplacement sur l'axe Y
     */
    public void updatePion(int lastY, int lastX, int newX, int newY) {
        for (Pions pion : this.allPion) {
            if (pion.getPosY() == lastY && pion.getPosX() == lastX) {
                pion.setPosX(newX);
                pion.setPosY(newY);
                break;
            }
        }
    }

    /**
     * update la variable {@code boolean}
     *
     * @param y {@code int}
     * @param x {@code int}
     */
    public void updatePionLive(int y, int x) {
        for (Pions pion : this.allPion) {
            if (pion.getPosY() == y && pion.getPosX() == x) {
                pion.setAlive(false);
                this.lastPions--;
                break;
            }
        }
    }

    public char getPionLetter(int y, int x) {
        for (Pions pion : this.allPion) {
            if (pion.getPosY() == y && pion.getPosX() == x) {
                return pion.getLetter();
            }
        }
        return ' ';
    }

    /**
     * recup le pion a la position x - y
     *
     * @param y {@code int}
     * @param x {@code int}
     * @return retourne un Pion si il existe sinon null
     */
    public Pions getPion(int y, int x) {
        for (Pions pion : this.allPion) {
            if (pion.getPosX() == x && pion.getPosY() == y) return pion;
        }
        return null;
    }

    public void updatePionToDame(int x, int y) {
        Pions pion;
        if (this.id == 1 && y == 0 && x <= 9 && x >= 0) {
            pion = getPion(y, x);
            this.allPion.add(new Dame(this.letter[1], pion.getPosX(), pion.getPosY()));
            this.allPion.remove(pion);

        } else if (this.id == 0 && y == 9 && x <= 9 && x >= 0) {
            pion = getPion(y, x);
            this.allPion.add(new Dame(this.letter[1], pion.getPosX(), pion.getPosY()));
            this.allPion.remove(pion);
        }
    }

    //to fix
    public ArrayList<String> checkPossibleMoves(char[][] playboard) {
        possibleMoves.clear();
        if (!name.equals("AI")) {
            System.out.println("\nVoici les déplacements possibles de " + this.getName() + "\n");
        }
        for (Pions p : this.allPion) {
            for (String str : this.allMoves) {
                String[] strsplited = str.split("");

                if (p.isAlive() &&
                        (board.checkIfMovePossible(p.getPosY(), p.getPosX(), "check1", null, strsplited, playboard) ||
                                board.checkIfMovePossible(p.getPosY(), p.getPosX(), "check2", this, strsplited, playboard))) {
                    possibleMoves.add(p.toStringBot() + " " + str);
                }
            }

        }
        returnOneMove();
        return possibleMoves;
    }

    public String returnOneMove() {
        Random randomMove = new Random();
        int index = randomMove.nextInt(possibleMoves.size());
        return possibleMoves.get(index);
    }
}
