package linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stack<T> {

    SingleLinkedList sl = new SingleLinkedList();
    private SingleLinkedList.Node top;

    public SingleLinkedList.Node<T> push(T content) {

        if (top == null) {
            top = sl.new Node<>(content);
        } else {
            SingleLinkedList.Node node = sl.new Node(content);
            node.next = top;
            top = node;
        }

        return top;

    }

    public T peek() {
        if (top != null)
            return (T)top.content;
        return null;
    }

    public T pop() {
        SingleLinkedList.Node origTopNode = top;
        if (top != null) {
            top = top.next;
            return (T)origTopNode.content;
        }
        return null;
    }

    class ExpressionEvaluator  {
        List<String> operators = Arrays.asList(new String[] {"/", "*", "-", "+"} );
        Stack<String> operatorStack = new Stack();
        Stack<Integer> operandStack = new Stack();

        private boolean isOperatorHigherPriority(String op1, String op2) {
           return operators.indexOf(op1) < operators.indexOf(op2);
        }

        private boolean isOperator(String operator) {
            return operators.contains(operator);
        }

        private Integer operate (Integer rightOperand, Integer leftOperand, String operator) {
            if(rightOperand!= null) {
                if (operator.equals("+")) {
                    return leftOperand + rightOperand;
                } else if (operator.equals("-")) {
                    return leftOperand - rightOperand;
                } else if (operator.equals("*")) {
                    return leftOperand * rightOperand;
                } else if (operator.equals("/")) {
                    return leftOperand / rightOperand;
                }
            } else {
                return leftOperand;
            }

            return null;
        }

        private void process(Stack<Integer> operandStack, String operator) {
            Integer rightOperand = operandStack.pop();
            Integer leftOperand = operandStack.pop();

            operandStack.push(operate(rightOperand, leftOperand, operator));
        }

        public int evaluatePostFix(String expression) {
            for (int i = 0; i < expression.length(); i++) {
                String character = expression.charAt(i) + "";
                if (isOperator(character)) {
                    process(operandStack, character);
                }
                else {
                    //Assuming character is a number
                    operandStack.push(Integer.parseInt(character));
                }
            }
            return operandStack.pop();
        }

        public int evaluateInfix(String expression) {
            // Our end-game is that we need to have the operand stack in decreasing order of priority (highest priority
            // on top), if we have find something with higher priority earlier than the current element then we process it
            // and go on processing until we find a smaller element, then we put the current element above it.
            // If the current one is higher priority than the one in the stack we move ahead because the one after
            // this may be even higher priority and that needs to be processed first
            // 3 + 2 * 8
            // 3 * 8 + 2
            // TODO - add paranthesis evaluation

            for (int i = 0; i < expression.length(); i++) {
                String character = expression.charAt(i) + "";
                if (isOperator(character)) {
                    //Check precedence, if operator in stack has higher precedence process that first, if lower precedence then push into stack
                    String operatorFromStack = operatorStack.peek();

                    if(operatorFromStack != null) {
                        if(isOperatorHigherPriority(character, operatorFromStack)) {
                            //When we find something of higher priority (current character) than the last operator (operator inside the stack),
                            // then don't process because anything yet, because we may find something
                            // ahead which is of higher priority and so we can't process it's
                            // left operand (which will be this current operand's right)
                            operatorStack.push(character);
                        } else {
                            // When we find an operand that has lower priority from the one in the stack,
                            // we can process the one in the stack because we will anyway be storing this in
                            // our stack and if the next one is lower we will process this, if higher we will go ahead
                            while (operatorFromStack != null && isOperatorHigherPriority(operatorFromStack, character)) {
                                process(operandStack, operatorFromStack);
                                operatorStack.pop();
                                operatorFromStack = operatorStack.peek();
                            }
                            //After we process all higher than this operators we put this into the stack and continue,
                            //this will be processed when we see a lower priority operator ahead
                            operatorStack.push(character);
                        }
                    } else {
                        //Operator stack is null, put it in operator stack
                        operatorStack.push(character);
                    }
                } else {
                    operandStack.push(Integer.parseInt(character));
                }
            }

            processAll(operandStack, operatorStack);
            return operandStack.pop();
        }

        public int evaluateInfixSimpler(String expression) {
            // Our end-game is that we need to have the operand stack in decreasing order of priority (highest priority
            // on top), if we have find something with higher priority than it's next element then we process it.
            // If the next one is higher priority we move ahead because the one after this may be even higher priority
            // and that needs to be processed
            // first
            // 3 + 2 * 8
            // 3 * 8 + 2
            // TODO - add paranthesis evaluation

            for (int i = 0; i < expression.length(); i++) {
                String character = expression.charAt(i) + "";
                if (isOperator(character)) {
                    //Check precedence, if operator in stack has higher precedence process that first, if lower precedence then push into stack
                    String operatorFromStack = operatorStack.peek();
                    while (operatorFromStack != null && isOperatorHigherPriority(operatorFromStack, character)) {
                        process(operandStack, operatorFromStack);
                        operatorStack.pop();
                        operatorFromStack = operatorStack.peek();
                    }
                    operatorStack.push(character);
                } else {
                    operandStack.push(Integer.parseInt(character));
                }
            }

            processAll(operandStack, operatorStack);
            return operandStack.pop();
        }

        private void processAll(Stack<Integer> operandStack, Stack<String> operatorStack) {
            while (operatorStack.peek() != null) {
                process(operandStack, operatorStack.pop());
            }
        }
    }

    public static void main (String args[]) {
        Stack s = new Stack();
        String postfixExp = "32+45+*";
        Stack.ExpressionEvaluator expressionEvaluator = s.new ExpressionEvaluator();
        int postFixRes = expressionEvaluator.evaluatePostFix(postfixExp);
        System.out.println(postFixRes == 45);


        String infixIncreasingPriorityExp = "2+3*6/2";
        int infixIncreasingOrder = expressionEvaluator.evaluateInfixSimpler(infixIncreasingPriorityExp);
        System.out.println(infixIncreasingOrder == 11);

        String infixDecreasingPriorityExp = "6/3*2+1";
        int infixDecreasingOrder = expressionEvaluator.evaluateInfixSimpler(infixDecreasingPriorityExp);
        System.out.println(infixDecreasingOrder == 5);

        String infixRandomOrderExp = "6*4/2+1";
        int infixRandomOrder = expressionEvaluator.evaluateInfixSimpler(infixRandomOrderExp);
        System.out.println(infixRandomOrder == 13);

        String infixRepeatedOperatorExp = "6*4/2*1";
        int infixRepeatedOperator = expressionEvaluator.evaluateInfixSimpler(infixRepeatedOperatorExp);
        System.out.println(infixRepeatedOperator == 12);
    }
}
