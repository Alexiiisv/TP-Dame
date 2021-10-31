package meilleur.com.model;

import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    private final int id;
    private ArrayList<Pion> stuff = new ArrayList<>();
    private final char letter;

    public Player(int id) {
        this.id = id;
        this.letter = id == 1 ? 'P' : 'p';
    }

    public ArrayList<Pion> getStuff() {
        return stuff;
    }

    public void setStuff(ArrayList<Pion> stuff) {
        this.stuff = stuff;
    }

    public int getId() {
        return id;
    }

    public char getLetter() {
        return letter;
    }

    public void initStuff() {
        int y = id == 0 ? 0 : 6, x = 0;

        for (int i = 0; i < 21; i++) {


            if (y%2 == 0 && x < 2) x = 1;
            if (y%2 == 1 && x < 2) x = 0;
            if (y%2 == 1 && x < 10) x += 2;
            if (y%2 == 0 && x < 11) x += 2;

            if (id == 1) this.stuff.add(new Pion(this.letter, x-2, y));
            else this.stuff.add(new Pion(this.letter, x-2, y));


            if (i%5 == 0 && x == 11 || x == 10) y++;
            if (i%5 == 0 && x > 5) x = 0;
        }
    }

    public int countAlive() {
        int count = 0;
        for (Pion p : this.stuff) {
            if (p.isAlive()) count++;
        }
        return count;
    }

    public void printStuff() {
        System.out.println("voici les pions du joueurs " + this.id + "\n");
        for (Pion p : this.stuff) {
            if (p.isAlive()) System.out.println(p.toString());
        }
        System.out.println();
    }
    public boolean movePion(String move) {
        boolean test = true;
        String[] resultMove = move.split("");
        for (Pion pion: this.stuff) {
            if (pion.getPosY() == Integer.parseInt(resultMove[0]) && pion.getPosX() == Integer.parseInt(resultMove[1])) {
                System.out.println(Arrays.toString(resultMove));
                return true;
            }
        }
        return false;
    }

}
