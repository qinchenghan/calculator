package shape;
public class Rectangle {
    private int height;
    private int width;
    private int area;
    private int perimeter;

    public Rectangle(int height, int width)
    {
        this.height = height;
        this.width = width;
        this.area = height * width;
        this.perimeter = (height + width) * 2;
    }

    public int getHeight()
    {
        return this.height;
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getArea()
    {
        return this.area;
    }

    public int getPerimeter()
    {
        return this.perimeter;
    }
}
