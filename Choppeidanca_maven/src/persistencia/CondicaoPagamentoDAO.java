package choppeidanca.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



import choppeidanca.modelo.CondicaoPagamento;


public class CondicaoPagamentoDAO {
	 
	    PreparedStatement stmt;
	    String sql;
	    ConexaoSing cs = ConexaoSing.getInstance();
	    Connection con = cs.con;
	    
	    //INSERE UMA CONDIÇÃO DE PAGAMENTO NO BANCO
	    public String inserir(CondicaoPagamento condicaopagamaneto) {
	        try {
	            sql = "insert into condicao(nome,quantidadedevezes) values (?,?);";
	            stmt = con.prepareStatement(sql);
	            stmt.setString(1, condicaopagamaneto.getNome());
	            stmt.setInt(2, condicaopagamaneto.getQtdVezes());
	            stmt.executeUpdate();
	            
	            stmt.close();
	            return "condicao de pagamento cadastrada com sucesso";

	        } catch (SQLException ex) {
	            Logger.getLogger(CondicaoPagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } 

	        return "condição de pagamento nao  não cadastrada por algum problema";
	    }

	    //LISTA AS Condições de pagamento DO BANCO
	    public List<CondicaoPagamento> listar() {
	        try {
	         
	            String sql = "select * from condicao";
	            stmt = con.prepareStatement(sql);
	            
	            ResultSet result = stmt.executeQuery();
	            List<CondicaoPagamento> condicaopagamentos = new ArrayList<>();
	            while (result.next()) {
	                CondicaoPagamento condicaopagamento = new CondicaoPagamento();
	                condicaopagamento.setId(result.getInt(1));
	                condicaopagamento.setNome(result.getString(2));
	                condicaopagamento.setQtdVezes(result.getInt(3));

	                condicaopagamentos.add(condicaopagamento);
	            }
	            result.close();
	            stmt.close();
	            return condicaopagamentos;
	        } catch (SQLException ex) {
	            Logger.getLogger(CondicaoPagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return null;
	    }

	    public CondicaoPagamento buscar(Integer id) {
	        try {
	           
	            String sql = "select * from condicao where idcondicao = ?";
	            stmt = con.prepareStatement(sql);

	            stmt.setInt(1, id);
	            ResultSet result = stmt.executeQuery();
	            CondicaoPagamento condicaopagamento = new CondicaoPagamento();
	            while (result.next()) {
	            	condicaopagamento.setId(result.getInt(1));
	            	condicaopagamento.setNome(result.getString(2));
	            	condicaopagamento.setQtdVezes(result.getInt(3));	            	
	            	return condicaopagamento;
	            }

	            result.close();
	            stmt.close();
	            

	        } catch (SQLException ex) {
	            Logger.getLogger(CondicaoPagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return null;
	    }

	    public String deletar(int id) {
	        try {
	         
	            String sql = "delete from condicao where idcondicao=?";
	            stmt = con.prepareStatement(sql);
	            stmt.setInt(1, id);
	            if (stmt.execute()) {
	            	stmt.close();
	                return "Condição de pagamento deletada com sucesso";               
	            } else {
	                return null;
	            }          
	        } catch (SQLException ex) {
	            return "NO";
	        } 
	    }

	    public String alterar(CondicaoPagamento condicaopagamento) {
	        try {
	           
	            String sql ="update condicao set nome=?,quantidadedevezes=? where idcondicao=?";
	            stmt = con.prepareStatement(sql);
	            stmt.setString(1, condicaopagamento.getNome());
	            stmt.setInt(2, condicaopagamento.getQtdVezes());
	            stmt.setInt(3, condicaopagamento.getId());
	            stmt.executeUpdate();
	            
	            stmt.close();
	            return "Condição de Pagamento alterada com sucesso";
	        } catch (SQLException ex) {
	            Logger.getLogger(CondicaoPagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return "ok";
	    }

	    
	     public CondicaoPagamento buscar(String Nome, int QtdVezes) {
	        try {
	          
	            String sql = "select * from condicao where nome=? and uf=?";
	            stmt = con.prepareStatement(sql);

	            stmt.setString(1, Nome);
	            stmt.setInt(2, QtdVezes);
	            ResultSet result = stmt.executeQuery();
	            result.next();
	            CondicaoPagamento condicaopagamento = new CondicaoPagamento();
	            condicaopagamento.setNome(result.getString(1));
	            condicaopagamento.setQtdVezes(result.getInt(2));

	            result.close();
	            stmt.close();
	            return condicaopagamento;

	        } catch (SQLException ex) {
	            Logger.getLogger(CondicaoPagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } 
	        return null;
	    }
}
	