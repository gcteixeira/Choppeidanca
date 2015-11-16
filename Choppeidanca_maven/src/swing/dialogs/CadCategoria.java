package choppeidanca.swing.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import choppeidanca.modelo.Categoria;
import choppeidanca.modelosJtable.Modelo_Categoria;
import choppeidanca.persistencia.CategoriaDAO;
import choppeidanca.utils.Singleton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadCategoria extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtCategoria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadCategoria dialog = new CadCategoria();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	Modelo_Categoria mc = new Modelo_Categoria();
	private JButton btnExcluir;
	private JButton btnEditar;
	private JRadioButton rbAtivo;
	private JRadioButton rbInativo;
	private JButton btnSalvar;
	private JButton btnNovo;
	private JButton btnListar;
	Singleton s = Singleton.getInstance();
	Categoria c = new Categoria();
	
	private ImageIcon imgAlert = new ImageIcon(getClass().getResource(
			"/choppeidanca/imagens/error.png"));
	
	public CadCategoria() {
		setTitle("Choppeidan\u00E7a - Cadastro de Categoria");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 694, 165);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JLabel lblNome = new JLabel("Categoria");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		contentPanel.add(lblNome, gbc_lblNome);

		txtCategoria = new JTextField();
		GridBagConstraints gbc_txtCategoria = new GridBagConstraints();
		gbc_txtCategoria.insets = new Insets(0, 0, 5, 5);
		gbc_txtCategoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCategoria.gridx = 1;
		gbc_txtCategoria.gridy = 0;
		contentPanel.add(txtCategoria, gbc_txtCategoria);
		txtCategoria.setColumns(10);

		JLabel lblStatus = new JLabel("Status");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.anchor = GridBagConstraints.EAST;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 2;
		gbc_lblStatus.gridy = 0;
		contentPanel.add(lblStatus, gbc_lblStatus);

		rbAtivo = new JRadioButton("Ativo");
		buttonGroup.add(rbAtivo);
		GridBagConstraints gbc_rbAtivo = new GridBagConstraints();
		gbc_rbAtivo.insets = new Insets(0, 0, 5, 5);
		gbc_rbAtivo.gridx = 3;
		gbc_rbAtivo.gridy = 0;
		contentPanel.add(rbAtivo, gbc_rbAtivo);

		rbInativo = new JRadioButton("Inativo");
		buttonGroup.add(rbInativo);
		GridBagConstraints gbc_rbInativo = new GridBagConstraints();
		gbc_rbInativo.insets = new Insets(0, 0, 5, 5);
		gbc_rbInativo.gridx = 4;
		gbc_rbInativo.gridy = 0;
		contentPanel.add(rbInativo, gbc_rbInativo);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					Categoria c = new Categoria();

					c.setTipo(txtCategoria.getText());

					/**
					 * Se ativo = 1, se inativo = 0
					 */
					if (rbAtivo.isSelected()) {
						c.setStatus(1);
						rbAtivo.setSelected(false);
					}
					if (rbInativo.isSelected()) {
						c.setStatus(0);
						rbInativo.setSelected(false);
					}

					CategoriaDAO dao = new CategoriaDAO();
					dao.inserir(c);

					mc.fireTableDataChanged();

					txtCategoria.setText("");

					JOptionPane.showMessageDialog(null,
							"Categoria cadastrada com sucesso");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Erro ao cadastrar categoria");
				}
			}
		});
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.insets = new Insets(0, 0, 5, 0);
		gbc_btnSalvar.gridx = 5;
		gbc_btnSalvar.gridy = 0;
		contentPanel.add(btnSalvar, gbc_btnSalvar);

		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCategoria.setText("");

				btnSalvar.setEnabled(true);
				btnEditar.setEnabled(false);
				btnExcluir.setEnabled(false);
			}
		});
		GridBagConstraints gbc_btnNovo = new GridBagConstraints();
		gbc_btnNovo.insets = new Insets(0, 0, 0, 5);
		gbc_btnNovo.gridx = 0;
		gbc_btnNovo.gridy = 2;
		contentPanel.add(btnNovo, gbc_btnNovo);

		btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Runnable r = new Runnable() {

					@Override
					public void run() {

						s.statusBotao = false;

						BuscaCategoria bc = new BuscaCategoria();
						bc.setVisible(true);

						while (bc.isShowing()) {

						}

						txtCategoria.setText(s.cat.getTipo());
						if (s.cat.getStatus() == 1) {
							rbAtivo.setSelected(true);
						}
						if (s.cat.getStatus() == 0) {
							rbInativo.setSelected(true);
						}					
						c = s.cat;
						
						String nome = txtCategoria.getText();
					
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
		GridBagConstraints gbc_btnListar = new GridBagConstraints();
		gbc_btnListar.insets = new Insets(0, 0, 0, 5);
		gbc_btnListar.gridx = 2;
		gbc_btnListar.gridy = 2;
		contentPanel.add(btnListar, gbc_btnListar);

		btnEditar = new JButton("Editar");
		GridBagConstraints gbc_btnEditar = new GridBagConstraints();
		gbc_btnEditar.insets = new Insets(0, 0, 0, 5);
		gbc_btnEditar.gridx = 3;
		gbc_btnEditar.gridy = 2;
		contentPanel.add(btnEditar, gbc_btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					

					c.setTipo(txtCategoria.getText());

					if (rbAtivo.isSelected()) {
						c.setStatus(1);
						rbAtivo.setSelected(false);
					}
					if (rbInativo.isSelected()) {
						c.setStatus(0);
						rbInativo.setSelected(false);
					}

					CategoriaDAO dao = new CategoriaDAO();
					dao.alterar(c);

					mc.fireTableDataChanged();

					JOptionPane.showMessageDialog(null,
							"Alteração Feita com Sucesso");

					txtCategoria.setText("");

					btnSalvar.setEnabled(true);
					btnEditar.setEnabled(false);
					btnExcluir.setEnabled(false);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"Erro ao alterar categoria");
				}
			}
		});
		btnEditar.setEnabled(false);

		btnExcluir = new JButton("Excluir");
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.insets = new Insets(0, 0, 0, 5);
		gbc_btnExcluir.gridx = 4;
		gbc_btnExcluir.gridy = 2;
		contentPanel.add(btnExcluir, gbc_btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int result = JOptionPane.showConfirmDialog(null,
							"Deseja excluir a categoria " + c.getTipo()
									+ "?", "Deseja realizar essa operação?",
							dialogButton);

						if (result == JOptionPane.YES_OPTION) {

							CategoriaDAO dao = new CategoriaDAO();
							if (dao.deletar(c.getCodigo()) == "NO") {
								JOptionPane.showMessageDialog(null,
										"Não foi possivel excluir essa categoria",
										"Problemas ao Excluir", 1, imgAlert);
								return;
							} else {
								
								JOptionPane.showMessageDialog(null,
										"Exclusão feita com sucesso");
								
								txtCategoria.setText("");
								
								btnSalvar.setEnabled(true);
								btnEditar.setEnabled(false);
								btnExcluir.setEnabled(false);
								
							}

					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"Problemas ao excluir categoria");
				}
			}
		});
		btnExcluir.setEnabled(false);

		JButton cancelButton = new JButton("Cancelar");
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.gridx = 5;
		gbc_cancelButton.gridy = 2;
		contentPanel.add(cancelButton, gbc_cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadCategoria.this.dispose();
			}
		});

	}


}
