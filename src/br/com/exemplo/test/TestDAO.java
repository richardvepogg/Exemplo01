package br.com.exemplo.test;

import java.util.List;

import br.com.exemplo.bens.Usuario;
import br.com.exemplo.jdbc.UsuarioDAO;


public class TestDAO {

	public static void main(String[] args) {
//testCadastro();
	//testAlterar();
	//testDeletar();
	buscarTodos();
	}

	
	public static void testCadastro() {
		Usuario usu = new Usuario();
		
usu.setNome("jeremiasorpiemiro");
usu.setEmail("Rfdsfsdfs");
usu.setSenha("65656");
		

UsuarioDAO usuDao = new UsuarioDAO();
usuDao.cadastro(usu);

	}
	
	public static void testAlterar() {
		Usuario usu = new Usuario();
		
usu.setNome("richard");
usu.setEmail("vepogmail");
usu.setSenha("84485");
usu.setId(6);
		

UsuarioDAO usuDao = new UsuarioDAO();
usuDao.alterar(usu);

	}
	
	public static void testDeletar() {
		
	Usuario usu = new Usuario();
	usu.setId(9);
	
	UsuarioDAO usuDAO = new UsuarioDAO();
	usuDAO.deletar(usu);
	}
	
	
	public static void buscarTodos() {
		Usuario usu = new Usuario();
		UsuarioDAO usuDAO = new UsuarioDAO();
		List<Usuario> listaResultado = usuDAO.buscarTodos(usu);
		
		for(Usuario u:listaResultado) {
			System.out.println("Id: "+u.getId()+ " Nome: "+u.getNome()+" Email: "+u.getEmail()+ " Senha:"+u.getSenha() );
		}
	}
}
