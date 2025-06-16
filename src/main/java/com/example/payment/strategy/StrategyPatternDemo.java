package com.example.payment.strategy;

public class StrategyPatternDemo {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456", "Alice"));
        context.processPayment(100.0);

        context.setPaymentStrategy(new DebitCardPayment("9876-5432-1098-7654", "Bob"));
        context.processPayment(200.0);

        context.setPaymentStrategy(new OnlineBankingPayment("alice@bank"));
        context.processPayment(300.0);
    }
} 