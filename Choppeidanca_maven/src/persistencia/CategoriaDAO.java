package choppeidanca.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import choppeidanca.modelo.Categoria;

public class CategoriaDAO {
	Connection con = ConexaoSing.con;
	PreparedStatement stmt;
	String sql;

	public String inserir(Categoria catgoria) {
		try {
			sql = "insert into categoria (nome,statuscategoria) values (?,?);";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, catgoria.getTipo());
			stmt.setInt(2, catgoria.getStatus());
			stmt.executeUpdate();
			stmt.close();
			return "cidade cadastrada com sucesso";

		} catch (SQLException ex) {
			Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return "categoria não cadastrada por algum problema";
	}

	public List<Categoria> listar() {
		try {
			
			String sql = "select idcategoria,nome,statuscategoria from categoria";
			stmt = con.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			List<Categoria> categorias = new ArrayList<>();
			while (result.next()) {
				Categoria categoria = new Categoria();
				categoria.setCodigo(new Integer(result.getInt(1)));
				categoria.setTipo(result.getString(2));
				categoria.setStatus(new Integer(result.getInt(3)));

				categorias.add(categoria);
			}
			result.close();
			stmt.close();

			return categorias;
		} catch (SQLException ex) {
			Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return null;
	}

	public Categoria buscar(Integer id) {
		try {

			String sql = "select idcategoria,nome,statuscategoria  from categoria where idcategoria = ?";
			stmt = con.prepareStatement(sql);

			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			Categoria categoria = new Categoria();
			while (result.next()) {
				categoria.setCodigo(result.getInt(1));
				categoria.setTipo(result.getString(2));
				categoria.setStatus(result.getInt(3));
			}
			result.close();
			stmt.close();

			return categoria;
		} catch (SQLException ex) {
			Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return null;
	}


	public String deletar(Integer id) {
		try {
			con = Conexao.conectar();
			String sql = "delete from categoria where idcategoria=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id.intValue());
			if (stmt.execute()) {
				return "Categoria deletada com sucesso";
			} else {
				return null;
			}
		} catch (SQLException ex) {
			return "NO";
		}
	
	}

	public String alterar(Categoria categoria) {
		try {

			String sql = "update categoria set nome=?, statuscategoria=? where idcategoria=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, categoria.getTipo());
			stmt.setInt(2, (categoria.getStatus()));
			stmt.setInt(3, categoria.getCodigo());
			stmt.executeUpdate();

			stmt.close();
			return "categoria alterada com sucesso";
		} catch (SQLException ex) {
			Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return "ok";
	}

	public Categoria buscar(String nome) {
		try {
			
			String sql = "select idcategoria,nome,statuscategoria  from categoria where nome=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, nome);
			ResultSet result = stmt.executeQuery();
			Categoria categoria = new Categoria();
			while (result.next()) {
				categoria.setCodigo(result.getInt(1));
				categoria.setTipo(result.getString(2));
				categoria.setStatus(result.getInt(3));

			}
			result.close();
			stmt.close();
			return categoria;

		} catch (SQLException ex) {
			Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		} 
		return null;
	}
}
