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
import choppeidanca.modelo.Uf;

public class UfDAO {
	Connection con = ConexaoSing.con;
	PreparedStatement stmt;
	String sql;


	// INSERE UM Uf NO BANCO
	public String inserir(Uf uf) {
		try {
			sql = "insert into estado (nome,sigla,pais_idpais) values (?,?,?);";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, uf.getNome());
			stmt.setString(2, uf.getSigla());
			stmt.setInt(3, uf.getPais().getId());
			stmt.executeUpdate();
			
			stmt.close();
			return "UF cadastrado com sucesso";

		} catch (SQLException ex) {
			Logger.getLogger(UfDAO.class.getName()).log(Level.SEVERE, null, ex);
		} 

		return "Uf não cadastrado por algum problema";
	}

	// LISTA AS Uf DO BANCO
	public List<Uf> listar() {
		try {
			String sql = "select idestado ,pais_idpais ,nome ,sigla  from estado";
			stmt = con.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			List<Uf> ufs = new ArrayList<>();
			while (result.next()) {
				Uf uf = new Uf();
				uf.setId(new Integer(result.getInt(1)));

				PaisDAO dao = new PaisDAO();
				Pais pais = dao.buscar(result.getInt(2));
				uf.setPais(pais);

				uf.setNome(result.getString(3));
				uf.setSigla(result.getString(4));

				ufs.add(uf);
			}
			
			result.close();
			stmt.close();
			return ufs;
		} catch (SQLException ex) {
			Logger.getLogger(UfDAO.class.getName()).log(Level.SEVERE, null, ex);
		} 
		return null;
	}

	public Uf buscar(Integer id) {
		try {
			String sql = "select idestado ,pais_idpais ,nome ,sigla  from estado where idestado = ?";
			stmt = con.prepareStatement(sql);

			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			result.next();
			Uf uf = new Uf();
			uf.setId(result.getInt(1));

			PaisDAO dao = new PaisDAO();
			Pais pais = dao.buscar(result.getInt(2));
			uf.setPais(pais);

			uf.setNome(result.getString(3));
			uf.setSigla(result.getString(4));
			
			result.close();
			stmt.close();
			return uf;

		} catch (SQLException ex) {
			Logger.getLogger(UfDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public String deletar(Integer id) {
		try {
			con = Conexao.conectar();
			String sql = "delete from estado where idestado=?;";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			if (stmt.execute()) {
				return "Uf deletadao com sucesso";
			} else {
				return null;
			}
		} catch (SQLException ex) {
			return "NO";
		}
	}

	public String alterar(Uf uf) {
		try {
			String sql = "update estado set nome=?, pais_idpais=?, sigla=? where idestado=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, uf.getNome());
			stmt.setInt(2, uf.getPais().getId());
			stmt.setString(3, uf.getSigla());
			stmt.setInt(4, uf.getId());
			stmt.executeUpdate();
			
			stmt.close();
			return "Uf alterado com sucesso";
		} catch (SQLException ex) {
			Logger.getLogger(UfDAO.class.getName()).log(Level.SEVERE, null, ex);
		} 
		return "ok";
	}


	public Uf buscar(String nome) {
		try {
			String sql = "select idestado ,pais_idpais ,nome ,sigla  from estado where nome = ?";
			stmt = con.prepareStatement(sql);

			stmt.setString(1, nome);
			ResultSet result = stmt.executeQuery();
			Uf uf = new Uf();
			while (result.next()) {
				uf.setId(result.getInt(1));

				PaisDAO dao = new PaisDAO();
				Pais pais = dao.buscar(result.getInt(2));
				uf.setPais(pais);

				uf.setNome(result.getString(3));
				uf.setSigla(result.getString(4));


			}
			result.close();
			stmt.close();
			return uf;

		} catch (SQLException ex) {
			Logger.getLogger(UfDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
