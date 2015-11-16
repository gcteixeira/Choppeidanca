package choppeidanca.utils;

import choppeidanca.swing.frames.Principal;

public class MinhaAplicacao {

	private static MinhaAplicacao self;

	private Principal telaPrincipal = null;

	private MinhaAplicacao() {

	}

	public static synchronized MinhaAplicacao get() {
		if (self == null) {
			self = new MinhaAplicacao();
		}
		return self;
	}

	public Principal getTelaPrincipal() {
		return telaPrincipal;
	}

	public void setTelaPrincipal(Principal telaPrincipal) {
		if (this.telaPrincipal != null) {
			throw new RuntimeException("Não pode mudar a tela principal"
					+ " depois que ela for atribuída.");
		}
		this.telaPrincipal = telaPrincipal;
	}
}
