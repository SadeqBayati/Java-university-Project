package ir.ac.kntu.store;

import ir.ac.kntu.manage.ManageGame;
import ir.ac.kntu.manage.ManageGameAccessory;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Salable {

    public abstract Float getPrice();
    static ArrayList<Salable> arrayList = new ArrayList<>();
    public abstract String getName();
    public Salable(){
        update();
    }

    public static ArrayList<Salable> getArrayList() {
        return new ArrayList<>(arrayList);
    }

    public void update(){
        ManageGameAccessory.getAccessories().stream().filter(gameAccessory -> !arrayList.contains(gameAccessory)).forEach(arrayList::add);
        ManageGame.getGames().stream().filter(game -> !arrayList.contains(game)).forEach(arrayList::add);
    }
}
