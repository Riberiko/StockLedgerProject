package StockLedger;

import Deque.EmptyQueueException;
import Deque.LinkedDeque;

import java.util.ArrayList;
import java.util.Iterator;

public class LedgerEntry {

    LinkedDeque<StockPurchase> stockPurchases;

    ArrayList<Double> stockPrices;
    ArrayList<Integer> stockQuantities;

    private String symbol;

    public LedgerEntry(String symbol){
        stockPurchases = new LinkedDeque<StockPurchase>();

        stockPrices = new ArrayList<>();
        stockQuantities = new ArrayList<>();

        this.symbol = symbol;
    }

    /**
     * Buys the stock
     * @param price for the stock
     */
    public void buyStock(double price){
        stockPurchases.add(new StockPurchase(symbol, price));

        int index = stockPrices.indexOf(price);

        if(index == -1){
            stockPrices.add(price);

            index = stockPrices.size()-1;

            stockQuantities.add(index, 1);

            return;
        }

        stockQuantities.set(index, stockQuantities.get(index)+1);

    }

    /**
     * Sells the sock
     *
     * @param quantity  selling for the stock
     * @param priceSold for the price
     * @return  profits made on this action
     */
    public double sellStock(int quantity, double priceSold){

        double profit = priceSold;

        if(quantity > stockPurchases.size()) throw new IllegalArgumentException("Cant sell more than you have");

        for(int i = 0; i < quantity; i++){

            double cost = 0;

            //TODO: Fix this mess
            try {
                cost = stockPurchases.peekBack().cost();
            } catch (EmptyQueueException e) {
                e.printStackTrace();
            }

            priceSold -= cost;

            int index = getIndex(cost);
            stockQuantities.set(index, stockQuantities.get(index)-1);
        }

        return profit;
    }

    /**
     * Retrieves the symbol name
     * @return
     */
    public String getSymbol(){
        return symbol;
    }

    /**
     * Retrieves the index for stock prices
     * @param cost
     * @return
     */
    private int getIndex(double cost){
        for(int i = 0; i < stockPrices.size(); i++){
            if(stockPrices.get(i) == cost) return i;
        }

        return -1;
    }

    /**
     * Retrieves a string representation for the Ledger entry
     * @return
     */
    public String display(){
        String str = symbol.toUpperCase() + ": ";

        for(int i = 0; i < stockPrices.size(); i++){
            str += stockPrices.get(i) + " (" + stockQuantities.get(i) + " shares)";
            if(i != stockPrices.size()-1) str += ",";
        }

        return str;
    }

    public String toString(){
        return getSymbol();
    }
}
