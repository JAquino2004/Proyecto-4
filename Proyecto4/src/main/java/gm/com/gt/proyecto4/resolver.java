/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gm.com.gt.proyecto4;
import org.graalvm.polyglot.*;
import java.util.Map;
/**
 *FUNCION PARA RESOLVER OPERACIONES EN TABLAS
 * @author Joshua
 */
public class resolver {

    private Context context;

    public resolver() {
        // Inicializa el contexto de GraalVM JavaScript
        this.context = Context.newBuilder("js")
                .allowAllAccess(true) // Permite acceso completo para la evaluación
                .build();
    }

    /**
     *Evalua la formula utilizando javascript
     * @param formula La cadena de la formula
     * @param cellValues Un mapa de nombres de celda a sus valores 
     * @return El resultado de la evaluacion
     */
    public Object evaluate(String formula, Map<String, Object> cellValues) {
        if (formula == null || !formula.startsWith("=")) {
            return formula; // No es una fórmula, devuelve el valor tal cual
        }

        // Eliminar el "=" inicial
        String jsExpression = formula.substring(1);

        
        for (Map.Entry<String, Object> entry : cellValues.entrySet()) {
            context.getBindings("js").putMember(entry.getKey(), entry.getValue());
        }

        try {
            // Evaluar la expresión JavaScript
            Value result = context.eval("js", jsExpression);
            // Convertir el resultado a un tipo Java apropiado
            if (result.isNumber()) {
                return result.asDouble();
            } else if (result.isBoolean()) {
                return result.asBoolean();
            } else if (result.isString()) {
                return result.asString();
            }
            // Para otros tipos complejos
            return result.asHostObject(); 
        } catch (PolyglotException e) {
            System.err.println("Error al evaluar fórmula '" + formula + "': " + e.getMessage());
            // Retorna un mensaje de error visual
            return "#ERROR!"; 
        } finally {
            // Limpiar las variables del contexto para la próxima evaluación (opcional pero buena práctica)
            for (String key : cellValues.keySet()) {
                context.getBindings("js").putMember(key, null); // Setear a null o remover
            }
        }
    }

    public void close() {
        if (context != null) {
            context.close();
        }
    }
}
