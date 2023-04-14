package Models.Entities;

import Models.Interface.CronExpression;

import java.util.regex.Pattern;

public class Weekday extends Base implements CronExpression {
    public Weekday(String expression) {
        super(expression);
        this.maximumRange = 7;
        this.minimumRange = 1;
    }

    @Override
    public boolean isValid(String expression) {
        return Pattern.matches("[*]", expression) ||
                Pattern.matches("[0-9]", expression) ||
                Pattern.matches("[0-9]-[0-9]", expression);
    }

    @Override
    public boolean controlRange(String expression) {

        if (
                Pattern.matches("[0-9]", expression)
        ) {
            int N = Integer.parseInt(expression);

            if (N <= this.maximumRange && N >= this.minimumRange) {
                return true;
            } else {
                return false;
            }
        } else if (Pattern.matches("[0-9]-[0-9]", expression)) {
            String[] segments = expression.split("-", 0);
            int maxN = Integer.parseInt(segments[1]);
            int minN = Integer.parseInt(segments[0]);

            if (maxN >= minN && maxN <= this.maximumRange && minN >= this.minimumRange) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public String getResult(String expression) {
        if (
                Pattern.matches("[*]", expression)
        ) {
            String result = "";

            for (int i = this.minimumRange; i <= this.maximumRange; i++) {
                result = result + i + " ";
            }
            return "day of week " + result;
        } else if (Pattern.matches("[0-9]", expression)) {
            return "week day " + expression;
        } else if (
                Pattern.matches("[0-9]-[0-9]", expression)
        ) {
            String[] segments = expression.split("-", 0);
            int maxN = Integer.parseInt(segments[1]);
            int minN = Integer.parseInt(segments[0]);

            String result = "";

            for (int i = minN; i <= maxN; i++) {
                result = result + i + " ";
            }
            return "day of week " + result;
        }
        return null;
    }
}
