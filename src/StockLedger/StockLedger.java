package StockLedger;


import java.util.ArrayList;

public class StockLedger implements StockLedgerInterface{

    ArrayList<LedgerEntry> database;    //storage

    double profit;  //profit for selling

    public StockLedger(){
        database = new ArrayList<>();
        profit = 0;
    }


    /**
     *
     * {{@inheritDoc}}
     */
    @Override
    public void buyStock(String name, double cost, int quantity) {

        int val = getIndex(name);

        if(val == -1) {
            database.add(new LedgerEntry(name));
            val = database.size()-1;
        }

        for(int i = 0; i < quantity; i++){
            database.get(val).buyStock(cost);
        }
    }

    /**
     *{{@inheritDoc}}
     */
    @Override
    public double sellStock(String name, int quantity, double value) {

        double profit = 0;

        int val = getIndex(name);

        if(val == -1) throw new IllegalArgumentException("Symbol not found");

        profit += database.get(val).sellStock(quantity,value);

        this.profit += profit;  //ensures object profit

        return profit;
    }

    /**
     *{{@inheritDoc}}
     */
    @Override
    public String getSymbol() {
        return null;
    }

    /**
     * Display the stock ledger
     */
    public void display() {
        System.out.println("---- Stock Ledger ----");
        for (int i = 0; i < database.size(); i++){
            System.out.println(database.get(i).display());
        }
        System.out.println("\n\tProfit : "+profit+"\n");
    }

    /**
     * Retrieves the index of the Ledger entry with the symbol name
     * @param name
     * @return
     */
    private int getIndex(String name){
        for(int i = 0; i < database.size(); i++){
            if(database.get(i).getSymbol().toUpperCase().equals(name.toUpperCase())) return i;
        }
        return -1;
    }
}
