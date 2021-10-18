package com.bank.online;

import com.bank.online.ui.BankOnline;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Application {
    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext("com.bank.online");
        BankOnline bankOnline = context.getBean(BankOnline.class);
        bankOnline.choiceAction();
    }
}
