package calculator7;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("calculator7");
        while (in.hasNextLine())
        {
            String str = in.nextLine().replaceAll(" ", "").toLowerCase();
            if (str.equals("stop"))
            {
                break;
            }
            List<String> list = new ArrayList<>();
            System.out.println("String is " + str);
            try {
                list = Parse.parse(str);
            } catch (ArithmeticException e) {
                System.out.println("Invalid Input");
            } catch (Exception e) {
                System.out.println("Something went wrong, pleas try again!");
            }
            list.removeIf(String::isEmpty);
            System.out.println(list);
        }
        in.close();
    }
}
