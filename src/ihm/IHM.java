package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Panel;
import java.awt.Button;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class IHM {

	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtPolynome;
	private JTextField txtPolynome_1;
	private JTextField txtPolynomeRes;

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
		
		JLabel lblPolynome = new JLabel("Polynome 1");
		panel_2.add(lblPolynome);
		
		txtPolynome = new JTextField();
		txtPolynome.setText("Polynome 1");
		panel_2.add(txtPolynome);
		txtPolynome.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		
		JLabel lblPolynome_1 = new JLabel("Polynome 2");
		panel_3.add(lblPolynome_1);
		
		txtPolynome_1 = new JTextField();
		txtPolynome_1.setText("Polynome 2");
		panel_3.add(txtPolynome_1);
		txtPolynome_1.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.CENTER);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{162, 162, 162, 162, 0};
		gbl_panel_4.rowHeights = new int[]{137, 137, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JRadioButton rdbtnAddition = new JRadioButton("Addition");
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
		GridBagConstraints gbc_rdbtnMultiplication = new GridBagConstraints();
		gbc_rdbtnMultiplication.fill = GridBagConstraints.VERTICAL;
		gbc_rdbtnMultiplication.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMultiplication.gridx = 1;
		gbc_rdbtnMultiplication.gridy = 0;
		panel_4.add(rdbtnMultiplication, gbc_rdbtnMultiplication);
		buttonGroup.add(rdbtnMultiplication);
		
		JRadioButton rdbtnModulo = new JRadioButton("Modulo");
		GridBagConstraints gbc_rdbtnModulo = new GridBagConstraints();
		gbc_rdbtnModulo.fill = GridBagConstraints.VERTICAL;
		gbc_rdbtnModulo.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnModulo.gridx = 2;
		gbc_rdbtnModulo.gridy = 0;
		panel_4.add(rdbtnModulo, gbc_rdbtnModulo);
		buttonGroup.add(rdbtnModulo);
		
		JRadioButton rdbtnExponentiation = new JRadioButton("Exponentiation");
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
		
		JLabel lblResultat = new JLabel("Resultat");
		panel_5.add(lblResultat);
		
		txtPolynomeRes = new JTextField();
		txtPolynomeRes.setText("Polynome res");
		panel_5.add(txtPolynomeRes);
		txtPolynomeRes.setColumns(10);
	}
}
