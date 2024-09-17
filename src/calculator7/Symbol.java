package calculator7;

public enum Symbol
{
    add("+"),
    sub("-"),
    mul("*"),
    div("/"),
    mol("%"),
    expo("^"),
    leftp("("),
    rightp(")"),
    fact("!"),
    sqrt("sqrt("),
    sin("sin("),
    cos("cos("),
    tan("tan("),
    log("log("),
    ln("ln("),
    pi("pi"),
    e("e");

    private String symbol;

    private Symbol(String symbol)
    {
        this.symbol = symbol;
    }

    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public static boolean contains(String str) {
        for (Symbol op : Symbol.values()) {
            if (op.getSymbol().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static int startsWithSymbol(String str)
    {
        for (Symbol op : Symbol.values())
        {
            if (str.startsWith(op.getSymbol()))
            {
                return op.getSymbol().length();
            }
        }
        return -1;
    }
}