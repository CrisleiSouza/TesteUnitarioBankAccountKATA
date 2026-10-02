package com.bank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente un compte bancaire avec dépôts, retraits et impression des relevés.
 */
public class BankAccount {
    private final List<Transaction> transactions = new ArrayList<>();
    private BigDecimal balance = BigDecimal.ZERO;

    public void deposit(BigDecimal amount) {
        validateAmount(amount);
        balance = balance.add(amount);
        transactions.add(new Transaction(LocalDate.now(), amount, balance));
    }

    public void withdraw(BigDecimal amount) {
        validateAmount(amount);
        balance = balance.subtract(amount);
        transactions.add(new Transaction(LocalDate.now(), amount.negate(), balance));
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif.");
        }
    }

    public void printStatement() {
        System.out.println("DATE | AMOUNT | BALANCE");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public BigDecimal getBalance() {
        return balance;
    }
}