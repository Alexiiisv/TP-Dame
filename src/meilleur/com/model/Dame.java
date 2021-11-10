package meilleur.com.model;

public class Dame extends Objet {

    public Dame(char letter, int posX, int posY) {
        super(letter, posX, posY);
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