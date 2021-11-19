package meilleur.com.model;

public class Pion extends Pions {

    public Pion(char letter, int posX, int posY) {
        super(letter, posX, posY);
    }
}

/*
    Les pions se déplacent sur les diagonales, du joueur vers le joueur adverse.
    Ils ne se déplacent que d'une case à la fois sauf lorsqu'il y a une prise.
 */

