package calculator2;
import java.util.*;
public class Main {
    // + - * / ( ) ^ % ! 
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

    public static boolean hasInt(String str)
    {
        for (int i = 0; i < str.length(); i++)
        {
            if (Character.isDigit(str.charAt(i)))
            {
                return true;
            }
        }
        return false;
    }

    public static int nonNegativeMin(int a, int b)
    {
        if (a < 0 && b < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        else if (a < 0)
        {
            return b;
        }
        else if (b < 0)
        {
            return a;
        }
        else
        {
            return Math.min(a, b);
        }
    }
    
    public static int solveExpression(List<String> list)
    {
        while (list.size() >= 3)
        {
            if (list.contains("*") || list.contains("/"))
            {
                int im = list.indexOf("*");
                int id = list.indexOf("/");
                int index = nonNegativeMin(im, id);
                int num1 = Integer.parseInt(list.get(index - 1));
                int num2 = Integer.parseInt(list.get(index + 1));
                switch (list.get(index)) {
                    case "*":
                        list.set(index, Integer.toString(num1 * num2));
                        break;
                    case "/":
                        if (num2 != 0)
                        {
                            list.set(index, Integer.toString(num1 / num2));
                        }
                        else
                        {
                            throw new ArithmeticException();
                        }
                        break;
                    default:
                        throw new ArithmeticException();
                }
                list.remove(index + 1);
                list.remove(index - 1);
            }
            else
            {
                int ip = list.indexOf("+");
                int im = list.indexOf("-");
                int index = nonNegativeMin(ip, im);
                int num1 = Integer.parseInt(list.get(index - 1));
                int num2 = Integer.parseInt(list.get(index + 1));
                switch (list.get(index)) {
                    case "+":
                        list.set(index, Integer.toString(num1 + num2));
                        break;
                    case "-":
                        list.set(index, Integer.toString(num1 - num2));
                        break;
                    default:
                        throw new ArithmeticException();
                }
                list.remove(index + 1);
                list.remove(index - 1);
            }
        }
        return Integer.parseInt(list.get(0));
    }

    public static void main(String[] args) {
        System.out.println("To use calculator, type an expression");
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine())
        {
            String str = in.nextLine();
            if (!hasInt(str))
            {
                break;
            }
            String[] array = str.split(" ");
            if (array.length > 0)
            {
                List<String> list = new ArrayList<>(Arrays.asList(array));
                System.out.println(solveExpression(list));
            }
        }
        in.close();
    }
}