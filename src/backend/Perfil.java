package backend;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class Perfil extends Usuario{
    
    private String idCuenta;
    private String hobbies; 
    private String gustos;
    private InputStream fotografia; 
    private String lugarDeResidencia;
    private String telefono;
    private String genero;
    private String idioma;
    
    /**
     * @return the tarjetaDeCredito
     */
    public String getIdCuenta() {
        return idCuenta;
    }

    /**
     * @param tarjetaDeCredito the tarjetaDeCredito to set
     */
    public void setTarjetaDeCredito(String tarjetaDeCredito) {
        this.idCuenta = tarjetaDeCredito;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    public Perfil() {
	super();
    }
    
    /**
     * @param nombre
     * @param email
     * @param password
     * @param hobbies
     * @param gustos
     * @param fotografia
     * @param lugarDeResidencia
     * @param telefono
     * @param genero
     * @param idioma
     * @param fechaDeNacimiento
     */
    public Perfil(String nombre, String email, String password, int tipoDeUsuario, String tarjetaDeCredito,String hobbies, String gustos, InputStream fotografia,
	    String lugarDeResidencia, String telefono, String genero, String idioma) {
	super(nombre, email, password, tipoDeUsuario);
	this.idCuenta= tarjetaDeCredito;
	this.hobbies = hobbies;
	this.gustos = gustos;
	this.fotografia = fotografia;
	this.lugarDeResidencia = lugarDeResidencia;
	this.telefono = telefono;
	this.genero = genero;
	this.idioma = idioma;
    }
    
    public Perfil(HttpServletRequest request) {
	super(request.getParameter("nombre"), request.getParameter("email"), request.getParameter("password"), getTipoUsuario(request));
	this.hobbies=request.getParameter("hobbies");
	this.gustos=request.getParameter("gustos");
	this.lugarDeResidencia=request.getParameter("lugarDeResidencia");
	this.telefono=request.getParameter("telefono");
	this.genero=request.getParameter("genero");
	this.idioma=request.getParameter("idioma");
    }
    
    public static int getTipoUsuario(HttpServletRequest request) {
	System.out.println("Nomre: "+request.getParameter("nombre"));
	System.out.println("Email "+ request.getParameter("email"));
	System.out.println("Hobbies "+ request.getParameter("hobbies"));
	System.out.println("Gustos "+ request.getParameter("gustos"));
	System.out.println("Idioma "+ request.getParameter("idioma"));
	String tipo = request.getParameter("tipo-de-usuario");
	System.out.println("Tipo de Usuario " + tipo);
	return Integer.parseInt(tipo);
    }
    
    
    /**
     * @return the hobbies
     */
    public String getHobbies() {
        return hobbies;
    }
    /**
     * @param hobbies the hobbies to set
     */
    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }
    /**
     * @return the gustos
     */
    public String getGustos() {
        return gustos;
    }
    /**
     * @param gustos the gustos to set
     */
    public void setGustos(String gustos) {
        this.gustos = gustos;
    }
    /**
     * @return the fotografia
     */
    public InputStream getFotografia() {
        return fotografia;
    }
    /**
     * @param fotografia the fotografia to set
     */
    public void setFotografia(InputStream fotografia) {
        this.fotografia = fotografia;
    }
    /**
     * @return the lugarDeResidencia
     */
    public String getLugarDeResidencia() {
        return lugarDeResidencia;
    }
    /**
     * @param lugarDeResidencia the lugarDeResidencia to set
     */
    public void setLugarDeResidencia(String lugarDeResidencia) {
        this.lugarDeResidencia = lugarDeResidencia;
    }
    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }
    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    /**
     * @return the genero
     */
    public String isGenero() {
        return genero;
    }
    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }
    /**
     * @return the idioma
     */
    public String getIdioma() {
        return idioma;
    }
    /**
     * @param idioma the idioma to set
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    
    
    public PreparedStatement  crearSentencia(int siguienteRegistro,int siguienteCuenta) throws SQLException {
	String sqlPerfil = "INSERT INTO Perfil(id_usuario, id_cuenta, hobbies, gustos, fotografia, lugar_de_residencia, numero_de_telefono, genero, idioma)"
		+ "values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement statementPerfil=null; 
	 statementPerfil = SqlConection.conexion.prepareStatement(sqlPerfil);
	        //ultimo registro en la base de datos
	        statementPerfil.setInt(1, siguienteRegistro);
	        statementPerfil.setInt(2, siguienteCuenta);
	        statementPerfil.setString(3, this.getHobbies());
	        statementPerfil.setString(4, this.getGustos());
	        statementPerfil.setBlob(5, this.getFotografia());
	        statementPerfil.setString(6, this.getLugarDeResidencia());
	        statementPerfil.setString(7, this.getTelefono());
	        statementPerfil.setString(8, this.getGenero());
	        statementPerfil.setString(9, this.getIdioma());
	 return statementPerfil;
    }
}
