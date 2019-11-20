# StringConverter
Method which receives a string, filter it and the returns a output String

Basically, we have a interface which one method. We implement that interface on a service implementation. There we have all the logic, We check the Period with an array which contains the 5 possible Periods, if the period doesn't match, return an "Invalid". After, we check the rest of the string, separating seconds and miliseconds, calculating plus time and setting it. Finally, we return the period string, plus " - " plus the time String.

IMPORTANT!-

In order to check properly the funcionality, there are 3 variables (String param) on the main class, you can edit them and use the one you prefer in order to check what output the program brings you, depending on the input.
