import java.util.Comparator;
//finished
public class PriceComparator implements Comparator<TradeOrder> {
boolean ascending;
	
	public PriceComparator() {
		ascending=true;
	}
	
	public PriceComparator(boolean asc) {
		ascending=asc;
	}
	
	public int compare(TradeOrder stock1, TradeOrder stock2) {
		if(stock1.isMarket()== true && stock2.isMarket() == true) {
			return 0;
		}
		else if(stock1.isMarket() && stock2.isLimit()){
			return -1;
		}
		else if(stock1.isLimit() && stock2.isMarket()) {
			return 1;
		}
		else{
			int cents1= (int) ((stock1.getPrice()%1)*100);
			int cents2= (int) ((stock2.getPrice()%1)*100);
			if(ascending== true) {
				return cents1-cents2;
			}
			else {
				return cents2-cents1;
			}
		}
	}
}
