package choppeidanca.swing.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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

import javax.swing.JScrollPane;
import javax.swing.JTable;

import choppeidanca.modelo.CondicaoPagamento;
import choppeidanca.modelosJtable.Modelo_Condicao;
import choppeidanca.persistencia.CondicaoPagamentoDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadCondicao extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCondicao;
	private JTextField txtQtd;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadCondicao dialog = new CadCondicao();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	Modelo_Condicao mc = new Modelo_Condicao();
	private JButton btnExcluir;
	private JButton btnEditar;
	private JButton btnSalvar;
	private ImageIcon imgAlert = new ImageIcon(getClass().getResource(
			"/choppeidanca/imagens/error.png"));
	public CadCondicao() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		carregaDados();
		
		setTitle("Choppeidan\u00E7a - Cadastro de Condi\u00E7\u00E3o de Pagamento");
		setModal(true);
		setBounds(100, 100, 647, 396);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 289, 0, 51, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		JLabel lblCondio = new JLabel("Condi\u00E7\u00E3o");
		GridBagConstraints gbc_lblCondio = new GridBagConstraints();
		gbc_lblCondio.insets = new Insets(0, 0, 5, 5);
		gbc_lblCondio.anchor = GridBagConstraints.EAST;
		gbc_lblCondio.gridx = 0;
		gbc_lblCondio.gridy = 0;
		contentPanel.add(lblCondio, gbc_lblCondio);
		
		txtCondicao = new JTextField();
		GridBagConstraints gbc_txtCondicao = new GridBagConstraints();
		gbc_txtCondicao.insets = new Insets(0, 0, 5, 5);
		gbc_txtCondicao.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCondicao.gridx = 1;
		gbc_txtCondicao.gridy = 0;
		contentPanel.add(txtCondicao, gbc_txtCondicao);
		txtCondicao.setColumns(10);
		
		JLabel lblQuantidadeDeX = new JLabel("Quantidade de X");
		GridBagConstraints gbc_lblQuantidadeDeX = new GridBagConstraints();
		gbc_lblQuantidadeDeX.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantidadeDeX.anchor = GridBagConstraints.EAST;
		gbc_lblQuantidadeDeX.gridx = 2;
		gbc_lblQuantidadeDeX.gridy = 0;
		contentPanel.add(lblQuantidadeDeX, gbc_lblQuantidadeDeX);
		
		txtQtd = new JTextField();
		GridBagConstraints gbc_txtQtd = new GridBagConstraints();
		gbc_txtQtd.insets = new Insets(0, 0, 5, 5);
		gbc_txtQtd.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQtd.gridx = 3;
		gbc_txtQtd.gridy = 0;
		contentPanel.add(txtQtd, gbc_txtQtd);
		txtQtd.setColumns(10);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {

					CondicaoPagamento cp = new CondicaoPagamento();
					cp.setNome(txtCondicao.getText());
					cp.setQtdVezes(Integer.parseInt(txtQtd.getText()));

					CondicaoPagamentoDAO dao = new CondicaoPagamentoDAO();
					dao.inserir(cp);

					carregaDados();

					limparCampos();
					
					
					JOptionPane.showMessageDialog(null,
							"Cadastrado com sucesso!");
				} catch (Exception ex) {
					// TODO: handle exception
				}
			}
		});
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.insets = new Insets(0, 0, 5, 0);
		gbc_btnSalvar.gridx = 4;
		gbc_btnSalvar.gridy = 0;
		contentPanel.add(btnSalvar, gbc_btnSalvar);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPanel.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (e.getClickCount() == 2) {

					CondicaoPagamento cp = getInstance();

					txtCondicao.setText(cp.getNome());
					txtQtd.setText(Integer.toString(cp.getQtdVezes()));

					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
					btnSalvar.setEnabled(false);

				}
				
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(mc);
			
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					CondicaoPagamento cp = getInstance();
					cp.setNome(txtCondicao.getText());
					cp.setQtdVezes(Integer.parseInt(txtQtd.getText()));

					CondicaoPagamentoDAO dao = new CondicaoPagamentoDAO();
					dao.alterar(cp);

					JOptionPane.showMessageDialog(null,
							"Alteração Feita com Sucesso");

					carregaDados();
					limparCampos();
					
					btnSalvar.setEnabled(true);
					btnEditar.setEnabled(false);
					btnExcluir.setEnabled(false);

				} catch (Exception ex) {

				}

			}
		});
		btnEditar.setEnabled(false);
		buttonPane.add(btnEditar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CondicaoPagamento cp = getInstance();
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int result = JOptionPane.showConfirmDialog(null,
							"Deseja excluir a condicao " + cp.getNome() + "?",
							"Deseja realizar essa operação", dialogButton);
					
					if (result == JOptionPane.YES_OPTION) {

						CondicaoPagamentoDAO dao = new CondicaoPagamentoDAO();
						if (dao.deletar(cp.getId()) == "NO") {
							JOptionPane
									.showMessageDialog(
											null,
											"Não é possivel excluir essa condição",
											"Problemas ao Excluir", 1, imgAlert);
							return;
						} else {

							carregaDados();

							JOptionPane.showMessageDialog(null,
									"Exclusão feita com sucesso");

							
							limparCampos();
							
							btnSalvar.setEnabled(true);
							btnEditar.setEnabled(false);
							btnExcluir.setEnabled(false);
							
						}
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				
				}
			}
		});
		btnExcluir.setEnabled(false);
		buttonPane.add(btnExcluir);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadCondicao.this.dispose();
			}
		});
		buttonPane.add(cancelButton);

	}

	protected void limparCampos() {
		txtCondicao.setText("");
		txtQtd.setText("");		
	}

	private void carregaDados() {
		CondicaoPagamentoDAO dao = new CondicaoPagamentoDAO();
		mc.setCondicoes(dao.listar());
		mc.fireTableDataChanged();

	}

	protected CondicaoPagamento getInstance() {
		CondicaoPagamento cp = mc.getSelectedObject(table.getSelectedRow());
		return cp;
	}

}
