package backend;

public class Main {
    
	private static Password password;
	
	public static void main(String args[]) {
		new Main();
	}

	public Main() {
	    /**
		password = new Password();
		try {

			String cadena = "98kevinf";
			System.out.println("Password sin encriptar "+ cadena);
			String passwordEncriptada = password.encriptar(cadena);
			System.out.println("Password encriptada '"+passwordEncriptada+"'");
			String passwordOriginal = password.desencriptar(passwordEncriptada);
			System.out.println("Password desencriptada "+ passwordOriginal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	    @SuppressWarnings("unused")
	    SqlConection conexion = new SqlConection();
	}
}
