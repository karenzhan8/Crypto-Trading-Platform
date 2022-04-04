package cryptoTrader.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * class to get list of available crypto
 */
public class AvailableCryptoList {
	private static AvailableCryptoList instance = null;
	
	private Map<String, String> tickerIDMap = new HashMap<>();
	
	private List<String> availableCryptosList = new ArrayList<>();
	
	/**
	 * Creates a global point of access to AvailableCryptoList using singleton design pattern  
	 */
	public static AvailableCryptoList getInstance() {
		if (instance == null)
			instance = new AvailableCryptoList();
		
		return instance;
	}
	
	/**
	 * calls findAvailableCryptos()
	 */
	private AvailableCryptoList() {
		findAvailableCryptos();
	}
	
	/**
	 * trys to connect to URL and throws IOException if not
	 */
	public void call() {
		String urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=IBM&apikey=VNEY4VV2AWF1EB51";
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				System.out.println(inline);
//				JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
//				int size = jsonArray.size();
//				
//				String name, id;
//				for (int i = 0; i < size; i++) {
//					JsonObject object = jsonArray.get(i).getAsJsonObject();
//					name = object.get("name").getAsString();
//					id = object.get("id").getAsString();
//					
//					availableCryptosMap.put(name, id);
//					availableCryptosList.add(name);
//				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block e.printStackTrace();
		}
	}
	
	/**
	 * gets available crypto data
	 */
	private void findAvailableCryptos() {

		String urlString = 
				"https://api.coingecko.com/api/v3/coins/markets" + 
						"?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false";
//		ALPHAVANTAGE API KEY = VNEY4VV2AWF1EB51
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
				int size = jsonArray.size();
				
				String name, id, symbol;
				for (int i = 0; i < size; i++) {
					JsonObject object = jsonArray.get(i).getAsJsonObject();
					name = object.get("name").getAsString();
					id = object.get("id").getAsString();
					symbol = object.get("symbol").getAsString();
				
					
					tickerIDMap.put(symbol, id);
					
					availableCryptosList.add(symbol);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block e.printStackTrace();
		}
	}
	
	/**
	 * returns an array of available crypto
	 * @return array of available crypto
	 */
	public String[] getAvailableCryptos() {
		return availableCryptosList.toArray(new String[availableCryptosList.size()]);
	}
	
	/**
	 * returns coin if it is available
	 * @param coinList	array of coins
	 * @return		coin only if it is available or else returns null
	 */
	public String coinAvailable(String[] coinList) {
		
		String[] availableCoins = availableCryptosList.toArray(new String[availableCryptosList.size()]);
		
		for (int i=0; i < coinList.length; i++) {
			if (!Arrays.asList(availableCoins).contains(coinList[i].toLowerCase())) {
				return coinList[i];
			}
		}
		
		return null;
	}
	
	/**
	 * returns crypto ID from given ticker
	 * @param tickerName	name of the ticker
	 * @return	crypto ID
	 */
	public String getCryptoIDfromTicker(String tickerName) {
		return tickerIDMap.get(tickerName.toLowerCase());
	}
	

}
