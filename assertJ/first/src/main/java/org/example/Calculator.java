package org.example;

public class Calculator {
    public static int calculation(int firstOperand, int secondOperand, char operator) {
        int result;

        switch (operator) {
            case '+':
                result = firstOperand + secondOperand;
                break;
            case '-':
                result = firstOperand - secondOperand;
                break;
            case '*':
                result = firstOperand * secondOperand;
                break;
            case '/':
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                    break;
                } else {
                    throw new ArithmeticException("Division by zero is not possible");
                }
            default:
                throw new IllegalStateException("Unexpected value operator: " + operator);
        }
        return result;
    }

    //В классе Calculator создайте метод calculateDiscount, который принимает сумму покупки и процент скидки
    // и возвращает сумму с учетом скидки
        public static double calculateDiscount(double purchaseAmount, int discountAmount) {
        if (purchaseAmount<0)throw new ArithmeticException("purchaseAmount can not be negotive");
        if (discountAmount<0)throw new ArithmeticException("discountAmount can not be negotive");

        double disc=discountAmount*0.01;
        return purchaseAmount-(purchaseAmount*disc);
    }
}