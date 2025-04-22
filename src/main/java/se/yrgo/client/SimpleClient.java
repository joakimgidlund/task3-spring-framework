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

        CustomerManagementService customerManagementService = context.getBean("customerManagementService",
                CustomerManagementService.class);

        // Print every customer
        customerManagementService.getAllCustomers().forEach(System.out::println);

        CallHandlingService callHandlingService = context.getBean("callHandlingService", CallHandlingService.class);

        // Get a customer that exists to record a call in CallHandlingService
        Customer customer = customerManagementService.getAllCustomers().get(0);

        Call call = new Call("test call");
        List<Action> actionColl = new ArrayList<>();

        actionColl.add(new Action("make tests", null, "testuser"));

        callHandlingService.recordCall(customer.getCustomerId(), call, actionColl);

        // Print the calls of "customer" to make sure the new call was added
        customerManagementService.getFullCustomerDetail(customer.getCustomerId()).getCalls()
                .forEach(System.out::println);

        context.close();
    }
}