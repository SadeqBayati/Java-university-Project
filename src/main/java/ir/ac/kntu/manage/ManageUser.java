package ir.ac.kntu.manage;

import ir.ac.kntu.Enum.GameType;
import ir.ac.kntu.Menu.Menu;
import ir.ac.kntu.presen.Developer;
import ir.ac.kntu.store.Game;
import ir.ac.kntu.Scan.ScannerWrapper;
import ir.ac.kntu.presen.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.System.out;

public class ManageUser {

    public static ArrayList<User> users = new ArrayList<>();


    public static void changeUser(User user) {
        for (User user1 : users)
            if (user1.hashCode() == user.hashCode())
                if (user1.equals(user)) {
                    ArrayList<User> freind = user.getFreind();
                    ArrayList<Game> games = user.getGames();
                    ArrayList<User> requestToFreind = user.getRequestFromFreind();
                    User user2 = User.read();
                    user2.setFreind(freind);
                    user2.setGames(games);
                    user2.setRequestFromFreind(requestToFreind);
                    users.set(users.indexOf(user), user2);
                }
    }

    public static boolean deleteUser(User user) {

        for (User user1 : users) {
            if (user1.hashCode() == user.hashCode()) {
                if (user1.equals(user)) {
                    users.remove(user);
                    return true;
                }
            }
        }

        return false;
    }

    public static void printUser(User user) {
        System.out.println(user);
    }

    public static void printUsers() {
        for (User user : users) {
            printUser(user);
        }
    }

    public static void printAllUserName() {
        int number = 0;
        for (User user : users) {
            number++;
            System.out.println(number + " " + user.userName);
        }
    }

    public static void findUser(String search) {
        for (User user : users) {
            if (user.toString().contains(search))
                System.out.println(user.userName);
        }
    }

    public static void chargeWallet(User user) {
        System.out.println("How much to charge?");
        user.chargeWallet(ScannerWrapper.getInstance().nextDouble().floatValue());
    }


    public static void sendRequest(User send, User get) {
        get.addRequest(send);
    }


    public static User findUserWithUserNameAndPass(String userName, String pass) {
        for (User user : users) {
            if (user.getUserName().equals(userName) && user.pass.equals(pass))
                return user;
        }
        return null;
    }

    public static ArrayList<User> printAndReturnUserThatNotFreind(User user) {
        ArrayList<User> userThatNotFreind = new ArrayList<>();
        int number = 0;
        for (User user1 : users) {
            if (!user.getFreind().contains(user1) && !user1.equals(user)) {
                number++;
                System.out.println(number + " " + user1.getUserName());
                userThatNotFreind.add(user1);
            }
        }
        return userThatNotFreind;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }
}
