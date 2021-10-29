package meilleur.com.utilitaire;

import meilleur.com.model.Board;
import meilleur.com.model.Player;

public class Game {

        Function function = new Function();
        Board board = new Board();
        Player p1 = new Player();
        Player p2 = new Player();

    public void newGame() {
        function.mainMenu();
        if (function.choix()) {
            board.createBoard();
            board.printMap();
            p1.initStuff();
            p1.printStuff();;
            p2.initStuff();
            p2.printStuff();

        }
    }
}
