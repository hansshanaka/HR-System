/*
 * gets the bean of mailMail from the applicationContext.xml file and calls the sendMail method of MailMail class
 */
package com.misyn.hrsystem.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Shanaka
 * This class contains to check email, if the system fails to send email
 */
public class Test {

    public static void main(String[] args) {

    	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");

    	MailMail mm = (MailMail) context.getBean("mailMail");
        mm.sendMail("pradeeppra@gmail.com",
    		   "shanaka@mi-synergy.com",
    		   "MI Synergy",
    		   "Testing only \n\n Hello Spring Email Sender");
    }

}
