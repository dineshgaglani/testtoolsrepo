package interviewcamp.time1;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ExpressionEvaluator {

    Stack<Character> operatorStack;
    Stack<Integer> operandStack;

    Map<Character, Integer> precedenceMap;

    ExpressionEvaluator() {
        precedenceMap = new HashMap<>();
        precedenceMap.put('*', 2);
        precedenceMap.put('/', 2);
        precedenceMap.put('+', 1);
        precedenceMap.put('-', 1);

        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    /*
        Assuming that only single digit numbers are operands
     */
    public Integer evaluateInfixExpression(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            Character curr = expression.charAt(i);
            if (curr == ' ') {
                continue;
            }
            if (precedenceMap.containsKey(curr)) {
                //It is an operator
                //MISTAKE - this condition should be moved to while because otherwise while will execute even on operator stack empty
//                if (!operatorStack.isEmpty()) {
                //END MISTAKE
                    while (!operatorStack.isEmpty() && precedenceMap.get(operatorStack.peek()) >= precedenceMap.get(curr)) {
                        Integer operand2 = operandStack.pop();
                        Integer operand1 = operandStack.pop();
                        operandStack.push(process(operand1, operand2, operatorStack.pop()));
                    }
//                }
                operatorStack.push(curr);
            } else {
                //It is an operand
                operandStack.push(Integer.parseInt(curr + ""));
            }
        }
        while (!operatorStack.isEmpty()) {
            Integer operand2 = operandStack.pop();
            Integer operand1 = operandStack.pop();
            operandStack.push(process(operand1, operand2, operatorStack.pop()));
        }

        return operandStack.pop();
    }

    public Integer process(Integer operand1, Integer operand2, Character operation) {
        switch (operation) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1/operand2;
        }
        return null;
    }

    public static void main (String args[]) {
        ExpressionEvaluator e = new ExpressionEvaluator();

        //Only 1 operation
        System.out.println(e.evaluateInfixExpression("2+3") + " should equal 5");

        //Same precedence operation 2 times
        System.out.println(e.evaluateInfixExpression("2+3-4")+ " should equal 1");

        //Increasing precedence all the time
        System.out.println(e.evaluateInfixExpression("2+3*5")+ " should equal 17");

        //Decreasing precedence all the time
        System.out.println(e.evaluateInfixExpression("3*5+2")+ " should equal 17");
    }
}

