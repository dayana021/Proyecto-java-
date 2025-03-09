

public class ExpressionEvaluator {
    public static boolean evaluateExpression(String expression, String[] variables, boolean[] values) {
        for (int i = 0; i < 2; i++) {
            expression = expression.replace(variables[i], String.valueOf(values[i]));
        }
        
        // Reemplazar conectores lógicos por operadores Java
        expression = expression.replace("*", "||") // Disyunción
                            .replace("/", "&&") // Conjunción
                            .replace("!=", "->") // Condicional
                            .replace("==", "<->") // Bicondicional
                            .replace("!", "^"); // Disyunción excluyente
        
        try {
            return eval(expression);
        } catch (Exception e) {
            System.out.println("Error al evaluar la expresión '" + expression + "': " + e.getMessage());
            return false;
        }
    }

    // Evaluador simple para expresiones lógicas
    private static boolean eval(String expression) throws Exception {
        if (expression.contains("||")) {
            String[] parts = expression.split("\\|\\|");
            return eval(parts[0].trim()) || eval(parts[1].trim()); // Disyunción
        }
        if (expression.contains("&&")) { 
            String[] parts = expression.split("&&");
            return eval(parts[0].trim()) && eval(parts[1].trim()); // Conjunción
        }
        if (expression.contains("<->")) {
            String[] parts = expression.split("<->");
            return eval(parts[0].trim()) == eval(parts[1].trim()); // Bicondicional
        }
        if (expression.contains("->")) {
            String[] parts = expression.split("->");
            return !eval(parts[0].trim()) || eval(parts[1].trim()); // Condicional
        }
        if (expression.contains("^")) {
            String[] parts = expression.split("\\^");
            return eval(parts[0].trim()) ^ eval(parts[1].trim()); // Disyunción excluyente
        }
        if (expression.startsWith("!")) {
            return !eval(expression.substring(1).trim()); // Negación
        }
        return Boolean.parseBoolean(expression);
    }
}
