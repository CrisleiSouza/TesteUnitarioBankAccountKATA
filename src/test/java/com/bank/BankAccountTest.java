package com.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {
    private BankAccount account;

    @BeforeEach
    public void setup() {
        account = new BankAccount();
    }

    @Test
    public void should_deposit_money() {
        account.deposit(BigDecimal.valueOf(1000));
        assertEquals(BigDecimal.valueOf(1000), account.getBalance());
    }

    @Test
    public void should_withdraw_money() {
        account.deposit(BigDecimal.valueOf(1000));
        account.withdraw(BigDecimal.valueOf(300));
        assertEquals(BigDecimal.valueOf(700), account.getBalance());
    }

    @Test
    public void should_store_transactions() {
        account.deposit(BigDecimal.valueOf(500));
        account.withdraw(BigDecimal.valueOf(200));
        assertEquals(2, account.getTransactions().size());
    }

    @Test
    public void should_not_allow_negative_deposit() {
        assertThrows(IllegalArgumentException.class, () ->
            account.deposit(BigDecimal.valueOf(-100)));
    }

    @Test
    public void should_not_allow_zero_withdrawal() {
        assertThrows(IllegalArgumentException.class, () ->
            account.withdraw(BigDecimal.ZERO));
    }
}