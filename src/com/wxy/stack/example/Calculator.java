package com.wxy.stack.example;

import com.wxy.stack.Stack;
import com.wxy.stack.impl.ArrayStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>四则运算器</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/10/13 21:43
 */
public class Calculator {

    private static Map<String, Integer> operators = new HashMap<>();
    static {
        operators.put("+", 1);
        operators.put("-", 1);
        operators.put("*", 2);
        operators.put("/", 2);
        operators.put("", -1);
    }

    private static final String ERROR_ZERO = "zero_error";

    private static Pattern pattern = Pattern.compile("\\d+\\.?\\d?");

    private static Stack<String> stack;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            stack = new ArrayStack<>();
            String input = scan.nextLine();
            String suffix = transformInfixToSuffix(input);
            System.out.println(suffix);
            String result = calculateSuffixValue(suffix);
            if (result.equals(ERROR_ZERO)) {
                throw new RuntimeException("除数不能为零");
            }
            System.out.println(result);
        }
    }

    private static String calculateSuffixValue(String suffix) {
        String[] values = suffix.split(" ");
        String result = "";
        for (String str : values) {
            if (isNum(str)) {
                stack.push(str);
            } else {
                Double num2 = Double.valueOf(stack.pop());
                Double num1 = Double.valueOf(stack.pop());
                result = calculate(str, num1, num2);
                stack.push(result);
            }
        }
        return result;
    }

    private static String calculate(String str, Double num1, Double num2) {
        double result = 0;
        switch (str) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (0 == num2) {
                    return ERROR_ZERO;
                }
                result = num1 / num2;
                break;
        }
        return String.valueOf(result);
    }

    /**
     *  将中缀表达式转换为后缀表达式.
     * @param expression 表达式
     * @return 后缀表达式
     */
    private static String transformInfixToSuffix(String expression) {
        StringBuilder infix = new StringBuilder(expression);
        StringBuilder suffix = new StringBuilder();
        String tmp = "";
        while (infix.length() > 0) {
            String nextElement = nextElement(infix);

            if (isNum(nextElement)) {
                suffix.append(nextElement).append(" ");
            } else if (")".equals(nextElement)) {
                tmp = stack.pop();
                while (!"(".equals(tmp)) {
                    suffix.append(tmp).append(" ");
                    tmp = stack.pop();
                }
            } else if ("(".equals(nextElement) || operators.get(nextElement) > operators.get(getTopElement())) {
                stack.push(nextElement);
            } else if (notExistBracket() && operators.get(nextElement) <= operators.get(getBottom())) {
                while (stack.size() > 0) {
                    suffix.append(stack.pop()).append(" ");
                }
                stack.push(nextElement);
            } else if (existBracket() && operators.get(nextElement) <= operators.get(getBottomOperator())) {
                tmp = stack.pop();
                while (!"(".equals(tmp)) {
                    suffix.append(tmp).append(" ");
                    tmp = stack.pop();
                }
                stack.push(nextElement);
            }
        }

        while (stack.size() > 0) {
            suffix.append(stack.pop()).append(" ");
        }
        return suffix.toString();
    }

    private static String getBottom() {
        if (stack.size() > 0) {
            return stack.get(0);
        }
        return "";
    }

    private static String getTopElement() {
        String tmp = "";
        for (int i = stack.size() - 1; i >= 0 ; i--) {
            tmp = stack.get(i);
            if("(".equals(tmp)) {
                break;
            } else if (!isNum(tmp)) {
                return tmp;
            }
        }
        return "";
    }

    private static String getBottomOperator() {
        for (int i = stack.size() - 1; i >= 0; i--) {
            if ("(".equals(stack.get(i))) {
                return stack.get(i + 1);
            }
        }
        return "";
    }

    private static boolean existBracket() {
        for (int i = stack.size() - 1; i >= 0; i--) {
            if ("(".equals(stack.get(i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean notExistBracket() {
        return !existBracket();
    }

    private static String nextElement(StringBuilder infix) {
        StringBuilder result = new StringBuilder();

        char c = infix.charAt(0);
        infix.deleteCharAt(0);
        result.append(c);

        if (isNum(c)) {
            while (infix.length() > 0 && isNum(infix.charAt(0))) {
                result.append(infix.charAt(0));
                infix.deleteCharAt(0);
            }
        }
        return result.toString();
    }

    private static boolean isNum(char c) {
        return (c >= '0' && c <= '9') || c == '.';
    }

    private static boolean isNum(String str) {
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
