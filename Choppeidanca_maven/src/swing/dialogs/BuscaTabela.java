package choppeidanca.swing.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;

import javax.swing.JTable;

import choppeidanca.modelo.TabelaPreco;
import choppeidanca.modelosJtable.Modelo_TabelaPreco;
import choppeidanca.persistencia.TabelaPrecoDAO;
import choppeidanca.utils.Singleton;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

public class BuscaTabela extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscaTabela dialog = new BuscaTabela();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	Modelo_TabelaPreco mtp = new Modelo_TabelaPreco();

	public BuscaTabela() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		carregaDados();
		
		setTitle("Choppeidan\u00E7a - Busca Tabela Pre\u00E7o\r\n");
		setModal(true);
		setBounds(100, 100, 755, 463);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPanel.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(mtp);

		JButton btnNovaTabelaPreo = new JButton("Nova Tabela Pre\u00E7o");
		btnNovaTabelaPreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Runnable r = new Runnable() {

					@Override
					public void run() {

						BuscaTabela.this.setModal(false);

						CadTabelaPreco ctp = new CadTabelaPreco();
						ctp.setVisible(true);

						while (ctp.isShowing()) {

						}

						BuscaTabela.this.setModal(true);
					}
				};
				Thread t = new Thread(r);
				t.start();
			}
		});
		GridBagConstraints gbc_btnNovaTabelaPreo = new GridBagConstraints();
		gbc_btnNovaTabelaPreo.anchor = GridBagConstraints.NORTH;
		gbc_btnNovaTabelaPreo.gridx = 1;
		gbc_btnNovaTabelaPreo.gridy = 0;
		contentPanel.add(btnNovaTabelaPreo, gbc_btnNovaTabelaPreo);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Singleton s = Singleton.getInstance();
				s.tp = getInstance();

				Date datahj = new Date();
				
				if (s.tp.getDataValidade().after(datahj)) {
					BuscaTabela.this.dispose();							
				} else if (s.tp.getDataValidade().before(datahj)) {
					JOptionPane.showMessageDialog(null, "Essa tabela preço está vencida");
				}
				
				
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscaTabela.this.dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

	}

	private void carregaDados() {
		TabelaPrecoDAO dao = new TabelaPrecoDAO();
		mtp.setTabelas(dao.listar());
		mtp.fireTableDataChanged();
	}

	protected TabelaPreco getInstance() {
		TabelaPreco tp = mtp.getSelectedObject(table.getSelectedRow());
		return tp;
	}
	
}
