package choppeidanca.swing.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import choppeidanca.modelo.Produto;
import choppeidanca.modelo.TabelaPreco;
import choppeidanca.modelo.TabelaPrecoDetalhe;
import choppeidanca.modelosJtable.Modelo_TabelaPrecoDetalhe;
import choppeidanca.persistencia.TabelaPDetalheDAO;
import choppeidanca.utils.EditarCelulaTabela;
import choppeidanca.utils.Singleton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscaTabelaDetalhe extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private List<TabelaPrecoDetalhe> selecionados = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscaTabelaDetalhe dialog = new BuscaTabelaDetalhe();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	Modelo_TabelaPrecoDetalhe mtp = new Modelo_TabelaPrecoDetalhe();

	Singleton s = Singleton.getInstance();

	public BuscaTabelaDetalhe() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		carregaDados();

		selecionados = s.produtos_Venda;
		s.produtos_Venda = new ArrayList<>();

		setTitle("Choppeidan\u00E7a - Movimento - Busca Produto");
		setModal(true);
		setBounds(100, 100, 757, 442);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPanel.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		scrollPane.setViewportView(table);
		table.setModel(mtp);
		TableColumn col = table.getColumnModel().getColumn(4);
		col.setCellEditor(new EditarCelulaTabela());
		mtp.setValueAt(Singleton.getInstance().quantidade,
				table.getSelectedRow(), 4);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Runnable r2 = new Runnable() {

					@Override
					public void run() {
						TabelaPrecoDetalhe tp = new TabelaPrecoDetalhe();

						s.produtos_Venda = getInstance();

						for (int i = 0; i < s.produtos_Venda.size(); i++) {
							String qtdCarrinho = (String) s.produtos_Venda.get(i).getQuantidade();
							int qtd = Integer.parseInt(qtdCarrinho);
							if (s.produtos_Venda.get(i).getProduto().getQtdEstoque() < qtd) {
								JOptionPane.showMessageDialog(null,	"Desculpe, mas a quantidade a ser comprada do produto " + s.produtos_Venda.get(i).getProduto().getDescricao() + " está indisponivel no estoque");
								s.produtos_Venda = new ArrayList<>();
								s.valor_bruto = 0;
								return;
							} else {
								if (i == s.produtos_Venda.size() - 1) {
									BuscaTabelaDetalhe.this.dispose();
								}
							}
						}

					}
				};

				Thread th = new Thread(r2);
				th.start();

//				BuscaTabelaDetalhe.this.dispose();

			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscaTabelaDetalhe.this.dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

	}

	private List<TabelaPrecoDetalhe> getInstance() {
		selecionados = mtp.getSelectedObject(table.getSelectedRows());
		return selecionados;
	}

	private void carregaDados() {
		TabelaPDetalheDAO dao = new TabelaPDetalheDAO();
		mtp.setTabelas(dao.listar());
	}

}
