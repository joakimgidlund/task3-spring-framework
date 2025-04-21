package se.yrgo.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import se.yrgo.domain.Action;
import se.yrgo.domain.Call;
import se.yrgo.domain.Customer;
import se.yrgo.services.calls.CallHandlingService;
import se.yrgo.services.customers.CustomerManagementService;
import se.yrgo.services.customers.CustomerNotFoundException;

public class SimpleClient {

    public static void main(String[] args) throws CustomerNotFoundException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        CustomerManagementService customerManagementService = context.getBean("customerManagementService", CustomerManagementService.class);
        customerManagementService.getAllCustomers().forEach(System.out::println);

        CallHandlingService callHandlingService = context.getBean("callHandlingService", CallHandlingService.class);

        Customer customer = customerManagementService.getAllCustomers().get(0);

        Call call = new Call("test call");
        List<Action> actionColl = new ArrayList<>();

        actionColl.add(new Action("make tests", null, "testuser"));

        callHandlingService.recordCall(customer.getCustomerId(), call, actionColl);

        context.close();
    }
}