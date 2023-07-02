package ir.ac.kntu.store;

import ir.ac.kntu.presen.Seller;
import ir.ac.kntu.presen.User;

import java.util.Map;

public abstract class GameAccessory extends Salable {


    public GameAccessory() {
        super();
    }

    public abstract String getName();
    public abstract void setName(String name);
    public abstract Seller getSeller();
    public abstract void setSeller(Seller seller);
    public abstract Map<User, String> getCommunity();
    public abstract Map<User, Float> getRate();
    public abstract Float getAverageRate();
    public abstract String getExplanation();
    public abstract void setExplanation(String explanation);
    public abstract Float getPrice();
    public abstract void setPrice(float price);

    public abstract int getStockNumber();

    public abstract void minusStockNumber();

    public abstract void plusesNumberOfSelling();
    public abstract int getNumberOfSelling();

    public static final int numberOfPurchases = 0;
}
