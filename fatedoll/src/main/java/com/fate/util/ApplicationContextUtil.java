package com.fate.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUtil {
    private static ApplicationContext context;

    static {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }
}