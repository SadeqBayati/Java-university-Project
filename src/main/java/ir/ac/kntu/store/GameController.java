package ir.ac.kntu.store;

import ir.ac.kntu.Enum.SuitableDevice;
import ir.ac.kntu.Scan.ScannerWrapper;
import ir.ac.kntu.presen.Seller;
import ir.ac.kntu.presen.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameController extends GameAccessory {
    int stockNumber;

    int numberOfSelling = 0;

    Map<User, String> community = new HashMap<User, String>();
    String name;
    Float price;
    String explanation;
    Seller seller;
    Map<User, Float> rate;
    SuitableDevice suitableDevice;
    boolean isHasWire;
    public GameController(String name, String explanation, Float price, Seller seller, boolean isHasWire, SuitableDevice suitableDevice,int stockNumber) {
        super();
        this.stockNumber = stockNumber;
        this.name = name;
        this.explanation = explanation;
        this.price = price;
        this.seller = seller;
        this.isHasWire = isHasWire;
        this.suitableDevice = suitableDevice;
        rate = new HashMap<>();
    }

    public int getStockNumber() {
        return stockNumber;
    }

    @Override
    public void minusStockNumber() {
        stockNumber--;
    }

    @Override
    public void plusesNumberOfSelling() {
        numberOfSelling++;
    }

    public int getNumberOfSelling() {
        return numberOfSelling;
    }

    public static GameController read(Seller seller) {
        String name;
        String explanation;
        float price;
        boolean isHasWire;
        SuitableDevice suitableDevice;


        System.out.println("enter Name");
        name = ScannerWrapper.getInstance().next();
        System.out.println("enter Explanation");
        explanation = ScannerWrapper.getInstance().next();
        System.out.println("enter price");
        price = ScannerWrapper.getInstance().nextDouble().floatValue();
        System.out.println("Is your gamepad wired?    1 == yes");
        isHasWire = ScannerWrapper.getInstance().nextInt() == 1;
        try {
            System.out.println("What device is it suitable for?");
            suitableDevice = SuitableDevice.valueOf(ScannerWrapper.getInstance().next());
        } catch (Exception e) {
            suitableDevice = SuitableDevice.Computer;
        }

        System.out.println("please enter stock number");
        int stock = ScannerWrapper.getInstance().nextInt();
        return new GameController(name, explanation, price, seller, isHasWire, suitableDevice,stock);
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Map<User, Float> getRate() {
        return rate;
    }

    @Override
    public Float getAverageRate() {
        int number = 0;
        float f = 0f;
        for (Float s : rate.values()){
            f += s;
            number++;
        }
        return f/number;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Map<User, String> getCommunity() {
        return community;
    }

    @Override
    public Float getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    @Override
    public Seller getSeller() {
        return seller;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameController that = (GameController) o;
        return stockNumber == that.stockNumber && numberOfSelling == that.numberOfSelling && isHasWire == that.isHasWire && Objects.equals(community, that.community) && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(explanation, that.explanation);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
        result = 31 * result + (this.price == null ? 0 : this.price.hashCode());
        result = 31 * result + (this.explanation == null ? 0 : this.explanation.hashCode());
        result = 31 * result + suitableDevice.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "GameController{" +
                "community=" + community +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", explanation='" + explanation + '\'' +
                ", suitableDevice=" + suitableDevice +
                ", isHasWire=" + isHasWire +
                ", Stock Number "+ stockNumber +
                '}';
    }
}

