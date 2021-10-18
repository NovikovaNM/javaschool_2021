package com.bank.online.data;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public interface AccountRepository {
    Map<Long, BigDecimal> getAccounts(String name);

    void changeMoney(Long account, BigDecimal sum, String c) throws IOException;
}
