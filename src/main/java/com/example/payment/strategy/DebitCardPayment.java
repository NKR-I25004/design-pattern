package com.example.payment.strategy;

public class DebitCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolder;

    public DebitCardPayment(String cardNumber, String cardHolder) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Debit Card (" + cardHolder + ")");
    }
} 