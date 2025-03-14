package br.com.fiap.proj_bank_lm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.proj_bank_lm.model.Account;
import br.com.fiap.proj_bank_lm.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
    AccountService accountService = new AccountService();
    List<Account> repository = new ArrayList<>();
    private Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping
    public List<Account> index() {
        return repository;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Account> create(@RequestBody Account account) {
        log.info("Cadastrando a conta " + account.getAccountNumber());

        if (!accountService.validationAccount(account)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro no corpo da requisição");
        }

        account.setAccountNumber(Math.abs(new Random().nextLong()));

        repository.add(account);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Account> getToId(@PathVariable Long id) {
        log.info("Buscando uma conta pelo id: " + id);
        return ResponseEntity.ok(getById(id));
    }
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Account> getToCpf(@PathVariable String cpf) {
        log.info("Buscando uma conta pelo cpf: " + cpf);
        return ResponseEntity.ok(getByCpf(cpf));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Account> closeAccount(@PathVariable Long id) {
        log.info("Encerrando a conta: " + id);
        getById(id).setActivate(false);
        return ResponseEntity.noContent().build();
    }
    // métodos auxiliares
    private Account getByCpf(String cpf) {
        return repository.stream()
                .filter(c -> {
                    return c.getCpf().equals(cpf);
                })
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada para o CPF " + cpf));
    }

    private Account getById(Long id) {
        return repository.stream()
                .filter(c -> {
                    return c.getAccountNumber().equals(id);
                })
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada para o CPF " + id));
    }


}
