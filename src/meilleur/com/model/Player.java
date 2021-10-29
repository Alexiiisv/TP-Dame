package meilleur.com.model;

import java.util.ArrayList;

public class Player {
    private int id;
    private ArrayList<Object> stuff = new ArrayList<>();

    public ArrayList<Object> getStuff() {
        return stuff;
    }

    public void setStuff(ArrayList<Object> stuff) {
        this.stuff = stuff;
    }

    public void initStuff() {
        for (int i = 0; i < 20; i++) {
            this.stuff.add(new Pion());
        }
    }

    public void printStuff() {
        for (Object o : this.stuff) {
            System.out.println(o.toString());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
