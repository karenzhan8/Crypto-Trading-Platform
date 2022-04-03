package main.java.cryptoTrader.utils;

import java.util.ArrayList;
import java.util.List;

/*
 *  contains a cumulative list of all trade actions performed thus far, updated after every trade performed;
 *  each time a trade is performed:
 *  	1. the trade is added to this cumulative list
 *  	2. the cumulative list is sent to DataVisulationCreator to create an updated histogram and table 
 *  		- this is done by fetching (with getter method) getCumulativeTrades to use as argument when createCharts is called in MainUI
 */
public class ExecuteTrade {

	List<String[]> cumulativeTrades = new ArrayList<String[]>();
	TradeStrategy trader = new TradeStrategy(); // used to perform trades
	
	public void executeTrade(String strategy, String[] coins, String name) {
		
		cumulativeTrades.add(trader.getExecution(strategy, coins, name));
		
	}
	
	public List<String[]> getCumulativeTrades () {
		
		return cumulativeTrades;
		
	}
	
}
