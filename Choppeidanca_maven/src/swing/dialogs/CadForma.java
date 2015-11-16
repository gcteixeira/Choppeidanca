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

import choppeidanca.modelo.FormaPagamento;
import choppeidanca.modelosJtable.Modelo_FormaDePagamento;
import choppeidanca.persistencia.FormaPagamentoDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadForma extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTipo;
	private JTable tbForma;
	private JButton btnSalvar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadForma dialog = new CadForma();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	Modelo_FormaDePagamento mf = new Modelo_FormaDePagamento();
	private JButton btnExcluir;
	private JButton btnEditar;

	private ImageIcon imgAlert = new ImageIcon(getClass().getResource(
			"/choppeidanca/imagens/error.png"));
	
	public CadForma() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Choppeidan\u00E7a - Cadastro de Forma de Pagamento");
		setModal(true);
		
		carregaDados();
	
	
		setBounds(100, 100, 577, 376);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		JLabel lblTipo = new JLabel("Tipo");
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipo.anchor = GridBagConstraints.EAST;
		gbc_lblTipo.gridx = 0;
		gbc_lblTipo.gridy = 0;
		contentPanel.add(lblTipo, gbc_lblTipo);
		
		txtTipo = new JTextField();
		GridBagConstraints gbc_txtTipo = new GridBagConstraints();
		gbc_txtTipo.insets = new Insets(0, 0, 5, 5);
		gbc_txtTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTipo.gridx = 1;
		gbc_txtTipo.gridy = 0;
		contentPanel.add(txtTipo, gbc_txtTipo);
		txtTipo.setColumns(10);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					FormaPagamento f = new FormaPagamento();
					f.setTipo(txtTipo.getText());

					FormaPagamentoDAO dao = new FormaPagamentoDAO();
					dao.inserir(f);

					carregaDados();

					limparCampos();

					JOptionPane.showMessageDialog(null,
							"Forma de Pagamento cadastrado com sucesso");

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Erro ao cadastrar Forma de Pagamento");
				}

			}
		});
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.insets = new Insets(0, 0, 5, 0);
		gbc_btnSalvar.gridx = 2;
		gbc_btnSalvar.gridy = 0;
		contentPanel.add(btnSalvar, gbc_btnSalvar);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPanel.add(scrollPane, gbc_scrollPane);
		
		tbForma = new JTable();
		tbForma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (e.getClickCount() == 2) {
					
					int linha = tbForma.getSelectedRow();

					FormaPagamento f= getInstance();

					txtTipo.setText(f.getTipo());
					
					btnSalvar.setEnabled(false);
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
					
				}
				
				
			}
		});
		scrollPane.setViewportView(tbForma);
		tbForma.setModel(mf);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FormaPagamento forma = getInstance();

					forma.setTipo(txtTipo.getText());

					FormaPagamentoDAO dao = new FormaPagamentoDAO();
					dao.alterar(forma);

					carregaDados();

					JOptionPane.showMessageDialog(null,"Alteração Feita com Sucesso");

					limparCampos();

					btnSalvar.setEnabled(true);
					btnEditar.setEnabled(false);
					btnExcluir.setEnabled(false);
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao alterar pais");
				}
			}
		});
		buttonPane.add(btnEditar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FormaPagamento forma = getInstance();
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int result = JOptionPane
							.showConfirmDialog(null, "Deseja excluir a forma de pagamento "
									+ forma.getTipo() + "?", "Deseja realizar essa operação?", dialogButton);

					if (result == JOptionPane.YES_OPTION) {

						FormaPagamentoDAO dao = new FormaPagamentoDAO();
						if (dao.deletar(forma.getCodigo()) == "NO") {
							JOptionPane
									.showMessageDialog(
											null,
											"Não Foi possivel excluir essa forma",
											"Problemas ao Excluir", 1, imgAlert);
							return;
						} else {

							mf.fireTableDataChanged();

							JOptionPane.showMessageDialog(null,
									"Exclusão feita com sucesso");

							
							limparCampos();
							
							carregaDados();
							
							btnSalvar.setEnabled(true);
							btnEditar.setEnabled(false);
							btnExcluir.setEnabled(false);
							
						}

					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"Problemas ao excluir forma de pagamento");
				}
			}
		});

		buttonPane.add(btnExcluir);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadForma.this.dispose();
			}
		});

		buttonPane.add(cancelButton);

	}

	private void carregaDados() {
		FormaPagamentoDAO dao = new FormaPagamentoDAO();
		mf.setFormas(dao.listar());
		mf.fireTableDataChanged();

	}

	private FormaPagamento getInstance() {
		FormaPagamento f = mf.getSelectedObject(tbForma.getSelectedRow());
		return f;
	}

	private void limparCampos() {
		txtTipo.setText("");
	}

}
