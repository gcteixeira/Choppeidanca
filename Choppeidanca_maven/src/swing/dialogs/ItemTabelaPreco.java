package choppeidanca.swing.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import choppeidanca.modelo.Produto;
import choppeidanca.modelo.TabelaPreco;
import choppeidanca.modelo.TabelaPrecoDetalhe;
import choppeidanca.modelosJtable.Modelo_TabelaPrecoDetalhe;
import choppeidanca.persistencia.TabelaPDetalheDAO;
import choppeidanca.utils.Singleton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class ItemTabelaPreco extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTabela;
	private JTextField txtProduto;
	private JTextField txtValor;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ItemTabelaPreco dialog = new ItemTabelaPreco();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	TabelaPreco tp = new TabelaPreco();
	Produto p = new Produto();
	Modelo_TabelaPrecoDetalhe mtpd = new Modelo_TabelaPrecoDetalhe();
	Singleton s = Singleton.getInstance();
	private JButton btnSalvar;
	private JButton btnEditar;
	private JButton btnExcluir;

	public ItemTabelaPreco() {
		
		carregaDados();
		
		setTitle("Choppeidan\u00E7a - Item Tabela Pre\u00E7o");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 791, 459);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, -15, 95,
				0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0,
				0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JLabel lblTabelaPreo = new JLabel("Tabela Pre\u00E7o");
		GridBagConstraints gbc_lblTabelaPreo = new GridBagConstraints();
		gbc_lblTabelaPreo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTabelaPreo.gridx = 0;
		gbc_lblTabelaPreo.gridy = 0;
		contentPanel.add(lblTabelaPreo, gbc_lblTabelaPreo);

		JButton btnPesquisaTabela = new JButton("");
		btnPesquisaTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Runnable r = new Runnable() {
					
					@Override
					public void run() {

						BuscaTabela bt = new BuscaTabela();
						bt.setVisible(true);
						
						while (bt.isShowing()) {
							
						}
												
						txtTabela.setText(s.tp.getNome());
						tp = s.tp;
												
					}
				};
				
				Thread t = new Thread(r);
				t.start();

			}
		});
		btnPesquisaTabela.setIcon(new ImageIcon(ItemTabelaPreco.class
				.getResource("/choppeidanca/imagens/magnifier.png")));
		GridBagConstraints gbc_btnPesquisaTabela = new GridBagConstraints();
		gbc_btnPesquisaTabela.insets = new Insets(0, 0, 5, 5);
		gbc_btnPesquisaTabela.fill = GridBagConstraints.BOTH;
		gbc_btnPesquisaTabela.gridx = 1;
		gbc_btnPesquisaTabela.gridy = 0;
		contentPanel.add(btnPesquisaTabela, gbc_btnPesquisaTabela);

		txtTabela = new JTextField();
		txtTabela.setEditable(false);
		GridBagConstraints gbc_txtTabela = new GridBagConstraints();
		gbc_txtTabela.insets = new Insets(0, 0, 5, 5);
		gbc_txtTabela.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTabela.gridx = 2;
		gbc_txtTabela.gridy = 0;
		contentPanel.add(txtTabela, gbc_txtTabela);
		txtTabela.setColumns(10);

		JLabel lblProduto = new JLabel("Produto");
		GridBagConstraints gbc_lblProduto = new GridBagConstraints();
		gbc_lblProduto.insets = new Insets(0, 0, 5, 5);
		gbc_lblProduto.gridx = 3;
		gbc_lblProduto.gridy = 0;
		contentPanel.add(lblProduto, gbc_lblProduto);

		JButton btnPesquisaProduto = new JButton("");
		btnPesquisaProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Runnable r = new Runnable() {
					
					@Override
					public void run() {

						BuscaProduto bp = new BuscaProduto();
						bp.setVisible(true);
						
						while (bp.isShowing()) {
							
						}
						
						
						txtProduto.setText(s.produto.getDescricao());
						p = s.produto;
					}
				};
				
				Thread t = new Thread(r);
				t.start();
				
				// BuscaProduto bp = new BuscaProduto();
				// bp.setVisible(true);

			}
		});
		btnPesquisaProduto.setIcon(new ImageIcon(ItemTabelaPreco.class
				.getResource("/choppeidanca/imagens/magnifier.png")));
		GridBagConstraints gbc_btnPesquisaProduto = new GridBagConstraints();
		gbc_btnPesquisaProduto.insets = new Insets(0, 0, 5, 5);
		gbc_btnPesquisaProduto.fill = GridBagConstraints.BOTH;
		gbc_btnPesquisaProduto.gridx = 4;
		gbc_btnPesquisaProduto.gridy = 0;
		contentPanel.add(btnPesquisaProduto, gbc_btnPesquisaProduto);

		txtProduto = new JTextField();
		txtProduto.setEditable(false);
		GridBagConstraints gbc_txtProduto = new GridBagConstraints();
		gbc_txtProduto.insets = new Insets(0, 0, 5, 5);
		gbc_txtProduto.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProduto.gridx = 5;
		gbc_txtProduto.gridy = 0;
		contentPanel.add(txtProduto, gbc_txtProduto);
		txtProduto.setColumns(10);

		JLabel lblValor = new JLabel("Valor");
		GridBagConstraints gbc_lblValor = new GridBagConstraints();
		gbc_lblValor.insets = new Insets(0, 0, 5, 5);
		gbc_lblValor.anchor = GridBagConstraints.EAST;
		gbc_lblValor.gridx = 6;
		gbc_lblValor.gridy = 0;
		contentPanel.add(lblValor, gbc_lblValor);

		txtValor = new JTextField();
		GridBagConstraints gbc_txtValor = new GridBagConstraints();
		gbc_txtValor.insets = new Insets(0, 0, 5, 5);
		gbc_txtValor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtValor.gridx = 7;
		gbc_txtValor.gridy = 0;
		contentPanel.add(txtValor, gbc_txtValor);
		txtValor.setColumns(10);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					TabelaPrecoDetalhe tpd = new TabelaPrecoDetalhe();

					tpd.setProduto(p);
					tpd.setTabelaPreco(tp);
					tpd.setValor(Double.parseDouble(txtValor.getText()));

					TabelaPDetalheDAO dao = new TabelaPDetalheDAO();
					dao.inserir(tpd);

					mtpd.fireTableDataChanged();
					limparCampos();
					carregaDados();
					JOptionPane.showMessageDialog(null,
							"Cadastrado com sucesso JAO");

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
				}

			}
		});
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.insets = new Insets(0, 0, 5, 0);
		gbc_btnSalvar.gridx = 8;
		gbc_btnSalvar.gridy = 0;
		contentPanel.add(btnSalvar, gbc_btnSalvar);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 9;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPanel.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {

					TabelaPrecoDetalhe tpd = getInstance();
					txtProduto.setText(tpd.getProduto().getDescricao());
					txtTabela.setText(tpd.getTabelaPreco().getNome());
					txtValor.setText(Double.toString(tpd.getValor()));

					btnSalvar.setEnabled(false);
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);

				}

			}
		});
		scrollPane.setViewportView(table);
		table.setModel(mtpd);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TabelaPrecoDetalhe tpd = getInstance();

					tpd.setProduto(p);
					tpd.setTabelaPreco(tp);
					tpd.setValor(Double.parseDouble(txtValor.getText()));

					TabelaPDetalheDAO dao = new TabelaPDetalheDAO();
					dao.alterar(tpd);

					mtpd.fireTableDataChanged();

					JOptionPane.showMessageDialog(null,
							"alteraçao feita com sucesso JAO");

					limparCampos();
					carregaDados();
					btnSalvar.setEnabled(true);
					btnEditar.setEnabled(false);
					btnExcluir.setEnabled(false);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnEditar.setEnabled(false);
		buttonPane.add(btnEditar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					TabelaPrecoDetalhe tpd = getInstance();
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int result = JOptionPane.showConfirmDialog(null,
							"Deseja excluir esses itens da tabela preço ?",
							"Deseja realizar essa operação?", dialogButton);

					if (result == JOptionPane.YES_NO_OPTION) {
						TabelaPDetalheDAO dao = new TabelaPDetalheDAO();
						dao.deletar(tpd.getId());
						mtpd.fireTableDataChanged();

						JOptionPane.showMessageDialog(null,
								"Excluido com sucesso");

						limparCampos();
						carregaDados();
						btnSalvar.setEnabled(true);
						btnEditar.setEnabled(false);
						btnExcluir.setEnabled(false);

					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Problemas ao excluir");
				}
			}
		});
		btnExcluir.setEnabled(false);

		buttonPane.add(btnExcluir);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ItemTabelaPreco.this.dispose();
			}
		});
		buttonPane.add(cancelButton);

	}

	private void carregaDados() {
		TabelaPDetalheDAO dao = new TabelaPDetalheDAO();
		mtpd.setTabelas(dao.listar());
		mtpd.fireTableDataChanged();
		
	}

	protected void limparCampos() {
		txtProduto.setText("");
		txtTabela.setText("");
		txtValor.setText("");

	}

	private TabelaPrecoDetalhe getInstance() {
		TabelaPrecoDetalhe tp = mtpd.getSelectedObject(table.getSelectedRow());
		return tp;
	}

}
