package meilleur.com.utilitaire;

import meilleur.com.model.Board;
import meilleur.com.model.Player;


public class Game {
    public boolean botornot = false;
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
            initGame(choixGame);
            inGameF();
            function.writeFile();
            System.exit(10);
        } else if (choixGame.equals("replay") || choixGame.equals("r")) {
            initGame(choixGame);
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
    void initGame(String str) {
        if (!(str.equals("replay") || str.equals("r"))) botornot = function.isBotPlaying();
        function.appendDataToResult(null, p1, p2);
        board.createBoard();
        p1.initPion();
        p2.initPion();
        board.placePion(p1.getAllPion());
        board.placePion(p2.getAllPion());
        if (!(str.equals("replay") || str.equals("r"))) System.out.println("\nLe match opposera " + p1.getName() + " & " + p2.getName() + "\n");
    }

    protected void inGameF() {
        while (inGame) {
            if (choix == 1) {
                player = function.playerSwitch();
                System.out.println(function.botMoves(getPlayer(player), board.playBoard));
                choix = 3;
            }
            if (choix == 3) {
                choix = board.move(getPlayer(player), getPlayer(!player));
            }
            if (choix == 0) {
                function.removeLast();
                function.appendDataToResult("\t],\n\t\"Winner\": \"Il n'y a pas de gagnant un joueur a quitter la partie\"\n}");
                inGame = false;
            }
            if (board.ReturnMove().length() == 12 && board.isHaveMoved()) {
                function.appendDataToResult(board.ReturnMove());
                board.setHaveMoved(false);
            }
            conditionIfWinner();
        }
        System.out.println(getPlayer(player).getName() + " a gagné");
    }

    private void conditionIfWinner() {
        if (haveSomeoneWin() == 1) {
            function.removeLast();
            function.appendDataToResult("\t],\n\t\"Winner\": \"" + p1.getName() + "\"\n}");
            inGame = false;
        }
        if (haveSomeoneWin() == 2) {
            function.removeLast();
            function.appendDataToResult("\t],\n\t\"Winner\": \"" + p2.getName() + "\"\n}");
            inGame = false;
        }
    }

    private int haveSomeoneWin() {
        if (p1.getLastPions() == 0) return 2;
        if (p2.getLastPions() == 0) return 1;
        return 0;
    }
}
