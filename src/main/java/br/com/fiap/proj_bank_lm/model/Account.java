package br.com.fiap.proj_bank_lm.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class Account {
    private Long id;
    private Long accountNumber;
    private String agency;
    private String holderName; 
    private Long cpf;
    private LocalDate openDate;
    private BigDecimal openingBalance;
    private boolean activate;
    private TypeAccount typeAccount;
}
