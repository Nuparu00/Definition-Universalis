package com.nuparu.defuni.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.nuparu.defuni.DefinitionUniversalis;

import javax.swing.JColorChooser;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class ColorPicker {

	private JFrame frmProvinceColorPicker;
	public JColorChooser colorChooser;
	private JPanel panel;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;

	/**
	 * Launch the application.
	 */
	public void open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmProvinceColorPicker.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ColorPicker() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProvinceColorPicker = new JFrame();
		frmProvinceColorPicker.setTitle("Province Color Picker");
		frmProvinceColorPicker.setBounds(100, 100, 651, 439);
		
		colorChooser = new JColorChooser();
		frmProvinceColorPicker.getContentPane().add(colorChooser, BorderLayout.NORTH);
		
		panel = new JPanel();
		frmProvinceColorPicker.getContentPane().add(panel, BorderLayout.SOUTH);
		
		btnNewButton = new JButton("Accept");
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefinitionUniversalis.window.hexField.setText(Integer.toHexString(colorChooser.getColor().getRGB()).substring(2));
				frmProvinceColorPicker.dispose();
				frmProvinceColorPicker.setVisible(false);
			}
		});
		
		btnNewButton_1 = new JButton("Random Color");
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorChooser.setColor((int)Long.parseLong(DefinitionUniversalis.random(),16));
			}
		});
		
		btnNewButton_2 = new JButton("Check Color");
		panel.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hex = Integer.toHexString(colorChooser.getColor().getRGB()).substring(2);
				boolean free = DefinitionUniversalis.isColorFree(hex);
				JOptionPane.showMessageDialog(frmProvinceColorPicker, hex + " is" + (free == false ? " not a free color" : " a free color"));
			}
		});
		
		btnNewButton_3 = new JButton("Exit");
		panel.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmProvinceColorPicker.dispose();
				frmProvinceColorPicker.setVisible(false);
			}
		});
	}

}
