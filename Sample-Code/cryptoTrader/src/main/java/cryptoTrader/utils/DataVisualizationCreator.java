package cryptoTrader.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import cryptoTrader.gui.MainUI;

public class DataVisualizationCreator {
	
	public void createCharts() {
		
		ExecuteTrade cumTrades = new ExecuteTrade();
		
		List<String[]> dataList = cumTrades.getCumulativeTrades();
		createTableOutput(dataList);
		createBar(dataList);
	}


	private void createTextualOutput() {
//		DefaultTableModel dtm = new  DefaultTableModel(new Object[] {"Broker Name", "Ticker List", "Strategy Name"}, 1);
//		JTable table = new JTable(dtm);
//		//table.setPreferredSize(new Dimension(600, 300));
//		dtm.e
//		JScrollPane scrollPane = new JScrollPane(table);
//		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
//                "Broker Actions",
//                TitledBorder.CENTER,
//                TitledBorder.TOP));
//		
//	
//		
//		scrollPane.setPreferredSize(new Dimension(800, 300));
//		table.setFillsViewportHeight(true);;
		
//		MainUI.getInstance().updateStats(scrollPane);
	}
	
	private void createTableOutput(List<List<String>> tradeActions) {
		// Dummy dates for demo purposes. These should come from selection menu
		Object[] columnNames = {"Trader","Strategy","Action", "CryptoCoin","Quantity","Price","Date"};
		
		// tradeActions format: {trader name, strategy, action, coin, quantity, price, date}
		String[][] data = new String[tradeActions.size()][7];
		for (int i=0; i < data.length; i++) {
			String[] currLine = tradeActions.get(i);
			data[i][0] = currLine[0];
			data[i][1] = currLine[1];
			data[i][2] = currLine[2];
			data[i][3] = currLine[3];
			data[i][4] = currLine[4];
			data[i][5] = currLine[5];
			data[i][6] = currLine[6];
		};
		

		JTable table = new JTable(data, columnNames);
		//table.setPreferredSize(new Dimension(600, 300));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Trader Actions",
                TitledBorder.CENTER,
                TitledBorder.TOP));
		
	
		
		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);;
		
		MainUI.getInstance().updateStats(scrollPane);
	}
	
	public void createBar(List<String[]> strategyFrequencies) {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for (int i=0; i < strategyFrequencies.size(); i++) {
			String[] tradeItem = strategyFrequencies.get(i);
			dataset.setValue(tradeItem[0], tradeItem[1], tradeItem[2]);
		};

		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Strategy");
		plot.setDomainAxis(domainAxis);
		LogAxis rangeAxis = new LogAxis("Actions(Buys or Sells)");
		rangeAxis.setRange(new Range(0.1, 20.0));
		plot.setRangeAxis(rangeAxis);

		//plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		//plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		JFreeChart barChart = new JFreeChart("Actions Performed By Traders So Far", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		MainUI.getInstance().updateStats(chartPanel);
	}

}
