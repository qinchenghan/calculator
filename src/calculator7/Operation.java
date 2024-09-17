package calculator7;

public class Operation {
    Symbol op;
    double a;
    double b;

    public Operation(Symbol op, double a)
    {
        this.op = op;
        this.a = a;
    }

    public Operation(Symbol op, double a, double b)
    {
        this.op = op;
        this.a = a;
        this.b = b;
    }

    public static double performOperation(Symbol op, double a)
    {
        switch (op) {
            case fact:
                return factorial(a);
            case sqrt:
                return Math.sqrt(a);
            case sin:
                return Math.sin(a);
            case cos:
                return Math.cos(a);
            case tan:
                return Math.tan(a);
            case log:
                return Math.log10(a);
            case ln:
                return Math.log(a);
            default:
                throw new ArithmeticException();
        }
    }

    public static double performOperation(Symbol op, double a, double b)
    {
        switch (op) {
            case add:
                return a + b;
            case sub:
                return a - b;
            case mul:
                return a * b;
            case div:
                if (b != 0)
                {
                    return a / b;
                }
                else
                {
                    throw new ArithmeticException();
                }
            case mol:
                if (b != 0)
                {
                    return a % b;
                }
                else
                {
                    throw new ArithmeticException();
                }
            case expo:
                return Math.pow(a, b);
            default:
                throw new ArithmeticException();
        }
    }

    public static double factorial(double a)
    {
        if (a < 0 || a % 1 != 0)
        {
            throw new ArithmeticException();
        }
        double res = 1.0;
        for (double i = 1.0; i <= a; i++)
        {
            res *= i;
        }
        return res;
    }
}
