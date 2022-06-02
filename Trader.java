import java.util.ArrayList;

public class Trader implements Comparable<Trader> {

	private Brokerage broker;
	private String name;
	private String password;
	private TraderWindow myWindow;
	
	ArrayList<String> mailbox= new ArrayList<String>();
	
	public Trader(Brokerage broker, String name, String pswd) {
		this.broker=broker;
		this.name=name;
		password=pswd;
	}
	
	public int compareTo(Trader other) {
		return (name.compareToIgnoreCase(other.getName()));
	}
	
	public String getName() {
		return name;
	}
	
	public Brokerage getBroker() {
		return broker;
	}
	
	public boolean equals(Trader other) {
		if(name.equalsIgnoreCase(other.getName())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isEmpty() {
		if(mailbox.size()==0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getPassword() {
		return password;
	}
	
	//come back to after I finish brokerage
	public void getQuote(String symbol) {
		broker.getQuote(symbol, this);
	}
	
	public void openWindow() {
		myWindow= new TraderWindow(this);
		if(mailbox.size()!=0) {
			for(String msg: mailbox) {
				myWindow.showMessage(msg);
			}
		}
	}
	
	public void recieveMessage(String message) {
		mailbox.add(message);
		if(myWindow!=null) {
			for(String msg: mailbox) {
				myWindow.showMessage(msg);
			}
		}
	}
	
	public void quit() {
		broker.logout(this);
		myWindow=null;
	}
	
	public void placeOrder(TradeOrder tradeOrder) {
		broker.placeOrder(tradeOrder);
	}
		
}
