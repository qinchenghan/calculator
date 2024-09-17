package window;
import javax.swing.*;
import java.awt.*;

public class Window extends JFrame
{
    int width = 500;
    int height = 500;

    public Window()
    {
        setSize(width, height);
        setTitle("calculator");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void launch()
    {
        setVisible(true);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, width, height);
    }
}
