 package choppeidanca.swing.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.MaskFormatter;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JComboBox;

import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFormattedTextField;
import javax.swing.SwingWorker;

import choppeidanca.modelo.CondicaoPagamento;
import choppeidanca.modelo.Contas;
import choppeidanca.modelo.DataFatura;
import choppeidanca.modelo.FormaPagamento;
import choppeidanca.modelo.Movimento;
import choppeidanca.modelo.MovimentoProduto;
import choppeidanca.modelo.Pessoa;
import choppeidanca.modelo.Produto;
import choppeidanca.modelo.TabelaPrecoDetalhe;
import choppeidanca.modelosJtable.Modelo_Teste;
import choppeidanca.persistencia.ContasDAO;
import choppeidanca.persistencia.DataFaturaDAO;
import choppeidanca.persistencia.MovimentoDAO;
import choppeidanca.persistencia.MovimentoProdutoDAO;
import choppeidanca.persistencia.ProdutoDAO;
import choppeidanca.utils.MonetarioDocument;
import choppeidanca.utils.Singleton;

import java.awt.Window.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class FrmMovimento extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCliente;
	private JTable tblProdutos;
	private JTextField txtSubtotal;
	private JTextField txtForma;
	private JTextField txtCondicao;
	private JTable table;
	private JTextField txtDesconto;
	private JTextField txtAcrescimo;
	private JTextField txtTotal;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JFormattedTextField txtDataVenda;
	private JComboBox cbCondicao;
	private JButton btnBuscaCondicao;
	private JButton btnBuscaForma;
	private JButton btnPesquisaProduto;
	private JButton btnPesquisaCliente;
	private JComboBox cbOperacao;
	public List<Contas> data_parcelas = new ArrayList<>();
	List<TabelaPrecoDetalhe> produtos = new ArrayList<>();
	
	Singleton s = Singleton.getInstance();
	Pessoa p = new Pessoa();
	FormaPagamento fp = new FormaPagamento();
	CondicaoPagamento cp = new CondicaoPagamento();
	Modelo_Teste mt = new Modelo_Teste();
	Modelo_Grid mg = new Modelo_Grid();

	Object value2 = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmMovimento dialog = new FrmMovimento();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmMovimento() {
		setTitle("Choppeidan\u00E7a - Movimento");
		MaskFormatter mascaradata = null;
		try {
			mascaradata = new MaskFormatter("##/##/####");
			mascaradata.setPlaceholderCharacter('_');
		} catch (ParseException e1) {

			e1.printStackTrace();
		}

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 1074, 535);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				1.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JLabel lblOperacao = new JLabel("Operacao");
		GridBagConstraints gbc_lblOperacao = new GridBagConstraints();
		gbc_lblOperacao.insets = new Insets(0, 0, 5, 5);
		gbc_lblOperacao.anchor = GridBagConstraints.EAST;
		gbc_lblOperacao.gridx = 0;
		gbc_lblOperacao.gridy = 0;
		contentPanel.add(lblOperacao, gbc_lblOperacao);

		cbOperacao = new JComboBox();
		cbOperacao.setModel(new DefaultComboBoxModel(new String[] { "Venda",
				"Compra" }));
		GridBagConstraints gbc_cbOperacao = new GridBagConstraints();
		gbc_cbOperacao.gridwidth = 3;
		gbc_cbOperacao.insets = new Insets(0, 0, 5, 5);
		gbc_cbOperacao.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbOperacao.gridx = 1;
		gbc_cbOperacao.gridy = 0;
		contentPanel.add(cbOperacao, gbc_cbOperacao);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridwidth = 9;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblCliente = new JLabel("Cliente");
		GridBagConstraints gbc_lblCliente = new GridBagConstraints();
		gbc_lblCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblCliente.gridx = 0;
		gbc_lblCliente.gridy = 0;
		panel.add(lblCliente, gbc_lblCliente);

		btnPesquisaCliente = new JButton("");
		btnPesquisaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Runnable r = new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						BuscaPessoa bp = new BuscaPessoa();
						bp.setVisible(true);

						while (bp.isShowing()) {
						}
						String tipo;
						if (s.p.getTipo() == 0) {
							tipo = "Fï¿½sica";
						} else {
							tipo = "Juridica";
						}

						txtCliente.setText(s.p.getNome() + " / " + tipo);

						p = s.p;
					}
				};

				Thread t = new Thread(r);
				t.start();
			}
		});
		GridBagConstraints gbc_btnPesquisaCliente = new GridBagConstraints();
		gbc_btnPesquisaCliente.insets = new Insets(0, 0, 5, 5);
		gbc_btnPesquisaCliente.gridx = 1;
		gbc_btnPesquisaCliente.gridy = 0;
		panel.add(btnPesquisaCliente, gbc_btnPesquisaCliente);
		btnPesquisaCliente.setIcon(new ImageIcon(FrmMovimento.class
				.getResource("/choppeidanca/imagens/magnifier.png")));

		txtCliente = new JTextField();
		GridBagConstraints gbc_txtCliente = new GridBagConstraints();
		gbc_txtCliente.gridwidth = 2;
		gbc_txtCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCliente.insets = new Insets(0, 0, 5, 5);
		gbc_txtCliente.gridx = 2;
		gbc_txtCliente.gridy = 0;
		panel.add(txtCliente, gbc_txtCliente);
		txtCliente.setEditable(false);
		txtCliente.setColumns(10);

		JLabel lblProdutos = new JLabel("Produtos");
		GridBagConstraints gbc_lblProdutos = new GridBagConstraints();
		gbc_lblProdutos.anchor = GridBagConstraints.NORTH;
		gbc_lblProdutos.insets = new Insets(0, 0, 5, 5);
		gbc_lblProdutos.gridx = 0;
		gbc_lblProdutos.gridy = 1;
		panel.add(lblProdutos, gbc_lblProdutos);

		btnPesquisaProduto = new JButton("");
		btnPesquisaProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Runnable r = new Runnable() {
					@Override
					public void run() {
						BuscaTabelaDetalhe bp = new BuscaTabelaDetalhe();

						bp.setVisible(true);
						while (bp.isShowing()) {
						}

						if (s.produtos_Venda.isEmpty()) {
							
						} else {
							produtos = s.produtos_Venda;							
						}
						mt.setTabelas(produtos);
						mt.fireTableDataChanged();

						String valorStr = Double.toString(s.valor_bruto);

						valorStr = valorStr.replace(".", ",");

						txtSubtotal.setText(valorStr);
						txtTotal.setText(valorStr);
					}
				};
				Thread t = new Thread(r);
				t.start();

			}
		});
		btnPesquisaProduto.setIcon(new ImageIcon(FrmMovimento.class
				.getResource("/choppeidanca/imagens/magnifier.png")));
		GridBagConstraints gbc_btnPesquisaProduto = new GridBagConstraints();
		gbc_btnPesquisaProduto.anchor = GridBagConstraints.NORTH;
		gbc_btnPesquisaProduto.insets = new Insets(0, 0, 5, 5);
		gbc_btnPesquisaProduto.gridx = 1;
		gbc_btnPesquisaProduto.gridy = 1;
		panel.add(btnPesquisaProduto, gbc_btnPesquisaProduto);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);

		tblProdutos = new JTable();
		tblProdutos.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {

				int linha = tblProdutos.getSelectedRow();
				if (linha != -1) {
					System.out.println("Linha " + linha);

					Object value = tblProdutos.getValueAt(1, 4);

					if (value != value2) {
						System.err.println(value + " " + value2);

						atualizaValores(linha, value);
						value2 = value;

					} else {
						return;
					}
				}

			}
		});
		scrollPane.setViewportView(tblProdutos);
		tblProdutos.setModel(mt);

		JLabel lblNewLabel = new JLabel("Subtotal");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 2;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		txtSubtotal = new JTextField();
		txtSubtotal.setEditable(false);
		GridBagConstraints gbc_txtSubtotal = new GridBagConstraints();
		gbc_txtSubtotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSubtotal.gridx = 4;
		gbc_txtSubtotal.gridy = 2;
		panel.add(txtSubtotal, gbc_txtSubtotal);
		txtSubtotal.setColumns(10);

		JLabel lblFormaDePagamento = new JLabel("Forma de Pagamento");
		GridBagConstraints gbc_lblFormaDePagamento = new GridBagConstraints();
		gbc_lblFormaDePagamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFormaDePagamento.gridx = 0;
		gbc_lblFormaDePagamento.gridy = 3;
		contentPanel.add(lblFormaDePagamento, gbc_lblFormaDePagamento);

		btnBuscaForma = new JButton("");
		btnBuscaForma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwBuscaForma acao = new SwBuscaForma();
				acao.execute();
			}
		});
		btnBuscaForma.setIcon(new ImageIcon(FrmMovimento.class
				.getResource("/choppeidanca/imagens/magnifier.png")));
		GridBagConstraints gbc_btnBuscaForma = new GridBagConstraints();
		gbc_btnBuscaForma.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscaForma.gridx = 1;
		gbc_btnBuscaForma.gridy = 3;
		contentPanel.add(btnBuscaForma, gbc_btnBuscaForma);

		txtForma = new JTextField();
		txtForma.setEditable(false);
		GridBagConstraints gbc_txtForma = new GridBagConstraints();
		gbc_txtForma.gridwidth = 2;
		gbc_txtForma.insets = new Insets(0, 0, 5, 5);
		gbc_txtForma.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtForma.gridx = 2;
		gbc_txtForma.gridy = 3;
		contentPanel.add(txtForma, gbc_txtForma);
		txtForma.setColumns(10);

		JLabel lblCondiaoDePagamento = new JLabel("Condi\u00E7ao de Pagamento");
		GridBagConstraints gbc_lblCondiaoDePagamento = new GridBagConstraints();
		gbc_lblCondiaoDePagamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblCondiaoDePagamento.gridx = 4;
		gbc_lblCondiaoDePagamento.gridy = 3;
		contentPanel.add(lblCondiaoDePagamento, gbc_lblCondiaoDePagamento);

		btnBuscaCondicao = new JButton("");
		btnBuscaCondicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwBuscaCondicao acao = new SwBuscaCondicao();
				acao.execute();
			}
		});
		btnBuscaCondicao.setIcon(new ImageIcon(FrmMovimento.class
				.getResource("/choppeidanca/imagens/magnifier.png")));
		GridBagConstraints gbc_btnBuscaCondicao = new GridBagConstraints();
		gbc_btnBuscaCondicao.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscaCondicao.gridx = 5;
		gbc_btnBuscaCondicao.gridy = 3;
		contentPanel.add(btnBuscaCondicao, gbc_btnBuscaCondicao);

		txtCondicao = new JTextField();
		txtCondicao.setEditable(false);
		GridBagConstraints gbc_txtCondicao = new GridBagConstraints();
		gbc_txtCondicao.gridwidth = 2;
		gbc_txtCondicao.insets = new Insets(0, 0, 5, 5);
		gbc_txtCondicao.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCondicao.gridx = 6;
		gbc_txtCondicao.gridy = 3;
		contentPanel.add(txtCondicao, gbc_txtCondicao);
		txtCondicao.setColumns(10);

		cbCondicao = new JComboBox();
		cbCondicao.setModel(new DefaultComboBoxModel(new String[] {
				"\u00C1 Vista", "\u00C1 Prazo" }));
		GridBagConstraints gbc_cbCondicao = new GridBagConstraints();
		gbc_cbCondicao.insets = new Insets(0, 0, 5, 0);
		gbc_cbCondicao.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbCondicao.gridx = 8;
		gbc_cbCondicao.gridy = 3;
		contentPanel.add(cbCondicao, gbc_cbCondicao);

		JLabel lblDataDaOperao = new JLabel("Data da Opera\u00E7\u00E3o");
		GridBagConstraints gbc_lblDataDaOperao = new GridBagConstraints();
		gbc_lblDataDaOperao.anchor = GridBagConstraints.EAST;
		gbc_lblDataDaOperao.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataDaOperao.gridx = 0;
		gbc_lblDataDaOperao.gridy = 4;
		contentPanel.add(lblDataDaOperao, gbc_lblDataDaOperao);

		txtDataVenda = new JFormattedTextField(mascaradata);
		GridBagConstraints gbc_txtDataVenda = new GridBagConstraints();
		gbc_txtDataVenda.gridwidth = 2;
		gbc_txtDataVenda.insets = new Insets(0, 0, 5, 5);
		gbc_txtDataVenda.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDataVenda.gridx = 1;
		gbc_txtDataVenda.gridy = 4;
		contentPanel.add(txtDataVenda, gbc_txtDataVenda);

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		txtDataVenda.setText(format.format(new Date()));

		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.gridwidth = 7;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 2;
		gbc_scrollPane_1.gridy = 5;
		contentPanel.add(scrollPane_1, gbc_scrollPane_1);

		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.setModel(mg);

		JLabel lblDescontos = new JLabel("Descontos");
		GridBagConstraints gbc_lblDescontos = new GridBagConstraints();
		gbc_lblDescontos.anchor = GridBagConstraints.EAST;
		gbc_lblDescontos.insets = new Insets(0, 0, 0, 5);
		gbc_lblDescontos.gridx = 0;
		gbc_lblDescontos.gridy = 6;
		contentPanel.add(lblDescontos, gbc_lblDescontos);

		txtDesconto = new JTextField();
		GridBagConstraints gbc_txtDesconto = new GridBagConstraints();
		gbc_txtDesconto.gridwidth = 2;
		gbc_txtDesconto.insets = new Insets(0, 0, 0, 5);
		gbc_txtDesconto.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDesconto.gridx = 1;
		gbc_txtDesconto.gridy = 6;
		contentPanel.add(txtDesconto, gbc_txtDesconto);
		txtDesconto.setColumns(10);
		txtDesconto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		txtDesconto.setDocument(new MonetarioDocument());

		JLabel lblAcrescimos = new JLabel("Acrescimos");
		GridBagConstraints gbc_lblAcrescimos = new GridBagConstraints();
		gbc_lblAcrescimos.anchor = GridBagConstraints.EAST;
		gbc_lblAcrescimos.insets = new Insets(0, 0, 0, 5);
		gbc_lblAcrescimos.gridx = 3;
		gbc_lblAcrescimos.gridy = 6;
		contentPanel.add(lblAcrescimos, gbc_lblAcrescimos);

		txtAcrescimo = new JTextField();
		txtAcrescimo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {

				try {
					if (txtAcrescimo.getText() != null
							&& txtDesconto.getText() != null) {
						double t = 0;
						double d = 0;
						double a = 0;

						String t2 = txtTotal.getText();
						String a2 = txtAcrescimo.getText();
						String d2 = txtDesconto.getText();
						t2 = t2.replace(".", "").replace(",", ".");
						a2 = a2.replace(".", "").replace(",", ".");
						d2 = d2.replace(".", "").replace(",", ".");

						t = Double.parseDouble(t2);
						a = Double.parseDouble(a2);
						d = Double.parseDouble(d2);

						t = t - d + a;

						txtTotal.setText(format(t));

						Singleton s = Singleton.getInstance();

						s.mp.setAcrescimo(a);
						s.mp.setDesconto(d);

						txtAcrescimo.setText("");
						txtDesconto.setText("");
						carregaData();

					}
				} catch (NumberFormatException ex) {

				}
			}

			public String format(double x) {
				return String.format("%.2f", x);
			}
		});
		GridBagConstraints gbc_txtAcrescimo = new GridBagConstraints();
		gbc_txtAcrescimo.gridwidth = 2;
		gbc_txtAcrescimo.anchor = GridBagConstraints.NORTH;
		gbc_txtAcrescimo.insets = new Insets(0, 0, 0, 5);
		gbc_txtAcrescimo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAcrescimo.gridx = 4;
		gbc_txtAcrescimo.gridy = 6;
		contentPanel.add(txtAcrescimo, gbc_txtAcrescimo);
		txtAcrescimo.setColumns(10);
		txtAcrescimo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		txtAcrescimo.setDocument(new MonetarioDocument());

		JLabel lblTotal = new JLabel("Total");
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.anchor = GridBagConstraints.EAST;
		gbc_lblTotal.insets = new Insets(0, 0, 0, 5);
		gbc_lblTotal.gridx = 6;
		gbc_lblTotal.gridy = 6;
		contentPanel.add(lblTotal, gbc_lblTotal);

		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		GridBagConstraints gbc_txtTotal = new GridBagConstraints();
		gbc_txtTotal.anchor = GridBagConstraints.NORTH;
		gbc_txtTotal.insets = new Insets(0, 0, 0, 5);
		gbc_txtTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTotal.gridx = 7;
		gbc_txtTotal.gridy = 6;
		contentPanel.add(txtTotal, gbc_txtTotal);
		txtTotal.setColumns(10);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Movimento m = new Movimento();
					Contas c = new Contas();

					m.setPessoa(p);
					m.setFormaPagamento(fp);
					m.setCondicaoPagamento(cp);

					SimpleDateFormat formatIn = new SimpleDateFormat(
							"dd/MM/yyyy");
					String str = txtDataVenda.getText();
					Date date;
					try {

						date = formatIn.parse(str);

						java.sql.Date datesql = new java.sql.Date(date
								.getTime());

						m.setData_venda(datesql);
						c.setData_emissao(datesql);

						m.setOperacao(verificaOperacao());

						MovimentoDAO mdao = new MovimentoDAO();
						mdao.inserir(m);

					} catch (ParseException e3) {
						JOptionPane.showMessageDialog(null,
								"Data da operacao Invalida");
						e3.printStackTrace();
					}

					MovimentoProduto mp = new MovimentoProduto();
					mp.setMovimento(m);
					// mp.setTabela_detalhe();
					// mp.setQuantidade(quantidade);

					String valor_btr = txtSubtotal.getText();
					String desconto = txtDesconto.getText();
					String acrescimo = txtAcrescimo.getText();
					String valor_lqd = txtTotal.getText();
					valor_btr = valor_btr.replace(",", ".");
					desconto = desconto.replace(",", ".");
					acrescimo = acrescimo.replace(",", ".");
					valor_lqd = valor_lqd.replace(",", ".");

					mp.setValorund(Double.parseDouble(valor_btr));
					mp.setDesconto(Double.parseDouble(desconto));
					mp.setAcrescimo(Double.parseDouble(acrescimo));
					mp.setValor_liquido(Double.parseDouble(valor_lqd));

					MovimentoProdutoDAO mpdao = new MovimentoProdutoDAO();
					for (int i = 0; i < s.produtos_Venda.size(); i++) {
						mp.setTabela_detalhe(s.produtos_Venda.get(i));

						String quantidade = (String) s.produtos_Venda.get(i)
								.getQuantidade();
						mp.setQuantidade((String) s.produtos_Venda.get(i)
								.getQuantidade());
						mpdao.inserir(mp);

						mp.setQuantidade(Integer.parseInt((String) mt
								.getTabelas().get(i).getQuantidade()));
						String qtd = (String) s.produtos_Venda.get(i)
								.getQuantidade();

						if (verificaOperacao() == 1) {
							diminuiEstoque(mp.getTabela_detalhe().getProduto(),
									qtd);
						} else if (verificaOperacao() == 0) {
							almentaEstoque(mp.getTabela_detalhe().getProduto(),
									qtd);
						}

						mpdao.inserir(mp);

					}

					c.setMovimento(m);
					c.setForma(fp);
					c.setCondicao(cp);

					c.setTipo(verificaOperacao());

					// c.setStatus(verificaStatus());
					//
					if (cbCondicao.getSelectedItem().equals("Á Vista")) {
						c.setStatus(1);
					} else if (cbCondicao.getSelectedItem().equals("Á Prazo")) {
						c.setStatus(0);
					}

					c.setValor_parcela(data_parcelas.get(0).getValor_parcela());

					c.setValor_total(Double.parseDouble(valor_lqd));

					for (int i = 0; i < data_parcelas.size(); i++) {

						SimpleDateFormat format = new SimpleDateFormat(
								"dd/MM/yyyy");
						String dataStr = mg.retornaData(data_parcelas.get(i)
								.getData_fatura());
						Date date2;
						try {

							date2 = format.parse(dataStr);
							java.sql.Date datesql = new java.sql.Date(date2
									.getTime());

							c.setData_fatura(datesql);
						} catch (Exception e) {

						}

					}

					ContasDAO cdao = new ContasDAO();
					cdao.inserir(c);


					JOptionPane.showMessageDialog(null,
							"Movimento efetuado com sucesso");

					limparCampos();
				
					for (int i = 0; i < s.produtos_Venda.size(); i++) {
						s.produtos_Venda.remove(i);
					}
					
					for (int j = 0; j < data_parcelas.size(); j++) {
						data_parcelas.remove(j);
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		buttonPane.add(btnSalvar);

		btnCancelar = new JButton("Cancelar Venda");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();

				for (int i = 0; i < s.produtos_Venda.size(); i++) {
					s.produtos_Venda.remove(i);
				}
				
				for (int j = 0; j < data_parcelas.size(); j++) {
					data_parcelas.remove(j);
				}
			}
		});
		buttonPane.add(btnCancelar);

	}

	private void diminuiEstoque(Produto p, String quantidade) {
		int qtd = Integer.parseInt(quantidade);
		int qtdInicial = p.getQtdEstoque();
		p.setQtdEstoque(qtdInicial - qtd);
		ProdutoDAO dao = new ProdutoDAO();
		dao.alterar(p);
	}

	private void almentaEstoque(Produto p, String quantidade) {
		int qtd = Integer.parseInt(quantidade);
		int qtdInicial = p.getQtdEstoque();
		p.setQtdEstoque(qtdInicial + qtd);
		ProdutoDAO dao = new ProdutoDAO();
		dao.alterar(p);
	}

	protected void limparCampos() {

		txtCliente.setText("");
		txtCondicao.setText("");
		txtForma.setText("");
		txtTotal.setText("");
		txtSubtotal.setText("");
		List<TabelaPrecoDetalhe> lista = new ArrayList<>();
		mt.setTabelas(lista);
		mt.fireTableDataChanged();
	}

	protected int verificaStatus() {

		int qtdvezes = cp.getQtdVezes();

		if (cbCondicao.getSelectedItem() == "A Vista") {
			return 1;
		} else {
			return 0;
		}
	}

	protected int verificaOperacao() {
		if (cbOperacao.getSelectedItem() == "Venda") {
			return 1;
		} else {
			return 0;
		}
	}

	private double calculaSubtotal(int quantidade, int selectedRow,
			double subtotal) {

		double valor = s.produtos_Venda.get(selectedRow).getValor();

		valor = valor * (quantidade - 1);

		subtotal = subtotal + valor;

		return subtotal;
	}

	class SwBuscaCondicao extends SwingWorker<Void, Void> {
		BuscaCondicao bc = new BuscaCondicao();

		@Override
		protected Void doInBackground() throws Exception {
			bc.setVisible(true);
			while (bc.isShowing()) {

			}
			return null;
		}

		@Override
		protected void done() {
			txtCondicao.setText(s.cp.getNome() + " / " + s.cp.getQtdVezes());
			data_parcelas.clear();
			mg.fireTableDataChanged();
			cp = s.cp;
			carregaData();

		}
	}

	class SwBuscaForma extends SwingWorker<Void, Void> {
		BuscaForma bf = new BuscaForma();

		@Override
		protected Void doInBackground() throws Exception {
			bf.setVisible(true);
			while (bf.isShowing()) {

			}
			return null;
		}

		@Override
		protected void done() {
			txtForma.setText(s.fp.getTipo());
			fp = s.fp;
		}
	}

	public void atualizaValores(int rowIndex, Object value) {

		double valor = Singleton.getInstance().produtos_Venda.get(rowIndex)
				.getValor();
		double vlr_btr = Singleton.getInstance().valor_bruto;
		int quantidade = (int) value;
		valor = valor * (quantidade - 1);
		vlr_btr = vlr_btr + valor;

		String str = txtSubtotal.getText();

		Singleton.getInstance().valor_bruto = vlr_btr;

		txtTotal.setText(Double.toString(vlr_btr));
		System.err.println(vlr_btr);

	}

	private void carregaData() {

		data_parcelas = new ArrayList<>();
		for (int i = 0; i < s.cp.getQtdVezes(); i++) {
			Contas con = new Contas();
			GregorianCalendar c = new GregorianCalendar();
			c.add(Calendar.MONTH, +i);
			con.setData_fatura(c.getTime());
			con.setValor_parcela(calculaParcela(s.cp.getQtdVezes()));
			data_parcelas.add(con);

		}
		mg.fireTableDataChanged();

	}

	private double calculaParcela(int vezes) {

		double t = 0;

		String t2 = txtTotal.getText();
		t2 = t2.replace(".", "").replace(",", ".");

		t = Double.parseDouble(t2);

		return t / vezes;
	}

	class Modelo_Grid extends AbstractTableModel {

		@Override
		public int getColumnCount() {
			return 2;
		}

		@Override
		public int getRowCount() {
			return data_parcelas.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return retornaData(data_parcelas.get(rowIndex).getData_fatura());
			case 1:
				return data_parcelas.get(rowIndex).getValor_parcela();
			default:
				return null;
			}

		}

		@Override
		public String getColumnName(int column) {

			switch (column) {
			case 0:
				return "Data de Vencimento";
			case 1:
				return "Valor da Parcela";
			default:
				return "ERRO";
			}

		}

		private String retornaData(Date data) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			String dataStr = format.format(data);
			return dataStr;
		}
	}

}
