package meilleur.com.utilitaire;

import meilleur.com.model.Board;
import meilleur.com.model.Player;

public class Game {
    Function function = new Function();
    Board board = new Board();
    Player p1 = new Player(0, "Jackie");
    Player p2 = new Player(1, "Michel");
    int choix = 0;
    boolean inGame = true, player = true;

    public void newGame() {
        function.mainMenu();
        if (function.choix()) {
            initGame();
            while (inGame) {
                if (choix != 2) {
                    player = function.playerSwitch();
                }
                choix = board.move(getPlayer(player), getPlayer(!player));
                if (choix == 0) {
                    function.appendDataToResult("\t]\n}");
                    function.writeFile();
                    System.exit(10);
                }
                function.appendDataToResult(board.ReturnMove());
            }
        }
    }

    Player getPlayer(boolean b) {
        if (b) return p1;
        return p2;
    }

    void initGame() {
        function.appendDataToResult(null, p1, p2);
        board.createBoard();
        p1.initStuff();
        p2.initStuff();
        board.placePion(p1.getStuff());
        board.placePion(p2.getStuff());
    }
}
