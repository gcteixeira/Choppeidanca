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

import choppeidanca.utils.Singleton;
import choppeidanca.modelo.Pais;
import choppeidanca.modelosJtable.Modelo_Pais;
import choppeidanca.persistencia.PaisDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscaPais extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private ImageIcon imgAlert = new ImageIcon(getClass().getResource(
			"/choppeidanca/imagens/error.png"));
	private JButton btnNovoPais;
	public boolean isVisible;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscaPais dialog = new BuscaPais();
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
	Modelo_Pais mp = new Modelo_Pais();
	Pais p = new Pais();
	public BuscaPais() {
		setTitle("Choppeidan\u00E7a - Busca de Pais");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		carregaDados();

		s.pais = p;
		
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
		table.setModel(mp);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Selecionar");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int linha = table.getSelectedRow();

				if (linha == -1) {

					JOptionPane
							.showMessageDialog(
									null,
									"Por Favor, selecione uma linha da lista de Paises",
									"Ação Ilegal", 1, imgAlert);

				} else {
					s.pais = getInstance();

					BuscaPais.this.dispose();

				}
			}
		});

		btnNovoPais = new JButton("Novo");
		btnNovoPais.setEnabled(s.statusBotao);
		btnNovoPais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				Runnable r = new Runnable() {

					@Override
					public void run() {

						CadPais cp = new CadPais();

						System.out.println("chamou");
						cp.setVisible(true);

						while (cp.isShowing()) {

						}

						carregaDados();

					}
				};
				Thread t = new Thread(r);
				t.start();

			}
		});
		buttonPane.add(btnNovoPais);
		buttonPane.add(okButton);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Pais pais = getInstance();
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int result = JOptionPane.showConfirmDialog(null,
							"Deseja excluir o pais " + pais.getNome() + "?",
							"Deseja realizar essa operação?", dialogButton);

					if (result == JOptionPane.YES_OPTION) {

						PaisDAO dao = new PaisDAO();
						if (dao.deletar(pais.getId()) == "NO") {
							JOptionPane
									.showMessageDialog(
											null,
											"Esse País tem cidades e estados vinculados a ele, não é possivel excluir esse país",
											"Problemas ao Excluir", 1, imgAlert);
						} else {

							carregaDados();

							JOptionPane.showMessageDialog(null,
									"Exclusão feita com sucesso");

						}

					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"Problemas ao excluir pais");
				}

			}
		});
		buttonPane.add(btnExcluir);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BuscaPais.this.dispose();
			}
		});
		buttonPane.add(cancelButton);

	}

	protected Pais getInstance() {
		Pais p = mp.getSelectedObject(table.getSelectedRow());
		return p;
	}

	private void carregaDados() {
		PaisDAO dao = new PaisDAO();
		mp.setPaises(dao.listar());
		mp.fireTableDataChanged();
	}

}
