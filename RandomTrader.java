
public class RandomTrader extends Trader{

	int money;
	int desire;
	Stock assignedStock;
	
	public RandomTrader(Brokerage broker, String name, String pswd, int money, int moneyDesired, String stock) {
		super(broker, name, pswd);
		this.money=money;
		assignedStock= broker.getStock(stock);
		// TODO Auto-generated constructor stub
	}
	
	
	public void makeTrades() {
	
		if(money>desire) {
			if(assignedStock.getBuyOrder()!=null) {
				double changeHi= assignedStock.getHighprice()*.2;
				double changeLo= assignedStock.getLowPrice()*-.2;
				double price= (Math.random()*changeHi-changeLo)+changeLo;
				int numShares= (int) (money/price);
				TradeOrder tradeOrder= new TradeOrder(this, assignedStock.getSymbol(), true, false, numShares, price);
				super.getBroker().placeOrder(tradeOrder);
				assignedStock.getBuyOrder().getTrader().recieveMessage("Your order has been taken");
				}
			else if(assignedStock.getSellOrder()!=null) {
				double changeHi= assignedStock.getHighprice()*.2;
				double price= (Math.random()*changeHi-assignedStock.getLowPrice())+assignedStock.getLowPrice();
				int numShares= (int) (money/price);
				TradeOrder tradeOrder= new TradeOrder(this, assignedStock.getSymbol(), false, false, numShares, price);
				super.getBroker().placeOrder(tradeOrder);
				}

			}
		else {
			if(assignedStock.getSellOrder()!=null) {
				double changeHi= assignedStock.getHighprice()*.2;
				double price= (Math.random()*changeHi-assignedStock.getLowPrice())+assignedStock.getLowPrice();
				int numShares= (int) (money/price);
				TradeOrder tradeOrder= new TradeOrder(this, assignedStock.getSymbol(), false, false, numShares, price);
				super.getBroker().placeOrder(tradeOrder);
				}
			else if(assignedStock.getBuyOrder()!=null) {
				double changeHi= assignedStock.getHighprice()*.2;
				double changeLo= assignedStock.getLowPrice()*-.2;
				double price= (Math.random()*changeHi-changeLo)+changeLo;
				int numShares= (int) (money/price);
				TradeOrder tradeOrder= new TradeOrder(this, assignedStock.getSymbol(), true, false, numShares, price);
				super.getBroker().placeOrder(tradeOrder);
				}
		}
				
	}

}
