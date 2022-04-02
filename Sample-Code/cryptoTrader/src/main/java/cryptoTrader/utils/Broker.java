package cryptoTrader.utils;

public class Broker {
	private String name;
	private String[] coinList;
	private String strategy;
	
	public Broker(String name, String strategy, String[] coinList){
		this.name = name;
		this.strategy = strategy;
		this.coinList = coinList;
	}
	
	public String getName() {
		return name;
	}
	
	public String getStrategy() {
		return strategy;
	}
	
	public String[] getCoinList() {
		return coinList;
	}
}
