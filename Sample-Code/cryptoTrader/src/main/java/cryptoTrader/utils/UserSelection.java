package cryptoTrader.utils;

import java.util.ArrayList;

/**
 *stores and gives access to list of all brokers, strategy and coin list
 *lets you add or remove a broker
 */
public class UserSelection {
	private ArrayList<Broker> brokerList;
	private ArrayList<String> strategyList;
	private ArrayList<String[]> coinsList;
	private int numBrokers;
	private ArrayList<Integer> freqList;
	
	/**
	 * Constructor class
	 * Initializes brokerList, strategyList, coinsList, and numBrokers
	 */
	public UserSelection() {
        	brokerList = new ArrayList<Broker>(); 
		strategyList = new ArrayList<String>();
		coinsList = new ArrayList<String[]>();
		numBrokers = 0;
		freqList = new ArrayList<Integer>();
	}
		/**
	 * Adds broker and updates numBrokers or prints message indicating that broker is already in list
	 * @param name		name of broker to be added
	 * @param strategy	strategy for broker to be added
	 * @param coinList	coinList for broker to be added
	 */
	public void addBroker(String name, String strategy, String[] coinList) {
		if (!brokerList.contains(name)) { //if broker is not in list yet
			
			Broker newBroker = new Broker(name, strategy, coinList);
			
			brokerList.add(newBroker);
			strategyList.add(strategy);
			numBrokers++;
	        	coinsList.add(coinList);
		} else {
			//System.out.println("Broker already in list");
			int index = brokerList.indexOf(name);
			int value = freqList.get(index);
			value++;
			freqList.set(index, value);
		}
	}
	
	public void addBroker(String name, String strategy, String[] coinList, int freq) {
		if (!brokerList.contains(name)) { //if broker is not in list yet
			
			Broker newBroker = new Broker(name, strategy, coinList);
			
			brokerList.add(newBroker);
			strategyList.add(strategy);
			numBrokers++;
	        	coinsList.add(coinList);
	        	freqList.add(freq);
		} else {
			System.out.println("Broker already in list");
		}
	}
	
	/**
	 * removes broker and updates numBrokers or prints out message indicating that broker to be removed is not in list
	 * @param name	broker name to be removed
	 */
	public void removeBroker(String name) {
		if (brokerList.contains(name)) {//check if broker is in list
			int index = brokerList.indexOf(name);
			strategyList.remove(index);
			coinsList.remove(index);
			brokerList.remove(index);
			numBrokers--;
			freqList.removie(index)
		} else {
			System.out.println("Broker not in list");
		}
	}

	
	/**
	 * getter class for brokerList
	 * @return brokerList
	*/
    public ArrayList<Broker> getBrokerList() {
        return brokerList;
    }
	
	/**
     * getter class for strategyList
     * @return strategyList
     */
    public ArrayList<String> getStrategyList() {
        return strategyList;
    }
    
	    /**
     * getter class for coinsList
     * @return coinsList
     */
    public ArrayList<String[]> getCoinLists() {
        return coinsList;
    }
	
	    /**
     * getter class for number of brokers
     * @return numBrokers
     */
    public int getNumBrokers() {
	    return numBrokers;
    }
	
    public ArrayList<Integer> getFreqList(){
    	return freqList;
    }
    
	/**
     * checks if broker in dataBase and returns the name if in database
     * @return 	name of broker if in Database or null if not in database
     */
    public String inDatabase(String name) {
    	
    	if (brokerList.contains(name)) {
    		return name;
    	} else {
    		return null;
    	}
    	
    }
	    
}
