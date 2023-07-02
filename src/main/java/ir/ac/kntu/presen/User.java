package ir.ac.kntu.presen;

import ir.ac.kntu.Scan.ScannerWrapper;
import ir.ac.kntu.store.Game;
import ir.ac.kntu.store.GameAccessory;

import java.util.ArrayList;

public class User {

    public int score = 0;
    public String userName;
    public String pass;
    String phoneNumber;
    Float wallet;
    String email;
    ArrayList<User> freind = new ArrayList<>();
    ArrayList<Game> games = new ArrayList<>();

    private ArrayList<GameAccessory> gameAccessoryArrayList = new ArrayList<>();
    ArrayList<User> requestFromFreind = new ArrayList<>();

    public User(String userName, String pass, String phoneNumber, String email) {
        this.email = email;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.pass = pass;
        this.wallet = 0f;
    }


    public ArrayList<GameAccessory> getGameAccessoryArrayList() {
        return gameAccessoryArrayList;
    }

    public void setGameAccessoryArrayList(ArrayList<GameAccessory> gameAccessoryArrayList) {
        this.gameAccessoryArrayList = gameAccessoryArrayList;
    }

    public void addRequest(User user) {
        if (user != null && !requestFromFreind.contains(user)) requestFromFreind.add(user);
    }

    public ArrayList<User> getRequestFromFreind() {
        return requestFromFreind;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setRequestFromFreind(ArrayList<User> requestFromFreind) {
        this.requestFromFreind = requestFromFreind;
    }

    public void setFreind(ArrayList<User> freind) {
        this.freind = freind;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public ArrayList<User> getFreind() {
        return freind;
    }

    public ArrayList<Game> getGames() {
        return games;
    }


    public void addGame(Game game) {
        games.add(game);
    }

    public void addFreind(User user) {
        freind.add(user);
    }

    public String getUserName() {
        return userName;
    }

    public Float getWallet() {
        return wallet;
    }

    public void chargeWallet(Float money) {
        this.wallet += money;
    }

    public static String getPass() {
        String pass;
        do {
            System.out.println("enter pass");
            pass = ScannerWrapper.getInstance().next();
        } while (pass.matches("[A-Z]") && pass.matches("[0-9]") && pass.matches("[a-z]"));
        return pass;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setWallet(Float wallet) {
        this.wallet = wallet;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static User read() {
        String userName;
        String pass;
        String phoneNumber;
        String email;
        System.out.println("enter UserName");
        userName = ScannerWrapper.getInstance().next();
        pass = getPass();
        System.out.println("enter email");
        email = ScannerWrapper.getInstance().next();
        System.out.println("enter phone number");
        phoneNumber = ScannerWrapper.getInstance().next();

        return new User(userName, pass, phoneNumber, email);
    }

    @Override
    public String toString() {

        return "\n" + userName + "\n" + "phoneNumber : " + phoneNumber + "\n" + "email : " + email + "\n"
                + "score "+ score  ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if (this.userName == ((User) o).getUserName()) return true;
        return false;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (this.userName == null ? 0 : this.userName.hashCode());
        result = 31 * result + (this.pass == null ? 0 : this.pass.hashCode());
        result = 31 * result + (this.email == null ? 0 : this.email.hashCode());
        return result;
    }

    public void seeAllFreind() {
        for (User user : freind) {
            System.out.println(user.userName);
        }
    }

    public void findFreind(String sample) {
        boolean atLessOneThingWasPrinted = false;
        for (User user : freind)
            if (user.toString().contains(sample)) {
                System.out.println(user);
                atLessOneThingWasPrinted = true;
            }
        if (!atLessOneThingWasPrinted) System.out.println("Unfortunately, no items were found");
    }

    public void printRequest() {
        int number = 0;
        boolean atLessOneThingWasPrinted = false;
        for (User user : getRequestFromFreind()) {
            number++;
            System.out.println(number + " " + user.getUserName());
            atLessOneThingWasPrinted = true;
        }
        if (!atLessOneThingWasPrinted) System.out.println("You Hasn't Request");
    }

    public void acceptRequest(User requested) {
        System.out.println("Do you accept " + requested.getUserName() + "'s request?        1=yes     2=no");
        switch (ScannerWrapper.getInstance().nextInt()) {
            case 1:
                requested.freind.add(this);
                this.freind.add(requested);
                System.out.println("You accept " + requested.getUserName() + "'s request");
                break;
            case 2:
                System.out.println("You did not accept " + requested.getUserName() + "'s request");
                break;
        }
    }
    public int getLevel(){
        if (score > 100)
            return 4;
        else if(score > 50)
            return 3;
        else if(score > 20)
            return 2;
        else return 1;
    }
}