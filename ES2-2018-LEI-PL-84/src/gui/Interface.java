package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import objects.Variable;

public class Interface {
	
	private JFrame frame;
	private Dimension screenResolution;
	private Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
	private JPanel northPanel ;
	private JPanel centerPanel;
	private JPanel centerLeftPanel;
	private JButton loadButton;
	private JButton saveButton;
	private JButton addVarButton;
	private JButton deleteVarButton;
	private JButton addCritButton;
	private JButton deleteCritButton;
	private DefaultTableModel varTableModel;
	private DefaultTableModel critTableModel;
	private JTable varTable;
	private TableColumn varTableColumn;
	private JTable critTable;
	private JLabel emailLabel;
	private JTextField emailField;
	private JButton runButton;
	private JButton sendEmailButton;
	private JButton FAQButton;
	private JLabel nameLabel;
	private JTextField nameText;
	private JLabel descLabel;
	private JTextArea descText;
	private JTextField varField;
	private JComboBox opBox;
	private JComboBox typeBox;
	private JTextField valueField;
	private JScrollPane critPane;
	
	public Interface() {
		frame = new JFrame("ES2 Project");
		screenResolution = Toolkit.getDefaultToolkit().getScreenSize();
		centerLeftPanel = new JPanel(new GridLayout(4,1));
		FAQButton = new JButton("F.A.Q");
		loadButton = new JButton("Load Problem");
		saveButton = new JButton("Save Problem");
		addVarButton = new JButton("Add Variable");
		deleteVarButton = new JButton("Delete Variable");
		addCritButton = new JButton("Add Criterium");
		deleteCritButton = new JButton("Delete Criterium");
		varTableModel = new DefaultTableModel(new Object[][] {}, new Object[] {"Name","Type"});
		critTableModel = new DefaultTableModel(new Object[][] {}, new Object[] {"Name","PATH"});
		varTable = new JTable(varTableModel);
		varTableColumn = varTable.getColumnModel().getColumn(1);
		critTable = new JTable(critTableModel);
		addListeners();
	}
	
