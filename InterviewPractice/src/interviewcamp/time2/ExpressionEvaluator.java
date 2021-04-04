package interviewcamp.time2;

import java.util.HashMap;
import java.util.Stack;

public class ExpressionEvaluator {

    Stack<Character> operatorStack;
    Stack<Integer> operandStack;

    HashMap<Character, Integer> precedenceMap;

    public ExpressionEvaluator() {
        precedenceMap = new HashMap<>();
        precedenceMap.put('+', 1);
        precedenceMap.put('-', 1);
        precedenceMap.put('*', 2);
        precedenceMap.put('/', 2);

        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    public Integer evaluateExpression(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            Character curr = expression.charAt(i);
            if (curr == ' ') {
                continue;
            }
            if (precedenceMap.containsKey(curr)) {
                //It is an operator
                while(!operatorStack.isEmpty() && precedenceMap.get(operatorStack.peek()) >= precedenceMap.get(curr)) {
                    Integer operand2 = operandStack.pop();
                    Integer operand1 = operandStack.pop();
                    Character operator = operatorStack.pop();
                    operandStack.push(process(operand1, operand2, operator));
                }
                operatorStack.push(curr);
            } else {
                operandStack.push(Integer.parseInt(curr + ""));
            }
        }

        while(!operatorStack.isEmpty()) {
            Integer operand2 = operandStack.pop();
            Integer operand1 = operandStack.pop();
            Character operator = operatorStack.pop();
            operandStack.push(process(operand1, operand2, operator));
        }

        return operandStack.pop();
    }

    private Integer process(Integer operand1, Integer operand2, Character operator) {
        switch(operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
        }
        return null;
    }

    public static void main (String args[]) {
        ExpressionEvaluator e = new ExpressionEvaluator();

        //Only 1 operation
        System.out.println(e.evaluateExpression("2+3") + " should equal 5");

        //Same precedence operation 2 times
        System.out.println(e.evaluateExpression("2+3-4")+ " should equal 1");

        //Increasing precedence all the time
        System.out.println(e.evaluateExpression("2+3*5")+ " should equal 17");

        //Decreasing precedence all the time
        System.out.println(e.evaluateExpression("3*5+2")+ " should equal 17");
    }
}
