import Models.Entities.*;
import java.util.regex.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        CronExpressionParser test1 = new CronExpressionParser("*/15 0 1,5 * 1-5 /usr/bin/find/get");
        CronExpressionParser test2 = new CronExpressionParser("*/9 0 8-23 * 1-5 /usr/bin/request/settings");
        CronExpressionParser test3 = new CronExpressionParser("*/10 0 1-15 * 1-5 2016 /usr/bin/find");
        CronExpressionParser test4 = new CronExpressionParser("15-37 0 1,5 * 1-5 /usr/bin/fetch");

//      error case
        CronExpressionParser test5 = new CronExpressionParser("*sdfsd/70 0 1,5 * 1-5 /usr/bin/find");
        CronExpressionParser test6 = new CronExpressionParser("*/7 0 Hello * 1-5 /usr/bin/find");

        System.out.println("\n\n\nTest 1: \t\t" + test1.getExpression() +"\n");
        test1.processingExpression(test1);
        System.out.println("\n\n\nTest 2: \t\t"+ test2.getExpression()+"\n");
        test1.processingExpression(test2);
        System.out.println("\n\n\nTest 3: \t\t"+ test3.getExpression()+"\n");
        test1.processingExpression(test3);
        System.out.println("\n\n\nTest 4: \t\t"+ test4.getExpression()+"\n");
        test1.processingExpression(test4);


    }
}

class CronExpressionParser{
    private String expression;
    private  String command;

//    constructor of the CronExpressionParser class
    CronExpressionParser(String expression){
        this.expression = expression;
    }

//    getter and setter function of field expression
    public void setExpression(String expression) {
        this.expression = expression;
    }
    public String getExpression() {
        return expression;
    }

//    getter and setter function of field command
    public String getCommand() {
        return command;
    }
    public void setCommand(String command) {
        this.command = command;
    }

    //    processing the expression and returning result

