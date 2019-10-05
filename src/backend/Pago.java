package backend;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Pago {
    private int idPago; 
    private Date fecha; 
    private int idUsuario;
    private int idSuscripcion;
    /**
     * @return the idPago
     */
    public int getIdPago() {
        return idPago;
    }
    /**
     * @param idPago the idPago to set
     */
    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }
    
    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }
    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }
    /**
     * @param idUsuario t//he idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    /**
     * @return the idSuscripcion
     */
    public int getIdSuscripcion() {
        return idSuscripcion;
    }
    /**
     * @param idSuscripcion the idSuscripcion to set
     */
    public void setIdSuscripcion(int idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }
    /**
     * @param idPago
     * @param mes
     * @param anio
     * @param idUsuario
     * @param idSuscripcion
     */
    public Pago(int idPago, Date fecha,  int idUsuario, int idSuscripcion) {
	super();
	this.idPago = idPago;
	this.fecha= fecha;
	this.idUsuario = idUsuario;
	this.idSuscripcion = idSuscripcion;
    }
    
    public Pago() {
	super();
    }
    
    public Pago(ResultSet resultado) {
	try {
	    this.idPago = resultado.getInt(1);
	    this.fecha= resultado.getDate(2);
	    this.idUsuario= resultado.getInt(3);
	    this.idSuscripcion = resultado.getInt(4);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    
    public ArrayList<Pago> pagos(int codigoSuscripcion) throws SQLException {
	ArrayList<Pago> pagos = new ArrayList<Pago>();
	String sql = "SELECT * FROM Pago WHERE id_suscripcion = ? ";
	new SqlConection();
	try {
		PreparedStatement stm =  SqlConection.conexion.prepareStatement(sql);
		stm.setInt(1, codigoSuscripcion);
		ResultSet resultados = stm.executeQuery();
		while (resultados.next()) {
		    pagos.add(new Pago(resultados.getInt(1), 
			    resultados.getDate(2),
			    resultados.getInt(3), 
			    resultados.getInt(4)));
		}
	} catch (Exception e) {
	    e.printStackTrace();
	}

	
	return pagos;
    }
    
}
