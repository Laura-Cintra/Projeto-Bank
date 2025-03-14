package br.com.fiap.proj_bank_lm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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

        account.setId(Math.abs(new Random().nextLong()));

        repository.add(account);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Account> getToId(@PathVariable Long id) {
        log.info("Buscando uma conta pelo id: " + id);
        return ResponseEntity.ok(getAccount(id, "id"));
    }
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Account> getToCpf(@PathVariable Long cpf) {
        log.info("Buscando uma conta pelo cpf: " + cpf);
        return ResponseEntity.ok(getAccount(cpf, "cpf"));
    }

    private Account getAccount(Long identify, String type) {
        return repository.stream()
                .filter(c -> {
                    switch (type) {
                        case "id": return c.getId().equals(identify);
                        case "cpf": return c.getCpf().equals(identify);
                        default: return false;
                    }
                })
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));
    }
    
}
