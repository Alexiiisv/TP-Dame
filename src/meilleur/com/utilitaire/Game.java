package meilleur.com.utilitaire;

import meilleur.com.model.Board;
import meilleur.com.model.Player;

public class Game {
    Function function = new Function();
    Board board = new Board();
    Player p1 = new Player(0, "Jackie");
    Player p2 = new Player(1, "Michel");
    int choix = 1;
    boolean inGame = true, player = true;

    /** Corps du jeu, endroit ou le jeu fonctionne */
    public void newGame() {
        function.mainMenu();
        String choixGame = function.choix();
        if (choixGame.equals("jouer") || choixGame.equals("j")) {
            initGame();
            while (inGame) {
                if (choix == 1) {
                    player = function.playerSwitch();
                    choix = 3;
                }
                if (choix == 3) choix = board.move(getPlayer(player), getPlayer(!player));
                if (choix == 0) {
                    function.appendDataToResult("\t]\n}");
                    function.writeFile();
                    System.exit(10);
                }
                function.appendDataToResult(board.ReturnMove());
            }
        } else if (choixGame.equals("replay")) {
            initGame();
            board.printMap();
            ReplayGame.watchReplay(board, p1, p2);
        } else System.out.println("Au revoir");
    }

    /**
     * retourne un Player suivant un boolean
     * @param b = boolean equivalent au joueur
     * @return Player
     */
    Player getPlayer(boolean b) {
        if (b) return p1;
        return p2;
    }

    /** initialise le jeu */
    void initGame() {
        function.appendDataToResult(null, p1, p2);
        board.createBoard();
        p1.initPion();
        p2.initPion();
        board.placePion(p1.getAllPion());
        board.placePion(p2.getAllPion());
        System.out.println("\nLe match opposera " + p1.getName() + " & " + p2.getName() + "\n");
    }
}
