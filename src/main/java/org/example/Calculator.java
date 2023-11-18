import java.util.Scanner;

//public class Calculator {
//    /**
//     *
//     * @param str задаваемое пользователем выражение
//     * @return Object()
//     */
//    public static double eval(final String str) {
//        return new Object() {
//            int pos = -1, ch;
//
//            void nextChar() {
//                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
//            }
//
//            boolean eat(int charToEat) {
//                while (ch == ' ') nextChar();
//                if (ch == charToEat) {
//                    nextChar();
//                    return true;
//                }
//                return false;
//            }
//
//            double parse() {
//                nextChar();
//                double x = parseExpression();
//                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
//                return x;
//            }
//
//
//            double parseExpression() {
//                double x = parseTerm();
//                for (;;) {
//                    if      (eat('+')) x += parseTerm(); // addition
//                    else if (eat('-')) x -= parseTerm(); // subtraction
//                    else return x;
//                }
//            }
//
//            double parseTerm() {
//                double x = parseFactor();
//                for (;;) {
//                    if      (eat('*')) x *= parseFactor(); // multiplication
//                    else if (eat('/')) x /= parseFactor(); // division
//                    else return x;
//                }
//            }
//
//            double parseFactor() {
//                if (eat('+')) return +parseFactor(); // unary plus
//                if (eat('-')) return -parseFactor(); // unary minus
//
//                double x;
//                int startPos = this.pos;
//                if (eat('(')) { // parentheses
//                    x = parseExpression();
//                    if (!eat(')')) throw new RuntimeException("Missing ')'");
//                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
//                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
//                    x = Double.parseDouble(str.substring(startPos, this.pos));
//                } else if (ch >= 'a' && ch <= 'z') { // functions
//                    while (ch >= 'a' && ch <= 'z') nextChar();
//                    String func = str.substring(startPos, this.pos);
//                    if (eat('(')) {
//                        x = parseExpression();
//                        if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
//                    } else {
//                        x = parseFactor();
//                    }
//                    if (func.equals("sqrt")) x = Math.sqrt(x);
//                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
//                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
//                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
//                    else throw new RuntimeException("Unknown function: " + func);
//                } else {
//                    throw new RuntimeException("Unexpected: " + (char)ch);
//                }
//
//                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation
//
//                return x;
//            }
//        }.parse();
//    }
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Введите выражение: ");
//        String expression = scanner.nextLine();
//
//        try {
//            double result = eval(expression);
//            System.out.println("Результат: " + result);
//        } catch (IllegalArgumentException e) {
//            System.out.println("Ошибка: " + e.getMessage());
//        }
//    }
//}
///**
// * Класс для вычисления арифметических выражений.
// * Она выполняет сложение, вычитание, умножение, деление, возведение в степень
// * @autor Toloknova Evgenia
//*/
//public class Calculator {
//
//    /**
//     * Функция удаления лишних пробелов в выражении.
//     * @param expression - выражение
//     * @return
//     */
//    public static String removeSpaces(String expression) {
//        StringBuilder result = new StringBuilder();
//
//        for (int i = 0; i < expression.length(); i++) {
//            if (expression.charAt(i) != ' ') {
//                result.append(expression.charAt(i));
//            }
//        }
//        return result.toString();
//    }
//
//    /**
//     *
//     * @param expression выражение
//     * @return
//     */
//    public static double evaluateExpression(String expression) {
//
//        expression = removeSpaces(expression);
//        if (expression.matches(".*[a-zA-Z]+.*")) {
//
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Введите значения переменных:");
//
//            // Замена имен переменных на их значения в выражении
//            String[] variables = expression.replaceAll("[^a-zA-Z]", "").split("");
//            for (String variable : variables) {
//                System.out.print(variable + ": ");
//                double value = scanner.nextDouble();
//                expression = expression.replaceAll(variable, Double.toString(value));
//            }
//        }
//
//        // Проверка, что выражение содержит только допустимые символы
//        if (!expression.matches("[0-9+\\-*/^()%+]")) {
//            throw new IllegalArgumentException("Недопустимые символы в выражении");
//        }
//
//        // Проверка, что выражение содержит правильную структуру скобок
//        if (!HowManyParentheses(expression)) {
//            throw new IllegalArgumentException("Неправильная структура скобок в выражении");
//        }
//
//        // Вычисление значения выражения
//        return evaluateExpressionRecursively(expression);
//    }
//
//
//    /**
//     * Функция проверки нужного количества скобок
//     * @param expression выражение
//     * @return проверка
//     */
//    private static boolean HowManyParentheses(String expression) {
//        int count = 0;
//
//        for (char ch : expression.toCharArray()) {
//            if (ch == '(') {
//                count++;
//            } else if (ch == ')') {
//                count--;
//                if (count < 0) {
//                    return false;
//                }
//            }
//        }
//
//        return count == 0;
//    }
//
//    private static double evaluateExpressionRecursively(String expression) {
//
//
//
//        // Базовый случай: если выражение содержит только число, вернуть его значение
//        if (expression.matches("[0-9.]+")) {
//            return Double.parseDouble(expression);
//        }
//
//        // Если выражение содержит только одну пару скобок, удалить их и рекурсивно вычислить значение выражения внутри скобок
//        if (expression.startsWith("(") && expression.endsWith(")")) {
//            return evaluateExpressionRecursively(expression.substring(1, expression.length() - 1));
//        }
//
//        // Поиск самого вложенного оператора (если выражение содержит несколько операторов)
//        int numParentheses = 0;
//        int operatorIndex = -1;
//        char operatorChar = ' ';
//        for (int i = 0; i < expression.length(); i++) {
//            char ch = expression.charAt(i);
//            if (ch == '(') {
//                numParentheses++;
//            } else if (ch == ')') {
//                numParentheses--;
//            } else if (numParentheses == 0 && isOperator(ch)) {
//                operatorIndex = i;
//                operatorChar = ch;
//            }
//        }
//
//        // Рекурсивное вычисление значений операндов и выполнение операции
//        double leftOperand = evaluateExpressionRecursively(expression.substring(0, operatorIndex));
//        double rightOperand = evaluateExpressionRecursively(expression.substring(operatorIndex + 1));
//
//        switch (operatorChar) {
//            case '+':
//                return leftOperand + rightOperand;
//            case '-':
//                return leftOperand - rightOperand;
//            case '*':
//                return leftOperand * rightOperand;
//            case '/':
//                return leftOperand / rightOperand;
//            case '^':
//                return Math.pow(leftOperand, rightOperand);
//            default:
//                throw new IllegalArgumentException("Недопустимая операция: " + operatorChar);
//        }
//    }
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Введите выражение: ");
//        String expression = scanner.nextLine();
//
//        try {
//            double result = evaluateExpression(expression);
//            System.out.println("Результат: " + result);
//        } catch (IllegalArgumentException e) {
//            System.out.println("Ошибка: " + e.getMessage());
//        }
//    }
//
//    private static boolean isOperator(char ch) {
//        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
//    }
//}

