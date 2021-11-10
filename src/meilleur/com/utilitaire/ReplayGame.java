package meilleur.com.utilitaire;

import meilleur.com.model.Board;
import meilleur.com.model.Player;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ReplayGame {
    private static ArrayList<String> eachMove = new ArrayList<>();
    public static void watchReplay(Board board, Player p1, Player p2) {
        readJson("10-11-2021 a 11-08-43.json");
        playReplay(board, p2, p1);
    }
    private static void readJson(String string) {
        try {
            FileReader file = new FileReader("src/meilleur/com/save/" + string);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                if (data.length() > 7) {
                    data = data.substring(3, 9);
                    if (data.matches("[0-9][0-9] [1-9]([T^][$L]|[T^][$R]|[B^][$R]|[B^][$L])")) {
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
        for (String str: eachMove) {
            waitTime();
            if (i ==0) {
                board.move(p1, p2, str);
                i++;
            }
            else {
                board.move(p2, p1, str);
                i--;
            }
        }
        System.exit(0);
    }

    private static void waitTime() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
