package cryptoTrader.utils;

import java.io.*;
import java.util.*;

public class TradeStrategy {
	
	public String[] getExecution(String strategy, String[] coins, String name) { //selection object
		List<String> TradeExecution = new ArrayList<>();
		TradeExecution.add(name);
		TradeExecution.add(strategy);
		
		DataFetcher coinGecko = new DataFetcher();
		List<Double> prices = new ArrayList<>();
		for(int i = 0; i < coins.length; i++) {
			prices.add(coinGecko.getPriceForCoin(coins[i], null)); // how to get date?!, format of id - bitcoin or BTC?
		}
		
		if(strategy == "Strategy-A") {
			TradeExecution = StrategyA(coins);
		} else if(strategy == "Strategy-B") {
			
		} else if(strategy == "Strategy-C") {
			
		} else if(strategy == "Strategy-D") {
			
		} else {
			
		}
	}
	
	private String[] StrategyA(String[] coins, List<Double> prices) {
		Double Bitcoin = null;
		Double Ethereum = null;
		List<String> execution = null;
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
				execution.add("buy");
				execution.add("ADA");
				execution.add("800");
				execution.add(Double.toString(cardano)); // how get date?!
			} else {
				execution.add("sell");
				execution.add("BTC");
				execution.add("2");
				execution.add(Double.toString(Bitcoin));
			}
			
		}
	}
}

// the trade strategy class

// takes string as input

// chooses strategy and executes it, conditions for trade must be selected coins
// but coin to buy/sell does not have to be


//A: if BTC > 46k and ETH < 3800 then buy 800 units Cardano, else sell 2 Bitcoin

//B: if Cardano > $1 then buy 10 LUNA, else sell 3 Bitcoin

//C: if ethereum > 3800 and Cardano <$1 buy 200 FTM, else sell 500 Cardano

//D: if price of Cardano > price FTM, then buy 100 FTM, else buy 100 Cardano