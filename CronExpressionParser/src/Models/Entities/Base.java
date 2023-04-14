package Models.Entities;

public class Base {
    public int minimumRange;
    public int maximumRange;
    public String expressionString;

    Base(String expression) {
        this.expressionString = expression;
    }
}
