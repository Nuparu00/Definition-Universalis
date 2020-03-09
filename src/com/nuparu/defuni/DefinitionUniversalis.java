package com.nuparu.defuni;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.nuparu.defuni.province.Province;
import com.nuparu.defuni.ui.ColorPicker;
import com.nuparu.defuni.utils.CsvHelper;
import com.nuparu.defuni.utils.IniFile;
import javax.swing.JTextField;
import javax.swing.JColorChooser;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DefinitionUniversalis {

	
	
	
	
	
	
	public static boolean conosleMode = false;
	public static boolean running = true;
	public static String directory = "C:/Users/Kuba/Documents/wurm";

	public static List<Province> provinces;

	private static final Pattern HEXADECIMAL_PATTERN = Pattern.compile("\\p{XDigit}+");
	private static Random rand = new Random();
	
	public ColorPicker provinceColorPicker;
	
	/*
	 * UI Related fields below
	 */
	
	
	
	
	
	
	
	
	
	
	private JFrame frmDefinitionUniversalis;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JSeparator separator;
	private JTabbedPane tabbedPane;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel_3;
	private JPanel panel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;

	/**
	 * Launch the application.
	 */
	
	public static DefinitionUniversalis window;
	private JLabel lblNewLabel_8;
	private JTextField nameField;
	private JLabel lblNewLabel_9;
	public JTextField hexField;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JScrollPane scrollPane;
	private JTable table;
	private JSplitPane splitPane;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	
	public static void main(String[] args) {
		System.out.println("Welcome to Definition Universalis TM 1.0");
		System.out.println("By Nuparu00");
		start();
		if(directory.isEmpty()) {
			if(!conosleMode) {
			JOptionPane.showMessageDialog(null, "modPath in the config.ini file is not defined!");
			}
			System.out.println("modPath in the config.ini file is not defined!");
			System.exit(-1);
		}
		provinces = CsvHelper.loadDefinition(directory + "/map/definition.csv");
		if(!conosleMode) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new DefinitionUniversalis();
					window.frmDefinitionUniversalis.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}
		});
		}else {
		provinces = CsvHelper.loadDefinition(directory + "/map/definition.csv");
		System.out.println("Loaded " + provinces.size() + " provinces");
		Scanner in = new Scanner(System.in);
		while (running) {	
			String command = in.nextLine();
			String[] words = command.split(" ");
			switch (words[0]) {
			case "add":
				addProvince(words);
				break;
			case "check":
				checkColor(words);
				break;
			case "exit":
				running = false;
				break;
			case "help":
				help();
				break;
			case "random":
				random();
				break;
			default:
				System.out.println("Unknown command. Write \"help\" to show all commands");
				break;
			}
		}
		in.close();
		}
	}

	/**
	 * Create the application.
	 */
	public DefinitionUniversalis() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDefinitionUniversalis = new JFrame();
		frmDefinitionUniversalis.setTitle("Definition Universalis");
		frmDefinitionUniversalis.setBounds(100, 100, 450, 332);
		frmDefinitionUniversalis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblNewLabel = new JLabel("Definition Universalis");
		lblNewLabel.setFont(new Font("Garamond", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frmDefinitionUniversalis.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		panel = new JPanel();
		frmDefinitionUniversalis.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		lblNewLabel_1 = new JLabel("Version 1.0");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblNewLabel_1);
		
		separator = new JSeparator();
		panel.add(separator);
		
		lblNewLabel_2 = new JLabel("By Nuparu00");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel_2);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmDefinitionUniversalis.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		panel_1 = new JPanel();
		tabbedPane.addTab("Home", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel_3 = new JLabel("Welcome!");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_3, BorderLayout.NORTH);
		
		panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		lblNewLabel_4 = new JLabel("Mod Path: ");
		panel_3.add(lblNewLabel_4, "4, 4");
		
		lblNewLabel_6 = new JLabel("C:/");
		panel_3.add(lblNewLabel_6, "6, 4");
		
		lblNewLabel_5 = new JLabel("Loaded Provinces:");
		panel_3.add(lblNewLabel_5, "4, 6");
		
		lblNewLabel_7 = new JLabel("0000");
		panel_3.add(lblNewLabel_7, "6, 6");
		
		panel_2 = new JPanel();
		tabbedPane.addTab("Add Province", null, panel_2, null);
		FormLayout fl_panel_2 = new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("244dlu:grow"),
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,});
		panel_2.setLayout(fl_panel_2);
		
		lblNewLabel_8 = new JLabel("Name");
		panel_2.add(lblNewLabel_8, "4, 4");
		
		nameField = new JTextField();
		panel_2.add(nameField, "4, 6, fill, default");
		nameField.setColumns(10);
		
		lblNewLabel_9 = new JLabel("Hex Color");
		panel_2.add(lblNewLabel_9, "4, 8");
		
		hexField = new JTextField();
		panel_2.add(hexField, "4, 10, fill, default");
		hexField.setColumns(10);
		
		btnNewButton = new JButton("Select Color");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				provinceColorPicker = new ColorPicker();
				provinceColorPicker.open();
				
				if(!hexField.getText().isEmpty()) {
					if(isHexadecimal(hexField.getText())) {
						provinceColorPicker.colorChooser.setColor((int)Long.parseLong(hexField.getText(),16));
					}
				}
			}
		});
		panel_2.add(btnNewButton, "4, 12");
		
		btnNewButton_1 = new JButton("Add");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nameField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frmDefinitionUniversalis, "Name field is empty!");
					return;
				}
				if(hexField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frmDefinitionUniversalis, "Hex Color field is empty!");
					return;
				}
					String hex = hexField.getText();
					String name = nameField.getText();
					if (!isHexadecimal(hex)) {
						System.out.println("Given color " + hex + " is not valid!");
						JOptionPane.showMessageDialog(frmDefinitionUniversalis, "Given color " + hex + " is not valid!");
						return;
					}
					if (!isColorFree(hex)) {
						System.out.println("Color " + hex + " is not free!");
						JOptionPane.showMessageDialog(frmDefinitionUniversalis, "Color " + hex + " is not free!");
						return;
					}
					JOptionPane.showMessageDialog(frmDefinitionUniversalis, "Successfully added a province!");
					addProvince(name,hex);
				
			}
		});
		
		splitPane = new JSplitPane();
		panel_2.add(splitPane, "4, 14, fill, fill");
		
		btnNewButton_2 = new JButton("Check Color");
		splitPane.setLeftComponent(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hexField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frmDefinitionUniversalis, "Hex Color field is empty!");
					return;
				}
				String hex = hexField.getText();
				if (!isHexadecimal(hex)) {
					System.out.println("Given color " + hex + " is not valid!");
					JOptionPane.showMessageDialog(frmDefinitionUniversalis, "Given color " + hex + " is not valid!");
					return;
				}
				boolean free = isColorFree(hex);
				JOptionPane.showMessageDialog(frmDefinitionUniversalis, hex + " is" + (free == false ? " not a free color" : " a free color"));
			}
		});
		
		btnNewButton_3 = new JButton("Random Color");
		splitPane.setRightComponent(btnNewButton_3);
		panel_2.add(btnNewButton_1, "4, 16");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hexField.setText(random());
			}
		});		
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(new String[] { "ID", "R", "G", "B", "Name" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int c) {
				   return getValueAt(0, c).getClass();
				}
		};

		table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		scrollPane = new JScrollPane(table);
		tabbedPane.addTab("Provinces", null, scrollPane, null);
		
		
		lblNewLabel_7.setText("" + provinces.size());
		lblNewLabel_6.setText(directory);
		initTable();
	}

	/*
	 * Tries to load the mod path
	 */
	private static void start() {
		String path = "./config.ini";
		File file = new File(path);
		if (!file.exists()) {
			try{
				file.createNewFile();
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
					bw.append("[Settings]\r\n" + 
							"modPath=");
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			catch (IOException e) {
				 e.printStackTrace();
			}
			System.exit(-1);
			return;
			
		}
		try {
			IniFile ini = new IniFile(path);
			directory = ini.getString("Settings", "modPath", "");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public void initTable() {
		for (Province province : provinces) {
			Object[] row = new Object[] { province.id, province.r, province.g, province.b, province.name };
			((DefaultTableModel) table.getModel()).addRow(row);
		}
	}

	public static void addProvince(String name, String hex) {
		int[] rgb = hexToRGB(hex);
		Province province = new Province(provinces.get(provinces.size() - 1).id + 1, rgb[0], rgb[1], rgb[2], name);
		provinces.add(province);

		File definition = new File(directory + "/map/definition.csv");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(definition, true));
			bw.append(System.getProperty("line.separator") + province.serialize());
			bw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		File history = new File(directory + "/history/provinces/" + province.id + " - " + province.name + ".txt");
		history.getParentFile().mkdirs();
		try {
			history.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(history, true));
			bw.append("culture = english\r\n" + "religion = catholic\r\n" + "hre = no\r\n" + "base_tax = 2\r\n"
					+ "base_production = 2\r\n" + "base_manpower = 2\r\n" + "is_city = no\r\n"
					+ "discovered_by = western\r\n" + "discovered_by = muslim\r\n" + "discovered_by = ottoman\r\n"
					+ "discovered_by = eastern");
			bw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		if(window != null) {
		Object[] row = new Object[] { province.id, province.r, province.g, province.b, province.name };
		((DefaultTableModel) window.table.getModel()).addRow(row);
		}
		
		
		File localisation = new File(directory + "/localisation/prov_names_l_english.yml");
		if(localisation.exists() && !localisation.isDirectory()) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(localisation, true));
				bw.append(System.getProperty("line.separator") + " PROV" + province.id + ":0 \"" + province.name +"\"");
				bw.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Added " + province.toString());
	}

	public static void addProvince(String[] words) {

		if (words.length != 3) {
			System.out.println("Wrong command syntax: add <hex> <name>");
			return;
		}

		String hex = words[1];

		String name = words[2];

		if (!isHexadecimal(hex)) {
			System.out.println("Given color " + hex + " is not valid!");
			return;
		}
		if (!isColorFree(hex)) {
			System.out.println("Color " + hex + " is not free!");
			return;
		}
		addProvince(name, hex);

	}

	public static void checkColor(String[] words) {
		if (words.length != 2) {
			System.out.println("Wrong command syntax: check <hex>");
			return;
		}
		String color = words[1];
		if (!isHexadecimal(color)) {
			System.out.println("Given color " + color + " is not valid!");
			return;
		}

		System.out.println("Color " + color + (isColorFree(color) ? " is" : " is not") + " free.");
	}

	public static String random() {
		int r = rand.nextInt(256);
		int g = rand.nextInt(256);
		int b = rand.nextInt(256);

		while (true) {
			boolean flag = true;
			for (Province province : provinces) {
				if (province.r == r && province.g == g && province.b == b) {
					r = rand.nextInt(256);
					g = rand.nextInt(256);
					b = rand.nextInt(256);
					flag = false;
					break;
				}
			}
			if (flag) {
				break;
			}
		}
		String hex = String.format("%02x%02x%02x", r, g, b);
		System.out.println(hex + " is a free color!");
		return hex;
	}

	public static void help() {
		System.out.println("help - lists all commands");
		System.out.println("add <hex> <name> - adds a new province");
		System.out.println("check <hex> - checks if color is free");
		System.out.println("exit - exits application");
	}

	private static boolean isHexadecimal(String input) {
		final Matcher matcher = HEXADECIMAL_PATTERN.matcher(input);
		return matcher.matches();
	}

	public static boolean isColorFree(String hex) {
		int[] rgb = hexToRGB(hex);
		for (Province province : provinces) {
			if (province.r == rgb[0] && province.g == rgb[1] && province.b == rgb[2])
				return false;
		}
		return true;
	}

	public static int[] hexToRGB(final String rgb) {
		final int[] ret = new int[3];
		for (int i = 0; i < 3; i++) {
			ret[i] = Integer.parseInt(rgb.substring(i * 2, i * 2 + 2), 16);
		}
		return ret;
	}
}
