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
			TradeExecution = StrategyB(coins, prices);
		} else if(strategy.equals("Strategy-C")) {
			TradeExecution = StrategyC(coins, prices);
		} else if(strategy.equals("Strategy-D")) {
			TradeExecution = StrategyD(coins, prices);
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
	
	//A: if Bitcoin (BTC) > 46k and Ethereum (ETH) < 3800 then buy 800 units Cardano (ADA), else sell 2 Bitcoin (BTC)
	private static List<String> StrategyA(String[] coins, List<Double> prices) {
		List<String> execution = null;
		DataFetcher coinGecko = new DataFetcher();
		
		Double Bitcoin = null;
		Double Ethereum = null;	
		
		for (int i = 0; i < coins.length; i++) {
			if("BTC".equals(coins[i])) {
				Bitcoin = coinGecko.getPriceForCoin("bitcoin", date());
			} else if("ETH".equals(coins[i])) {
				Ethereum = coinGecko.getPriceForCoin("ethereum", date());
			}
		}
		
		if (Bitcoin != null && Ethereum != null) { // if both coins are in the list of requested coins
			Double Cardano = coinGecko.getPriceForCoin("cardano", date());
			
			if(Bitcoin > 58000 && Ethereum < 4000) {
				execution.add("buy");
				execution.add("ADA");
				execution.add("800");
				execution.add(Double.toString(Cardano)); 
			} else {
				execution.add("sell");
				execution.add("BTC");
				execution.add("2");
				execution.add(Double.toString(Bitcoin));
			}
		}
		return execution;
	}

	//B: if Cardano (ADA) > $1 then buy 10 LUNA, else sell 3 Bitcoin
	private static List<String> StrategyB(String[] coins, List<Double> prices) {
		List<String> execution = null;
		DataFetcher coinGecko = new DataFetcher();
		
		Double Cardano = null;
		Double Luna = null;
		Double Bitcoin = null;		
		
		for (int i = 0; i < coins.length; i++) {
			//If Cardano can be found in request 
			if("ADA".equals(coins[i])) {
				Cardano = coinGecko.getPriceForCoin("cardano", date());
			} 
		}
		
		if (Cardano != null) { // if both coins are in the list of requested coins
			Bitcoin = coinGecko.getPriceForCoin("bitcoin", date());
			Luna = coinGecko.getPriceForCoin("terra-luna", date());
			
			if(Cardano > 1) {
				execution.add("buy");
				execution.add("LUNA"); //what is the shorthand 
				execution.add("10");
				execution.add(Double.toString(Luna)); 
			} else {
				execution.add("sell");
				execution.add("BTC");
				execution.add("3");
				execution.add(Double.toString(Bitcoin));
			}
		}
		return execution;
	}
	//C: if Ethereum (ETH) > $3800 and Cardano (ADA) < $1 buy 200 Fantom (FTM), else sell 500 Cardano (ADA)
	private static List<String> StrategyC(String[] coins, List<Double> prices) {
		List<String> execution = null;
		DataFetcher coinGecko = new DataFetcher();
		
		Double Ethereum = null;
		Double Cardano = null;	
		
		for (int i = 0; i < coins.length; i++) {
			if("ETH".equals(coins[i])) {
				Ethereum = coinGecko.getPriceForCoin("ethereum", date());
			} else if("ADA".equals(coins[i])) {
				Cardano = coinGecko.getPriceForCoin("cardano", date());
			}
		}
		
		if (Ethereum != null && Cardano != null) { // if both coins are in the list of requested coins
			Double Fantom = coinGecko.getPriceForCoin("fantom", date());	
			
			if(Ethereum > 38000 && Cardano < 1) {
				execution.add("buy");
				execution.add("FTM");
				execution.add("200");
				execution.add(Double.toString(Fantom)); 
			} else {
				execution.add("sell");
				execution.add("ADA");
				execution.add("500");
				execution.add(Double.toString(Cardano));
			}
		}
		return execution;
	}
	
	//D: if price of Cardano (ADA) > price Fantom (FTM), then buy 100 FTM, else buy 100 Cardano
	private static List<String> StrategyD(String[] coins, List<Double> prices) {
		List<String> execution = null;
		DataFetcher coinGecko = new DataFetcher();
		
		Double Cardano = null;
		Double Fantom = null;	
		
		for (int i = 0; i < coins.length; i++) {
			if("ADA".equals(coins[i])) {
				Cardano = coinGecko.getPriceForCoin("cardano", date());
			} else if("FTM".equals(coins[i])) {
				Fantom = coinGecko.getPriceForCoin("fantom", date());
			}
		}
		
		if (Cardano != null && Fantom != null) { // if both coins are in the list of requested coins			
			if(Cardano > Fantom) {
				execution.add("buy");
				execution.add("FTM");
				execution.add("100");
				execution.add(Double.toString(Cardano)); 
			} else {
				execution.add("buy");
				execution.add("ADA");
				execution.add("100");
				execution.add(Double.toString(Fantom));
			}
		}
		return execution;
	}
}