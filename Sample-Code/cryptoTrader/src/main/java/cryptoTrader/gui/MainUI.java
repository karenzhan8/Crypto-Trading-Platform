package cryptoTrader.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import utils.DataVisualizationCreator;
import utils.ExecuteTrade;
import utils.LogIn;
import utils.UserSelection;

import javax.swing.JTextField;
import javax.swing.JLabel;

import utils.DataVisualizationCreator;

public class MainUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static MainUI instance;
	private JPanel stats, chartPanel, tablePanel;

	// Should be a reference to a separate object in actual implementation
	private List<String> selectedList;

	private JTextArea selectedTickerList;
//	private JTextArea tickerList;
	private JTextArea tickerText;
	private JTextArea BrokerText;
	private JComboBox<String> strategyList;
	private Map<String, List<String>> brokersTickers = new HashMap<>();
	private Map<String, String> brokersStrategies = new HashMap<>();
	private List<String> selectedTickers = new ArrayList<>();
	private String selectedStrategy = "";
	private DefaultTableModel dtm;
	private JTable table;
	private JTextField Name;
	private JTextField Coin;
	private JTextField Strategy;
	
	private static JLabel passwordLabel, label;
	private static JTextField username;
	private static JPasswordField password;
	private static JButton button;
	
	// stores database of brokers
	UserSelection brokerDatabase = new UserSelection();
	// stores list of cumulative trades
	ExecuteTrade cumulativeTrades = new ExecuteTrade();

	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}

	private MainUI() {

		// Set window title
		super("Crypto Trading Tool");

		// Set top bar

		JPanel north = new JPanel();
		
		//facilitate log in and verifying credentials
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JFrame frame = new JFrame();
		frame.setTitle("Login");
		frame.add(panel);
		frame.setSize(new Dimension (400, 200));
		
		label = new JLabel ("Username");
		label.setBounds(100, 8, 70, 20);
		panel.add(label);
		
		username = new JTextField();
		username.setBounds(100, 27, 193, 28);;
		panel.add(username);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(100, 55, 70, 20);
		panel.add(passwordLabel);
		
		password = new JPasswordField();
		password.setBounds(100, 75, 193, 28);
		panel.add(password);
		
		button = new JButton("Login");
		button.setBounds(100, 110, 90, 25);
		button.addActionListener(this);
		panel.add(button);
		
		frame.setVisible(true);
	
//
//		north.add(strategyList);
//
//		// Set bottom bar
//		JLabel from = new JLabel("From");
//		UtilDateModel dateModel = new UtilDateModel();
//		Properties p = new Properties();
//		p.put("text.today", "Today");
//		p.put("text.month", "Month");
//		p.put("text.year", "Year");
//		JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);
//		@SuppressWarnings("serial")
//		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new AbstractFormatter() {
//			private String datePatern = "dd/MM/yyyy";
//
//			private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePatern);
//
//			@Override
//			public Object stringToValue(String text) throws ParseException {
//				return dateFormatter.parseObject(text);
//			}
//
//			@Override
//			public String valueToString(Object value) throws ParseException {
//				if (value != null) {
//					Calendar cal = (Calendar) value;
//					return dateFormatter.format(cal.getTime());
//				}
//
//				return "";
//			}
//		});

		JButton trade = new JButton("Perform Trade");
		trade.setActionCommand("refresh");
		trade.addActionListener(this);

		JPanel south = new JPanel();
		
		south.add(trade);

		dtm = new DefaultTableModel(new Object[] { "Trading Client", "Coin List", "Strategy Name" }, 0);
		table = new JTable(dtm);
		// table.setPreferredSize(new Dimension(600, 300));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Trading Client Actions",
				TitledBorder.CENTER, TitledBorder.TOP));
		Vector<String> strategyNames = new Vector<String>();
		strategyNames.add("None");
		strategyNames.add("Strategy-A");
		strategyNames.add("Strategy-B");
		strategyNames.add("Strategy-C");
		strategyNames.add("Strategy-D");
		TableColumn strategyColumn = table.getColumnModel().getColumn(2);
		JComboBox comboBox = new JComboBox(strategyNames);
		strategyColumn.setCellEditor(new DefaultCellEditor(comboBox));
		JButton addRow = new JButton("Add Row");
		JButton remRow = new JButton("Remove Row");
		addRow.setActionCommand("addTableRow");
		addRow.addActionListener(this);
		remRow.setActionCommand("remTableRow");
		remRow.addActionListener(this);

		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);
		

		JPanel east = new JPanel();
//		east.setLayout();
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
//		east.add(table);
		east.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Enter Broker Name:");
		east.add(lblNewLabel);
		
		Name = new JTextField();
		east.add(Name);
		Name.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Coin(s):");
		east.add(lblNewLabel_1);
		
		Coin = new JTextField();
		east.add(Coin);
		Coin.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Strategy:");
		east.add(lblNewLabel_2);
		
		Strategy = new JTextField();
		east.add(Strategy);
		Strategy.setColumns(10);
		Strategy.setText("Strategy-");
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(addRow);
		buttons.add(remRow);
		east.add(buttons);
//		east.add(selectedTickerListLabel);
//		east.add(selectedTickersScrollPane);

		// Set charts region
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(1250, 650));
		stats = new JPanel();
		stats.setLayout(new GridLayout(2, 2));

		west.add(stats);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(west, BorderLayout.CENTER);
		getContentPane().add(south, BorderLayout.SOUTH);
//		getContentPane().add(west, BorderLayout.WEST);
	}

	public void updateStats(JComponent component) {
		stats.add(component);
		stats.revalidate();
	}

	public static void main(String[] args) {
		JFrame frame = MainUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if ("refresh".equals(command)) { // after perform trade, then start getting each selection broker
			for (int count = 0; count < dtm.getRowCount(); count++){
					Object traderObject = dtm.getValueAt(count, 0); 
					if (traderObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in Trader name on line " + (count + 1) );
						return;
					} // CHECK HERE IF NAME MATCHES A PREVIOUS BROKER NAME
					String traderName = traderObject.toString(); // traderName string
					Object coinObject = dtm.getValueAt(count, 1); 
					if (coinObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in cryptocoin list on line " + (count + 1) );
						return;
					}
					String[] coinNames = coinObject.toString().split(","); // list of coins
					Object strategyObject = dtm.getValueAt(count, 2);
					if (strategyObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in strategy name on line " + (count + 1) );
						return;
					}
					String strategyName = strategyObject.toString(); // strategy name string
					System.out.println(traderName + " " + Arrays.toString(coinNames) + " " + strategyName); // could use here to make selection object
	        }
			cumulativeTrades.performTrade(brokerDatabase);
			stats.removeAll();
			DataVisualizationCreator creator = new DataVisualizationCreator();
			creator.createCharts(cumulativeTrades.getCumulativeTrades(), histoList);
		} else if ("addTableRow".equals(command)) {
			String[] coinList = Coin.getText().split(",");
			String[] newRow = {Name.getText(), Coin.getText(), Strategy.getText()};
			brokerDatabase.addBroker(newRow[0], newRow[2], coinList);
			dtm.addRow(newRow); 
		} else if ("remTableRow".equals(command)) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				dtm.removeRow(selectedRow); // connect to remove the selected row data from here (THIS IS PERFORMED AFTER TRADE BUTTON HIT)
			}
		} else if ("login".equals(command)) {
			LogIn database = new LogIn();
			
			String user = username.getText();
			String pass = password.getText();
			
			if (database.verify(user, pass)) {
				JOptionPane.showMessageDialog(null,  "Login Successful");
			}
			else {
				System.exit(1);
			}
		}
	}

}
