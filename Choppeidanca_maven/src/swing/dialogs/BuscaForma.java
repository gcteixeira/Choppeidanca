package choppeidanca.swing.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;

import javax.swing.JTable;

import choppeidanca.modelo.FormaPagamento;
import choppeidanca.modelosJtable.Modelo_FormaDePagamento;
import choppeidanca.persistencia.FormaPagamentoDAO;
import choppeidanca.utils.Singleton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Insets;

public class BuscaForma extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscaForma dialog = new BuscaForma();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	Modelo_FormaDePagamento mf = new Modelo_FormaDePagamento();

	public BuscaForma() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		carregaDados();
		
		setModal(true);
		setTitle("Choppeidan\u00E7a - Movimento - Busca Forma de Pagamento");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JLabel lblSelecioneUmaForma = new JLabel(
				"Selecione uma Forma de Pagamento da tabela e clique em OK");
		GridBagConstraints gbc_lblSelecioneUmaForma = new GridBagConstraints();
		gbc_lblSelecioneUmaForma.gridwidth = 2;
		gbc_lblSelecioneUmaForma.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelecioneUmaForma.gridx = 0;
		gbc_lblSelecioneUmaForma.gridy = 0;
		contentPanel.add(lblSelecioneUmaForma, gbc_lblSelecioneUmaForma);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPanel.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(mf);

		JButton btnNovaForma = new JButton("Nova Forma");
		btnNovaForma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Runnable r = new Runnable() {

					@Override
					public void run() {

						CadForma cfd = new CadForma();
						cfd.setVisible(true);
						
						while (cfd.isShowing()) {
							
						}

						carregaDados();
						mf.fireTableDataChanged();
					}
				};
				Thread t = new Thread(r);
				t.start();

			}
		});
		GridBagConstraints gbc_btnNovaForma = new GridBagConstraints();
		gbc_btnNovaForma.anchor = GridBagConstraints.NORTH;
		gbc_btnNovaForma.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNovaForma.gridx = 1;
		gbc_btnNovaForma.gridy = 1;
		contentPanel.add(btnNovaForma, gbc_btnNovaForma);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Singleton s = Singleton.getInstance();

				s.fp = getInstance();

				BuscaForma.this.dispose();

			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscaForma.this.dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

	}

	protected FormaPagamento getInstance() {
		FormaPagamento fp = mf.getSelectedObject(table.getSelectedRow());
		return fp;
	}
	
	private void carregaDados() {
		FormaPagamentoDAO dao = new FormaPagamentoDAO();
		mf.setFormas(dao.listar());

	}

}
