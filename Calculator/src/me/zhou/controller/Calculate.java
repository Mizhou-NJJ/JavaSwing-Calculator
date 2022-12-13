package me.zhou.controller;

import java.util.Stack;

public class Calculate {
    //数字栈
    private static Stack<Double> numberStack = new Stack<>();
    //符号栈
    private static Stack<Character> symbolStack = new Stack<>();

    /**
     * 计算表达式并计算
     * @param expression
     * @return
     */
    public static String caculate(String expression) {
        numberStack.clear();
        symbolStack.clear();
        expression = removeStrSpace(expression); // 去除空格
// 如果算术表达式尾部没有‘=’号，则在尾部添加‘=’，表示结束符
        if (expression.length() > 1
                && !"=".equals(expression.charAt(expression.length() - 1) + "")) {
            expression += "=";
        }
// 检查表达式是否合法
        if (!isStandard(expression)) {
            for(int i=0;i<expression.length();i++){
                if (!isNumber(expression.charAt(i))) return null;
            }
            return expression;
        }
// 用于缓存数字，因为数字可能是多位的
        StringBuilder temp = new StringBuilder();
// 从表达式的第一个字符开始处理
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i); // 获取一个字符
            if (isNumber(ch)) { // 若当前字符是数字
                temp.append(ch); // 加入到数字缓存中
            } else { // 非数字的情况
                String tempStr = temp.toString(); // 将数字缓存转为字符串
                if (!tempStr.isEmpty()) {

                    numberStack.push(Double.parseDouble(tempStr)); // 将数字压栈
                    temp = new StringBuilder(); // 重置数字缓存
                }
                while (!comparePri(ch) && !symbolStack.empty()) {
                    if (numberStack.size()<2) return null;
                    double b = numberStack.pop();
                    double a = numberStack.pop();
                    switch (symbolStack.pop()) {
                        case '+':
                            numberStack.push(a+b);
                            break;
                        case '-':
                            numberStack.push(a-b);
                            break;
                        case '*':
                            numberStack.push(a*b);
                            break;
                        case '/':
                            numberStack.push(a/b);
                            break;
                        default:
                            break;
                    }
                } // while循环结束
                if (ch != '=') {
                    symbolStack.push(new Character(ch)); // 符号入栈
                    if (ch == ')') { // 去括号
                        symbolStack.pop();
                        symbolStack.pop();
                    }
                }
            }
        } // for循环结束
        return  String.valueOf(numberStack.pop()); // 返回计算结果
    }
    /**

     * 去除字符串中的所有空格

     */
    private static String removeStrSpace(String str) {
        return str != null ? str.replaceAll(" ", "") : "";
    }

    /**

     * 检查算术表达式的基本合法性，符合返回true，否则false

     */

    private static boolean isStandard(String expression) {
        if (expression == null || expression.isEmpty()) // 表达式不能为空
            return false;
        Stack stack = new Stack(); // 用来保存括号，检查左右括号是否匹配
        boolean b = false; // 用来标记'='符号是否存在多个
        for (int i = 0; i < expression.length(); i++) {
            char n = expression.charAt(i);
// 判断字符是否合法
            if (!(isNumber(n) || "(".equals(n + "") || ")".equals(n + "")
                    || "+".equals(n + "") || "-".equals(n + "")
                    || "*".equals(n + "") || "/".equals(n + "") || "=".equals(n
                    + ""))) {
                return false;
            }
// 将左括号压栈，用来给后面的右括号进行匹配
            if ("(".equals(n + "")) {
                stack.push(n);
            }

            if (")".equals(n + "")) { // 匹配括号

                if (stack.isEmpty() || !"(".equals((char) stack.pop() + "")) // 括号是否匹配

                    return false;
            }
// 检查是否有多个'='号
            if ("=".equals(n + "")) {

                if (b)

                    return false;

                b = true;

            }

        }
// 可能会有缺少右括号的情况
        if (!stack.isEmpty())
            return false;
// 检查'='号是否不在末尾
        if (!("=".equals(expression.charAt(expression.length() - 1) + "")))
            return false;
        return true;
    }

    //判断是否是数字
    private static boolean isNumber(char num) {
        if ((num >= '0' && num <= '9') || num == '.')
            return true;
        return false;
    }

    /**
     * 比较优先级：如果当前运算符比栈顶元素运算符优先级高则返回true，否则返回false
     */
    private static boolean comparePri(char symbol) {
        if (symbolStack.empty()) { // 空栈返回ture
            return true;
        }
        char top = symbolStack.peek(); // 查看堆栈顶部的对象，注意不是出栈
        if (top == '(') {
            return true;
        }
        switch (symbol) {
            case '(': // 优先级最高
                return true;
            case '*':
            case '/': {
                if (top == '+' || top == '-') // 优先级比+和-高
                    return true;
                else
                    return false;
            }
            case '+':
                return false;
            case '-':
                return false;
            case ')': // 优先级最低
                return false;
            case '=': // 结束符
                return false;
            default:

                break;
        }
        return true;
    }

}