	public void open() {
		frame.setLayout(new BorderLayout());
		addContent();
		frame.setSize(screenResolution);
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	
	public void addContent() {
		addNorthPanel();
		frame.add(northPanel, BorderLayout.NORTH);
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1,2));
		addCenterLeftPanel();
		addCenterRightPanel();
		frame.add(centerPanel, BorderLayout.CENTER);
		
	}
	
	public void addNorthPanel() {
		northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(1,2));
		northPanel.setBorder(border);
		JPanel northLeftPanel = new JPanel();
		northLeftPanel.setLayout(new FlowLayout());
		northLeftPanel.setBorder(border);
		JPanel northRightPanel = new JPanel();
		northRightPanel.setLayout(new FlowLayout());
		northRightPanel.setBorder(border);
		emailLabel = new JLabel("E-mail");
		emailField = new JTextField("",20);
		runButton = new JButton("RUN");
		sendEmailButton = new JButton("Send E-mail");
		
		northLeftPanel.add(emailLabel); northLeftPanel.add(emailField);
		northRightPanel.add(runButton); northRightPanel.add(sendEmailButton);
		northRightPanel.add(FAQButton);
		northPanel.add(northLeftPanel, BorderLayout.WEST);
		northPanel.add(northRightPanel, BorderLayout.EAST);
		northPanel.add(northRightPanel, BorderLayout.WEST);
	}
	
	public void addCenterLeftPanel() {
		JPanel centerLeftPanel = new JPanel(new GridLayout(4,1));
		centerLeftPanel.setBorder(border);
		JPanel problemNamePanel = new JPanel(new FlowLayout());
		JPanel problemDescPanel = new JPanel(new BorderLayout());
		JPanel problemOptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		nameLabel = new JLabel("Name");
		nameText = new JTextField("", 20);
		descLabel = new JLabel("Description");
		descText = new JTextArea(); descText.setBorder(border);
		
		
		problemNamePanel.add(nameLabel);
		problemNamePanel.add(nameText);
		centerLeftPanel.add(new JLabel(""));
		centerLeftPanel.add(problemNamePanel);
		problemDescPanel.add(descLabel, BorderLayout.NORTH);
		problemDescPanel.add(descText);
		centerLeftPanel.add(problemDescPanel);
		problemOptionPanel.add(loadButton); problemOptionPanel.add(saveButton);
		centerLeftPanel.add(problemOptionPanel);
		centerPanel.add(centerLeftPanel);
	}
	
	public void addCenterRightPanel() {
		
		typeBox = new JComboBox();
        typeBox.addItem("Snowboarding");
		
		JPanel centerRightPanel = new JPanel();
		centerRightPanel.setLayout(new GridLayout(4,1)); 
		centerRightPanel.setBorder(border);
		JPanel rightOptionsPanel = new JPanel(new FlowLayout());
		JPanel varTablePanel = new JPanel(new BorderLayout());
		JPanel varOptionPanel = new JPanel(new GridLayout(2,1));
		varTableColumn.setCellEditor(new DefaultCellEditor(typeBox));
		JScrollPane tablePane = new JScrollPane(varTable);
		JPanel restPanel = new JPanel(new GridLayout(2,3));
		restPanel.add(new JLabel("Variable"));  restPanel.add(new JLabel("Operation")); restPanel.add(new JLabel("Value"));
		varField = new JTextField();
		restPanel.add(varField);
		String[] opArray = {"=","!=",">","<"};
		opBox = new JComboBox(opArray);
		restPanel.add(opBox);
		valueField = new JTextField();
		restPanel.add(valueField);
		JPanel critPanel = new JPanel(new BorderLayout());
		JPanel critOptionPanel = new JPanel(new GridLayout(2,1));
		critPane = new JScrollPane(critTable);
		
		critPanel.add(new JLabel("Criteria"), BorderLayout.NORTH);
		critOptionPanel.add(addCritButton, BorderLayout.EAST);
		critOptionPanel.add(deleteCritButton, BorderLayout.EAST);
		critPanel.add(critOptionPanel, BorderLayout.EAST);
		critPanel.add(critPane);
		varTablePanel.add(new JLabel("Variables"), BorderLayout.NORTH);
		varOptionPanel.add(addVarButton);
		varOptionPanel.add(deleteVarButton);
		varTablePanel.add(varOptionPanel, BorderLayout.EAST);
		varTablePanel.add(tablePane);
		rightOptionsPanel.add(new JLabel("Max. Time"));
		rightOptionsPanel.add(new JTextField("",5));
		rightOptionsPanel.add(new JLabel("Max. Variables"));
		rightOptionsPanel.add(new JTextField("",5));
		centerRightPanel.add(rightOptionsPanel);
		centerRightPanel.add(varTablePanel);
		centerRightPanel.add(restPanel);
		centerRightPanel.add(critPanel);
		centerPanel.add(centerRightPanel, BorderLayout.EAST);
	}
	
	public void addListeners() {
		
		FAQButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createFAQFrame();
			}
		});
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser load = new JFileChooser();
				int open = load.showOpenDialog(centerLeftPanel);
			}
		});
		saveButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser save = new JFileChooser();
				int open = save.showSaveDialog(centerLeftPanel);
			}
		});
		addVarButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				varTableModel.addRow(new Object[][] {});
			}
		});
		addCritButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				critTableModel.addRow(new Object[][] {});
			}
		});
		deleteVarButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(varTable.getSelectedRow() != -1)
					varTableModel.removeRow(varTable.getSelectedRow());
			}
		});
		deleteCritButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(critTable.getSelectedRow() != -1)
					critTableModel.removeRow(critTable.getSelectedRow());
			}
		});
	}
	
	public void createFAQFrame() {
		JFrame FAQframe = new JFrame("F.A.Q");
		FAQframe.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		FAQframe.setLayout(new GridLayout(10, 0));
		JLabel faq1 = new JLabel("Que informação será enviada por e-mail?");
		JLabel faq2 = new JLabel("Frequently asked question 2");
		JLabel faq3 = new JLabel("Frequently asked question 3");
		JLabel faq4 = new JLabel("Frequently asked question 4");
		JTextArea faq1Text = new JTextArea(); faq1Text.setEditable(false);
		JTextArea faq2Text = new JTextArea(); faq2Text.setEditable(false);
		JTextArea faq3Text = new JTextArea(); faq3Text.setEditable(false);
		JTextArea faq4Text = new JTextArea(); faq4Text.setEditable(false);
		faq1Text.setText("Resposta para a pergunta 1");
		faq2Text.setText("Resposta para a pergunta 2");
		faq3Text.setText("Resposta para a pergunta 3");
		faq4Text.setText("Resposta para a pergunta 4");
		FAQframe.add(faq1); FAQframe.add(faq1Text);
		FAQframe.add(faq2); FAQframe.add(faq2Text);
		FAQframe.add(faq3); FAQframe.add(faq3Text);
		FAQframe.add(faq4); FAQframe.add(faq4Text);
		FAQframe.setSize(1000, 500);
		FAQframe.setResizable(false);
		FAQframe.setVisible(true);
	}
	
	public Variable getVariable(int row) {
		Variable var = new Variable(varTableModel.getValueAt(row, 0).toString(), varTableModel.getValueAt(row, 1).toString());
		return var;
	}
	
	public static void main(String[] args) {
		Interface i = new Interface();
		i.open();
	}
}
