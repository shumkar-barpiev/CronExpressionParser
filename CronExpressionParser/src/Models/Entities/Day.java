package Models.Entities;

import Models.Interface.CronExpression;

import java.util.regex.Pattern;

public class Day extends Base implements CronExpression {
    public Day(String expression) {
        super(expression);
        this.maximumRange = 31;
        this.minimumRange = 1;
    }

    @Override
    public boolean isValid(String expression) {

        if (
                Pattern.matches("[0-9]{2},[0-9]", expression) ||
                        Pattern.matches("[0-9],[0-9]", expression) ||
                        Pattern.matches("[0-9]{2},[0-9]{2}", expression) ||
                        Pattern.matches("[0-9]", expression) ||
                        Pattern.matches("[0-9]{2}", expression) ||
                        Pattern.matches("[*]", expression) ||
                        Pattern.matches("[0-9]-[0-9]", expression) ||
                        Pattern.matches("[0-9]-[0-9]{2}", expression) ||
                        Pattern.matches("[0-9]{2}-[0-9]{2}", expression)
        ) {
            return true;
        }
        return false;
    }

    @Override
    public boolean controlRange(String expression) {
        if (
                Pattern.matches("[0-9]{2}", expression) ||
                        Pattern.matches("[0-9]", expression)
        ) {
            int N = Integer.parseInt(expression);

            if (N <= this.maximumRange && N >= this.minimumRange) {
                return true;
            } else {
                return false;
            }
        } else if (
                Pattern.matches("[0-9]-[0-9]", expression) ||
                        Pattern.matches("[0-9]-[0-9]{2}", expression) ||
                        Pattern.matches("[0-9]{2}-[0-9]{2}", expression)
        ) {
            String[] segments = expression.split("-", 0);
            int maxN = Integer.parseInt(segments[1]);
            int minN = Integer.parseInt(segments[0]);

            if (maxN >= minN && maxN <= this.maximumRange && minN >= this.minimumRange) {
                return true;
            } else {
                return false;
            }

        } else if (
                Pattern.matches("[0-9]{2},[0-9]", expression) ||
                        Pattern.matches("[0-9],[0-9]", expression) ||
                        Pattern.matches("[0-9]{2},[0-9]{2}", expression)
        ) {
            String[] segments = expression.split(",", 0);
            int month = Integer.parseInt(segments[0]);
            int day = Integer.parseInt(segments[1]);

            if (month >= 1 && month <= 12 && day <= this.maximumRange && day >= this.minimumRange) {
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
            return "day " + result;
        } else if (
                Pattern.matches("[0-9]{2},[0-9]", expression) ||
                        Pattern.matches("[0-9],[0-9]", expression) ||
                        Pattern.matches("[0-9]{2},[0-9]{2}", expression)

        ) {
            String[] segments = expression.split(",", 0);
            int month = Integer.parseInt(segments[0]);
            int day = Integer.parseInt(segments[1]);

            return "day of month " + month + " " + day;
        } else if (
                Pattern.matches("[0-9]{2}", expression) ||
                        Pattern.matches("[0-9]", expression)
        ) {
            return "day" + expression;
        } else if (
                Pattern.matches("[0-9]-[0-9]", expression) ||
                        Pattern.matches("[0-9]-[0-9]{2}", expression) ||
                        Pattern.matches("[0-9]{2}-[0-9]{2}", expression)
        ) {
            String[] segments = expression.split("-", 0);
            int maxN = Integer.parseInt(segments[1]);
            int minN = Integer.parseInt(segments[0]);

            String result = "";

            for (int i = minN; i <= maxN; i++) {
                result = result + i + " ";
            }
            return "day " + result;
        }

        return null;
    }
}


