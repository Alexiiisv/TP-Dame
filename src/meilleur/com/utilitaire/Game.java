package meilleur.com.utilitaire;

import meilleur.com.model.Board;

public class Game {

    public void newGame() {
        System.out.println("TP - Échecs \n");
        Board board = new Board();
        board.CreateBoard();
        board.printMap();
    }
}
