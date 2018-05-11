package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.ArrayList;


import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import extras.Email;
import extras.Parser;
import objects.Problem;
import objects.Variable;

public class Interface {
	
	private JTabbedPane tabs;
	private JFrame frame;
	private Dimension screenResolution;
	private Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
	private JPanel northPanel ;
	private JPanel problemPanel;
	private JPanel configPanel;
	private JButton loadButton;
	private JButton saveButton;
	private JButton addVarButton;
	private JButton deleteVarButton;
	private JButton addCritButton;
	private JButton deleteCritButton;
	private MyTableModel varTableModel;
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
	private JButton Send;
	private JTextField nameText;
	private JLabel descLabel;
	private JTextArea descText;
	private JComboBox varBox;
	private JComboBox opBox;
	private JComboBox typeBox;
	private JTextField valueField;
	private JScrollPane critPane;
	private JTextField maxTimeField;
	private JTextField maxVarField;
	private String path;
	private ArrayList<Variable> varList;
	private ArrayList<String> varNameList;

	
	public Interface() {
		frame = new JFrame("ES2 Project");
		screenResolution = Toolkit.getDefaultToolkit().getScreenSize();
		tabs = new JTabbedPane();
		problemPanel = new JPanel(new GridLayout(5,1));
		configPanel = new JPanel(new BorderLayout());
		FAQButton = new JButton("F.A.Q");
		loadButton = new JButton("Load Problem");
		saveButton = new JButton("Save Problem");
		addVarButton = new JButton("Add Variable");
		deleteVarButton = new JButton("Delete Variable");
		addCritButton = new JButton("Add Criterium");
		deleteCritButton = new JButton("Delete Criterium");
		varTableModel = new MyTableModel(new Object[][] {}, new Object[] {"Name","Type", "Minimum Value", "Maximum Value"});
		critTableModel = new DefaultTableModel(new Object[][] {}, new Object[] {"Name","PATH"});
		varTable = new JTable(varTableModel);
		varTableColumn = varTable.getColumnModel().getColumn(1);
		critTable = new JTable(critTableModel);
		sendEmailButton = new JButton("Send E-mail");
		runButton = new JButton("RUN");
		Send = new JButton("Send E-mail");
		varList = new ArrayList<Variable>();
		varNameList = new ArrayList<String>();
		addListeners();
	}
	
