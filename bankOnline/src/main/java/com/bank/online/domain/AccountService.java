package com.bank.online.domain;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public interface AccountService {
    Map<Long, BigDecimal> getAccounts(String name);

    //void depositMoney(Long account, BigDecimal sum);

    void changeMoney(Long account, BigDecimal sum, String c) throws IOException;
}
