package ir.ac.kntu.search;

import ir.ac.kntu.store.Game;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SearchGame {

    public static ArrayList<Game> searchInArray(ArrayList<Game> arrayList, String string){
        ArrayList<Game> arrayListNew = new ArrayList<>();

        for (Game game : arrayList){
            if (game.toString().contains(string))
                arrayListNew.add(game);
        }
        return arrayListNew;
    }

    public static ArrayList<Game> searchWithFilter(ArrayList<Game> games,Predicate<Game> predicate){
        ArrayList<Game> gameArrayList = games.stream().filter(predicate).collect(Collectors.toCollection(ArrayList::new));
        return gameArrayList;
    }

}
