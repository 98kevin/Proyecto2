package backend.controlators;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.Perfil;


/**
 * Servlet implementation class ControladorSuscriptor
 */
@WebServlet("/Vistas/ControladorSuscriptor")
public class ControladorSuscriptor extends HttpServlet {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 6843281787590743824L;


	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorSuscriptor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int codigo = Integer.valueOf( request.getParameter("idUsuario"));
		System.out.println("Codigo en metodo get: "+ codigo);
		Perfil perfil = new Perfil();
		ResultSet resultadoSql = perfil.revistasDisponibles(codigo);
		try {
			while (resultadoSql.next()) {
					//Cartas 
					out.print("	<form name=\"form\" action=\"VerRevista\" method=\"get\">");
					out.print("<div class=\"card\" style=\"width: 18rem;\">");
					out.print("<div class=\"card-body\">");
					out.print("<h5 class=\"card-title\">"+resultadoSql.getInt(1)+"</h5>");
					out.print("<h6 class=\"card-subtitle mb-2 text-muted\">"+resultadoSql.getString(2)+"</h6>");
					out.print("<p class=\"card-text\">"+resultadoSql.getString(3)+"</p>");
					out.print("<input type=\"hidden\" name=\"path\" value=\""+resultadoSql.getString(4)+"\">");
					out.print("<p class=\"card-text\">"+"Fecha de publicacion: "+resultadoSql.getString(5)+"</p>");
					out.print("<input type=\"hidden\" name=\"idPublicacion\" value=\""+resultadoSql.getString(6)+"\">");
					out.print("<button type=\"submit\" class=\"btn btn-default\">Ver revista</button>");
					out.print("</div>");
					out.print("</div>");
					out.print("</form>");
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
