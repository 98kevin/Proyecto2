package pruebasProyecto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test/testQuery")
public class ServletPrueba extends HttpServlet {
    /**
     * Serializacion
     */
    private static final long serialVersionUID = -2960438929543050756L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String data = "Hello World!"; 
	response.setContentType("text/plain"); 
	response.setCharacterEncoding("UTF-8"); 
	response.getWriter().write(data);
    }
    
}
