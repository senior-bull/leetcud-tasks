package com.vito.eeasy;


import java.util.ArrayDeque;
import java.util.Deque;

public class ValidateParenthesis {

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.addLast(c);
            } else {

                if (stack.isEmpty()) {
                    return false;
                }

                if (c == ')') {

                    char compl = stack.removeLast();
                    if (compl != '(') {
                        return false;
                    }
                } else if (c == ']') {
                    char compl = stack.removeLast();
                    if (compl != '[') {
                        return false;
                    }

                } else if (c == '}') {

                    char compl = stack.removeLast();
                    if (compl != '{') {
                        return false;
                    }
                }
            }
        }

        return stack.isEmpty();
    }
}
