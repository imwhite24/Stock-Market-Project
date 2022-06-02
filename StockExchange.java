import java.util.HashMap;

public class StockExchange {
	HashMap<String, Stock> listedStocks= new HashMap<String, Stock>();
	
	
	public String getQuote(String symbol) {
		if(listedStocks.containsKey(symbol)) {
			return listedStocks.get(symbol).getQuote();
		}
		else {
			return null;
		}
	}
	
	public Stock getStock(String symbol) {
		return listedStocks.get(symbol);
	}
	
	
	public void listStock(String symbol, String name, double price) {
		Stock newStock= new Stock(symbol, name, price);
		listedStocks.put(symbol, newStock);
	}
	
	public void placeOrder(TradeOrder tradeOrder) {
		String symbol= tradeOrder.getSymbol();
		listedStocks.get(symbol).placeOrder(tradeOrder);
		
	}
	
}
