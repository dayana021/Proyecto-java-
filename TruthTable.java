import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TruthTable {
    public static void printTruthTable(String[] variables, String[] expressions) {
        List<boolean[]> truthTable = generateTruthTable(variables.length);
        List<String> headers = new ArrayList<>(Arrays.asList(variables));
        headers.addAll(Arrays.asList(expressions));

        System.out.println(String.join(" | ", headers));
        System.out.println("-".repeat(4 * headers.size() - 1));

        for (boolean[] values : truthTable) {
            List<String> row = new ArrayList<>();
            for (boolean value : values) {
                row.add(value ? "1" : "0");
            }
            for (String expr : expressions) {
                boolean result = ExpressionEvaluator.evaluateExpression(expr, variables, values);
                row.add(result ? "1" : "0");
            }
            System.out.println(String.join(" | ", row));
        }
    }

    private static List<boolean[]> generateTruthTable(int numVariables) {
        List<boolean[]> table = new ArrayList<>();
        int numRows = (int) Math.pow(2, numVariables);
        
        for (int i = 0; i < numRows; i++) {
            boolean[] row = new boolean[numVariables];
            for (int j = 0; j < numVariables; j++) {
                row[j] = (i & (1 << (numVariables - j - 1))) != 0;
            }
            table.add(row);
        }

        return table;
    }
}