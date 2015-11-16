package choppeidanca.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import choppeidanca.modelo.Cidade;
import choppeidanca.modelo.Uf;

public class CidadeDAO {
	Connection con = ConexaoSing.con;
	PreparedStatement stmt;
	String sql;

	// INSERE UMA CIDADE NO BANCO
	public String inserir(Cidade cidade) {
		try {
			sql = "insert into cidade (nome,estado_idestado) values (?,?);";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, cidade.getNome());

			// Linkando um Uf com uma cidade
			stmt.setInt(2, cidade.getUf().getId());

			stmt.executeUpdate();
			stmt.close();
			return "cidade cadastrada com sucesso";

		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		}

		return "cidade não cadastrada por algum problema";
	}

	// LISTA AS CIDADES DO BANCO
	public List<Cidade> listar() {
		try {
			String sql = "select idcidade,estado_idestado,nome from cidade";
			stmt = con.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			List<Cidade> cidades = new ArrayList<>();
			while (result.next()) {
				Cidade cidade = new Cidade();
				cidade.setId(result.getInt(1));

				// Buscando o UF pela chave
				UfDAO dao = new UfDAO();
				Uf uf = dao.buscar(result.getInt(2));
				cidade.setUf(uf);

				cidade.setNome(result.getString(3));

				cidades.add(cidade);
			}
			result.close();
			stmt.close();
			return cidades;
		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return null;
	}

	public Cidade buscar(Integer id) {
		try {
			String sql = "select idcidade,estado_idestado,nome from cidade where idcidade = ?";
			stmt = con.prepareStatement(sql);

			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			Cidade cidade = new Cidade();
			while (result.next()) {
				cidade.setId(result.getInt(1));
				UfDAO dao = new UfDAO();
				Uf uf = dao.buscar(new Integer(result.getInt(2)));
				cidade.setUf(uf);
				cidade.setNome(result.getString(3));
			}
			result.close();
			stmt.close();
			return cidade;

		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		} 
		return null;
	}
	public String deletar(Integer id) {
		try {
			con = Conexao.conectar();
			String sql = "delete from cidade where idcidade=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id.intValue());
			if (stmt.execute()) {
				return "Cidade deletada com sucesso";
			} else {
				return null;
			}
		} catch (SQLException ex) {
			return "NO";
		} 	
	}
	public String alterar(Cidade cidade) {
		try {
			
			String sql = "update cidade set nome=?,estado_idestado=? where idcidade=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, cidade.getNome());
			stmt.setInt(2, cidade.getUf().getId());
			stmt.setInt(3, cidade.getId());
			stmt.executeUpdate();
	
			stmt.close();
			return "cidade alterada com sucesso";
		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return "ok";
	}

	public Cidade buscar(String nome) {
		try {
			String sql = "select idcidade,estado_idestado,nome from cidade where nome=?";
			stmt = con.prepareStatement(sql);

			stmt.setString(1, nome);

			ResultSet result = stmt.executeQuery();
			Cidade cidade = new Cidade();
			while (result.next()) {
				cidade.setId(result.getInt(1));
				cidade.setNome(result.getString(2));

				UfDAO dao = new UfDAO();
				Uf uf2 = dao.buscar(new Integer(result.getInt(3)));
				cidade.setUf(uf2);
			}
			result.close();
			stmt.close();
			return cidade;

		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return null;
	}
}
