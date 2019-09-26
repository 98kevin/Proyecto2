package backend;

import java.sql.Date;

public class MeGusta {
    Date fecha; 
    Cliente cliente;
    Revista revista;
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
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }
    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    /**
     * @return the revista
     */
    public Revista getRevista() {
        return revista;
    }
    /**
     * @param revista the revista to set
     */
    public void setRevista(Revista revista) {
        this.revista = revista;
    }
    /**
     * @param fecha
     * @param cliente
     * @param revista
     */
    public MeGusta(Date fecha, Cliente cliente, Revista revista) {
	super();
	this.fecha = fecha;
	this.cliente = cliente;
	this.revista = revista;
    }
    
    

}
