package meilleur.com.utilitaire;

import meilleur.com.model.Board;
import meilleur.com.model.Player;

public class Game {
    Function function = new Function();
    Board board = new Board();
    Player p1 = new Player(0, "Jackie");
    Player p2 = new Player(1, "Michel");
    boolean inGame = true;

    public void newGame() {
        function.mainMenu();
        if (function.choix()) {
            initGame();
            while (inGame) {
                boolean player = function.playerSwitch();
                board.move(getPlayer(player), getPlayer(!player));
            }
        }
    }

    public Player getPlayer(boolean b) {
        if (b) return p1;
        return p2;
    }

    public void initGame() {
        //function.FileCreateReadWrite();
        board.createBoard();
        p1.initStuff();
        p2.initStuff();
        board.placePion(p1.getStuff());
        board.placePion(p2.getStuff());
    }
}
