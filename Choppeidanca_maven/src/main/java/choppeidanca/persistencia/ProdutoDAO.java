package choppeidanca.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.text.StyleContext.SmallAttributeSet;

import choppeidanca.modelo.Categoria;
import choppeidanca.modelo.Produto;
import choppeidanca.modelo.TabelaPreco;

public class ProdutoDAO {
	Connection con = ConexaoSing.con;
	PreparedStatement stmt;
	String sql;

	// INSERE UM MOVIMENTO NO BANCO
	public String inserir(Produto produto) {
		try {
			sql = "insert into produto (categoria_idcategoria,produtonome,statusproduto,qtdestoque) values (?,?,?,?);";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, produto.getCategoria().getCodigo());
			stmt.setString(2, produto.getDescricao());
			stmt.setBoolean(3, produto.getStatus());
			stmt.setInt(4, produto.getQtdEstoque());
			stmt.executeUpdate();
			
			stmt.close();
			return "Produto cadastrado com sucesso";

		} catch (SQLException ex) {
			Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		} 
		return "Produto não cadastradao por algum problema";
	}

	// LISTA AS PESSOAS DO BANCO
	public List<Produto> listar() {
		try {

			String sql = "select id,categoria_idcategoria,produtonome,statusproduto,qtdestoque from produto";
			stmt = con.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			List<Produto> produtos = new ArrayList<>();
			Produto produto = new Produto();
			while (result.next()) {
				produto.setCodigo(result.getInt(1));

				CategoriaDAO catdao = new CategoriaDAO();
				Categoria categoria = catdao.buscar(result.getInt(2));
				produto.setCategoria(categoria);

				produto.setDescricao(result.getString(3));
				produto.setStatus(result.getBoolean(4));
				produto.setQtdEstoque(result.getInt(5));
				produtos.add(produto);
			}
			result.close();
			stmt.close();
			return produtos;
		} catch (SQLException ex) {
			Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		} 
		return null;
	}

	public Produto buscar(Integer id) {
		try {

			String sql = "select   id,categoria_idcategoria,produtonome,statusproduto,qtdestoque  from produto where id = ?";
			stmt = con.prepareStatement(sql);

			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			Produto produto = new Produto();
			while (result.next()) {
				produto.setCodigo(new Integer(result.getInt(1)));

				CategoriaDAO catdao = new CategoriaDAO();
				Categoria categoria = catdao.buscar(result.getInt(2));
				produto.setCategoria(categoria);

				produto.setDescricao(new String(result.getString(3)));
				produto.setStatus(new Boolean(result.getBoolean(4)));
				produto.setQtdEstoque(result.getInt(5));
			}
			result.close();
			stmt.close();
			return produto;

		} catch (SQLException ex) {
			Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		} 
		return null;
	}

	public String deletar(Integer id) {
		try {

			String sql = "delete from produto where id=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id.intValue());
			if (stmt.execute()) {
				stmt.close();
				return "Produto deletado com sucesso";
			} else {
				return null;
			}
		} catch (SQLException ex) {
			return "NO";
		}
	}

	public String alterar(Produto produto) {
		try {

			sql = "update produto set categoria_idcategoria=?,produtonome=?,statusproduto=?,qtdestoque=? where id=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, produto.getCategoria().getCodigo());
			stmt.setString(2, produto.getDescricao());
			stmt.setBoolean(3, produto.getStatus());
			stmt.setInt(4, produto.getQtdEstoque());
			stmt.setInt(5, produto.getCodigo());
			stmt.executeUpdate();
			
			stmt.close();
			return "Pessoa alterada com sucesso";
		} catch (SQLException ex) {
			Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		} 
		return "ok";
	}

	public Produto buscar(String nome) {
		try {

			String sql = "select  id,categoria_idcategoria,produtonome,statusproduto,qtdestoque  from produto where produtonome = ?";
			stmt = con.prepareStatement(sql);

			stmt.setString(1, nome);
			ResultSet result = stmt.executeQuery();
			Produto produto = new Produto();
			while (result.next()) {
				produto.setCodigo(new Integer(result.getInt(1)));

				CategoriaDAO catdao = new CategoriaDAO();
				Categoria categoria = catdao.buscar(result.getInt(2));
				produto.setCategoria(categoria);

				produto.setDescricao(new String(result.getString(3)));
				produto.setStatus(new Boolean(result.getBoolean(4)));
				produto.setQtdEstoque(result.getInt(5));
			}
			result.close();
			stmt.close();
			return produto;

		} catch (SQLException ex) {
			Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return null;
	}

}


