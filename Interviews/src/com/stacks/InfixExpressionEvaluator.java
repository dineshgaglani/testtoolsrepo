package com.stacks;

import com.singlelist.LinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dgaglani on 5/7/14.
 */
public class InfixExpressionEvaluator {

    Map<String, Integer> operatorPriorityMap;
    LinkedListStack operatorStack = new LinkedListStack();

    public InfixExpressionEvaluator() {
        operatorPriorityMap = new HashMap<String, Integer>();
        operatorPriorityMap.put("(", 6);
        operatorPriorityMap.put("/", 5);
        operatorPriorityMap.put("*", 4);
        operatorPriorityMap.put("+", 3);
        operatorPriorityMap.put("-", 2);
        operatorPriorityMap.put(")", 1);
    }

    public void evaluteInfixExpression(String expression) throws Exception{
        int operand = 0;
        //Insert max number in operator stack, as for the first loop we dont want the operation to be run until we get to the next operator. Putting in a max number will nullify the first
        operatorStack.push(Integer.MAX_VALUE);
        LinkedListStack operandStack = new LinkedListStack();
        LinkedListStack individualOperandStack = new LinkedListStack();
        boolean performOperation = false;
        for(int charCtr = 0; charCtr < expression.length(); charCtr++) {
            char currentCharacter = expression.charAt(charCtr);
            if(operatorPriorityMap.containsKey(Character.toString(currentCharacter))) {
                //We have reached an operator, the operand has ended, number-ify it
                operand = getNumberInsideStack(individualOperandStack);
                individualOperandStack.emptyStack();
                operandStack.push(operand);
                //Perform the operation if it has to be performed
                if(performOperation == true) {
                    //Pop operator
                    int operator = operatorStack.peek().value;
                    operatorStack.pop();
                    //get operands
                    int operand1 = operandStack.peek().value;
                    operandStack.pop();
                    int operand2 = operandStack.peek().value;
                    operandStack.pop();
                    operandStack.push(getResultOfOperation(operator, operand1, operand2));
                }
                //We have the operand, checking operator priority
                int currentCharacterPriority = operatorPriorityMap.get(Character.toString(currentCharacter));
                int priorityOfTopCharacterInStack = operatorStack.peek().value;
                if(currentCharacterPriority > priorityOfTopCharacterInStack) {
                    //Current operator priority is greater than top element of operand stack, put inside stack, set the performOperation boolean and get the next operand and perform operation.
                    /*if(performOperation == true) {
                        performOperation = false;
                        //Pop operator
                        int operator = operatorStack.peek();
                        operatorStack.pop();
                        //get operands
                        int operand1 = operandStack.peek();
                        operandStack.pop();
                        int operand2 = operandStack.peek();
                        operandStack.pop();
                        operandStack.push(getResultOfOperation(operator, operand1, operand2));
                    } else {*/
                        performOperation = true;
                        operatorStack.push(operatorPriorityMap.get(Character.toString(currentCharacter)));
                    //}

                } else {
                    //Current priority lesser than last value in stack, push into operand stack
                    operatorStack.push(operatorPriorityMap.get(Character.toString(currentCharacter)));
                    performOperation = false;
                }

            } else {
                //This is a number, feed it inside the operand stack
                individualOperandStack.push(Integer.parseInt(Character.toString(currentCharacter)));
            }
        }
    }

    public int getResultOfOperation(int operand1, int operand2, int operator) {
         switch (operator) {
             case 2 : return  operand1/operand2;
             case 3 : return operand1*operand2;
             case 4 : return operand1+operand2;
             case 5 : return operand1-operand2;
             default : return 0;
         }
    }

    public int getNumberInsideStack(LinkedListStack individualOperandStack) throws Exception {
        int tensExponentCounter = 0;
        int result = 0;
         while(individualOperandStack.stackSize > 0) {
            //Last number is at units place, second at 10s place and so on
             int x  = individualOperandStack.peek().value;
             individualOperandStack.pop();
             int decimalPlaceNumber = new Double(x * Math.pow(10, tensExponentCounter)).intValue();
             tensExponentCounter++;
             result = result + decimalPlaceNumber;
         }
        return result;
    }

    public static void main(String args[]) throws Exception{
        InfixExpressionEvaluator infixEvaluator = new InfixExpressionEvaluator();
        infixEvaluator.evaluteInfixExpression("10+5*8");
    }
}
