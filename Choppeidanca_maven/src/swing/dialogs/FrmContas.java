package choppeidanca.swing.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;

import javax.swing.JTable;

import choppeidanca.modelo.Contas;
import choppeidanca.modelo.Pais;
import choppeidanca.modelosJtable.Modelo_Contas;
import choppeidanca.persistencia.ContasDAO;
import choppeidanca.persistencia.PaisDAO;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmContas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmContas dialog = new FrmContas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	Modelo_Contas mc = new Modelo_Contas();
	
	public FrmContas() {
		setTitle("Choppeidan\u00E7a - Contas");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		carregaDados();
		setBounds(100, 100, 884, 527);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPanel.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(mc);
		
		JButton btnQuitar = new JButton("Quitar Conta");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Contas c = getInstance();
				
				c.setStatus(1);
				mc.fireTableDataChanged();
				
			}
		});
		GridBagConstraints gbc_btnQuitar = new GridBagConstraints();
		gbc_btnQuitar.anchor = GridBagConstraints.EAST;
		gbc_btnQuitar.gridx = 0;
		gbc_btnQuitar.gridy = 1;
		contentPanel.add(btnQuitar, gbc_btnQuitar);

	}

	protected Contas getInstance() {
		Contas c = mc.getSelectedObject(table.getSelectedRow());
		return c;
	}

	private void carregaDados() {
		ContasDAO dao = new ContasDAO();
		mc.setContas(dao.listar());
		mc.fireTableDataChanged();
	}
	
}
