package model;

/**
 *
 * @author huynh
 */
public class Cart extends Product{
    private int amount;
    
    public Cart() { 
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
