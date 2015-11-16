package choppeidanca.swing.paineis;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import choppeidanca.swing.dialogs.CadCategoria;
import choppeidanca.swing.dialogs.CadCidade;
import choppeidanca.swing.dialogs.CadCondicao;
import choppeidanca.swing.dialogs.CadEstado;
import choppeidanca.swing.dialogs.CadForma;
import choppeidanca.swing.dialogs.CadPais;
import choppeidanca.swing.dialogs.CadProduto;
import choppeidanca.swing.dialogs.CadTabelaPreco;
import choppeidanca.swing.dialogs.FrmContas;
import choppeidanca.swing.dialogs.ItemTabelaPreco;
import choppeidanca.swing.dialogs.CadPessoaFisica;
import choppeidanca.swing.dialogs.CadPessoaJuridica;
import choppeidanca.swing.dialogs.FrmMovimento;
import choppeidanca.utils.MinhaAplicacao;
import javax.swing.JButton;

public class PainelApp extends JPanel {

/**
	 * Create the panel.
	 */
//	private ImageIcon imgCheck = new ImageIcon(getClass().getResource("/logo.png"));
	
	public PainelApp() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{21, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JMenuBar menuBar = new JMenuBar();
		GridBagConstraints gbc_menuBar = new GridBagConstraints();
		gbc_menuBar.anchor = GridBagConstraints.NORTH;
		gbc_menuBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_menuBar.gridx = 0;
		gbc_menuBar.gridy = 0;
		add(menuBar, gbc_menuBar);
		
		JMenu mnCadastros = new JMenu("Cadastros");
		menuBar.add(mnCadastros);
		
		JMenu mnPessoa = new JMenu("Pessoa");
		mnCadastros.add(mnPessoa);
		
		JMenuItem mntmPessoaFsica = new JMenuItem("Pessoa F\u00EDsica");
		mntmPessoaFsica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadPessoaFisica cpf = new CadPessoaFisica();
				cpf.setVisible(true);
				
			}
		});
		mnPessoa.add(mntmPessoaFsica);
		
		JMenuItem mntmPessoaJurdica = new JMenuItem("Pessoa Jur\u00EDdica");
		mntmPessoaJurdica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadPessoaJuridica cpj = new CadPessoaJuridica();
				cpj.setVisible(true);
			}
		});
		mnPessoa.add(mntmPessoaJurdica);
		
		JMenuItem mntmProduto = new JMenuItem("Produto");
		mntmProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadProduto cp = new CadProduto();
				cp.setVisible(true);
			}
		});
		mnCadastros.add(mntmProduto);
		
		JMenuItem mntmItemTabelaPreo = new JMenuItem("Item Tabela Pre\u00E7o");
		mntmItemTabelaPreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemTabelaPreco itp = new ItemTabelaPreco();
				itp.setVisible(true);
			}
		});
		mnCadastros.add(mntmItemTabelaPreo);
		
		JMenuItem mntmCategoria = new JMenuItem("Categoria");
		mntmCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadCategoria cc = new CadCategoria();
				cc.setVisible(true);
			}
		});
		mnCadastros.add(mntmCategoria);
		
		JMenuItem mntmTabelaPreo = new JMenuItem("Tabela Pre\u00E7o");
		mntmTabelaPreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadTabelaPreco ct = new CadTabelaPreco();
				ct.setVisible(true);
			}
		});
		mnCadastros.add(mntmTabelaPreo);
		
		JMenuItem mntmCondioDePagamento = new JMenuItem("Condi\u00E7\u00E3o de Pagamento");
		mntmCondioDePagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadCondicao cc = new CadCondicao();
				cc.setVisible(true);
			}
		});
		mnCadastros.add(mntmCondioDePagamento);
		
		JMenuItem mntmFormaDePagamento = new JMenuItem("Forma de Pagamento");
		mntmFormaDePagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CadForma cf = new CadForma();
				cf.setVisible(true);
				
			}
		});
		mnCadastros.add(mntmFormaDePagamento);
		
		JMenu mnEndereo = new JMenu("Endere\u00E7o");
		mnCadastros.add(mnEndereo);
		
		JMenuItem mntmCidade = new JMenuItem("Cidade");
		mntmCidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CadCidade cc = new CadCidade();
				cc.setVisible(true);
				
			}
		});
		mnEndereo.add(mntmCidade);
		
		JMenuItem mntmEstado = new JMenuItem("Estado");
		mntmEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CadEstado ce = new CadEstado();
				ce.setVisible(true);
				
			}
		});
		mnEndereo.add(mntmEstado);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Pais");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadPais cp = new CadPais();
				cp.setVisible(true);
				
			}
		});
		mnEndereo.add(mntmNewMenuItem);
		
		JMenu mnMovimento = new JMenu("Movimento");
		menuBar.add(mnMovimento);
		
		JMenuItem mntmNovaCompravenda = new JMenuItem("Nova Compra/Venda");
		mntmNovaCompravenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrmMovimento m = new FrmMovimento();
				m.setVisible(true);
			}
		});
		mnMovimento.add(mntmNovaCompravenda);
		
		JMenu mnContas = new JMenu("Contas");
		menuBar.add(mnContas);
		
		JMenuItem mntmContas = new JMenuItem("Contas");
		mntmContas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				FrmContas c = new FrmContas();
				c.setVisible(true);
				
			}
		});
		mnContas.add(mntmContas);
		
		JMenu mnRelatorios = new JMenu("Relatorios");
		menuBar.add(mnRelatorios);
		
		
		JMenuItem mntmRelatorioCategoria = new JMenuItem("Relatorio de Categoria");
		mnRelatorios.add(mntmRelatorioCategoria);
		
		
		JMenuItem mntmRelatorioDeMunicipio = new JMenuItem("Relatorio de Municipio, Estado e Pais");
		mnRelatorios.add(mntmRelatorioDeMunicipio);
		
		JMenuItem mntmRelatorioDeMovimento = new JMenuItem("Relatorio de Movimento");
		mnRelatorios.add(mntmRelatorioDeMovimento);
		
		JMenuItem mntmRelatorioDePessoa = new JMenuItem("Relatorio de Pessoa");
		mnRelatorios.add(mntmRelatorioDePessoa);
		
		JMenuItem mntmRelatorioDeProduto = new JMenuItem("Relatorio de Produto");
		mnRelatorios.add(mntmRelatorioDeProduto);
		
		JMenuItem mntmRelatorioDeEstoque = new JMenuItem("Relatorio de Estoque");
		mnRelatorios.add(mntmRelatorioDeEstoque);
		
		JMenuItem mntmRelatorioDePessoaJuridica = new JMenuItem("Relatorio de Pessoa Juridica");
		mnRelatorios.add(mntmRelatorioDePessoaJuridica);
		
		JMenuItem mntmRelatorioDePessoaFisica = new JMenuItem("Relatorio de Pessoa Fisica");
		mnRelatorios.add(mntmRelatorioDePessoaFisica);
		
		JMenu mnConfiguraes = new JMenu("Configura\u00E7\u00F5es");
		menuBar.add(mnConfiguraes);
		
		JMenuItem mntmsair = new JMenuItem("Sair");
		mntmsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MinhaAplicacao.get().getTelaPrincipal().showLogin();
			}
		});
		mnConfiguraes.add(mntmsair);

	}
}
