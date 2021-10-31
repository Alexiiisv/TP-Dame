package meilleur.com.utilitaire;

import meilleur.com.model.Board;
import meilleur.com.model.Player;

public class Game {
        Function function = new Function();
        Board board = new Board();
        Player p1 = new Player(0);
        Player p2 = new Player(1);

    public void newGame() {
        function.mainMenu();
        if (function.choix()) {
            board.createBoard();
            p1.initStuff();
            p2.initStuff();
            board.placePion(p1.getStuff());
            board.placePion(p2.getStuff());
            board.printMap();
            function.playerSwitch();

        }
    }
}
