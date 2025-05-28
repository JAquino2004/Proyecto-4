/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;

import javax.swing.event.*;
import javax.swing.table.*;

public class TableModel {

    public static DefaultTableModel crearTabla() {
        int filas = 5;
        int columnas = 5;

        // Nombres de columnas: "Col 1", "Col 2", ..., "Col 5"
        String[] nombresColumnas = new String[columnas];
        for (int i = 0; i < columnas; i++) {
            nombresColumnas[i] = "Col " + (i + 1);
        }

        // Datos vacíos
        Object[][] datos = new Object[filas][columnas];

        // Crear el modelo
        DefaultTableModel modelo = new DefaultTableModel(datos, nombresColumnas);

        // Listener que detecta cambios
        modelo.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() != TableModelEvent.ALL_COLUMNS) {
            int fila = e.getFirstRow();
            int columna = e.getColumn();

            // Extra safety check
            if (fila >= 0 && columna >= 0 && fila < modelo.getRowCount() && columna < modelo.getColumnCount()) {
                
                
                Object nuevoValor = modelo.getValueAt(fila, columna);
                if(nuevoValor!=null){
                  System.out.println("Celda modificada [" + fila + "," + columna + "] -> " + nuevoValor);  
                }
              
            }
        }
            }
        });

        return modelo;
    }
}
