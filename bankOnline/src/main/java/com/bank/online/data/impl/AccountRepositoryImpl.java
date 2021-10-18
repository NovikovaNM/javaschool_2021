package com.bank.online.data.impl;

import com.bank.online.data.AccountRepository;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Repository
public class AccountRepositoryImpl implements AccountRepository {
    public static final String ACCOUNTS_FILE_NAME = "bankOnline\\accounts.txt";
    @Override
    public Map<Long, BigDecimal> getAccounts(String name) {
        Map<String, Map<Long,BigDecimal>> map = getDataFromFile();
        return map.get(name);
    }

    @Override
    public void changeMoney(Long account, BigDecimal sum, String c) throws IOException {
        Path path = Paths.get(ACCOUNTS_FILE_NAME);
        List<String> list = Files.readAllLines(path);
        for (int i = 0; i < list.size(); i++) {
            String[] values = list.get(i).split(" ");
            if (account == Long.parseLong(values[0])) {
                if ("-".equals(c)) {
                    BigDecimal diff = new BigDecimal(values[2]).subtract(sum);
                    if (diff.compareTo(BigDecimal.ZERO) >= 0) {
                        values[2] = diff.toString();
                    } else {
                        throw new IllegalArgumentException("Недостаточно средств");
                    }
                } else {
                    values[2] = new BigDecimal(values[2]).add(sum).toString();
                }
                list.set(i,String.join(" ",values));
            }
        }
        Files.write(path, list);
    }
    private Map<String, Map<Long,BigDecimal>> getDataFromFile() {
        try (Stream<String> stream = Files.lines(Paths.get(ACCOUNTS_FILE_NAME))) {
            return stream.collect(Collectors.groupingBy(line -> line.split(" ")[1],
                    Collectors.toMap(line -> Long.parseLong(line.split(" ")[0]),line -> new BigDecimal(line.split(" ")[2]))));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
