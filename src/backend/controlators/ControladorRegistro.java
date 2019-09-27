package backend.controlators;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import backend.Perfil;
import backend.SqlConection;
import exceptions.ErrorCreacionUsuario;

@MultipartConfig(maxFileSize = 1000000)    // upload file's size up to 1 MB
@WebServlet("/Vistas/Controlador-Registro")
public class ControladorRegistro extends HttpServlet{
    /**
     * 
     */
    private static final long serialVersionUID = 256240027249774542L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Perfil perfil = new Perfil(request);
	InputStream inputStream = null; // input stream de la carga de la fotografia
	// obtenemos la parte del arhivo subido  en esta parte multiple del request
        Part filePart = request.getPart("fotografia");
        if (filePart != null)              
            // Obtenemos el input stream del archivo cargador
            inputStream = filePart.getInputStream();
	perfil.setFotografia(inputStream);
	try {
	    SqlConection conexion = new SqlConection();
	    conexion.escribirRegistro(perfil);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("resultado.jsp");
            dispatcher.forward(request, response);
	} catch (ErrorCreacionUsuario | SQLException  e) {
	    request.setAttribute("error", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("RegistroUsuario.jsp");
            dispatcher.forward(request, response);
	    e.printStackTrace();
	}
    }

}