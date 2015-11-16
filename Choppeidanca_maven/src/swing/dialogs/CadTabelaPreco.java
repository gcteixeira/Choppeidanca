package choppeidanca.swing.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import choppeidanca.modelo.TabelaPreco;
import choppeidanca.modelosJtable.Modelo_TabelaPreco;
import choppeidanca.persistencia.TabelaPrecoDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadTabelaPreco extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTable tbTabelaPreco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadTabelaPreco dialog = new CadTabelaPreco();
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
	private JButton btnExcluir;
	private JButton btnEditar;
	private JButton btnSalvar;
	private JFormattedTextField txtValidade;
	private ImageIcon imgAlert = new ImageIcon(getClass().getResource(
			"/choppeidanca/imagens/error.png"));
	
	
	public CadTabelaPreco() {

		carregaDados();

		setTitle("Choppeidan\u00E7a - Cadastro de Tabela de Pre\u00E7o");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 715, 426);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 44, 400, 16, 117, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JLabel lblNome = new JLabel("Nome");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		contentPanel.add(lblNome, gbc_lblNome);

		txtNome = new JTextField();
		GridBagConstraints gbc_txtNome = new GridBagConstraints();
		gbc_txtNome.insets = new Insets(0, 0, 5, 5);
		gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNome.gridx = 1;
		gbc_txtNome.gridy = 0;
		contentPanel.add(txtNome, gbc_txtNome);
		txtNome.setColumns(10);

		JLabel lblData = new JLabel("Data Validade");
		GridBagConstraints gbc_lblData = new GridBagConstraints();
		gbc_lblData.insets = new Insets(0, 0, 5, 5);
		gbc_lblData.anchor = GridBagConstraints.EAST;
		gbc_lblData.gridx = 2;
		gbc_lblData.gridy = 0;
		contentPanel.add(lblData, gbc_lblData);

		MaskFormatter mascaradata = null;
		try {
			mascaradata = new MaskFormatter("##/##/####");
			mascaradata.setPlaceholderCharacter('_');
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		txtValidade = new JFormattedTextField(mascaradata);
		GridBagConstraints gbc_txtValidade = new GridBagConstraints();
		gbc_txtValidade.insets = new Insets(0, 0, 5, 5);
		gbc_txtValidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtValidade.gridx = 3;
		gbc_txtValidade.gridy = 0;
		contentPanel.add(txtValidade, gbc_txtValidade);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TabelaPreco tp = new TabelaPreco();

					tp.setNome(txtNome.getText());

					SimpleDateFormat formatIn = new SimpleDateFormat(
							"dd/MM/yyyy");
					String str = txtValidade.getText();
					Date date;
					try {

						date = formatIn.parse(str);
						java.sql.Date datesql = new java.sql.Date(date
								.getTime());
						tp.setDataValidade(datesql);

						TabelaPrecoDAO dao = new TabelaPrecoDAO();
						dao.inserir(tp);
						
						carregaDados();
						limparCampos();

						JOptionPane.showMessageDialog(null,
								"Tabela Preco cadastrado com sucesso");

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Data Invalida");
						System.err.println(e);
					}

				} catch (Exception e3) {
					JOptionPane.showMessageDialog(null,
							"Erro ao cadastrar Tabela de Preço");
				}

			}
		});
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.insets = new Insets(0, 0, 5, 0);
		gbc_btnSalvar.gridx = 4;
		gbc_btnSalvar.gridy = 0;
		contentPanel.add(btnSalvar, gbc_btnSalvar);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPanel.add(scrollPane, gbc_scrollPane);

		tbTabelaPreco = new JTable();
		tbTabelaPreco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {

					TabelaPreco tp = getInstance();

					txtNome.setText(tp.getNome());

					txtValidade.setText(mtp.retornaDate(tp.getDataValidade()));

					btnSalvar.setEnabled(false);
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);

				}
			}
		});
		scrollPane.setViewportView(tbTabelaPreco);
		tbTabelaPreco.setModel(mtp);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					TabelaPreco tp = getInstance();

					tp.setNome(txtNome.getText());

					SimpleDateFormat formatIn = new SimpleDateFormat(
							"dd/MM/yyyy");
					String str = txtValidade.getText();
					Date date;
					try {

						date = formatIn.parse(str);
						java.sql.Date datesql = new java.sql.Date(date
								.getTime());
						tp.setDataValidade(datesql);

						TabelaPrecoDAO dao = new TabelaPrecoDAO();
						dao.alterar(tp);

						carregaDados();
						JOptionPane.showMessageDialog(null,
								"Alteração Feita com Sucesso");

						limparCampos();

						btnSalvar.setEnabled(true);
						btnEditar.setEnabled(false);
						btnExcluir.setEnabled(false);

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Data Invalida");
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao Alterar");
				}

			}
		});
		btnEditar.setEnabled(false);
		buttonPane.add(btnEditar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TabelaPreco tp = getInstance();
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int result = JOptionPane.showConfirmDialog(null,
							"Deseja excluir a tabela de preço" + tp.getNome()
									+ "?", "Deseja realizar essa operação?",
							dialogButton);

					if (result == JOptionPane.YES_NO_OPTION) {

						TabelaPrecoDAO dao = new TabelaPrecoDAO();

						if (dao.deletar(tp.getId()) == "NO") {
							JOptionPane.showMessageDialog(null,
									"Não foi possivel excluir esse produto",
									"Problemas ao Excluir", 1, imgAlert);
						} else {
							JOptionPane.showMessageDialog(null,
									"Exclusão feita com sucesso");
							limparCampos();
							carregaDados();
							btnSalvar.setEnabled(true);
							btnEditar.setEnabled(false);
							btnExcluir.setEnabled(false);
						}

					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"Problemas ao excluir essa tabela");
				}

			}
		});
		btnExcluir.setEnabled(false);
		buttonPane.add(btnExcluir);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadTabelaPreco.this.dispose();
			}
		});
		buttonPane.add(cancelButton);

	}

	private void carregaDados() {
		TabelaPrecoDAO dao = new TabelaPrecoDAO();
		mtp.setTabelas(dao.listar());
		mtp.fireTableDataChanged();
	}

	protected void limparCampos() {
		txtNome.setText("");
		txtValidade.setText("");
	}

	private TabelaPreco getInstance() {
		TabelaPreco tp = mtp.getSelectedObject(tbTabelaPreco.getSelectedRow());
		return tp;
	}

}
