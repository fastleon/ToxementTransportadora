package co.com.toxement.toxdoortest.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    private static String metodo = "MD5";

    public static String encriptarMD5(String texto) {
        try {
            MessageDigest md5 = MessageDigest.getInstance(metodo);
            md5.update(texto.getBytes());
            byte[] digest = md5.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verificarMD5(String texto, String txtEncrypt) {
        return (encriptarMD5(texto).equals(txtEncrypt));
    }
}
