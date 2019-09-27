package backend;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;


public class Password {

	private String password="98kevinf";
    
    private static SecretKeySpec createSecretKey(char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // La sal que se almacenara junto con los datos cifrados
        byte[] salt = new String("12345678").getBytes();
        // Disminuir esto acelera el tiempo de inicio y puede ser útil durante las pruebas, pero también facilita a los atacantes de fuerza bruta
        int iterationCount = 40000;
        // Otros valores que me da java.security.InvalidKeyException: Invalido tamanio de clave
        int keyLength = 128;
    	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
        SecretKey keyTmp = keyFactory.generateSecret(keySpec);
        return new SecretKeySpec(keyTmp.getEncoded(), "AES");
    }

    public String encriptar(String pass) throws GeneralSecurityException, UnsupportedEncodingException {
    	SecretKeySpec key = createSecretKey(password.toCharArray());
    	Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        pbeCipher.init(Cipher.ENCRYPT_MODE, key);
        AlgorithmParameters parameters = pbeCipher.getParameters();
        IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
        byte[] cryptoText = pbeCipher.doFinal(pass.getBytes("UTF-8"));
        byte[] iv = ivParameterSpec.getIV();
        return base64Encode(iv) + ":" + base64Encode(cryptoText);
    }

    private String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public String desencriptar(String pass) throws GeneralSecurityException, IOException {
    	SecretKeySpec key = createSecretKey(password.toCharArray());
        String iv = pass.split(":")[0];
        String property = pass.split(":")[1];
        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
        return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
    }

    private byte[] base64Decode(String property) throws IOException {
        return Base64.getDecoder().decode(property);
    }
    
    public boolean aceptada(String claveIngresada,String claveEnSistema) {
    	boolean result=false;
    	try {
		result = (claveIngresada.equals(desencriptar(claveEnSistema)));
    	} catch (GeneralSecurityException | IOException e) {
    	    e.printStackTrace();
    	}	
    	return result;
    }
}
