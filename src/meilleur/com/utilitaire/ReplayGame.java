package meilleur.com.utilitaire;

import meilleur.com.model.Board;
import meilleur.com.model.Player;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ReplayGame {
    private static final ArrayList<String> eachMove = new ArrayList<>();
    private static final Function function = new Function();

    public static void watchReplay(Board board, Player p1, Player p2) {
        String fileName = function.printAllSave();
        readJson(fileName);
        playReplay(board, p2, p1);
    }

    private static void readJson(String string) {
        String reg = "[0-9][0-9] [1-9]([T^][$L]|[T^][$R]|[B^][$R]|[B^][$L])";
        try {
            FileReader file = new FileReader("src/meilleur/com/save/" + string);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                if (data.length() > 7) {
                    data = data.substring(3, 9);
                    if (data.matches(reg)) {
                        eachMove.add(data);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void playReplay(Board board, Player p1, Player p2) {
        int i = 0;
        board.printplayBoard();
        for (String str : eachMove) {
            function.waitTime(1500, 'R');
            if (i == 0) {
                board.move(p1, p2, str);
                i++;
            } else {
                board.move(p2, p1, str);
                i--;
            }
        }
        System.exit(0);
    }

}
