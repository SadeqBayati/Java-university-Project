package ir.ac.kntu.manage;

import ir.ac.kntu.Scan.ScannerWrapper;
import ir.ac.kntu.presen.User;
import ir.ac.kntu.store.Game;
import ir.ac.kntu.store.GameAccessory;

public class ManageLibrary {

    public static void addGameCommunity(User user , Game game){
        System.out.println("Please Enter String");
        String community = ScannerWrapper.getInstance().next();
        game.getCommunity().put(user,community);
    }
    public static void printCommunity(Game game){
        for (String s : game.getCommunity().values())
            System.out.println(s);
    }
    public static void addRateGame(User user, Game game){
        System.out.println("Please Enter Float");
        Float rate = ScannerWrapper.getInstance().nextDouble().floatValue();
        game.rate.put(user,rate);
    }
    public static void addRateGameAccessory(User user, GameAccessory gameAccessory){
        System.out.println("Please Enter Float");
        gameAccessory.getRate().put(user,ScannerWrapper.getInstance().nextDouble().floatValue());
    }

    public static void addGameAccessoryCommunity(User user, GameAccessory gameAccessory){
        System.out.println("Please Enter String");
        gameAccessory.getCommunity().put(user,ScannerWrapper.getInstance().next());
    }

}
