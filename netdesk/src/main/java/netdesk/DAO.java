package netdesk;

import java.sql.*;

public class DAO {
	private Connection conexao;

	public DAO() {
		conexao = null;
	}

	public boolean conectar() {
		String driverName = "org.postgresql.Driver";
		String serverName = "localhost";
		String mydatabase = "netdesk";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
		String username = "postgres";
		String password = "426513";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexao efetuada com o postgres!");
		} catch (ClassNotFoundException e) {
			System.err.println("Conexao nao efetuada com o postgres -- Driver nao encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexao nao efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}

	public boolean close() {
		boolean status = false;

		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

	// ----------------------------------------------------------------------------------------------------------------------------------
	public boolean inserirUsuario(Usuario usuario) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate(
					"INSERT INTO usuario (cpf, nome, datanasc, username, senha, email, cidade, estado, pais, avaliacao) "
							+ "VALUES (" + usuario.getCPF() + ", '" + usuario.getNome() + "', " + usuario.getDataNasc()
							+ ", '" + usuario.getUsername() + "', '" + usuario.getSenha() + "', " + usuario.getEmail()
							+ ", '" + usuario.getCidade() + "', '" + usuario.getEstado() + "', '" + usuario.getPais()
							+ "', " + usuario.getAvaliacao() + ")");
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarUsuario(Usuario usuario, String cpf) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE usuario SET nome = '" + usuario.getNome() + "', datanasc = " + usuario.getDataNasc()
					+ ", username = '" + usuario.getUsername() + "', senha = '" + usuario.getSenha() + "', email = "
					+ usuario.getEmail() + " WHERE cpf = " + cpf;
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean excluirUsuario(String CPF) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM usuario WHERE cpf = " + CPF);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean usuarioExiste(String CPF) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM usuario WHERE cpf = " + CPF);
			status = rs.next();
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public Usuario getUsuario(String CPF) {
		Usuario resposta = new Usuario();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM usuario WHERE cpf = " + CPF);
			if (rs.next()) {
				resposta = new Usuario(
						rs.getString("cpf"),
						rs.getString("nome"),
						rs.getDate("datanasc"),
						rs.getString("username"),
						rs.getString("senha"),
						rs.getString("email"),
						rs.getString("cidade"),
						rs.getString("estado"),
						rs.getString("pais"),
						rs.getInt("avaliacao"));
			}
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return resposta;
	}

	// ----------------------------------------------------------------------------------------------------------------------------------
	public Anuncio[] getAllAnuncios() {
		Anuncio[] anuncios = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM anuncio");
			if (rs.next()) {
				rs.last();
				anuncios = new Anuncio[rs.getRow()];
				rs.beforeFirst();

				for (int i = 0; rs.next(); i++) {
					anuncios[i] = new Anuncio(
							rs.getInt("id"),
							rs.getString("cpf"),
							rs.getString("titulo"),
							rs.getString("descricao"),
							rs.getString("valor"),
							rs.getString("cpu"),
							rs.getString("ram"),
							rs.getString("gpu"),
							rs.getString("so"),
							rs.getString("armazenamento"),
							rs.getString("pais"),
							rs.getString("cidade"),
							rs.getString("estado"),
							rs.getString("situacao"));
				}
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return anuncios;
	}

	public Anuncio getAnuncio(int id) {
		Statement st = conexao.createStatement();
		String sql = "SELECT * FROM anuncio WHERE id = " + id;
		ResultSet rs = st.executeQuery(sql);
		Anuncio temp = new Anuncio(
				rs.getInt("id"),
				rs.getString("cpf"),
				rs.getString("titulo"),
				rs.getString("descricao"),
				rs.getString("valor"),
				rs.getString("cpu"),
				rs.getString("ram"),
				rs.getString("gpu"),
				rs.getString("so"),
				rs.getString("armazenamento"),
				rs.getString("pais"),
				rs.getString("cidade"),
				rs.getString("estado"),
				rs.getString("situacao"));
		return temp;
	}

	public boolean inserirAnuncio(Anuncio anuncio) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate(
					"INSERT INTO anuncio (id, cpf, titulo, descricao, valor, gpu, ram, cpu, so, armazenamento, pais, cidade, estado, situacao) "
							+ "VALUES (" + anuncio.getID() + ", '" + anuncio.getCPF() + "', '" + anuncio.getTitulo()
							+ "', '" + anuncio.getDescricao() + "', '" + anuncio.getValor() + "', '" + anuncio.getGPU()
							+ "', '" + anuncio.getRAM() + "', '" + anuncio.getCPU() + "', '" + anuncio.getSO() + "', '"
							+ anuncio.getArmazenamento() + "', '" + anuncio.getPais() + "', '" + anuncio.getCidade()
							+ "', '" + anuncio.getEstado() + "', '" + anuncio.getSituacao() + "')");
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarAnuncio(Anuncio anuncio, int id, String cpf) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE anuncio SET titulo = '" + anuncio.getTitulo() + "', descricao = '"
					+ anuncio.getDescricao() + "', valor = '" + anuncio.getValor() + "', gpu = '" + anuncio.getGPU()
					+ "', ram = '" + anuncio.getRAM() + "', cpu = '" + anuncio.getCPU() + "', so = '" + anuncio.getSO()
					+ "', armazenamento = '" + anuncio.getArmazenamento() + "', pais = '" + anuncio.getPais()
					+ "', cidade = '" + anuncio.getCidade() + "', estado = '" + anuncio.getEstado() + "', situacao = '"
					+ anuncio.getSituacao() + "' WHERE id = " + id + " AND cpf = " + cpf;
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean excluirAnuncio(int id, String cpf) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM anuncio WHERE id = " + id + " AND cpf = " + cpf;
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
}