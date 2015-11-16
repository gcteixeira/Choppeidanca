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
import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;

import choppeidanca.modelo.Cidade;
import choppeidanca.modelo.Pessoa;
import choppeidanca.modelosJtable.Modelo_PessoaJuridica;
import choppeidanca.persistencia.PessoaDAO;
import choppeidanca.utils.Singleton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CadPessoaJuridica extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtRazao;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtComplemento;
	private JFormattedTextField txtInscricao;
	private JFormattedTextField txtCnpj;
	private JFormattedTextField txtDataCriacao;
	private JFormattedTextField txtTelefone;
	private JButton button;
	private JButton btnSalvar;
	private JButton btnNovo;
	private JButton btnListar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnCancelar;
	private JLabel lblContato;
	private JTextField txtContato;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadPessoaJuridica dialog = new CadPessoaJuridica();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	Pessoa p = new Pessoa();
	Cidade c = new Cidade();
	Singleton s = Singleton.getInstance();
	Modelo_PessoaJuridica mpj = new Modelo_PessoaJuridica();
	private ImageIcon imgAlert = new ImageIcon(getClass().getResource(
			"/choppeidanca/imagens/error.png"));

	public CadPessoaJuridica() {

		MaskFormatter mascaracnpj = null;
		MaskFormatter mascaraie = null;
		MaskFormatter mascaradata = null;
		MaskFormatter mascarafone = null;

		try {
			mascaracnpj = new MaskFormatter("##.###.###/####-##");
			mascaraie = new MaskFormatter("#######");
			mascaradata = new MaskFormatter("##/##/####");
			mascarafone = new MaskFormatter("(##)####-####");
			mascaracnpj.setPlaceholderCharacter('_');
			mascaraie.setPlaceholderCharacter('_');
			mascaradata.setPlaceholderCharacter('_');
			mascarafone.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			e.printStackTrace();
		}

		setTitle("Choppeidan\u00E7a - Cadastro de Pessoa Juridica");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 932, 466);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 158, 0, 127, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JLabel lblNomeFantasia = new JLabel("Nome Fantasia");
		GridBagConstraints gbc_lblNomeFantasia = new GridBagConstraints();
		gbc_lblNomeFantasia.anchor = GridBagConstraints.EAST;
		gbc_lblNomeFantasia.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomeFantasia.gridx = 0;
		gbc_lblNomeFantasia.gridy = 0;
		contentPanel.add(lblNomeFantasia, gbc_lblNomeFantasia);

		txtNome = new JTextField();
		GridBagConstraints gbc_txtNome = new GridBagConstraints();
		gbc_txtNome.gridwidth = 2;
		gbc_txtNome.insets = new Insets(0, 0, 5, 5);
		gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNome.gridx = 1;
		gbc_txtNome.gridy = 0;
		contentPanel.add(txtNome, gbc_txtNome);
		txtNome.setColumns(10);

		JLabel lblIe = new JLabel("Inscri\u00E7\u00E3o");
		GridBagConstraints gbc_lblIe = new GridBagConstraints();
		gbc_lblIe.anchor = GridBagConstraints.EAST;
		gbc_lblIe.insets = new Insets(0, 0, 5, 5);
		gbc_lblIe.gridx = 3;
		gbc_lblIe.gridy = 0;
		contentPanel.add(lblIe, gbc_lblIe);

		txtInscricao = new JFormattedTextField(mascaraie);
		GridBagConstraints gbc_txtInscricao = new GridBagConstraints();
		gbc_txtInscricao.insets = new Insets(0, 0, 5, 5);
		gbc_txtInscricao.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtInscricao.gridx = 4;
		gbc_txtInscricao.gridy = 0;
		contentPanel.add(txtInscricao, gbc_txtInscricao);

		JLabel lblCnpj = new JLabel("CNPJ");
		GridBagConstraints gbc_lblCnpj = new GridBagConstraints();
		gbc_lblCnpj.anchor = GridBagConstraints.EAST;
		gbc_lblCnpj.insets = new Insets(0, 0, 5, 5);
		gbc_lblCnpj.gridx = 5;
		gbc_lblCnpj.gridy = 0;
		contentPanel.add(lblCnpj, gbc_lblCnpj);

		txtCnpj = new JFormattedTextField(mascaracnpj);
		txtCnpj.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String cnpj = txtCnpj.getText();
				Pessoa p = new Pessoa();
				cnpj = cnpj.replace(".", "").replace("/", "").replace("-", "");
				if (!p.isCNPJ(cnpj)) {
					JOptionPane.showMessageDialog(CadPessoaJuridica.this,
							"CNPJ INVALIDO");
					txtCnpj.setText("");
					txtCnpj.requestFocus();
				}
			}
		});
		GridBagConstraints gbc_txtCnpj = new GridBagConstraints();
		gbc_txtCnpj.insets = new Insets(0, 0, 5, 0);
		gbc_txtCnpj.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCnpj.gridx = 6;
		gbc_txtCnpj.gridy = 0;
		contentPanel.add(txtCnpj, gbc_txtCnpj);

		JLabel lblRazoSocial = new JLabel("Raz\u00E3o Social");
		GridBagConstraints gbc_lblRazoSocial = new GridBagConstraints();
		gbc_lblRazoSocial.anchor = GridBagConstraints.EAST;
		gbc_lblRazoSocial.insets = new Insets(0, 0, 5, 5);
		gbc_lblRazoSocial.gridx = 0;
		gbc_lblRazoSocial.gridy = 1;
		contentPanel.add(lblRazoSocial, gbc_lblRazoSocial);

		txtRazao = new JTextField();
		GridBagConstraints gbc_txtRazao = new GridBagConstraints();
		gbc_txtRazao.gridwidth = 2;
		gbc_txtRazao.insets = new Insets(0, 0, 5, 5);
		gbc_txtRazao.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRazao.gridx = 1;
		gbc_txtRazao.gridy = 1;
		contentPanel.add(txtRazao, gbc_txtRazao);
		txtRazao.setColumns(10);

		JLabel lblDataCriao = new JLabel("Data Cria\u00E7\u00E3o");
		GridBagConstraints gbc_lblDataCriao = new GridBagConstraints();
		gbc_lblDataCriao.anchor = GridBagConstraints.EAST;
		gbc_lblDataCriao.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataCriao.gridx = 3;
		gbc_lblDataCriao.gridy = 1;
		contentPanel.add(lblDataCriao, gbc_lblDataCriao);

		txtDataCriacao = new JFormattedTextField(mascaradata);
		GridBagConstraints gbc_txtDataCriacao = new GridBagConstraints();
		gbc_txtDataCriacao.insets = new Insets(0, 0, 5, 5);
		gbc_txtDataCriacao.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDataCriacao.gridx = 4;
		gbc_txtDataCriacao.gridy = 1;
		contentPanel.add(txtDataCriacao, gbc_txtDataCriacao);

		lblContato = new JLabel("Contato");
		GridBagConstraints gbc_lblContato = new GridBagConstraints();
		gbc_lblContato.anchor = GridBagConstraints.EAST;
		gbc_lblContato.insets = new Insets(0, 0, 5, 5);
		gbc_lblContato.gridx = 0;
		gbc_lblContato.gridy = 2;
		contentPanel.add(lblContato, gbc_lblContato);

		txtContato = new JTextField();
		GridBagConstraints gbc_txtContato = new GridBagConstraints();
		gbc_txtContato.gridwidth = 2;
		gbc_txtContato.insets = new Insets(0, 0, 5, 5);
		gbc_txtContato.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtContato.gridx = 1;
		gbc_txtContato.gridy = 2;
		contentPanel.add(txtContato, gbc_txtContato);
		txtContato.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");
		GridBagConstraints gbc_lblTelefone = new GridBagConstraints();
		gbc_lblTelefone.anchor = GridBagConstraints.EAST;
		gbc_lblTelefone.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefone.gridx = 3;
		gbc_lblTelefone.gridy = 2;
		contentPanel.add(lblTelefone, gbc_lblTelefone);

		txtTelefone = new JFormattedTextField(mascarafone);
		GridBagConstraints gbc_txtTelefone = new GridBagConstraints();
		gbc_txtTelefone.anchor = GridBagConstraints.NORTH;
		gbc_txtTelefone.insets = new Insets(0, 0, 5, 5);
		gbc_txtTelefone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTelefone.gridx = 4;
		gbc_txtTelefone.gridy = 2;
		contentPanel.add(txtTelefone, gbc_txtTelefone);

		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		GridBagConstraints gbc_lblEndereo = new GridBagConstraints();
		gbc_lblEndereo.anchor = GridBagConstraints.EAST;
		gbc_lblEndereo.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndereo.gridx = 0;
		gbc_lblEndereo.gridy = 3;
		contentPanel.add(lblEndereo, gbc_lblEndereo);

		txtEndereco = new JTextField();
		GridBagConstraints gbc_txtEndereco = new GridBagConstraints();
		gbc_txtEndereco.gridwidth = 4;
		gbc_txtEndereco.insets = new Insets(0, 0, 5, 5);
		gbc_txtEndereco.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEndereco.gridx = 1;
		gbc_txtEndereco.gridy = 3;
		contentPanel.add(txtEndereco, gbc_txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblNumero = new JLabel("N\u00B0");
		GridBagConstraints gbc_lblNumero = new GridBagConstraints();
		gbc_lblNumero.anchor = GridBagConstraints.EAST;
		gbc_lblNumero.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumero.gridx = 5;
		gbc_lblNumero.gridy = 3;
		contentPanel.add(lblNumero, gbc_lblNumero);

		txtNumero = new JTextField();
		GridBagConstraints gbc_txtNumero = new GridBagConstraints();
		gbc_txtNumero.insets = new Insets(0, 0, 5, 0);
		gbc_txtNumero.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNumero.gridx = 6;
		gbc_txtNumero.gridy = 3;
		contentPanel.add(txtNumero, gbc_txtNumero);
		txtNumero.setColumns(10);

		JLabel lblBairro = new JLabel("Bairro");
		GridBagConstraints gbc_lblBairro = new GridBagConstraints();
		gbc_lblBairro.anchor = GridBagConstraints.EAST;
		gbc_lblBairro.insets = new Insets(0, 0, 5, 5);
		gbc_lblBairro.gridx = 0;
		gbc_lblBairro.gridy = 4;
		contentPanel.add(lblBairro, gbc_lblBairro);

		txtBairro = new JTextField();
		GridBagConstraints gbc_txtBairro = new GridBagConstraints();
		gbc_txtBairro.insets = new Insets(0, 0, 5, 5);
		gbc_txtBairro.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBairro.gridx = 1;
		gbc_txtBairro.gridy = 4;
		contentPanel.add(txtBairro, gbc_txtBairro);
		txtBairro.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade");
		GridBagConstraints gbc_lblCidade = new GridBagConstraints();
		gbc_lblCidade.insets = new Insets(0, 0, 5, 5);
		gbc_lblCidade.gridx = 2;
		gbc_lblCidade.gridy = 4;
		contentPanel.add(lblCidade, gbc_lblCidade);

		button = new JButton("");
		button.addActionListener(new ActionListener() {
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
		button.setIcon(new ImageIcon(CadPessoaJuridica.class
				.getResource("/choppeidanca/imagens/magnifier.png")));
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.fill = GridBagConstraints.HORIZONTAL;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 3;
		gbc_button.gridy = 4;
		contentPanel.add(button, gbc_button);

		txtCidade = new JTextField();
		txtCidade.setEditable(false);
		GridBagConstraints gbc_txtCidade = new GridBagConstraints();
		gbc_txtCidade.insets = new Insets(0, 0, 5, 5);
		gbc_txtCidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCidade.gridx = 4;
		gbc_txtCidade.gridy = 4;
		contentPanel.add(txtCidade, gbc_txtCidade);
		txtCidade.setColumns(10);

		JLabel lblComplemento = new JLabel("Complemento");
		GridBagConstraints gbc_lblComplemento = new GridBagConstraints();
		gbc_lblComplemento.anchor = GridBagConstraints.EAST;
		gbc_lblComplemento.insets = new Insets(0, 0, 5, 5);
		gbc_lblComplemento.gridx = 5;
		gbc_lblComplemento.gridy = 4;
		contentPanel.add(lblComplemento, gbc_lblComplemento);

		txtComplemento = new JTextField();
		GridBagConstraints gbc_txtComplemento = new GridBagConstraints();
		gbc_txtComplemento.insets = new Insets(0, 0, 5, 0);
		gbc_txtComplemento.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtComplemento.gridx = 6;
		gbc_txtComplemento.gridy = 4;
		contentPanel.add(txtComplemento, gbc_txtComplemento);
		txtComplemento.setColumns(10);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Pessoa p = new Pessoa();
					p.setNome(txtNome.getText());
					p.setTipo(1);// se fisica 0, se juridica 1
					p.setApelido(txtRazao.getText());
					p.setEndereco(txtEndereco.getText());
					p.setNumero(txtNumero.getText());
					p.setBairro(txtBairro.getText());
					p.setCidade(c);
					p.setTelefone(txtTelefone.getText());
					SimpleDateFormat formatIn = new SimpleDateFormat(
							"dd/MM/yyyy");
					String str = txtDataCriacao.getText();
					Date date;
					try {
						date = formatIn.parse(str);
						java.sql.Date datesql = new java.sql.Date(date
								.getTime());
						p.setDataNascimento(datesql);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(CadPessoaJuridica.this,
								"Data Invalida");
					}
					p.setCpfcnpj(txtCnpj.getText());
					p.setRginscricao(txtInscricao.getText());
					p.setComplemento(txtComplemento.getText());
					p.setContato(txtContato.getText());
					if (verificaCamposNulos() == "OK") {
						PessoaDAO dao = new PessoaDAO();
						dao.inserir(p);
						limparCampos();
						JOptionPane.showMessageDialog(CadPessoaJuridica.this,
								"Pessoa Juridica cadastrada com sucesso");
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(CadPessoaJuridica.this,
							"Erro ao cadastrar Pessoa Juridica");
					e.printStackTrace();
				}
			}
		});
		btnSalvar.setIcon(new ImageIcon(CadPessoaJuridica.class
				.getResource("/choppeidanca/imagens/Salvar.png")));
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.fill = GridBagConstraints.BOTH;
		gbc_btnSalvar.insets = new Insets(0, 0, 5, 0);
		gbc_btnSalvar.gridx = 6;
		gbc_btnSalvar.gridy = 5;
		contentPanel.add(btnSalvar, gbc_btnSalvar);

		JPanel buttonPane = new JPanel();
		GridBagConstraints gbc_buttonPane = new GridBagConstraints();
		gbc_buttonPane.anchor = GridBagConstraints.EAST;
		gbc_buttonPane.gridwidth = 7;
		gbc_buttonPane.gridx = 0;
		gbc_buttonPane.gridy = 7;
		contentPanel.add(buttonPane, gbc_buttonPane);
		GridBagLayout gbl_buttonPane = new GridBagLayout();
		gbl_buttonPane.columnWidths = new int[] { 737, 0, 0, 47, 65, 0 };
		gbl_buttonPane.rowHeights = new int[] { 23, 0 };
		gbl_buttonPane.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_buttonPane.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		buttonPane.setLayout(gbl_buttonPane);

		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();

				btnSalvar.setEnabled(true);
				btnEditar.setEnabled(false);
				btnExcluir.setEnabled(false);
			}
		});
		GridBagConstraints gbc_btnNovo = new GridBagConstraints();
		gbc_btnNovo.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNovo.insets = new Insets(0, 0, 0, 5);
		gbc_btnNovo.gridx = 0;
		gbc_btnNovo.gridy = 0;
		buttonPane.add(btnNovo, gbc_btnNovo);

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
						if (p.getTipo() == 0) {
							JOptionPane.showMessageDialog(null,
									"Por Favor, Selecione uma Pessoa JURIDICA",
									"Operação Ilegal", 1, imgAlert);
							return;
						} else {

							txtNome.setText(p.getNome());
							txtRazao.setText(p.getApelido());
							txtEndereco.setText(p.getEndereco());
							txtNumero.setText(p.getNumero());
							txtBairro.setText(p.getBairro());
							txtCidade.setText(p.getCidade().getNome());
							txtTelefone.setText(p.getTelefone());
							txtDataCriacao.setText(mpj.retornaDate(p
									.getDataNascimento()));
							txtInscricao.setText(p.getRginscricao());
							txtCnpj.setText(p.getCpfcnpj());
							txtComplemento.setText(p.getComplemento());
							c = s.p.getCidade();

							String nome = txtNome.getText();
							String endereco = txtEndereco.getText();
							String bairro = txtBairro.getText();
							String cidade = txtCidade.getText();
							String telefone = txtTelefone.getText();
							String data = txtDataCriacao.getText();
							String cnpj = txtCnpj.getText();
							String ie = txtInscricao.getText();

							if (!nome.equals("") || !endereco.equals("")
									|| !bairro.equals("") || !cidade.equals("")
									|| !telefone.equals("") || !data.equals("")
									|| !cnpj.equals("") || !ie.equals("")) {

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
		gbc_btnListar.fill = GridBagConstraints.BOTH;
		gbc_btnListar.insets = new Insets(0, 0, 0, 5);
		gbc_btnListar.gridx = 1;
		gbc_btnListar.gridy = 0;
		buttonPane.add(btnListar, gbc_btnListar);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					p.setNome(txtNome.getText());
					// se fisica 0, se juridica 1
					p.setTipo(1);
					p.setApelido(txtRazao.getText());
					p.setEndereco(txtEndereco.getText());
					p.setNumero(txtNumero.getText());
					p.setBairro(txtBairro.getText());
					p.setCidade(c);
					p.setTelefone(txtTelefone.getText());

					SimpleDateFormat formatIn = new SimpleDateFormat(
							"dd/MM/yyyy");
					String str = txtDataCriacao.getText();
					Date date;
					try {
						date = formatIn.parse(str);
						java.sql.Date datesql = new java.sql.Date(date
								.getTime());
						p.setDataNascimento(datesql);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(CadPessoaJuridica.this,
								"Data Invalida");
					}

					p.setCpfcnpj(txtCnpj.getText());
					p.setRginscricao(txtInscricao.getText());
					p.setComplemento(txtComplemento.getText());
					p.setContato(txtContato.getText());

					if (verificaCamposNulos() == "OK") {
						PessoaDAO dao = new PessoaDAO();
						dao.alterar(p);

						JOptionPane.showMessageDialog(CadPessoaJuridica.this,
								"Alteração feita com sucesso");

						limparCampos();

						btnSalvar.setEnabled(true);
						btnEditar.setEnabled(false);
						btnExcluir.setEnabled(false);
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(CadPessoaJuridica.this,
							"Erro ao alterar");
				}
			}
		});
		btnEditar.setEnabled(false);
		GridBagConstraints gbc_btnEditar = new GridBagConstraints();
		gbc_btnEditar.insets = new Insets(0, 0, 0, 5);
		gbc_btnEditar.gridx = 2;
		gbc_btnEditar.gridy = 0;
		buttonPane.add(btnEditar, gbc_btnEditar);

		btnExcluir = new JButton("Excluir");
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
		btnExcluir.setIcon(new ImageIcon(CadPessoaJuridica.class
				.getResource("/choppeidanca/imagens/table_row_delete.png")));
		btnExcluir.setEnabled(false);
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnExcluir.insets = new Insets(0, 0, 0, 5);
		gbc_btnExcluir.gridx = 3;
		gbc_btnExcluir.gridy = 0;
		buttonPane.add(btnExcluir, gbc_btnExcluir);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadPessoaJuridica.this.dispose();
			}
		});
		btnCancelar.setIcon(new ImageIcon(CadPessoaJuridica.class
				.getResource("/choppeidanca/imagens/cancel.png")));
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnCancelar.gridx = 4;
		gbc_btnCancelar.gridy = 0;
		buttonPane.add(btnCancelar, gbc_btnCancelar);

	}

	protected void limparCampos() {
		txtCnpj.setText("");
		txtInscricao.setText("");
		txtDataCriacao.setText("");
		txtNumero.setText("");
		txtTelefone.setText("");
		txtRazao.setText("");
		txtBairro.setText("");
		txtComplemento.setText("");
		txtNome.setText("");
		txtEndereco.setText("");
		txtContato.setText("");
	}

	public String verificaCamposNulos() {

		String nome = txtNome.getText();
		String razao = txtRazao.getText();
		String endereco = txtEndereco.getText();
		String bairro = txtBairro.getText();
		String cidade = txtCidade.getText();
		String telefone = txtTelefone.getText();
		String cnpj = txtCnpj.getText();
		String ie = txtInscricao.getText();

		if (nome.equals("") && endereco.equals("") && bairro.equals("")
				&& cidade.equals("") && telefone.equals("") && razao.equals("")
				&& cnpj.equals("") && ie.equals("")) {

			JOptionPane
					.showMessageDialog(
							null,
							"Os campos nome fantasia, razão social, inscricao, cnpj, endereço, bairro, cidade e telefone nao podem ser nulos",
							"Campos Nulos", 1, imgAlert);
			return "NO";
		}

		if (nome.equals("")) {
			JOptionPane.showMessageDialog(null,
					"O campo nome fantasia deve conter um valor",
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
			button.grabFocus();
			return "NO";
		}
		if (telefone.equals("")) {
			JOptionPane.showMessageDialog(null,
					"O campo telefone deve conter um valor",
					"Campo não pode ser nulo", 1, imgAlert);
			txtTelefone.grabFocus();
			return "NO";
		}
		if (razao.equals("")) {
			JOptionPane.showMessageDialog(null,
					"O campo razão social deve conter um valor",
					"Campo não pode ser nulo", 1, imgAlert);
			txtRazao.grabFocus();
			return "NO";
		}
		if (cnpj.equals("")) {
			JOptionPane.showMessageDialog(null,
					"O campo cnpj deve conter um valor",
					"Campo não pode ser nulo", 1, imgAlert);
			txtCnpj.grabFocus();
			return "NO";
		}
		if (ie.equals("")) {
			JOptionPane.showMessageDialog(null,
					"O campo inscrição deve conter um valor",
					"Campo não pode ser nulo", 1, imgAlert);
			txtInscricao.grabFocus();
			return "NO";
		}
		return "OK";
	}

}
