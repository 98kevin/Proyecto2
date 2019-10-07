package backend.controlators;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.Editor;
import backend.genaradorHTML;

/**
 * Servlet implementation class ReporteEditorSuscripciones
 */
@WebServlet("/Vistas/ReporteEditorGanancias")
public class ReporteEditorGanancias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReporteEditorGanancias() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ingresando al metodo get");
		PrintWriter out = response.getWriter();
		int codigo = Integer.valueOf( request.getParameter("idUsuario"));
		Editor editor = new Editor();
		ResultSet resultadoSql = editor.reporteDeGanancias();
		String tablaHtml = genaradorHTML.convertirTabla(resultadoSql);
		ResultSet resultadoGeneral = editor.reporteDeGananciasGeneral(codigo);
		String tablaHtmlGeneral = genaradorHTML.convertirTabla(resultadoGeneral);
		out.print(tablaHtml);
		out.print(tablaHtmlGeneral);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
