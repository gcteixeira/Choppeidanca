package choppeidanca.swing.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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

import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;

import choppeidanca.modelo.Cidade;
import choppeidanca.modelo.Pessoa;
import choppeidanca.modelosJtable.Modelo_PessoaFisica;
import choppeidanca.persistencia.PessoaDAO;
import choppeidanca.utils.Singleton;

import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CadPessoaFisica extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtApelido;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtComplemento;
	private JFormattedTextField txtTelefone;
	private JFormattedTextField txtDataNasc;
	private JFormattedTextField txtCpf;
	private JFormattedTextField txtRg;
	private JButton btnNewButton;
	private JButton btnSalvar;
	private JButton btnListar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadPessoaFisica dialog = new CadPessoaFisica();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	private ImageIcon imgAlert = new ImageIcon(getClass().getResource(
			"/choppeidanca/imagens/error.png"));
	Modelo_PessoaFisica mpf = new Modelo_PessoaFisica();
	Singleton s = Singleton.getInstance();
	Pessoa p = new Pessoa();
	Cidade c = new Cidade();

	public CadPessoaFisica() {
		setTitle("Choppeidan\u00E7a - Cadastro de Pessoa Fisica");

		// carregaDados();

		MaskFormatter mascaracpf = null;
		MaskFormatter mascararg = null;
		MaskFormatter mascaradata = null;
		MaskFormatter mascarafone = null;

		try {
			mascaracpf = new MaskFormatter("###.###.###-##");
			mascararg = new MaskFormatter("##.###.###-#");
			mascaradata = new MaskFormatter("##/##/####");
			mascarafone = new MaskFormatter("(##)####-####");
			mascaracpf.setPlaceholderCharacter('_');
			mascararg.setPlaceholderCharacter('_');
			mascaradata.setPlaceholderCharacter('_');
			mascarafone.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 838, 465);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 45, 164, 35, 0, 120, 55,
				116, 0 };
		gbl_contentPanel.rowHeights = new int[] { 20, 29, 20, 23, 35, 14, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JLabel lblNome = new JLabel("Nome");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		contentPanel.add(lblNome, gbc_lblNome);

		txtNome = new JTextField();
		GridBagConstraints gbc_txtNome = new GridBagConstraints();
		gbc_txtNome.gridwidth = 2;
		gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNome.insets = new Insets(0, 0, 5, 5);
		gbc_txtNome.gridx = 1;
		gbc_txtNome.gridy = 0;
		contentPanel.add(txtNome, gbc_txtNome);
		txtNome.setColumns(10);

		JLabel RG = new JLabel("RG");
		GridBagConstraints gbc_RG = new GridBagConstraints();
		gbc_RG.anchor = GridBagConstraints.EAST;
		gbc_RG.insets = new Insets(0, 0, 5, 5);
		gbc_RG.gridx = 3;
		gbc_RG.gridy = 0;
		contentPanel.add(RG, gbc_RG);

		txtRg = new JFormattedTextField(mascararg);
		GridBagConstraints gbc_txtRg = new GridBagConstraints();
		gbc_txtRg.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRg.insets = new Insets(0, 0, 5, 5);
		gbc_txtRg.gridx = 4;
		gbc_txtRg.gridy = 0;
		contentPanel.add(txtRg, gbc_txtRg);

		JLabel lblCpf = new JLabel("CPF");
		GridBagConstraints gbc_lblCpf = new GridBagConstraints();
		gbc_lblCpf.anchor = GridBagConstraints.EAST;
		gbc_lblCpf.insets = new Insets(0, 0, 5, 5);
		gbc_lblCpf.gridx = 5;
		gbc_lblCpf.gridy = 0;
		contentPanel.add(lblCpf, gbc_lblCpf);

		txtCpf = new JFormattedTextField(mascaracpf);
		txtCpf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String cpf = txtCpf.getText();
				Pessoa p = new Pessoa();
				cpf = cpf.replace(".", "").replace("-", "");
				if (!p.isCPF(cpf)) {
					JOptionPane.showMessageDialog(null, "CPF INVALIDO");
					txtCpf.grabFocus();
					txtCpf.setText("");
				}
			}
		});
		GridBagConstraints gbc_txtCpf = new GridBagConstraints();
		gbc_txtCpf.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCpf.insets = new Insets(0, 0, 5, 0);
		gbc_txtCpf.gridx = 6;
		gbc_txtCpf.gridy = 0;
		contentPanel.add(txtCpf, gbc_txtCpf);

		JLabel lblApelido = new JLabel("Apelido");
		GridBagConstraints gbc_lblApelido = new GridBagConstraints();
		gbc_lblApelido.anchor = GridBagConstraints.EAST;
		gbc_lblApelido.insets = new Insets(0, 0, 5, 5);
		gbc_lblApelido.gridx = 0;
		gbc_lblApelido.gridy = 1;
		contentPanel.add(lblApelido, gbc_lblApelido);

		txtApelido = new JTextField();
		GridBagConstraints gbc_txtApelido = new GridBagConstraints();
		gbc_txtApelido.gridwidth = 2;
		gbc_txtApelido.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtApelido.insets = new Insets(0, 0, 5, 5);
		gbc_txtApelido.gridx = 1;
		gbc_txtApelido.gridy = 1;
		contentPanel.add(txtApelido, gbc_txtApelido);
		txtApelido.setColumns(10);

		JLabel lblDataDeNascimento = new JLabel("Nascimento");
		GridBagConstraints gbc_lblDataDeNascimento = new GridBagConstraints();
		gbc_lblDataDeNascimento.anchor = GridBagConstraints.EAST;
		gbc_lblDataDeNascimento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataDeNascimento.gridx = 3;
		gbc_lblDataDeNascimento.gridy = 1;
		contentPanel.add(lblDataDeNascimento, gbc_lblDataDeNascimento);

		txtDataNasc = new JFormattedTextField(mascaradata);
		GridBagConstraints gbc_txtDataNasc = new GridBagConstraints();
		gbc_txtDataNasc.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDataNasc.insets = new Insets(0, 0, 5, 5);
		gbc_txtDataNasc.gridx = 4;
		gbc_txtDataNasc.gridy = 1;
		contentPanel.add(txtDataNasc, gbc_txtDataNasc);

		JLabel lblTelefone = new JLabel("Telefone");
		GridBagConstraints gbc_lblTelefone = new GridBagConstraints();
		gbc_lblTelefone.anchor = GridBagConstraints.EAST;
		gbc_lblTelefone.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefone.gridx = 5;
		gbc_lblTelefone.gridy = 1;
		contentPanel.add(lblTelefone, gbc_lblTelefone);

		txtTelefone = new JFormattedTextField(mascarafone);
		GridBagConstraints gbc_txtTelefone = new GridBagConstraints();
		gbc_txtTelefone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTelefone.insets = new Insets(0, 0, 5, 0);
		gbc_txtTelefone.gridx = 6;
		gbc_txtTelefone.gridy = 1;
		contentPanel.add(txtTelefone, gbc_txtTelefone);

		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		GridBagConstraints gbc_lblEndereo = new GridBagConstraints();
		gbc_lblEndereo.anchor = GridBagConstraints.EAST;
		gbc_lblEndereo.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndereo.gridx = 0;
		gbc_lblEndereo.gridy = 2;
		contentPanel.add(lblEndereo, gbc_lblEndereo);

		txtEndereco = new JTextField();
		GridBagConstraints gbc_txtEndereco = new GridBagConstraints();
		gbc_txtEndereco.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEndereco.insets = new Insets(0, 0, 5, 5);
		gbc_txtEndereco.gridwidth = 4;
		gbc_txtEndereco.gridx = 1;
		gbc_txtEndereco.gridy = 2;
		contentPanel.add(txtEndereco, gbc_txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblN = new JLabel("N\u00B0");
		GridBagConstraints gbc_lblN = new GridBagConstraints();
		gbc_lblN.anchor = GridBagConstraints.EAST;
		gbc_lblN.insets = new Insets(0, 0, 5, 5);
		gbc_lblN.gridx = 5;
		gbc_lblN.gridy = 2;
		contentPanel.add(lblN, gbc_lblN);

		txtNumero = new JTextField();
		GridBagConstraints gbc_txtNumero = new GridBagConstraints();
		gbc_txtNumero.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNumero.insets = new Insets(0, 0, 5, 0);
		gbc_txtNumero.gridx = 6;
		gbc_txtNumero.gridy = 2;
		contentPanel.add(txtNumero, gbc_txtNumero);
		txtNumero.setColumns(10);

		JLabel lblBairro = new JLabel("Bairro");
		GridBagConstraints gbc_lblBairro = new GridBagConstraints();
		gbc_lblBairro.anchor = GridBagConstraints.EAST;
		gbc_lblBairro.insets = new Insets(0, 0, 5, 5);
		gbc_lblBairro.gridx = 0;
		gbc_lblBairro.gridy = 3;
		contentPanel.add(lblBairro, gbc_lblBairro);

		txtBairro = new JTextField();
		GridBagConstraints gbc_txtBairro = new GridBagConstraints();
		gbc_txtBairro.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBairro.insets = new Insets(0, 0, 5, 5);
		gbc_txtBairro.gridx = 1;
		gbc_txtBairro.gridy = 3;
		contentPanel.add(txtBairro, gbc_txtBairro);
		txtBairro.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade");
		GridBagConstraints gbc_lblCidade = new GridBagConstraints();
		gbc_lblCidade.anchor = GridBagConstraints.EAST;
		gbc_lblCidade.insets = new Insets(0, 0, 5, 5);
		gbc_lblCidade.gridx = 2;
		gbc_lblCidade.gridy = 3;
		contentPanel.add(lblCidade, gbc_lblCidade);

		btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Runnable r = new Runnable() {

					@Override
					public void run() {
						s.statusBotao = true;
						BuscaCidade bc = new BuscaCidade();
						bc.setVisible(true);

						while (bc.isShowing()) {

						}

						txtCidade.setText(s.cidade.getNome() + " - "
								+ s.cidade.getUf().getSigla());
						c = s.cidade;

					}
				};

				Thread t = new Thread(r);
				t.start();

			}
		});
		btnNewButton.setIcon(new ImageIcon(CadPessoaFisica.class
				.getResource("/choppeidanca/imagens/magnifier.png")));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 3;
		contentPanel.add(btnNewButton, gbc_btnNewButton);

		txtCidade = new JTextField();
		txtCidade.setEditable(false);
		GridBagConstraints gbc_txtCidade = new GridBagConstraints();
		gbc_txtCidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCidade.insets = new Insets(0, 0, 5, 5);
		gbc_txtCidade.gridx = 4;
		gbc_txtCidade.gridy = 3;
		contentPanel.add(txtCidade, gbc_txtCidade);
		txtCidade.setColumns(10);

		JLabel lblComplemento = new JLabel("Complemento");
		GridBagConstraints gbc_lblComplemento = new GridBagConstraints();
		gbc_lblComplemento.anchor = GridBagConstraints.EAST;
		gbc_lblComplemento.insets = new Insets(0, 0, 5, 5);
		gbc_lblComplemento.gridx = 5;
		gbc_lblComplemento.gridy = 3;
		contentPanel.add(lblComplemento, gbc_lblComplemento);

		txtComplemento = new JTextField();
		GridBagConstraints gbc_txtComplemento = new GridBagConstraints();
		gbc_txtComplemento.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtComplemento.insets = new Insets(0, 0, 5, 0);
		gbc_txtComplemento.gridx = 6;
		gbc_txtComplemento.gridy = 3;
		contentPanel.add(txtComplemento, gbc_txtComplemento);
		txtComplemento.setColumns(10);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					Pessoa p = new Pessoa();
					p.setNome(txtNome.getText());
					// se fisica 0, se juridica 1
					p.setTipo(0);
					p.setApelido(txtApelido.getText());
					p.setEndereco(txtEndereco.getText());
					p.setNumero(txtNumero.getText());
					p.setBairro(txtBairro.getText());
					p.setCidade(c);
					p.setTelefone(txtTelefone.getText());

					SimpleDateFormat formatIn = new SimpleDateFormat(
							"dd/MM/yyyy");
					String str = txtDataNasc.getText();
					Date date;
					try {
						date = formatIn.parse(str);
						java.sql.Date datesql = new java.sql.Date(date
								.getTime());
						p.setDataNascimento(datesql);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Data Invalida");
					}

					p.setCpfcnpj(txtCpf.getText());
					p.setRginscricao(txtRg.getText());
					p.setComplemento(txtComplemento.getText());

					s.pessoas.add(p);

					if (verificaCamposNulos() == "OK") {
						PessoaDAO dao = new PessoaDAO();
						if (dao.inserir(p) != "Erro") {

							limparCampos();

							JOptionPane.showMessageDialog(null,
									"Pessoa Fisica cadastrada com sucesso");
						}

					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Erro ao cadastrar Pessoa fisica");

				}

			}
		});
		btnSalvar.setIcon(new ImageIcon(CadPessoaFisica.class
				.getResource("/choppeidanca/imagens/Salvar.png")));
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.fill = GridBagConstraints.BOTH;
		gbc_btnSalvar.insets = new Insets(0, 0, 5, 0);
		gbc_btnSalvar.gridx = 6;
		gbc_btnSalvar.gridy = 4;
		contentPanel.add(btnSalvar, gbc_btnSalvar);

		JButton btnNewButton_1 = new JButton("Novo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();

				btnSalvar.setEnabled(true);
				btnEditar.setEnabled(false);
				btnExcluir.setEnabled(false);
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 6;
		contentPanel.add(btnNewButton_1, gbc_btnNewButton_1);

		JPanel buttonPane = new JPanel();
		GridBagConstraints gbc_buttonPane = new GridBagConstraints();
		gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonPane.gridwidth = 6;
		gbc_buttonPane.gridx = 1;
		gbc_buttonPane.gridy = 6;
		contentPanel.add(buttonPane, gbc_buttonPane);
		GridBagLayout gbl_buttonPane = new GridBagLayout();
		gbl_buttonPane.columnWidths = new int[] { 135, 89, 291, 61, 63, 75, 0 };
		gbl_buttonPane.rowHeights = new int[] { 23, 0 };
		gbl_buttonPane.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gbl_buttonPane.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		buttonPane.setLayout(gbl_buttonPane);

		btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Runnable r = new Runnable() {

					@Override
					public void run() {

						s.statusBotao = false;

						BuscaPessoa bc = new BuscaPessoa();
						bc.setVisible(true);

						while (bc.isShowing()) {

						}
						p = s.p;
						if (p.getTipo() == 1) {
							JOptionPane.showMessageDialog(null,
									"Por Favor, Selecione uma Pessoa FISICA",
									"Operação Ilegal", 1, imgAlert);
							return;
						} else {

							txtNome.setText(p.getNome());
							txtApelido.setText(p.getApelido());
							txtEndereco.setText(p.getEndereco());
							txtNumero.setText(p.getNumero());
							txtBairro.setText(p.getBairro());
							txtCidade.setText(p.getCidade().getNome());
							txtTelefone.setText(p.getTelefone());
							txtDataNasc.setText(mpf.retornaDate(p
									.getDataNascimento()));
							txtRg.setText(p.getRginscricao());
							txtCpf.setText(p.getCpfcnpj());
							txtComplemento.setText(p.getComplemento());
							c = s.p.getCidade();

							String nome = txtNome.getText();
							String endereco = txtEndereco.getText();
							String bairro = txtBairro.getText();
							String cidade = txtCidade.getText();
							String telefone = txtTelefone.getText();
							String datanasc = txtDataNasc.getText();
							String cpf = txtCpf.getText();
							String rg = txtRg.getText();

							if (!nome.equals("") || !endereco.equals("")
									|| !bairro.equals("") || !cidade.equals("")
									|| !telefone.equals("")
									|| !datanasc.equals("") || !cpf.equals("")
									|| !rg.equals("")) {

								btnSalvar.setEnabled(false);
								btnEditar.setEnabled(true);
								btnExcluir.setEnabled(true);
							}

						}

					}

				};

				Thread t = new Thread(r);
				t.start();

			}
		});
		GridBagConstraints gbc_btnListar = new GridBagConstraints();
		gbc_btnListar.anchor = GridBagConstraints.EAST;
		gbc_btnListar.insets = new Insets(0, 0, 0, 5);
		gbc_btnListar.gridx = 2;
		gbc_btnListar.gridy = 0;
		buttonPane.add(btnListar, gbc_btnListar);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					p.setNome(txtNome.getText());
					// se fisica 0, se juridica 1
					p.setTipo(0);
					p.setApelido(txtApelido.getText());
					p.setEndereco(txtEndereco.getText());
					p.setNumero(txtNumero.getText());
					p.setBairro(txtBairro.getText());
					p.setCidade(c);
					p.setTelefone(txtTelefone.getText());

					SimpleDateFormat formatIn = new SimpleDateFormat(
							"dd/MM/yyyy");
					String str = txtDataNasc.getText();
					Date date;
					try {
						date = formatIn.parse(str);
						java.sql.Date datesql = new java.sql.Date(date
								.getTime());
						p.setDataNascimento(datesql);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Data Invalida");
					}

					p.setCpfcnpj(txtCpf.getText());
					p.setRginscricao(txtRg.getText());
					p.setComplemento(txtComplemento.getText());

					if (verificaCamposNulos() == "OK") {
						PessoaDAO dao = new PessoaDAO();
						dao.alterar(p);

						JOptionPane.showMessageDialog(null,
								"Alteração feita com sucesso");

						limparCampos();

						btnSalvar.setEnabled(true);
						btnEditar.setEnabled(false);
						btnExcluir.setEnabled(false);
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao alterar");
				}

			}
		});
		btnEditar.setEnabled(false);
		GridBagConstraints gbc_btnEditar = new GridBagConstraints();
		gbc_btnEditar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEditar.insets = new Insets(0, 0, 0, 5);
		gbc_btnEditar.gridx = 3;
		gbc_btnEditar.gridy = 0;
		buttonPane.add(btnEditar, gbc_btnEditar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int dialogButton = JOptionPane.YES_NO_OPTION;
				int result = JOptionPane.showConfirmDialog(null,
						"Deseja excluir a pessoa " + p.getNome() + "?",
						"Deseja realizar essa operação?", dialogButton);

				if (result == JOptionPane.YES_OPTION) {
					PessoaDAO dao = new PessoaDAO();
					if (dao.deletar(p.getId()) == "NO") {
						JOptionPane.showMessageDialog(null,
								"Não foi possivel excluir essa Pessoa",
								"Problemas ao Excluir", 1, imgAlert);
						return;
					} else {

						JOptionPane.showMessageDialog(null,
								"Exclusao feita com sucesso");

						limparCampos();

						btnSalvar.setEnabled(true);
						btnEditar.setEnabled(false);
						btnExcluir.setEnabled(false);

					}

				}
			}
		});
		btnExcluir.setIcon(new ImageIcon(CadPessoaFisica.class
				.getResource("/choppeidanca/imagens/table_row_delete.png")));
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.fill = GridBagConstraints.BOTH;
		gbc_btnExcluir.insets = new Insets(0, 0, 0, 5);
		gbc_btnExcluir.gridx = 4;
		gbc_btnExcluir.gridy = 0;
		buttonPane.add(btnExcluir, gbc_btnExcluir);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadPessoaFisica.this.dispose();
			}
		});
		btnCancelar.setIcon(new ImageIcon(CadPessoaFisica.class
				.getResource("/choppeidanca/imagens/cancel.png")));
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.fill = GridBagConstraints.BOTH;
		gbc_btnCancelar.gridx = 5;
		gbc_btnCancelar.gridy = 0;
		buttonPane.add(btnCancelar, gbc_btnCancelar);

	}

	protected void limparCampos() {
		txtApelido.setText("");
		txtBairro.setText("");
		txtCidade.setText("");
		txtComplemento.setText("");
		txtCpf.setText("");
		txtDataNasc.setText("");
		txtEndereco.setText("");
		txtNome.setText("");
		txtNumero.setText("");
		txtRg.setText("");
		txtTelefone.setText("");

	}

	public String verificaCamposNulos() {

		String nome = txtNome.getText();
		String endereco = txtEndereco.getText();
		String bairro = txtBairro.getText();
		String cidade = txtCidade.getText();
		String telefone = txtTelefone.getText();
		String datanasc = txtDataNasc.getText();
		String cpf = txtCpf.getText();
		String rg = txtRg.getText();

		if (nome.equals("") && endereco.equals("") && bairro.equals("")
				&& cidade.equals("") && telefone.equals("")
				&& datanasc.equals("") && cpf.equals("") && rg.equals("")) {

			JOptionPane
					.showMessageDialog(
							null,
							"Os campos nome, data de nascimento, rg, cpf, endereço, bairro, cidade e telefone",
							"Campos Nulos", 1, imgAlert);
			return "NO";
		}

		if (nome.equals("")) {
			JOptionPane.showMessageDialog(null,
					"O campo nome deve conter um valor",
					"Campo não pode ser nulo", 1, imgAlert);
			txtNome.grabFocus();
			return "NO";
		}
		if (endereco.equals("")) {
			JOptionPane.showMessageDialog(null,
					"O campo endereço deve conter um valor",
					"Campo não pode ser nulo", 1, imgAlert);
			txtEndereco.grabFocus();
			return "NO";
		}
		if (bairro.equals("")) {
			JOptionPane.showMessageDialog(null,
					"O campo bairro deve conter um valor",
					"Campo não pode ser nulo", 1, imgAlert);
			txtBairro.grabFocus();
			return "NO";
		}
		if (cidade.equals("")) {
			JOptionPane.showMessageDialog(null,
					"Selecione uma cidade clicando no botão com a lupa",
					"Campo não pode ser nulo", 1, imgAlert);
			btnNewButton.grabFocus();
			return "NO";
		}
		if (telefone.equals("")) {
			JOptionPane.showMessageDialog(null,
					"O campo telefone deve conter um valor",
					"Campo não pode ser nulo", 1, imgAlert);
			txtTelefone.grabFocus();
			return "NO";
		}
		if (datanasc.equals("")) {
			JOptionPane.showMessageDialog(null,
					"O campo data de nascimento deve conter um valor",
					"Campo não pode ser nulo", 1, imgAlert);
			txtDataNasc.grabFocus();
			return "NO";
		}
		if (cpf.equals("")) {
			JOptionPane.showMessageDialog(null,
					"O campo cpf deve conter um valor",
					"Campo não pode ser nulo", 1, imgAlert);
			txtCpf.grabFocus();
			return "NO";
		}
		if (rg.equals("")) {
			JOptionPane.showMessageDialog(null,
					"O campo rg deve conter um valor",
					"Campo não pode ser nulo", 1, imgAlert);
			txtRg.grabFocus();
			return "NO";
		}
		return "OK";
	}

	// private void carregaDados() {
	// PessoaDAO dao = new PessoaDAO();
	// mpf.setPessoas(dao.listar());
	// }

}
