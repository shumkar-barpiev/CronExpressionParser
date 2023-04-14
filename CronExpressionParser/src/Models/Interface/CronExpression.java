package Models.Interface;

public interface CronExpression {
    public boolean isValid(String expression);
    public boolean controlRange(String expression);
    public String getResult(String expression);
}
