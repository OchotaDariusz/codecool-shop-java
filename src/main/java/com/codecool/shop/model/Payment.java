package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.Currency;

public class Payment extends BaseModel {

    private Currency currency;
    private BigDecimal amountToPay;
    private boolean isPaid;
    private PaymentMethod paymentMethod;


    public Payment(Currency currency, BigDecimal amountToPay) {
        this.currency = currency;
        this.amountToPay = amountToPay;
    }

    private enum PaymentMethod {
        CREDIT_CARD,
        PAYPAL;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean isPaid() {
        return this.isPaid;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


}
