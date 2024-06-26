package shape;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        // RandomizedSet obj = new RandomizedSet();
        // System.out.println(obj.insert(0));
        // System.out.println(obj.insert(1));
        // System.out.println(obj.remove(0));
        // System.out.println(obj.insert(2));
        // System.out.println(obj.remove(1));
        // System.out.println(obj.getRandom());


        Scanner in = new Scanner(System.in);
        while (in.hasNextInt())
        {
            Square sq = new Square(in.nextInt());
            System.out.println("Square with sidelength " + sq.getHeight() + " has an area of " + sq.getArea());
        }
        in.close();
    }
}