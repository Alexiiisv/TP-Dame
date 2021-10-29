package meilleur.com.model;

public class Dame {

    private char Letter; //j1 = 'd' || j2 = 'D'
    private int posX, posY;
    private boolean alive = true;

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
        return "Jsuis une Dame";
    }
}


/*
    La dame peut se déplacer dans tous les sens d'autant de cases qu'elle le souhaite à partir du moment où
    elle suit les diagonales et qu'aucune pièce n'est sur sa trajectoire.
 */