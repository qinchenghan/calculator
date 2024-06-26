package calculator1;
public class CustomPrint {
    public static void println(String[] array)
    {
        String str = "[";
        for (int i = 0; i < array.length; i++)
        {
            if (i != array.length - 1)
            {
                str += array[i] + ", ";
            }
            else
            {
                str += array[i] + "]";
            }
        }
        System.out.println(str);
    }
}
