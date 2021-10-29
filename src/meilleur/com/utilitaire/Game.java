package meilleur.com.utilitaire;

import meilleur.com.model.Board;

public class Game {

        Function function = new Function();
        Board board = new Board();

    public void newGame() {
        function.mainMenu();
        if (function.choix()) {
            board.createBoard();
            board.printMap();
        }
    }
}
