package calculator3;
import java.util.*;
public class Main {
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
    
    public static int indexoflast(List<String> list, String str)
    {
        for (int i = list.size() - 1; i >= 0; i--)
        {
            if (list.get(i).equals(str))
            {
                return i;
            }
        }
        return -1;
    }

    public static int solveExpression(List<String> list)
    {
        System.out.println("solving expression " + list);
        while (list.size() >= 3)
        {
            if ((list.contains("(") && !list.contains(")")) || (!list.contains("(") && list.contains(")")) )
            {
                throw new ArithmeticException();
            }
            else if (list.contains("(") && list.contains(")"))
            {
                System.out.println("() found");
                int start = list.indexOf("(");
                int end = indexoflast(list, ")");
                List<String> list1 = new ArrayList<>(list.subList(0, start));
                List<String> list2 = new ArrayList<>(list.subList(start + 1, end));
                System.out.println("expression inside () is " + list2);
                List<String> list3 = new ArrayList<>();
                if (end >= list.size() - 1)
                {
                    list3 = new ArrayList<>();
                }
                else
                {
                    list3 = new ArrayList<>(list.subList(end + 1, list.size()));
                }
                list = list1;
                list.add(Integer.toString(solveExpression(list2)));
                list.addAll(list3);
                System.out.println("expression after eliminating () is " + list);
            }
            else if (list.contains("^"))
            {
                System.out.println("^ found");
                int index = list.indexOf("^");
                int num1 = Integer.parseInt(list.get(index - 1));
                int num2 = Integer.parseInt(list.get(index + 1));
                list.set(index, Integer.toString((int)Math.pow(num1, num2)));
                list.remove(index + 1);
                list.remove(index - 1);
                System.out.println("expression after ^ is " + list);
            }
            else if (list.contains("*") || list.contains("/") || list.contains("%"))
            {
                int im = list.indexOf("*");
                int id = list.indexOf("/");
                int imo = list.indexOf("%");
                int index = nonNegativeMin(im, id, imo);
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
                    case "%":
                        list.set(index, Integer.toString(num1 % num2));
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
                int index = nonNegativeMin(ip, im, -1);
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