package Models.Entities;

import Models.Interface.CronExpression;

import java.util.regex.Pattern;

public class Year extends Base implements CronExpression {
    public Year(String expression) {
        super(expression);
        this.maximumRange = 2023;
        this.minimumRange = 1950;
    }

    @Override
    public boolean isValid(String expression) {
        return Pattern.matches("[0-9]{4}", expression);
    }

    @Override
    public boolean controlRange(String expression) {
        if (Pattern.matches("[0-9]{4}", expression)) {
            int N = Integer.parseInt(expression);

            if (N <= this.maximumRange && N >= this.minimumRange) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public String getResult(String expression) {

        return "year " + expression;
    }
}
