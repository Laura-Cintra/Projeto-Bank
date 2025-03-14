package br.com.fiap.proj_bank_lm.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class AccountMovements{
    private Long accountNumber;
    private BigDecimal amount;

    public AccountMovements(Long accountNumber, BigDecimal amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }
}