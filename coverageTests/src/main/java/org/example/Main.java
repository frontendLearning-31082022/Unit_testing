package org.example;

public class Main {

    /*
    Протестерируйте два метода: проверки четности числа и
        проверки вхождения в int интервал
        с проверкой процента покрытия класса тестами
     */
    public static void main(String[] args) {
        boolean res = evenOddNumber(2);
        boolean res2 = numberInInterval(-100);
    }

    public static boolean evenOddNumber(int n) {
        if (n % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean numberInInterval(int n) {
        if (n < 25) return false;
        if (n > 100) return false;

        return true;
    }

}