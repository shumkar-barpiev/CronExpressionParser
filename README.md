# CronExpressionParser

This project written in java programming language. You can run this project by Main.java.


<hr>
Test 1: 		*/15 0 1,5 * 1-5 /usr/bin/find/get<br>

minute 0 15 30 45 <br>
hour 0<br>
day 1 2 3 4 5 <br>
month 0 1 2 3 4 5 6 7 8 9 10 11 12 <br>
day of week 1 2 3 4 5 <br>
command /usr/bin/find/get<br>


<hr>
Test 2: 		*/9 0 8-23 * 1-5 /usr/bin/request/settings <br>

minute 0 9 18 27 36 45 54 <br>
hour 0<br>
day 1 2 3 4 5 <br>
month 0 1 2 3 4 5 6 7 8 9 10 11 12 <br>
day of week 1 2 3 4 5 <br>
command /usr/bin/request/settings<br>


<hr>
Test 3: 		*/10 0 1-15 * 1-5 2016 /usr/bin/find<br>

minute 0 10 20 30 40 50 <br>
hour 0<br>
day 1 2 3 4 5 <br>
month 0 1 2 3 4 5 6 7 8 9 10 11 12 <br>
day of week 1 2 3 4 5 <br>
year 2016<br>
command /usr/bin/find<br>


<hr>
Test 4: 		15-37 0 1,5 * 1-5 /usr/bin/fetch<br>

minute 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 <br>
hour 0<br>
day 1 2 3 4 5 <br>
month 0 1 2 3 4 5 6 7 8 9 10 11 12 <br>
day of week 1 2 3 4 5 <br>
command /usr/bin/fetch<br>


<hr>

Invalid input case:

input = "*/7 0 Hello * 1-5 /usr/bin/find" //Hello is invalid input<br>
input = "*sdfsd/32 0 1,5 * 1-5 /usr/bin/find" //minute parameter is invalid<br>
input = "*/68 0 1,5 * 1-5 /usr/bin/find" //minute step is out of range<br>
