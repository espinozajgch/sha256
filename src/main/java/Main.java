import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ThreadLocalRandom;


public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String hexString = "";
        String base = "";
        while(true) {

            base = cadenaAleatoria();
            LOGGER.info("cadena: {} ", base);

            hexString = sha256(base);

            if (hexString.contains("b00da")) {
                LOGGER.info(hexString);
                break;
            }
            else{
                LOGGER.info("sin coincidencias");
            }
        }
    }

    public static String sha256(String base)  throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        final byte[] hash = digest.digest(base.getBytes("UTF-8"));
        final StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < hash.length; i++) {
            final String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static int numeroAleatorioEnRango(int minimo, int maximo) {
        // nextInt regresa en rango pero con límite superior exclusivo, por eso sumamos 1
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }

    public static String cadenaAleatoria(){
        String alfabeto = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        String cadena = "";
        for (int x = 0; x < 10; x++) {
            int indiceAleatorio = numeroAleatorioEnRango(0, alfabeto.length() - 1);
            char caracterAleatorio = alfabeto.charAt(indiceAleatorio);
            cadena += caracterAleatorio;
        }

        return cadena;
    }
}

