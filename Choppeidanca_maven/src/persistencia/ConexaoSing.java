package choppeidanca.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConexaoSing {

	private static ConexaoSing instancia;
	static Connection con = conectar();
		
	public static synchronized ConexaoSing getInstance() {
		if (instancia == null) {
			instancia = new ConexaoSing();
		}
		return instancia;
	}

	public static Connection conectar() {
		try {

			String url = "jdbc:postgresql://localhost/choppeidanca";
			String usuario = "wssim";
			String senha = "Ws18012001";

			Class.forName("org.postgresql.Driver");

			

			con = DriverManager.getConnection(url, usuario, senha);
			return con;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
