/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geosolutions.zoom.security;

/**
 *
 * @author Administrador
 */
public class Cripto {

    private String caracteres = "abcdefghijklmnopqrstuvwxyzáéíóú 1234567890@.,-_";

    public String Encriptar(String text, int key) {
        String texto = LimpiarCadena(text);
        //aqui se almacena el resultado
        String res = "";
        for (int i = 0; i < texto.length(); i++) {
            //busca la posicion del caracter en la variable tabla
            int pos = caracteres.indexOf(texto.charAt(i));
            //realiza el reemplazo
            if ((pos + key) < caracteres.length()) {
                res = res + caracteres.charAt(pos + key);
            } else {
                res = res + caracteres.charAt((pos + key) - caracteres.length());
            }
        }
        return res;
    }

    public String Desencriptar(String text, int key) {
        String texto = LimpiarCadena(text);
        String res = "";
        for (int i = 0; i < texto.length(); i++) {
            int pos = caracteres.indexOf(texto.charAt(i));
            if ((pos - key) < 0) {
                res = res + caracteres.charAt((pos - key) + caracteres.length());
            } else {
                res = res + caracteres.charAt(pos - key);
            }
        }
        return res;
    }

    private String LimpiarCadena(String t) {
        //transforma el texto a minusculas
        t = t.toLowerCase();
        //eliminamos todos los retornos de carro
        t = t.replaceAll("\n", "");
        //eliminamos caracteres prohibidos
        for (int i = 0; i < t.length(); i++) {
            int pos = caracteres.indexOf(t.charAt(i));
            if (pos == -1) {
                t = t.replace(t.charAt(i), ' ');
            }
        }
        return t;
    }
}
