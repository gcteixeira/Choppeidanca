package choppeidanca.swing.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import choppeidanca.modelo.Categoria;
import choppeidanca.modelosJtable.Modelo_Categoria;
import choppeidanca.persistencia.CategoriaDAO;
import choppeidanca.utils.Singleton;

import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;

import javax.swing.JTable;

public class BuscaCategoria extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscaCategoria dialog = new BuscaCategoria();
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
	Modelo_Categoria mc = new Modelo_Categoria();

	private JTable table;
	private JButton btnNovo;
	private ImageIcon imgAlert = new ImageIcon(getClass().getResource(
			"/choppeidanca/imagens/error.png"));

	Categoria c = new Categoria();
	
	public BuscaCategoria() {
		setTitle("Choppeidan\u00E7a - Busca de Categoria");

		s.cat = c;
		
		carregaDados();

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

		btnNovo = new JButton("Nova Categoria");
		btnNovo.setVisible(s.statusBotao);
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Runnable r = new Runnable() {

					@Override
					public void run() {
						CadCategoria cc = new CadCategoria();
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
									"Por Favor, selecione uma linha da lista de Categorias",
									"Ação Ilegal", 1, imgAlert);

				} else {
					s.cat = getInstance();
					if (mc.getStringStatus(s.cat.getStatus()).equals("Ativo")) {
						BuscaCategoria.this.dispose();						
					} else if (mc.getStringStatus(s.cat.getStatus()).equals("Inativo")) {
						JOptionPane.showMessageDialog(null,
								"Essa categoria está inativa.");						
					}
					

				}
			}
		});
		buttonPane.add(okButton);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Categoria c = getInstance();
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int result = JOptionPane.showConfirmDialog(null,
							"Deseja excluir a categoria " + c.getTipo() + "?",
							"Deseja realizar essa operação?", dialogButton);

					if (result == JOptionPane.YES_OPTION) {

						CategoriaDAO dao = new CategoriaDAO();
						if (dao.deletar(c.getCodigo()) == "NO") {
							JOptionPane.showMessageDialog(null,
									"Não foi possivel excluir essa categoria",
									"Problemas ao Excluir", 1, imgAlert);
						} else {
							carregaDados();
							JOptionPane.showMessageDialog(null,
									"Exclusão feita com sucesso");
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
				BuscaCategoria.this.dispose();
			}
		});
		buttonPane.add(cancelButton);

	}

	protected Categoria getInstance() {
		Categoria c = mc.getSelectedObject(table.getSelectedRow());
		return c;
	}

	private void carregaDados() {
		CategoriaDAO dao = new CategoriaDAO();
		mc.setCategorias(dao.listar());
		mc.fireTableDataChanged();
	}

}
