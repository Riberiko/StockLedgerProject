import StockLedger.StockLedger;

public class Main {


    public static void main(String[] args) {

        StockLedger client = new StockLedger();

        client.buyStock("AAPL", 45, 20);
        client.buyStock("AAPL", 75, 20);
        client.buyStock("MSFT", 95, 20);
        client.display();

        client.sellStock("AAPL", 30, 60);
        client.display();

        client.sellStock("AAPL", 10, 65);
        client.display();

        client.buyStock("AAPL", 20, 100);
        client.buyStock("appl", 20,24);
        client.buyStock("TSLA", 36, 200);
        client.display();

        client.sellStock("appl", 10, 65);
        client.display();

        client.buyStock("tsla", 30, 150);
        client.display();

        client.buyStock("msft", 60, 5);
        client.buyStock("msft", 70, 5);
        client.display();

        client.sellStock("msft", 4, 30);
        client.display();

        client.sellStock("msft", 2, 30);
        client.display();

    }

}
