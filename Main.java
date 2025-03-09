import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] variables;
        String[] expressions;
        String respuesta;

        do {
            // Mostrar el menú de operadores lógicos
            System.out.println("Ingrese la expresión lógica utilizando los siguientes símbolos:");
            System.out.println("Conjunción = /");
            System.out.println("Disyunción = *");
            System.out.println("Condicional = !=");
            System.out.println("Bicondicional = ==");
            System.out.println("Disyunción excluyente =!");

            // Solicitar las variables al usuario
            while (true) {
                System.out.println("Ingrese las variables (separadas por comas): ");
                variables = scanner.nextLine().replace(" ", "").split(",");

                // Validar el número de variables
                if (variables.length > 3) {
                    System.out.println("Error: El número máximo de variables es 3.");
                    continue;
                }
                boolean valid = true;
                for (String variable: variables) {
                    try{
                        int variable_cast = Integer.parseInt(variable);
                            System.out.println("Error: Las variables deben ser una letra.");
                            valid = false;
                            break;
                    }
                    catch (NumberFormatException e){
                        continue;
                    }
                }
                // Validar que las variables no estén vacías
                for (String variable : variables) {
                    if (variable.isEmpty()) {
                        System.out.println("Error: Las variables no pueden estar vacías.");
                        valid = false;
                        break;
                    
                    }
                }
                if (valid) break;
            }

            // Solicitar las expresiones lógicas al usuario
            while (true) {
                System.out.println("Ingrese las expresiones lógicas (separadas por comas): ");
                expressions = scanner.nextLine().replace(" ", "").split(",");

                // Validar el número de expresiones
                if (expressions.length > 3) {
                    System.out.println("Error: El número máximo de expresiones es 3.");
                    continue;
                }

                // Validar que las expresiones no estén vacías
                boolean valid = true;
                for (String expression : expressions) {
                    if (expression.isEmpty()) {
                        System.out.println("Error: Las expresiones no pueden estar vacías.");
                        valid = false;
                        break;
                    }
                }
                List<String> listaVariables = Arrays.asList(variables); 
                System.out.println(listaVariables);
                for (String expression : expressions) {
                    for (String expression_ : expression.split("")) {
                        String grupo = "/*=!"; // Grupo de caracteres permitidos

                        if (grupo.indexOf(expression_) != -1) { // Verifica si el caracter está en el grupo
                            continue;
                        
                        } 
                        if (listaVariables.contains(expression_)) { // Verifica si la variable está en el grupo
                            continue;
                        }
                        
                        else {
                            System.out.println(expression_);
    
                            System.out.println("Error: Solo puedes utilizar las proposiciones que colocaste");
                            valid = false;
                            break;
                        }   
                    }
                }

                if (valid) break;
            }

            // Generar y mostrar la tabla de verdad
            TruthTable.printTruthTable(variables, expressions);

            // Preguntar si desea realizar otra operación
            System.out.println("¿Desea realizar otra operación? (s/n): ");
            respuesta = scanner.nextLine().trim().toLowerCase();
        } while (respuesta.equals("s"));

        scanner.close();
    }
}