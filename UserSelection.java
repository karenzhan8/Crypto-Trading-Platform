package cryptoTrader.utils;

import java.util.ArrayList;

public class UserSelection {
	private ArrayList<Broker> brokerList;
	private ArrayList<String> strategyList;
	private ArrayList<String[]> coinsList;
	
	public UserSelection() {
        brokerList = new ArrayList<Broker>(); 
		strategyList = new ArrayList<String>();
		coinsList = new ArrayList<String[]>();
	}
	
	public void addBroker(Broker name, String strategy, String[] coinList) {
		if (!brokerList.contains(name)) { //if broker is not in list yet
			brokerList.add(name);
			strategyList.add(strategy);
	        coinsList.add(coinList);
		} else {
			System.out.println("Broker already in list");
		}
	}
	
	public void removeBroker(String name) {
		if (brokerList.contains(name)) {//check if broker is in list
			int index = brokerList.indexOf(name);
			strategyList.remove(index);
			coinsList.remove(index);
			brokerList.remove(index);
			
		} else {
			System.out.println("Broker not in list");
		}
	}
	
    public ArrayList<Broker> getBrokerList() {
        return brokerList;
    }
    public ArrayList<String> getStrategyList() {
        return strategyList;
    }
    
    public ArrayList<String[]> getCoinLists() {
        return coinsList;
    }
}
