package org.example.spring_miniBank.service;

import org.example.spring_miniBank.model.Account;
import org.example.spring_miniBank.model.User;
import org.example.spring_miniBank.properties.AccountProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class AccountService {

    private int idCounter;
    private final Map<Integer, Account> accountsMap;
    private final AccountProperties accountProperties;

    @Autowired
    public AccountService(AccountProperties accountProperties) {
        this.accountProperties = accountProperties;
        this.accountsMap = new HashMap<>();
        this.idCounter = 0;
    }


    public Account createAccount(User user) {

        if (user == null) {
            throw new IllegalArgumentException("user cannot be null");
        }

        Account account = new Account(++idCounter, user.getId(), accountProperties.getDefaultAmount());
        accountsMap.put(idCounter, account);
        return account;
    }


    public Optional<Account> findAccountById(Integer accountId) {
        validatePositiveId(accountId, "account id");
        return Optional.ofNullable(accountsMap.get(accountId));
    }


    public List<Account> getUsersAccounts(Integer userId) {
        validatePositiveId(userId, "user id");
        return accountsMap.values().stream()
                .filter(account -> userId.equals(account.getUserId()))
                .toList();
    }


    public void depositAccount(Integer accountId, Integer amount) {
        validatePositiveId(accountId, "account id");
        validatePositiveAmount(amount);

        Account account = findAccountById(accountId).orElseThrow(() -> new IllegalArgumentException("No such account: id=%s".formatted(accountId)));
        account.setMoneyAmount(account.getMoneyAmount() + amount);
    }


    public void withdrawAccount(Integer accountId, Integer amount) {
        validatePositiveId(accountId, "account id");
        validatePositiveAmount(amount);

        Account account = findAccountById(accountId).orElseThrow(() -> new IllegalArgumentException("account not found"));

        if (account.getMoneyAmount() < amount) {
            throw new IllegalArgumentException("insufficient funds on account id=%s, moneyAmount=%s, attempted withdraw=%s"
                    .formatted(account.getId(), account.getMoneyAmount(), amount));
        }

        account.setMoneyAmount(account.getMoneyAmount() - amount);
    }


    public int transferAccount(Integer fromAccountId, Integer toAccountId, Integer amount) {
        validatePositiveId(fromAccountId, "from account id");
        validatePositiveId(toAccountId, "to account id");
        validatePositiveAmount(amount);

        if (fromAccountId.equals(toAccountId)) {
            throw new IllegalArgumentException("source and target account id must be different");
        }

        Account from = findAccountById(fromAccountId).orElseThrow(() -> new IllegalArgumentException("No such account: id%s".formatted(fromAccountId)));
        Account to = findAccountById(toAccountId).orElseThrow(() -> new IllegalArgumentException("No such account: id%s".formatted(toAccountId)));

        if (amount > from.getMoneyAmount()) {
            throw new IllegalArgumentException("insufficient funds on account id=%s, moneyAmount=%s, attempted transfer=%s"
                    .formatted(from.getId(), from.getMoneyAmount(), amount));
        }

        from.setMoneyAmount(from.getMoneyAmount() - amount);


        BigDecimal amountBD = BigDecimal.valueOf(amount);
        BigDecimal commissionRate = accountProperties.getTransferCommission();
        BigDecimal amountToTransferBD;

        if (from.getUserId() == to.getUserId()) {
            amountToTransferBD = amountBD;
        } else {
            BigDecimal commission = amountBD.multiply(commissionRate);
            amountToTransferBD = amountBD.subtract(commission).setScale(2, RoundingMode.HALF_UP);
        }


        int amountToTransfer = amountToTransferBD.intValue();

        to.setMoneyAmount(to.getMoneyAmount() + amountToTransfer);
        return amount - amountToTransfer;
    }


    public Account closeAccount(Integer accountId) {
        validatePositiveId(accountId, "account id");

        Account accountClose = findAccountById(accountId).orElseThrow(() -> new IllegalArgumentException("No such account: id=%s".formatted(accountId)));

        int userId = accountClose.getUserId();
        List<Account> userAccounts = getUsersAccounts(userId);

        if (userAccounts.size() == 1) {
            throw new IllegalArgumentException("account cannot be close because it is the only one");
        }

        accountsMap.remove(accountId);

        Account accountToTransferMoney = userAccounts.stream()
                .filter(account -> account.getId() != accountId)
                .findFirst()
                .orElseThrow();

        int newAmount = accountToTransferMoney.getMoneyAmount() + accountClose.getMoneyAmount();
        accountToTransferMoney.setMoneyAmount(newAmount);
        return accountClose;
    }


    private void validatePositiveId(Integer id, String fieldName) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(fieldName + " must be positive - [ >0 ]");
        }
    }


    private void validatePositiveAmount(Integer amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("amount must be positive - [ >0 ]");
        }
    }

}
