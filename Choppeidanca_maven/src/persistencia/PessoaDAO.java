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

import choppeidanca.modelo.Cidade;
import choppeidanca.modelo.Pessoa;

public class PessoaDAO {
	
	Connection con = ConexaoSing.con;
	PreparedStatement stmt;
	String sql;

	// INSERE UMA PESSOA NO BANCO
	public String inserir(Pessoa pessoa) {
		try {
			sql = "insert into pessoa (nome, apelido, tipo, endereco, numero, bairro, cidade_idcidade, telefone, datanasc, cpfcnpj, rginscricao, complemento, contato) values (?,?,?,?,?,?,?,?,?,?,?,?,?);";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getApelido());
			stmt.setInt(3, pessoa.getTipo());
			stmt.setString(4, pessoa.getEndereco());
			stmt.setString(5, pessoa.getNumero());
			stmt.setString(6, pessoa.getBairro());
			stmt.setInt(7, pessoa.getCidade().getId());
			stmt.setString(8, pessoa.getTelefone());
			stmt.setDate(9, (Date) pessoa.getDataNascimento());
			stmt.setString(10, pessoa.getCpfcnpj());
			stmt.setString(11, pessoa.getRginscricao());
			stmt.setString(12, pessoa.getComplemento());
			stmt.setString(13, pessoa.getContato());
			stmt.executeUpdate();
			
			stmt.close();
			return "Pessoa cadastrada com sucesso";

		} catch (SQLException ex) {
			return "ERRO";
		}

		
	}

	// LISTA AS PESSOAS DO BANCO
	public List<Pessoa> listar() {
		try {
			String sql = "select idpessoa, nome, apelido, tipo, endereco, numero, bairro, cidade_idcidade, telefone, contato, datanasc, cpfcnpj, rginscricao, complemento from pessoa";
			stmt = con.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			List<Pessoa> pessoas = new ArrayList<>();
			while (result.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setId(result.getInt(1));
				pessoa.setNome(result.getString(2));
				pessoa.setApelido(result.getString(3));
				pessoa.setTipo(result.getInt(4));
				pessoa.setEndereco(result.getString(5));
				pessoa.setNumero(result.getString(6));
				pessoa.setBairro(result.getString(7));

				CidadeDAO dao = new CidadeDAO();
				Cidade cidade = dao.buscar(result.getInt(8));
				pessoa.setCidade(cidade);

				pessoa.setTelefone(result.getString(9));
				pessoa.setContato(result.getString(10));
				pessoa.setDataNascimento(result.getDate(11));
				pessoa.setCpfcnpj(result.getString(12));
				pessoa.setRginscricao(result.getString(13));
				pessoa.setComplemento(result.getString(14));

				pessoas.add(pessoa);
			}
			result.close();
			stmt.close();
			return pessoas;
		} catch (SQLException ex) {
			Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		} 
		return null;
	}

	public Pessoa buscar(Integer id) {
		try {
			
			String sql = "select * from pessoa where idpessoa = ?";
			stmt = con.prepareStatement(sql);

			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			Pessoa pessoa = new Pessoa();
			while (result.next()) {
				pessoa.setId(result.getInt(1));
				pessoa.setNome(result.getString(2));
				pessoa.setApelido(result.getString(3));
				pessoa.setTipo(result.getInt(4));
				pessoa.setEndereco(result.getString(5));
				pessoa.setNumero(result.getString(6));
				pessoa.setBairro(result.getString(7));

				CidadeDAO dao = new CidadeDAO();
				Cidade cidade = dao.buscar(result.getInt(8));
				pessoa.setCidade(cidade);

				pessoa.setTelefone(result.getString(9));
				pessoa.setContato(result.getString(10));
				pessoa.setDataNascimento(result.getDate(11));
				pessoa.setCpfcnpj(result.getString(12));
				pessoa.setRginscricao(result.getString(13));
				pessoa.setComplemento(result.getString(14));

				
			}
			
			result.close();
			stmt.close();
			
			return pessoa;
			
		} catch (SQLException ex) {
			Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return null;
	}

	public String deletar(Integer id) {
		try {
			
			String sql = "delete from pessoa where idpessoa=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id.intValue());
			if (stmt.execute()) {
				stmt.close();
				return "Pessoa deletada com sucesso";
			} else {
				return null;
			}
		} catch (SQLException ex) {
			return "NO";
		}
	}

	public String alterar(Pessoa pessoa) {
		try {
			con = Conexao.conectar();
			String sql = "update pessoa set nome=?, apelido=?, datanasc=?, rginscricao=?, cpfcnpj=?, endereco=?, numero=?, bairro=?, cidade_idcidade=?, telefone=?, complemento=?, tipo=?, contato=? where idpessoa=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getApelido());
			stmt.setDate(3, (Date) pessoa.getDataNascimento());
			stmt.setString(4, pessoa.getRginscricao());
			stmt.setString(5, pessoa.getCpfcnpj());
			stmt.setString(6, pessoa.getEndereco());
			stmt.setString(7, pessoa.getNumero());
			stmt.setString(8, pessoa.getBairro());
			stmt.setInt(9, pessoa.getCidade().getId());
			stmt.setString(10, pessoa.getTelefone());
			stmt.setString(11, pessoa.getContato());
			stmt.setInt(12, pessoa.getTipo());
			stmt.setString(13, pessoa.getContato());
			stmt.setInt(14, pessoa.getId());
			stmt.executeUpdate();
			
			stmt.close();
			return "Pessoa alterada com sucesso";
		} catch (SQLException ex) {
			Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		} 
		return "ok";
	}


	public Pessoa buscar(Integer id, String nome, Byte tipo, String apelido,
			String rua, String numero, String bairro, String cidade,
			String telefone, String contato) {
		try {
			String sql = "select * from movimento where nome=? and uf=?";
			stmt = con.prepareStatement(sql);

			stmt.setInt(1, id);
			stmt.setString(2, nome);
			stmt.setByte(3, tipo);
			stmt.setString(4, apelido);
			stmt.setString(5, rua);
			stmt.setString(6, numero);
			stmt.setString(7, bairro);
			stmt.setString(8, cidade);
			stmt.setString(9, telefone);
			stmt.setString(10, contato);
			ResultSet result = stmt.executeQuery();
			result.next();
			Pessoa pessoa = new Pessoa();
			pessoa.setId(new Integer(result.getInt(1)));
			pessoa.setNome(new String(result.getString(2)));
			pessoa.setTipo(new Byte(result.getByte(3)));
			pessoa.setApelido(new String(result.getString(4)));
			// pessoa.setRua(new String(result.getString(5)));
			pessoa.setNumero(new String(result.getString(6)));
			pessoa.setBairro(new String(result.getString(7)));

			CidadeDAO dao = new CidadeDAO();
			Cidade cid = dao.buscar(result.getInt(8));
			pessoa.setCidade(cid);

			pessoa.setTelefone(new String(result.getString(9)));
			pessoa.setContato(new String(result.getString(10)));
			
			result.close();
			stmt.close();
			return pessoa;

		} catch (SQLException ex) {
			Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		} 
		return null;
	}
	
	
	
	
}
