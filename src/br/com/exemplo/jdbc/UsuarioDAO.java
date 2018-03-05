package br.com.exemplo.jdbc;
import br.com.exemplo.bens.Usuario;
import br.com.exemplo.jdbc.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO {

private	Connection con = Conexao.getConnection();

public void cadastro(Usuario usuario) {
 
	
	String sql = "INSERT INTO public.\"Usuario\"(\r\n" + 
			"	nome, email, senha)\r\n" + 
			"	VALUES (?, ?, ?);";
try {
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setString(1, usuario.getNome());
	preparador.setString(2, usuario.getEmail());
	preparador.setString(3, usuario.getSenha());

	preparador.execute();
preparador.close();

System.out.println("Cadastrado com sucesso!");
} catch (SQLException e) {
	System.out.println("Erro - "+e.getMessage());
}

}

public void alterar(Usuario usuario) {
 
	
	String sql = "UPDATE public.\"Usuario\"\r\n" + 
			"	SET nome=?, email=?, senha=?\r\n" + 
			"	WHERE id=?;";
try {
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setString(1, usuario.getNome());
	preparador.setString(2, usuario.getEmail());
	preparador.setString(3, usuario.getSenha());
	preparador.setInt(4, usuario.getId());
	
	preparador.execute();
preparador.close();

System.out.println("Alterado com sucesso!");
} catch (SQLException e) {
	System.out.println("Erro - "+e.getMessage());
}

}

public void deletar(Usuario usuario) {
 
	
	String sql = "DELETE FROM public.\"Usuario\"\r\n" + 
			"	WHERE id=?;";
try {
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setInt(1, usuario.getId());
	
	
	preparador.execute();
preparador.close();

System.out.println("Deletado com sucesso!");
} catch (SQLException e) {
	System.out.println("Erro - "+e.getMessage());
}

}

public List<Usuario> buscarTodos(Usuario usuario){
	String sql = "SELECT *\r\n" + 
			"	FROM public.\"Usuario\";";
			List<Usuario> lista = new ArrayList<Usuario>();
			try {
				PreparedStatement preparador = con.prepareStatement(sql);
			    ResultSet resultado = preparador.executeQuery();
			    while(resultado.next()) {
			    	Usuario usu = new Usuario();
			    	usu.setId(resultado.getInt("id"));
			    	usu.setNome(resultado.getString("nome"));
			    	usu.setEmail(resultado.getString("email"));
			    	usu.setSenha(resultado.getString("senha"));
			    	lista.add(usu);
			    }
				
			} catch (SQLException e) {
				System.out.println("Erro - "+e.getMessage());
			}
			
return lista;
}


public Usuario buscarporID(Integer id) {
	Usuario usuRetorno = null;
	String sql = "SELECT *\r\n" + 
			"	FROM public.\"Usuario\" where id=?;";
	try {
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setInt(1, id);
		
		ResultSet resultado = preparador.executeQuery();
		
		if(resultado.next()) {
			usuRetorno = new Usuario();
			usuRetorno.setId(resultado.getInt("id"));
			usuRetorno.setNome(resultado.getString("nome"));
			usuRetorno.setEmail(resultado.getString("email"));
			usuRetorno.setSenha(resultado.getString("senha"));
			
		}
	System.out.println("Encontrado com sucesso!");
	} catch (SQLException e) {
		System.out.println("Erro de Sql:"+e.getMessage());
	}
return usuRetorno;
}


}
