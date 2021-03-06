package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.jfree.ui.RefineryUtilities;

import antiSpamFilter.BinaryProblemAutomaticConfiguration;
import antiSpamFilter.DoubleProblemAutomaticConfiguration;
import antiSpamFilter.GenericBinaryProblem;
import antiSpamFilter.GenericDoubleProblem;
import antiSpamFilter.GenericIntegerProblem;
import antiSpamFilter.IntegerProblemAutomaticConfiguration;
import extras.Email;
import extras.GeradorDeGraficos;
//import extras.Email;
import extras.Parser;
import objects.Problem;
import objects.Restriction;
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
	private MyTableModel resTableModel;
	private JTable resTable;
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
	private JButton addResButton;
	private JButton deleteResButton;
	private JScrollPane critPane;
	private String path;
	private ArrayList<Variable> varList;
	private JList<CheckBoxItem> algorithmList;
	private DefaultListModel<CheckBoxItem> algorithmListModel;
	private JScrollPane algorithmPane;
	private JCheckBox intBox;
	private JCheckBox doubleBox;
	private JCheckBox binaryBox;
	private ArrayList<String> intAlgorithms;
	private ArrayList<String> doubleAlgorithms;
	private ArrayList<String> binaryAlgorithms;
	private ArrayList<Integer> checkedAlgorithmsInt;
	private ArrayList<String> checkedAlgorithms;
	private String varType = "Integer";
	private JIntegerField objectiveField;
	
	public Interface() {
		initAttributes();
		initAlgorithmLists();
		addCheckListeners();
		setAlgorithmList();
		addListeners();
	}
	
	public void initAttributes() {
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
		addResButton = new JButton("Add Restriction");
		deleteResButton = new JButton("Delete Restriction");
		addCritButton = new JButton("Add Criterium");
		deleteCritButton = new JButton("Delete Criterium");
		varTableModel = new MyTableModel(new Object[][] {}, new Object[] {"Name","Type", "Minimum Value", "Maximum Value", "Number of Bits"});
		critTableModel = new DefaultTableModel(new Object[][] {}, new Object[] {"Name","PATH"});
		varTable = new JTable(varTableModel);
		insertVar("tmp_2018_var_a_b_c", "Integer", 0, 0, 0);
		varTableModel.removeRow(0);
		resTableModel = new MyTableModel(new Object[][] {},  new Object[] {"Variable", "Operation", "Value"});
		resTable = new JTable(resTableModel);
		critTable = new JTable(critTableModel);
		sendEmailButton = new JButton("Send E-mail");
		runButton = new JButton("RUN");
		Send = new JButton("Send E-mail");
		varList = new ArrayList<Variable>();
		intBox = new JCheckBox("Integer");
		doubleBox = new JCheckBox("Decimal");
		binaryBox = new JCheckBox("Binary");
		algorithmListModel = new DefaultListModel<>();
		algorithmList = new JList<CheckBoxItem>(algorithmListModel);
		checkedAlgorithmsInt = new ArrayList<Integer>();
		algorithmPane = new JScrollPane(algorithmList);
		checkedAlgorithms = new ArrayList<String>();
		objectiveField = new JIntegerField();
		objectiveField.setPreferredSize(new Dimension(20,20));
	}
	
	public void open() {
		frame.setLayout(new BorderLayout());
		addContent();
		frame.setSize((int)screenResolution.getWidth()/2+100, (int)screenResolution.getHeight()/2+300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void addContent() {
		addNorthPanel();
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
		northLeftPanel.add(loadButton);
		northLeftPanel.add(saveButton);
		JPanel northRightPanel = new JPanel();
		northRightPanel.setLayout(new FlowLayout());
		northRightPanel.setBorder(border);
		northRightPanel.add(runButton); northRightPanel.add(sendEmailButton);
		northRightPanel.add(FAQButton);
		northPanel.add(northLeftPanel, BorderLayout.WEST);
		northPanel.add(northRightPanel, BorderLayout.EAST);
		northPanel.add(northRightPanel, BorderLayout.WEST);
		frame.add(northPanel, BorderLayout.NORTH);
	}
	
	public void addProblemPanel() {
		problemPanel.setBorder(border);
		JPanel emailPanel = new JPanel(new FlowLayout());
		JPanel problemNamePanel = new JPanel(new FlowLayout());
		JPanel problemDescPanel = new JPanel(new BorderLayout());
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
	}
	
	public void addConfigPanel() {
		JPanel ConfigPanel = new JPanel(new GridLayout(5,1));
		JPanel topConfigPanel = new JPanel(new GridLayout(2,1));
		/////////////// OBJECTIVES /////////////////
		JPanel objectivesPanel = new JPanel(new FlowLayout());
		objectivesPanel.add(new JLabel("Number of Objectives"));
		objectivesPanel.add(objectiveField);
		/////////////// ALGORITHMS /////////////////
		JPanel algTypePanel = new JPanel(new FlowLayout());
		JPanel algorithmsPanel = new JPanel(new BorderLayout());
		algorithmsPanel.setBorder(border);
		algTypePanel.add(intBox); algTypePanel.add(doubleBox); algTypePanel.add(binaryBox);
		algorithmsPanel.add(algorithmPane);
		/////////////// VARIABLES /////////////////
		JPanel varTablePanel = new JPanel(new BorderLayout());
		JPanel varOptionPanel = new JPanel(new GridLayout(2,1));
		JScrollPane tablePane = new JScrollPane(varTable);
		///////////////// RESTRICTIONS ////////////////
		JPanel resPanel = new JPanel(new BorderLayout());
		JPanel resOptionPanel = new JPanel(new GridLayout(2, 1));
		varBox = new JComboBox();
		String[] opArray = {"=","!=",">","<"};
		opBox = new JComboBox(opArray);
		TableColumn resVariableColumn = resTable.getColumnModel().getColumn(0);
		resVariableColumn.setCellEditor(new DefaultCellEditor(varBox));
		TableColumn resOperationColumn = resTable.getColumnModel().getColumn(1);
		resOperationColumn.setCellEditor(new DefaultCellEditor(opBox));
		JScrollPane resPane = new JScrollPane(resTable);
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
		resPanel.add(new JLabel("Restriction"), BorderLayout.NORTH);
		resOptionPanel.add(addResButton);
		resOptionPanel.add(deleteResButton);
		resPanel.add(resOptionPanel, BorderLayout.EAST);
		resPanel.add(resPane);
		topConfigPanel.add(objectivesPanel);
		topConfigPanel.add(algTypePanel);
		ConfigPanel.add(topConfigPanel);
		ConfigPanel.add(algorithmsPanel);
		ConfigPanel.add(varTablePanel);
		ConfigPanel.add(resPanel);
		ConfigPanel.add(critPanel);
		configPanel.add(ConfigPanel);
	}
	
	public void loadProblem(Problem problem) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				nameText.setText(problem.getProblemName());
				emailField.setText(problem.getEmail());
				descText.setText(problem.getDescription());
				varTableModel.setRowCount(0);
				String type = problem.getVars().get(0).getType();
				if(type.equals("Integer")) {
					intBox.doClick();
				}
				else if(type.equals("Double")) {
					doubleBox.doClick();	
				}
				else if(type.equals("Binary")) {	
					binaryBox.doClick();
				}
				for(int i=0; i<problem.getVars().size(); i++) {
					String name = problem.getVars().get(i).getName();
					String tmpType = problem.getVars().get(i).getType();
					Object minValue = problem.getVars().get(i).getMinValue();
					Object maxValue = problem.getVars().get(i).getMaxValue();
					int nrBits = problem.getVars().get(i).getNumberBits();
					if(tmpType.equals("Integer") || tmpType.equals("Decimal"))
						insertVar(name, tmpType, minValue, maxValue, 0);
					else
						insertVar(name, tmpType, null, null, nrBits);
				}
				resTableModel.setRowCount(0);
				for(Restriction a : problem.getRestrictions()) {
					System.out.println(a.toString());
					String name = a.getVarName();
					String op = a.getOperation();
					Object value = a.getValue();
					insertRes(name, op, value);
				}
				for(String[] it : problem.getPaths()) {
					insertPath(it[0], it[1]);
				}
				for(int i = 0;i<algorithmListModel.size();i++) {
					CheckBoxItem item = algorithmListModel.get(i);
					for(String it : problem.getAlgorithms()) {
						if (item.getName().equals(it)) {
							item.setSelected(true);
							break;
						}
					}
				}

				
			}
		});
		
	}
	
	public void addCheckListeners() {
		intBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(intBox.isSelected()) {
					varTable.clearSelection();
					varTableModel.setRowCount(0);
					varList.clear();
					varType = "Integer";
					doubleBox.setEnabled(false);
					binaryBox.setEnabled(false);
				}else {
					varTable.clearSelection();
					varTableModel.setRowCount(0);
					varList.clear();
					doubleBox.setEnabled(true);
					binaryBox.setEnabled(true);
				}
				algorithmListModel.clear();
				checkedAlgorithms.clear();
				checkedAlgorithmsInt.clear();
				for(String i : intAlgorithms)
					algorithmListModel.addElement(new CheckBoxItem(i));
			}
		});
		doubleBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(doubleBox.isSelected()) {
					varTable.clearSelection();
					varTableModel.setRowCount(0);
					varList.clear();
					varType = "Double";
					intBox.setEnabled(false);
					binaryBox.setEnabled(false);
				}else {
					varTable.clearSelection();
					varTableModel.setRowCount(0);
					varList.clear();
					intBox.setEnabled(true);
					binaryBox.setEnabled(true);
				}
				algorithmListModel.clear();
				checkedAlgorithms.clear();
				checkedAlgorithmsInt.clear();
				for(String i : doubleAlgorithms)
					algorithmListModel.addElement(new CheckBoxItem(i));
			}
		});
		binaryBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(binaryBox.isSelected()) {
					varTable.clearSelection();
					varTableModel.setRowCount(0);
					varList.clear();
					varType ="Integer";
					intBox.setEnabled(false);
					doubleBox.setEnabled(false);
				}else {
					varTable.clearSelection();
					varTableModel.setRowCount(0);
					varList.clear();
					intBox.setEnabled(true);
					doubleBox.setEnabled(true);
				}
				algorithmListModel.clear();
				checkedAlgorithms.clear();
				checkedAlgorithmsInt.clear();
				for(String i : binaryAlgorithms)
					algorithmListModel.addElement(new CheckBoxItem(i));
			}
		});
	
		algorithmList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JList list = (JList) e.getSource();
				int index = list.locationToIndex(e.getPoint());
				CheckBoxItem item = (CheckBoxItem) list.getModel().getElementAt(index);
				if(!item.isSelected()) {
					checkedAlgorithmsInt.add(/*item.getName()*/index+1);
					checkedAlgorithms.add(item.getName());
				}
				else {
					checkedAlgorithmsInt.remove(/*item.getName()*/index+1);
				checkedAlgorithms.remove(item.getName());
				}
			}
		});
	}
	
	public void addListeners() {
		runButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (getReady()) {
//					//Email
//					Email email= new Email();
//					String mensagem= new String("Muito obrigado por usar esta plataforma de otimiza��o. Ser� informado por email sobre o progresso do processo de otimiza��o, quando o processo de otimiza��o tiver atingido 25%, 50%, 75% do total do (n�mero de avalia��es ou) tempo estimado, e tamb�m quando o processo tiver terminado, com sucesso ou devido � ocorr�ncia de erros.");
//					int ano=Calendar.getInstance().getTime().getYear()+1900;
//	                email.createMessage("Optimiza��o em curso:" + nameText.getText() + " " + Calendar.getInstance().getTime().getDate()+"-"+Calendar.getInstance().getTime().getMonth()+"-"+ano+ " "+Calendar.getInstance().getTime().getHours()+":"+Calendar.getInstance().getTime().getMinutes() , mensagem);
//					String to=emailField.getText();
//					email.adddestination(to);
//					//inserir o mail do admin em vez do raoma
//					email.CC("sbpss@iscte-iul.pt");
					System.out.println("Email Enviado");
//					String file=new String(path);
//					email.anexo(file);
//					email.send();
					
					runAlgorithm();
					
					//Grafico		        
					GeradorDeGraficos demo;
					try {
						//Alterar o nome do ficheiro
						demo = new GeradorDeGraficos("Solu��es �timas geradas pelo processo de otimiza��o","BEST_HV_FUN",DefinePath());
						demo.pack();
				        RefineryUtilities.positionFrameOnScreen(demo, 0.95, 0.2);
				        demo.setVisible(true);

					} catch (IOException e) {
						e.printStackTrace(); 
					}		
				}else {
					JOptionPane.showMessageDialog(new JPanel(),"Campos Por Preencher","Erro", JOptionPane.ERROR_MESSAGE);
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
//			Email email= new Email();
//			email.createMessage(nameText.getText(),descText.getText());
//			//inserir o mail do admin em vez do raoma
//			email.adddestination("raoma@iscte-iul.pt");
//			email.send();
		}
	});
		
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
				int open = load.showOpenDialog(problemPanel);
				path= load.getSelectedFile().getAbsolutePath();
				Problem problem = Parser.read_XML(load.getSelectedFile().toString());
				loadProblem(problem);
				
				
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
					if(varTableModel.getRowCount()>0) {
						for(Variable v : varList) {
							parser.addVariables(v.getName(), v.getType(), ""+v.getMinValue(), ""+v.getMaxValue(), ""+v.getNumberBits());
						}
					}
					if(resTableModel.getRowCount()>0) {
						Restriction tmp = null;
						for(int i=0; i<resTableModel.getRowCount(); i++) {
							tmp = getRestriction(i);
							parser.addLimitations(tmp);
						}
					}
					if(critTableModel.getRowCount()>0) {
						for (int i = 0; i < critTableModel.getRowCount(); i++) {
							parser.addPaths(critTableModel.getValueAt(i, 0).toString(), critTableModel.getValueAt(i, 1).toString());
						}
					}
					if(!checkedAlgorithms.isEmpty()) {
						int id = 0;
						for(String algorithm : checkedAlgorithms) {
							parser.addChosenAlgorithm(id, algorithm);
							id++;
						}
					}
					if(!nameText.getText().isEmpty()) {
						parser.addName(nameText.getText());
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
				if(intBox.isSelected()) {
					varType = "Integer";
					varTableModel.setIntervalCellEditable(true);
					varTableModel.setBitsCellEditable(false);
				}else if(doubleBox.isSelected()) {
					varType = "Double";
					varTableModel.setIntervalCellEditable(true);
					varTableModel.setBitsCellEditable(false);
				}else if(binaryBox.isSelected()){
					varType = "Binary";
					varTableModel.setIntervalCellEditable(false);
					varTableModel.setBitsCellEditable(true);
				}
					
				varTableModel.addRow(new Object[] {null, varType, null, null});
				Class tmp = null;
				if(varType.equals("Integer"))
					tmp=Integer.class;
				else if(varType.equals("Double"))
					tmp=Double.class;
				else 
					tmp=Integer.class;
				
				varTableModel.setCellDataType(tmp);
				varTableModel.getColumnClass(2);
				varTableModel.getColumnClass(3);
			}
		});
		
		addResButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resTableModel.addRow(new Object[][] {});
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
					int index = varTable.getSelectedRow();
					if(varList.size()-1 >= index)
						varList.remove(index);
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
		
		deleteResButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(resTable.getSelectedRow() > 0) {
					resTable.setRowSelectionInterval(resTable.getSelectedRow()-1, resTable.getSelectedRow()-1);
					resTableModel.removeRow(resTable.getSelectedRow()+1);
					resTable.clearSelection();
				}else {
					resTable.clearSelection();
					resTableModel.removeRow(0);
				}
			}
		});
		
		varTableModel.addTableModelListener(new TableModelListener() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void tableChanged(TableModelEvent e) {
				if(varTable.getSelectedRowCount()>0){
					if(varTable.getValueAt(varTable.getSelectedRow(), varTable.getSelectedColumn())!=null) {
						if(varTable.getSelectedColumn() == 0) {
							String varName = (String) varTableModel.getValueAt(varTable.getSelectedRow(), 0);
							String varType = (String) varTableModel.getValueAt(varTable.getSelectedRow(), 1);
							addVariableToList(varName, varType);
						}
						if(varTable.getSelectedColumn() == 2) {
							Object value = varTable.getValueAt(varTable.getSelectedRow(), 2);
							int index = varTable.getSelectedRow();
							if(varList.size()-1 >= index)
								varList.get(index).setMinValue(value);
						}
						if(varTable.getSelectedColumn() == 3) {
							Object value = varTable.getValueAt(varTable.getSelectedRow(), varTable.getSelectedColumn());
							int index = varTable.getSelectedRow();
							if(varList.size()-1 >= index)
								varList.get(index).setMaxValue(value);
						}	
						if(varTable.getSelectedColumn() == 4) {
							int value = (int) varTable.getValueAt(varTable.getSelectedRow(), varTable.getSelectedColumn());
							int index = varTable.getSelectedRow();
							if(varList.size()-1 >= index)
								varList.get(index).setNumberBits(value);
						}	
					}
				}
				varBox.removeAllItems();
				for(Variable i : varList)
					varBox.addItem(i.getName());
			}
		});
		
		resTableModel.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				for(int i=0; i<varTable.getRowCount(); i++) {
					if(resTable.getSelectedRow()!=-1) {
					try {
						if(resTable.getValueAt(resTable.getSelectedRow(), 0).equals(varTable.getValueAt(i, 0))) {
							resTableModel.setCellDataType(varTableModel.getDataType());
							resTableModel.getColumnClass(2);
						}
					}catch(NullPointerException e2) {
						JOptionPane.showMessageDialog(frame, "Warning: Choose a variable!");
					}
				}}
			}
		});
	}
	 
	private void runAlgorithm() {
		int nVar=varList.size();
		int nObj=1;
		if(!objectiveField.getText().equals(""))	
			nObj = Integer.valueOf(objectiveField.getText());
		int nConst=0;
		String probName=nameText.getText();
		if (intBox.isSelected()) {
			GenericIntegerProblem x = new GenericIntegerProblem(nVar, nObj, nConst, probName,(String)critTableModel.getValueAt(0, 1));
			if(varTableModel.getValueAt(0,2)!=null && varTableModel.getValueAt(0,3)!=null ) {
				x.setLimits((Integer)varTableModel.getValueAt(0,2), (Integer)varTableModel.getValueAt(0,3));	
			}
			GenericIntegerProblem[] args = {x};
			try {
				IntegerProblemAutomaticConfiguration.main(args, getCheckedAlgorithms());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if (doubleBox.isSelected()) {
			GenericDoubleProblem x = new GenericDoubleProblem(nVar, nObj, nConst, probName,(String)critTableModel.getValueAt(0, 1));
			if(varTableModel.getValueAt(0,2)!=null && varTableModel.getValueAt(0,3)!=null ) {
				x.setLimits((Double)varTableModel.getValueAt(0,2), (Double)varTableModel.getValueAt(0,3));	
			}
			GenericDoubleProblem[] args = {x};
			try {
				DoubleProblemAutomaticConfiguration.main(args, getCheckedAlgorithms());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if (binaryBox.isSelected()) {
			GenericBinaryProblem x = new GenericBinaryProblem(nVar, nObj, nConst, probName,(int)varTableModel.getValueAt(0,4),(String)critTableModel.getValueAt(0, 1));
			GenericBinaryProblem[] args = {x};
			try {
				BinaryProblemAutomaticConfiguration.main(args, getCheckedAlgorithms());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String DefinePath() {
		String graphicPath = String.format("experimentBaseDirectory\\%s\\data\\%s\\%s\\BEST_HV_FUN.tsv",getCheckedBox(),checkedAlgorithms.get(0),nameText.getText());
		return graphicPath;
	}
	
	private boolean getReady() {
		if (emailField.getText().isEmpty()) {
			return false;
		}
		if (nameText.getText().isEmpty()) {
			return false;
		}
		if (getCheckedBox().equals(null)) {
			return false;
		}
		if (checkedAlgorithms.size()<1) {
			return false;
		}
		if (critTableModel.getRowCount()<1) {
			return false;
		}
		if (varTableModel.getRowCount()<2) {
			return false;
		}
		return true;
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
	
	public void createAlgorithmFrame() {
		JFrame algFrame = new JFrame("Algorithms");
		JPanel typePanel = new JPanel();
		JPanel listPanel = new JPanel();
		typePanel.setLayout(new GridLayout(3, 1));
		typePanel.setBorder(border);
		listPanel.setLayout(new BorderLayout());
		listPanel.setBorder(border);
		algFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		algFrame.setLayout(new GridLayout(1, 2));
		JCheckBox intCheck = new JCheckBox("Integer");
		JCheckBox doubleCheck = new JCheckBox("Double");
		JCheckBox binaryCheck = new JCheckBox("Binary");
		intCheck.setEnabled(false);
		typePanel.add(intCheck); typePanel.add(doubleCheck); typePanel.add(binaryCheck);
		listPanel.add(algorithmList);
		algFrame.add(typePanel);
		algFrame.add(listPanel);
		algFrame.setSize(500, 500);
		algFrame.setResizable(false);
		algFrame.setVisible(true);
	}
	
	private void initAlgorithmLists() {
		intAlgorithms = new ArrayList<String>();
		doubleAlgorithms = new ArrayList<String>();
		binaryAlgorithms = new ArrayList<String>();
		
		intAlgorithms.add("NSGAII");
		intAlgorithms.add("SMSEMOA");
		intAlgorithms.add("MOCell");
		intAlgorithms.add("PAES");
		intAlgorithms.add("RandomSearch");
		
		doubleAlgorithms.add("NSGAII");
		doubleAlgorithms.add("SMSEMOA");
		doubleAlgorithms.add("GDE3");
		doubleAlgorithms.add("IBEA");
		doubleAlgorithms.add("MOCell");
		doubleAlgorithms.add("MOAED");
		doubleAlgorithms.add("PAES");
		doubleAlgorithms.add("RandomSearch");
		
		binaryAlgorithms.add("NSGAII");
		binaryAlgorithms.add("SMSEMOA");
		binaryAlgorithms.add("MOCell");
		binaryAlgorithms.add("MOCH");
		binaryAlgorithms.add("PAES");
		binaryAlgorithms.add("RandomSearch");
		binaryAlgorithms.add("SPEA2");
	}
	
	private void setAlgorithmList() {
		algorithmList.setCellRenderer(new CheckBoxListRenderer());
		algorithmList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		algorithmList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JList list = (JList) e.getSource();
				int index = list.locationToIndex(e.getPoint());
				CheckBoxItem item = (CheckBoxItem) list.getModel().getElementAt(index);
				item.setSelected(!item.isSelected());
				list.repaint(list.getCellBounds(index, index));
			}
		});
	}
	
	public Variable getVariable(int row) {
		Variable var = new Variable(varTableModel.getValueAt(row, 0).toString(), varTableModel.getValueAt(row, 1).toString(), varTableModel.getValueAt(row, 2), varTableModel.getValueAt(row, 3), (int) varTableModel.getValueAt(row, 4));
		return var;
	}
	public Restriction getRestriction(int row) {
		Restriction res = new Restriction(resTableModel.getValueAt(row, 0).toString(), resTableModel.getValueAt(row, 1).toString(), resTableModel.getValueAt(row, 2)+"");
		return res;
	}
	
	public void insertVar(String name, String type, Object minValue, Object maxValue, int nrBits) {
		varTableModel.addRow(new Object[] {});
		varTable.setValueAt(name, varTable.getRowCount()-1, 0);
		varTable.setValueAt(type, varTable.getRowCount()-1, 1);
		Class tmp = null;
		if(type.equals("Integer")) {
			tmp=Integer.class;
		}
		else if(type.equals("Decimal")) {
			tmp=Double.class;
		}
		else if(type.equals("Binary")){
			tmp=Integer.class;
		}
		
		varTableModel.setCellDataType(tmp);
		varTableModel.getColumnClass(2);
		varTableModel.getColumnClass(3);
		if(type.equals("Integer") || type.equals("Decimal")) {
			varTable.setValueAt(minValue, varTable.getRowCount()-1, 2);
			varTable.setValueAt(maxValue, varTable.getRowCount()-1, 3);
		}else if(type.equals("Binary"))
			varTable.setValueAt(nrBits, varTable.getRowCount()-1, 4);
	}
	
	public void insertRes(String name, String op, Object value) {
		resTableModel.addRow(new Object[] {name, op, null});
			try {
				Class tmp = null;
				if(varType.equals("Integer"))
					tmp=Integer.class;
				else if(varType.equals("Double"))
					tmp=Double.class;
				else 
					tmp=String.class;
				resTableModel.setCellDataType(tmp);
				resTableModel.getColumnClass(2);
				resTableModel.setValueAt(value, resTable.getRowCount()-1, 2);
			}catch(NullPointerException e1) {
				JOptionPane.showMessageDialog(frame, "Warning: Variable must have a type!");
			}
		
	}
	public void insertPath(String name, String path) {
		critTableModel.addRow(new Object[] {name, path});
	}
	
	private void addVariableToList(String varName, String type) {
		Variable var = new Variable(varName, type, null, null, 0);
		if(varList.isEmpty())
			varList.add(var);
		else {
			ArrayList<String> tmpList = new ArrayList<String>();
			for(int i=0; i<varList.size(); i++) {
				tmpList.add(varList.get(i).getName());
			}
			if(!tmpList.contains(varName))
				varList.add(var);
			}
	}

	public ArrayList<Integer> getCheckedAlgorithms() {
		  return checkedAlgorithmsInt;
	}
	
	private String getCheckedBox() {
		if (intBox.isSelected()) {
			return "IntegerProblemStudy";
		}else if (doubleBox.isSelected()) {
			return "DoubleProblemStudy";
		}else if (binaryBox.isSelected()) {
			return "BinaryProblemStudy";
		}else {
			return null;
		}
	}
	
	public ArrayList<Integer> getIntervals() {
		int min = (int) varTable.getValueAt(0, 2);
		int max = (int) varTable.getValueAt(0, 3);
		ArrayList<Integer> interval = new ArrayList<Integer>();
		interval.add(min); interval.add(max);
		return interval;
	}
	
//	public static void main(String[] args) {
//		Interface i = new Interface();
//		i.open();
//	}
}
