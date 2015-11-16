package choppeidanca.swing.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import choppeidanca.modelo.Pais;
import choppeidanca.modelosJtable.Modelo_Pais;
import choppeidanca.persistencia.PaisDAO;
import choppeidanca.utils.Singleton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadPais extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadPais dialog = new CadPais();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	Modelo_Pais mp = new Modelo_Pais();
	private JButton btnSalvar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnListar;
	private ImageIcon imgAlert = new ImageIcon(getClass().getResource(
			"/choppeidanca/imagens/error.png"));
	Singleton s = Singleton.getInstance();

	Pais p = new Pais();
	private JButton btnNovo;

	public CadPais() {

		carregaDados();

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Choppeidan\u00E7a - Cadastro de Pais");
		setModal(true);
		setBounds(100, 100, 572, 241);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 1.0, 0.0,
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

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					Pais p = new Pais();
					p.setNome(txtNome.getText());

					if (verificaCampos()) {
						PaisDAO dao = new PaisDAO();
						dao.inserir(p);						
						mp.fireTableDataChanged();
						
						
						limparCampos();
						
						JOptionPane.showMessageDialog(null,
								"Pais cadastrado com sucesso");
					} else {
						JOptionPane.showMessageDialog(null, "Campos Obrigatorios nao podem ser nulos");
					}


				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Erro ao cadastrar Pais");
				}

			}
		});
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.insets = new Insets(0, 0, 5, 0);
		gbc_btnSalvar.gridx = 4;
		gbc_btnSalvar.gridy = 0;
		contentPanel.add(btnSalvar, gbc_btnSalvar);

		btnNovo = new JButton("Novo");
		GridBagConstraints gbc_btnNovo = new GridBagConstraints();
		gbc_btnNovo.insets = new Insets(0, 0, 0, 5);
		gbc_btnNovo.gridx = 0;
		gbc_btnNovo.gridy = 2;
		contentPanel.add(btnNovo, gbc_btnNovo);
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				limparCampos();

				btnSalvar.setEnabled(true);
				btnEditar.setEnabled(false);
				btnExcluir.setEnabled(false);

			}
		});

		btnListar = new JButton("Listar");
		GridBagConstraints gbc_btnListar = new GridBagConstraints();
		gbc_btnListar.anchor = GridBagConstraints.EAST;
		gbc_btnListar.insets = new Insets(0, 0, 0, 5);
		gbc_btnListar.gridx = 1;
		gbc_btnListar.gridy = 2;
		contentPanel.add(btnListar, gbc_btnListar);
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Runnable r = new Runnable() {

					@Override
					public void run() {

						s.statusBotao = false;

						BuscaPais bp = new BuscaPais();
						bp.setVisible(true);
						bp.isVisible = false;

						while (bp.isShowing()) {

						}

						txtNome.setText(s.pais.getNome());

						p = s.pais;

						String nome = txtNome.getText();
						if (!nome.equals("")) {
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

		btnEditar = new JButton("Editar");
		GridBagConstraints gbc_btnEditar = new GridBagConstraints();
		gbc_btnEditar.insets = new Insets(0, 0, 0, 5);
		gbc_btnEditar.gridx = 2;
		gbc_btnEditar.gridy = 2;
		contentPanel.add(btnEditar, gbc_btnEditar);
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					p.setNome(txtNome.getText());
					if (verificaCampos()) {
						PaisDAO dao = new PaisDAO();
						dao.alterar(p);
						
						mp.fireTableDataChanged();
						
						JOptionPane.showMessageDialog(null,
								"Alteração Feita com Sucesso");
						
						limparCampos();
						
						btnSalvar.setEnabled(true);
						btnEditar.setEnabled(false);
						btnExcluir.setEnabled(false);
						
					} else {
						JOptionPane.showMessageDialog(null, "Campos Obrigatorios nao podem ser nulos");
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao alterar pais");
				}
			}
		});

		btnExcluir = new JButton("Excluir");
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.insets = new Insets(0, 0, 0, 5);
		gbc_btnExcluir.gridx = 3;
		gbc_btnExcluir.gridy = 2;
		contentPanel.add(btnExcluir, gbc_btnExcluir);
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					int dialogButton = JOptionPane.YES_NO_OPTION;
					int result = JOptionPane.showConfirmDialog(null,
							"Deseja excluir o pais " + p.getNome() + "?",
							"Deseja realizar essa operação?", dialogButton);

					if (result == JOptionPane.YES_OPTION) {

						PaisDAO dao = new PaisDAO();
						if (dao.deletar(p.getId()) == "NO") {
							JOptionPane
									.showMessageDialog(
											null,
											"Esse País tem cidades e estados vinculados a ele, não é possivel excluir esse país",
											"Problemas ao Excluir", 1, imgAlert);
							return;
						} else {

							mp.fireTableDataChanged();

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
							"Problemas ao excluir pais");
				}

			}
		});
		btnExcluir.setActionCommand("OK");

		JButton cancelButton = new JButton("Cancelar");
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.gridx = 4;
		gbc_cancelButton.gridy = 2;
		contentPanel.add(cancelButton, gbc_cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadPais.this.dispose();

			}
		});
		cancelButton.setActionCommand("Cancel");

	}

	protected boolean verificaCampos() {
		
		if (!txtNome.getText().equals("")) {
			return true;
		} 
		
		return false;
	}

	private void carregaDados() {
		PaisDAO dao = new PaisDAO();
		mp.setPaises(dao.listar());
		mp.fireTableDataChanged();
	}

	private void limparCampos() {
		txtNome.setText("");
	}

}
