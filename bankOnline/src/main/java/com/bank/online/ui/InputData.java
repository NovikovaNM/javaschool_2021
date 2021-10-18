package com.bank.online.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;

public class InputData {
    public static int choiceInt(String msg, int min, int max, BufferedReader reader) {
        int result = -1;
        do {
            System.out.println(msg + " Для выхода введите 0");
            try {
                String str = reader.readLine();
                result = Integer.parseInt(str);
                if (result >= min && result <= max) {
                    return result;
                }
            } catch (NumberFormatException | IOException e) {
            }
        } while (result != 0);
        return 0;
    }

    public static long choiceLong(String msg, Long min, Long max, BufferedReader reader) {
        long result = -1;
        do {
            System.out.println(msg + " Для выхода введите 0");
            try {
                String str = reader.readLine();
                result = Long.parseLong(str);
                if (result >= min && result <= max) {
                    return result;
                }
            } catch (NumberFormatException| IOException e) {}
        } while (result != 0) ;
        return 0;
    }

    public static BigDecimal choiceBigDecimal(String msg, BigDecimal min, BigDecimal max, BufferedReader reader) {
        BigDecimal result = BigDecimal.valueOf(-1L);
        do {
            System.out.println(msg + " Для выхода введите 0");
            try {
                String str = reader.readLine();
                result = new BigDecimal(str);
                if (result.compareTo(min)>=0 && result.compareTo(max) <= 0) {
                    return result;
                }
            } catch (NumberFormatException| IOException e) {}
        } while (!result.equals(0)) ;
        return BigDecimal.ZERO;
    }
}