package cryptoTrader.utils;

// idk if i need these..?
import java.io.*;
import java.util.*;

/* 
 * current issues: 
 * BIG ISSUE - don't know what format to use the api (requires ID & date), but idk what id is supposed to be? i.e. BTC/Bitcoin, etc.) nor 
 * 			   how to get the date.
 * 
 * Secondary:
 * Coin prices - requested coins by brokers will need to have the api fetch their prices, then they are 'notified'. Do we make the API call 
 * 			     in this class or elsewhere and have it imported in? (like coin prices attached to Broker object)
 * When broker does not have all coins for strategy - will have to add nulls for remaining cols (coin, action, quantity, price, strategy?)
 * */

// returns the trade action of one broker at a time as an array for use by display table
public class TradeStrategy {
	private List<String> TradeExecution = new ArrayList<>();
	
	public String[] getExecution(String strategy, String[] coins, String name) { //selection object
		TradeExecution.add(name);
		TradeExecution.add(strategy);
		
		// gets the prices of each coin the broker listed
		DataFetcher coinGecko = new DataFetcher();
		List<Double> prices = new ArrayList<>();
		for(int i = 0; i < coins.length; i++) {
			prices.add(coinGecko.getPriceForCoin(coins[i], null)); // how to get date?!, format of id - bitcoin or BTC?
		}
		
		// calls the appropriate strategy method
		if(strategy == "Strategy-A") {
			StrategyA(coins, prices);
		} else if(strategy == "Strategy-B") {
			StrategyB(coins, prices);
		} else if(strategy == "Strategy-C") {
			StrategyC(coins, prices);
		} else if(strategy == "Strategy-D") {
			StrategyD(coins, prices);
		}
		
		// returns an array for the display table with action from broker.
		return TradeExecution.toArray(new String[TradeExecution.size()]);
	}

	//A: if BTC > 46k and ETH < 3800 then buy 800 units Cardano, else sell 2 Bitcoin
	private void StrategyA(String[] coins, List<Double> prices) {
		Double Bitcoin = null;
		Double Ethereum = null;
		for(int i = 0; i < coins.length; i++) {
			if("BTC" == coins[i]) {
				Bitcoin = prices.get(i);
			} else if("ETH" == coins[i]) {
				Ethereum = prices.get(i);
			}
		}
		if(Bitcoin != null && Ethereum != null) { // if both coins are in the list of requested coins
			DataFetcher coinGecko = new DataFetcher();

			Double cardano = coinGecko.getPriceForCoin("cardano", null);
			if(Bitcoin > 58000 && Ethereum < 4000) {
				TradeExecution.add("buy");
				TradeExecution.add("ADA");
				TradeExecution.add("800");
				TradeExecution.add(Double.toString(cardano)); // how get date?!
			} else {
				TradeExecution.add("sell");
				TradeExecution.add("BTC");
				TradeExecution.add("2");
				TradeExecution.add(Double.toString(Bitcoin));
			}
			
		}
	}

	//B: if Cardano > $1 then buy 3000 Dogecoin, else sell 3 Bitcoin
	private void StrategyB(String[] coins, List<Double> prices) {
		Double Cardano = null;
		for(int i = 0; i < coins.length; i++) {
			if("ADA" == coins[i]) {
				Cardano = prices.get(i);
				break;
			} 
		}
		if(Cardano != null) { // if both coins are in the list of requested coins
			DataFetcher coinGecko = new DataFetcher();

			Double Dogecoin = coinGecko.getPriceForCoin("dogecoin", null);
			if(Cardano > 1.40) {
				TradeExecution.add("buy");
				TradeExecution.add("DOGE");
				TradeExecution.add("3000");
				TradeExecution.add(Double.toString(Dogecoin)); // how get date?!
			} else {
				TradeExecution.add("sell");
				TradeExecution.add("BTC");
				TradeExecution.add("3");
				TradeExecution.add(Double.toString(Cardano));
			}
			
		}
	}
	
	//C: if ethereum > 3800 and Cardano <$1.40 buy 200 FTM, else sell 500 Cardano
	private void StrategyC(String[] coins, List<Double> prices) {
		Double Cardano = null;
		Double Ethereum = null;
		for(int i = 0; i < coins.length; i++) {
			if("ADA" == coins[i]) {
				Cardano = prices.get(i);
			} else if("ETH" == coins[i]) {
				Ethereum = prices.get(i);
			}
		}
		if(Cardano != null && Ethereum != null) { // if both coins are in the list of requested coins
			DataFetcher coinGecko = new DataFetcher();

			Double Fantom = coinGecko.getPriceForCoin("fantom", null);
			if(Cardano < 1.40 && Ethereum > 3800) {
				TradeExecution.add("buy");
				TradeExecution.add("FTM");
				TradeExecution.add("200");
				TradeExecution.add(Double.toString(Fantom)); // how get date?!
			} else {
				TradeExecution.add("sell");
				TradeExecution.add("ADA");
				TradeExecution.add("500");
				TradeExecution.add(Double.toString(Cardano));
			}
			
		}
	}
	
	//D: if price of Cardano > price FTM, then buy 100 FTM, else buy 100 Cardano
	private void StrategyD(String[] coins, List<Double> prices) {
		Double Cardano = null;
		Double Fantom = null;
		for(int i = 0; i < coins.length; i++) {
			if("ADA" == coins[i]) {
				Cardano = prices.get(i);
			} else if("FTM" == coins[i]) {
				Fantom = prices.get(i);
			}
		}
		if(Cardano != null && Fantom != null) { // if both coins are in the list of requested coins
			if(Cardano > Fantom) {
				TradeExecution.add("buy");
				TradeExecution.add("FTM");
				TradeExecution.add("100");
				TradeExecution.add(Double.toString(Fantom)); // how get date?!
			} else {
				TradeExecution.add("buy");
				TradeExecution.add("ADA");
				TradeExecution.add("100");
				TradeExecution.add(Double.toString(Cardano));
			}
			
		}
	}
}


// chooses strategy and executes it, conditions for trade must be selected coins
// but coin to buy/sell does not have to be