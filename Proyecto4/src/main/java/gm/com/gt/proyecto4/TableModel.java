package gm.com.gt.proyecto4;
/**
 * ESTA CLASE DEFINE EL MODELO QUE LA TABLA UTILIZARA
 * @author Joshua
 */
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;

public class TableModel {
/***
 * metodo para crear el table model
 * @return el table model
 */
    public static DefaultTableModel crearTabla() {
        int filas = 5;
        int columnas = 5;
        MatrizDinamica matriz = new MatrizDinamica();

        String[] nombresColumnas = new String[columnas];
        for (int i = 0; i < columnas; i++) {
            ConvertirLetra letra = new ConvertirLetra();
            nombresColumnas[i] = letra.letras(i + 1);
        }

        Object[][] datos = new Object[filas][columnas];
        DefaultTableModel modelo = new DefaultTableModel(datos, nombresColumnas);

        resolver evaluator = new resolver();

        modelo.addTableModelListener(new TableModelListener() {
            @Override
            /***
             * metodo para insertar listeners a la tabla cuando esta cambia
             */
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() != TableModelEvent.ALL_COLUMNS) {
                    int fila = e.getFirstRow();
                    int columna = e.getColumn();
                    Object nuevoValor = modelo.getValueAt(fila, columna);

                    if (nuevoValor == null) {
                        matriz.eliminar(columna, fila);
                        return;
                    }

                    if (nuevoValor instanceof String str && str.startsWith("=")) {
                        String formula = str.toUpperCase();
                        Object resultado = "#ERROR!";

                        if (formula.startsWith("=SUMA(") && formula.endsWith(")")) {
                            String rango = formula.substring(6, formula.length() - 1);
                            resultado = evaluarSumaRango(rango);
                        } else {
                            Map<String, Object> celdas = new HashMap<>();
                            for (int r = 0; r < modelo.getRowCount(); r++) {
                                for (int c = 0; c < modelo.getColumnCount(); c++) {
                                    String nombre = getCellName(r, c);
                                    Object val = modelo.getValueAt(r, c);
                                    if (val != null && !(val instanceof String && ((String) val).startsWith("="))) {
                                        celdas.put(nombre, parseDouble(val));
                                    }
                                }
                            }
                            resultado = evaluator.evaluate(formula, celdas);
                        }

                        modelo.setValueAt(resultado, fila, columna);
                        matriz.insertar(columna, fila, resultado);
                    } else  {
                        matriz.insertar(columna, fila, nuevoValor);
                        System.out.println(nuevoValor);
                    }
                }
            }
            /***
             * funcion para obtener la cordenada en forma de letra y numero
             */
            private String getCellName(int fila, int columna) {
                return String.valueOf((char) ('A' + columna)) + (fila + 1);
            }
            /***
             * funcion para convertir el resultado en double
             */
            private Double parseDouble(Object val) {
                try {
                    return Double.parseDouble(val.toString());
                } catch (Exception e) {
                    return 0.0;
                }
            }
            /***
             * funcion que convierte la celda en coordenada
             */
            private int[] convertirCeldaACoordenada(String celda) {
                char columnaChar = celda.toUpperCase().charAt(0);
                int columna = columnaChar - 'A';
                int fila = Integer.parseInt(celda.substring(1)) - 1;
                return new int[]{fila, columna};
            }
/***
 * funcion para sumar en rangos
 */
            private double evaluarSumaRango(String rango) {
                try {
                    String[] partes = rango.split(":");
                    if (partes.length != 2) return 0.0;

                    int[] inicio = convertirCeldaACoordenada(partes[0]);
                    int[] fin = convertirCeldaACoordenada(partes[1]);

                    int filaInicio = Math.min(inicio[0], fin[0]);
                    int filaFin = Math.max(inicio[0], fin[0]);
                    int colInicio = Math.min(inicio[1], fin[1]);
                    int colFin = Math.max(inicio[1], fin[1]);

                    double suma = 0.0;
                    for (int fila = filaInicio; fila <= filaFin; fila++) {
                        for (int col = colInicio; col <= colFin; col++) {
                            NodoDato nodo = matriz.buscarNodo(col, fila);
                            if (nodo != null && nodo.getValor() != null) {
                                Object val = nodo.getValor();
                                if (!(val instanceof String && ((String) val).startsWith("="))) {
                                    suma += parseDouble(val);
                                }
                            }
                        }
                    }

                    return suma;
                } catch (Exception ex) {
                    return 0.0;
                }
            }
        });

        return modelo;
    }
}
