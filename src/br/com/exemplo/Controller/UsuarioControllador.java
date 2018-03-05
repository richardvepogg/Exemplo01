package br.com.exemplo.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.exemplo.bens.Usuario;
import br.com.exemplo.jdbc.UsuarioDAO;

/**
 * Servlet implementation class UsuarioControllador
 */
@WebServlet("/UsuarioControllador")
public class UsuarioControllador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioControllador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usu = new Usuario();
		String acao = request.getParameter("acao");
		UsuarioDAO usuDAO = new UsuarioDAO();
		
		if(acao != null && acao.equals("lis")) {		
List<Usuario> lista = usuDAO.buscarTodos(usu);
		
		request.setAttribute("lista", lista);
		RequestDispatcher saida = request.getRequestDispatcher("listaUsuarios.jsp");
		saida.forward(request, response);
	}else if(acao != null && acao.equals("ex")) {
          String id = request.getParameter("id");
          usu.setId(Integer.parseInt(id));
          usuDAO.deletar(usu);
          response.sendRedirect("UsuarioControllador?acao=lis");

	}else if(acao != null && acao.equals("alt")) {
	String id = request.getParameter("id");
	Usuario usuario = usuDAO.buscarporID(Integer.parseInt(id));
	request.setAttribute("usuario", usuario);
	request.getRequestDispatcher("AlterarCad.jsp").forward(request, response);
	//AlterarCad.jsp

	}else if(acao != null && acao.equals("cad")) {
	RequestDispatcher saida = request.getRequestDispatcher("cadastro.jsp");
	saida.forward(request, response);
	}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sid = request.getParameter("id");
		String snome = request.getParameter("nome");
		String smail = request.getParameter("email");
		String ssenha = request.getParameter("senha");
		
		Usuario usuario = new Usuario();
		usuario.setNome(snome);
		usuario.setEmail(smail);
		usuario.setSenha(ssenha);
		usuario.setId(Integer.parseInt(sid));
		
		UsuarioDAO usuDao = new UsuarioDAO();
		
      //salvando
		usuDao.alterar(usuario);
		//PrintWriter out = response.getWriter();
		
		//out salvo
		
		response.sendRedirect("UsuarioControllador?acao=lis");
		
	}

}
