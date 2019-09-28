package backend;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Permiso {
    
    private int codigo; 
    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }
    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    private boolean suscripciones;
    private boolean meGusta;
    private boolean comentarios;
    /**
     * @return the suscripciones
     */
    public boolean isSuscripciones() {
        return suscripciones;
    }
    /**
     * @param suscripciones the suscripciones to set
     */
    public void setSuscripciones(boolean suscripciones) {
        this.suscripciones = suscripciones;
    }
    /**
     * @return the meGusta
     */
    public boolean isMeGusta() {
        return meGusta;
    }
    /**
     * @param meGusta the meGusta to set
     */
    public void setMeGusta(boolean meGusta) {
        this.meGusta = meGusta;
    }
    /**
     * @return the comentarios
     */
    public boolean isComentarios() {
        return comentarios;
    }
    /**
     * @param comentarios the comentarios to set
     */
    public void setComentarios(boolean comentarios) {
        this.comentarios = comentarios;
    }
    
    public Permiso(int codigo) {
	super();
	this.codigo= codigo;
	this.suscripciones=true;
	this.meGusta=true;
	this.comentarios= true;
    }
    
    public PreparedStatement crearSentencia() throws SQLException {
	    String statement = "INSERT INTO Permisos values (?,?,?,?)";
	    PreparedStatement statementPermiso = SqlConection.getConexion().prepareStatement(statement);
	    //Siguiente registro en la base de datos
	    statementPermiso.setInt(1, this.getCodigo());
	    statementPermiso.setBoolean(2, this.isSuscripciones());
	    statementPermiso.setBoolean(3, this.isMeGusta());
	    statementPermiso.setBoolean(4, this.isComentarios());
	    System.out.println("Sentencia de permiso creada");
	return statementPermiso;
    }
    
    
}
