package choppeidanca.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import choppeidanca.modelo.Pais;

public class PaisDAO {
	Connection con = ConexaoSing.con;
	PreparedStatement stmt;
	String sql;

	// INSERE UM Uf NO BANCO
	public String inserir(Pais pais) {
		try {
			sql = "insert into pais (nome) values (?);";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, pais.getNome());
			stmt.executeUpdate();
			
			stmt.close();
			return "Pais cadastrado com sucesso";

		} catch (SQLException ex) {
			Logger.getLogger(PaisDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		} 

		return "Pais não cadastrado por algum problema";
	}

	// LISTA AS PAIS DO BANCO
	public List<Pais> listar() {
		try {
			String sql = "select idpais,nome from pais";
			stmt = con.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			List<Pais> paises = new ArrayList<>();
			while (result.next()) {
				Pais pais = new Pais();
				pais.setId(new Integer(result.getInt(1)));
				pais.setNome(result.getString(2));
				paises.add(pais);
			}
			result.close();
			stmt.close();
			return paises;
		} catch (SQLException ex) {
			Logger.getLogger(PaisDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return null;
	}

	public Pais buscar(Integer id) {
		try {

			String sql = "select idpais,nome from pais where idpais = ?";
			stmt = con.prepareStatement(sql);

			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			result.next();
			Pais pais = new Pais();
			pais.setId(result.getInt(1));
			pais.setNome(result.getString(2));
			
			result.close();
			stmt.close();
			return pais;

		} catch (SQLException ex) {
			Logger.getLogger(PaisDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		} 
		return null;
	}
	public String deletar(Integer id) {
		try {
			con = Conexao.conectar();

			String sql = "delete from pais where idpais=?";

			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);

			if (stmt.execute()) {
				return "Pais deletadao com sucesso";
			} else {
				return null;
			}
		} catch (SQLException ex) {
			return "NO";
		}
		
	}

	public String alterar(Pais pais) {
		try {

			String sql = "update pais set nome=? where idpais=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, pais.getNome());
			stmt.setInt(2, pais.getId());
			stmt.executeUpdate();
			
			stmt.close();
			return "pais alterado com sucesso";
		} catch (SQLException ex) {
			Logger.getLogger(PaisDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return "ok";
	}

	public Pais buscar(String nome) {
		try {

			String sql = "select idpais,nome from pais where nome=?";
			stmt = con.prepareStatement(sql);

			stmt.setString(1, nome);
			ResultSet result = stmt.executeQuery();
			Pais pais = new Pais();
			while (result.next()) {
				pais.setId(result.getInt(1));
				pais.setNome(result.getString(2));
			}
			result.close();
			stmt.close();
			return pais;

		} catch (SQLException ex) {
			Logger.getLogger(PaisDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return null;
	}
}
