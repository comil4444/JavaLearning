package com.cn.eric.leetcode;

import java.util.Arrays;
import java.util.Stack;

public class RPN {
    public static final String ADD_OPERATOR = "+";
    public static final String MIN_OPERATOR = "-";
    public static final String MUL_OPERATOR = "*";
    public static final String DIV_OPERATOR = "/";


    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length ==0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals(ADD_OPERATOR)) {
                Integer preNum = stack.pop();
                Integer preNum2 = stack.pop();
                stack.push(preNum + preNum2);
            } else if (tokens[i].equals(MIN_OPERATOR)) {
                Integer preNum = stack.pop();
                Integer preNum2 = stack.pop();
                stack.push(preNum - preNum2);
            } else if (tokens[i].equals(MUL_OPERATOR)) {
                Integer preNum = stack.pop();
                Integer preNum2 = stack.pop();
                stack.push(preNum * preNum2);
            } else if (tokens[i].equals(DIV_OPERATOR)) {
                Integer preNum = stack.pop();
                Integer preNum2 = stack.pop();
                stack.push(preNum / preNum2);
            } else {
                Integer curNum = Integer.parseInt(tokens[i]);
                if (curNum < 0) {
                    Integer preNum = stack.pop();
                    stack.push(preNum + curNum);
                } else {
                    stack.push(curNum);
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        System.out.println(new RPN().evalRPN(Arrays.asList("2","1","+","3","*").toArray(new String[]{})));
    }
}