package backend;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
public class Usuario {

    	private final int SUSCRIPTOR=1;
    	private final int EDITOR=2;
    	private final int ADMINISTRADOR=3;
    	
	private String nombre;
	private String email;
	private String password;
	private int tipoDeUsuario;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Usuario() {
	    super();
	}
	
	public Usuario(String nombre, String email, String password, int tipoDeUsuario) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.tipoDeUsuario=tipoDeUsuario;
	}
	
	public Usuario(HttpServletRequest request) {
	    this.email=request.getParameter("email");
	    this.password=request.getParameter("password");
	}
	
	public Usuario(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	/**
	 * @return the tipoDeUsuario
	 */
	public int getTipoDeUsuario() {
	    return tipoDeUsuario;
	}
	/**
	 * @param tipoDeUsuario the tipoDeUsuario to set
	 */
	public void setTipoDeUsuario(int tipoDeUsuario) {
	    this.tipoDeUsuario = tipoDeUsuario;
	}
	public boolean integro() {
	    SqlConection conexion = new SqlConection();
	    String passwordReal= conexion.leerPassword(this.getEmail());
	    String passwordDesencriptada= descifrarPassword(passwordReal);
	    return (getPassword().equals(passwordDesencriptada));
	}	
	
	public PreparedStatement crearSentencia( int siguienteRegistro) throws SQLException {
	    String sqlUser = "INSERT INTO Usuario (id_usuario, nombre, correo_electronico, password, tipo_de_usuario) values (?,?,?,?,?)";
	    PreparedStatement statementUser = SqlConection.getConexion().prepareStatement(sqlUser);
	    	//Siguiente registro en la base de datos
	        statementUser.setInt(1, siguienteRegistro );
	        statementUser.setString(2, this.getNombre());
	        statementUser.setString(3, this.getEmail());
	        statementUser.setString(4, this.cifrarPassword());
	        statementUser.setInt(5, this.getTipoDeUsuario());
	   return statementUser;
	}
	
	
	private String cifrarPassword() {
	    Password pass = new Password();
	    String passwordEncriptada=null;
	    try {
		passwordEncriptada =pass.encriptar(this.getPassword());
	    } catch (UnsupportedEncodingException | GeneralSecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    return passwordEncriptada;
	}
	
	
	private String descifrarPassword(String password) {
	    Password pass = new Password();
	    String passwordDesencriptada=null;
	    try {
		passwordDesencriptada =pass.desencriptar(password);
	    } catch (GeneralSecurityException | IOException e) {
		e.printStackTrace();
	    }
	    return passwordDesencriptada;
	}
	
	public String getRecurso() {
	    SqlConection conexion = new SqlConection();
	    	switch(conexion.leerTipoDeUsuario(getEmail())) {
	    	case(SUSCRIPTOR):
	    	    return "view-suscriptor.jsp";
	    	case(EDITOR):
	    	    return "view-editor.jsp";
	    	case(ADMINISTRADOR):
	    	    return "view-administrador.jsp";
	    	default :
		    return "error.jsp";
	    	}

	}
	
}
