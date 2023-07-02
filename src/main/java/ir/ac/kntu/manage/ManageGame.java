package ir.ac.kntu.manage;

import ir.ac.kntu.Enum.GameType;
import ir.ac.kntu.Enum.Type;
import ir.ac.kntu.presen.Developer;
import ir.ac.kntu.store.Game;
import ir.ac.kntu.Print.Print;
import ir.ac.kntu.Scan.ScannerWrapper;
import ir.ac.kntu.presen.User;
import ir.ac.kntu.search.SearchGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.lang.System.in;
import static java.lang.System.out;


public class ManageGame {
    public static ArrayList<Game> games = new ArrayList<>();

    public static void printGame() {
        Print.printArrayGame(games, "");
    }


    public static void printGame(User user) {
        AtomicInteger number = new AtomicInteger(1);
        ArrayList<String> arrayList = new ArrayList<>();

        games.forEach(game ->
                arrayList.add(user.getGames().contains(game) ? number.getAndIncrement() + " " + game.name : number.getAndIncrement() + " " + game.name + " you can by"));

        Print.printArrayGame(arrayList);
    }


    public static void printGameOfUser(User user) {
        Print.printArrayGame(user.getGames(), "");
    }

    public static ArrayList<Game> printGameOfUser(User user, String search) {
        int number = 0;
        ArrayList<Game> games1 = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (Game game : user.getGames()) {
            if (game.getName().contains(search)) {
                number++;
                games1.add(game);
                stringArrayList.add(number + " " + game.name);
            }
        }
        Print.printArrayGame(stringArrayList);
        return games1;
    }


    public static ArrayList<Game> searchGame(String search) {
        ArrayList<Game> hasSearchConditions;
        hasSearchConditions = SearchGame.searchInArray(games, search);
        Print.printArrayGame(hasSearchConditions, "");
        return hasSearchConditions;
    }

    public static void printByFilterWithPrice() {
        out.println("Enter min Price");
        int min = ScannerWrapper.getInstance().nextInt();
        out.println("Enter max Price");
        int max = ScannerWrapper.getInstance().nextInt();
        ArrayList<Game> gameArrayList = SearchGame.searchWithFilter(games, game -> game.price > min && game.price < max);
        Print.printArrayGame(gameArrayList, "");
    }

    public static boolean buyGame(User user, Game game) {


        if (user.getLevel() < game.getNeedLevel()){
            out.println("شما امتیاز لازم زا ندارید");
            return false;
        }

        if (user.getGames().contains(game)) {
            out.println("you already buy this game");
            return false;
        }


        if (user.getWallet() >= game.getPrice(user)) {
            user.setWallet(user.getWallet() - game.getPrice(user));
            user.addGame(game);
            out.println("you buy this game");
            return true;
        }

        out.println("your wallet isn't enough");
        return false;
    }

    public static void creatGame() {
        Game sample = Game.read();
        boolean exist = games.stream().filter(game -> game.hashCode() == sample.hashCode()).filter(game -> game.equals(sample)).toList().size() != 0;
        if (exist) games.add(sample);
        else out.println("this Game Already exist");
    }
    public static void changeGame(Game change) {

        for (Game game : games)
            if (game.hashCode() == change.hashCode())
                if (game.equals(change)) {
                    out.println("enter Name");
                    game.setName(ScannerWrapper.getInstance().next());
                    System.out.println("enter Explanation");
                    game.setExplanation(ScannerWrapper.getInstance().next());
                    System.out.println("enter Genre");
                    game.setGenre(Type.valueOf(ScannerWrapper.getInstance().next()));
                    System.out.println("enter price");
                    game.setPrice(ScannerWrapper.getInstance().nextDouble().floatValue());
                }
    }
    public static boolean deleteGame(Game removeGame) {
        for (Game game : games)
            if (game.hashCode() == removeGame.hashCode())
                if (game.equals(removeGame)) {
                    games.remove(game);
                    ManageUser.getUsers().forEach(user -> user.getGames().remove(removeGame));
                    return true;
                }
        return false;
    }

    public static ArrayList<Game> getGames() {
        return new ArrayList<>(games);
    }

}
