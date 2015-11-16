package choppeidanca.swing.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import choppeidanca.swing.paineis.PainelApp;
import choppeidanca.swing.paineis.PainelLogin;
import choppeidanca.utils.MinhaAplicacao;

import java.awt.Frame;
import java.awt.Dimension;

public class Principal extends JFrame {

	private JPanel contentPane;
]
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					MinhaAplicacao.get().setTelaPrincipal(frame);
					frame.setVisible(true);
					frame.showLogin();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Choppeidan\u00E7a v1.1 ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 501);
	}

	public void showLogin() {
		contentPane = new PainelLogin();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		revalidate();
	}

	public void showPrincipal() {
		contentPane =  new PainelApp();
		setContentPane(contentPane);
		revalidate();
	}

}
