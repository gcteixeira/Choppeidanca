package choppeidanca.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import choppeidanca.modelo.FormaPagamento;



public class FormaPagamentoDAO {
	  	Connection con = ConexaoSing.getInstance().con;
	    PreparedStatement stmt;
	    String sql;

	    //INSERE UMA FORMA DE PAGAMENTO NO BANCO
	    public String inserir(FormaPagamento formapagamaneto) {
	        try {  
	            sql = "insert into forma(nome) values (?);";
	            stmt = con.prepareStatement(sql);
	            stmt.setString(1, formapagamaneto.getTipo());
	            stmt.executeUpdate();
	            
	            stmt.close();
	            
	            return "forma de pagamento cadastrada com sucesso";

	        } catch (SQLException ex) {
	            Logger.getLogger(FormaPagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } 

	        return "Forma  de pagamento nao  não cadastrada por algum problema";
	    }

	    //LISTA AS Forma de pagamento DO BANCO
	    public List<FormaPagamento> listar() {
	        try {
	            String sql = "select idforma, nome from forma";
	            stmt = con.prepareStatement(sql);
	            ResultSet result = stmt.executeQuery();
	            List<FormaPagamento> formapagamentos = new ArrayList<>();
	            while (result.next()) {
	                FormaPagamento formapagamento = new FormaPagamento();
	                formapagamento.setCodigo(result.getInt(1));
	                formapagamento.setTipo(result.getString(2));

	                formapagamentos.add(formapagamento);
	            }
	            result.close();
	            stmt.close();
	            return formapagamentos;
	        } catch (SQLException ex) {
	            Logger.getLogger(FormaPagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return null;
	    }

	    public FormaPagamento buscar(Integer id, String nome) {
	        try {
	            String sql = "select idforma, nome from forma where idforma = ?";
	            stmt = con.prepareStatement(sql);

	            stmt.setInt(1, id);
	            stmt.setString(2, nome);
	            ResultSet result = stmt.executeQuery();
	            result.next();
	            FormaPagamento formapagamento = new FormaPagamento();
	            formapagamento.setCodigo(result.getInt(1));
	            formapagamento.setTipo(result.getString(2));

	            result.close();
	            stmt.close();
	            return formapagamento;

	        } catch (SQLException ex) {
	            Logger.getLogger(FormaPagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } 
	        return null;
	    }

	    public String deletar(Integer id) {
	        try {
	            String sql = "delete from forma where idforma=?";
	            stmt = con.prepareStatement(sql);
	            stmt.setInt(1, id);
	            if (stmt.execute()) {
	            	stmt.close();
	                return "Forma de pagamento deletada com sucesso";
	            } else {
	                return null;
	            }
	        } catch (SQLException ex) {
	            return "NO";
	        } 
	    }

	    public String alterar(FormaPagamento formapagamento) {
	    	  try {
		            String sql ="update forma set nome=? where idforma=?";
		            stmt = con.prepareStatement(sql);
		            stmt.setString(1, formapagamento.getTipo());
		            stmt.setInt(2, formapagamento.getCodigo());
		            stmt.executeUpdate();
		            
		            stmt.close();
		            return "forma de pagamento alterado com sucesso";
		        } catch (SQLException ex) {
	            Logger.getLogger(FormaPagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return "ok";
	    }
	    
	     public FormaPagamento buscar(int id) {
	        try {
	            String sql = "select idforma, nome from forma where idforma=?";
	            stmt = con.prepareStatement(sql);

	            stmt.setInt(1, id);
	            ResultSet result = stmt.executeQuery();
	            FormaPagamento formapagamento = new FormaPagamento();
	            while( result.next()) {
	            	formapagamento.setCodigo(result.getInt(1));
	            	formapagamento.setTipo(result.getString(2));	            	
	            }

	            result.close();
	            stmt.close();
	            return formapagamento;

	        } catch (SQLException ex) {
	            Logger.getLogger(FormaPagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } 
	        return null;
	    }
}
