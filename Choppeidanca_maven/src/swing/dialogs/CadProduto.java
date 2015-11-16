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

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import choppeidanca.modelo.Categoria;
import choppeidanca.modelo.Produto;
import choppeidanca.modelosJtable.Modelo_Produto;
import choppeidanca.persistencia.ProdutoDAO;
import choppeidanca.utils.Singleton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadProduto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtCategoria;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnSalvar;
	private JButton btnEditar;
	private JButton btnExcluir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadProduto dialog = new CadProduto();
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

	Categoria c = new Categoria();
	Produto p = new Produto();
	Singleton s = Singleton.getInstance();

	private ImageIcon imgAlert = new ImageIcon(getClass().getResource(
			"/choppeidanca/imagens/error.png"));
	private JTextField txtQuantidade;
	private JRadioButton rbInativo;
	private JRadioButton rbAtivo;

	public CadProduto() {

		setTitle("Choppeidan\u00E7a - Cadastro de Produto");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 836, 320);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 63, 0, 0, 64, 60, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0,
				0.0, Double.MIN_VALUE };
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
		gbc_txtNome.gridwidth = 4;
		gbc_txtNome.insets = new Insets(0, 0, 5, 5);
		gbc_txtNome.anchor = GridBagConstraints.NORTH;
		gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNome.gridx = 1;
		gbc_txtNome.gridy = 0;
		contentPanel.add(txtNome, gbc_txtNome);
		txtNome.setColumns(10);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Produto p = new Produto();

					p.setDescricao(txtNome.getText());
					p.setCategoria(c);

					if (rbAtivo.isSelected()) {
						p.setStatus(true);
						rbAtivo.setSelected(false);
					}
					if (rbInativo.isSelected()) {
						p.setStatus(false);
						rbInativo.setSelected(false);
					}

					p.setQtdEstoque(Integer.parseInt(txtQuantidade.getText()));

					ProdutoDAO dao = new ProdutoDAO();
					dao.inserir(p);
					mp.fireTableDataChanged();

					limparCampos();

					JOptionPane.showMessageDialog(null,
							"Cadastrado com Sucesso");

				} catch (Exception ex) {
					// TODO: handle exception
				}
			}
		});

		JLabel lblQuantidade = new JLabel("Quantidade");
		GridBagConstraints gbc_lblQuantidade = new GridBagConstraints();
		gbc_lblQuantidade.anchor = GridBagConstraints.EAST;
		gbc_lblQuantidade.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantidade.gridx = 5;
		gbc_lblQuantidade.gridy = 0;
		contentPanel.add(lblQuantidade, gbc_lblQuantidade);

		txtQuantidade = new JTextField();
		GridBagConstraints gbc_txtQuantidade = new GridBagConstraints();
		gbc_txtQuantidade.insets = new Insets(0, 0, 5, 0);
		gbc_txtQuantidade.gridx = 6;
		gbc_txtQuantidade.gridy = 0;
		contentPanel.add(txtQuantidade, gbc_txtQuantidade);
		txtQuantidade.setColumns(10);

		JLabel lblCategoria = new JLabel("Categoria");
		GridBagConstraints gbc_lblCategoria = new GridBagConstraints();
		gbc_lblCategoria.anchor = GridBagConstraints.EAST;
		gbc_lblCategoria.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategoria.gridx = 0;
		gbc_lblCategoria.gridy = 1;
		contentPanel.add(lblCategoria, gbc_lblCategoria);

		JButton btnPesquisaCat = new JButton("");
		btnPesquisaCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Runnable r = new Runnable() {

					@Override
					public void run() {
						s.statusBotao = true;
						BuscaCategoria bc = new BuscaCategoria();
						bc.setVisible(true);

						while (bc.isShowing()) {

						}

						c = s.cat;
						String categoria = c.getTipo();
						if (!categoria.equals("")) {
							txtCategoria.setText(c.getTipo());
						}

					}
				};

				Thread t = new Thread(r);
				t.start();

			}
		});
		btnPesquisaCat.setIcon(new ImageIcon(CadProduto.class
				.getResource("/choppeidanca/imagens/magnifier.png")));
		GridBagConstraints gbc_btnPesquisaCat = new GridBagConstraints();
		gbc_btnPesquisaCat.fill = GridBagConstraints.BOTH;
		gbc_btnPesquisaCat.insets = new Insets(0, 0, 5, 5);
		gbc_btnPesquisaCat.gridx = 1;
		gbc_btnPesquisaCat.gridy = 1;
		contentPanel.add(btnPesquisaCat, gbc_btnPesquisaCat);

		txtCategoria = new JTextField();
		txtCategoria.setEditable(false);
		GridBagConstraints gbc_txtCategoria = new GridBagConstraints();
		gbc_txtCategoria.gridwidth = 2;
		gbc_txtCategoria.insets = new Insets(0, 0, 5, 5);
		gbc_txtCategoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCategoria.gridx = 2;
		gbc_txtCategoria.gridy = 1;
		contentPanel.add(txtCategoria, gbc_txtCategoria);
		txtCategoria.setColumns(10);

		JLabel lblStatus = new JLabel("Status");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.anchor = GridBagConstraints.EAST;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 3;
		gbc_lblStatus.gridy = 1;
		contentPanel.add(lblStatus, gbc_lblStatus);

		rbAtivo = new JRadioButton("Ativo");
		buttonGroup.add(rbAtivo);
		GridBagConstraints gbc_rbAtivo = new GridBagConstraints();
		gbc_rbAtivo.insets = new Insets(0, 0, 5, 5);
		gbc_rbAtivo.gridx = 4;
		gbc_rbAtivo.gridy = 1;
		contentPanel.add(rbAtivo, gbc_rbAtivo);

		rbInativo = new JRadioButton("Inativo");
		buttonGroup.add(rbInativo);
		GridBagConstraints gbc_rbInativo = new GridBagConstraints();
		gbc_rbInativo.insets = new Insets(0, 0, 5, 5);
		gbc_rbInativo.gridx = 5;
		gbc_rbInativo.gridy = 1;
		contentPanel.add(rbInativo, gbc_rbInativo);
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSalvar.insets = new Insets(0, 0, 5, 0);
		gbc_btnSalvar.gridx = 6;
		gbc_btnSalvar.gridy = 1;
		contentPanel.add(btnSalvar, gbc_btnSalvar);

		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();

				btnSalvar.setEnabled(true);
				btnEditar.setEnabled(false);
				btnExcluir.setEnabled(false);
			}
		});
		GridBagConstraints gbc_btnNovo = new GridBagConstraints();
		gbc_btnNovo.insets = new Insets(0, 0, 0, 5);
		gbc_btnNovo.gridx = 0;
		gbc_btnNovo.gridy = 5;
		contentPanel.add(btnNovo, gbc_btnNovo);

		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Runnable r = new Runnable() {

					@Override
					public void run() {

						s.statusBotao = false;

						BuscaProduto bp = new BuscaProduto();
						bp.setVisible(true);

						while (bp.isShowing()) {

						}

						txtNome.setText(s.produto.getDescricao());
						txtCategoria
								.setText(s.produto.getCategoria().getTipo());
						if (s.produto.getStatus() == true) {
							rbAtivo.setSelected(true);
						}
						if (s.produto.getStatus() == false) {
							rbInativo.setSelected(true);
						}
						txtQuantidade.setText(Integer.toString(s.produto
								.getQtdEstoque()));

						p = s.produto;
						c = s.produto.getCategoria();

						String nome = txtNome.getText();
						String categoria = txtCategoria.getText();
						int estoque = Integer.parseInt(txtQuantidade.getText());
						if (!nome.equals("") || !categoria.equals("")
								|| estoque != 0) {
							btnSalvar.setEnabled(false);
							btnEditar.setEnabled(true);
							btnExcluir.setEnabled(true);
						}

					}
				};

				Thread t = new Thread(r);
				t.start();

			}
		});
		GridBagConstraints gbc_btnListar = new GridBagConstraints();
		gbc_btnListar.insets = new Insets(0, 0, 0, 5);
		gbc_btnListar.gridx = 3;
		gbc_btnListar.gridy = 5;
		contentPanel.add(btnListar, gbc_btnListar);

		btnExcluir = new JButton("Excluir");
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.insets = new Insets(0, 0, 0, 5);
		gbc_btnExcluir.gridx = 4;
		gbc_btnExcluir.gridy = 5;
		contentPanel.add(btnExcluir, gbc_btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int dialogButton = JOptionPane.YES_NO_OPTION;
				int result = JOptionPane.showConfirmDialog(null,
						"Deseja excluir o produto " + p.getDescricao() + "?",
						"Deseja realizar essa operação?", dialogButton);

				if (result == JOptionPane.YES_OPTION) {

					ProdutoDAO dao = new ProdutoDAO();
					if (dao.deletar(p.getCodigo()) == "NO") {
						JOptionPane.showMessageDialog(null,
								"Não foi possivel excluir esse produto",
								"Problemas ao Excluir", 1, imgAlert);
					} else {
						JOptionPane.showMessageDialog(null,
								"Exclusão feita com sucesso");
						limparCampos();

						btnSalvar.setEnabled(true);
						btnEditar.setEnabled(false);
						btnExcluir.setEnabled(false);
					}

				}

			}
		});
		btnExcluir.setEnabled(false);

		btnEditar = new JButton("Editar");
		GridBagConstraints gbc_btnEditar = new GridBagConstraints();
		gbc_btnEditar.insets = new Insets(0, 0, 0, 5);
		gbc_btnEditar.gridx = 5;
		gbc_btnEditar.gridy = 5;
		contentPanel.add(btnEditar, gbc_btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					p.setDescricao(txtNome.getText());
					p.setCategoria(c);

					if (rbAtivo.isSelected()) {
						p.setStatus(true);
						rbAtivo.setSelected(false);
					}
					if (rbInativo.isSelected()) {
						p.setStatus(false);
						rbInativo.setSelected(false);
					}
					p.setQtdEstoque(Integer.parseInt(txtQuantidade.getText()));

					ProdutoDAO dao = new ProdutoDAO();
					dao.alterar(p);

					mp.fireTableDataChanged();

					JOptionPane.showMessageDialog(null,
							"Alteração Feita com Sucesso");

					limparCampos();

					btnSalvar.setEnabled(true);
					btnEditar.setEnabled(false);
					btnExcluir.setEnabled(false);

				} catch (Exception ex) {
					// TODO: handle exception
				}

			}
		});
		btnEditar.setEnabled(false);

		JButton cancelButton = new JButton("Cancelar");
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.gridx = 6;
		gbc_cancelButton.gridy = 5;
		contentPanel.add(cancelButton, gbc_cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadProduto.this.dispose();
			}
		});

	}

	private void limparCampos() {
		txtNome.setText("");
		txtCategoria.setText("");
		txtQuantidade.setText("");
	}

}
