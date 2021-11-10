package meilleur.com.model;

public class Pion extends Objet{

    public Pion(char letter, int posX, int posY) {
        super(letter, posX, posY);
    }

    @Override
    public String toString() {
        return "Jsuis un Pion\nJe me trouve a la position X : " + this.posY + " Y : " + this.posX;
    }
}

/*
    Les pions se déplacent sur les diagonales, du joueur vers le joueur adverse.
    Ils ne se déplacent que d'une case à la fois sauf lorsqu'il y a une prise.
 */