	public void open() {
		frame.setLayout(new BorderLayout());
		addContent();
		frame.setSize((int)screenResolution.getWidth()/2+200, (int)screenResolution.getHeight()/2+200);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void addContent() {
		addNorthPanel();
		frame.add(northPanel, BorderLayout.NORTH);
		addProblemPanel();
		addConfigPanel();
		tabs.addTab("Problema", problemPanel);
		tabs.addTab("Configura��o", configPanel);
		frame.add(tabs);
		
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
		northRightPanel.add(runButton); northRightPanel.add(sendEmailButton);
		northRightPanel.add(FAQButton);
		northPanel.add(northLeftPanel, BorderLayout.WEST);
		northPanel.add(northRightPanel, BorderLayout.EAST);
		northPanel.add(northRightPanel, BorderLayout.WEST);
	}
	
	public void addProblemPanel() {
		problemPanel.setBorder(border);
		JPanel emailPanel = new JPanel(new FlowLayout());
		JPanel problemNamePanel = new JPanel(new FlowLayout());
		JPanel problemDescPanel = new JPanel(new BorderLayout());
		JPanel problemOptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		emailLabel = new JLabel("E-mail");
		emailField = new JTextField("",20);
		nameLabel = new JLabel("Name");
		nameText = new JTextField("", 20);
		descLabel = new JLabel("Description");
		descText = new JTextArea(); descText.setBorder(border);
		
		emailPanel.add(emailLabel); emailPanel.add(emailField);
		problemPanel.add(emailPanel);
		problemNamePanel.add(nameLabel);
		problemNamePanel.add(nameText);
		problemPanel.add(new JLabel(""));
		problemPanel.add(problemNamePanel);
		problemDescPanel.add(descLabel, BorderLayout.NORTH);
		problemDescPanel.add(descText);
		problemPanel.add(problemDescPanel);
		problemOptionPanel.add(loadButton); problemOptionPanel.add(saveButton);
		problemPanel.add(problemOptionPanel);
	}
	
	public void addConfigPanel() {
		JPanel leftConfigPanel = new JPanel(new GridLayout(4,1));
		leftConfigPanel.setBorder(border);
		JPanel rightConfigPanel = new JPanel(new BorderLayout());
		rightConfigPanel.setBorder(border);		
		typeBox = new JComboBox();
        typeBox.addItem("Integer"); typeBox.addItem("Decimal"); typeBox.addItem("Binary");
		JPanel optionsPanel = new JPanel(new FlowLayout());
		/////////////// VARIABLES /////////////////
		JPanel varTablePanel = new JPanel(new BorderLayout());
		JPanel varOptionPanel = new JPanel(new GridLayout(2,1));
		varTableColumn.setCellEditor(new DefaultCellEditor(typeBox));
		JScrollPane tablePane = new JScrollPane(varTable);
		///////////////// RESTRICTIONS ////////////////
		JPanel restPanel = new JPanel(new GridLayout(2,3));
		restPanel.add(new JLabel("Variable"));  restPanel.add(new JLabel("Operation")); restPanel.add(new JLabel("Value"));
		varBox = new JComboBox();
		restPanel.add(varBox);
		String[] opArray = {"=","!=",">","<"};
		opBox = new JComboBox(opArray);
		restPanel.add(opBox);
		valueField = new JTextField();
		restPanel.add(valueField);
		/////////////// CRITIREUM ////////////////
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
		optionsPanel.add(new JLabel("Max. Time"));
		maxTimeField = new JTextField("",5);
		optionsPanel.add(maxTimeField);
		optionsPanel.add(new JLabel("Max. Variables"));
		maxVarField = new JTextField("",5);
		optionsPanel.add(maxVarField);
		leftConfigPanel.add(optionsPanel);
		leftConfigPanel.add(varTablePanel);
		leftConfigPanel.add(restPanel);
		leftConfigPanel.add(critPanel);
		configPanel.add(leftConfigPanel, BorderLayout.WEST);
		configPanel.add(rightConfigPanel, BorderLayout.EAST);
	}
	public void loadProblem(Problem problem) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				nameText.setText(problem.getProblemName());
				sendEmailButton.setText(problem.getEmail());
				descText.setText(problem.getDescription());
				maxTimeField.setText(problem.getTempoDeEspera());
				System.out.println(problem);
				
				//missing implementation:
				//var table
				//limitations
				//paths
				
			}
		});
		
	}
	public void addListeners() {

		
		runButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Email email= new Email();
				String mensagem= new String("Muito obrigado por usar esta plataforma de otimiza��o. Ser� informado por email sobre o progresso do processo de otimiza��o, quando o processo de otimiza��o tiver atingido 25%, 50%, 75% do total do (n�mero de avalia��es ou) tempo estimado, e tamb�m quando o processo tiver terminado, com sucesso ou devido � ocorr�ncia de erros.");
				email.createMessage("Optimiza��o em curso:" + nameText.getText() + " " + Calendar.getInstance().getTime() , mensagem);
				String to=emailField.getText();
				if(to.isEmpty()) {
					System.out.println("o email n�o foi preenchido");
					JOptionPane.showMessageDialog(new JPanel(),"o email n�o foi preenchido","Erro mail", JOptionPane.ERROR_MESSAGE);
				}else {
					email.adddestination(to);
					//inserir o mail do admin em vez do raoma
					email.CC("raoma@iscte-iul.pt");
					String file=new String(path);
					email.anexo(file);
					email.send();
				}
			}
		});

		
		sendEmailButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createEmailFrame();

			}
		});
		
		Send.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Email email= new Email();
			email.createMessage(nameText.getText(),descText.getText());
			//inserir o mail do admin em vez do raoma
			email.adddestination("raoma@iscte-iul.pt");
			email.send();
		}
	});
		

		FAQButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createFAQFrame();
				System.out.println(varBox.getSelectedItem().toString());
			}
		});

		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser load = new JFileChooser();
				int open = load.showOpenDialog(problemPanel);
				path= load.getSelectedFile().getAbsolutePath();
				Problem problem = Parser.read_XML(load.getSelectedFile().toString());
				loadProblem(problem);
				//missing implementation:
				//var table
				//limitations
				//paths
				
				
			}
		});
		
		saveButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser save = new JFileChooser();
				int open = save.showSaveDialog(problemPanel);
				if (!nameText.getText().isEmpty()) {
					Parser parser = new Parser();
					if(!sendEmailButton.getText().isEmpty()) {
						parser.addEmail(emailField.getText());
					}
					if(!descText.getText().isEmpty()) {
						parser.addDescription(descText.getText());
					}
					if(!maxTimeField.getText().isEmpty()) {
						parser.addWaitingTime(maxTimeField.getText());
					}
					if(varTableModel.getRowCount()>0) {
						Variable tmp=null;
						for (int i = 0; i < varTableModel.getRowCount(); i++) {
							tmp=getVariable(i);
							parser.addVariables(tmp.getName(), tmp.getType(), ""+tmp.getMinValue(), ""+tmp.getMaxValue());
						}
					}
					if(!varBox.getSelectedItem().toString().isEmpty()&& !valueField.getText().isEmpty()) {
						parser.addLimitations(varBox.getSelectedItem().toString()+" "+opBox.getItemAt(opBox.getSelectedIndex())+" "+valueField.getText());
					}
					if(critTableModel.getRowCount()>0) {
						for (int i = 0; i < varTableModel.getRowCount(); i++) {
							parser.addPaths(i, critTableModel.getValueAt(i, 0).toString());
						}
					}
				parser.write_XML(nameText.getText(),save.getSelectedFile().toString());
				}else {
					System.out.println("Erro");
				}
				path= save.getSelectedFile().getAbsolutePath() + ".xml";
			}
		});
		
		addVarButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				varTable.clearSelection();
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
				if(varTable.getSelectedRow() != -1) {
					String varName = (String) varTableModel.getValueAt(varTable.getSelectedRow(), 0);
					varNameList.remove(varName);
					if(varTable.getSelectedRow() > 0) {
						varTable.setRowSelectionInterval(varTable.getSelectedRow()-1, varTable.getSelectedRow()-1);
						varTableModel.removeRow(varTable.getSelectedRow()+1);
						varTable.clearSelection();
					}else {
						varTable.clearSelection();
						varTableModel.removeRow(0);
					}
				}
			}
		});
		
		deleteCritButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(critTable.getSelectedRow() != -1)
					critTableModel.removeRow(critTable.getSelectedRow());
			}
		});
		varTableModel.addTableModelListener(new TableModelListener() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void tableChanged(TableModelEvent e) {
				String varType = null;
				if(varTable.getSelectedRowCount()>0){
					if(varTable.getValueAt(varTable.getSelectedRow(), varTable.getSelectedColumn())!=null) {
						if(varTable.getSelectedColumn() == 0) {
							String varName = (String) varTableModel.getValueAt(varTable.getSelectedRow(), 0);
							if(!varNameList.contains(varName)) 
//								varNameList.set(varNameList.indexOf(varName), element)
//								JOptionPane.showMessageDialog(frame, "Warning: Variable already exists!");
//							}else 
								varNameList.add(varName);
						}
						if(varTable.getSelectedColumn() == 1) {
							varType = (String) varTableModel.getValueAt(varTable.getSelectedRow(),  1);
							Class tmp = null;
							if(varType.equals("Integer"))
								tmp=Integer.class;
							else if(varType.equals("Decimal"))
								tmp=Double.class;
							else 
								tmp=String.class;
							
							varTableModel.setCellDataType(tmp, frame);
							varTableModel.getColumnClass(2);
							varTableModel.getColumnClass(3);
						}
					}
				}
				varBox.removeAllItems();
				for(String i : varNameList)
					varBox.addItem(i);
			}
		});
	}
	
	public void createFAQFrame() {
		JFrame FAQframe = new JFrame("F.A.Q");
		FAQframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		FAQframe.setLayout(new GridLayout(10, 0));
		JLabel faq1 = new JLabel("Que informa��o ser� enviada por e-mail?");
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
	
	public void createEmailFrame() {
		JFrame Emailframe = new JFrame("Email");
		JPanel problemNamePanel = new JPanel(new FlowLayout());
		JPanel problemDescPanel = new JPanel(new BorderLayout());
		JPanel problemOptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Emailframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Emailframe.setLayout(new GridLayout(10, 0));	
		nameLabel = new JLabel("Assunto");
		nameText = new JTextField("", 20);
		descLabel = new JLabel("Description");
		descText = new JTextArea(); descText.setBorder(border);		
		problemNamePanel.add(nameLabel);
		problemNamePanel.add(nameText);
		Emailframe.add(problemNamePanel);
		problemDescPanel.add(descLabel, BorderLayout.NORTH);
		problemDescPanel.add(descText);	
		Emailframe.add(problemDescPanel);
		problemOptionPanel.add(Send);
		Emailframe.add(problemOptionPanel);
		Emailframe.setSize(1000, 500);
		Emailframe.setResizable(false);
		Emailframe.setVisible(true);
	}
	
	public Variable getVariable(int row) {
		Variable var = new Variable(varTableModel.getValueAt(row, 0).toString(), varTableModel.getValueAt(row, 1).toString(), varTableModel.getValueAt(row, 2), varTableModel.getValueAt(row, 3));
		return var;
	}
	
	public void insertVar(String name, String type) {
		varTableModel.addRow(new Object[] {name, type});
	}
	
	public static void main(String[] args) {
		Interface i = new Interface();
		i.open();
	}
}
