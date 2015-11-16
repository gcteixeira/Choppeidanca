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
import java.awt.GridBagConstraints;

import javax.swing.JScrollPane;

import java.awt.Insets;

import javax.swing.JTable;

import choppeidanca.modelo.Pais;
import choppeidanca.modelo.Uf;
import choppeidanca.modelosJtable.Modelo_Uf;
import choppeidanca.persistencia.UfDAO;
import choppeidanca.utils.Singleton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscaEstado extends JDialog {
	private JTable table;

	private ImageIcon imgAlert = new ImageIcon(getClass().getResource(
			"/choppeidanca/imagens/error.png"));
	private JButton btnNovoUf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscaEstado dialog = new BuscaEstado();
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
	Modelo_Uf mu = new Modelo_Uf();
	Pais p = new Pais();
	Uf uf = new Uf();
	
	public BuscaEstado() {
		setTitle("Choppeidan\u00E7a - Busca de UF");

		carregaDados();
		
		s.uf = uf;
		s.uf.setPais(p);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 434, 0 };
		gridBagLayout.rowHeights = new int[] { 229, 33, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		getContentPane().add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(mu);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		GridBagConstraints gbc_buttonPane = new GridBagConstraints();
		gbc_buttonPane.anchor = GridBagConstraints.NORTH;
		gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonPane.gridx = 0;
		gbc_buttonPane.gridy = 1;
		getContentPane().add(buttonPane, gbc_buttonPane);

		btnNovoUf = new JButton("Novo");
		btnNovoUf.setVisible(s.statusBotao);
		btnNovoUf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Runnable r = new Runnable() {

					@Override
					public void run() {

						CadEstado ce = new CadEstado();

						ce.setVisible(true);

						while (ce.isShowing()) {

						}

						carregaDados();

					}
				};
				Thread t = new Thread(r);
				t.start();

			}
		});
		buttonPane.add(btnNovoUf);

		JButton okButton = new JButton("Selecionar");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int linha = table.getSelectedRow();

				if (linha == -1) {

					JOptionPane.showMessageDialog(null,
							"Por Favor, selecione uma linha da lista de UF",
							"Ação Ilegal", 1, imgAlert);

				} else {
					s.uf = getInstance();
					
					BuscaEstado.this.dispose();

				}

			}
		});
		buttonPane.add(okButton);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Uf uf = getInstance();
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
						} else {
							dao.deletar(uf.getId());

							mu.fireTableDataChanged();

							JOptionPane.showMessageDialog(null,
									"Exclusão feita com sucesso");
							
							carregaDados();
						}

					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"Problemas ao excluir estado");
				}

			}
		});
		buttonPane.add(btnExcluir);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscaEstado.this.dispose();
			}
		});
		buttonPane.add(cancelButton);

	}

	protected Uf getInstance() {
		Uf uf = mu.getSelectedObject(table.getSelectedRow());
		return uf;
	}

	private void carregaDados() {
		UfDAO dao = new UfDAO();
		mu.setUfs(dao.listar());
		mu.fireTableDataChanged();
	}

}
