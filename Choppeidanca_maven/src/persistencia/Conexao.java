package choppeidanca.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
		private static final String DRIVER = "org.postgres.jdbc.Driver";
	    private static final String URL = "jdbc:postgres://localhost/choppeidanca";
	    private static final String SENHA = "wssim";
	    private static final String USUARIO = "Ws18012001";


	    public static Connection conectar() {
	        try {

	        	String url ="jdbc:postgresql://localhost/choppeidanca"; 
	        	String usuario="wssim"; 
	        	String senha = "Ws18012001"; 

	       
				Class.forName("org.postgresql.Driver");
				

	        	Connection con=null; 

	        	con=DriverManager.getConnection(url,usuario,senha); 

	        	//System.out.println("Conexão realizada com sucesso."); 
	        	
//	        	DriverManager.registerDriver(new org.postgresql.Driver());
//	        	Connection con = DriverManager.getConnection("jdbc:postgres://localhost:5432/choppeidanca", 
//	        												 "postgres" , "postgres");
	        	return con;
	        } catch (ClassNotFoundException e) {

					e.printStackTrace(); 
	        } catch (SQLException ex) {
	            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
	        }

	        return null;
	    }
}
