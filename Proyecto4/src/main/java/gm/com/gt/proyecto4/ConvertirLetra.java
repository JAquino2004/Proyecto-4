/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gm.com.gt.proyecto4;
import java.util.HashMap;
/**
 *ESTA CLASE SE UTILIZA PARA PODER DEVOLVER VALORES EN FORMA DE LETRA LUEGO DE HABER RECIBIDO UN NUMERO, EN ORDEN ALFABETICO
 * @author Joshua
 */


public class ConvertirLetra {

    private static final HashMap<Integer, Character> mapaLetras = new HashMap<>();

    static {
        for (int i = 0; i < 26; i++) {
            mapaLetras.put(i, (char) ('A' + i));
        }
    }

    /*** 
     * este metodo obtiene un numero entero y devuelve una letra equivalente
     * @param numero VALOR QUE NOS ES DADO PARA CONVERTIR EN UNA LETRA
     * @return LETRA EQUIVALENTE AL VALOR DADO
     */
    public static String letras(int numero) {
        if (numero < 1) {
            throw new IllegalArgumentException("El número debe ser mayor o igual a 1.");
        }

        StringBuilder resultado = new StringBuilder();

        while (numero > 0) {
            numero--; // Ajuste para 0-indexado
            int residuo = numero % 26;
            resultado.insert(0, mapaLetras.get(residuo));
            numero /= 26;
        }

        return resultado.toString();
    }

   
}

