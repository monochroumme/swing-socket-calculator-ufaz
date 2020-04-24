package com.company.server;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpressionSolver {

    private String expression;
    private List<String> list;
    private String solution;
    private boolean integerDivision;

    public void setExpression(String expression) {
        this.expression = expression;
        init();
    }

    public void setIsIntegerDivision(boolean flag) {
        integerDivision = flag;
    }

    private synchronized void init() {
        integerDivision = true;
        list = new ArrayList<String>(Arrays.asList(expression.split(" ")));
    }

    public String solveExpression() {
        try {
            while (list.contains("*") || list.contains("/")) {
                int a = list.indexOf("*");
                int b = list.indexOf("/");
                if (((b > a) || (b <= -1)) && (a >= 0)) {
                    int f = Integer.parseInt(list.get(a - 1));
                    int s = Integer.parseInt(list.get(a + 1));
                    list.set(a - 1, String.valueOf((f * s)));
                    list.remove(a);
                    list.remove(a);
                } else if (b >= 0) {
                    int f = Integer.parseInt(list.get(b - 1));
                    int s = Integer.parseInt(list.get(b + 1));
                    if (integerDivision)
                        list.set(b - 1, String.valueOf((f / s)));
                    else
                        list.set(b - 1, String.valueOf(Math
                                .round((((double) f) / ((double) s)))));
                    list.remove(b);
                    list.remove(b);
                }
            }

            while (list.contains("+") || list.contains("-")) {
                int a = list.indexOf("+");
                int b = list.indexOf("-");
                if (((b > a) || (b <= -1)) && (a >= 0)) {
                    int f = Integer.parseInt(list.get(a - 1));
                    int s = Integer.parseInt(list.get(a + 1));
                    list.set(a - 1, String.valueOf((f + s)));
                    list.remove(a);
                    list.remove(a);
                } else if (b >= 0) {
                    int f = Integer.parseInt(list.get(b - 1));
                    int s = Integer.parseInt(list.get(b + 1));
                    list.set(b - 1, String.valueOf((f - s)));
                    list.remove(b);
                    list.remove(b);
                }
            }
            return solution = listToExpression(list).replace(" ", "");
        } catch (Exception e) {
            solution = expression;
            return solution;
        }
    }

    protected String listToExpression(List<?> list) {
        StringBuilder exp = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i != (list.size() - 1))
                exp.append(list.get(i)).append(" ");
            else
                exp.append(list.get(i));
        }
        return exp.toString();
    }

    @Override
    public String toString() {
        execute();
        return expression + " = " + solution;
    }

    public void execute() {
        solveExpression();
    }
}
