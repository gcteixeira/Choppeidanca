package choppeidanca.swing.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import TESTEPDF.Relatorio;
import choppeidanca.modelo.Cidade;
import choppeidanca.modelo.Pessoa;
import choppeidanca.modelosJtable.Modelo_PessoaFisica;
import choppeidanca.modelosJtable.Modelo_PessoaJuridica;
import choppeidanca.persistencia.PessoaDAO;
import choppeidanca.utils.Singleton;

public class BuscaPessoa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscaPessoa dialog = new BuscaPessoa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	List<Pessoa> pessoas;

	List<Pessoa> list_reserva = new ArrayList<>();

	Modelo_PessoaJuridica mpj = new Modelo_PessoaJuridica();
	Modelo_PessoaFisica mpf = new Modelo_PessoaFisica();
	Modelo_Pessoa mp = new Modelo_Pessoa();

	Singleton s = Singleton.getInstance();

	Pessoa p = new Pessoa();
	Cidade c = new Cidade();
	public BuscaPessoa() {
		setTitle("Choppeidan\u00E7a - Busca de Pessoa");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		setModal(true);

		carregaDados();

		s.p = p;
		s.p.setCidade(c);
		
		setBounds(100, 100, 777, 465);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 225, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JLabel lblNewLabel = new JLabel("Filtrar");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPanel.add(lblNewLabel, gbc_lblNewLabel);

		final JComboBox cbFiltro = new JComboBox();
		cbFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cbFiltro.getSelectedItem() == "F�sica e Jur�dica") {
					table.setModel(mp);
				} else {
					if (cbFiltro.getSelectedItem() == "F�sica") {
						for (int i = 0; i < s.pessoas.size(); i++) {
							if (s.pessoas.get(i).getTipo() == 0) {
								table.setModel(mpf);
							}
						}

					} else {
						for (int i = 0; i < s.pessoas.size(); i++) {
							if (s.pessoas.get(i).getTipo() == 1) {
								table.setModel(mpj);
							}
						}
					}

				}

			}
		});
		cbFiltro.setModel(new DefaultComboBoxModel(new String[] {
				"F\u00EDsica e Jur\u00EDdica", "F\u00EDsica", "Jur\u00EDdica" }));
		GridBagConstraints gbc_cbFiltro = new GridBagConstraints();
		gbc_cbFiltro.insets = new Insets(0, 0, 5, 5);
		gbc_cbFiltro.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbFiltro.gridx = 1;
		gbc_cbFiltro.gridy = 0;
		contentPanel.add(cbFiltro, gbc_cbFiltro);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 0;
		contentPanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 136, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JButton btnNovaPessoaFsica = new JButton("Nova Pessoa F\u00EDsica");
		btnNovaPessoaFsica.setVisible(s.statusBotao);
		btnNovaPessoaFsica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadPessoaFisica cpf = new CadPessoaFisica();
				cpf.setVisible(true);
				mp.fireTableDataChanged();
				mpf.fireTableDataChanged();
			}
		});
		GridBagConstraints gbc_btnNovaPessoaFsica = new GridBagConstraints();
		gbc_btnNovaPessoaFsica.insets = new Insets(0, 0, 0, 5);
		gbc_btnNovaPessoaFsica.gridx = 4;
		gbc_btnNovaPessoaFsica.gridy = 0;
		panel.add(btnNovaPessoaFsica, gbc_btnNovaPessoaFsica);

		JButton btnNovaPessoaJurdica = new JButton("Nova Pessoa Jur\u00EDdica");
		btnNovaPessoaJurdica.setVisible(s.statusBotao);
		btnNovaPessoaJurdica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadPessoaJuridica cpj = new CadPessoaJuridica();
				cpj.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnNovaPessoaJurdica = new GridBagConstraints();
		gbc_btnNovaPessoaJurdica.gridx = 5;
		gbc_btnNovaPessoaJurdica.gridy = 0;
		panel.add(btnNovaPessoaJurdica, gbc_btnNovaPessoaJurdica);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPanel.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(mp);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Singleton s = Singleton.getInstance();

				s.p = mp.getSelectedObject(table.getSelectedRow());

				System.out.println(s.p.getNome());
				BuscaPessoa.this.dispose();
			}
		});
		
		JButton btnRelatorio = new JButton("Relatorio");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Relatorio r = new Relatorio();
				
				r.geraRelatorio();
		        
		        
		        
				
			}
		});
		buttonPane.add(btnRelatorio);
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscaPessoa.this.dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

	}

	private void carregaDados() {
		PessoaDAO pdao = new PessoaDAO();
		Singleton s = Singleton.getInstance();
		s.pessoas = pdao.listar();
	}

	class Modelo_Pessoa extends AbstractTableModel {

		@Override
		public int getColumnCount() {
			return 9;
		}

		@Override
		public int getRowCount() {
			return Singleton.getInstance().pessoas.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return s.pessoas.get(rowIndex).getId();
			case 1:
				return s.pessoas.get(rowIndex).getNome();
			case 2:
				return s.pessoas.get(rowIndex).getApelido();
			case 3:
				return verificaTipo(s.pessoas.get(rowIndex).getTipo());
			case 4:
				return s.pessoas.get(rowIndex).getEndereco() + ", "
						+ s.pessoas.get(rowIndex).getNumero();
			case 5:
				return s.pessoas.get(rowIndex).getCidade();
			case 6:
				return s.pessoas.get(rowIndex).getTelefone();
			case 7:
				return s.pessoas.get(rowIndex).getRginscricao();
			case 8:
				return s.pessoas.get(rowIndex).getCpfcnpj();
			default:
				return "ERRO";
			}

		}

		private String verificaTipo(int tipo) {
			String statusString = null;
			if (tipo == 1) {
				statusString = "Jur�dica";
			} else if (tipo == 0) {
				statusString = "F�sica";
			}

			return statusString;
		}

		@Override
		public String getColumnName(int i) {
			switch (i) {
			case 0:
				return "ID";
			case 1:
				return "Nome / Nome Fantasia";
			case 2:
				return "Apelido / Raz�o Social";
			case 3:
				return "Tipo";
			case 4:
				return "Endere�o";
			case 5:
				return "Cidade";
			case 6:
				return "Telefone";
			case 7:
				return "RG / IE";
			case 8:
				return "CPF / CNPJ";
			default:
				return "ERRO";
			}
		}

		public Pessoa getSelectedObject(int row) {
			return s.pessoas.get(row);
		}

	}

}
