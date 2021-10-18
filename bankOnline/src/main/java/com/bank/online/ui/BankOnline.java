package com.bank.online.ui;

import com.bank.online.domain.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Map;
import java.util.function.BiConsumer;

import static com.bank.online.ui.InputData.*;
@Component
public class BankOnline {

    private AccountService accountService;
    private BiConsumer function;
    @Autowired
    public BankOnline(AccountService accountService) {
        this.accountService = accountService;
    }

    public void choiceAction() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                switch (choiceInt("Выберите действие: 1 - вывести все счета, 2 - положить деньги, 3 - снять деньги", 1, 3, reader)) {
                    case (1): {
                        System.out.println("Введите фамилию");
                        System.out.println(getAccounts(reader.readLine()));
                        break;
                    }
                    case (2): {
                        changeMoney(reader,(account, sum) -> depositMoney(account, sum));
                        break;
                    }
                    case (3): {
                        changeMoney(reader,(account, sum) -> withdrawMoney(account, sum));
                        break;
                    }
                    case (0): {
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<Long, BigDecimal> getAccounts(String name) {
        Map<Long, BigDecimal> map;
        map = accountService.getAccounts(name);
        return map;
    }
    public void depositMoney(Long account, BigDecimal sum) {
        try {
            accountService.changeMoney(account, sum, "+");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void withdrawMoney(Long account, BigDecimal sum) {
        try {
            accountService.changeMoney(account, sum, "-");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeMoney(BufferedReader reader, BiConsumer <Long, BigDecimal> function) {
        Long acc = choiceLong("Введите номер счета", 1L, 3000000000L,reader);
        if (acc != 0) {
            BigDecimal sum = choiceBigDecimal("Введите сумму", new BigDecimal("0"), new BigDecimal("1000000"), reader);
            if (sum.compareTo(BigDecimal.ZERO) != 0) {
                function.accept(acc,sum);
            }
        }
    }
}
