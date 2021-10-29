package meilleur.com.model;

public class Pion {

    private char Letter; //j1 = 'p' || j2 = 'P'
    private int posX, posY;
    private boolean alive = true;

    public Pion(char p, int x, int y) {
        this.Letter = p;
        this.posX = x;
        this.posY = y;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Character getLetter() {
        return Letter;
    }

    public void setLetter(char letter) {
        Letter = letter;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    @Override
    public String toString() {
        return "Jsuis un Pion";
    }
}

/*
    Les pions se déplacent sur les diagonales, du joueur vers le joueur adverse.
    Ils ne se déplacent que d'une case à la fois sauf lorsqu'il y a une prise.
 */

