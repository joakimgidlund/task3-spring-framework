package se.yrgo.services.customers;

import java.util.HashMap;
import java.util.List;

import se.yrgo.domain.Call;
import se.yrgo.domain.Customer;

public class CustomerManagementMockImpl implements CustomerManagementService {
	private HashMap<String, Customer> customerMap;

	public CustomerManagementMockImpl() {
		customerMap = new HashMap<String, Customer>();
		customerMap.put("OB74", new Customer("OB74", "Fargo Ltd", "some notes"));
		customerMap.put("NV10", new Customer("NV10", "North Ltd", "some other notes"));
		customerMap.put("RM210", new Customer("RM210", "River Ltd", "some more notes"));
	}

	@Override
	public void newCustomer(Customer newCustomer) {
		this.customerMap.put(newCustomer.getCustomerId(), newCustomer);
	}

	@Override
	public void updateCustomer(Customer changedCustomer) {
		this.customerMap.replace(changedCustomer.getCustomerId(), changedCustomer);
	}

	@Override
	public void deleteCustomer(Customer oldCustomer) {
		this.customerMap.remove(oldCustomer.getCustomerId());
	}

	@Override
	public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
		return this.customerMap.get(customerId);
	}

	@Override
	public List<Customer> findCustomersByName(String name) {
		return this.customerMap.values().stream().filter(c -> c.getCompanyName().equals(name)).toList();
	}

	@Override
	public List<Customer> getAllCustomers() {
		return this.customerMap.values().stream().toList();
	}

	@Override
	public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException {
		return this.customerMap.get(customerId);
	}

	@Override
	public void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException {
		// First find the customer
		// Call the addCall on the customer
		this.customerMap.get(customerId).addCall(callDetails);
	}

}
