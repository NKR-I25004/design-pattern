package com.example.payment;

import com.example.payment.strategy.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentContextTest {

    @Test
    void testCreditCardPayment() {
        PaymentContext context = new PaymentContext();
        context.setPaymentStrategy(new CreditCardPayment("1111", "Test User"));
        assertDoesNotThrow(() -> context.processPayment(50.0));
    }

    @Test
    void testDebitCardPayment() {
        PaymentContext context = new PaymentContext();
        context.setPaymentStrategy(new DebitCardPayment("2222", "Test User"));
        assertDoesNotThrow(() -> context.processPayment(75.0));
    }

    @Test
    void testOnlineBankingPayment() {
        PaymentContext context = new PaymentContext();
        context.setPaymentStrategy(new OnlineBankingPayment("test@bank"));
        assertDoesNotThrow(() -> context.processPayment(100.0));
    }

    @Test
    void testNoStrategySet() {
        PaymentContext context = new PaymentContext();
        Exception exception = assertThrows(IllegalStateException.class, () -> context.processPayment(10.0));
        assertEquals("Payment strategy not set", exception.getMessage());
    }
} 