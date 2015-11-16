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

import choppeidanca.modelo.Categoria;
import choppeidanca.modelo.Produto;
import choppeidanca.modelo.TabelaPreco;
import choppeidanca.modelo.TabelaPrecoDetalhe;

public class TabelaPDetalheDAO {

	Connection con = ConexaoSing.con;
	PreparedStatement stmt;
	String sql;

	public String inserir(TabelaPrecoDetalhe tp) {
		try {
			sql = "insert into produto_has_tabelapreco (produto_id, tabelapreco_idtabelapreco, valor) values (?,?,?);";
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, tp.getProduto().getCodigo());
			stmt.setInt(2, tp.getTabelaPreco().getId());
			stmt.setDouble(3, tp.getValor());
			stmt.executeUpdate();
			
			stmt.close();
			return "Tabela Preco cadastrado com sucesso";

		} catch (SQLException ex) {
			Logger.getLogger(TabelaPrecoDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		return "Tabela Preco não cadastrado por algum problema";
	}

	public List<TabelaPrecoDetalhe> listar() {
		try {
			String sql = "select id, produto_id, tabelapreco_idtabelapreco, valor from produto_has_tabelapreco";
			stmt = con.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			List<TabelaPrecoDetalhe> tabelas = new ArrayList<>();
			while (result.next()) {
				TabelaPrecoDetalhe tp = new TabelaPrecoDetalhe();
				tp.setId(result.getInt(1));
				
				ProdutoDAO dao = new ProdutoDAO();
				Produto p = dao.buscar(result.getInt(2));
				tp.setProduto(p);
				
				TabelaPrecoDAO dao2 = new TabelaPrecoDAO();
				TabelaPreco tp2 = dao2.buscar(result.getInt(3));
				tp.setTabelaPreco(tp2);
				
				tp.setValor(result.getDouble(4));
			
				tabelas.add(tp);
			}
			result.close();
			stmt.close();
			return tabelas;
		} catch (SQLException ex) {
			Logger.getLogger(TabelaPrecoDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return null;
	}

	public TabelaPreco buscar(int id) {
		try {
			String sql = "select id, produto_id, tabelapreco_idtabelapreco, valor from produto_has_tabelapreco where id = ?";
			stmt = con.prepareStatement(sql);

			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			TabelaPreco tp = new TabelaPreco();
			while (result.next()) {
				tp.setId(result.getInt(1));
				tp.setNome(result.getString(2));
				tp.setDataValidade(result.getDate(3));
			}
			result.close();
			stmt.close();
			return tp;

		} catch (SQLException ex) {
			Logger.getLogger(TabelaPrecoDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return null;
	}

	public String deletar(int id) {
		try {
			String sql = "delete from produto_has_tabelapreco where id=?;";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);

			if (stmt.execute()) {
				stmt.close();
				return "tabela preco deletada com sucesso";
			} else {
				return "problemas com a deleção";
			}
		} catch (SQLException ex) {
			return "NO";
		}
	}

	public String alterar(TabelaPrecoDetalhe tp) {
		try {
			String sql = "update produto_has_tabelapreco set produto_id=?, tabelapreco_idtabelapreco=?, valor=? where id=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, tp.getProduto().getCodigo());
			stmt.setInt(2, tp.getTabelaPreco().getId());
			stmt.setDouble(3, tp.getValor());
			stmt.setInt(4, tp.getId());
			stmt.executeUpdate();
			stmt.close();
			return "tabela preco alterado com sucesso";
		} catch (SQLException ex) {
			Logger.getLogger(TabelaPreco.class.getName()).log(Level.SEVERE,
					null, ex);
		} 
		return "ok";
	}
	
	public TabelaPreco buscar(String nome) {
		try {
			String sql = "select select id, produto_id, tabelapreco_idtabelapreco, valor from produto_has_tabelapreco from tabelapreco where nome = ?";
			stmt = con.prepareStatement(sql);

			stmt.setString(1, nome);
			ResultSet result = stmt.executeQuery();
			TabelaPreco tp = new TabelaPreco();
			while (result.next()) {
				tp.setId(result.getInt(1));
				tp.setNome(result.getString(2));
				tp.setDataValidade(result.getDate(3));
			}
			result.close();
			stmt.close();
			return tp;

		} catch (SQLException ex) {
			Logger.getLogger(TabelaPrecoDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return null;
	}

	
	
	
	
}
