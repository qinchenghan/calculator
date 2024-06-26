package calculator6;
import java.util.*;
public class Main {
    private final static List<String> opList = new ArrayList<>(Arrays.asList("+", "-", "*", "/", "%", "^", "(", ")", "!", "sqrt(", "sin(", "cos(", "tan(", "log(", "ln("));
    private final static List<String> parenList = new ArrayList<>(Arrays.asList( //4! = 24 = 1 * 2 * 3 * 4  5! = 1 * 2 * 3 * 4 * 5 = 120  10 ^ 2 = 100
                                                                                            // pi = 3.14..... 
                                                                                            // e = 2.71.........                  cos(pi) = -1 sin(pi) = 0    log_10(100) = 2 ln() = log_e() log_2(8) = 3 log_2(13) = 
        "(", "sqrt(", "sin(", "cos(", "tan(", "log(", "ln("));
    public static boolean isInt(String str)
    {
        if (!Character.isDigit(str.charAt(0)) && !(str.charAt(0) == '-'))
        {
            return false;
        }
        if (str.charAt(str.length() - 1) == '-')

        {
            return false;
        }
        for (int i = 1; i < str.length(); i++)
        {
            if (!Character.isDigit(str.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    public static boolean isDouble(String str)
    {
        if (isInt(str))
        {
            return true;
        }
        if (!Character.isDigit(str.charAt(0)) && !(str.charAt(0) == '-') && !(str.charAt(0) == '.'))
        {
            return false;
        }
        if (str.charAt(str.length() - 1) == '.' || str.charAt(str.length() - 1) == '-')
        {
            return false;
        }
        boolean hasdot = false;
        for (int i = 0; i < str.length(); i++)
        {
            if (i == 0)
            {
                if (!Character.isDigit(str.charAt(i)) && !(str.charAt(i) == '.') && !(str.charAt(i) == '-'))
                {
                    return false;
                }
                if (str.charAt(i) == '.')
                {
                    hasdot = true;
                }
            }
            else
            {        
                if (!Character.isDigit(str.charAt(i)) && !(str.charAt(i) == '.'))
                {
                    return false;
                }
                if (str.charAt(i) == '.')
                {
                    if (hasdot)
                    {
                        return false;
                    }
                    else
                    {
                        hasdot = true;
                    }
                }
            }
        }
        return true;
    }

    public static int nonNegativeMin(int a, int b)
    {
        if (a < 0 && b < 0)
        {
            throw new ArithmeticException();
        }
        if (a < 0) return b;
        else if (b < 0) return a;
        else return Math.min(a, b);
    }

    public static boolean overlap(List<String> list1, List<String> list2)
    {
        for (int i = 0; i < list1.size(); i++)
        {
            if (list2.contains(list1.get(i)))
            {
                return true;
            }
        }
        return false;
    }

    public static Double factorial(Double a)
    {
        if (a < 0 || a % 1 != 0)
        {
            throw new ArithmeticException();
        }
        Double res = 1.0;
        for (Double i = 1.0; i <= a; i++)
        {
            res *= i;
        }
        return res;
    }

    public static boolean matchingParen(List<String> list)
    {
        int res = 0;
        for (int i  = 0; i < list.size(); i++)
        {
            String str = list.get(i);
            if (parenList.contains(str))
            {
                res++;
            }
            else if (str.equals(")"))
            {
                res--;
            }
        }
        if (res == 0) return true;
        else return false;
    }

    public static boolean validInput(List<String> list)
    {
        System.out.println("Validating Input");
        boolean hasOp = false;
        for (int i = 0; i < list.size(); i++)
        {
            String str = list.get(i);
            if (opList.contains(str))
            {
                hasOp = true;
                continue;
            }
            if (!isDouble(str))
            {
                System.out.println("Validation failure");
                return false;
            }
        }
        if (list.size() > 1)
        {
            System.out.println("Validation is " + hasOp);
            return hasOp;
        }
        System.out.println("Validation successful");
        return true;
    }

    public static List<String> handleFactorial(List<String> list, int index) throws Exception
    {
        if (index == list.size() - 1)
        {
            throw new ArithmeticException();
        }
        double num1;
        try {
            num1 = Double.parseDouble(list.get(index + 1));
        } catch (ArithmeticException e) {
            throw new ArithmeticException();
        } catch (Exception e) {
            throw new Exception();
        }
        double num = factorial(num1);
        list.set(index, Double.toString(num));
        list.remove(index + 1);
        return list;
    }

    public static List<String> handleBasicOperation(List<String> list, int index) throws Exception
    {
        if (index == 0 || index == list.size() - 1)
        {
            throw new ArithmeticException();
        }
        String op = list.get(index);
        double num1;
        double num2;
        double num;
        try {
            num1 = Double.parseDouble(list.get(index - 1));
            num2 = Double.parseDouble(list.get(index + 1));
        } catch (ArithmeticException e) {
            throw new ArithmeticException();
        } catch (Exception e) {
            throw new Exception();
        }
        switch (op) {
            case "+":
                num = num1 + num2;
                break;
            case "-":
                num = num1 - num2;
                break;
            case "*":
                num = num1 * num2;
                break;
            case "/":
                if (num2 == 0)
                {
                    throw new ArithmeticException();
                }
                num = num1 / num2;
                break;
            case "%":
                if (num2 == 0)
                {
                    throw new ArithmeticException();
                }
                num = num1 % num2;
                break;
            case "^":
                num = Math.pow(num1, num2);
                break;
            default:
                throw new ArithmeticException();
        }
        list.set(index, Double.toString(num));
        list.remove(index + 1);
        list.remove(index - 1);
        return list;
    }

    public static List<String> handleParenthesis(List<String> list, int index) throws Exception
    {
        String op = list.get(index);
        if (op.equals("("))
        {
            List<String> list1 = new ArrayList<>(list.subList(0, index));
            List<String> list2 = new ArrayList<>();
            list2.add(Double.toString(solveExpression(list.subList(index + 1, list.size()))));
            list1.addAll(list2);
            list = list1;
        }
        else if (op.equals(")"))
        {
            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>(list.subList(index + 1, list.size()));
            list1.add(Double.toString(solveExpression(list.subList(0, index))));
            list1.addAll(list2);
            list = list1;
        }
        return list;
    }

    public static int indexOfAfter(List<String> list, String str, int index)
    {
        for (int i = index; i < list.size(); i++)
        {
            if (list.get(i).equals(str))
            {
                return i;
            }
        }
        return -1;
    }

    public static List<String> handleComplex(List<String> list, int index) throws Exception
    {
        String op = list.get(index);
        int index2 = indexOfAfter(list, ")", index);
        List<String> list1 = new ArrayList<>(list.subList(0, index));
        List<String> list2 = new ArrayList<>(list.subList(index + 1, index2));
        List<String> list3 = new ArrayList<>(list.subList(index2 + 1, list.size()));
        Double res = solveExpression(list2);
        switch (op) {
            case "sqrt(":
                res = Math.sqrt(res);
                break;
            case "sin(":
                res = Math.sin(res);
                break;
            case "cos(":
                res = Math.cos(res);
                break;
            case "tan(":
                res = Math.tan(res);
                break;
            case "log(":
                res = Math.log10(res);
                break;
            case "ln(":
                res = Math.log(res);
                break;
            default:
                throw new ArithmeticException();
        }
        list1.add(Double.toString(res));
        list1.addAll(list3);
        return list1;
    }

    public static double solveExpression(List<String> list) throws Exception
    {
        System.out.println("solving expression " + list);
        if (list.size() < 1)
        {
            throw new ArithmeticException();
        }
        while (list.size() > 1)
        {
            if (list.contains("sqrt(") || list.contains("sin("))
            {
                int index = nonNegativeMin(list.indexOf("sqrt("), list.indexOf("sin("));
                list = handleComplex(list, index);
            }
            else if (list.contains("cos(") || list.contains("tan("))
            {
                int index = nonNegativeMin(list.indexOf("cos("), list.indexOf("tan("));
                list = handleComplex(list, index);
            }
            else if (list.contains("log(") || list.contains("ln("))
            {
                int index = nonNegativeMin(list.indexOf("log("), list.indexOf("ln("));
                list = handleComplex(list, index);
            }
            else if (list.contains(")"))
            {
                int index = nonNegativeMin(list.indexOf("("), list.indexOf(")"));
                list = handleParenthesis(list, index);
            }
            else if (list.contains("!"))
            {
                int index = list.indexOf("!");
                list = handleFactorial(list, index);
            }
            else if (list.contains("^") || list.contains("%"))
            {
                int index = nonNegativeMin(list.indexOf("^"), list.indexOf("%"));
                list = handleBasicOperation(list, index);
            }
            else if (list.contains("*") || list.contains("/"))
            {
                int index = nonNegativeMin(list.indexOf("*"), list.indexOf("/"));
                list = handleBasicOperation(list, index);
            }
            else if (list.contains("+") || list.contains("-"))
            {
                int index = nonNegativeMin(list.indexOf("+"), list.indexOf("-"));
                list = handleBasicOperation(list, index);
            }
            else
            {
                throw new ArithmeticException();
            }
        }
        Double res;
        try {
            res = Double.parseDouble(list.get(0));
        } catch (ArithmeticException e) {
            throw new ArithmeticException();
        } catch (NumberFormatException e) {
            throw new ArithmeticException();
        } catch (Exception e) {
            throw new Exception();
        }
        return res;
    }

    public static List<String> parseStrings(String str)
    {
        List<String> list = new ArrayList<>();
        String temp = "";
        for (int i = 0; i < str.length(); i++)
        {
            String s = str.substring(i, i + 1);
            if (str.substring(i).startsWith("sqrt("))
            {
                list.add(temp);
                temp = "";
                list.add("sqrt(");
                i += 4;
            }
            else if (str.substring(i).startsWith("sin(") || str.substring(i).startsWith("cos(") || str.substring(i).startsWith("tan(") || str.substring(i).startsWith("log("))
            {
                list.add(temp);
                temp = "";
                list.add(str.substring(i, 4));
                i += 3;
            }
            else if (str.substring(i).startsWith("ln("))
            {
                list.add(temp);
                temp = "";
                list.add("ln(");
                i += 2;
            }
            else if (str.substring(i).startsWith("pi"))
            {
                if (temp.equals("-"))
                {
                    list.add(Double.toString(-Math.PI));
                    temp = "";
                    i++;
                    continue;
                }
                list.add(temp);
                temp = "";
                list.add(Double.toString(Math.PI));
                i++;
            }
            else if (s.equals("e"))
            {
                if (temp.equals("-"))
                {
                    list.add(Double.toString(-Math.E));
                    temp = "";
                    continue;
                }
                list.add(temp);
                temp = "";
                list.add(Double.toString(Math.E));
            }
            else if (opList.contains(s))
            {
                if (s.equals("-"))
                {
                    if (i != 0 && ("0123456789)".contains(str.substring(i - 1, i))))
                    {
                        list.add(temp);
                        temp = "";
                        list.add(s);
                    }
                    else
                    {
                        temp += s;
                    }
                    continue;
                }  
                list.add(temp);
                temp = "";
                list.add(s);
            }
            else if (Character.isDigit(s.charAt(0)))
            {
                temp += s;
            }
            else if (s.equals("."))
            {
                temp += s;
            }
            else
            {
                throw new ArithmeticException();
            }
        }
        list.add(temp);
        return list;
    }

    public static void main(String[] args) {
        System.out.println("To use calculator, type an expression, type \"stop\" to stop");
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine())
        {
            String str = in.nextLine().replaceAll(" ", "").toLowerCase();
            // str = "1 +1" "11"
            // str
            if (str.equals("stop"))
            {
                break;
            }
            List<String> list = new ArrayList<>();
            try {
                list = parseStrings(str);
                // list = [1, +, 1]
            } catch (ArithmeticException e) {
                System.out.println("Invalid input, please try again!");
            } catch (Exception e) {
                System.out.println("Something went wrong, pleas try again!");
            }
            list.removeIf(String::isEmpty);
            System.out.println(list);
            if (list.size() > 0)
            {
                if (!validInput(list))
                {
                    System.out.println("Invalid input, please try again!");
                    continue;
                }
                if (matchingParen(list))
                {
                    try {
                        Double ans = solveExpression(list);
                        System.out.println("The answer is: " + ans);
                    } catch (ArithmeticException e) {
                        System.out.println("Invalid input, please try again!");
                    } catch (Exception e) {
                        System.out.println("Something went wrong, pleas try again!");
                    }
                }
                else
                {
                    System.out.println("Invalid input, please try again!");
                }
            }
        }
        in.close();
    }
}