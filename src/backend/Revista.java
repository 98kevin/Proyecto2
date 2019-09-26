package backend;

public class Revista {

    String categoria; 
    Editor editor;
    double cuotaMensual;
    double costoDiario;
    /**
     * @param categoria
     * @param editor
     * @param cuotaMensual
     * @param costoDiario
     */
    public Revista(String categoria, Editor editor, double cuotaMensual, double costoDiario) {
	super();
	this.categoria = categoria;
	this.editor = editor;
	this.cuotaMensual = cuotaMensual;
	this.costoDiario = costoDiario;
    }
    
    
}
