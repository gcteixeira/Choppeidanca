package choppeidanca.swing.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import choppeidanca.modelo.Categoria;
import choppeidanca.modelo.Produto;
import choppeidanca.modelosJtable.Modelo_Produto;
import choppeidanca.persistencia.ProdutoDAO;
import choppeidanca.utils.Singleton;

import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;

import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscaProduto extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscaProduto dialog = new BuscaProduto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	Modelo_Produto mp = new Modelo_Produto();
	Produto p = new Produto();
	Singleton s = Singleton.getInstance();
	private JTable table;
	private ImageIcon imgAlert = new ImageIcon(getClass().getResource(
			"/choppeidanca/imagens/error.png"));

	Categoria c = new Categoria();

	public BuscaProduto() {
		setTitle("Choppeidan\u00E7a - Busca de Produto");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);

		carregaDados();

		s.produto = p;
		s.produto.setCategoria(c);

		setBounds(100, 100, 450, 300);
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
		scrollPane.setViewportView(table);
		table.setModel(mp);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnNovo = new JButton("Novo Produto");
		btnNovo.setVisible(s.statusBotao);
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Runnable r = new Runnable() {

					@Override
					public void run() {
						CadProduto cp = new CadProduto();
						cp.setVisible(true);

						while (cp.isShowing()) {

						}

						carregaDados();
					}
				};

				Thread t = new Thread(r);
				t.start();
			}
		});
		buttonPane.add(btnNovo);

		JButton okButton = new JButton("Selecionar");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();

				if (linha == -1) {

					JOptionPane
							.showMessageDialog(
									null,
									"Por Favor, selecione uma linha da lista de Produtos",
									"Ação Ilegal", 1, imgAlert);

				} else {
					p = getInstance();

					s.produto = p;
					BuscaProduto.this.dispose();

				}
			}
		});
		buttonPane.add(okButton);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Produto p = getInstance();
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int result = JOptionPane.showConfirmDialog(null,
							"Deseja excluir o produto " + p.getDescricao()
									+ "?", "Deseja realizar essa operação?",
							dialogButton);

					if (result == JOptionPane.YES_OPTION) {

						ProdutoDAO dao = new ProdutoDAO();
						if (dao.deletar(p.getCodigo()) == "NO") {
							JOptionPane.showMessageDialog(null,
									"Não foi possivel excluir esse produto",
									"Problemas ao Excluir", 1, imgAlert);
						} else {
							carregaDados();
							JOptionPane.showMessageDialog(null,
									"Exclusão feita com sucesso");
						}

					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"Problemas ao excluir Produto");
				}

			}
		});
		buttonPane.add(btnExcluir);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscaProduto.this.dispose();
			}
		});
		buttonPane.add(cancelButton);

	}

	private void carregaDados() {
		ProdutoDAO dao = new ProdutoDAO();
		mp.setProdutos(dao.listar());
		mp.fireTableDataChanged();
	}

	private Produto getInstance() {
		Produto p = mp.getSelectedObject(table.getSelectedRow());
		return p;
	}

}
