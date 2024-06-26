package calculator1;
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

    public static int solveExpression(List<String> list)
    {
        int res = 0;
        String symbol = " ";
        for (int i = 0; i < list.size(); i++)
        {
            if (isInt(list.get(i)))
            {
                if (res == 0)
                {
                    res = Integer.parseInt(list.get(i));
                }
                else
                {
                    int num = Integer.parseInt(list.get(i));
                    switch (symbol) {
                        case "+":
                            res += num;
                            break;
                        case "-":
                            res -= num;
                            break;
                        default:
                            throw new ArithmeticException();
                    }
                }
            }
            else
            {
                symbol = list.get(i);
            }
        }
        return res;
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
                List<String> list = Arrays.asList(array);
                System.out.println(solveExpression(list));
            }
        }
        in.close();
    }
}