//package com.company;

import java.util.Objects;
import java.util.Stack;

/**
 * A class that evaluates the value of an expression.
 */
public class Calculator {

    /**
     * Expression containing numbers, operators (+,-,*,/), brackets, spaces, other characters.
     */
    private String Expression;

    /**
     * Constructor of the Calculator class
     * @param str Expression
     */
    Calculator(String str) {
        this.Expression = str;
    }

    /**
     * Method for removing spaces from an expression
     */
    private void delSpace()
    {
        StringBuilder newstr = new StringBuilder();
        for (int pos = 0; pos < Expression.length(); pos++)
            if (Expression.charAt(pos) != ' ')
                newstr.append(Expression.charAt(pos));
        Expression = newstr.toString();
    }

    /**
     * The method of checking the expression for correctness
     * @return True if the expression is true, otherwise false
     */
    private boolean correctness()
    {
        if (Expression.isEmpty()) return false;
        else {
            delSpace();
            int bracket = 0;

            for (int pos = 0; pos < Expression.length(); pos++) {

                if (bracket >= 0) {

                    switch (Expression.charAt(pos)) {

                        case '+': case '-': case '*': case '/': {
                            if(pos == 0 || pos == Expression.length() - 1) return false;
                            else
                            if (Expression.charAt(pos+1) == '+' || Expression.charAt(pos+1) == '-' || Expression.charAt(pos+1) == '*' || Expression.charAt(pos+1) == '/' || Expression.charAt(pos+1) == ')')
                                return false;
                            break;
                        }

                        case '(': {
                            bracket++;
                            if (Expression.charAt(pos+1) == '+' || Expression.charAt(pos+1) == '-' || Expression.charAt(pos+1) == '*' || Expression.charAt(pos+1) == '/' ||  Expression.charAt(pos+1) == ')')
                                return false;
                            else if (pos == Expression.length() - 1) return false;
                            break;
                        }

                        case ')': {
                            bracket--;
                            if (pos == 0) return false;
                            else if (Expression.charAt(pos-1) == '+' || Expression.charAt(pos-1) == '-' || Expression.charAt(pos-1) == '*' || Expression.charAt(pos-1) == '/' || Expression.charAt(pos-1) == '(' )
                                return false;
                            break;
                        }

                        default:
                            if (Expression.charAt(pos) >= '0' && Expression.charAt(pos) <= '9') {
                                if (pos != 0)
                                    if (Expression.charAt(pos-1) == ')' )
                                        return false;
                                if (pos != Expression.length()-1)
                                    if (Expression.charAt(pos+1) == '(' )
                                        return false;
                            }
                            else return false;
                    }
                }
                else return false;
            }
            return bracket == 0;
        }
    }

