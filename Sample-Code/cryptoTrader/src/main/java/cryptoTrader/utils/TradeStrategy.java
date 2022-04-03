package cryptoTrader.utils;

import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

public class TradeStrategy {
	public List<String> getExecution(String strategy, String[] coins, String name) { //selection object
		List<String> TradeExecution = new ArrayList<>();
		TradeExecution.add(name);
		TradeExecution.add(strategy);
		
		DataFetcher coinGecko = new DataFetcher();
		List<Double> prices = new ArrayList<>();
		for(int i = 0; i < coins.length; i++) {
			//example parameters: ("bitcoin", "04-02-2022")
			prices.add(coinGecko.getPriceForCoin(coins[i], date())); 
		}
		
		if(strategy.equals("Strategy-A")) {
			TradeExecution = StrategyA(coins, prices);
		} else if(strategy.equals("Strategy-B")) {
			
		} else if(strategy.equals("Strategy-C")) {
			
		} else if(strategy.equals("Strategy-D")) {
			
		}
		
		return TradeExecution;
	}
	
	//class returns today's date such that when prices are added to String array coin, 
	//coin prices are up to date and accurate
	private static String date() {
		Format f = new SimpleDateFormat("MM-dd-20yy");
		String strDate = f.format(new Date());
		return (strDate);
	}
	
	//calls to coingecko api must use "bitcoin" and not "BTC"
	//method translates input from user into appropriate corresponding string
	//such that coingecko api returns accurate prices
	//TRANSLATE METHOD??!?! --> this section was added by karen
	
	//A: if BTC > 46k and ETH < 3800 then buy 800 units Cardano, else sell 2 Bitcoin
	private static List<String> StrategyA(String[] coins, List<Double> prices) {
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
		return execution;
	}

	//B: if Cardano > $1 then buy 10 LUNA, else sell 3 Bitcoin

	//C: if ethereum > 3800 and Cardano <$1 buy 200 FTM, else sell 500 Cardano

	//D: if price of Cardano > price FTM, then buy 100 FTM, else buy 100 Cardano
}
