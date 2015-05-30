package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
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

import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;

import polynomial.Polynomial;
import engine.LFSR;
import engine.MasseyCore;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class IHM {

	private JFrame frmCrypto;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtPolynome;
	private JTextField txtPolynome_1;
	private JTextField txtPolynomeRes;
	private JTextField txtP1;
	private JTextField txtOpperation;
	private JTextField txtP2;
	private IHMHandler handler = new IHMHandler();
	private JTextField txtRetroaction;
	private JTextField txtLfsr;
	private JTextField txtLength;
	private JTextField txtNombre;
	private JTextField txtSuiteChiffrante;
	private JTextField txtBase;
	private JTextField txtSuiteChiffranteMassey;
	private JTextField txtFichierCrypter;
	private JTextField txtPolynomeDeRetroaction;
	private JTextField txtPolynomeDeSortie;
	private JComboBox masseyFileType;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IHM window = new IHM();
					window.frmCrypto.setVisible(true);
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
		MasseyCore.initCore();
		
		frmCrypto = new JFrame();
		frmCrypto.setTitle("Crypto");
		frmCrypto.setBounds(100, 100, 673, 448);
		frmCrypto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmCrypto.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelPolynome = new JPanel();
		tabbedPane.addTab("Polynome", null, panelPolynome, null);
		panelPolynome.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panelPolynome.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		JLabel lblPolynome = new JLabel("Polynome 1 :");
		panel_2.add(lblPolynome);
		
		txtPolynome = new JTextField();
		txtPolynome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtPolynome.getText().equals("Polynome 1"))
					txtPolynome.setText("");
			}
		});
		txtPolynome.setToolTipText("Mettez les indices sÃ©parÃ©s par une virgule , exemple (1,5,6) donne (X+Xâ�µ+Xâ�¶)");
		txtPolynome.setText("Polynome 1");
		panel_2.add(txtPolynome);
		txtPolynome.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		
		JLabel lblPolynome_1 = new JLabel("Polynome 2 :");
		panel_3.add(lblPolynome_1);
		
		txtPolynome_1 = new JTextField();
		txtPolynome_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtPolynome_1.getText().equals("Polynome 2"))
					txtPolynome_1.setText("");
			}
		});
		txtPolynome_1.setToolTipText("Mettez les indices sÃ©parÃ©s par une virgule , exemple (1,5,6) donne (X+Xâ�µ+Xâ�¶)");
		txtPolynome_1.setText("Polynome 2");
		panel_3.add(txtPolynome_1);
		txtPolynome_1.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panelPolynome.add(panel_4, BorderLayout.CENTER);
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
		panelPolynome.add(panel_5, BorderLayout.SOUTH);
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
		
		JPanel panelLFSR = new JPanel();
		tabbedPane.addTab("LFSR", null, panelLFSR, null);
		panelLFSR.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_10 = new JPanel();
		panelLFSR.add(panel_10);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWidths = new int[]{0, 0, 0, 0, 40, 0, 46, 0};
		gbl_panel_10.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_10.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_10.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_10.setLayout(gbl_panel_10);
		
		JLabel lblLfsr = new JLabel("LFSR : ");
		GridBagConstraints gbc_lblLfsr = new GridBagConstraints();
		gbc_lblLfsr.insets = new Insets(0, 0, 5, 5);
		gbc_lblLfsr.gridx = 1;
		gbc_lblLfsr.gridy = 0;
		panel_10.add(lblLfsr, gbc_lblLfsr);
		
		txtLfsr = new JTextField();
		txtLfsr.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtLfsr.getText().equals("LFSR"))
					txtLfsr.setText("");
			}
		});
		txtLfsr.setText("LFSR");
		GridBagConstraints gbc_txtLfsr = new GridBagConstraints();
		gbc_txtLfsr.gridwidth = 3;
		gbc_txtLfsr.insets = new Insets(0, 0, 5, 5);
		gbc_txtLfsr.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLfsr.gridx = 2;
		gbc_txtLfsr.gridy = 0;
		panel_10.add(txtLfsr, gbc_txtLfsr);
		txtLfsr.setColumns(10);
		
		JLabel lblBase = new JLabel("Base : ");
		GridBagConstraints gbc_lblBase = new GridBagConstraints();
		gbc_lblBase.insets = new Insets(0, 0, 5, 5);
		gbc_lblBase.anchor = GridBagConstraints.EAST;
		gbc_lblBase.gridx = 5;
		gbc_lblBase.gridy = 0;
		panel_10.add(lblBase, gbc_lblBase);
		
		txtBase = new JTextField();
		txtBase.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtBase.getText().equals("Base"))
					txtBase.setText("");
			}
		});
		txtBase.setText("Base");
		GridBagConstraints gbc_txtBase = new GridBagConstraints();
		gbc_txtBase.insets = new Insets(0, 0, 5, 0);
		gbc_txtBase.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBase.gridx = 6;
		gbc_txtBase.gridy = 0;
		panel_10.add(txtBase, gbc_txtBase);
		txtBase.setColumns(10);
		
		JLabel lblRetroaction = new JLabel("Retroaction : ");
		GridBagConstraints gbc_lblRetroaction = new GridBagConstraints();
		gbc_lblRetroaction.insets = new Insets(0, 0, 5, 5);
		gbc_lblRetroaction.gridx = 1;
		gbc_lblRetroaction.gridy = 2;
		panel_10.add(lblRetroaction, gbc_lblRetroaction);
		
		txtRetroaction = new JTextField();
		txtRetroaction.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtRetroaction.getText().equals("Retroaction"))
					txtRetroaction.setText("");
			}
		});
		txtRetroaction.setText("Retroaction");
		GridBagConstraints gbc_txtRetroaction = new GridBagConstraints();
		gbc_txtRetroaction.gridwidth = 3;
		gbc_txtRetroaction.insets = new Insets(0, 0, 5, 5);
		gbc_txtRetroaction.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRetroaction.gridx = 2;
		gbc_txtRetroaction.gridy = 2;
		panel_10.add(txtRetroaction, gbc_txtRetroaction);
		txtRetroaction.setColumns(10);
		
		JLabel lblLongueur = new JLabel("Longueur : ");
		GridBagConstraints gbc_lblLongueur = new GridBagConstraints();
		gbc_lblLongueur.insets = new Insets(0, 0, 5, 5);
		gbc_lblLongueur.gridx = 1;
		gbc_lblLongueur.gridy = 4;
		panel_10.add(lblLongueur, gbc_lblLongueur);
		
		txtLength = new JTextField();
		txtLength.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtLength.getText().equals("Length"))
					txtLength.setText("");
			}
		});
		txtLength.setText("Length");
		GridBagConstraints gbc_txtLength = new GridBagConstraints();
		gbc_txtLength.gridwidth = 3;
		gbc_txtLength.insets = new Insets(0, 0, 5, 5);
		gbc_txtLength.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLength.gridx = 2;
		gbc_txtLength.gridy = 4;
		panel_10.add(txtLength, gbc_txtLength);
		txtLength.setColumns(10);
		
		JLabel lblLongueurSuite = new JLabel("longueur suite : ");
		GridBagConstraints gbc_lblLongueurSuite = new GridBagConstraints();
		gbc_lblLongueurSuite.insets = new Insets(0, 0, 0, 5);
		gbc_lblLongueurSuite.gridx = 1;
		gbc_lblLongueurSuite.gridy = 5;
		panel_10.add(lblLongueurSuite, gbc_lblLongueurSuite);
		
		txtNombre = new JTextField();
		txtNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtNombre.getText().equals("Nombre"))
					txtNombre.setText("");
			}
		});
		txtNombre.setText("Nombre");
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.gridwidth = 2;
		gbc_txtNombre.insets = new Insets(0, 0, 0, 5);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 2;
		gbc_txtNombre.gridy = 5;
		panel_10.add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);
		
		JButton btnGenere = new JButton("Genere");
		btnGenere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSuiteChiffrante.setText(handler.genere(txtNombre.getText(), txtLfsr.getText(), txtLength.getText(), txtRetroaction.getText(), txtBase.getText()));
				txtSuiteChiffranteMassey.setText(txtSuiteChiffrante.getText());
			}
		});
		GridBagConstraints gbc_btnGenere = new GridBagConstraints();
		gbc_btnGenere.insets = new Insets(0, 0, 0, 5);
		gbc_btnGenere.gridx = 4;
		gbc_btnGenere.gridy = 5;
		panel_10.add(btnGenere, gbc_btnGenere);
		
		JButton btnNext = new JButton("Next gen");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtSuiteChiffrante.setText(handler.nextGen(txtNombre.getText()));
			}
		});
		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		gbc_btnNext.gridx = 6;
		gbc_btnNext.gridy = 5;
		panel_10.add(btnNext, gbc_btnNext);
		
		JPanel panel_9 = new JPanel();
		panelLFSR.add(panel_9);
		GridBagLayout gbl_panel_9 = new GridBagLayout();
		gbl_panel_9.columnWidths = new int[]{0, 0};
		gbl_panel_9.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_9.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_9.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_9.setLayout(gbl_panel_9);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panel_9.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{101, 48, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		final JTextField textAreaDragDrop = new JTextField();
		textAreaDragDrop.setHorizontalAlignment(SwingConstants.CENTER);
		textAreaDragDrop.setText("Drag and Drop the file to crypt here");
		new FileDrop( System.out, textAreaDragDrop, new FileDrop.Listener() {   
		 	public void filesDropped( java.io.File[] files ){
		 		for( int i = 0; i < files.length; i++ ){  
		 			try{   textAreaDragDrop.setText(( files[i].getCanonicalPath() ));
		             } catch( java.io.IOException e ) {}
		         }  
		     }   
		 });
		GridBagConstraints gbc_textAreaDragDrop = new GridBagConstraints();
		gbc_textAreaDragDrop.insets = new Insets(0, 0, 5, 0);
		gbc_textAreaDragDrop.gridwidth = 2;
		gbc_textAreaDragDrop.fill = GridBagConstraints.BOTH;
		gbc_textAreaDragDrop.gridx = 0;
		gbc_textAreaDragDrop.gridy = 0;
		panel.add(textAreaDragDrop, gbc_textAreaDragDrop);
		
		JButton btnCrypt = new JButton("Crypt");
		btnCrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String output = JOptionPane.showInputDialog("Entrez le chemin du fichier de sortie : \n "+textAreaDragDrop.getText()+"\n Laissez vide pour avoir le chemin par default");
				if(output.isEmpty()){
					File file = new File(textAreaDragDrop.getText());
					output = file.getParent()+File.separator+"encrypted"+file.getName();
				}
				handler.crypt(txtLfsr.getText(), txtLength.getText(), txtRetroaction.getText(), txtBase.getText(), textAreaDragDrop.getText(), output);
			}
		});
		GridBagConstraints gbc_btnCrypt = new GridBagConstraints();
		gbc_btnCrypt.insets = new Insets(0, 0, 0, 5);
		gbc_btnCrypt.gridx = 0;
		gbc_btnCrypt.gridy = 1;
		panel.add(btnCrypt, gbc_btnCrypt);
		
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String output = JOptionPane.showInputDialog("Entrez le chemin du fichier de sortie : \n "+textAreaDragDrop.getText()+"\n Laissez vide pour avoir le chemin par default");
				if(output.isEmpty()){
					File file = new File(textAreaDragDrop.getText());
					output = file.getParent()+File.separator+"decrypted"+file.getName();
				}
				handler.decrypt(txtLfsr.getText(), txtLength.getText(), txtRetroaction.getText(), txtBase.getText(), textAreaDragDrop.getText(), output);
			}
		});
		GridBagConstraints gbc_btnDecrypt = new GridBagConstraints();
		gbc_btnDecrypt.gridx = 1;
		gbc_btnDecrypt.gridy = 1;
		panel.add(btnDecrypt, gbc_btnDecrypt);
		
		JLabel lblSortie = new JLabel("Suite Chiffrante");
		GridBagConstraints gbc_lblSortie = new GridBagConstraints();
		gbc_lblSortie.anchor = GridBagConstraints.NORTH;
		gbc_lblSortie.insets = new Insets(0, 0, 5, 0);
		gbc_lblSortie.gridx = 0;
		gbc_lblSortie.gridy = 1;
		panel_9.add(lblSortie, gbc_lblSortie);
		
		txtSuiteChiffrante = new JTextField();
		txtSuiteChiffrante.setHorizontalAlignment(SwingConstants.CENTER);
		txtSuiteChiffrante.setEditable(false);
		GridBagConstraints gbc_txtSuiteChiffrante = new GridBagConstraints();
		gbc_txtSuiteChiffrante.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSuiteChiffrante.gridx = 0;
		gbc_txtSuiteChiffrante.gridy = 2;
		panel_9.add(txtSuiteChiffrante, gbc_txtSuiteChiffrante);
		txtSuiteChiffrante.setColumns(10);
		
		JPanel panelMassey = new JPanel();
		tabbedPane.addTab("Massey", null, panelMassey, null);
		panelMassey.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_8 = new JPanel();
		panelMassey.add(panel_8);
		GridBagLayout gbl_panel_8 = new GridBagLayout();
		gbl_panel_8.columnWidths = new int[]{0, 0, 0};
		gbl_panel_8.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_8.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_8.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_8.setLayout(gbl_panel_8);
		
		JLabel lblSuiteChiffrante = new JLabel("Suite Chiffrante : ");
		GridBagConstraints gbc_lblSuiteChiffrante = new GridBagConstraints();
		gbc_lblSuiteChiffrante.insets = new Insets(0, 0, 5, 5);
		gbc_lblSuiteChiffrante.anchor = GridBagConstraints.EAST;
		gbc_lblSuiteChiffrante.gridx = 0;
		gbc_lblSuiteChiffrante.gridy = 0;
		panel_8.add(lblSuiteChiffrante, gbc_lblSuiteChiffrante);
		
		txtSuiteChiffranteMassey = new JTextField();
		txtSuiteChiffranteMassey.setText("Suite Chiffrante");
		GridBagConstraints gbc_txtSuiteChiffranteMassey = new GridBagConstraints();
		gbc_txtSuiteChiffranteMassey.insets = new Insets(0, 0, 5, 0);
		gbc_txtSuiteChiffranteMassey.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSuiteChiffranteMassey.gridx = 1;
		gbc_txtSuiteChiffranteMassey.gridy = 0;
		panel_8.add(txtSuiteChiffranteMassey, gbc_txtSuiteChiffranteMassey);
		txtSuiteChiffranteMassey.setColumns(10);
		
		txtFichierCrypter = new JTextField();
		new FileDrop( System.out, txtFichierCrypter, new FileDrop.Listener() {   
		 	public void filesDropped( java.io.File[] files ){
		 		for( int i = 0; i < files.length; i++ ){  
		 			try{   
		 				txtFichierCrypter.setText(( files[i].getCanonicalPath() ));
		 				getFileEnding(files[i].getCanonicalPath());
		             } catch( java.io.IOException e ) {}
		         }  
		     }   
		 });
		txtFichierCrypter.setHorizontalAlignment(SwingConstants.CENTER);
		txtFichierCrypter.setText("Deplacer le fichier crypter ici");
		GridBagConstraints gbc_txtFichierCrypter = new GridBagConstraints();
		gbc_txtFichierCrypter.gridwidth = 3;
		gbc_txtFichierCrypter.gridheight = 4;
		gbc_txtFichierCrypter.insets = new Insets(0, 0, 5, 0);
		gbc_txtFichierCrypter.fill = GridBagConstraints.BOTH;
		gbc_txtFichierCrypter.gridx = 0;
		gbc_txtFichierCrypter.gridy = 1;
		panel_8.add(txtFichierCrypter, gbc_txtFichierCrypter);
		txtFichierCrypter.setColumns(10);
		
		JPanel panel_12 = new JPanel();
		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
		gbc_panel_12.insets = new Insets(0, 0, 5, 0);
		gbc_panel_12.gridwidth = 2;
		gbc_panel_12.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_12.gridx = 0;
		gbc_panel_12.gridy = 5;
		panel_8.add(panel_12, gbc_panel_12);
		panel_12.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnRecuptLfsr = new JButton("Recupt LFSR");
		btnRecuptLfsr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LFSR lfsr = MasseyCore.findLFSR(txtSuiteChiffranteMassey.getText());
				txtPolynomeDeRetroaction.setText(new Polynomial(lfsr.getCoef()).toString());
				txtPolynomeDeSortie.setText(Integer.toBinaryString(lfsr.getEtatInit()));
			}
		});
		panel_12.add(btnRecuptLfsr);
		
		JButton btnDecrypter = new JButton("Decrypter");
		btnDecrypter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(masseyFileType.getSelectedItem().equals("") )
					JOptionPane.showMessageDialog(null, "Le type de fichier ne peut être vide",  "Type error", JOptionPane.ERROR_MESSAGE);
				else{
				String output = JOptionPane.showInputDialog("Entrez le chemin du fichier de sortie : \n "+txtFichierCrypter.getText()+"\n Laissez vide pour avoir le chemin par default");
				if(output.isEmpty()){
					File file = new File(txtFichierCrypter.getText());
					output = file.getParent()+File.separator+"MasseyDecrypted"+file.getName();
				}
				
					LFSR lfsr = handler.decryptMassey(txtFichierCrypter.getText(), output, (String)masseyFileType.getSelectedItem());
					txtPolynomeDeRetroaction.setText(new Polynomial(lfsr.getCoef()).toString());
					txtPolynomeDeSortie.setText(Integer.toBinaryString(lfsr.getEtatInit()));

				}
				
			}
		});
		panel_12.add(btnDecrypter);
		
		masseyFileType = new JComboBox();
		masseyFileType.setToolTipText("Selectionnez le type de fichier si necessaire.");
		masseyFileType.setModel(new DefaultComboBoxModel(new String[] {"", "pdf", "png", "jpg", "jpeg", "doc", "xml"}));
		panel_12.add(masseyFileType);
		
		JPanel panel_11 = new JPanel();
		panelMassey.add(panel_11);
		GridBagLayout gbl_panel_11 = new GridBagLayout();
		gbl_panel_11.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_11.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_11.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_11.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_11.setLayout(gbl_panel_11);
		
		JLabel lblPolynomeDeRetroaction = new JLabel("Polynome de retroaction : ");
		GridBagConstraints gbc_lblPolynomeDeRetroaction = new GridBagConstraints();
		gbc_lblPolynomeDeRetroaction.insets = new Insets(0, 0, 5, 5);
		gbc_lblPolynomeDeRetroaction.anchor = GridBagConstraints.EAST;
		gbc_lblPolynomeDeRetroaction.gridx = 0;
		gbc_lblPolynomeDeRetroaction.gridy = 1;
		panel_11.add(lblPolynomeDeRetroaction, gbc_lblPolynomeDeRetroaction);
		
		txtPolynomeDeRetroaction = new JTextField();
		txtPolynomeDeRetroaction.setEditable(false);
		txtPolynomeDeRetroaction.setText("Polynome de retroaction");
		GridBagConstraints gbc_txtPolynomeDeRetroaction = new GridBagConstraints();
		gbc_txtPolynomeDeRetroaction.insets = new Insets(0, 0, 5, 5);
		gbc_txtPolynomeDeRetroaction.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPolynomeDeRetroaction.gridx = 1;
		gbc_txtPolynomeDeRetroaction.gridy = 1;
		panel_11.add(txtPolynomeDeRetroaction, gbc_txtPolynomeDeRetroaction);
		txtPolynomeDeRetroaction.setColumns(10);
		
		JLabel lblPolynomeDeSortie = new JLabel("Polynome d'initialisation : ");
		GridBagConstraints gbc_lblPolynomeDeSortie = new GridBagConstraints();
		gbc_lblPolynomeDeSortie.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblPolynomeDeSortie.insets = new Insets(0, 0, 0, 5);
		gbc_lblPolynomeDeSortie.gridx = 0;
		gbc_lblPolynomeDeSortie.gridy = 2;
		panel_11.add(lblPolynomeDeSortie, gbc_lblPolynomeDeSortie);
		
		txtPolynomeDeSortie = new JTextField();
		txtPolynomeDeSortie.setEditable(false);
		txtPolynomeDeSortie.setText("Polynome");
		GridBagConstraints gbc_txtPolynomeDeSortie = new GridBagConstraints();
		gbc_txtPolynomeDeSortie.insets = new Insets(0, 0, 0, 5);
		gbc_txtPolynomeDeSortie.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPolynomeDeSortie.gridx = 1;
		gbc_txtPolynomeDeSortie.gridy = 2;
		panel_11.add(txtPolynomeDeSortie, gbc_txtPolynomeDeSortie);
		txtPolynomeDeSortie.setColumns(10);
	}
	
	private String getFileEnding(String path){
		if(path.endsWith("pdf")){
			masseyFileType.setSelectedItem("pdf");
			return "pdf";
		}
		if(path.endsWith("png")){
			masseyFileType.setSelectedItem("png");
			return "png";
		}
		if(path.endsWith("jpg")){
			masseyFileType.setSelectedItem("jpg");
			return "jpg";
		}
		if(path.endsWith("doc")){
			masseyFileType.setSelectedItem("doc");
			return "doc";
		}
		if(path.endsWith("xml")){
			masseyFileType.setSelectedItem("xml");
			return "xml";
		}
		if(path.endsWith("jpeg")){
			masseyFileType.setSelectedItem("jpeg");
			return "jpeg";
		}
		if(path.endsWith("png")){
			masseyFileType.setSelectedItem("png");
			return "png";
		}
		return ""	;
	}

}
