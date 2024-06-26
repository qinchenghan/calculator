package tutor;

import java.util.Arrays;

public class Main {
    public static void run()
    {
        System.out.println("跑");
    }

    public static void stop()
    {
        System.out.println("停");
    }

    public static int[] 向左走(int[] 坐标, int step)
    {
        int x = 坐标[0];
        int y = 坐标[1];
        return new int[]{x - step, y};
    }

    public static void main(String[] args) {
        run();
        stop();
        int[] 坐标 = new int[]{1, 0};
        System.out.println(Arrays.toString(坐标));
        坐标 = 向左走(坐标, 4);
        System.out.println(Arrays.toString(坐标));
    }
}
