package choppeidanca.swing.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import choppeidanca.modelo.Pais;
import choppeidanca.modelo.Uf;
import choppeidanca.modelosJtable.Modelo_Uf;
import choppeidanca.persistencia.PaisDAO;
import choppeidanca.persistencia.UfDAO;
import choppeidanca.utils.Singleton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadEstado extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtSigla;
	private JTextField txtPais;
	private ImageIcon imgAlert = new ImageIcon(getClass().getResource(
			"/choppeidanca/imagens/error.png"));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadEstado dialog = new CadEstado();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	Modelo_Uf mu = new Modelo_Uf();
	Uf uf = new Uf();
	Pais p = new Pais();
	Singleton s = Singleton.getInstance();
	private JButton btnSalvar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnNovo;
	private JButton btnListar;

	public CadEstado() {

		carregaDados();

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Choppeidan\u00E7a - Cadastro de UF");
		setModal(true);
		setBounds(100, 100, 624, 253);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
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
		gbc_txtNome.gridwidth = 3;
		gbc_txtNome.insets = new Insets(0, 0, 5, 5);
		gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNome.gridx = 1;
		gbc_txtNome.gridy = 0;
		contentPanel.add(txtNome, gbc_txtNome);
		txtNome.setColumns(10);

		JLabel lblSigla = new JLabel("Sigla");
		GridBagConstraints gbc_lblSigla = new GridBagConstraints();
		gbc_lblSigla.insets = new Insets(0, 0, 5, 5);
		gbc_lblSigla.anchor = GridBagConstraints.EAST;
		gbc_lblSigla.gridx = 4;
		gbc_lblSigla.gridy = 0;
		contentPanel.add(lblSigla, gbc_lblSigla);

		txtSigla = new JTextField();
		GridBagConstraints gbc_txtSigla = new GridBagConstraints();
		gbc_txtSigla.insets = new Insets(0, 0, 5, 0);
		gbc_txtSigla.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSigla.gridx = 5;
		gbc_txtSigla.gridy = 0;
		contentPanel.add(txtSigla, gbc_txtSigla);
		txtSigla.setColumns(10);

		JLabel lblPais = new JLabel("Pais");
		GridBagConstraints gbc_lblPais = new GridBagConstraints();
		gbc_lblPais.insets = new Insets(0, 0, 5, 5);
		gbc_lblPais.anchor = GridBagConstraints.EAST;
		gbc_lblPais.gridx = 0;
		gbc_lblPais.gridy = 1;
		contentPanel.add(lblPais, gbc_lblPais);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Runnable r = new Runnable() {

					@Override
					public void run() {
						s.statusBotao = true;
						BuscaPais bp = new BuscaPais();
						bp.setVisible(true);
						bp.isVisible = true;
						while (bp.isShowing()) {

						}

						txtPais.setText(s.pais.getNome());
						p = s.pais;

					}
				};

				Thread t = new Thread(r);
				t.start();

			}
		});
		button.setIcon(new ImageIcon(CadEstado.class
				.getResource("/choppeidanca/imagens/magnifier.png")));
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 1;
		contentPanel.add(button, gbc_button);

		txtPais = new JTextField();
		txtPais.setEditable(false);
		GridBagConstraints gbc_txtPais = new GridBagConstraints();
		gbc_txtPais.gridwidth = 3;
		gbc_txtPais.insets = new Insets(0, 0, 5, 5);
		gbc_txtPais.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPais.gridx = 2;
		gbc_txtPais.gridy = 1;
		contentPanel.add(txtPais, gbc_txtPais);
		txtPais.setColumns(10);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					Uf uf = new Uf();
					uf.setNome(txtNome.getText());
					uf.setSigla(txtSigla.getText());
					uf.setPais(p);

					UfDAO dao = new UfDAO();
					dao.inserir(uf);

					mu.fireTableDataChanged();

					limparCampos();
					JOptionPane.showMessageDialog(null,
							"Estado cadastrada com sucesso");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"Erro ao cadastrar Estado");
				}

			}
		});
		btnSalvar.setIcon(new ImageIcon(CadEstado.class
				.getResource("/choppeidanca/imagens/Salvar.png")));
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.insets = new Insets(0, 0, 5, 0);
		gbc_btnSalvar.gridx = 5;
		gbc_btnSalvar.gridy = 1;
		contentPanel.add(btnSalvar, gbc_btnSalvar);

		btnNovo = new JButton("Novo");
		GridBagConstraints gbc_btnNovo = new GridBagConstraints();
		gbc_btnNovo.insets = new Insets(0, 0, 0, 5);
		gbc_btnNovo.gridx = 0;
		gbc_btnNovo.gridy = 3;
		contentPanel.add(btnNovo, gbc_btnNovo);

		btnListar = new JButton("Listar");
		GridBagConstraints gbc_btnListar = new GridBagConstraints();
		gbc_btnListar.anchor = GridBagConstraints.EAST;
		gbc_btnListar.insets = new Insets(0, 0, 0, 5);
		gbc_btnListar.gridx = 2;
		gbc_btnListar.gridy = 3;
		contentPanel.add(btnListar, gbc_btnListar);

		btnEditar = new JButton("Editar");
		GridBagConstraints gbc_btnEditar = new GridBagConstraints();
		gbc_btnEditar.insets = new Insets(0, 0, 0, 5);
		gbc_btnEditar.gridx = 3;
		gbc_btnEditar.gridy = 3;
		contentPanel.add(btnEditar, gbc_btnEditar);
		btnEditar.setEnabled(false);

		btnExcluir = new JButton("Excluir");
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.insets = new Insets(0, 0, 0, 5);
		gbc_btnExcluir.gridx = 4;
		gbc_btnExcluir.gridy = 3;
		contentPanel.add(btnExcluir, gbc_btnExcluir);
		btnExcluir.setEnabled(false);

		JButton cancelButton = new JButton("Cancelar");
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_cancelButton.gridx = 5;
		gbc_cancelButton.gridy = 3;
		contentPanel.add(cancelButton, gbc_cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadEstado.this.dispose();

			}
		});
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					int dialogButton = JOptionPane.YES_NO_OPTION;
					int result = JOptionPane.showConfirmDialog(null,
							"Deseja excluir o estado " + uf.getNome() + "?",
							"Deseja realizar essa operação?", dialogButton);

					if (result == JOptionPane.YES_OPTION) {

						UfDAO dao = new UfDAO();
						if (dao.deletar(uf.getId()) == "NO") {
							JOptionPane
									.showMessageDialog(
											null,
											"Este UF tem cidades vinculados a ele, não é possivel excluir esse estado",
											"Problemas ao Excluir", 1, imgAlert);
							return;
						} else {
							

							JOptionPane.showMessageDialog(null,
									"Exclusão feita com sucesso");
							
							limparCampos();
							
							btnSalvar.setEnabled(true);
							btnEditar.setEnabled(false);
							btnExcluir.setEnabled(false);

						}

					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"Problemas ao excluir estado");
				}

			}
		});
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					uf.setNome(txtNome.getText());
					uf.setSigla(txtSigla.getText());
					uf.setPais(p);

					UfDAO dao = new UfDAO();
					dao.alterar(uf);

					mu.fireTableDataChanged();

					JOptionPane.showMessageDialog(null,
							"Alteração Feita com Sucesso");

					limparCampos();

					btnSalvar.setEnabled(true);
					btnEditar.setEnabled(false);
					btnExcluir.setEnabled(false);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"Erro ao alterar estado");
				}
			}
		});
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Runnable r = new Runnable() {

					@Override
					public void run() {

						s.statusBotao = false;

						BuscaEstado be = new BuscaEstado();
						be.setVisible(true);

						while (be.isShowing()) {

						}

						txtNome.setText(s.uf.getNome());
						txtPais.setText(s.uf.getPais().getNome());						
						txtSigla.setText(s.uf.getSigla());
						uf = s.uf;
						p = s.uf.getPais();
						
						String nome = txtNome.getText();
						String pais = txtPais.getText();
						String sigla = txtSigla.getText();
						if (!nome.equals("") || !pais.equals("") || !sigla.equals("")) {
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
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();

				btnSalvar.setEnabled(true);
				btnEditar.setEnabled(false);
				btnExcluir.setEnabled(false);
			}
		});

	}

	private void carregaDados() {
		UfDAO dao = new UfDAO();
		mu.setUfs(dao.listar());
		mu.fireTableDataChanged();
	}

	private void limparCampos() {
		txtNome.setText("");
		txtSigla.setText("");
		txtPais.setText("");
	}

}
