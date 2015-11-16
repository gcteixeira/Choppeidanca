package choppeidanca.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
		private static final String DRIVER = "org.postgres.jdbc.Driver";
	    private static final String URL = "jdbc:postgres://localhost:8400/choppeidanca";
	    private static final String SENHA = "Ws18012001";
	    private static final String USUARIO = "postgres";


	    public static Connection conectar() {
	        try {

	        	String url ="jdbc:postgresql://localhost:8400/choppeidanca"; 
	        	String usuario="postgres"; 
	        	String senha = "Ws18012001"; 

	       
				Class.forName("org.postgresql.Driver");
				

	        	Connection con=null; 

	        	con=DriverManager.getConnection(url,usuario,senha); 

	        	System.out.println("Conex�o realizada com sucesso."); 
	        	
	        	DriverManager.registerDriver(new org.postgresql.Driver());
        	return con;
	        } catch (ClassNotFoundException e) {

					e.printStackTrace(); 
	        } catch (SQLException ex) {
	            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
	        }

	        return null;
	    }
}
