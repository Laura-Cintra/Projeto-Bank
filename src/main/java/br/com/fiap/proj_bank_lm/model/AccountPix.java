package br.com.fiap.proj_bank_lm.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class AccountPix extends AccountMovements {
    private Long targetAccount;

    public AccountPix(Long accountNumber, BigDecimal amount, Long targetAccount) {
        super(accountNumber, amount);
        this.targetAccount = targetAccount;
    }
}