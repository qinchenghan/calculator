package calculator5;
import java.util.*;
public class Main {
    private final static List<String> symbolList = new ArrayList<>(Arrays.asList("+", "-", "*", "/", "%", "^", "(", ")"));
    public static boolean isInt(String str)
    {
        for (int i = 0; i < str.length(); i++)
        {
            if (!Character.isDigit(str.charAt(i)))
            {
                return false;
            }
        }
        return true;
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
        boolean hasSymbol = false;
        for (int i = 0; i < list.size(); i++)
        {
            String str = list.get(i);
            if (symbolList.contains(str))
            {
                hasSymbol = true;
                continue;
            }
            if (!isInt(str) && !(str.startsWith("-") && isInt(str.substring(1))))
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

    public static List<String> handleBasicOperation(List<String> list, int index)
    {
        if (index == 0 || index == list.size() - 1)
        {
            throw new ArithmeticException();
        }
        String symbol = list.get(index);
        int num1;
        int num2;
        int num;
        try {
            num1 = Integer.parseInt(list.get(index - 1));
            num2 = Integer.parseInt(list.get(index + 1));
        } catch (Exception e) {
            throw new ArithmeticException();
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
                num = (int) Math.pow(num1, num2);
                break;
            default:
                throw new ArithmeticException();
        }
        list.set(index, Integer.toString(num));
        list.remove(index + 1);
        list.remove(index - 1);
        return list;
    }

    public static List<String> handleParenthesis(List<String> list, int index)
    {
        String symbol = list.get(index);
        if (symbol.equals("("))
        {
            List<String> list1 = new ArrayList<>(list.subList(0, index));
            List<String> list2 = new ArrayList<>();
            list2.add(Integer.toString(solveExpression(list.subList(index + 1, list.size()))));
            list1.addAll(list2);
            list = list1;
        }
        else if (symbol.equals(")"))
        {
            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>(list.subList(index + 1, list.size()));
            list1.add(Integer.toString(solveExpression(list.subList(0, index))));
            list1.addAll(list2);
            list = list1;
        }
        return list;
    }

    public static int solveExpression(List<String> list)
    {
        System.out.println("solving expression " + list);
        if (list.size() < 1)
        {
            throw new ArithmeticException();
        }
        while (list.size() > 1)
        {
            if (list.contains(")"))
            {
                int index = nonNegativeMin(list.indexOf("("), list.indexOf(")"), -1);
                list = handleParenthesis(list, index);
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
            else
            {
                int index = nonNegativeMin(list.indexOf("+"), list.indexOf("-"), -1);
                list = handleBasicOperation(list, index);
            }
        }
        return Integer.parseInt(list.get(0));
    }

    public static void main(String[] args) {
        System.out.println("To use calculator, type an expression, type \"stop\" to stop");
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine())
        {
            String str = in.nextLine().trim();
            if (str.equals("stop"))
            {
                break;
            }
            String[] array = str.split("\\s+");
            if (array.length > 0)
            {
                List<String> list = new ArrayList<>(Arrays.asList(array));
                if (!validInput(list))
                {
                    System.out.println("Invalid input, please try again!");
                    continue;
                }
                if (matchingParen(list))
                {
                    try {
                        int ans = solveExpression(list);
                        System.out.println("The answer is: " + ans);
                    } catch (ArithmeticException e) {
                        System.out.println("Invalid input, please try again!");
                    } catch (Exception e) {
                        System.out.println("Something went wrong, pleas try");
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