/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//esta calse contiene los nodos que son guardados en las listas de columnas y filas
package gm.com.gt.proyecto4;

/**
 * ESTA CLASE DEFINE EL OBJETO Y METODOS PARA EL NODO DE LA MATRIZ
 * @author Joshua
 */
public class NodoDato {

     int x,y;
    NodoDato izquierda=null;
    NodoDato derecha=null;
    NodoDato arriba=null;
    NodoDato abajo=null;
    Object valor;
    
/***
 * constructor
 * @param x columna
 * @param y fila
 * @param valor valor que guardara el nodo
 */
    public NodoDato(int x, int y, Object valor) {
        this.x = x;
        this.y = y;
        this.valor = valor;
    }
/***
 * devuelve la columna del nodo
 * @return la columna del nodo
 */
    public int getX() {
        return x;
    }
/***
 * inserta el valor de columna del nodo
 * @param x la columna del nodo
 */
    public void setX(int x) {
        this.x = x;
    }
/***
 * devuelve el valor de fila del nodo
 * @return el valor de fila del nodo
 */
    public int getY() {
        return y;
    }
/***
 * inserta el valor de fila del nodo
 * @param y el valor de fila del nodo
 */
    public void setY(int y) {
        this.y = y;
    }
/***
 * devuelve el puntero hacia el nodo izquierdo del nodo
 * @return el nodo a la izquierda
 */
    public NodoDato getIzquierda() {
        return izquierda;
    }
/***
 * inserta el valor para el puntero del nodo izquiero al nodo
 * @param izquierda el nodo de la izquierda
 */
    public void setIzquierda(NodoDato izquierda) {
        this.izquierda = izquierda;
    }
/***
 * metodo para obtener el nodo de la derecha
 * @return el nodo de la derecha
 */
    public NodoDato getDerecha() {
        return derecha;
    }
/***
 * metodo para insertar el nodo de la derecha
 * @param derecha nodo de la derecha
 */
    public void setDerecha(NodoDato derecha) {
        this.derecha = derecha;
    }
/***
 * metood para obtener el nodo de arriba
 * @return el nodo de arriba
 */
    public NodoDato getArriba() {
        return arriba;
    }
/***
 * metodo para insertar el nodo de arriba
 * @param arriba nodo de arriba
 */
    public void setArriba(NodoDato arriba) {
        this.arriba = arriba;
    }
/***
 * obtener el nodo de abajo
 * @return el nodo de abajo
 */
    public NodoDato getAbajo() {
        return abajo;
    }
/***
 * insertar el nodo de abajo
 * @param abajo el nodo de abajo
 */
    public void setAbajo(NodoDato abajo) {
        this.abajo = abajo;
    }
/***
 * obtener el valor del nodo
 * @return el valor del nodo 
 */
    public Object getValor() {
        return valor;
    }
/***
 * insertar el valor del ndo
 * @param valor el valor del nodo
 */
    public void setValor(Object valor) {
        this.valor = valor;
    }

    
    
    
}
