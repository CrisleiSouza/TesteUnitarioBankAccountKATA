package com.bank;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Représente une transaction(date, montant, balance)
 */
public class Transaction {
    private final LocalDate date;
    private final BigDecimal amount;
    private final BigDecimal balance;

    public Transaction(LocalDate date, BigDecimal amount, BigDecimal balance) {
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return date + " | " + amount + " | " + balance;
    }
}