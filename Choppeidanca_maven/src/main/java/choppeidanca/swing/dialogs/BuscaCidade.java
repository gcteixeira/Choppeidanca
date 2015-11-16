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

import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;

import javax.swing.JTable;

import choppeidanca.modelo.Cidade;
import choppeidanca.modelo.Uf;
import choppeidanca.modelosJtable.Modelo_Cidade;
import choppeidanca.persistencia.CidadeDAO;
import choppeidanca.utils.Singleton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscaCidade extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscaCidade dialog = new BuscaCidade();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	Singleton s = Singleton.getInstance();
	Modelo_Cidade mc = new Modelo_Cidade();
	private ImageIcon imgAlert = new ImageIcon(getClass().getResource(
			"/choppeidanca/imagens/error.png"));
	private JButton btnNovo;
	Cidade c = new Cidade();
	Uf uf = new Uf();
	
	public BuscaCidade() {
		setTitle("Choppeidan\u00E7a - Busca de Cidade");

		carregaDados();

		s.cidade = c;
		s.cidade.setUf(uf);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
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
		table.setModel(mc);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnNovo = new JButton("Nova Cidade");
		btnNovo.setVisible(s.statusBotao);
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Runnable r = new Runnable() {
					
					@Override
					public void run() {
						CadCidade cc = new CadCidade();
						cc.setVisible(true);
						
						while (cc.isShowing()) {
							
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
									"Por Favor, selecione uma linha da lista de Cidades",
									"A��o Ilegal", 1, imgAlert);

				} else {
					c = getInstance();

					s.cidade = c;
					BuscaCidade.this.dispose();

				}
				
			}
		});
		buttonPane.add(okButton);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Cidade c = getInstance();
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int result = JOptionPane.showConfirmDialog(null,
							"Deseja excluir a cidade " + c.getNome() + "?",
							"Deseja realizar essa operação?", dialogButton);

					if (result == JOptionPane.YES_OPTION) {

						CidadeDAO dao = new CidadeDAO();
						if (dao.deletar(c.getId()) == "NO") {
							JOptionPane.showMessageDialog(null,
									"N�o foi possivel excluir essa cidade",
									"Problemas ao Excluir", 1, imgAlert);
						} else {
							carregaDados();
							JOptionPane.showMessageDialog(null,
									"Exclus�o feita com sucesso");
						}

					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"Problemas ao excluir cidade");
				}

			}
		});
		buttonPane.add(btnExcluir);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscaCidade.this.dispose();
			}
		});
		buttonPane.add(cancelButton);

	}

	protected Cidade getInstance() {
		Cidade c = mc.getSelectedObject(table.getSelectedRow());
		return c;
	}

	private void carregaDados() {
		CidadeDAO dao = new CidadeDAO();
		mc.setCidades(dao.listar());
		mc.fireTableDataChanged();
	}

}
