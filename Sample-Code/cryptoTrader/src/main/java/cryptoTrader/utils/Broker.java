package cryptoTrader.utils;

/**
 * class for broker object
 */
public class Broker {
	private String name;
	private String[] coinList;
	private String strategy;
	
	/**
	 * Constructor class
	 * @param name		name of broker
	 * @param strategy	strategy of broker
	 * @param coinList	list of coins for broker
	 */
	public Broker(String name, String strategy, String[] coinList){
		this.name = name;
		this.strategy = strategy;
		this.coinList = coinList;
	}
	
	/**
	 * getter class for broker name
	 * @return name	name of broker
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * getter class for broker strategy
	 * @return strategy	strategy of broker
	 */
	public String getStrategy() {
		return strategy;
	}
	
	/**
	 * getter class for coinList
	 * @return coinList	list of coin of the broker 
	 */
	public String[] getCoinList() {
		return coinList;
	}
}