    public void processingExpression(CronExpressionParser obj){
//        splitting the expression into segments
        String[] segments = obj.expression.split(" ", 0);

        if(segments.length < 6 || segments.length > 7){
            System.out.println("Number of segments are out of range or not enough!!!");
        }else{
            if (segments.length == 6){
                Minute minute = new Minute(segments[0]);
                Hour hour = new Hour(segments[1]);
                Day dayOfTheMonth = new Day(segments[2]);
                Month month = new Month(segments[3]);
                Weekday dayOfTheWeek = new Weekday(segments[4]);

                obj.setCommand(segments[segments.length-1]);


                if(minute.isValid(minute.expressionString) ){
                    if (minute.controlRange(minute.expressionString)){
                        if (hour.isValid(hour.expressionString)){
                            if(hour.controlRange(hour.expressionString)){
                                if (dayOfTheMonth.isValid(dayOfTheMonth.expressionString)){
                                    if(dayOfTheMonth.controlRange(dayOfTheMonth.expressionString)){
                                        if(month.isValid(month.expressionString)){
                                            if(month.controlRange(month.expressionString)){
                                                if(dayOfTheWeek.isValid(dayOfTheWeek.expressionString)){
                                                    if(dayOfTheWeek.controlRange(dayOfTheWeek.expressionString)){
                                                        System.out.println(minute.getResult(minute.expressionString));
                                                        System.out.println(hour.getResult(hour.expressionString));
                                                        System.out.println(dayOfTheMonth.getResult(dayOfTheWeek.expressionString));
                                                        System.out.println(month.getResult(month.expressionString));
                                                        System.out.println(dayOfTheWeek.getResult(dayOfTheWeek.expressionString));
                                                        System.out.println("command " + obj.command);
                                                    }else{
                                                        System.out.println("Invalid Week day values. Values are incorrect or step size is more than maximum value!!!");
                                                    }
                                                }else{
                                                    System.out.println("Invalid Week day input. Please, enter the valid inputs!!!");
                                                }
                                            }else{
                                                System.out.println("Invalid Month values. Values are incorrect or step size is more than maximum value!!!");
                                            }
                                        }else{
                                            System.out.println("Invalid Month input. Please, enter the valid inputs!!!");
                                        }
                                    }else{
                                        System.out.println("Invalid Day values. Values are incorrect or step size is more than maximum value!!!");
                                    }
                                }else{
                                    System.out.println("Invalid Day input. Please, enter the valid inputs!!!");
                                }
                            }else{
                                System.out.println("Invalid Hour values. Values are incorrect or step size is more than maximum value!!!");
                            }
                        }else{
                            System.out.println("Invalid Hour input. Please, enter the valid inputs!!!");
                        }
                    }else{
                        System.out.println("Invalid Minute values. Values are incorrect or step size is more than maximum value!!!");
                    }
                }else{
                    System.out.println("Invalid Minute input. Please, enter the valid inputs!!!");
                }

            } else if (segments.length == 7) {
                Minute minute = new Minute(segments[0]);
                Hour hour = new Hour(segments[1]);
                Day dayOfTheMonth = new Day(segments[2]);
                Month month = new Month(segments[3]);
                Weekday dayOfTheWeek = new Weekday(segments[4]);
                Year year = new Year(segments[5]);

                obj.setCommand(segments[segments.length-1]);

                if(minute.isValid(minute.expressionString) ){
                    if (minute.controlRange(minute.expressionString)){
                        if (hour.isValid(hour.expressionString)){
                            if(hour.controlRange(hour.expressionString)){
                                if (dayOfTheMonth.isValid(dayOfTheMonth.expressionString)){
                                    if(dayOfTheMonth.controlRange(dayOfTheMonth.expressionString)){
                                        if(month.isValid(month.expressionString)){
                                            if(month.controlRange(month.expressionString)){
                                                if(dayOfTheWeek.isValid(dayOfTheWeek.expressionString)){
                                                    if(dayOfTheWeek.controlRange(dayOfTheWeek.expressionString)){
                                                        if(year.isValid(year.expressionString)){
                                                            if(year.controlRange(year.expressionString)){
                                                                System.out.println(minute.getResult(minute.expressionString));
                                                                System.out.println(hour.getResult(hour.expressionString));
                                                                System.out.println(dayOfTheMonth.getResult(dayOfTheWeek.expressionString));
                                                                System.out.println(month.getResult(month.expressionString));
                                                                System.out.println(dayOfTheWeek.getResult(dayOfTheWeek.expressionString));
                                                                System.out.println(year.getResult(year.expressionString));
                                                                System.out.println("command " + obj.command);
                                                            }else{
                                                                System.out.println("Invalid Year values. Values are incorrect or step size is more than maximum value!!!");
                                                            }
                                                        }else{
                                                            System.out.println("Invalid Year input. Please, enter the valid inputs!!!");
                                                        }
                                                    }else{
                                                        System.out.println("Invalid Week day values. Values are incorrect or step size is more than maximum value!!!");
                                                    }
                                                }else{
                                                    System.out.println("Invalid Week day input. Please, enter the valid inputs!!!");
                                                }
                                            }else{
                                                System.out.println("Invalid Month values. Values are incorrect or step size is more than maximum value!!!");
                                            }
                                        }else{
                                            System.out.println("Invalid Month input. Please, enter the valid inputs!!!");
                                        }
                                    }else{
                                        System.out.println("Invalid Day values. Values are incorrect or step size is more than maximum value!!!");
                                    }
                                }else{
                                    System.out.println("Invalid Day input. Please, enter the valid inputs!!!");
                                }
                            }else{
                                System.out.println("Invalid Hour values. Values are incorrect or step size is more than maximum value!!!");
                            }
                        }else{
                            System.out.println("Invalid Hour input. Please, enter the valid inputs!!!");
                        }
                    }else{
                        System.out.println("Invalid Minute values. Values are incorrect or step size is more than maximum value!!!");
                    }
                }else{
                    System.out.println("Invalid Minute input. Please, enter the valid inputs!!!");
                }
            }
        }
    }
}