package choppeidanca.swing.paineis;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import choppeidanca.persistencia.ConexaoSing;
import choppeidanca.swing.frames.Principal;
import choppeidanca.utils.MinhaAplicacao;

public class PainelLogin extends JPanel {

	private JTextField txtUsuario;
	private JPasswordField txtSenha;

	// private ImageIcon imgCheck = new
	// ImageIcon(getClass().getResource("/logo.png"));

	/**
	 * Create the panel.
	 */
	public PainelLogin() {
		setToolTipText("a");
		setBackground(Color.YELLOW);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel panel = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {

				Graphics2D g2 = (Graphics2D) g.create();

				g2.setComposite(AlphaComposite.getInstance(
						AlphaComposite.SRC_OVER, 0.40f));
				g2.setColor(Color.WHITE);
				g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
				g2.setStroke(new BasicStroke(3));
				g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

				g2.dispose();

				super.paintComponent(g);
			}

		};

		panel.setMinimumSize(new Dimension(100, 100));
		panel.setPreferredSize(new Dimension(100, 100));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel = new JLabel(
				"Bem Vindo ao Choppeidan\u00E7a System\r\n");
		lblNewLabel.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Usuario");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		txtUsuario = new JTextField();
		{
			txtUsuario.setLayout(new BorderLayout());
		}
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (txtUsuario.getText().equals("admin")) {

				}

			}
		});

		GridBagConstraints gbc_txtUsuario = new GridBagConstraints();
		gbc_txtUsuario.gridwidth = 2;
		gbc_txtUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_txtUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsuario.gridx = 0;
		gbc_txtUsuario.gridy = 2;
		panel.add(txtUsuario, gbc_txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Senha");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 2;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		txtSenha = new JPasswordField();
		GridBagConstraints gbc_txtSenha = new GridBagConstraints();
		gbc_txtSenha.gridwidth = 2;
		gbc_txtSenha.insets = new Insets(0, 0, 5, 0);
		gbc_txtSenha.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSenha.gridx = 0;
		gbc_txtSenha.gridy = 4;
		panel.add(txtSenha, gbc_txtSenha);
				
						JButton btnLog = new JButton("Logar");
						GridBagConstraints gbc_btnLog = new GridBagConstraints();
						gbc_btnLog.anchor = GridBagConstraints.EAST;
						gbc_btnLog.insets = new Insets(0, 0, 5, 5);
						gbc_btnLog.gridx = 0;
						gbc_btnLog.gridy = 5;
						panel.add(btnLog, gbc_btnLog);
						btnLog.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {

								String usuario = txtUsuario.getText();
								String senha = txtSenha.getText();

								if (usuario.equals("admin") && senha.equals("admin")) {

									MinhaAplicacao.get().getTelaPrincipal().showPrincipal();

									ConexaoSing cs = ConexaoSing.getInstance();
									Connection con = cs.conectar();

								} else {
									JOptionPane.showMessageDialog(null,
											"Usuário ou senha incorretos", "Falha no Login", 1);
								}

							}
						});

		setOpaque(false);

		panel.setOpaque(false);
		
				JButton btnCancelar = new JButton("Cancelar");
				GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
				gbc_btnCancelar.anchor = GridBagConstraints.WEST;
				gbc_btnCancelar.insets = new Insets(0, 0, 5, 0);
				gbc_btnCancelar.gridx = 1;
				gbc_btnCancelar.gridy = 5;
				panel.add(btnCancelar, gbc_btnCancelar);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						MinhaAplicacao.get().getTelaPrincipal().dispose();

					}
				});

	}

	// Declaracao da imagem
	//private Image img = new ImageIcon(getClass().getResource(
	//		"/choppeidanca/imagens/img.jpg")).getImage();

//	@Override
//	protected void paintComponent(Graphics g) {
//
//		Graphics2D g2 = (Graphics2D) g.create();
//
//		g2.setColor(Color.BLACK);
//		g2.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
//		int metadeLarguraPainel = getWidth() / 2;
//		int metadeAlturaPainel = getHeight() / 2;
//		//int metadeLarguraImg = img.getWidth(null) / 2;
//		//int metadeAlturaImg = img.getHeight(null) / 2;
//		//int x = metadeLarguraPainel - metadeLarguraImg;
//		//int y = metadeAlturaPainel - metadeAlturaImg;
//		//g2.drawImage(img, x, y, null);
//
//		g2.dispose();
//		super.paintComponent(g);
//	}

}