    /**
     * A method that determines the operator's priority.
     * @param ch Symbol (operator, bracket)
     * @return Symbol priority
     */
    private int priority(char ch) {
        if (ch == '*' || ch == '/')
            return 3;
        else if (ch == '+' || ch == '-')
            return 2;
        else if (ch == '(')
            return 1;
        else if (ch == ')')
            return -1;
        return 0;
    }

    /**
     * A method that overwrites an expression into a postfix form.
     * @return True if it was possible to write, otherwise false.
     */
    private boolean postfixNotation() {

        if (!correctness() || Expression.isEmpty())
            return false;

        else {
            Stack<Character> charstack = new Stack<Character>();
            StringBuilder newstr = new StringBuilder();

            for (int pos = 0; pos < Expression.length(); pos++) {
                int typeoper = priority(Expression.charAt(pos));

                if (typeoper == 0) newstr.append(Expression.charAt(pos));

                else if (typeoper == 1) charstack.push(Expression.charAt(pos));

                else if (typeoper > 1) {
                    newstr.append(' ');

                    while (!charstack.empty()) {
                        if (priority(charstack.peek()) >= typeoper)
                            newstr.append(charstack.pop());
                        else break;
                    }

                    charstack.push(Expression.charAt(pos));
                }

                else if (typeoper == -1) {
                    newstr.append(' ');

                    while (priority(charstack.peek()) != 1)
                        newstr.append(charstack.pop());

                    charstack.pop();
                }
            }

            while (!charstack.empty()) newstr.append(charstack.pop());
            Expression = newstr.toString();
            return true;
        }
    }

    /**
     * A method that reads the value of an expression written in postfix form and writes the result in the field str.
     * @return True if it was calculated, otherwise false.
     */
    public boolean calculate() {

        boolean t = postfixNotation();
        if (!t) return false;
        else {

            StringBuilder res = new StringBuilder();
            Stack<Double> st = new Stack<Double>();

            for (int pos = 0;pos < Expression.length(); pos++) {

                if (Expression.charAt(pos) == ' ') continue;

                if (priority(Expression.charAt(pos)) == 0){

                    while (Expression.charAt(pos) != ' ' && priority(Expression.charAt(pos)) == 0) {
                        res.append(Expression.charAt(pos++));
                        if (pos == Expression.length()) break;
                    }

                    st.push(Double.parseDouble(res.toString()));
                    res = new StringBuilder();
                }

                if (priority(Expression.charAt(pos)) > 1) {

                    double num1 = st.pop();
                    double num2 = st.pop();

                    if (Expression.charAt(pos) == '+')
                        st.push(num2 + num1);

                    if (Expression.charAt(pos) == '-')
                        st.push(num2 - num1);

                    if (Expression.charAt(pos) == '*')
                        st.push(num2 * num1);

                    if (Expression.charAt(pos) == '/')
                        st.push(num2 / num1);
                }
            }
            Expression = Double.toString(st.pop());
            return true;
        }
    }

    /**
     * The method that displays the value of the field str on the screen.
     */
    @Override
    public String toString() {
        return Expression;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Calculator that = (Calculator) obj;
        return Objects.equals(Expression, that.Expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Expression);
    }

}