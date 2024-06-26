package calculator6;
import java.util.*;
public class Main {
    private final static List<String> symbolList = new ArrayList<>(Arrays.asList("+", "-", "*", "/", "%", "^", "(", ")", "sqrt(", "!"));
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

    public static int nonNegativeMin(int a, int b, int c)
    {
        if (a < 0 && b < 0 && c < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        else if (a < 0)
        {
            if (b < 0)
            {
                return c;
            }
            else if (c < 0)
            {
                return b;
            }
            else
            {
                return Math.min(b, c);
            }
        }
        else if (b < 0)
        {
            if (c < 0)
            {
                return a;
            }
            else
            {
                return Math.min(a, c);
            }
        }
        else if (c < 0)
        {
            return Math.min(a, b);
        }
        else
        {
            return Math.min(Math.min(a, b), c);
        }
    }

    public static boolean matchingParen(List<String> list)
    {
        int res = 0;
        for (int i  = 0; i < list.size(); i++)
        {
            String str = list.get(i);
            if (str.equals("("))
            {
                res++;
            }
            else if (str.equals("sqrt("))
            {
                res++;
            }
            else if (str.equals(")"))
            {
                res--;
            }
        }
        if (res == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean validInput(List<String> list)
    {
        if (list.size() == 2 && !list.contains(")") && !list.contains("!"))
        {
            return false;
        }
        boolean hasSymbol = false;
        for (int i = 0; i < list.size(); i++)
        {
            String str = list.get(i);
            if (symbolList.contains(str))
            {
                hasSymbol = true;
                continue;
            }
            if (!isDouble(str))
            {
                return false;
            }
        }
        if (list.size() > 1)
        {
            return true && hasSymbol;
        }
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
        String symbol = list.get(index);
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
        switch (symbol) {
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
        String symbol = list.get(index);
        if (symbol.equals("("))
        {
            List<String> list1 = new ArrayList<>(list.subList(0, index));
            List<String> list2 = new ArrayList<>();
            list2.add(Double.toString(solveExpression(list.subList(index + 1, list.size()))));
            list1.addAll(list2);
            list = list1;
        }
        else if (symbol.equals(")"))
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

    public static List<String> handleSquareRoot(List<String> list, int index) throws Exception
    {
        int index2 = indexOfAfter(list, ")", index);
        List<String> list1 = new ArrayList<>(list.subList(0, index));
        List<String> list2 = new ArrayList<>(list.subList(index + 1, index2));
        List<String> list3 = new ArrayList<>(list.subList(index2 + 1, list.size()));
        list1.add(Double.toString(Math.sqrt(solveExpression(list2))));
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
            if (list.contains("sqrt("))
            {
                int index = list.indexOf("sqrt(");
                list = handleSquareRoot(list, index);
            }
            else if (list.contains(")"))
            {
                int index = nonNegativeMin(list.indexOf("("), list.indexOf(")"), -1);
                list = handleParenthesis(list, index);
            }
            else if (list.contains("!"))
            {
                int index = list.indexOf("!");
                list = handleFactorial(list, index);
            }
            else if (list.contains("^"))
            {
                int index = list.indexOf("^");
                list = handleBasicOperation(list, index);
            }
            else if (list.contains("*") || list.contains("/") || list.contains("%"))
            {
                int index = nonNegativeMin(list.indexOf("*"), list.indexOf("/"), list.indexOf("%"));
                list = handleBasicOperation(list, index);
            }
            else if (list.contains("+") || list.contains("-"))
            {
                int index = nonNegativeMin(list.indexOf("+"), list.indexOf("-"), -1);
                list = handleBasicOperation(list, index);
            }
            else
            {
                throw new ArithmeticException();
            }
        }
        return Double.parseDouble(list.get(0));
    }

    public static List<String> parseStrings(String str)
    {
        List<String> list = new ArrayList<>();
        String temp = "";
        for (int i = 0; i < str.length(); i++)
        {
            String s = str.substring(i, i + 1);
            if (temp.isEmpty() && s.equals("-"))
            {
                temp += s;
            }
            else if (i < str.length() - 4 && s.equals("s"))
            {
                if (str.substring(i, i + 5).equals("sqrt("))
                {
                    if (temp.equals(".") || temp.equals("-"))
                    {
                        throw new ArithmeticException();
                    }
                    if (!temp.isEmpty())
                    {
                        list.add(temp);
                        temp = "";
                    }
                    list.add("sqrt(");
                    i += 4;
                }
            }
            else if (symbolList.contains(s))
            {
                if (temp.equals(".") || temp.equals("-"))
                {
                    throw new ArithmeticException();
                }
                if (!temp.isEmpty())
                {
                    list.add(temp);
                    temp = "";
                }
                list.add(s);
            }
            else if (Character.isDigit(s.charAt(0)))
            {
                temp += s;
            }
            else if (s.equals("."))
            {
                if (!temp.contains("."))
                {
                    temp += s;
                }
                else
                {
                    throw new ArithmeticException();
                }
            }
            else
            {
                throw new ArithmeticException();
            }
        }
        if (!temp.isEmpty())
        {
            if (!isDouble(temp))
            {
                throw new ArithmeticException();
            }
            list.add(temp);
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println("To use calculator, type an expression, type \"stop\" to stop");
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine())
        {
            String str = in.nextLine().replaceAll(" ", "").toLowerCase();
            if (str.equals("stop"))
            {
                break;
            }
            List<String> list = new ArrayList<>();
            try {
                list = parseStrings(str);
            } catch (ArithmeticException e) {
                System.out.println("Invalid input, please try again!");
            } catch (Exception e) {
                System.out.println("Something went wrong, pleas try again!");
            }
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