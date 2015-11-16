package choppeidanca.persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import choppeidanca.modelo.CondicaoPagamento;
import choppeidanca.modelo.DataFatura;
import choppeidanca.modelo.FormaPagamento;
import choppeidanca.modelo.Movimento;

public class DataFaturaDAO {

	Connection con = ConexaoSing.con;
	PreparedStatement stmt;
	String sql;

	// INSERE UMA CONTA NO BANCO
	public String inserir(DataFatura df) {
		try {
			sql = "insert into data_fatura (idconta, datafatura) values ((select max(id) from movimento_contas),?);";
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, df.getConta().getId());
			stmt.setDate(2, (Date) df.getData_fatura());
			stmt.executeUpdate();
			stmt.close();
			return "cidade cadastrada com sucesso";

		} catch (SQLException ex) {
			Logger.getLogger(ContasDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return "contas não cadastrada por algum problema";
	}

//	// LISTA AS CONTAS DO BANCO
//	public List<Contas> listar() {
//		try {
//			
//			String sql = "select id, idmovimento, idforma, idcondicao, dataemissao, datafatura, valorparcela, valortotal, tipo, status from movimento_contas";
//			stmt = con.prepareStatement(sql);
//			ResultSet result = stmt.executeQuery();
//			List<Contas> contas = new ArrayList<>();
//			while (result.next()) {
//				Contas c = new Contas();
//				
//				c.setId(result.getInt(1));
//				
//				MovimentoDAO mdao = new MovimentoDAO();
//				Movimento m = mdao.buscar(result.getInt(2));
//				c.setMovimento(m);
//				
//				FormaPagamentoDAO fdao = new FormaPagamentoDAO();
//				FormaPagamento f = fdao.buscar(result.getInt(3));
//				c.setForma(f);
//				
//				CondicaoPagamentoDAO cdao = new CondicaoPagamentoDAO();
//				CondicaoPagamento cp = cdao.buscar(result.getInt(4));
//				c.setCondicao(cp);
//				
//				c.setData_emissao(result.getDate(5));
//				c.setData_fatura(result.getDate(6));
//				c.setValor_parcela(result.getDouble(7));
//				c.setValor_total(result.getDouble(8));
//				c.setTipo(result.getInt(9));
//				c.setStatus(result.getInt(10));
//			
//				contas.add(c);
//			}
//			result.close();
//			stmt.close();
//			return contas;
//		} catch (SQLException ex) {
//			Logger.getLogger(ContasDAO.class.getName()).log(Level.SEVERE, null,
//					ex);
//		}
//		return null;
//	}
//
//	public Contas buscar(Integer id) {
//		try {
//			String sql = "select * from contas where id = ?";
//			stmt = con.prepareStatement(sql);
//
//			stmt.setInt(1, id);
//			ResultSet result = stmt.executeQuery();
//			result.next();
//			Contas contas = new Contas();
//			// contas.setCodigo(result.getInt(1));
//			// contas.setMovimento(result.getString(2));
//			
//			result.close();
//			stmt.close();
//			return contas;
//
//		} catch (SQLException ex) {
//			Logger.getLogger(ContasDAO.class.getName()).log(Level.SEVERE, null,
//					ex);
//		}
//		return null;
//	}
//
//	public String deletar(Integer id) {
//		try {
//			String sql = "delete from contas where id=?";
//			stmt = con.prepareStatement(sql);
//			stmt.setInt(1, id.intValue());
//			if (stmt.execute()) {
//				stmt.close();
//				return "Contas deletada com sucesso";
//			} else {
//				return "problemas com a deleção";
//			}
//		} catch (SQLException ex) {
//			Logger.getLogger(ContasDAO.class.getName()).log(Level.SEVERE, null,
//					ex);
//		} 
//		return null;
//	}
//
//	public String alterar(Contas contas) {
//		try {
//			String sql = "update contas set nome=?,uf=? where id=?";
//			stmt = con.prepareStatement(sql);
//			// stmt.setInt(1, contas.getCodigo());
//			// stmt.setString(2,contas.getMovimento());
//			// stmt.setInt(3,cidade.getId().intValue());
//			stmt.executeUpdate();
//			stmt.close();
//			return "contas alterada com sucesso";
//		} catch (SQLException ex) {
//			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null,
//					ex);
//		} 
//		return "ok";
//	}
//
//
//	public Contas buscar(Integer Codigo, String Movimento) {
//		try {
//			String sql = "select * from contas where nome=? and uf=?";
//			stmt = con.prepareStatement(sql);
//
//			stmt.setInt(1, Codigo);
//			stmt.setString(2, Movimento);
//			ResultSet result = stmt.executeQuery();
//			result.next();
//			Contas contas = new Contas();
//			// contas.setCodigo(result.getInt(1));
//			// contas.setMovimento(result.getString(2));
//			stmt.close();
//			return contas;
//
//		} catch (SQLException ex) {
//			Logger.getLogger(ContasDAO.class.getName()).log(Level.SEVERE, null,
//					ex);
//		} 
//		return null;
//	}

	
}
