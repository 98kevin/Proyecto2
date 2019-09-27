package backend;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cuenta {

    int idCuenta; 
    double abono;
    /**
     * @return the idCuenta
     */
    public int getIdCuenta() {
        return idCuenta;
    }
    /**
     * @param idCuenta the idCuenta to set
     */
    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }
    /**
     * @return the abono
     */
    public double getAbono() {
        return abono;
    }
    /**
     * @param abono the abono to set
     */
    public void setAbono(double abono) {
        this.abono = abono;
    }
    
    public Cuenta() {
	super();
    }
    
    /**
     * @param idCuenta
     * @param abono
     */
    public Cuenta(int idCuenta, double abono) {
	super();
	this.idCuenta = idCuenta;
	this.abono = abono;
    } 
    
    public PreparedStatement crearSentencia() throws SQLException {
	String sqlUser = "INSERT INTO Cuenta (abonos) values (?)";
	    PreparedStatement statementUser = SqlConection.getConexion().prepareStatement(sqlUser);
	    	//Siguiente registro en la base de datos
	        statementUser.setInt(1,0 );
	   return statementUser;
    }
    
}
