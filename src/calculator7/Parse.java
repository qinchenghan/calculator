package calculator7;
import java.util.*;
public class Parse {

    public static List<String> parse(String str)
    {
        List<String> list = new ArrayList<>();
        String temp = "";
        while (str.length() > 0)
        {
            if (Symbol.startsWithSymbol(str) >= 0)
            {
                int index = Symbol.startsWithSymbol(str);
                list.add(temp);
                temp = "";
                list.add(str.substring(0, index));
                str = str.substring(index, str.length());
            }
            else if ((str.charAt(0) >= '0' && str.charAt(0) <= '9') || str.charAt(0) == '.')
            {
                temp += str.charAt(0);
                str = str.substring(1, str.length());
            }
            else
            {
                throw new ArithmeticException();
            }
        }
        list.add(temp);
        return list;
    }

    public static List<String> handleNegative(List<String> list)
    {
        if (list.size() <= 1)
        {
            return list;
        }
        for (int i = 0; i < list.size(); i++)
        {
            String str = list.get(i);
            if (str.equals("-"))
            {
                if (i == 0 && isDouble(list.get(1)))
                {
                    list.set(1, "-" + list.get(1));
                    list.remove(0);
                    continue;
                }
                else if (i != 0)
                {
                    String pre = list.get(i - 1);
                    if (!isDouble(pre) && )
                }
            }
        }
    }

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
        if (str.equals(Symbol.pi.getSymbol() )|| str.equals(Symbol.e.getSymbol()))
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
}