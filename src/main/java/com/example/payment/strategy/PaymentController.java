package com.example.payment.strategy;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentContext paymentContext;

    public PaymentController(PaymentContext paymentContext) {
        this.paymentContext = paymentContext;
    }

    @PostMapping("/process")
    public String processPayment(@RequestBody PaymentRequest request) {
        PaymentStrategy strategy;
        switch (request.getPaymentType().toLowerCase()) {
            case "credit":
                strategy = new CreditCardPayment(request.getCardNumber(), request.getCardHolder());
                break;
            case "debit":
                strategy = new DebitCardPayment(request.getCardNumber(), request.getCardHolder());
                break;
            case "online":
                strategy = new OnlineBankingPayment(request.getBankAccount());
                break;
            default:
                return "Invalid payment type";
        }
        paymentContext.setPaymentStrategy(strategy);
        paymentContext.processPayment(request.getAmount());
        return "Payment processed successfully";
    }
} 