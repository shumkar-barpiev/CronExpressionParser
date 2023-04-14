package Models.Entities;

import java.util.regex.*;

import Models.Interface.CronExpression;

public class Minute extends Base implements CronExpression {
    public Minute(String expression) {
        super(expression);
        this.maximumRange = 59;
        this.minimumRange = 0;
    }

    @Override
    public boolean isValid(String expression) {
        if (
                Pattern.matches("[0-9]{2}/[0-9]", expression) ||
                        Pattern.matches("[0-9]/[0-9]", expression) ||
                        Pattern.matches("[0-9]{2}/[0-9]{2}", expression) ||
                        Pattern.matches("[0-9]", expression) ||
                        Pattern.matches("[0-9]{2}", expression) ||
                        Pattern.matches("[*]", expression) ||
                        Pattern.matches("[*]/[0-9]", expression) ||
                        Pattern.matches("[*]/[0-9]{2}", expression) ||
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
                Pattern.matches("[*]", expression) ||
                        Pattern.matches("[*]/[0-9]", expression) ||
                        Pattern.matches("[*]/[0-9]{2}", expression)
        ) {
            String[] segments1 = expression.split("/", 0);

            if (segments1.length > 1) {
                int N = Integer.parseInt(segments1[1]);

                if (N <= this.maximumRange && N >= this.minimumRange) {
                    return true;
                } else {
                    return false;
                }
            } else return true;
        } else if (
                Pattern.matches("[0-9]{2}/[0-9]", expression) ||
                        Pattern.matches("[0-9]/[0-9]", expression) ||
                        Pattern.matches("[0-9]{2}/[0-9]{2}", expression)
        ) {
            String[] segments = expression.split("/", 0);
            int maxN = Integer.parseInt(segments[0]);
            int minN = Integer.parseInt(segments[1]);

            if (maxN >= minN && maxN <= this.maximumRange && minN >= this.minimumRange) {
                return true;
            } else {
                return false;
            }
        } else if (
                Pattern.matches("[0-9]-[0-9]{2}", expression) ||
                        Pattern.matches("[0-9]-[0-9]", expression) ||
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
                Pattern.matches("[0-9]{2}", expression) ||
                        Pattern.matches("[0-9]", expression)

        ) {
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
        if (
                Pattern.matches("[*]", expression)
        ) {
            String result = "";

            for (int i = this.minimumRange; i <= this.maximumRange; i++) {
                result = result + i + " ";
            }
            return "minute " + result;
        } else if (
                Pattern.matches("[*]/[0-9]", expression) ||
                        Pattern.matches("[*]/[0-9]{2}", expression)

        ) {
            String result = "";
            String[] segments1 = expression.split("/", 0);
            int N = Integer.parseInt(segments1[1]);

            for (int i = this.minimumRange; i <= this.maximumRange; i += N) {
                result = result + i + " ";
            }
            return "minute " + result;

        } else if (
                Pattern.matches("[0-9]", expression) ||
                        Pattern.matches("[0-9]{2}", expression)
        ) {
            return "minute " + expression;
        } else if (
                Pattern.matches("[0-9]-[0-9]{2}", expression) ||
                        Pattern.matches("[0-9]-[0-9]", expression) ||
                        Pattern.matches("[0-9]{2}-[0-9]{2}", expression)
        ) {
            String[] segments = expression.split("-", 0);
            int maxN = Integer.parseInt(segments[1]);
            int minN = Integer.parseInt(segments[0]);
            String result = "";

            for (int i = minN; i <= maxN; i++) {
                result = result + i + " ";
            }
            return "minute " + result;

        }
        return null;
    }
}
