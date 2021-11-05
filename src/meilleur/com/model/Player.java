package meilleur.com.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    private final int id;
    private final ArrayList<Pion> allPion = new ArrayList<>();
    private final char letter;
    private final String name;

    public Player(int id, String name) {
        this.id = id;
        this.letter = id == 1 ? 'P' : 'p';
        this.name = name;
    }

    /** @return le nom du joueur */
    public String getName() {
        return name;
    }

    /** retourne la liste de tous les pions */
    public ArrayList<Pion> getAllPion() {
        return allPion;
    }

    /** @return lettre du joueur */
    public char getLetter() {
        return letter;
    }

    /** Initialise la position des pions */
    public void initPion() {
        int y = id == 0 ? 0 : 6, x = 0;

        for (int i = 0; i < 21; i++) {


            if (y % 2 == 0 && x < 2) x = 1;
            if (y % 2 == 1 && x < 2) x = 0;
            if (y % 2 == 1 && x < 10) x += 2;
            if (y % 2 == 0 && x < 11) x += 2;

            this.allPion.add(new Pion(this.letter, x - 2, y));

            if (i % 5 == 0 && x == 11 || x == 10) y++;
            if (i % 5 == 0 && x > 5) x = 0;
        }
    }

    /** Affiche tous les Pions du joueur */
    public void printPion() {
        System.out.println("voici les pions du joueurs " + this.id + "\n");
        int i = 0;
        for (Pion p : this.allPion) {
            if (p.isAlive()) {
                i++;
                System.out.println(i + " " + p.toString());
            }
        }
        System.out.println();
    }

    /**
     * verifie si un pion existe a une position donnée
     * @param move = placement du pion que le joueur veut bouger
     * @return true si un pion existe a la position donnée<p>false si n'existe pas
     */
    public boolean movePion(String move) {
        String[] resultMove = move.split("");
        for (Pion pion : this.allPion) {
            if (pion.getPosY() == Integer.parseInt(resultMove[0]) && pion.getPosX() == Integer.parseInt(resultMove[1])) {
                return true;
            }
        }
        return false;
    }

    /**
     * update la position du pion a la position lastX, lastY
     * @param lastY {@code int} = position avant déplacement sur l'axe Y
     * @param lastX {@code int} = position avant déplacement sur l'axe X
     * @param newX {@code int} = position apres déplacement sur l'axe X
     * @param newY {@code int} = position apres déplacement sur l'axe Y
     */
    public void updatePion(int lastY, int lastX, int newX, int newY) {
        for (Pion pion : this.allPion) {
            if (pion.getPosY() == lastY && pion.getPosX() == lastX) {
                pion.setPosX(newX);
                pion.setPosY(newY);
                break;
            }
        }
    }

    /**
     * update la variable {@code boolean}
     * @param y {@code int}
     * @param x {@code int}
     */
    public void updatePionLive(int y, int x) {
        for (Pion pion : this.allPion) {
            if (pion.getPosY() == y && pion.getPosX() == x) {
                pion.setAlive(false);
                break;
            }
        }
    }

    /**
     * recup le pion a la position x - y
     * @param y {@code int}
     * @param x {@code int}
     * @return retourne un Pion si il existe sinon null
     */
    public Pion getPion(int y, int x) {
        for (Pion pion : this.allPion) {
            if (pion.getPosX() == x && pion.getPosY() == y) return pion;
        }
        return null;
    }
}
