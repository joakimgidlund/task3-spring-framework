package se.yrgo.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import se.yrgo.services.customers.CustomerManagementService;

public class SimpleClient {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        CustomerManagementService customerManagementService = context.getBean("customerManagementService", CustomerManagementService.class);

        customerManagementService.getAllCustomers().forEach(System.out::println);

        context.close();
    }
}