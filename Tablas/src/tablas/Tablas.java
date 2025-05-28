package tablas;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Tablas {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tabla 4x4 - Detección de escritura");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Modelo de tabla de 4x4
            DefaultTableModel model = new DefaultTableModel(4, 4);
            JTable table = new JTable(model);

            // Listener para detectar cambios en el modelo
            model.addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    if (e.getType() == TableModelEvent.UPDATE) {
                        int row = e.getFirstRow();
                        int column = e.getColumn();
                        Object value = model.getValueAt(row, column);
                        System.out.println("Dato escrito: \"" + value + "\" en posición (fila " + row + ", columna " + column + ")");
                    }
                }
            });

            // Mostrar tabla
            frame.add(new JScrollPane(table), BorderLayout.CENTER);
            frame.setSize(400, 300);
            frame.setVisible(true);
        });
    }
}
