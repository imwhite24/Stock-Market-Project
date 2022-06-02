import java.util.Comparator;
import java.util.PriorityQueue;

public class Stock {
	private String stockSymbol;
	private String companyName;
	private double highPrice;
	private double lowPrice;
	private double price;
	private int dayVolume;

	static java.text.DecimalFormat money;

	PriceComparator buy= new PriceComparator(true);

	PriorityQueue<TradeOrder> buyOrders= new PriorityQueue<TradeOrder>(new PriceComparator(true));
	PriorityQueue<TradeOrder> sellOrders= new PriorityQueue<TradeOrder>(new PriceComparator(false));

	public Stock(String symbol, String name, double p) {
		stockSymbol= symbol;
		companyName= name;
		highPrice=p;
		lowPrice=p;
		price=p;
		dayVolume= 0;

	}

	public TradeOrder getBuyOrder() {
		if(!buyOrders.isEmpty()) {
			return buyOrders.peek();
		}
		else {
			return null;
		}
	}

	public String getSymbol() {
		return stockSymbol;
	}

	public TradeOrder getSellOrder() {
		if(!sellOrders.isEmpty()) {
			return sellOrders.peek();
		}
		else {
			return null;
		}
	}

	public double getHighprice() {
		return highPrice;
	}

	public double getLowPrice() {
		return lowPrice;
	}

	public void placeOrder(TradeOrder tradeOrder) {
		double price;
		if(tradeOrder.isMarket()==true) {
			price= (highPrice+lowPrice)/2;
		}
		else {
			price= tradeOrder.getPrice();
		}
		if(tradeOrder.isBuy()==true) {

			buyOrders.add(tradeOrder);
			String confirmation= "Buy " + stockSymbol + " (" + companyName + ") \n" + tradeOrder.getShares()+ " shares at $"
					+ price;

			tradeOrder.getTrader().recieveMessage(confirmation);
		}
		else {
			sellOrders.add(tradeOrder);
			String confirmation= "Sell " + stockSymbol + " (" + companyName + ") \n" + tradeOrder.getShares()+ " shares at $"
					+ price;
			tradeOrder.getTrader().recieveMessage(confirmation);
		}
		executeOrders();
	}

	private void executeOrders() {
		while(!buyOrders.isEmpty() && !sellOrders.isEmpty()) {
			TradeOrder buy= buyOrders.peek();
			TradeOrder sell= sellOrders.peek();
			if(buy.isLimit()&& sell.isLimit()) {
				if(buy.getPrice()<=sell.getPrice()) {
					execute(buy, sell, sell.getPrice());
				}
			}
			else if(buy.isLimit()&& sell.isMarket()) {
				if(buy.getPrice()<=lowPrice) {
					execute(buy, sell, buy.getPrice());
				}
			}
			else if(buy.isMarket() && sell.isLimit()) {
				if(sell.getPrice()>=lowPrice) {

				}
			}
			else {
				execute(buy, sell, (highPrice + lowPrice)/2);
			}
		}
	}

	private void execute(TradeOrder buyOrder, TradeOrder sellOrder, double price2) {
		// TODO Auto-generated method stub
		int sharesExecuted;
		if(buyOrder.getShares()>=sellOrder.getShares()) {
			sharesExecuted=sellOrder.getShares();
		}
		else {
			sharesExecuted=buyOrder.getShares();
		}

		buyOrder.subtractShares(sharesExecuted);
		sellOrder.subtractShares(sharesExecuted);

		if(buyOrder.getShares()==0) {
			buyOrders.remove();
			String message = "Your trade has been executed";
			String partialMessage= sharesExecuted + " of your shares have been sold";
			buyOrder.getTrader().recieveMessage(message);
			sellOrder.getTrader().recieveMessage(partialMessage);

		}
		else {
			sellOrders.remove();
			String message = "Your trade has been executed";
			String partialMessage= sharesExecuted + " of your order have been bought";
			sellOrder.getTrader().recieveMessage(message);
			buyOrder.getTrader().recieveMessage(partialMessage);
		}

	}

	//	public String getQuote() {
	//		TradeOrder buyOrder= buyOrders.peek();
	//		TradeOrder sellOrder= sellOrders.peek();
	//
	//		if(buyOrder.getShares()!=0 || sellOrder.getShares()!=0) {
	//			return companyName+ " (" + stockSymbol + ") \n" + "Price: " + price+ " hi: " + highPrice + " low: " + lowPrice + 
	//					" volume: " + dayVolume + "Ask: " + sellOrder.getPrice()+ " size: " + sellOrder.getShares() + " Ask: " +
	//					buyOrder.getPrice() + " size: " + buyOrder.getShares();
	//		}
	//		else {
	//			return "ahhhhhh";
	//		}
	//	}

	public String getQuote()
	{
		String ans = companyName + " (" + stockSymbol + " \nPrice: " + price
				+ "  hi: " + highPrice + "  lo: " + lowPrice + "  vol: " + dayVolume
				+ "\nAsk: ";

		if ( sellOrders.isEmpty() )
		{
			ans += "none";
		}
		else if ( sellOrders.peek().isMarket() )
		{
			ans += "market" + " size: " + sellOrders.peek().getShares();
		}
		else
		{
			ans += sellOrders.peek().getPrice() + " size: "
					+ sellOrders.peek().getShares();
		}

		ans += "  Bid: ";

		if ( buyOrders.isEmpty() )
		{
			ans += "none";
		}
		else if ( buyOrders.peek().isMarket() )
		{
			ans += "market" + " size: " + buyOrders.peek().getShares();
		}
		else
		{
			ans += buyOrders.peek().getPrice() + " size: "
					+ buyOrders.peek().getShares();
		}

		return ans;
	}

}
