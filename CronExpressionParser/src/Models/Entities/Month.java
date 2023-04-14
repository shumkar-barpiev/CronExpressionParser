package Models.Entities;

import Models.Interface.CronExpression;

import java.util.regex.Pattern;

public class Month extends Base implements CronExpression {
    public Month(String expression) {
        super(expression);
        this.maximumRange = 12;
        this.minimumRange = 0;
    }

    @Override
    public boolean isValid(String expression) {
        return Pattern.matches("[*]", expression) ||
                Pattern.matches("[0-9]", expression) ||
                Pattern.matches("[0-9]{2}", expression) ||
                Pattern.matches("[0-9]-[0-9]", expression) ||
                Pattern.matches("[0-9]-[0-9]{2}", expression) ||
                Pattern.matches("[0-9]{2}-[0-9]{2}", expression) ||
                Pattern.matches("[0-9]{4},[0-9]", expression) ||
                Pattern.matches("[0-9]{4},[0-9]{2}", expression);
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
                Pattern.matches("[0-9]{4},[0-9]", expression) ||
                        Pattern.matches("[0-9]{4},[0-9]{2}", expression)
        ) {

            String[] segments = expression.split(",", 0);
            int month = Integer.parseInt(segments[1]);
            int year = Integer.parseInt(segments[0]);

            Year ob = new Year("");

            if (year >= ob.minimumRange && year <= ob.maximumRange && month <= this.maximumRange && month >= this.minimumRange) {
                return true;
            } else {
                return false;
            }
        } else if (Pattern.matches("[*]", expression) ) {
            return  true;
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
            return "month " + result;
        } else if (
                Pattern.matches("[0-9]", expression) ||
                        Pattern.matches("[0-9]{2}", expression)
        ) {
            return "month" + expression;
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
            return "month " + result;
        } else if (Pattern.matches("[0-9]{4},[0-9]", expression) ||
                Pattern.matches("[0-9]{4},[0-9]{2}", expression)) {

            String[] segments = expression.split(",", 0);
            int month = Integer.parseInt(segments[1]);
            int year = Integer.parseInt(segments[0]);

            return "month of year " + year + " " + month;
        }

        return null;
    }
}
