package com.bank.online.domain.impl;

import com.bank.online.data.AccountRepository;
import com.bank.online.domain.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Map<Long, BigDecimal> getAccounts(String name) {
        if (name == null || name.length()==0) {
            throw new IllegalArgumentException("Не введена фамилия");
        }
        return accountRepository.getAccounts(name);
    }

    @Override
    public void changeMoney(Long account, BigDecimal sum, String c) throws IOException {
        if (account <=0 || sum.compareTo(BigDecimal.ZERO)<=0 || (!"+".equals(c) && !"-".equals(c))) {
            throw new IllegalArgumentException("Не корректны входные данные");
        }
        if (sum.compareTo(BigDecimal.valueOf(50000L))>0){
            throw new IllegalArgumentException("Сумма должна быть меньше 50 000");
        }
        if (sum.compareTo(BigDecimal.valueOf(10000L))>0){
            sum = sum.multiply(BigDecimal.valueOf(0.99));
        };
        accountRepository.changeMoney(account, sum, c);
    }
}
