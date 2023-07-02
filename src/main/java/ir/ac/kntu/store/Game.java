package ir.ac.kntu.store;

import ir.ac.kntu.Enum.GameType;
import ir.ac.kntu.Enum.Type;
import ir.ac.kntu.Scan.ScannerWrapper;
import ir.ac.kntu.manage.ManageGame;
import ir.ac.kntu.presen.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Game extends Salable {

    public String name;
    private GameType gameType;
    public Float price;
    String explanation;
    Type genre;

    int needLevel;
    public Map<User, Float> rate;
    public int numberOfVoters = 0;
    Map<User, String> community = new HashMap<User, String>();

    public Game(String name, Type genre, String explanation, Float price, GameType gameType, int needLevel) {
        super();
        this.name = name;
        this.needLevel = needLevel;
        this.explanation = explanation;
        this.price = price;
        this.genre = genre;
        rate = new HashMap<>();
        this.gameType = gameType;

    }


    public int getNeedLevel() {
        return needLevel;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public int getNumberOfVoters() {
        return numberOfVoters;
    }

    public Map<User, String> getCommunity() {
        return community;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Float getPrice(User user) {
        float price;
        switch (user.getLevel()) {
            case 1 -> price = this.price;
            case 2 -> price = this.price * 0.9f;
            case 3 -> price = this.price * 0.8f;
            default ->price = this.price * 0.7f;
        }
        return price;
    }

    public Float getPrice(){
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }

    public String getExplanation() {
        return explanation;
    }

    public Type getGenre() {
        return genre;
    }

    public void addCommunity(User user, String community) {
        this.community.put(user, community);
    }

    public void printCommunity() {
        for (String s : community.values()) {
            System.out.println(s);
        }
    }

    public String toString() {
        return name + "\n"
                + "explanation : " + explanation + "\n"
                + "Rate : " + getRate() + "\n"
                + "Price  : " + price;
    }


    public static Game read() {
        String name;
        int needLevel;
        String explanation;
        Float price;
        Type genre;
        Game game;
        System.out.println("enter Name");
        name = ScannerWrapper.getInstance().next();
        System.out.println("enter Explanation");
        explanation = ScannerWrapper.getInstance().next();
        System.out.println("enter Genre");
        try {
            genre = Type.valueOf(ScannerWrapper.getInstance().next());
        }catch (Exception e){
            genre = Type.Action;
        }

        System.out.println("enter price");
        price = ScannerWrapper.getInstance().nextDouble().floatValue();
        System.out.println("What is the minimum level required?");
        needLevel = ScannerWrapper.getInstance().nextInt();
        System.out.println("Is it the original version?   1 = yes");
        if (ScannerWrapper.getInstance().nextInt() == 1)
            game = new Game(name, genre, explanation, price, GameType.ORIGINAL, needLevel);
        else game = new Game(name, genre, explanation, price, GameType.BETA, needLevel);
        ManageGame.games.add(game);
        return game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return needLevel == game.needLevel && Objects.equals(name, game.name) && gameType == game.gameType && Objects.equals(price, game.price) && Objects.equals(explanation, game.explanation) && genre == game.genre;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
        result = 31 * result + (this.price == null ? 0 : this.price.hashCode());
        result = 31 * result + (this.explanation == null ? 0 : this.explanation.hashCode());
        return result;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setGenre(Type genre) {
        this.genre = genre;
    }

    public void setRate(Map<User, Float> rate) {
        this.rate = rate;
    }

    public void setNumberOfVoters(int numberOfVoters) {
        this.numberOfVoters = numberOfVoters;
    }


    public Float getRate() {
        Float rate = 0f;
        for (Float f : this.rate.values()) {
            rate += f;
        }
        rate /= this.rate.size();

        return rate;
    }

}
