package backend.controlators;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class VerRevista
 */
@WebServlet("/Vistas/VerRevista")

public class VerRevista extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerRevista() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getParameter("path");
		int codigoPublicacion = Integer.parseInt(request.getParameter("idPublicacion"));
		HttpSession session = request.getSession();
		session.setAttribute("path", path);
		session.setAttribute("idPublicacion", codigoPublicacion);
		System.out.println("Codigo de publicacion asingnado: "+ codigoPublicacion);
        response.sendRedirect("suscriptor/vistaRevistaNueva.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
