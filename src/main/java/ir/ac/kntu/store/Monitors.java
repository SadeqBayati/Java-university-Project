package ir.ac.kntu.store;

import ir.ac.kntu.Enum.SuitableDevice;
import ir.ac.kntu.Scan.ScannerWrapper;
import ir.ac.kntu.presen.Seller;
import ir.ac.kntu.presen.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Monitors extends GameAccessory {
    int stockNumber;
    int numberOfSelling = 0;
    int numberOfVoters = 0;
    Map<User, String> community = new HashMap<User, String>();
    String name;
    Float price;
    String explanation;
    Seller seller;
    Map<User, Float> rate;
    int size;
    int hertz;

    public Monitors(String name, String explanation, Float price, Seller seller, int size, int hertz, int stockNumber) {
        super();
        this.stockNumber = stockNumber;
        this.name = name;
        this.explanation = explanation;
        this.price = price;
        this.seller = seller;
        this.hertz = hertz;
        this.size = size;
        rate = new HashMap<>();
    }


    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Map<User, String> getCommunity() {
        return community;
    }

    public void setRate(Map<User, Float> rate) {
        this.rate = rate;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCommunity(Map<User, String> community) {
        this.community = community;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Float getPrice() {
        return price;
    }

    public int getHertz() {
        return hertz;
    }

    public int getSize() {
        return size;
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

    public Map<User, Float> getRate() {
        return rate;
    }


    public int getNumberOfSelling() {
        return numberOfSelling;
    }

    @Override
    public Float getAverageRate() {
        int number = 0;
        float f = 0f;
        for (Float s : rate.values()) {
            f += s;
            number++;
        }
        return f / number;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setHertz(int hertz) {
        this.hertz = hertz;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }


    public int getNumberOfVoters() {
        return numberOfVoters;
    }

    @Override
    public String toString() {
        return "Monitors{" + "name='" + name + '\'' + ", price=" + price + ", explanation='" + explanation + '\'' + ", size=" + size + ", hertz=" + hertz + ", Stock Number " + stockNumber + '}';
    }

    public static Monitors read(Seller seller) {

        String name;
        String explanation;
        float price;
        int hertz, size;

        System.out.println("enter Name");
        name = ScannerWrapper.getInstance().next();
        System.out.println("enter Explanation");
        explanation = ScannerWrapper.getInstance().next();
        System.out.println("enter price");
        price = ScannerWrapper.getInstance().nextDouble().floatValue();
        System.out.println("enter Size");
        size = ScannerWrapper.getInstance().nextInt();
        System.out.println("Enter Hertz");
        hertz = ScannerWrapper.getInstance().nextInt();
        System.out.println("please enter stock number");
        int stock = ScannerWrapper.getInstance().nextInt();
        return new Monitors(name, explanation, price, seller, size, hertz, stock);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monitors monitors = (Monitors) o;
        return size == monitors.size && hertz == monitors.hertz && Objects.equals(name, monitors.name) && Objects.equals(price, monitors.price) && Objects.equals(explanation, monitors.explanation) && Objects.equals(seller, monitors.seller);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
        result = 31 * result + (this.price == null ? 0 : this.price.hashCode());
        result = 31 * result + (this.explanation == null ? 0 : this.explanation.hashCode());
        result = 31 * result + hertz * size;
        return result;
    }
}
