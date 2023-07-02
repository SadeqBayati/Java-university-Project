package ir.ac.kntu.manage;

import ir.ac.kntu.Print.Print;
import ir.ac.kntu.Scan.ScannerWrapper;
import ir.ac.kntu.presen.Seller;
import ir.ac.kntu.presen.User;
import ir.ac.kntu.store.GameAccessory;
import ir.ac.kntu.store.GameController;
import ir.ac.kntu.store.Monitors;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class ManageGameAccessory {

    private static ArrayList<GameAccessory> accessories = new ArrayList<>();

    public static void printAccessories(Predicate<GameAccessory> predicate) {

        ArrayList<String> arrayList = new ArrayList<>();
        accessories.stream().filter(predicate).forEach(gameAccessory -> arrayList.add(gameAccessory.getName()));
        Print.printArrayGameAccessories(arrayList);
    }
    public static void printAccessories(Predicate<GameAccessory> predicate,Predicate<GameAccessory> predicate1) {

        ArrayList<String> arrayList = new ArrayList<>();
        accessories.stream().filter(predicate).filter(predicate1).forEach(gameAccessory -> arrayList.add(gameAccessory.getName()));
        Print.printArrayGameAccessories(arrayList);
    }

    public static ArrayList<GameAccessory> getAccessories() {
        return accessories;
    }

    public static void printAccessoriesOfSeller(Seller seller) {
        Print.printArrayGameAccessories(accessories.stream().filter(gameAccessory -> gameAccessory.getSeller().equals(seller)).map(GameAccessory::getName).collect(Collectors.toCollection(ArrayList::new)));
    }

    public static void printAccessoriesWithSearch(Seller seller) {
        out.println("please enter String Search");
        String search = ScannerWrapper.getInstance().next();
        ArrayList<String> stringArrayList = accessories.stream().filter(gameAccessory -> gameAccessory.getSeller().equals(seller)).filter(gameAccessory -> gameAccessory.toString().contains(search)).map(GameAccessory::getName).collect(Collectors.toCollection(ArrayList::new));
        Print.printArrayGameAccessories(stringArrayList);
        out.println("please choose one");
        int select = ScannerWrapper.getInstance().nextInt();
        if (select <= stringArrayList.size() && select > 0)
            out.println(accessories.stream().filter(gameAccessory -> gameAccessory.getName().equals(stringArrayList.get(select - 1))).toList().get(0).toString());
    }


    public static void printAccessoriesOfUser(User user) {
        ArrayList<String> arrayList = new ArrayList<>();
        user.getGameAccessoryArrayList().forEach(gameAccessory -> arrayList.add(gameAccessory.getName()));
        Print.printArrayGameAccessories(arrayList);
    }


    public static ArrayList<GameAccessory> printAccessoriesOfUser(User user, String search) {
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<GameAccessory> gameAccessoryArrayList = new ArrayList<>();

        user.getGameAccessoryArrayList().stream().filter(gameAccessory -> gameAccessory.toString().contains(search)).forEach(gameAccessory -> {
            gameAccessoryArrayList.add(gameAccessory);
            arrayList.add(gameAccessory.getName());
        });

        Print.printArrayGameAccessories(arrayList);
        return gameAccessoryArrayList;
    }


    public static ArrayList<GameAccessory> printAccessoriesOfUser(User user, String search, Class c) {
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<GameAccessory> gameAccessoryArrayList = new ArrayList<>();

        user.getGameAccessoryArrayList().stream().filter(gameAccessory -> gameAccessory.getClass() == c && gameAccessory.toString().contains(search)).forEach(gameAccessory -> {
            gameAccessoryArrayList.add(gameAccessory);
            arrayList.add(gameAccessory.getName());
        });

        Print.printArrayGameAccessories(arrayList);
        return gameAccessoryArrayList;
    }


    public static void buyGameAccessory(User user, GameAccessory gameAccessory) {
        if (gameAccessory.getStockNumber() == 0) {
            out.println("موجودی به پایان رسید");
            return;
        }
        if (user.getWallet() >= gameAccessory.getPrice()) {
            user.setWallet(user.getWallet() - gameAccessory.getPrice());
            user.getGameAccessoryArrayList().add(gameAccessory);
            out.println("you buy this item");
            gameAccessory.plusesNumberOfSelling();
            gameAccessory.minusStockNumber();
        } else out.println("your wallet isn't enough");
    }

    public static void changeGameAccessory(GameAccessory gameAccessory) {

        out.println("enter Name");
        gameAccessory.setName(ScannerWrapper.getInstance().next());
        System.out.println("enter Explanation");
        gameAccessory.setExplanation(ScannerWrapper.getInstance().next());
        System.out.println("enter price");
        gameAccessory.setPrice(ScannerWrapper.getInstance().nextDouble().floatValue());
    }

    public static void changeGameAccessory(Seller seller) {
        GameAccessory gameAccessory;
        ArrayList<String> arrayList = new ArrayList<>();
        accessories.stream().filter(gameAccessory1 -> gameAccessory1.getSeller().equals(seller)).forEach(gameAccessory1 -> arrayList.add(gameAccessory1.getName()));
        Print.printArrayGameAccessories(arrayList);
        if (arrayList.size() == 0) {
            out.println("فروشنده شما هیچ تجهیزاتی را ندارد");
            return;
        }
        out.println("please choose one");
        int select = ScannerWrapper.getInstance().nextInt();
        if (select <= accessories.size() && select > 0) gameAccessory = accessories.get(select - 1);
        else return;
        out.println("enter Name");
        gameAccessory.setName(ScannerWrapper.getInstance().next());
        System.out.println("enter Explanation");
        gameAccessory.setExplanation(ScannerWrapper.getInstance().next());
        System.out.println("enter price");
        gameAccessory.setPrice(ScannerWrapper.getInstance().nextDouble().floatValue());
    }

    public static boolean deleteGameAccessory(Seller seller) {
        ArrayList<String> arrayList = new ArrayList<>();
        accessories.stream().filter(gameAccessory -> gameAccessory.getSeller().equals(seller)).forEach(gameAccessory1 -> arrayList.add(gameAccessory1.getName()));
        Print.printArrayGameAccessories(arrayList);
        out.println("please choose one");
        int select = ScannerWrapper.getInstance().nextInt();
        if (select <= accessories.size() && select > 0) {
            deleteGameAccessory(accessories.get(select - 1));
            return true;
        }
        return false;
    }

    public static void deleteGameAccessory(GameAccessory gameAccessory) {
        accessories.remove(gameAccessory);
        ManageUser.getUsers().forEach(user -> user.getGameAccessoryArrayList().remove(gameAccessory));
    }


    public static GameAccessory createGameAccessory(Seller seller) {
        boolean exist;
        GameAccessory gameAccessory;
        out.println("What do you want to create?  i = controller  2 = monitor");

        if (ScannerWrapper.getInstance().nextInt() == 1)

            gameAccessory = GameController.read(seller);
        else

            gameAccessory = Monitors.read(seller);

        exist = accessories.stream().filter(gameAccessory1 -> accessories.contains(gameAccessory)).toList().size() == 0;
        if (exist) accessories.add(gameAccessory);
        else out.println("your Accessory Already Exist");
        return gameAccessory;
    }

}
