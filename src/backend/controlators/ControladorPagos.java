package backend.controlators;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import backend.Pago;
import backend.SqlConection;

@WebServlet("/Vistas/suscriptor/controlador-pagos")
public class ControladorPagos extends HttpServlet{

    /**
     * 
     */
    private static final long serialVersionUID = 6142942478995354481L;

    private int codigoSeleccionado; 
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	codigoSeleccionado = Integer.parseInt(request.getParameter("codigoRevista"));
	HttpSession sesion = request.getSession();
	sesion.setAttribute("codigoRevista", codigoSeleccionado);
	try {
	    SqlConection sql = new SqlConection();
	    int codigoUsuario= (int) request.getSession().getAttribute("codigo");
	    int idSuscripcion = sql.leerCodigoSuscripcion(codigoSeleccionado, codigoUsuario);
	    ArrayList<Pago> pagos = new Pago().pagos(idSuscripcion);
	    request.setAttribute("pagos", pagos);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
        RequestDispatcher dispatcher = request.getRequestDispatcher("cantidad-de-pagos.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session= request.getSession();
	    int codigoSeleccionado = (int) session.getAttribute("codigoRevista");
	    System.out.println("Codigo Seleccionado: "+codigoSeleccionado);
	    int codigoUsuario= (int) session.getAttribute("codigo");
	    int idSuscripcion;
	    Date fechaUltimoPago=null;
	    int cantidadDePagos = Integer.parseInt( request.getParameter("cantidad-de-pagos"));
	    SqlConection sql = new SqlConection();
		try {
		    idSuscripcion = sql.leerCodigoSuscripcion(codigoSeleccionado, codigoUsuario);
		    fechaUltimoPago = sql.leerUltimoPago(codigoSeleccionado, codigoUsuario);
		    sql.pagar(codigoUsuario, codigoSeleccionado, idSuscripcion, fechaUltimoPago, cantidadDePagos);
		} catch (SQLException e) {
		    e.printStackTrace();
		}
    }
}

