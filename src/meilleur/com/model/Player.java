package meilleur.com.model;

import java.util.ArrayList;

public class Player {
    private int id;
    private ArrayList<Pion> stuff = new ArrayList<>();

    public Player(int id) {
        this.id = id;
    }

    public ArrayList<Pion> getStuff() {
        return stuff;
    }

    public void setStuff(ArrayList<Pion> stuff) {
        this.stuff = stuff;
    }

    public void initStuff() {
        int y = id == 0 ? 0 : 6, x = 0;

        for (int i = 0; i < 21; i++) {


            if (y%2 == 0 && x < 2) x = 1;
            if (y%2 == 1 && x < 2) x = 0;
            if (y%2 == 1 && x < 10) x += 2;
            if (y%2 == 0 && x < 11) x += 2;

            if (id == 1) this.stuff.add(new Pion('P', x-2, y));
            else this.stuff.add(new Pion('p', x-2, y));


            if (i%5 == 0 && x == 11 || x == 10) y++;
            if (i%5 == 0 && x > 5) x = 0;
        }
    }

    public void printStuff() {
        System.out.println("voici les pions du joueurs " + this.id + "\n");
        for (Pion o : this.stuff) {
            System.out.println(o.toString());
        }
        System.out.println();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
