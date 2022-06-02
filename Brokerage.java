import java.util.TreeMap;

public class Brokerage implements Login {
	private TreeMap<String, Trader> registeredTraders= new TreeMap<String, Trader>();
	private TreeMap<String, Trader> loggedInTraders= new TreeMap<String, Trader>();
	StockExchange exchange;
	
	public Brokerage(StockExchange stockExchange) {
		exchange=stockExchange;
	}
	
	public int addUser(String name, String pswrd) {
		if(name.length()<4 || name.length()>10) {
			return -1;
		}
		else if(pswrd.length()<2 || pswrd.length()>10) {
			return -2;
		}
		else if(registeredTraders.containsKey(name)) {
			return -3;
		}
		else {
			Trader created= new Trader(this, name, pswrd);
			registeredTraders.put(name, created);
			return 0;
		}
	}
	
	public void addRandomUser(String name, RandomTrader bot) {
		loggedInTraders.put(name, bot);
	}
	
	public int login(String username, String password) {
		if(!registeredTraders.containsKey(username)) {
			return -1;
		}
		else if(!(registeredTraders.get(username).getPassword().equals(password))) {
			return -2;
		}
		else if(loggedInTraders.containsKey(username)) {
			return -3;
		}
		else {
			loggedInTraders.put(username, registeredTraders.get(username));
			if(registeredTraders.get(username).isEmpty()) {
				registeredTraders.get(username).recieveMessage("Welcome to Safe Trade!");
				registeredTraders.get(username).openWindow();
			}
			else {
				registeredTraders.get(username).openWindow();
			}
			return 0;
		}
	}
	
	public void logout(Trader trader) {
		loggedInTraders.remove(trader.getName());
	}
	
	public void placeOrder(TradeOrder tradeOrder) {
		exchange.placeOrder(tradeOrder);
	}
	
	public void getQuote(String symbol, Trader trader) {
		trader.recieveMessage(exchange.getQuote(symbol));
	}
	
	public Stock getStock(String symbol) {
		return exchange.getStock(symbol);
	}
	
}
