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

import choppeidanca.modelo.TabelaPreco;

public class TabelaPrecoDAO {

	Connection con = ConexaoSing.con;
	PreparedStatement stmt;
	String sql;

	public String inserir(TabelaPreco tp) {
		try {
			sql = "insert into tabelapreco (nome,dtvalidade) values (?,?);";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tp.getNome());
			stmt.setDate(2, (Date) tp.getDataValidade());
			stmt.executeUpdate();
			

			stmt.close();
			
			return "Tabela Preco cadastrado com sucesso";

		} catch (SQLException ex) {
			Logger.getLogger(TabelaPrecoDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		} 

		return "Tabela Preco não cadastrado por algum problema";
	}

	public List<TabelaPreco> listar() {
		try {
			String sql = "select idtabelapreco, nome,dtvalidade from tabelapreco";
			stmt = con.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			List<TabelaPreco> tabelas = new ArrayList<>();
			while (result.next()) {
				TabelaPreco tp = new TabelaPreco();
				tp.setId(result.getInt(1));
				tp.setNome(result.getString(2));
				tp.setDataValidade(result.getDate(3));

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
			String sql = "select idtabelapreco, nome,dtvalidade from tabelapreco where idtabelapreco = ?";
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
			String sql = "delete from tabelapreco where idtabelapreco=?;";
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

	public String alterar(TabelaPreco tp) {
		try {
			String sql = "update tabelapreco set nome=?, dtvalidade=? where idtabelapreco=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tp.getNome());
			stmt.setDate(2, (Date) tp.getDataValidade());
			stmt.setInt(3, tp.getId());
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
			String sql = "select idtabelapreco, nome,dtvalidade from tabelapreco where nome = ?";
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
