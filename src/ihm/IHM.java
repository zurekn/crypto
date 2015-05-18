package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import java.awt.Insets;

import javax.swing.JRadioButton;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IHM {

	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtPolynome;
	private JTextField txtPolynome_1;
	private JTextField txtPolynomeRes;
	private JTextField txtP1;
	private JTextField txtOpperation;
	private JTextField txtP2;
	private IHMHandler handler = new IHMHandler();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IHM window = new IHM();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IHM() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 673, 448);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Polynome", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		JLabel lblPolynome = new JLabel("Polynome 1 :");
		panel_2.add(lblPolynome);
		
		txtPolynome = new JTextField();
		txtPolynome.setToolTipText("Mettez les indices séparés par une virgule , exemple (1,5,6) donne (X+X⁵+X⁶)");
		txtPolynome.setText("Polynome 1");
		panel_2.add(txtPolynome);
		txtPolynome.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		
		JLabel lblPolynome_1 = new JLabel("Polynome 2 :");
		panel_3.add(lblPolynome_1);
		
		txtPolynome_1 = new JTextField();
		txtPolynome_1.setToolTipText("Mettez les indices séparés par une virgule , exemple (1,5,6) donne (X+X⁵+X⁶)");
		txtPolynome_1.setText("Polynome 2");
		panel_3.add(txtPolynome_1);
		txtPolynome_1.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.CENTER);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{162, 162, 162, 162, 0};
		gbl_panel_4.rowHeights = new int[]{101, 93, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JRadioButton rdbtnAddition = new JRadioButton("Addition");
		rdbtnAddition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				handler.setOpperation(arg0.getActionCommand());
			}
		});
		rdbtnAddition.setToolTipText("Addition entre le polynome 1 et 2");
		rdbtnAddition.setSelected(true);
		GridBagConstraints gbc_rdbtnAddition = new GridBagConstraints();
		gbc_rdbtnAddition.anchor = GridBagConstraints.NORTH;
		gbc_rdbtnAddition.fill = GridBagConstraints.VERTICAL;
		gbc_rdbtnAddition.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAddition.gridx = 0;
		gbc_rdbtnAddition.gridy = 0;
		panel_4.add(rdbtnAddition, gbc_rdbtnAddition);
		buttonGroup.add(rdbtnAddition);
		
		JRadioButton rdbtnMultiplication = new JRadioButton("Multiplication");
		rdbtnMultiplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				handler.setOpperation(arg0.getActionCommand());
			}
		});
		rdbtnMultiplication.setToolTipText("Multiplication entre le polynome 1 et 2");
		GridBagConstraints gbc_rdbtnMultiplication = new GridBagConstraints();
		gbc_rdbtnMultiplication.fill = GridBagConstraints.VERTICAL;
		gbc_rdbtnMultiplication.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMultiplication.gridx = 1;
		gbc_rdbtnMultiplication.gridy = 0;
		panel_4.add(rdbtnMultiplication, gbc_rdbtnMultiplication);
		buttonGroup.add(rdbtnMultiplication);
		
		JRadioButton rdbtnModulo = new JRadioButton("Modulo");
		rdbtnModulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handler.setOpperation(e.getActionCommand());
			}
		});
		rdbtnModulo.setToolTipText("Modulo du polynome 1, Mettre la valeur du modulo dans la case polynome 2\r\n");
		GridBagConstraints gbc_rdbtnModulo = new GridBagConstraints();
		gbc_rdbtnModulo.fill = GridBagConstraints.VERTICAL;
		gbc_rdbtnModulo.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnModulo.gridx = 2;
		gbc_rdbtnModulo.gridy = 0;
		panel_4.add(rdbtnModulo, gbc_rdbtnModulo);
		buttonGroup.add(rdbtnModulo);
		
		JRadioButton rdbtnExponentiation = new JRadioButton("Exponentiation");
		rdbtnExponentiation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handler.setOpperation(e.getActionCommand());
			}
		});
		rdbtnExponentiation.setToolTipText("Exponentiation du polynome 1, mettre la valeur de l'exponentielle dans la casse polynome 2");
		GridBagConstraints gbc_rdbtnExponentiation = new GridBagConstraints();
		gbc_rdbtnExponentiation.fill = GridBagConstraints.VERTICAL;
		gbc_rdbtnExponentiation.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnExponentiation.gridx = 3;
		gbc_rdbtnExponentiation.gridy = 0;
		panel_4.add(rdbtnExponentiation, gbc_rdbtnExponentiation);
		buttonGroup.add(rdbtnExponentiation);
		
		JLabel label = new JLabel(" ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		panel_4.add(label, gbc_label);
		
		JButton btnCalculer = new JButton("Calculer");
		btnCalculer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String result = handler.calculate(txtPolynome.getText(), txtPolynome_1.getText());
				if(result.equals(null))
					return;
				txtP1.setText(handler.getP1());
				txtP2.setText(handler.getP2());
				txtOpperation.setText(handler.getOpperation());
				txtPolynomeRes.setText(result);
			}
		});
		GridBagConstraints gbc_btnCalculer = new GridBagConstraints();
		gbc_btnCalculer.gridwidth = 2;
		gbc_btnCalculer.insets = new Insets(0, 0, 0, 5);
		gbc_btnCalculer.gridx = 1;
		gbc_btnCalculer.gridy = 1;
		panel_4.add(btnCalculer, gbc_btnCalculer);
		
		JLabel label_1 = new JLabel(" ");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.fill = GridBagConstraints.BOTH;
		gbc_label_1.insets = new Insets(0, 0, 0, 5);
		gbc_label_1.gridx = 2;
		gbc_label_1.gridy = 1;
		panel_4.add(label_1, gbc_label_1);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6, BorderLayout.CENTER);
		
		JLabel lblResultat = new JLabel("Resultat");
		panel_6.add(lblResultat);
		
		txtPolynomeRes = new JTextField();
		panel_6.add(txtPolynomeRes);
		txtPolynomeRes.setEditable(false);
		txtPolynomeRes.setText("Polynome res");
		txtPolynomeRes.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_7.getLayout();
		panel_5.add(panel_7, BorderLayout.NORTH);
		
		JLabel lblOpperation = new JLabel("Opperation :");
		panel_7.add(lblOpperation);
		
		txtP1 = new JTextField();
		txtP1.setEditable(false);
		txtP1.setText("P1");
		panel_7.add(txtP1);
		txtP1.setColumns(10);
		
		txtOpperation = new JTextField();
		txtOpperation.setEditable(false);
		txtOpperation.setText("Opperation");
		panel_7.add(txtOpperation);
		txtOpperation.setColumns(10);
		
		txtP2 = new JTextField();
		txtP2.setEditable(false);
		txtP2.setText("P2");
		panel_7.add(txtP2);
		txtP2.setColumns(10);
	}
}
