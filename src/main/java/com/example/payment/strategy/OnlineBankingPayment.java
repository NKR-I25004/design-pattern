package com.example.payment.strategy;

public class OnlineBankingPayment implements PaymentStrategy {
    private String bankAccount;

    public OnlineBankingPayment(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Online Banking (Account: " + bankAccount + ")");
    }
} 