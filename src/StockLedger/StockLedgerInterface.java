package StockLedger;

public interface StockLedgerInterface {

    /**
     * Buys the stocks
     *
     * @param name  of the stock
     * @param cost  of the stock
     * @param quantity  of the stocks to purchase
     */
    public void buyStock(String name, double cost, int quantity);

    /**
     * Sells the stocks
     *
     * @param name  of the stock
     * @param quantity  of the stock
     * @param cost  of the stock
     * @return  the profits from selling
     */
    public double sellStock(String name, int quantity, double cost);

    /**
     * The symbol for the stock
     *
     * @return
     */
    public String getSymbol();

    /**
     * Text representation for the stock
     */
    public void display();
}
