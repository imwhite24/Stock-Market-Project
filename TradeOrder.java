//Finished
public class TradeOrder {

	private Trader trader;
	private String stockSymbol;
	private boolean buyOrder;
	private boolean marketOrder;
	private boolean limitOrder;
	private int numShares;
	private double price;
	
	public TradeOrder(Trader trader, String symbol, boolean buyOrder, boolean marketOrder, int numShares, double price) {
		this.trader=trader;
		stockSymbol=symbol;
		this.buyOrder=buyOrder;
		this.marketOrder=marketOrder;
		this.numShares=numShares;
		this.price=price;
		if(marketOrder== false) {
			limitOrder=true;
		}
	}
	
	public Trader getTrader() {
		return trader;
	}
	
	public String getSymbol() {
		return stockSymbol;
	}
	
	public boolean isBuy() {
		return buyOrder;
	}
	
	public boolean isSell() {
		if(buyOrder==false) {
			return true;
		}
		return false;
	}
	
	public boolean isLimit() {
		return limitOrder;
	}
	
	public boolean isMarket() {
		return marketOrder;
	}
	
	public int getShares() {
		return numShares;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void subtractShares(int shares) {
		if(numShares>=shares) {
			numShares-= shares;
		}
	}
	
}
