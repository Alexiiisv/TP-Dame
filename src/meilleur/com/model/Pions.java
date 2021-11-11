package meilleur.com.model;

public class Pions {

    char Letter;
    int posX, posY;
    boolean alive;

    public Pions(char letter, int posX, int posY) {
        Letter = letter;
        this.posX = posX;
        this.posY = posY;
        this.alive = true;
    }

    public char getLetter() {
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

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

}
